/*
Copyright (C) 2009  Diego Darriba

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package es.uvigo.darwin.prottest.util.checkpoint;

import static es.uvigo.darwin.prottest.global.ApplicationGlobals.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Comparator;

import es.uvigo.darwin.prottest.util.checkpoint.status.ApplicationStatus;

public class CheckPointManager {

    private ApplicationStatus lastCheckpoint;
    private File snapshotDir;
    private File snapshotFile;
    private boolean cpEnabled;
    private final String SNAPSHOT_PREFIX = "SNAPSHOT";
    private final String SNAPSHOT_SUFFIX = "snp";

    public ApplicationStatus getLastCheckpoint() {
        return lastCheckpoint;
    }

    public CheckPointManager() {
        String snapshotDirName = APPLICATION_PROPERTIES.getProperty("snapshot_dir");
        cpEnabled = snapshotDirName != null;
        if (cpEnabled) {
            snapshotDir = new File(snapshotDirName);
            if (!snapshotDir.exists()) {
                snapshotDir.mkdirs();
            }
        }
    }

    public synchronized boolean setStatus(ApplicationStatus newCheckpoint) {
        if (cpEnabled) {
            // check integrity
            boolean validStatus = (lastCheckpoint == null || lastCheckpoint.isCompatible(newCheckpoint));

            if (validStatus) {
                lastCheckpoint = newCheckpoint;
                saveStatus(lastCheckpoint);
            } else {
                return false;
            }
        }
        return true;
    }

    private synchronized void saveStatus(ApplicationStatus checkpoint) {

        if (cpEnabled) {
            try {
                if (snapshotFile == null) {
                    // create new Snapshot file
                    snapshotFile = File.createTempFile(
                            SNAPSHOT_PREFIX, SNAPSHOT_SUFFIX, snapshotDir);
                }

                if (snapshotFile.exists()) {
                    snapshotFile.delete();
                }
                snapshotFile.createNewFile();

                FileOutputStream fos = new FileOutputStream(snapshotFile);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(checkpoint);
                out.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings("unchecked")
    public boolean loadStatus(ApplicationStatus initialStatus) {

        boolean loaded = false;
        if (cpEnabled) {
            if (snapshotDir != null && snapshotDir.exists() && snapshotDir.canRead()) {
                FilenameFilter filter = new FilenameFilter() {

                    @Override
                    public boolean accept(File dir, String name) {

                        return name.startsWith(SNAPSHOT_PREFIX) && name.endsWith(SNAPSHOT_SUFFIX);
                    }
                };
                File[] snapshotFiles = snapshotDir.listFiles(filter);
                Arrays.sort(snapshotFiles, new Comparator() {

                    public int compare(Object o1, Object o2) {

                        if (((File) o1).lastModified() > ((File) o2).lastModified()) {
                            return -1;
                        } else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
                            return +1;
                        } else {
                            return 0;
                        }
                    }
                });

                ObjectInputStream in = null;
                for (File statusFile : snapshotFiles) {
                    try {
                        //Construct the ObjectInputStream object
                        in = new ObjectInputStream(new FileInputStream(statusFile));

                        Object obj = null;

                        obj = in.readObject();

                        if (obj instanceof ApplicationStatus) {
                            if (initialStatus.isCompatible((ApplicationStatus) obj)) {
                                this.snapshotFile = statusFile;
                                this.lastCheckpoint = (ApplicationStatus) obj;
                                loaded = true;
                                break;
                            }
                        }

                    } catch (EOFException ex) {
                        // This exception will be caught when EOF is reached
                        // There is no data in the status file
                        statusFile.delete();
                    } catch (ClassNotFoundException ex) {
                        // This exception will be caught when there is no 
                        // serialized data in the status file
                        statusFile.delete();
                    } catch (FileNotFoundException ex) {

                    } catch (IOException ex) {

                    } finally {
                        //Close the ObjectInputStream
                        try {
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        return loaded;

    }

    public void done() {
        if (snapshotFile != null) {
            snapshotFile.delete();
        }
        lastCheckpoint = null;
    }
}
