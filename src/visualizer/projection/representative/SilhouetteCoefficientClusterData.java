/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.representative;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.datamining.clustering.SilhouetteCoefficient;
import visualizer.matrix.Matrix;
import visualizer.projection.distance.Dissimilarity;

/**
 *
 * @author wilson
 */
public class SilhouetteCoefficientClusterData implements BoxplotDataGenerator {
    private Dissimilarity diss;
    private AnalysisType type;
    
    public SilhouetteCoefficientClusterData(Dissimilarity diss, AnalysisType type) {
        this.diss = diss;
        this.type = type;
    }    
    
    @Override
    public float[] generateLocalAnalysis(Matrix cluster) throws IOException {
        return new SilhouetteCoefficient().execute(cluster, diss);
    }
    
    @Override
    public String toString() {
        return "Silhouette Coefficient";
    }

    @Override
    public float[] generateGlobalAnalysis(Matrix selected, Matrix projection) throws IOException {
        float[] scSelected = new float[selected.getRowCount()];
        
        SilhouetteCoefficient sc = new SilhouetteCoefficient();
        float[] scProjection = sc.execute(projection, diss);
        
        int size = 0;
        
        
        for( int i = 0; i < projection.getRowCount(); ++i )
            for( int j = 0; j < selected.getRowCount(); ++j )
                if( selected.getRow(j).getId().equals(projection.getRow(i).getId()) )
                    scSelected[size++] = scProjection[i];
        
        Logger.getLogger("Cluster Analysis Information").log(Level.INFO, "Size Global Analysis - {0}: {1}", new Object[]{toString(), size});
        
        return scSelected;
    }
    
    @Override
    public AnalysisType analysisType() {
        return type;
    }
    
    @Override
    public String getColor() {
        return "orange";
    }
}
