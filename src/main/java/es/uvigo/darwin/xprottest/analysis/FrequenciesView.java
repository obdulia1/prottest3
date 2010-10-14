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
package es.uvigo.darwin.xprottest.analysis;

import es.uvigo.darwin.prottest.util.ProtTestAlignment;
import es.uvigo.darwin.prottest.util.printer.ProtTestFormattedOutput;
import pal.alignment.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * This frame shows the observed frequencies of every amino-acid on
 * the selected alignment.
 * 
 * @author  Diego Darriba
 */
public class FrequenciesView extends javax.swing.JFrame {
    
    private int freqWidth = 5;
    
    /** Creates new form FrequenciesView */
    public FrequenciesView(Alignment alignment) {
        initComponents();
        double[] frequencies = ProtTestAlignment.getFrequencies(alignment);
        for (int i = 0; i < frequencies.length; i++) {
            tableFrequencies.setValueAt(ProtTestAlignment.charOfIndex(i), i, 0);
            tableFrequencies.setValueAt(
                ProtTestFormattedOutput.getDecimalString(frequencies[i], freqWidth), i, 1);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableFrequencies = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(es.uvigo.darwin.xprottest.XProtTestApp.class).getContext().getResourceMap(FrequenciesView.class);
        tableFrequencies.setBackground(resourceMap.getColor("tableFrequencies.background")); // NOI18N
        DefaultTableModel freqTableModel =
        new javax.swing.table.DefaultTableModel() {
            public Class getColumnClass(int column) {
                if (column >= 0 && column <= getColumnCount()){
                    try {
                        return getValueAt(0, column).getClass();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return Object.class;
                    }
                }
                else
                return Object.class;
            }
            public boolean isCellEditable(int row, int col) { return false; }
        };
        freqTableModel.addColumn("AA", new String[20][1]);
        freqTableModel.addColumn("Frequency", new Double[20][1]);
        tableFrequencies.setModel(freqTableModel
        );
        RowSorter<TableModel> freqSorter = new TableRowSorter<TableModel>(freqTableModel);
        tableFrequencies.setRowSorter(freqSorter);
        tableFrequencies.setColumnSelectionAllowed(true);
        tableFrequencies.setName("tableFrequencies"); // NOI18N
        jScrollPane1.setViewportView(tableFrequencies);

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/analysis/resources/FrequenciesView"); // NOI18N
        lblTitle.setText(bundle.getString("msg-title")); // NOI18N
        lblTitle.setName("lblTitle"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tableFrequencies;
    // End of variables declaration//GEN-END:variables
    
}
