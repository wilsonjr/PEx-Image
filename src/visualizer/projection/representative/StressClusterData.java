/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.representative;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.datamining.dataanalysis.Stress;
import visualizer.matrix.Matrix;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.distance.Euclidean;

/**
 *
 * @author wilson
 */
public class StressClusterData implements BoxplotDataGenerator {
    private DistanceMatrix dmatdata;
    private DistanceMatrix dmatdataProjection;
    private Stress algorithm;
    private AnalysisType type;
    
    public StressClusterData(DistanceMatrix dmatdataProjection, DistanceMatrix dmatdata, Stress algorithm, AnalysisType type) {
        this.dmatdata = dmatdata;
        this.algorithm = algorithm;
        this.dmatdataProjection = dmatdataProjection;
        this.type = type;
    }

    @Override
    public float[] generateLocalAnalysis(Matrix cluster) throws IOException {          
        return algorithm.calculateGroup(dmatdata, new DistanceMatrix(cluster, new Euclidean()));
    }
    
    @Override
    public String toString() {
        return "Stress";
    }

    @Override
    public float[] generateGlobalAnalysis(Matrix selected, Matrix projection) throws IOException {
        
        float[] stressSelected = new float[selected.getRowCount()];
        
        DistanceMatrix dmatprj = new DistanceMatrix(projection, new Euclidean());
        float[] values = algorithm.calculateGroup(dmatdataProjection, dmatprj);
        
        int size = 0;
        for( int i = 0; i < projection.getRowCount(); ++i )
            for( int j = 0; j < selected.getRowCount(); ++j )
                if( selected.getRow(j).getId().equals(projection.getRow(i).getId()) ) {
                    stressSelected[size++] = values[i];
                    break;
                }
                    
        Logger.getLogger("Cluster Analysis Information").log(Level.INFO, "Size Global Analysis - {0}: {1}", new Object[]{toString(), size});
        
        return stressSelected;
    }
    
    @Override
    public AnalysisType analysisType() {
        return type;
    }
    
    @Override
    public String getColor() {
        return "red";
    }
}
