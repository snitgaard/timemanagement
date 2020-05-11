/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.GUI.controller;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import timemanagement.BE.Project;
import timemanagement.gui.model.Model;
import timemanagement.gui.model.ModelException;

/**
 *
 * @author The Cowboys
 */
public class fillChart implements Runnable
{

    private Model model;
    private final long DELAY = 1;
    private int index = 0;

    public void initialize(URL url, ResourceBundle rb)
    {
        model = model.getInstance();
    }

    @Override
    public void run()
    {

        while (!Thread.currentThread().isInterrupted())
        {
            Platform.runLater(()
                    ->
            {
                
                int number = -1;
                try
                {
                    
                    for (Project allProject : model.getAllProjects())
                    {
                        
                            number = number + 1;
                            model.getAllProjects().get(number);
//                            TimeUnit.SECONDS.sleep(DELAY);
                            System.out.println(model.getAllProjects().get(number));
                            
                        
                        
                        
                    }
                    
                } catch (ModelException ex)
                {
                    java.util.logging.Logger.getLogger(fillChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                
                
            });
        }
    }
}


