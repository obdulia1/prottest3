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
package es.uvigo.darwin.xprottest.results;

import static es.uvigo.darwin.prottest.global.ApplicationGlobals.*;
import static es.uvigo.darwin.prottest.selection.printer.PrintFramework.getDisplayValue;

import es.uvigo.darwin.prottest.model.Model;
import es.uvigo.darwin.prottest.facade.util.SelectionChunk;
import es.uvigo.darwin.prottest.selection.InformationCriterion;
import es.uvigo.darwin.prottest.selection.model.SelectionModel;
import es.uvigo.darwin.prottest.util.ProtTestAlignment;
import es.uvigo.darwin.prottest.util.printer.ProtTestFormattedOutput;
import es.uvigo.darwin.prottest.util.printer.ProtTestPrinter;
import java.util.Collection;
import javax.swing.JTable;
import org.jdesktop.application.Action;
import pal.alignment.Alignment;
import javax.swing.RowSorter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import es.uvigo.darwin.xprottest.XProtTestView;
import java.util.HashMap;

/**
 * The view of the model selection results. It includes a selection
 * under all supported information criterions.
 * 
 * @author Diego Darriba
 */
public class ResultsView extends javax.swing.JFrame {

    private static final int AIC_TAB = 0;
    private static final int BIC_TAB = 1;
    private static final int AICC_TAB = 2;
    private static final int DT_TAB = 3;
    private int rows;
    javax.swing.table.DefaultTableModel aicTableModel,
            bicTableModel,
            aiccTableModel,
            dtTableModel;
    private Model[] models;
    private Alignment alignment;
    private XProtTestView mainFrame;
    private int sampleSizeMode = DEFAULT_SAMPLE_SIZE_MODE;
    private double sampleSize = 0.0;
    private HashMap<Integer, SelectionChunk> aicResults, bicResults, aiccResults, dtResults;

//    private boolean existInvModels = false,
//            existGammaModels = false,
//            existGammaInvModels = false,
//            existFModels = false;

    private void loadCache(double confidenceInterval) {
        // criterion cache
        aicResults = new HashMap<Integer, SelectionChunk>(SIZE_MODE_NAMES.length - 1);
        bicResults = new HashMap<Integer, SelectionChunk>(SIZE_MODE_NAMES.length - 1);
        aiccResults = new HashMap<Integer, SelectionChunk>(SIZE_MODE_NAMES.length - 1);
        dtResults = new HashMap<Integer, SelectionChunk>(SIZE_MODE_NAMES.length - 1);
        for (int sizeMode : SIZE_MODE_VALUES) {
            if (sizeMode != SIZEMODE_USERSIZE) {
                aicResults.put(sizeMode, mainFrame.getFacade().computeInformationCriterion(alignment, models,
                        SelectionChunk.AIC, sizeMode, 0.0, confidenceInterval));
                bicResults.put(sizeMode, mainFrame.getFacade().computeInformationCriterion(alignment, models,
                        SelectionChunk.BIC, sizeMode, 0.0, confidenceInterval));
                aiccResults.put(sizeMode, mainFrame.getFacade().computeInformationCriterion(alignment, models,
                        SelectionChunk.AICC, sizeMode, 0.0, confidenceInterval));
                dtResults.put(sizeMode, mainFrame.getFacade().computeInformationCriterion(alignment, models,
                        SelectionChunk.DT, sizeMode, 0.0, confidenceInterval));
            }
        }
    }

