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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import static utilities.encryptThisString.encryptThisString;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class MainAdminViewController implements Initializable {

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
    @FXML
    private JFXButton nyOpgaveButton;
    @FXML
    private JFXDatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            timeLoggerPane.toFront();
            
            model = new Model();
            for (Project projects : model.getAllProjects()) {
                projektComboBox.getItems().add(projects.getProjektNavn());
            }
//            projektComboBox.setItems(model.getAllProjects());
        
            fillColumns();

        } catch (IOException ex) {
            Logger.getLogger(MainUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException ex) {
            Logger.getLogger(MainUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
       
    }
    
   

    /**
     * Changes the view depending on which button in the side panel you click
     *
     * @param actionEvent
     */
    @FXML
    private void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == timeLoggerButton) {
            timeLoggerPane.toFront();
        }
        if (actionEvent.getSource() == opgaverButton) {
            opgaverPane.toFront();
        }
        if (actionEvent.getSource() == analyseButton) {
            analysePane.toFront();
        }
        if (actionEvent.getSource() == projekterButton) {
            projektPane.toFront();
        }
        if (actionEvent.getSource() == opretBrugerButton) {
            opretBrugerPane.toFront();
        }
    }

    private void fillColumns() throws ModelException
    {
        //Opgaver tableview
        opgaverTableView.setItems(model.getAllTasksProjektNavn());
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
    private void handleStartDate(ActionEvent event) {
    }

    @FXML
    private void handleEndDate(ActionEvent event) {
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
            opgaveData();
            opgaverTableView.setItems(model.refreshTasks());

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

    @FXML
    private void setProjectData(ActionEvent event) throws ModelException {
        projectData();

    }
    
    private void projectData() throws ModelException
    {
        List<Project> projectNames = model.getAllProjects();
        List<Project> result = new ArrayList<>();

        for (Project projects : projectNames) {
            if (projects.getProjektNavn().equals(projektComboBox.getSelectionModel().getSelectedItem())) {

                result.add(projects);
            }
        }

        sagsNrField.setText(result.get(0).getId() + "");
        kundeField.setText(result.get(0).getKundeId() + "");
        LocalDate localDate = LocalDate.parse(result.get(0).getStartDato());
        datePicker.setValue(localDate);

        if (projektComboBox.getSelectionModel().getSelectedItem() != null) {
            opgaveComboBox.setDisable(false);
            titelField.setDisable(false);
            timeField.setDisable(false);
            beskrivelseTextArea.setDisable(false);
            betaltCheckBox.setDisable(false);
            nyOpgaveButton.setDisable(false);
        } else {
            opgaveComboBox.setDisable(true);
            titelField.setDisable(true);
            timeField.setDisable(true);
            beskrivelseTextArea.setDisable(true);
            betaltCheckBox.setDisable(true);
            nyOpgaveButton.setDisable(true);
        }

        opgaveComboBox.getItems().clear();

        for (Task tasks : model.getAllTasksByProject(result.get(0).getId())) {
            opgaveComboBox.getItems().add(tasks.getOpgaveNavn());
        }
    }

    @FXML
    private void setOpgaveData(ActionEvent event) throws ModelException {
        opgaveData();
        if (titelField.getText() != null && timeField.getText() != null && beskrivelseTextArea.getText() != null) {
            btn_start.setDisable(false);
        }
    }

    private void opgaveData() throws ModelException {
        List<Task> taskNames = model.getAllTasks();
        List<Task> result = new ArrayList<>();

        for (Task tasks : taskNames) {
            if (tasks.getOpgaveNavn().equals(opgaveComboBox.getSelectionModel().getSelectedItem())) {

                result.add(tasks);
            }
        }

        if (opgaveComboBox.getSelectionModel().getSelectedItem() != null) {
            titelField.setText(result.get(0).getOpgaveNavn());

            long hours = (result.get(0).getBrugtTid() - result.get(0).getBrugtTid() % 3600) / 3600;
            long minutes = (result.get(0).getBrugtTid() % 3600 - result.get(0).getBrugtTid() % 3600 % 60) / 60;
            long seconds = result.get(0).getBrugtTid() % 3600 % 60;
            NumberFormat f = new DecimalFormat("00");
            timeField.setText(f.format(hours) + ":" + f.format(minutes) + ":" + f.format(seconds));
            beskrivelseTextArea.setText(result.get(0).getBeskrivelse());

            if (result.get(0).getBetalt() == 1) {
                betaltCheckBox.setSelected(true);
            }
        } else {
            titelField.clear();
            timeField.clear();
            beskrivelseTextArea.clear();
            betaltCheckBox.setSelected(false);
    private void handleCreateUser(ActionEvent event) throws ModelException
    {
        if (opretAdminCheckBox.isSelected())
        {
            System.out.println("it is true");
            String adminLogin = txt_userLogin.getText();
            String adminPassword = txt_userPassword.getText();
            model.createAdmin(adminLogin, encryptThisString(adminPassword));
            int adminId = model.getAdminId(adminLogin);
            model.createUserAdmin(null, null, adminId);
        } else
        {
            String userLogin = txt_userLogin.getText();
            String userPassword = txt_userPassword.getText();
            model.createUser(userLogin, encryptThisString(userPassword), null);
        }
    }

    @FXML
    private void createOpgave(ActionEvent event) throws ModelException {

        int projektId = Integer.parseInt(sagsNrField.getText());

        if (betaltCheckBox.isSelected() == true) {
            model.createTask(titelField.getText(), projektId, 0, LocalDate.now().toString(), beskrivelseTextArea.getText(), 1);
        } else {
            model.createTask(titelField.getText(), projektId, 0, LocalDate.now().toString(), beskrivelseTextArea.getText(), 0);
        }
        
        projectData();
        opgaveComboBox.getSelectionModel().select(titelField.getText());
        fillColumns();

    }

    @FXML
    private void handleCreateProjekt(ActionEvent event) {
    }

    @FXML
    private void handleCreateUser(ActionEvent event) {
    }

}
