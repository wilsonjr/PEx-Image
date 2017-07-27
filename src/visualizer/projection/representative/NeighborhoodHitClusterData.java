/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.representative;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.datamining.dataanalysis.NeighborhoodHit;
import visualizer.matrix.Matrix;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.distance.Euclidean;

/**
 *
 * @author wilson
 */
public class NeighborhoodHitClusterData implements BoxplotDataGenerator {
    private final int nNeighbors;
    private AnalysisType type;
    
    public NeighborhoodHitClusterData(int nNeighbors, AnalysisType type) {
        this.nNeighbors = nNeighbors;
        this.type = type;
    }

    @Override
    public float[] generateLocalAnalysis(Matrix cluster) throws IOException {
        return NeighborhoodHit.getNHitValues(nNeighbors-1, cluster, new DistanceMatrix(cluster, new Euclidean()));
    }
    
    @Override
    public String toString() {
        return "Neighborhood Hit";
    }

    @Override
    public float[] generateGlobalAnalysis(Matrix selected, Matrix projection) throws IOException {
        float[] nHitSelected = new float[selected.getRowCount()];
        
        float[] nHitProjection = NeighborhoodHit.getNHitValues(nNeighbors-1, projection, 
                        new DistanceMatrix(projection, new Euclidean()));
        int size = 0;
        
        for( int i = 0; i < projection.getRowCount(); ++i )
            for( int j = 0; j < selected.getRowCount(); ++j )
                if( selected.getRow(j).getId().equals(projection.getRow(i).getId()) ) {
                    nHitSelected[size++] = nHitProjection[i];
                    break;
                }
            
        Logger.getLogger("Cluster Analysis Information").log(Level.INFO, "Size Global Analysis - {0}: {1}", new Object[]{toString(), size});
        return nHitSelected;
    }
    
    @Override
    public AnalysisType analysisType() {
        return type;
    }
    
    @Override
    public String getColor() {
        return "green";
    }
}
