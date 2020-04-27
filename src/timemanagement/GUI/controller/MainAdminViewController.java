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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.gui.model.Model;
import timemanagement.gui.model.ModelException;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class MainAdminViewController implements Initializable
{

    @FXML
    private JFXButton timeLoggerButton;
    @FXML
    private JFXButton opgaverButton;
    @FXML
    private JFXButton analyseButton;
    @FXML
    private JFXButton projekterButton;
    @FXML
    private StackPane stackPane;
    @FXML
    private SplitPane projektPane;
    @FXML
    private SplitPane analysePane;
    @FXML
    private SplitPane opgaverPane;
    @FXML
    private TableColumn<Task, String> opgaveNavnColumn;
    @FXML
    private TableColumn<Task, String> projektNavnColumn;
    @FXML
    private TableColumn<Task, Integer> brugtTidColumn;
    @FXML
    private TableColumn<Task, String> datoColumn;
    @FXML
    private SplitPane timeLoggerPane;
    @FXML
    private JFXComboBox<String> projektComboBox;
    @FXML
    private JFXComboBox<String> opgaveComboBox;
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
    private FontAwesomeIconView startIcon;
    @FXML
    private JFXButton startTime;
    private Model model;
    @FXML
    private ImageView btn_close;
    @FXML
    private TableView<Task> opgaverTableView;
    @FXML
    private JFXComboBox<String> projektComboBox2;
    @FXML
    private JFXButton createProjekt;
    private JFXTextField kundeNavn;
    @FXML
    private JFXTextField txt_projektNavn;
    @FXML
    private JFXTextField txt_kundeNavn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        try
        {
            model = new Model();
            for (Project projects : model.getAllProjects())
            {
                projektComboBox.getItems().add(projects.getProjektNavn());
            }
            for (Project projects : model.getAllProjects())
            {
                projektComboBox2.getItems().add(projects.getProjektNavn());
            }
//            projektComboBox.setItems(model.getAllProjects());
            for (Task tasks : model.getAllTasks())
            {
                opgaveComboBox.getItems().add(tasks.getOpgaveNavn());
            }
            opgaverTableView.setItems(model.getAllTasksProjektNavn());
            
        } catch (IOException ex)
        {
            Logger.getLogger(MainUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException ex)
        {
            Logger.getLogger(MainUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        opgaveNavnColumn.setCellValueFactory(new PropertyValueFactory<>("opgaveNavn"));
        projektNavnColumn.setCellValueFactory(new PropertyValueFactory<>("projektNavn"));
        brugtTidColumn.setCellValueFactory(new PropertyValueFactory<>("brugtTid"));
        datoColumn.setCellValueFactory(new PropertyValueFactory<>("dato"));
    }

    /**
     * Changes the view depending on which button in the side panel you click
     *
     * @param actionEvent
     */
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
        if (actionEvent.getSource() == analyseButton)
        {
            analysePane.toFront();
        }
        if (actionEvent.getSource() == projekterButton)
        {
            projektPane.toFront();
        }
    }

    @FXML
    private void handleStartDate(ActionEvent event)
    {
    }

    @FXML
    private void handleEndDate(ActionEvent event)
    {
    }

    /**
     * Handles the start / stop time function and changes the button icon /
     * label depending on which action is to be performed.
     *
     * @param event
     */
    @FXML
    private void handleStartTime(ActionEvent event)
    {
        if (startIcon.getGlyphName().equals("PAUSE"))
        {
            startIcon.setIcon(FontAwesomeIcon.PLAY);
            startTime.setText("Start tid");
        } else if (startIcon.getGlyphName().equals("PLAY"))
        {
            startIcon.setIcon(FontAwesomeIcon.PAUSE);
            startTime.setText("Stop tid");
        }
    }

    /**
     * Minimizes the stage
     *
     * @param event
     */
    @FXML
    private void minimize_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Closes the stage, since this is the very first stage, it will close the
     * program completely.
     *
     * @param event
     */
    @FXML
    private void close_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCreateProjekt(ActionEvent event) throws ModelException {
        String projektNavn = txt_projektNavn.getText();
        String kunde = txt_kundeNavn.getText();
        String startDato = LocalDate.now()+"";
        model.createProjekt(projektNavn, kunde, startDato);
    }

}