    /** Creates new form ResultsView */
    public ResultsView(XProtTestView mainFrame, Alignment alignment, Model[] models, boolean correctDone) {
        this.mainFrame = mainFrame;
        this.alignment = alignment;
        this.rows = models.length;
        this.models = models;

//        for (Model model : models) {
//            if (!existInvModels && model.isInv() && !model.isGamma()) {
//                existInvModels = true;
//            } else if (!existGammaModels && !model.isInv() && model.isGamma()) {
//                existGammaModels = true;
//            } else if (!existGammaInvModels && model.isInv() && model.isGamma()) {
//                existGammaInvModels = true;
//            } else if (!existFModels && model.isPlusF()) {
//                existFModels = true;
//            }
//        }

        initComponents();
        loadCache(Double.parseDouble(sliderConfidenceInterval.getValue() + "") / 100);
        fillInResults(DEFAULT_SAMPLE_SIZE_MODE, 0.0);

        lblNotComplete.setVisible(!correctDone);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sampleSizeModeButtonGroup = new javax.swing.ButtonGroup();
        resultsTabbedPane = new javax.swing.JTabbedPane();
        aicPanel = new javax.swing.JPanel();
        aicScrollPanel = new javax.swing.JScrollPane();
        aicTable = new javax.swing.JTable();
        aicDataPanel = new javax.swing.JPanel();
        lblAICOverall = new javax.swing.JLabel();
        lblAICOverallAlpha = new javax.swing.JLabel();
        lblAICOverallAlphaInv = new javax.swing.JLabel();
        lblAICOverallInvAlpha = new javax.swing.JLabel();
        lblAICOverallInv = new javax.swing.JLabel();
        displayAICoAlpha = new javax.swing.JLabel();
        displayAICoAlphaInv = new javax.swing.JLabel();
        displayAICoInvAlpha = new javax.swing.JLabel();
        displayAICoInv = new javax.swing.JLabel();
        lblAICPI = new javax.swing.JLabel();
        lblAICImpInv = new javax.swing.JLabel();
        lblAICImpAlpha = new javax.swing.JLabel();
        lblAICImpF = new javax.swing.JLabel();
        lblAICImpAlphaInv = new javax.swing.JLabel();
        displayAICImpInv = new javax.swing.JLabel();
        displayAICImpAlpha = new javax.swing.JLabel();
        displayAICImpAlphaInv = new javax.swing.JLabel();
        displayAICImpF = new javax.swing.JLabel();
        bicPanel = new javax.swing.JPanel();
        bicScrollPanel = new javax.swing.JScrollPane();
        bicTable = new javax.swing.JTable();
        bicDataPanel = new javax.swing.JPanel();
        lblBICOverall = new javax.swing.JLabel();
        lblBICOverallAlpha = new javax.swing.JLabel();
        lblBICOverallAlphaInv = new javax.swing.JLabel();
        lblBICOverallInvAlpha = new javax.swing.JLabel();
        lblBICOverallInv = new javax.swing.JLabel();
        displayBICoAlpha = new javax.swing.JLabel();
        displayBICoAlphaInv = new javax.swing.JLabel();
        displayBICoInvAlpha = new javax.swing.JLabel();
        displayBICoInv = new javax.swing.JLabel();
        lblBICImpInv = new javax.swing.JLabel();
        lblBICImpAlpha = new javax.swing.JLabel();
        lblBICImpF = new javax.swing.JLabel();
        lblBICImpAlphaInv = new javax.swing.JLabel();
        displayBICImpInv = new javax.swing.JLabel();
        displayBICImpAlpha = new javax.swing.JLabel();
        displayBICImpAlphaInv = new javax.swing.JLabel();
        displayBICImpF = new javax.swing.JLabel();
        lblBICPI = new javax.swing.JLabel();
        aiccPanel = new javax.swing.JPanel();
        aiccScrollPanel = new javax.swing.JScrollPane();
        aiccTable = new javax.swing.JTable();
        aiccDataPanel = new javax.swing.JPanel();
        lblAICcOverall = new javax.swing.JLabel();
        lblAICcOverallAlpha = new javax.swing.JLabel();
        lblAICcOverallAlphaInv = new javax.swing.JLabel();
        lblAICcOverallInvAlpha = new javax.swing.JLabel();
        lblAICcOverallInv = new javax.swing.JLabel();
        displayAICcoAlpha = new javax.swing.JLabel();
        displayAICcoAlphaInv = new javax.swing.JLabel();
        displayAICcoInvAlpha = new javax.swing.JLabel();
        displayAICcoInv = new javax.swing.JLabel();
        lblAICcPI = new javax.swing.JLabel();
        lblAICcImpInv = new javax.swing.JLabel();
        lblAICcImpAlpha = new javax.swing.JLabel();
        lblAICcImpF = new javax.swing.JLabel();
        lblAICcImpAlphaInv = new javax.swing.JLabel();
        displayAICcImpInv = new javax.swing.JLabel();
        displayAICcImpAlpha = new javax.swing.JLabel();
        displayAICcImpAlphaInv = new javax.swing.JLabel();
        displayAICcImpF = new javax.swing.JLabel();
        dtPanel = new javax.swing.JPanel();
        dtScrollPanel = new javax.swing.JScrollPane();
        dtTable = new javax.swing.JTable();
        dtDataPanel = new javax.swing.JPanel();
        lblDTOverall = new javax.swing.JLabel();
        lblDTOverallAlpha = new javax.swing.JLabel();
        lblDTOverallAlphaInv = new javax.swing.JLabel();
        lblDTOverallInvAlpha = new javax.swing.JLabel();
        lblDTOverallInv = new javax.swing.JLabel();
        displayDToAlpha = new javax.swing.JLabel();
        displayDToAlphaInv = new javax.swing.JLabel();
        displayDToInvAlpha = new javax.swing.JLabel();
        displayDToInv = new javax.swing.JLabel();
        lblDTPI = new javax.swing.JLabel();
        lblDTImpInv = new javax.swing.JLabel();
        lblDTImpAlpha = new javax.swing.JLabel();
        lblDTImpF = new javax.swing.JLabel();
        lblDTImpAlphaInv = new javax.swing.JLabel();
        displayDTImpInv = new javax.swing.JLabel();
        displayDTImpAlpha = new javax.swing.JLabel();
        displayDTImpAlphaInv = new javax.swing.JLabel();
        displayDTImpF = new javax.swing.JLabel();
        lblSampleSizeMode = new javax.swing.JLabel();
        txtSampleSize = new javax.swing.JTextField();
        sizeModeAlignment = new javax.swing.JRadioButton();
        sizeModeShannon = new javax.swing.JRadioButton();
        sizeModeVar = new javax.swing.JRadioButton();
        sizeModeShannonNL = new javax.swing.JRadioButton();
        sizeModeNL = new javax.swing.JRadioButton();
        sizeModeCustom = new javax.swing.JRadioButton();
        sliderConfidenceInterval = new javax.swing.JSlider();
        lblConfInt = new javax.swing.JLabel();
        lblSampleSize = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblCommandLine = new javax.swing.JTextArea();
        lblComandLineLabel = new javax.swing.JLabel();
        lblSelectedModelLabel = new javax.swing.JLabel();
        lblSelectedModel = new javax.swing.JLabel();
        lblNotComplete = new javax.swing.JLabel();
        lblConfidence = new javax.swing.JLabel();
        btnExport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ResultsView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        resultsTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        resultsTabbedPane.setName("resultsTabbedPane"); // NOI18N

        aicPanel.setName("aicPanel"); // NOI18N

        aicScrollPanel.setName("aicScrollPanel"); // NOI18N

        aicTableModel =
        new javax.swing.table.DefaultTableModel() {
            public Class getColumnClass(int column) {
                if (column == 0)
                return String.class;
                if (column >= 1 && column <= getColumnCount()) {
                    try {
                        return getValueAt(0, column).getClass();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return Object.class;
                    }
                } else
                return Object.class;
            }

            public boolean isCellEditable(int row, int col) { return false; }
        };
        aicTableModel.addColumn("Model", new String[rows][1]);
        aicTableModel.addColumn("-LnL", new Double[rows][1]);
        aicTableModel.addColumn("AIC", new Double[rows][1]);
        aicTableModel.addColumn("deltaAIC", new Double[rows][1]);
        aicTableModel.addColumn("AIC weight", new Double[rows][1]);
        aicTable.setModel(
            aicTableModel
        );
        aicTable.setCellSelectionEnabled(true);
        aicTable.setName("aicTable"); // NOI18N
        RowSorter<TableModel> aicSorter = new TableRowSorter<TableModel>(aicTableModel);
        aicTable.setRowSorter(aicSorter);

        SelectionListener listener = new SelectionListener(aicTable);
        aicTable.getSelectionModel().addListSelectionListener(listener);
        aicTable.getColumnModel().getSelectionModel()
        .addListSelectionListener(listener);
        aicScrollPanel.setViewportView(aicTable);

        aicDataPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        aicDataPanel.setName("aicDataPanel"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/results/resources/ResultsView"); // NOI18N
        lblAICOverall.setText(bundle.getString("lbl-overall")); // NOI18N
        lblAICOverall.setName("lblAICOverall"); // NOI18N

        lblAICOverallAlpha.setText(bundle.getString("lbl-overall-alpha")); // NOI18N
        lblAICOverallAlpha.setName("lblAICOverallAlpha"); // NOI18N

        lblAICOverallAlphaInv.setText(bundle.getString("lbl-overall-alpha-inv")); // NOI18N
        lblAICOverallAlphaInv.setName("lblAICOverallAlphaInv"); // NOI18N

        lblAICOverallInvAlpha.setText(bundle.getString("lbl-overall-inv-alpha")); // NOI18N
        lblAICOverallInvAlpha.setName("lblAICOverallInvAlpha"); // NOI18N

        lblAICOverallInv.setText(bundle.getString("lbl-overall-inv")); // NOI18N
        lblAICOverallInv.setName("lblAICOverallInv"); // NOI18N

        displayAICoAlpha.setText(resourceMap.getString("displayAICoAlpha.text")); // NOI18N
        displayAICoAlpha.setName("displayAICoAlpha"); // NOI18N

        displayAICoAlphaInv.setText(resourceMap.getString("displayAICoAlphaInv.text")); // NOI18N
        displayAICoAlphaInv.setName("displayAICoAlphaInv"); // NOI18N

        displayAICoInvAlpha.setText(resourceMap.getString("displayAICoInvAlpha.text")); // NOI18N
        displayAICoInvAlpha.setName("displayAICoInvAlpha"); // NOI18N

        displayAICoInv.setText(resourceMap.getString("displayAICoInv.text")); // NOI18N
        displayAICoInv.setName("displayAICoInv"); // NOI18N

        lblAICPI.setText(resourceMap.getString("lblAICPI.text")); // NOI18N
        lblAICPI.setName("lblAICPI"); // NOI18N

        lblAICImpInv.setText(resourceMap.getString("lblAICImpInv.text")); // NOI18N
        lblAICImpInv.setName("lblAICImpInv"); // NOI18N

        lblAICImpAlpha.setText(resourceMap.getString("lblAICImpAlpha.text")); // NOI18N
        lblAICImpAlpha.setName("lblAICImpAlpha"); // NOI18N

        lblAICImpF.setText(resourceMap.getString("lblAICImpF.text")); // NOI18N
        lblAICImpF.setName("lblAICImpF"); // NOI18N

        lblAICImpAlphaInv.setText(resourceMap.getString("lblAICImpAlphaInv.text")); // NOI18N
        lblAICImpAlphaInv.setName("lblAICImpAlphaInv"); // NOI18N

        displayAICImpInv.setText(resourceMap.getString("displayAICImpInv.text")); // NOI18N
        displayAICImpInv.setName("displayAICImpInv"); // NOI18N

        displayAICImpAlpha.setText(resourceMap.getString("displayAICImpAlpha.text")); // NOI18N
        displayAICImpAlpha.setName("displayAICImpAlpha"); // NOI18N

        displayAICImpAlphaInv.setText(resourceMap.getString("displayAICImpAlphaInv.text")); // NOI18N
        displayAICImpAlphaInv.setName("displayAICImpAlphaInv"); // NOI18N

        displayAICImpF.setText(resourceMap.getString("displayAICImpF.text")); // NOI18N
        displayAICImpF.setName("displayAICImpF"); // NOI18N

        javax.swing.GroupLayout aicDataPanelLayout = new javax.swing.GroupLayout(aicDataPanel);
        aicDataPanel.setLayout(aicDataPanelLayout);
        aicDataPanelLayout.setHorizontalGroup(
            aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aicDataPanelLayout.createSequentialGroup()
                .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aicDataPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAICOverallAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICOverallAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICOverallInvAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICOverallInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayAICoInv)
                            .addComponent(displayAICoInvAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(displayAICoAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(displayAICoAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                        .addGap(40, 40, 40))
                    .addGroup(aicDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAICOverall, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aicDataPanelLayout.createSequentialGroup()
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblAICImpAlpha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICImpInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICImpF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICImpAlphaInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(displayAICImpInv, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(displayAICImpAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayAICImpAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayAICImpF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblAICPI, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap())
        );
        aicDataPanelLayout.setVerticalGroup(
            aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aicDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAICOverall)
                    .addComponent(lblAICPI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aicDataPanelLayout.createSequentialGroup()
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICOverallAlpha)
                            .addComponent(displayAICoAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICOverallAlphaInv)
                            .addComponent(displayAICoAlphaInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICOverallInvAlpha)
                            .addComponent(displayAICoInvAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICOverallInv)
                            .addComponent(displayAICoInv)))
                    .addGroup(aicDataPanelLayout.createSequentialGroup()
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICImpInv)
                            .addComponent(displayAICImpInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICImpAlpha)
                            .addComponent(displayAICImpAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICImpAlphaInv)
                            .addComponent(displayAICImpAlphaInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICImpF)
                            .addComponent(displayAICImpF))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout aicPanelLayout = new javax.swing.GroupLayout(aicPanel);
        aicPanel.setLayout(aicPanelLayout);
        aicPanelLayout.setHorizontalGroup(
            aicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aicScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addComponent(aicDataPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        aicPanelLayout.setVerticalGroup(
            aicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aicScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aicDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        resultsTabbedPane.addTab(resourceMap.getString("aicPanel.TabConstraints.tabTitle"), aicPanel); // NOI18N

        bicPanel.setName("bicPanel"); // NOI18N

        bicScrollPanel.setName("bicScrollPanel"); // NOI18N

        bicTableModel =
        new javax.swing.table.DefaultTableModel() {
            public Class getColumnClass(int column) {
                if (column >= 0 && column <= getColumnCount()) {
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
        bicTableModel.addColumn("Model", new String[rows][1]);
        bicTableModel.addColumn("-LnL", new Double[rows][1]);
        bicTableModel.addColumn("BIC", new Double[rows][1]);
        bicTableModel.addColumn("deltaBIC", new Double[rows][1]);
        bicTableModel.addColumn("BIC weight", new Double[rows][1]);
        bicTable.setModel(
            bicTableModel
        );
        bicTable.setCellSelectionEnabled(true);
        bicTable.setName("bicTable"); // NOI18N
        RowSorter<TableModel> bicSorter = new TableRowSorter<TableModel>(bicTableModel);
        bicTable.setRowSorter(bicSorter);

        SelectionListener bicListener = new SelectionListener(bicTable);
        bicTable.getSelectionModel().addListSelectionListener(bicListener);
        bicTable.getColumnModel().getSelectionModel()
        .addListSelectionListener(bicListener);
        bicScrollPanel.setViewportView(bicTable);

        bicDataPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bicDataPanel.setName("bicDataPanel"); // NOI18N

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/results/Bundle"); // NOI18N
        lblBICOverall.setText(bundle1.getString("lbl-overall")); // NOI18N
        lblBICOverall.setName("lblBICOverall"); // NOI18N

        lblBICOverallAlpha.setText(bundle1.getString("lbl-overall-alpha")); // NOI18N
        lblBICOverallAlpha.setName("lblBICOverallAlpha"); // NOI18N

        lblBICOverallAlphaInv.setText(bundle1.getString("lbl-overall-alpha-inv")); // NOI18N
        lblBICOverallAlphaInv.setName("lblBICOverallAlphaInv"); // NOI18N

        lblBICOverallInvAlpha.setText(bundle1.getString("lbl-overall-inv-alpha")); // NOI18N
        lblBICOverallInvAlpha.setName("lblBICOverallInvAlpha"); // NOI18N

        lblBICOverallInv.setText(bundle1.getString("lbl-overall-inv")); // NOI18N
        lblBICOverallInv.setName("lblBICOverallInv"); // NOI18N

        displayBICoAlpha.setText(resourceMap.getString("displayBICoAlpha.text")); // NOI18N
        displayBICoAlpha.setName("displayBICoAlpha"); // NOI18N

        displayBICoAlphaInv.setText(resourceMap.getString("displayBICoAlphaInv.text")); // NOI18N
        displayBICoAlphaInv.setName("displayBICoAlphaInv"); // NOI18N

        displayBICoInvAlpha.setText(resourceMap.getString("displayBICoInvAlpha.text")); // NOI18N
        displayBICoInvAlpha.setName("displayBICoInvAlpha"); // NOI18N

        displayBICoInv.setText(resourceMap.getString("displayBICoInv.text")); // NOI18N
        displayBICoInv.setName("displayBICoInv"); // NOI18N

        lblBICImpInv.setText(resourceMap.getString("lblBICImpInv.text")); // NOI18N
        lblBICImpInv.setName("lblBICImpInv"); // NOI18N

        lblBICImpAlpha.setText(resourceMap.getString("lblBICImpAlpha.text")); // NOI18N
        lblBICImpAlpha.setName("lblBICImpAlpha"); // NOI18N

        lblBICImpF.setText(resourceMap.getString("lblBICImpF.text")); // NOI18N
        lblBICImpF.setName("lblBICImpF"); // NOI18N

        lblBICImpAlphaInv.setText(resourceMap.getString("lblBICImpAlphaInv.text")); // NOI18N
        lblBICImpAlphaInv.setName("lblBICImpAlphaInv"); // NOI18N

        displayBICImpInv.setText(resourceMap.getString("displayBICImpInv.text")); // NOI18N
        displayBICImpInv.setName("displayBICImpInv"); // NOI18N

        displayBICImpAlpha.setText(resourceMap.getString("displayBICImpAlpha.text")); // NOI18N
        displayBICImpAlpha.setName("displayBICImpAlpha"); // NOI18N

        displayBICImpAlphaInv.setText(resourceMap.getString("displayBICImpAlphaInv.text")); // NOI18N
        displayBICImpAlphaInv.setName("displayBICImpAlphaInv"); // NOI18N

        displayBICImpF.setText(resourceMap.getString("displayBICImpF.text")); // NOI18N
        displayBICImpF.setName("displayBICImpF"); // NOI18N

        lblBICPI.setText(resourceMap.getString("lblBICPI.text")); // NOI18N
        lblBICPI.setName("lblBICPI"); // NOI18N

        javax.swing.GroupLayout bicDataPanelLayout = new javax.swing.GroupLayout(bicDataPanel);
        bicDataPanel.setLayout(bicDataPanelLayout);
        bicDataPanelLayout.setHorizontalGroup(
            bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bicDataPanelLayout.createSequentialGroup()
                .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bicDataPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblBICOverallAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBICOverallAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBICOverallInvAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBICOverallInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayBICoInv)
                            .addComponent(displayBICoInvAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(displayBICoAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(displayBICoAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                        .addGap(40, 40, 40))
                    .addGroup(bicDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBICOverall, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bicDataPanelLayout.createSequentialGroup()
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblBICImpAlpha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBICImpInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBICImpF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBICImpAlphaInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(displayBICImpInv, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(displayBICImpAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayBICImpAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayBICImpF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bicDataPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBICPI, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
                .addContainerGap())
        );
        bicDataPanelLayout.setVerticalGroup(
            bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bicDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBICOverall)
                    .addComponent(lblBICPI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bicDataPanelLayout.createSequentialGroup()
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBICOverallAlpha)
                            .addComponent(displayBICoAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBICOverallAlphaInv)
                            .addComponent(displayBICoAlphaInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBICOverallInvAlpha)
                            .addComponent(displayBICoInvAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBICOverallInv)
                            .addComponent(displayBICoInv)))
                    .addGroup(bicDataPanelLayout.createSequentialGroup()
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBICImpInv)
                            .addComponent(displayBICImpInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBICImpAlpha)
                            .addComponent(displayBICImpAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBICImpAlphaInv)
                            .addComponent(displayBICImpAlphaInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBICImpF)
                            .addComponent(displayBICImpF))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bicPanelLayout = new javax.swing.GroupLayout(bicPanel);
        bicPanel.setLayout(bicPanelLayout);
        bicPanelLayout.setHorizontalGroup(
            bicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bicScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addComponent(bicDataPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        bicPanelLayout.setVerticalGroup(
            bicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bicScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bicDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        resultsTabbedPane.addTab(resourceMap.getString("bicPanel.TabConstraints.tabTitle"), bicPanel); // NOI18N

        aiccPanel.setName("aiccPanel"); // NOI18N

        aiccScrollPanel.setName("aiccScrollPanel"); // NOI18N

        aiccTableModel =
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
        aiccTableModel.addColumn("Model", new String[rows][1]);
        aiccTableModel.addColumn("-LnL", new Double[rows][1]);
        aiccTableModel.addColumn("AICc", new Double[rows][1]);
        aiccTableModel.addColumn("deltaAICc", new Double[rows][1]);
        aiccTableModel.addColumn("AICc weight", new Double[rows][1]);
        aiccTable.setModel(
            aiccTableModel
        );
        aiccTable.setCellSelectionEnabled(true);
        aiccTable.setName("aiccTable"); // NOI18N
        RowSorter<TableModel> aiccSorter = new TableRowSorter<TableModel>(aiccTableModel);
        aiccTable.setRowSorter(aiccSorter);

        SelectionListener aiccListener = new SelectionListener(aiccTable);
        aiccTable.getSelectionModel().addListSelectionListener(aiccListener);
        aiccTable.getColumnModel().getSelectionModel()
        .addListSelectionListener(aiccListener);
        aiccScrollPanel.setViewportView(aiccTable);

        aiccDataPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        aiccDataPanel.setName("aiccDataPanel"); // NOI18N

        lblAICcOverall.setText(bundle1.getString("lbl-overall_1")); // NOI18N
        lblAICcOverall.setName("lblAICcOverall"); // NOI18N

        lblAICcOverallAlpha.setText(bundle1.getString("lbl-overall-alpha_1")); // NOI18N
        lblAICcOverallAlpha.setName("lblAICcOverallAlpha"); // NOI18N

        lblAICcOverallAlphaInv.setText(bundle1.getString("lbl-overall-alpha-inv_1")); // NOI18N
        lblAICcOverallAlphaInv.setName("lblAICcOverallAlphaInv"); // NOI18N

        lblAICcOverallInvAlpha.setText(bundle1.getString("lbl-overall-inv-alpha_1")); // NOI18N
        lblAICcOverallInvAlpha.setName("lblAICcOverallInvAlpha"); // NOI18N

        lblAICcOverallInv.setText(bundle1.getString("lbl-overall-inv_1")); // NOI18N
        lblAICcOverallInv.setName("lblAICcOverallInv"); // NOI18N

        displayAICcoAlpha.setText(resourceMap.getString("displayAICcoAlpha.text")); // NOI18N
        displayAICcoAlpha.setName("displayAICcoAlpha"); // NOI18N

        displayAICcoAlphaInv.setText(resourceMap.getString("displayAICcoAlphaInv.text")); // NOI18N
        displayAICcoAlphaInv.setName("displayAICcoAlphaInv"); // NOI18N

        displayAICcoInvAlpha.setText(resourceMap.getString("displayAICcoInvAlpha.text")); // NOI18N
        displayAICcoInvAlpha.setName("displayAICcoInvAlpha"); // NOI18N

        displayAICcoInv.setText(resourceMap.getString("displayAICcoInv.text")); // NOI18N
        displayAICcoInv.setName("displayAICcoInv"); // NOI18N

        lblAICcPI.setText(resourceMap.getString("lblAICcPI.text")); // NOI18N
        lblAICcPI.setName("lblAICcPI"); // NOI18N

        lblAICcImpInv.setText(resourceMap.getString("lblAICcImpInv.text")); // NOI18N
        lblAICcImpInv.setName("lblAICcImpInv"); // NOI18N

        lblAICcImpAlpha.setText(resourceMap.getString("lblAICcImpAlpha.text")); // NOI18N
        lblAICcImpAlpha.setName("lblAICcImpAlpha"); // NOI18N

        lblAICcImpF.setText(resourceMap.getString("lblAICcImpF.text")); // NOI18N
        lblAICcImpF.setName("lblAICcImpF"); // NOI18N

        lblAICcImpAlphaInv.setText(resourceMap.getString("lblAICcImpAlphaInv.text")); // NOI18N
        lblAICcImpAlphaInv.setName("lblAICcImpAlphaInv"); // NOI18N

        displayAICcImpInv.setText(resourceMap.getString("displayAICcImpInv.text")); // NOI18N
        displayAICcImpInv.setName("displayAICcImpInv"); // NOI18N

        displayAICcImpAlpha.setText(resourceMap.getString("displayAICcImpAlpha.text")); // NOI18N
        displayAICcImpAlpha.setName("displayAICcImpAlpha"); // NOI18N

        displayAICcImpAlphaInv.setText(resourceMap.getString("displayAICcImpAlphaInv.text")); // NOI18N
        displayAICcImpAlphaInv.setName("displayAICcImpAlphaInv"); // NOI18N

        displayAICcImpF.setText(resourceMap.getString("displayAICcImpF.text")); // NOI18N
        displayAICcImpF.setName("displayAICcImpF"); // NOI18N

        javax.swing.GroupLayout aiccDataPanelLayout = new javax.swing.GroupLayout(aiccDataPanel);
        aiccDataPanel.setLayout(aiccDataPanelLayout);
        aiccDataPanelLayout.setHorizontalGroup(
            aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aiccDataPanelLayout.createSequentialGroup()
                .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aiccDataPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAICcOverallAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICcOverallAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICcOverallInvAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICcOverallInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayAICcoInv)
                            .addComponent(displayAICcoInvAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(displayAICcoAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(displayAICcoAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                        .addGap(40, 40, 40))
                    .addGroup(aiccDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAICcOverall, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aiccDataPanelLayout.createSequentialGroup()
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblAICcImpAlpha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICcImpInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICcImpF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAICcImpAlphaInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(displayAICcImpInv, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(displayAICcImpAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayAICcImpAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayAICcImpF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblAICcPI, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap())
        );
        aiccDataPanelLayout.setVerticalGroup(
            aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aiccDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAICcOverall)
                    .addComponent(lblAICcPI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aiccDataPanelLayout.createSequentialGroup()
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICcOverallAlpha)
                            .addComponent(displayAICcoAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICcOverallAlphaInv)
                            .addComponent(displayAICcoAlphaInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICcOverallInvAlpha)
                            .addComponent(displayAICcoInvAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICcOverallInv)
                            .addComponent(displayAICcoInv)))
                    .addGroup(aiccDataPanelLayout.createSequentialGroup()
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICcImpInv)
                            .addComponent(displayAICcImpInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICcImpAlpha)
                            .addComponent(displayAICcImpAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICcImpAlphaInv)
                            .addComponent(displayAICcImpAlphaInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aiccDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAICcImpF)
                            .addComponent(displayAICcImpF))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout aiccPanelLayout = new javax.swing.GroupLayout(aiccPanel);
        aiccPanel.setLayout(aiccPanelLayout);
        aiccPanelLayout.setHorizontalGroup(
            aiccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aiccPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aiccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(aiccScrollPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addComponent(aiccDataPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        aiccPanelLayout.setVerticalGroup(
            aiccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aiccPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aiccScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aiccDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        resultsTabbedPane.addTab(resourceMap.getString("aiccPanel.TabConstraints.tabTitle"), aiccPanel); // NOI18N

        dtPanel.setName("dtPanel"); // NOI18N

        dtScrollPanel.setName("dtScrollPanel"); // NOI18N

        dtTableModel =
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
        dtTableModel.addColumn("Model", new String[rows][1]);
        dtTableModel.addColumn("-LnL", new Double[rows][1]);
        dtTableModel.addColumn("DT", new Double[rows][1]);
        dtTableModel.addColumn("deltaDT", new Double[rows][1]);
        dtTableModel.addColumn("DT weight", new Double[rows][1]);
        dtTable.setModel(
            dtTableModel
        );
        dtTable.setCellSelectionEnabled(true);
        dtTable.setName("dtTable"); // NOI18N
        RowSorter<TableModel> dtSorter = new TableRowSorter<TableModel>(dtTableModel);
        dtTable.setRowSorter(dtSorter);

        SelectionListener dtListener = new SelectionListener(dtTable);
        dtTable.getSelectionModel().addListSelectionListener(dtListener);
        dtTable.getColumnModel().getSelectionModel()
        .addListSelectionListener(dtListener);
        dtScrollPanel.setViewportView(dtTable);

        dtDataPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dtDataPanel.setName("dtDataPanel"); // NOI18N

        lblDTOverall.setText(bundle1.getString("lbl-overall_2")); // NOI18N
        lblDTOverall.setName("lblDTOverall"); // NOI18N

        lblDTOverallAlpha.setText(bundle1.getString("lbl-overall-alpha_2")); // NOI18N
        lblDTOverallAlpha.setName("lblDTOverallAlpha"); // NOI18N

        lblDTOverallAlphaInv.setText(bundle1.getString("lbl-overall-alpha-inv_2")); // NOI18N
        lblDTOverallAlphaInv.setName("lblDTOverallAlphaInv"); // NOI18N

        lblDTOverallInvAlpha.setText(bundle1.getString("lbl-overall-inv-alpha_2")); // NOI18N
        lblDTOverallInvAlpha.setName("lblDTOverallInvAlpha"); // NOI18N

        lblDTOverallInv.setText(bundle1.getString("lbl-overall-inv_2")); // NOI18N
        lblDTOverallInv.setName("lblDTOverallInv"); // NOI18N

        displayDToAlpha.setText(resourceMap.getString("displayDToAlpha.text")); // NOI18N
        displayDToAlpha.setName("displayDToAlpha"); // NOI18N

        displayDToAlphaInv.setText(resourceMap.getString("displayDToAlphaInv.text")); // NOI18N
        displayDToAlphaInv.setName("displayDToAlphaInv"); // NOI18N

        displayDToInvAlpha.setText(resourceMap.getString("displayDToInvAlpha.text")); // NOI18N
        displayDToInvAlpha.setName("displayDToInvAlpha"); // NOI18N

        displayDToInv.setText(resourceMap.getString("displayDToInv.text")); // NOI18N
        displayDToInv.setName("displayDToInv"); // NOI18N

        lblDTPI.setText(resourceMap.getString("lblDTPI.text")); // NOI18N
        lblDTPI.setName("lblDTPI"); // NOI18N

        lblDTImpInv.setText(resourceMap.getString("lblDTImpInv.text")); // NOI18N
        lblDTImpInv.setName("lblDTImpInv"); // NOI18N

        lblDTImpAlpha.setText(resourceMap.getString("lblDTImpAlpha.text")); // NOI18N
        lblDTImpAlpha.setName("lblDTImpAlpha"); // NOI18N

        lblDTImpF.setText(resourceMap.getString("lblDTImpF.text")); // NOI18N
        lblDTImpF.setName("lblDTImpF"); // NOI18N

        lblDTImpAlphaInv.setText(resourceMap.getString("lblDTImpAlphaInv.text")); // NOI18N
        lblDTImpAlphaInv.setName("lblDTImpAlphaInv"); // NOI18N

        displayDTImpInv.setText(resourceMap.getString("displayDTImpInv.text")); // NOI18N
        displayDTImpInv.setName("displayDTImpInv"); // NOI18N

        displayDTImpAlpha.setText(resourceMap.getString("displayDTImpAlpha.text")); // NOI18N
        displayDTImpAlpha.setName("displayDTImpAlpha"); // NOI18N

        displayDTImpAlphaInv.setText(resourceMap.getString("displayDTImpAlphaInv.text")); // NOI18N
        displayDTImpAlphaInv.setName("displayDTImpAlphaInv"); // NOI18N

        displayDTImpF.setText(resourceMap.getString("displayDTImpF.text")); // NOI18N
        displayDTImpF.setName("displayDTImpF"); // NOI18N

        javax.swing.GroupLayout dtDataPanelLayout = new javax.swing.GroupLayout(dtDataPanel);
        dtDataPanel.setLayout(dtDataPanelLayout);
        dtDataPanelLayout.setHorizontalGroup(
            dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dtDataPanelLayout.createSequentialGroup()
                .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dtDataPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDTOverallAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDTOverallAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDTOverallInvAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDTOverallInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayDToInv)
                            .addComponent(displayDToInvAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(displayDToAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(displayDToAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                        .addGap(40, 40, 40))
                    .addGroup(dtDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDTOverall, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dtDataPanelLayout.createSequentialGroup()
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblDTImpAlpha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDTImpInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDTImpF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDTImpAlphaInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(displayDTImpInv, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(displayDTImpAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayDTImpAlphaInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayDTImpF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblDTPI, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap())
        );
        dtDataPanelLayout.setVerticalGroup(
            dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dtDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDTOverall)
                    .addComponent(lblDTPI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dtDataPanelLayout.createSequentialGroup()
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDTOverallAlpha)
                            .addComponent(displayDToAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDTOverallAlphaInv)
                            .addComponent(displayDToAlphaInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDTOverallInvAlpha)
                            .addComponent(displayDToInvAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDTOverallInv)
                            .addComponent(displayDToInv)))
                    .addGroup(dtDataPanelLayout.createSequentialGroup()
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDTImpInv)
                            .addComponent(displayDTImpInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDTImpAlpha)
                            .addComponent(displayDTImpAlpha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDTImpAlphaInv)
                            .addComponent(displayDTImpAlphaInv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dtDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDTImpF)
                            .addComponent(displayDTImpF))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dtPanelLayout = new javax.swing.GroupLayout(dtPanel);
        dtPanel.setLayout(dtPanelLayout);
        dtPanelLayout.setHorizontalGroup(
            dtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dtPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtScrollPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addComponent(dtDataPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dtPanelLayout.setVerticalGroup(
            dtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dtPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dtScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        resultsTabbedPane.addTab(resourceMap.getString("dtPanel.TabConstraints.tabTitle"), dtPanel); // NOI18N

        lblSampleSizeMode.setText(resourceMap.getString("lblSampleSizeMode.text")); // NOI18N
        lblSampleSizeMode.setName("lblSampleSizeMode"); // NOI18N

        txtSampleSize.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSampleSize.setText(resourceMap.getString("defaultCustomSampleSize")); // NOI18N
        txtSampleSize.setEnabled(false);
        txtSampleSize.setName("txtSampleSize"); // NOI18N
        txtSampleSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSampleSizeMousePressed(evt);
            }
        });
        txtSampleSize.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                onWriteCustomSampleSize(evt);
            }
        });
        txtSampleSize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSampleSizeKeyPressed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(ResultsView.class, this);
        sizeModeAlignment.setAction(actionMap.get("setSampleSize")); // NOI18N
        sampleSizeModeButtonGroup.add(sizeModeAlignment);
        sizeModeAlignment.setSelected(true);
        sizeModeAlignment.setText(bundle.getString("btn-sizemode-alignment")); // NOI18N
        sizeModeAlignment.setName("sizeModeAlignment"); // NOI18N
        sizeModeAlignment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSampleSize(evt);
            }
        });

        sizeModeShannon.setAction(actionMap.get("setSampleSize")); // NOI18N
        sampleSizeModeButtonGroup.add(sizeModeShannon);
        sizeModeShannon.setText(bundle.getString("btn-sizemode-shannon")); // NOI18N
        sizeModeShannon.setName("sizeModeShannon"); // NOI18N
        sizeModeShannon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSampleSize(evt);
            }
        });

        sizeModeVar.setAction(actionMap.get("setSampleSize")); // NOI18N
        sampleSizeModeButtonGroup.add(sizeModeVar);
        sizeModeVar.setText(bundle.getString("btn-sizemode-alignment-var")); // NOI18N
        sizeModeVar.setName("sizeModeVar"); // NOI18N
        sizeModeVar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSampleSize(evt);
            }
        });

        sampleSizeModeButtonGroup.add(sizeModeShannonNL);
        sizeModeShannonNL.setText(bundle.getString("btn-sizemode-shannon-nxl")); // NOI18N
        sizeModeShannonNL.setName("sizeModeShannonNL"); // NOI18N
        sizeModeShannonNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSampleSize(evt);
            }
        });

