/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author The Cowboys
 */
public class LoginController implements Initializable
{

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView btn_close;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXPasswordField passwordField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void close_app(MouseEvent event)
    {
    }

    @FXML
    private void minimize_app(MouseEvent event)
    {
    }

    @FXML
    private void handleLoginButton(ActionEvent event)
    {
    }
    
}
