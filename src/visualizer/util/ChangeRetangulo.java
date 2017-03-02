/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.util;

import br.com.methods.utils.OverlapRect;

/**
 *
 * @author wilson
 */
public class ChangeRetangulo {
    
    public OverlapRect first, second, third;
    
    public ChangeRetangulo() {}
    
    public ChangeRetangulo(OverlapRect first, OverlapRect second) {
        this.first = first;
        this.second = second;
    }
    
}
