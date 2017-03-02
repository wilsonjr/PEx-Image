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

package visualizer.topic;

import visualizer.graph.Graph;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class CovarianceTopicSettings extends javax.swing.JDialog {

    /** Creates new form CovarianceTopicSettings */
    private CovarianceTopicSettings(javax.swing.JDialog parent) {
        super(parent);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        cancekButton = new javax.swing.JButton();
        parametersPanel = new javax.swing.JPanel();
        percentageTermsLabel = new javax.swing.JLabel();
        percentageTopicsLabel = new javax.swing.JLabel();
        percentageTermsTextField = new javax.swing.JTextField();
        percentageTopicsTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Covariance Topics Settings");
        setModal(true);

        closeButton.setText("OK");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        cancekButton.setText("Cancel");
        cancekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancekButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancekButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        parametersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));
        parametersPanel.setLayout(new java.awt.GridBagLayout());

        percentageTermsLabel.setText("Percentage terms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(percentageTermsLabel, gridBagConstraints);

        percentageTopicsLabel.setText("Percentage topics");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(percentageTopicsLabel, gridBagConstraints);

        percentageTermsTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(percentageTermsTextField, gridBagConstraints);

        percentageTopicsTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(percentageTopicsTextField, gridBagConstraints);

        getContentPane().add(parametersPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    this.refreshData();
    this.setVisible(false);
}//GEN-LAST:event_closeButtonActionPerformed

private void cancekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancekButtonActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_cancekButtonActionPerformed

    public static CovarianceTopicSettings getInstance(javax.swing.JDialog parent) {
        if (instance == null || instance.getParent() != parent) {
            instance = new CovarianceTopicSettings(parent);
        }

        return instance;
    }

    public void display(Graph graph) {
        this.graph = graph;

        TopicData tdata = graph.getTopicData();
        this.percentageTermsTextField.setText(Float.toString(tdata.getPercentageTerms()));
        this.percentageTopicsTextField.setText(Float.toString(tdata.getPercentageTopics()));

        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private void refreshData() {
        TopicData tdata = graph.getTopicData();
        tdata.setPercentageTerms(Float.parseFloat(this.percentageTermsTextField.getText()));
        tdata.setPercentageTopics(Float.parseFloat(this.percentageTopicsTextField.getText()));
    }

    private static CovarianceTopicSettings instance;
    private Graph graph;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancekButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel parametersPanel;
    private javax.swing.JLabel percentageTermsLabel;
    private javax.swing.JTextField percentageTermsTextField;
    private javax.swing.JLabel percentageTopicsLabel;
    private javax.swing.JTextField percentageTopicsTextField;
    // End of variables declaration//GEN-END:variables
}
