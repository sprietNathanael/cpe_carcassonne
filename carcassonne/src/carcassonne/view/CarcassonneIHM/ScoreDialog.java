/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import javax.swing.JDialog;

/**
 *
 * @author Bertrand
 */
public class ScoreDialog extends JDialog
{
    public ScoreDialog(){
        initComponent();
    }
    
    private void initComponent()
    {
        this.setTitle("Score détaillé");
        
    }
}
