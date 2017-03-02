/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.graph;

import java.awt.Graphics;

/**
 *
 * @author wilson
 */
public class ClusterRenderer extends Renderer {
    
    @Override
    public void render(Graphics g, Representative rep) {
        super.render(g, rep);
        if( Representative.isShowRepresentative() || rep.isShowThisRepresentative() )
            rep.setShowThisRepresentative(false);
    }
    
    
    
}
