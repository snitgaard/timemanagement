/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class MainUserViewController implements Initializable
{

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
    private JFXButton timeLoggerButton;
    @FXML
    private JFXButton opgaverButton;
    @FXML
    private TableColumn<Task, String> opgaveNavnColumn;
    @FXML
    private TableColumn<Project, String> projektNavnColumn;
    @FXML
    private TableColumn<Task, Integer> brugtTidColumn;
    @FXML
    private TableColumn<Task, String> datoColumn;
    @FXML
    private SplitPane opgaverPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXButton btn_start;
    @FXML
    private ImageView btn_close;

    private final long createdMillis = System.currentTimeMillis();
    private Model model;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private FontAwesomeIconView startIcon;
    @FXML
    private TableView<Task> opgaverTableView;
    private Project project;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            model = new Model();
            for (Project projects : model.getAllProjects())
            {
                projektComboBox.getItems().add(projects.getProjektNavn());
            }
//            projektComboBox.setItems(model.getAllProjects());
            for (Task tasks : model.getAllTasks())
            {
                opgaveComboBox.getItems().add(tasks.getOpgaveNavn());
            }
            opgaverTableView.setItems(model.getAllTasks());

        } catch (IOException ex)
        {
            Logger.getLogger(MainUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException ex)
        {
            Logger.getLogger(MainUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        opgaveNavnColumn.setCellValueFactory(new PropertyValueFactory<>("opgaveNavn"));
//        projektNavnColumn.setCellValueFactory(new PropertyValueFactory<>("projektNavn"));
        projektNavnColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProjektNavn()));
        brugtTidColumn.setCellValueFactory(new PropertyValueFactory<>("brugtTid"));
        datoColumn.setCellValueFactory(new PropertyValueFactory<>("dato"));

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

    /**
     * Gets the current time and disables the stop button. Calculating the time
     * used and displays is into the field.
     *
     * @param event
     */
    private void stopTidMethod() throws ParseException
    {
        try
        {
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            slutTidField.setText(sdf.format(date));

            String startTid = startTidField.getText();
            String slutTid = slutTidField.getText();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(startTid);
            Date date2 = format.parse(slutTid);
            long difference = date2.getTime() - date1.getTime();

            long input = difference / 1000;
            long hours = (input - input % 3600) / 3600;
            long minutes = (input % 3600 - input % 3600 % 60) / 60;
            long seconds = input % 3600 % 60;

            brugtTidField.setText(hours + " Hours  " + minutes + " Minutes  " + seconds + " Seconds  ");

        } catch (Exception e)
        {
        }
    }

    /**
     * Handles the start / stop time function and changes the button icon /
     * label depending on which action is to be performed. Also gets the time
     * different between when you press start and stop and calculates it into
     * HH:mm:ss using the stopTidMethod.
     *
     * @param event
     * @throws ParseException
     */
    @FXML
    private void handleTime(ActionEvent event) throws ParseException
    {
        if (startIcon.getGlyphName().equals("PAUSE"))
        {
            startIcon.setIcon(FontAwesomeIcon.PLAY);
            btn_start.setText("Start tid");
            stopTidMethod();
        } else if (startIcon.getGlyphName().equals("PLAY"))
        {
            startIcon.setIcon(FontAwesomeIcon.PAUSE);
            btn_start.setText("Stop tid");
            try
            {
                java.util.Date date = new java.util.Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                startTidField.setText(sdf.format(date));
                slutTidField.clear();
                brugtTidField.clear();
            } catch (Exception e)
            {
            }
        }
    }

    @FXML
    private void setProjectData(ActionEvent event) throws ModelException
    {
        List<Project> projectNames = model.getAllProjects();
        List<Project> result = new ArrayList<>();

        for (Project projects : projectNames)
        {
            if (projects.getProjektNavn().equals(projektComboBox.getSelectionModel().getSelectedItem()))
            {

                result.add(projects);

            }
        }

        sagsNrField.setText(result.get(0).getId() + "");
        kundeField.setText(result.get(0).getKunde());
    }
}
