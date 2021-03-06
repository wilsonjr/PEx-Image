/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.datamining.dataanalysis;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import visualizer.graph.Graph;
import visualizer.util.OpenDialog;
import visualizer.util.filefilter.XMLFilter;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class NeighborhoodHitView extends javax.swing.JDialog {

    /** Creates new form NeighborhoodHitView */
    private NeighborhoodHitView(java.awt.Frame parent) {
        super(parent);
        initComponents();
        initModels();

        this.projTable.setModel(this.projTableModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonPanel = new javax.swing.JPanel();
        generateButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        projPanel = new javax.swing.JPanel();
        projScrollPane = new javax.swing.JScrollPane();
        projTable = new javax.swing.JTable();
        projButtonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        nrNeighborsPanel = new javax.swing.JPanel();
        nrNeighborsLabel = new javax.swing.JLabel();
        nrNeighborsTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Neighborhood Hit");
        setModal(true);

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(generateButton);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.PAGE_END);

        projPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Projections"));
        projPanel.setLayout(new java.awt.BorderLayout(5, 5));

        projScrollPane.setViewportView(projTable);

        projPanel.add(projScrollPane, java.awt.BorderLayout.CENTER);

        projButtonPanel.setLayout(new java.awt.GridBagLayout());

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projButtonPanel.add(addButton, gridBagConstraints);

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projButtonPanel.add(removeButton, gridBagConstraints);

        projPanel.add(projButtonPanel, java.awt.BorderLayout.LINE_END);

        nrNeighborsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        nrNeighborsLabel.setText("Number of neighbors");
        nrNeighborsPanel.add(nrNeighborsLabel);

        nrNeighborsTextField.setColumns(10);
        nrNeighborsTextField.setText("30");
        nrNeighborsPanel.add(nrNeighborsTextField);

        projPanel.add(nrNeighborsPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(projPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int result = OpenDialog.showOpenDialog(new XMLFilter(), this);

        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            String description = filename.substring(filename.lastIndexOf("\\") + 1,
                    filename.lastIndexOf("."));

            this.projTableModel.addRow(new String[]{description, filename});
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        int index = this.projTable.getSelectedRow();

        if (index > -1) {
            this.projTableModel.removeRow(index);
        }        
    }//GEN-LAST:event_removeButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        
        ArrayList<NeighborhoodHit.Serie> series = new ArrayList<NeighborhoodHit.Serie>();

        for (int i = 0; i < this.projTable.getRowCount(); i++) {
            String filename = (String) this.projTable.getValueAt(i, 1);

            if (filename.trim().length() > 0) {
                String description = (String) this.projTable.getValueAt(i, 0);

                NeighborhoodHit.Serie serie = new NeighborhoodHit.Serie(description, filename);

                series.add(serie);
            }
        }

        NeighborhoodHit.getInstance(this).display(series, Integer.parseInt(this.nrNeighborsTextField.getText()));        
    }//GEN-LAST:event_generateButtonActionPerformed

    public static NeighborhoodHitView getInstance(javax.swing.JFrame parent) {
        return new NeighborhoodHitView(parent);
    }
    
    public void display(Graph graph) {
        this.graph = graph;
        display();
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private void initModels() {
        String[] titles = new String[]{"Description", "File name"};
        this.projTableModel = new DefaultTableModel(null, titles);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                NeighborhoodHitView dialog = new NeighborhoodHitView(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }

                });
                dialog.setVisible(true);
            }

        });
    }

    private Graph graph;
    private DefaultTableModel projTableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel nrNeighborsLabel;
    private javax.swing.JPanel nrNeighborsPanel;
    private javax.swing.JTextField nrNeighborsTextField;
    private javax.swing.JPanel projButtonPanel;
    private javax.swing.JPanel projPanel;
    private javax.swing.JScrollPane projScrollPane;
    private javax.swing.JTable projTable;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
