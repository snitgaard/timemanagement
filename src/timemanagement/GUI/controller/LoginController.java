/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import timemanagement.BE.Admin;
import timemanagement.BE.User;
import timemanagement.gui.model.Model;
import timemanagement.gui.model.ModelException;


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
    private Model model;
    private User selectedUser;
    private Admin selectedAdmin;
    
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private JFXButton btnLogin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            model = new Model();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void handleLoginButton(ActionEvent event) throws ModelException, IOException
    {
        String username = emailField.getText();
        String password = passwordField.getText();

        if (model.checkUserCredentials(username, password))
        {
            User selectedUser = model.getSpecificUser(username);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/timemanagement/gui/view/MainUserView.fxml"));
            redirectToStage(fxmlLoader);
            MainUserViewController mainUserController = fxmlLoader.getController();
            // Here the StudentAttendanceController is given important data objects,
            // This secures that it is the correct ones we are working with.
//            mainUserController.ApplyImportantData(model, this, selectedUser);

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        

        } else if (model.checkAdminCredentials(username, password))
        {
            Admin selectedAdmin = model.getSpecificAdmin(username);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/timemanagement/gui/view/MainAdminView.fxml"));
            redirectToStage(fxmlLoader);
            MainAdminViewController mainAdmincontroller = fxmlLoader.getController();
//            // Here the TeacherMainController is given important data objects,
//            // This secures that it is the correct ones we are working with.
//            teachercontroller.ApplyImportantData(model, this, selectedTeacher);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        } else
        {
            Border warning = new Border(new BorderStroke(javafx.scene.paint.Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
            emailField.setBorder(warning);
            passwordField.setBorder(warning);
        }
    }
    
    private void redirectToStage(FXMLLoader fxmlLoader) throws IOException
    {
        Parent root = fxmlLoader.load();
        Object c = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
        root.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }
    
}