        sampleSizeModeButtonGroup.add(sizeModeNL);
        sizeModeNL.setText(bundle.getString("btn-sizemode-nxl")); // NOI18N
        sizeModeNL.setName("sizeModeNL"); // NOI18N
        sizeModeNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSampleSize(evt);
            }
        });

        sizeModeCustom.setAction(actionMap.get("setCustomSampleSize")); // NOI18N
        sampleSizeModeButtonGroup.add(sizeModeCustom);
        sizeModeCustom.setText(bundle.getString("btn-sizemode-custom")); // NOI18N
        sizeModeCustom.setName("sizeModeCustom"); // NOI18N
        sizeModeCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setCustomSampleSize(evt);
            }
        });

        sliderConfidenceInterval.setValue(100);
        sliderConfidenceInterval.setName("sliderConfidenceInterval"); // NOI18N
        sliderConfidenceInterval.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sliderConfidenceIntervalMouseReleased(evt);
            }
        });
        sliderConfidenceInterval.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderConfidenceIntervalStateChanged(evt);
            }
        });

        lblConfInt.setText(resourceMap.getString("lblConfInt.text")); // NOI18N
        lblConfInt.setName("lblConfInt"); // NOI18N

        lblSampleSize.setText(resourceMap.getString("lblSampleSize.text")); // NOI18N
        lblSampleSize.setName("lblSampleSize"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        lblCommandLine.setBackground(resourceMap.getColor("lblCommandLine.background")); // NOI18N
        lblCommandLine.setColumns(20);
        lblCommandLine.setEditable(false);
        lblCommandLine.setRows(1);
        lblCommandLine.setBorder(null);
        lblCommandLine.setName("lblCommandLine"); // NOI18N
        jScrollPane1.setViewportView(lblCommandLine);

        lblComandLineLabel.setText(resourceMap.getString("lblComandLineLabel.text")); // NOI18N
        lblComandLineLabel.setName("lblComandLineLabel"); // NOI18N

        lblSelectedModelLabel.setText(resourceMap.getString("lblSelectedModelLabel.text")); // NOI18N
        lblSelectedModelLabel.setName("lblSelectedModelLabel"); // NOI18N

        lblSelectedModel.setText(resourceMap.getString("lblSelectedModel.text")); // NOI18N
        lblSelectedModel.setName("lblSelectedModel"); // NOI18N

        lblNotComplete.setBackground(resourceMap.getColor("lblNotComplete.background")); // NOI18N
        lblNotComplete.setFont(resourceMap.getFont("lblNotComplete.font")); // NOI18N
        lblNotComplete.setForeground(resourceMap.getColor("lblNotComplete.foreground")); // NOI18N
        lblNotComplete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/resources/XProtTestView"); // NOI18N
        lblNotComplete.setText(bundle2.getString("models-not-complete")); // NOI18N
        lblNotComplete.setName("lblNotComplete"); // NOI18N
        lblNotComplete.setOpaque(true);

        lblConfidence.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblConfidence.setText(resourceMap.getString("lblConfidence.text")); // NOI18N
        lblConfidence.setName("lblConfidence"); // NOI18N

        btnExport.setAction(actionMap.get("exportData")); // NOI18N
        btnExport.setName("btnExport"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultsTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sizeModeAlignment)
                                        .addGap(15, 15, 15))
                                    .addComponent(sizeModeNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(lblConfInt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(sizeModeCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSampleSize, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(sizeModeVar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sizeModeShannon, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sizeModeShannonNL, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblSampleSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblConfidence, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sliderConfidenceInterval, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))))
                    .addComponent(lblSampleSizeMode)
                    .addComponent(lblNotComplete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblComandLineLabel)
                            .addComponent(lblSelectedModelLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSelectedModel, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExport))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSampleSizeMode)
                    .addComponent(lblSampleSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sizeModeAlignment)
                    .addComponent(sizeModeShannonNL)
                    .addComponent(sizeModeShannon)
                    .addComponent(sizeModeVar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sizeModeNL)
                    .addComponent(sizeModeCustom)
                    .addComponent(txtSampleSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblConfInt)
                        .addComponent(lblConfidence))
                    .addComponent(sliderConfidenceInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNotComplete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultsTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectedModelLabel)
                    .addComponent(lblSelectedModel)
                    .addComponent(btnExport))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblComandLineLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private String oldSampleSizeModeStr;
    private int oldSampleSizeMode = DEFAULT_SAMPLE_SIZE_MODE;
    private void setSampleSize(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setSampleSize

        sampleSizeMode = DEFAULT_SAMPLE_SIZE_MODE;
        sampleSize = 0.0;
        String btnName = evt.getActionCommand();
        if (!btnName.equals(oldSampleSizeModeStr)) {
            if (btnName.equals(
                    java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/results/resources/ResultsView").getString("btn-sizemode-alignment"))) {
                sampleSizeMode = SIZEMODE_ALIGNMENT;
            } else if (btnName.equals(
                    java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/results/resources/ResultsView").getString("btn-sizemode-alignment-var"))) {
                sampleSizeMode = SIZEMODE_ALIGNMENT_VAR;
            } else if (btnName.equals(
                    java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/results/resources/ResultsView").getString("btn-sizemode-shannon"))) {
                sampleSizeMode = SIZEMODE_SHANNON;
            } else if (btnName.equals(
                    java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/results/resources/ResultsView").getString("btn-sizemode-shannon-nxl"))) {
                sampleSizeMode = SIZEMODE_SHANNON_NxL;
            } else if (btnName.equals(
                    java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/results/resources/ResultsView").getString("btn-sizemode-nxl"))) {
                sampleSizeMode = SIZEMODE_NxL;
            } else if (btnName.equals(
                    java.util.ResourceBundle.getBundle("es/uvigo/darwin/xprottest/results/resources/ResultsView").getString("btn-sizemode-custom"))) {
                sampleSizeMode = SIZEMODE_USERSIZE;
                try {
                    sampleSize = Double.parseDouble(txtSampleSize.getText());
                } catch (NumberFormatException e) {
                    sampleSize = 0.0;
                }
            }

            txtSampleSize.setEnabled(false);
            fillInResults(sampleSizeMode, sampleSize);

            oldSampleSizeModeStr = btnName;
            oldSampleSizeMode = sampleSizeMode;
            oldCustomSampleSize = 0.0;
        }
}//GEN-LAST:event_setSampleSize
    private boolean waitingForSampleSize = false;
    private void setCustomSampleSize(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setCustomSampleSize
        if (sizeModeCustom.isSelected()
                && !sizeModeCustom.getActionCommand().equals(oldSampleSizeModeStr)) {
            txtSampleSize.setEnabled(true);
            txtSampleSize.grabFocus();
            oldSampleSizeModeStr = sizeModeCustom.getActionCommand();
            waitingForSampleSize = true;
        }
    }//GEN-LAST:event_setCustomSampleSize
    private double oldCustomSampleSize;

    private void computeCustomSampleSize() {
        int sampleSizeMode = SIZEMODE_USERSIZE;
        double sampleSize = 0.0;
        try {
            sampleSize = Double.parseDouble(txtSampleSize.getText());
        } catch (NumberFormatException e) {
            sampleSize = 0.0;
        }

        if (waitingForSampleSize || sampleSize != oldCustomSampleSize) {
            fillInResults(sampleSizeMode, sampleSize);
            oldCustomSampleSize = sampleSize;
            waitingForSampleSize = false;
        }
    }
    private void onWriteCustomSampleSize(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_onWriteCustomSampleSize
        computeCustomSampleSize();
    }//GEN-LAST:event_onWriteCustomSampleSize

    private void txtSampleSizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSampleSizeMousePressed
        computeCustomSampleSize();
    }//GEN-LAST:event_txtSampleSizeMousePressed

    private void txtSampleSizeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSampleSizeKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            computeCustomSampleSize();
        }
    }//GEN-LAST:event_txtSampleSizeKeyPressed

    private void sliderConfidenceIntervalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderConfidenceIntervalStateChanged
        double confidenceInterval = Double.parseDouble(sliderConfidenceInterval.getValue() + "") / 100;
        lblConfidence.setText(String.valueOf(confidenceInterval));
}//GEN-LAST:event_sliderConfidenceIntervalStateChanged

    private void sliderConfidenceIntervalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderConfidenceIntervalMouseReleased
        double confidenceInterval = Double.parseDouble(sliderConfidenceInterval.getValue() + "") / 100;
        loadCache(confidenceInterval);
        fillInResults(oldSampleSizeMode, Double.parseDouble(txtSampleSize.getText()));
    }//GEN-LAST:event_sliderConfidenceIntervalMouseReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel aicDataPanel;
    private javax.swing.JPanel aicPanel;
    private javax.swing.JScrollPane aicScrollPanel;
    private javax.swing.JTable aicTable;
    private javax.swing.JPanel aiccDataPanel;
    private javax.swing.JPanel aiccPanel;
    private javax.swing.JScrollPane aiccScrollPanel;
    private javax.swing.JTable aiccTable;
    private javax.swing.JPanel bicDataPanel;
    private javax.swing.JPanel bicPanel;
    private javax.swing.JScrollPane bicScrollPanel;
    private javax.swing.JTable bicTable;
    private javax.swing.JButton btnExport;
    private javax.swing.JLabel displayAICImpAlpha;
    private javax.swing.JLabel displayAICImpAlphaInv;
    private javax.swing.JLabel displayAICImpF;
    private javax.swing.JLabel displayAICImpInv;
    private javax.swing.JLabel displayAICcImpAlpha;
    private javax.swing.JLabel displayAICcImpAlphaInv;
    private javax.swing.JLabel displayAICcImpF;
    private javax.swing.JLabel displayAICcImpInv;
    private javax.swing.JLabel displayAICcoAlpha;
    private javax.swing.JLabel displayAICcoAlphaInv;
    private javax.swing.JLabel displayAICcoInv;
    private javax.swing.JLabel displayAICcoInvAlpha;
    private javax.swing.JLabel displayAICoAlpha;
    private javax.swing.JLabel displayAICoAlphaInv;
    private javax.swing.JLabel displayAICoInv;
    private javax.swing.JLabel displayAICoInvAlpha;
    private javax.swing.JLabel displayBICImpAlpha;
    private javax.swing.JLabel displayBICImpAlphaInv;
    private javax.swing.JLabel displayBICImpF;
    private javax.swing.JLabel displayBICImpInv;
    private javax.swing.JLabel displayBICoAlpha;
    private javax.swing.JLabel displayBICoAlphaInv;
    private javax.swing.JLabel displayBICoInv;
    private javax.swing.JLabel displayBICoInvAlpha;
    private javax.swing.JLabel displayDTImpAlpha;
    private javax.swing.JLabel displayDTImpAlphaInv;
    private javax.swing.JLabel displayDTImpF;
    private javax.swing.JLabel displayDTImpInv;
    private javax.swing.JLabel displayDToAlpha;
    private javax.swing.JLabel displayDToAlphaInv;
    private javax.swing.JLabel displayDToInv;
    private javax.swing.JLabel displayDToInvAlpha;
    private javax.swing.JPanel dtDataPanel;
    private javax.swing.JPanel dtPanel;
    private javax.swing.JScrollPane dtScrollPanel;
    private javax.swing.JTable dtTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAICImpAlpha;
    private javax.swing.JLabel lblAICImpAlphaInv;
    private javax.swing.JLabel lblAICImpF;
    private javax.swing.JLabel lblAICImpInv;
    private javax.swing.JLabel lblAICOverall;
    private javax.swing.JLabel lblAICOverallAlpha;
    private javax.swing.JLabel lblAICOverallAlphaInv;
    private javax.swing.JLabel lblAICOverallInv;
    private javax.swing.JLabel lblAICOverallInvAlpha;
    private javax.swing.JLabel lblAICPI;
    private javax.swing.JLabel lblAICcImpAlpha;
    private javax.swing.JLabel lblAICcImpAlphaInv;
    private javax.swing.JLabel lblAICcImpF;
    private javax.swing.JLabel lblAICcImpInv;
    private javax.swing.JLabel lblAICcOverall;
    private javax.swing.JLabel lblAICcOverallAlpha;
    private javax.swing.JLabel lblAICcOverallAlphaInv;
    private javax.swing.JLabel lblAICcOverallInv;
    private javax.swing.JLabel lblAICcOverallInvAlpha;
    private javax.swing.JLabel lblAICcPI;
    private javax.swing.JLabel lblBICImpAlpha;
    private javax.swing.JLabel lblBICImpAlphaInv;
    private javax.swing.JLabel lblBICImpF;
    private javax.swing.JLabel lblBICImpInv;
    private javax.swing.JLabel lblBICOverall;
    private javax.swing.JLabel lblBICOverallAlpha;
    private javax.swing.JLabel lblBICOverallAlphaInv;
    private javax.swing.JLabel lblBICOverallInv;
    private javax.swing.JLabel lblBICOverallInvAlpha;
    private javax.swing.JLabel lblBICPI;
    private javax.swing.JLabel lblComandLineLabel;
    private javax.swing.JTextArea lblCommandLine;
    private javax.swing.JLabel lblConfInt;
    private javax.swing.JLabel lblConfidence;
    private javax.swing.JLabel lblDTImpAlpha;
    private javax.swing.JLabel lblDTImpAlphaInv;
    private javax.swing.JLabel lblDTImpF;
    private javax.swing.JLabel lblDTImpInv;
    private javax.swing.JLabel lblDTOverall;
    private javax.swing.JLabel lblDTOverallAlpha;
    private javax.swing.JLabel lblDTOverallAlphaInv;
    private javax.swing.JLabel lblDTOverallInv;
    private javax.swing.JLabel lblDTOverallInvAlpha;
    private javax.swing.JLabel lblDTPI;
    private javax.swing.JLabel lblNotComplete;
    private javax.swing.JLabel lblSampleSize;
    private javax.swing.JLabel lblSampleSizeMode;
    private javax.swing.JLabel lblSelectedModel;
    private javax.swing.JLabel lblSelectedModelLabel;
    private javax.swing.JTabbedPane resultsTabbedPane;
    private javax.swing.ButtonGroup sampleSizeModeButtonGroup;
    private javax.swing.JRadioButton sizeModeAlignment;
    private javax.swing.JRadioButton sizeModeCustom;
    private javax.swing.JRadioButton sizeModeNL;
    private javax.swing.JRadioButton sizeModeShannon;
    private javax.swing.JRadioButton sizeModeShannonNL;
    private javax.swing.JRadioButton sizeModeVar;
    private javax.swing.JSlider sliderConfidenceInterval;
    private javax.swing.JTextField txtSampleSize;
    // End of variables declaration//GEN-END:variables

    private void fillInResults(int sampleSizeMode, double sampleSize) {

        SelectionChunk chunkAIC, chunkBIC, chunkAICC, chunkDT;
        double confidenceInterval = Double.parseDouble(sliderConfidenceInterval.getValue() + "") / 100;
        if (sampleSizeMode != SIZEMODE_USERSIZE) {
            chunkAIC = aicResults.get(sampleSizeMode);
            chunkBIC = bicResults.get(sampleSizeMode);
            chunkAICC = aiccResults.get(sampleSizeMode);
            chunkDT = dtResults.get(sampleSizeMode);
        } else {
            chunkAIC = mainFrame.getFacade().computeInformationCriterion(alignment, models,
                    SelectionChunk.AIC, sampleSizeMode, sampleSize, confidenceInterval);
            chunkBIC = mainFrame.getFacade().computeInformationCriterion(alignment, models,
                    SelectionChunk.BIC, sampleSizeMode, sampleSize, confidenceInterval);
            chunkAICC = mainFrame.getFacade().computeInformationCriterion(alignment, models,
                    SelectionChunk.AICC, sampleSizeMode, sampleSize, confidenceInterval);
            chunkDT = mainFrame.getFacade().computeInformationCriterion(alignment, models,
                    SelectionChunk.DT, sampleSizeMode, sampleSize, confidenceInterval);
        }

        Collection<SelectionModel> aicIt = chunkAIC.getConfidenceModels();
        Collection<SelectionModel> bicIt = chunkBIC.getConfidenceModels();
        Collection<SelectionModel> aiccIt = chunkAICC.getConfidenceModels();
        Collection<SelectionModel> dtIt = chunkDT.getConfidenceModels();

        aicTableModel.setRowCount(aicIt.size());
        fillTable(aicIt, aicTable);

        displayAICoAlpha.setText(getDisplayValue(chunkAIC.getOverallAlpha(), PARAMETER_G, chunkAIC.existGammaModels()));
        displayAICoAlphaInv.setText(getDisplayValue(chunkAIC.getOverallAlphaInv(), PARAMETER_IG, chunkAIC.existGammaInvModels()));
        displayAICoInvAlpha.setText(getDisplayValue(chunkAIC.getOverallInvAlpha(), PARAMETER_IG, chunkAIC.existGammaInvModels()));
        displayAICoInv.setText(getDisplayValue(chunkAIC.getOverallInv(), PARAMETER_I, chunkAIC.existInvModels()));
        displayAICImpAlpha.setText(getDisplayValue(chunkAIC.getAlphaImportance(), PARAMETER_G, chunkAIC.existGammaModels()));
        displayAICImpInv.setText(getDisplayValue(chunkAIC.getInvImportance(), PARAMETER_I, chunkAIC.existInvModels()));
        displayAICImpAlphaInv.setText(getDisplayValue(chunkAIC.getAlphaInvImportance(), PARAMETER_IG, chunkAIC.existGammaInvModels()));
        displayAICImpF.setText(getDisplayValue(chunkAIC.getFImportance(), PARAMETER_F, chunkAIC.existFModels()));

        bicTableModel.setRowCount(bicIt.size());
        fillTable(bicIt, bicTable);

        displayBICoAlpha.setText(getDisplayValue(chunkBIC.getOverallAlpha(), PARAMETER_G, chunkBIC.existGammaModels()));
        displayBICoAlphaInv.setText(getDisplayValue(chunkBIC.getOverallAlphaInv(), PARAMETER_IG, chunkBIC.existGammaInvModels()));
        displayBICoInvAlpha.setText(getDisplayValue(chunkBIC.getOverallInvAlpha(), PARAMETER_IG, chunkBIC.existGammaInvModels()));
        displayBICoInv.setText(getDisplayValue(chunkBIC.getOverallInv(), PARAMETER_I, chunkBIC.existInvModels()));
        displayBICImpAlpha.setText(getDisplayValue(chunkBIC.getAlphaImportance(), PARAMETER_G, chunkBIC.existGammaModels()));
        displayBICImpInv.setText(getDisplayValue(chunkBIC.getInvImportance(), PARAMETER_I, chunkBIC.existInvModels()));
        displayBICImpAlphaInv.setText(getDisplayValue(chunkBIC.getAlphaInvImportance(), PARAMETER_IG, chunkBIC.existGammaInvModels()));
        displayBICImpF.setText(getDisplayValue(chunkBIC.getFImportance(), PARAMETER_F, chunkBIC.existFModels()));

        aiccTableModel.setRowCount(aiccIt.size());
        fillTable(aiccIt, aiccTable);

        displayAICcoAlpha.setText(getDisplayValue(chunkAICC.getOverallAlpha(), PARAMETER_G, chunkAICC.existGammaModels()));
        displayAICcoAlphaInv.setText(getDisplayValue(chunkAICC.getOverallAlphaInv(), PARAMETER_IG, chunkAICC.existGammaInvModels()));
        displayAICcoInvAlpha.setText(getDisplayValue(chunkAICC.getOverallInvAlpha(), PARAMETER_IG, chunkAICC.existGammaInvModels()));
        displayAICcoInv.setText(getDisplayValue(chunkAICC.getOverallInv(), PARAMETER_I, chunkAICC.existInvModels()));
        displayAICcImpAlpha.setText(getDisplayValue(chunkAICC.getAlphaImportance(), PARAMETER_G, chunkAICC.existGammaModels()));
        displayAICcImpInv.setText(getDisplayValue(chunkAICC.getInvImportance(), PARAMETER_I, chunkAICC.existInvModels()));
        displayAICcImpAlphaInv.setText(getDisplayValue(chunkAICC.getAlphaInvImportance(), PARAMETER_IG, chunkAICC.existGammaInvModels()));
        displayAICcImpF.setText(getDisplayValue(chunkAICC.getFImportance(), PARAMETER_F, chunkAICC.existFModels()));

        dtTableModel.setRowCount(dtIt.size());
        fillTable(dtIt, dtTable);

        displayDToAlpha.setText(getDisplayValue(chunkDT.getOverallAlpha(), PARAMETER_G, chunkDT.existGammaModels()));
        displayDToAlphaInv.setText(getDisplayValue(chunkDT.getOverallAlphaInv(), PARAMETER_IG, chunkDT.existGammaInvModels()));
        displayDToInvAlpha.setText(getDisplayValue(chunkDT.getOverallInvAlpha(), PARAMETER_IG, chunkDT.existGammaInvModels()));
        displayDToInv.setText(getDisplayValue(chunkDT.getOverallInv(), PARAMETER_I, chunkDT.existInvModels()));
        displayDTImpAlpha.setText(getDisplayValue(chunkDT.getAlphaImportance(), PARAMETER_G, chunkDT.existGammaModels()));
        displayDTImpInv.setText(getDisplayValue(chunkDT.getInvImportance(), PARAMETER_I, chunkDT.existInvModels()));
        displayDTImpAlphaInv.setText(getDisplayValue(chunkDT.getAlphaInvImportance(), PARAMETER_IG, chunkDT.existGammaInvModels()));
        displayDTImpF.setText(getDisplayValue(chunkDT.getFImportance(), PARAMETER_F, chunkDT.existFModels()));

        lblSampleSize.setText(String.valueOf(ProtTestAlignment.calculateSampleSize(alignment, sampleSizeMode, sampleSize)));
    }

    private void fillTable(Collection<SelectionModel> iterator, JTable table) {
        int row = 0;
        for (SelectionModel model : iterator) {
            table.setValueAt(
                    model.getModel().getModelName(), row, 0);
            table.setValueAt(
                    model.getModel().getLk(), row, 1);
            try {
                table.setValueAt(
                        new Double(
                        ProtTestFormattedOutput.getDecimalString(model.getValue(), CRITERION_PRECISSION)), row, 2);
                table.setValueAt(
                        new Double(
                        ProtTestFormattedOutput.getDecimalString(model.getDeltaValue(), CRITERION_PRECISSION)), row, 3);
                table.setValueAt(
                        new Double(
                        ProtTestFormattedOutput.getDecimalString(model.getWeightValue(), CRITERION_PRECISSION)), row, 4);
            } catch (NumberFormatException e) {
                table.setValueAt(Double.NaN, row, 2);
                table.setValueAt(Double.NaN, row, 3);
                table.setValueAt(Double.NaN, row, 4);
            }
            row++;
        }
    }

    private void showCommandLine(String modelName) {
        for (Model model : models) {
            if (model.getModelName().equals(modelName)) {
                lblSelectedModel.setText(modelName);
                StringBuilder command = new StringBuilder();
                for (int i = 1; i < model.getCommandLine().length; i++) {
                    command.append(model.getCommandLine()[i]).append(" ");
                }
                lblCommandLine.setText(command.toString());
                break;
            }
        }
    }

    class SelectionListener implements ListSelectionListener {

        JTable table;

        // It is necessary to keep the table since it is not possible
        // to determine the table from the event's source
        SelectionListener(JTable table) {
            this.table = table;
        }

        public void valueChanged(ListSelectionEvent e) {
            // If cell selection is enabled, both row and column change events are fired
            int first = 0, last = 0;
            if (e.getSource() == table.getSelectionModel()
                    && table.getRowSelectionAllowed()) {
                // Column selection changed
                first = e.getFirstIndex();
                last = e.getLastIndex();
            } else if (e.getSource() == table.getColumnModel().getSelectionModel()
                    && table.getColumnSelectionAllowed()) {
                // Row selection changed
                first = e.getFirstIndex();
                last = e.getLastIndex();
            }

            if (e.getValueIsAdjusting()) {
                // The mouse button has not yet been released
            } else {
                try {
                    showCommandLine(table.getValueAt(table.getSelectedRow(), 0).toString());
                } catch (IndexOutOfBoundsException ex) {
                    // Clear
                    lblCommandLine.setText("");
                    lblSelectedModel.setText("");
                }
            }
        }
    }


