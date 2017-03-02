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
package visualizer.projection.sammon;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.projection.ProjectionData;
import visualizer.projection.SourceType;
import visualizer.util.Util;
import visualizer.wizard.ProjectionView;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class SammonMappingProjectionView extends ProjectionView {

    /** Creates new form SammonMappingProjectionView */
    public SammonMappingProjectionView(ProjectionData pdata) {
        super(pdata);
        initComponents();

        int nrobjects = 0;
        
        if (this.pdata.getSourceType() == SourceType.CORPUS) {
            nrobjects = Util.countFiles(this.pdata.getSourceFile());
        } else if (pdata.getSourceType() == SourceType.DISTANCE_MATRIX) {
            try {
                nrobjects = Util.countObjectsDistanceFile(this.pdata.getSourceFile());
            } catch (IOException ex) {
                Logger.getLogger(SammonMappingProjectionView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (pdata.getSourceType() == SourceType.POINTS) {
            try {
                nrobjects = Util.countObjectsPointsFile(this.pdata.getSourceFile());
            } catch (IOException ex) {
                Logger.getLogger(SammonMappingProjectionView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.nIterationsTextField.setText(Integer.toString(nrobjects));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        improvementPanel = new javax.swing.JPanel();
        nIterationsLabel = new javax.swing.JLabel();
        mfLabel = new javax.swing.JLabel();
        mfTextField = new javax.swing.JTextField();
        nIterationsTextField = new javax.swing.JTextField();
        statusPanel = new javax.swing.JPanel();
        statusProgressBar = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Sammon's Mapping Projection"));
        setLayout(new java.awt.GridBagLayout());

        improvementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Sammon Mapping parameters"));
        improvementPanel.setLayout(new java.awt.GridBagLayout());

        nIterationsLabel.setText("Number of iterations");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        improvementPanel.add(nIterationsLabel, gridBagConstraints);

        mfLabel.setText("Magic Factor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        improvementPanel.add(mfLabel, gridBagConstraints);

        mfTextField.setColumns(5);
        mfTextField.setText("0.3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        improvementPanel.add(mfTextField, gridBagConstraints);

        nIterationsTextField.setColumns(5);
        nIterationsTextField.setText("100");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        improvementPanel.add(nIterationsTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(improvementPanel, gridBagConstraints);

        statusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));
        statusPanel.setLayout(new java.awt.BorderLayout());

        statusProgressBar.setPreferredSize(new java.awt.Dimension(350, 22));
        statusProgressBar.setStringPainted(true);
        statusPanel.add(statusProgressBar, java.awt.BorderLayout.SOUTH);

        statusLabel.setText("   ");
        statusLabel.setMinimumSize(new java.awt.Dimension(100, 22));
        statusLabel.setPreferredSize(new java.awt.Dimension(100, 22));
        statusPanel.add(statusLabel, java.awt.BorderLayout.NORTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(statusPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public void setStatus(String status, int value) {
        this.statusLabel.setText(status);
        this.statusProgressBar.setValue(value);
    }

    public void refreshData() {
        this.pdata.setFractionDelta(Float.parseFloat(this.mfTextField.getText()));
        this.pdata.setNumberIterations(Integer.parseInt(this.nIterationsTextField.getText()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel improvementPanel;
    private javax.swing.JLabel mfLabel;
    private javax.swing.JTextField mfTextField;
    private javax.swing.JLabel nIterationsLabel;
    private javax.swing.JTextField nIterationsTextField;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JProgressBar statusProgressBar;
    // End of variables declaration//GEN-END:variables
}
