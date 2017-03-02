/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.representative;

/**
 *
 * @author wilson
 */
public enum AnalysisType {
    GLOBAL(0), LOCAL(1), BOTH(2);
    
    private final int value;
    
    AnalysisType(int value) {
        this.value = value;
    }
    
    public static AnalysisType which(int c) {
        switch( c ) {
            case 0:
                return GLOBAL;
            case 1:
                return LOCAL;
            case 2:
                return BOTH;
            
        }
        
        return GLOBAL;
    }
    
    public int getValue() {
        return value;
    }
    
}
