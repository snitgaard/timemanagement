/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class MainUserViewController implements Initializable
{

    @FXML
    private SplitPane timeLoggerPane;
    @FXML
    private JFXComboBox<?> projektComboBox;
    @FXML
    private JFXComboBox<?> opgaveComboBox;
    @FXML
    private JFXTextField startTidField;
    @FXML
    private JFXTextField slutTidField;
    @FXML
    private JFXTextField brugtTidField;
    @FXML
    private JFXTextField sagsNrField;
    @FXML
    private JFXCheckBox betaltCheckBox;
    @FXML
    private JFXTextField kundeField;
    @FXML
    private JFXTextField medarbejderField;
    @FXML
    private JFXTextField timeField;
    @FXML
    private JFXTextField titelField;
    @FXML
    private JFXTextArea beskrivelseTextArea;
    @FXML
    private JFXButton timeLoggerButton;
    @FXML
    private JFXButton opgaverButton;
    @FXML
    private TableColumn<?, ?> opgaveNavnColumn;
    @FXML
    private TableColumn<?, ?> projektNavnColumn;
    @FXML
    private TableColumn<?, ?> brugtTidColumn;
    @FXML
    private TableColumn<?, ?> datoColumn;
    @FXML
    private SplitPane opgaverPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXButton btn_start;
    @FXML
    private ImageView btn_close;
    @FXML
    private JFXButton btn_stop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        opgaverPane.setResizableWithParent(stackPane, Boolean.FALSE);
    }

    @FXML
    private void handleClicks(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() == timeLoggerButton)
        {
            timeLoggerPane.toFront();
        }
        if (actionEvent.getSource() == opgaverButton)
        {
            opgaverPane.toFront();
        }
    }


    /**
     * Closes the stage, since this is the very first stage, it will close the program completely.
     *
     * @param event
     */
    @FXML
    private void close_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    /**
     * Minimizes the stage
     *
     * @param event
     */
    @FXML
    private void minimize_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    
     /**
     * Gets the current time and disables the start button
     *
     * @param event
     */
    
    @FXML
    private void starttime(ActionEvent event)
    {
        try 
            
        {
                java.util.Date date = new java.util.Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                startTidField.setText(sdf.format(date));
                btn_start.setDisable(true);
                btn_stop.setDisable(false);
                slutTidField.clear();
                brugtTidField.clear();
        }
        catch (Exception e) {
        }
    }

    
     /**
     * Gets the current time and disables the stop button.
     * Calculating the time used and displays is into the field. 
     * 
     * @param event
     */
    
    @FXML
    private void stopTid(ActionEvent event) 
{
        try 
            
        {
                java.util.Date date = new java.util.Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                slutTidField.setText(sdf.format(date));
                btn_start.setDisable(false);
                btn_stop.setDisable(true);
                
               String startTid = startTidField.getText();
               String slutTid = slutTidField.getText();
               SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
               Date date1 = format.parse(startTid);
               Date date2 = format.parse(slutTid);
               long difference = date2.getTime() - date1.getTime(); 
               

               
               long input = difference / 1000;
               long hours = (input - input%3600)/3600;
               long minutes = (input%3600 - input%3600%60)/60;
               long seconds = input%3600%60;
               
               brugtTidField.setText(hours +  " Hours  " + minutes + " Minutes  " + seconds + " Seconds  ");
               
               
                
                
        }
        
        catch (Exception e){
        }
    }
}
