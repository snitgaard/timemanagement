/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.GUI.controller;

import java.net.URL;
import java.util.ResourceBundle;
import timemanagement.gui.model.Model;


/**
 *
 * @author The Cowboys
 */
public class fillChart implements Runnable {

    private Model model;
    private final long DELAY = 1;
    private int index = 0;
    
    public void initialize(URL url, ResourceBundle rb)
    {
        model = model.getInstance();
    }
    
    @Override
    public void run() {

        try {
            
            while (!Thread.currentThread().isInterrupted()) {
                Platform.runLater(()
                        -> {
                    
                
                        int number = -1;
                        long DELAY = 1;
                        int index = 0;
                    for (Project allProject : model.getAllProjects()) {
                        try {
                            number = number + 1;
                            model.getAllProjects().get(number);
                            System.out.println(model.getAllProjects().get(number));

                        } catch (ModelException ex) {
                            Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    index = (index + 1) % images.size();
                    TimeUnit.SECONDS.sleep(DELAY);
                });
                        }
            }catch (InterruptedException ex)
            {
                System.out.println(ex);
            }  
    }
}
