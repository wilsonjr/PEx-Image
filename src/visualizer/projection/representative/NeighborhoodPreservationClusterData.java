/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.representative;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.datamining.dataanalysis.NeighborhoodPreservation;
import visualizer.matrix.Matrix;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.distance.Euclidean;
import visualizer.util.KNN;
import visualizer.util.Pair;

/**
 *
 * @author wilson
 */
public class NeighborhoodPreservationClusterData implements BoxplotDataGenerator {
    private int nNeighbors;
    private DistanceMatrix dmatdata;
    private DistanceMatrix dmatdataProjection;
    private AnalysisType type;
    
        
    public NeighborhoodPreservationClusterData(int nNeighbors, DistanceMatrix dmatdata, DistanceMatrix dmatdataProjection, AnalysisType type) {
        this.nNeighbors = nNeighbors;
        this.dmatdata = dmatdata;
        this.dmatdataProjection = dmatdataProjection;
        this.type = type;
    }
    
    @Override
    public float[] generateLocalAnalysis(Matrix cluster) throws IOException {
        
        
        DistanceMatrix dmatproj = new DistanceMatrix(cluster, new Euclidean());
        
        
        
        if( dmatdata.getElementCount() != dmatproj.getElementCount() ) {
            throw new IOException("Data set different from projection.");
        }

        KNN knnproj = new KNN(nNeighbors);
        Pair[][] nproj = knnproj.execute(dmatproj);

        KNN knndata = new KNN(nNeighbors);
        Pair[][] ndata = knndata.execute(dmatdata);
        
        float[] nPreservationProjection = NeighborhoodPreservation.getNPreservationValues(nNeighbors-1, 
                new DistanceMatrix(cluster, new Euclidean()), nproj, ndata);

        return nPreservationProjection;
    }
        
    @Override
    public String toString() {
        return "Neighborhood Preservation";
    }

    @Override
    public float[] generateGlobalAnalysis(Matrix selected, Matrix projection) throws IOException {
        float[] nPreservationSelected = new float[selected.getRowCount()];
        
        DistanceMatrix dmatproj = new DistanceMatrix(projection, new Euclidean());
        if (dmatdataProjection.getElementCount() != dmatproj.getElementCount()) {
            throw new IOException("Data set different from projection.");
        }
        
        KNN knnproj = new KNN(nNeighbors);
        Pair[][] nproj = knnproj.execute(dmatproj);

        KNN knndata = new KNN(nNeighbors);
        Pair[][] ndata = knndata.execute(dmatdataProjection);

        
        float[] nPreservationProjection = NeighborhoodPreservation.getNPreservationValues(nNeighbors-1, 
                dmatproj, nproj, ndata);
        
        int size = 0;        
        for( int i = 0; i < projection.getRowCount(); ++i )
            for( int j = 0; j < selected.getRowCount(); ++j )
                if( selected.getRow(j).getId().equals(projection.getRow(i).getId()) ) {
                    nPreservationSelected[size++] = nPreservationProjection[i];
                    break;
                }
                    
        
        Logger.getLogger("Cluster Analysis Information").log(Level.INFO, "Size Global Analysis - {0}: {1}", new Object[]{toString(), size});
        return nPreservationSelected;
    }

    @Override
    public AnalysisType analysisType() {
        return type;
    }
}
