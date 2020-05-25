/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import timemanagement.BE.User;
import timemanagement.gui.model.Model;
import timemanagement.gui.model.ModelException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import static utilities.encryptThisString.encryptThisString;

/**
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

    private ExecutorService executor;
    private Runnable fillChart;

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private JFXButton btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        model = model.getInstance();

    }

    /**
     * Closes the stage, since this is the very first stage, it will close the
     * program completely.
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
        String password = encryptThisString(passwordField.getText());

        if (model.checkUserCredentials(username, password, 0))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/timemanagement/gui/view/TimeLoggerView.fxml"));
            redirectToStage(fxmlLoader);
            TimeLoggerViewController mainAdminController = fxmlLoader.getController();
            mainAdminController.ApplyImportantData(model.getSpecificUser(username));

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();

        } else if (model.checkUserCredentials(username, password, 1))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/timemanagement/gui/view/TimeLoggerView.fxml"));
            redirectToStage(fxmlLoader);
            TimeLoggerViewController mainAdminController = fxmlLoader.getController();
            mainAdminController.showAdminButtons();
            mainAdminController.ApplyImportantData(model.getSpecificUser(username));
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
        stage.setAlwaysOnTop(false);
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
