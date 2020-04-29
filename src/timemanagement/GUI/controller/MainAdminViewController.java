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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import static javax.swing.UIManager.getInt;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.Kunde;
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
    private Model model;
    @FXML
    private ImageView btn_close;
    @FXML
    private TableView<Task> opgaverTableView;
    @FXML
    private JFXComboBox<String> projektComboBox2;
    @FXML
    private JFXTextField txt_projektNavn;
    @FXML
    private JFXTextField txt_kundeNavn;
    @FXML
    private JFXButton btn_start;
    @FXML
    private JFXTextField txt_userPassword;
    @FXML
    private JFXTextField txt_userLogin;
    @FXML
    private JFXButton opretBrugerButton;
    @FXML
    private SplitPane opretBrugerPane;
    @FXML
    private TableView<Project> projekterTableView;
    @FXML
    private TableColumn<Project, String> kundeColumn;
    @FXML
    private TableColumn<Project, String> projektNavnAdminColumn;
    @FXML
    private TableColumn<Project, Integer> brugtTidAdminColumn;
    @FXML
    private JFXCheckBox opretAdminCheckBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        try
        {
            timeLoggerPane.toFront();
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
            projekterTableView.setItems(model.getProjectKundeNavn());

        } catch (IOException ex)
        {
            Logger.getLogger(MainUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException ex)
        {
            Logger.getLogger(MainUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fillColumns();
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
        if (actionEvent.getSource() == opretBrugerButton)
        {
            opretBrugerPane.toFront();
        }
    }

    private void fillColumns()
    {
        //Opgaver tableview
        opgaveNavnColumn.setCellValueFactory(new PropertyValueFactory<>("opgaveNavn"));
        projektNavnColumn.setCellValueFactory(new PropertyValueFactory<>("projektNavn"));
        brugtTidColumn.setCellValueFactory(new PropertyValueFactory<>("brugtTid"));
        datoColumn.setCellValueFactory(new PropertyValueFactory<>("dato"));

        //Projekter tableview
        projektNavnAdminColumn.setCellValueFactory(new PropertyValueFactory<>("projektNavn"));
        kundeColumn.setCellValueFactory(new PropertyValueFactory<>("kundeNavn"));
        brugtTidAdminColumn.setCellValueFactory(new PropertyValueFactory<>("brugtTid"));
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
            model.addTime(input, opgaveComboBox.getSelectionModel().getSelectedItem());

        } catch (Exception e)
        {
        }
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
     *
     * @param event
     * @throws ModelException
     */
    @FXML
    private void handleCreateProjekt(ActionEvent event) throws ModelException
    {
        String projektNavn = txt_projektNavn.getText();
        System.out.println("projektNavn =" + projektNavn);
        String kundeNavn = txt_kundeNavn.getText();
        String startDato = LocalDate.now() + "";

        System.out.println("startDato =" + startDato);
        System.out.println("kundeNavn =" + kundeNavn);
        System.out.println("specific kunde =" + model.getKundeId(kundeNavn));
        model.createKunde(kundeNavn);
        int kundeId = model.getKundeId(kundeNavn);

        System.out.println("kundeId =" + kundeId);
        if (model.createKunde(kundeNavn) == true)
        {
            model.createProjekt(projektNavn, kundeId, startDato);
            projekterTableView.setItems(model.refreshProjects());
        }
    }

    @FXML
    private void handleCreateUser(ActionEvent event) throws ModelException
    {
        if (opretAdminCheckBox.isSelected())
        {
            System.out.println("it is true");
            String adminLogin = txt_userLogin.getText();
            String adminPassword = txt_userPassword.getText();
            model.createAdmin(adminLogin, adminPassword);
        } else
        {
            String userLogin = txt_userLogin.getText();
            String userPassword = txt_userPassword.getText();
            model.createUser(userLogin, userPassword);
        }
    }
}