//    private String getDisplayValue(double value, String parameter) {
//        String toDisplay;
//        boolean existModels = false;
//        if (parameter.equals(PARAMETER_I)) {
//            existModels = existInvModels;
//        } else if (parameter.equals(PARAMETER_G)) {
//            existModels = existGammaModels;
//        } else if (parameter.equals(PARAMETER_IG)) {
//            existModels = existGammaInvModels;
//        } else if (parameter.equals(PARAMETER_F)) {
//            existModels = existFModels;
//        }
//
//        if (existModels) {
//            toDisplay = ProtTestFormattedOutput.getDecimalString(value, IMPORTANCE_PRECISSION);
//        } else {
//            toDisplay = "No " + parameter + " models";
//        }
//        return toDisplay;
//    }

    @Action
    public void exportData() {
        InformationCriterion ic = null;
        if (sampleSizeMode != SIZEMODE_USERSIZE) {
            switch (resultsTabbedPane.getSelectedIndex()) {
                case AIC_TAB:
                    ic = aicResults.get(sampleSizeMode).getInformationCriterion();
                    break;
                case BIC_TAB:
                    ic = bicResults.get(sampleSizeMode).getInformationCriterion();
                    break;
                case AICC_TAB:
                    ic = aiccResults.get(sampleSizeMode).getInformationCriterion();
                    break;
                case DT_TAB:
                    ic = dtResults.get(sampleSizeMode).getInformationCriterion();
                    break;
            }
        } else {
            double confidenceInterval = Double.parseDouble(sliderConfidenceInterval.getValue() + "") / 100;
            switch (resultsTabbedPane.getSelectedIndex()) {
                case AIC_TAB:
                    ic = mainFrame.getFacade().computeInformationCriterion(alignment, models,
                    SelectionChunk.AIC, sampleSizeMode, sampleSize, confidenceInterval).getInformationCriterion();
                    break;
                case BIC_TAB:
                    ic = mainFrame.getFacade().computeInformationCriterion(alignment, models,
                    SelectionChunk.BIC, sampleSizeMode, sampleSize, confidenceInterval).getInformationCriterion();
                    break;
                case AICC_TAB:
                    ic = mainFrame.getFacade().computeInformationCriterion(alignment, models,
                    SelectionChunk.AICC, sampleSizeMode, sampleSize, confidenceInterval).getInformationCriterion();
                    break;
                case DT_TAB:
                    ic = mainFrame.getFacade().computeInformationCriterion(alignment, models,
                    SelectionChunk.DT, sampleSizeMode, sampleSize, confidenceInterval).getInformationCriterion();
                    break;
            }
        }
        if (ic != null) {
            mainFrame.enableHandler();
            ProtTestPrinter.printSelectionHeader(CRITERION_NAMES[resultsTabbedPane.getSelectedIndex()]);
            mainFrame.getFacade().printModelsSorted(ic);
            mainFrame.disableHandler();
        }
    }
}
