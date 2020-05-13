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
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import timemanagement.BE.Kunde;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;
import timemanagement.DAL.DalException;
import timemanagement.gui.model.Model;
import timemanagement.gui.model.ModelException;
import static utilities.encryptThisString.encryptThisString;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class MainAdminViewController implements Initializable
{

    @FXML
    private TableColumn<Kunde, String> clientNameColumn;
    @FXML
    private TableColumn<Kunde, String> clientContactColumn;
    @FXML
    private TableColumn<Kunde, String> clientEmailColumn;
    @FXML
    private TableColumn<Kunde, Double> clientHourlyRateColumn;
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
    private TableColumn<Task, Long> brugtTidColumn;
    @FXML
    private TableColumn<Task, String> datoColumn;
    @FXML
    private SplitPane timeLoggerPane;
    @FXML
    private JFXComboBox<Project> projektComboBox;
    @FXML
    private JFXComboBox<Task> opgaveComboBox;
    @FXML
    private JFXTextField startTidField;
    @FXML
    private JFXTextField slutTidField;
    @FXML
    private JFXTextField brugtTidField;
    @FXML
    private JFXCheckBox betaltCheckBox;
    @FXML
    private JFXTextField kundeField;
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
    private JFXComboBox<Project> projektComboBox2;
    @FXML
    private JFXTextField txt_projektNavn;
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
    private TableColumn<Project, Long> brugtTidAdminColumn;
    @FXML
    private JFXCheckBox opretAdminCheckBox;
    @FXML
    private JFXButton nyOpgaveButton;
    @FXML
    private TableView<User> userView;
    @FXML
    private TableColumn<User, String> userViewEmail;
    @FXML
    private JFXTextField txt_hourlyRate;
    @FXML
    private JFXTextField txt_nyBrugtTid;
    private User selectedUser;
    private TableColumn<Task, Integer> idColumn;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXButton btnTaskClearFilter;
    @FXML
    private TableColumn<User, String> userViewRolle;
    @FXML
    private JFXComboBox<String> userComboBox;
    @FXML
    private JFXCheckBox ongoingCheckbox;
    private TableColumn<Project, Integer> archivedColumn;
    @FXML
    private Label loginTextField;
    private LoginController controller;
    private String username;
    ObservableList<User> allUsersResultList = FXCollections.observableArrayList();
    ObservableList<Project> allProjectsFilteredList = FXCollections.observableArrayList();
    ObservableList<Task> filteredTaskList = FXCollections.observableArrayList();
    @FXML
    private JFXButton clientButton;
    @FXML
    private TableView<Kunde> clientTableView;
    @FXML
    private JFXTextField txt_Contact;
    @FXML
    private JFXTextField txt_Email;
    @FXML
    private JFXTextField txt_Client;
    @FXML
    private SplitPane clientPane;
    
    private JFXTextField txt_HourlyRate;
    
    ListView<String> onGoing = new ListView<>();
    
    @FXML
    private JFXTextField txt_ClientHourlyRate;
    @FXML
    private JFXComboBox<Kunde> clientComboBox;
    @FXML
    private BarChart<String, Long> barChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private JFXTextField txt_HourlyRateProject;
    @FXML
    private TableColumn<Project, Double> hourlyRateAdminColumn;

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

            model = model.getInstance();
            setProjects();

            ObservableList<String> roles = FXCollections.observableArrayList("Admin", "User");
            userComboBox.setItems(roles);

//            projekterTableView.setItems(model.getProjectKundeNavn());
            
            fillChart();

        } catch (ModelException ex)
        {
            Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ApplyImportantData(User selectedUser) throws ModelException
    {
        this.selectedUser = selectedUser;
        loginTextField.setText(selectedUser + "");
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
        if (actionEvent.getSource() == clientButton)
        {
            clientPane.toFront();
        }
    }

    /**
     * Makes the admin buttons visible for admins only
     */
    public void showAdminButtons()
    {
        analyseButton.setVisible(true);
        projekterButton.setVisible(true);
        opretBrugerButton.setVisible(true);
        clientButton.setVisible(true);
    }

    /**
     * Fills the project combobox with projects
     *
     * @throws ModelException
     */
    private void setProjects() throws ModelException
    {
        projektComboBox.setItems(model.getProjectKundeNavn());
        projektComboBox2.setItems(model.getAllProjects());
//        for (Project projects : model.getAllProjects())
//        {
//            projektComboBox.getItems().add(projects.ge);
//        }
    }

    /**
     * Fills the columns in the view with proper data. Seperates the columns and
     * the data within the columns.
     *
     * @throws ModelException
     */
    private void fillColumns() throws ModelException
    {
        //Opgaver tableview
        List<Task> taskList = model.getAllTasksProjektNavn();
        
        
        for (Task task : taskList) {
            if (task.getUserId() == selectedUser.getId())
            {
                filteredTaskList.add(task);
            }
        }
        
        opgaverTableView.setItems(filteredTaskList);
        
        opgaveNavnColumn.setCellValueFactory(cellData -> cellData.getValue().opgaveNavnProperty());
        projektNavnColumn.setCellValueFactory(cellData -> cellData.getValue().projektNavnProperty());
        brugtTidColumn.setCellValueFactory(cellData -> cellData.getValue().brugtTidObservableValue());
        datoColumn.setCellValueFactory(cellData -> cellData.getValue().datoProperty());

        //Projekter tableview
        setProjectTable();

        //User & Admin views
        fillUserAdminViews();

        //Client view
        fillClientView();
    }

    private void fillClientView() throws ModelException
    {
        clientTableView.setItems(model.getAllKunder());
        clientNameColumn.setCellValueFactory(cellData -> cellData.getValue().kundeNavnProperty());
        clientContactColumn.setCellValueFactory(cellData -> cellData.getValue().kontaktPersonProperty());
        clientEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        clientHourlyRateColumn.setCellValueFactory(cellData -> cellData.getValue().hourlyRateObservable());
    }

    private void fillUserAdminViews() throws ModelException
    {
        userView.setItems(model.getAllUsers());

        userViewEmail.setCellValueFactory(cellData -> cellData.getValue().userLoginProperty());
        userViewRolle.setCellValueFactory(cellData -> cellData.getValue().adminRighsProperty());
    }

    private void setProjectTable() throws ModelException
    {
        clientComboBox.setItems(model.getAllKunder());
        List<Project> allProjectsList = model.getProjectKundeNavn();
        ObservableList<Project> allProjectsResultList = FXCollections.observableArrayList();
        allProjectsFilteredList.clear();

        int brugtTidMinutter = 0;
        for (Project project1 : allProjectsList)
        {

            if (project1.getOngoing() == 1)
            {
                allProjectsFilteredList.add(project1);
            }

            allProjectsResultList.add(project1);

        }

        if (ongoingCheckbox.isSelected() == true)
        {
            projekterTableView.setItems(allProjectsFilteredList);

        } else
        {
            projekterTableView.setItems(model.getProjectKundeNavn());
            
        }

        projektNavnAdminColumn.setCellValueFactory(cellData -> cellData.getValue().projektNavnProperty());
        kundeColumn.setCellValueFactory(cellData -> cellData.getValue().kundeNavnProperty());
        brugtTidAdminColumn.setCellValueFactory(cellData -> cellData.getValue().brugtTidObservable());
        hourlyRateAdminColumn.setCellValueFactory(cellData -> cellData.getValue().hourlyRateObservable());
        
        
    }

    /**
     * Uses the date filter method to find a start date
     *
     * @param event
     */
    @FXML
    private void handleStartDate(ActionEvent event)
    {
        try
        {
            dateFilter();
        } catch (Exception e)
        {

        }
    }

    /**
     * Uses the date filter method to find an end date
     *
     * @param event
     */
    @FXML
    private void handleEndDate(ActionEvent event)
    {
        try
        {
            dateFilter();
        } catch (Exception e)
        {

        }
    }

    /**
     * Handles the start / stop time function and changes the button icon /
     * label depending on which action is to be performed. Also gets the time
     * difference between when you press start and stop and calculates it into
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
        Task selectedTask = opgaveComboBox.getSelectionModel().getSelectedItem();
        Project selectedProject = projektComboBox.getSelectionModel().getSelectedItem();
        long gammelBrugtTid = selectedTask.getBrugtTid();
        long gammelProjektTid = selectedProject.getBrugtTid();

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
            long variableNumber = input / 60;

            if (variableNumber == 0)
            {
                variableNumber = 1;
            }
            brugtTidField.setText(hours + ":" + minutes);
            model.addTime(variableNumber, opgaveComboBox.getSelectionModel().getSelectedItem().getOpgaveNavn());

            for (int i = 0; i < opgaverTableView.getItems().size(); i++)
            {
                if (selectedTask.getId() == opgaverTableView.getItems().get(i).getId())
                {
                    opgaverTableView.getItems().get(i).setBrugtTid(gammelBrugtTid + variableNumber);
                }

            }

            for (int i = 0; i < projekterTableView.getItems().size(); i++)
            {
                if (selectedTask.getProjektId() == projekterTableView.getItems().get(i).getId())
                {
                    projekterTableView.getItems().get(i).setBrugtTid(gammelProjektTid + variableNumber);
                    model.updateProjectTime(selectedProject);
                }
            }

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
    private void setProjectData(ActionEvent event) throws ModelException
    {
        projectData();
    }

    private void projectData() throws ModelException
    {
        Project selectedProject = projektComboBox.getSelectionModel().getSelectedItem();

        opgaveComboBox.getItems().clear();

        if (projektComboBox.getSelectionModel().getSelectedItem() != null)
        {
            kundeField.setText(selectedProject.getKundeNavn());
            opgaveComboBox.setDisable(false);
            titelField.setDisable(false);
            beskrivelseTextArea.setDisable(false);
            betaltCheckBox.setDisable(false);
            nyOpgaveButton.setDisable(false);
            
            for (Task tasks : filteredTaskList)
            {
                if (tasks.getProjektId() == selectedProject.getId())
                {
                    opgaveComboBox.getItems().add(tasks);
                }
            }
        } else
        {
            opgaveComboBox.setDisable(true);
            titelField.setDisable(true);
            beskrivelseTextArea.setDisable(true);
            betaltCheckBox.setDisable(true);
            nyOpgaveButton.setDisable(true);
            
        }

    }

    @FXML
    private void setOpgaveData(ActionEvent event) throws ModelException
    {
        opgaveData();
        if (titelField.getText() != null && beskrivelseTextArea.getText() != null)
        {
            btn_start.setDisable(false);
        }
    }

    private void opgaveData() throws ModelException
    {
        Task selectedTask = opgaveComboBox.getSelectionModel().getSelectedItem();

        if (opgaveComboBox.getSelectionModel().getSelectedItem() != null)
        {
            titelField.setText(selectedTask.getOpgaveNavn());

//            long hours = (result.get(0).getBrugtTid() - result.get(0).getBrugtTid() % 3600) / 3600;
//            long minutes = (result.get(0).getBrugtTid() % 3600 - result.get(0).getBrugtTid() % 3600 % 60) / 60;
//            long seconds = result.get(0).getBrugtTid() % 3600 % 60;
//            NumberFormat f = new DecimalFormat("00");
            beskrivelseTextArea.setText(selectedTask.getBeskrivelse());

            if (selectedTask.getBetalt() == 1)
            {
                betaltCheckBox.setSelected(true);
            }
        } else
        {
            titelField.clear();
            beskrivelseTextArea.clear();
            betaltCheckBox.setSelected(false);

        }
    }

    /**
     * Creates a new user and updates the list of users / admins dynamically. If
     * the admin checkbox is selected, the user will become an admin.
     *
     * @param event
     * @throws ModelException
     */
    @FXML
    private void handleCreateUser(ActionEvent event) throws ModelException
    {
        if (opretAdminCheckBox.isSelected())
        {
            String adminLogin = txt_userLogin.getText();
            String adminPassword = encryptThisString(txt_userPassword.getText());
            model.createUserAdmin(adminLogin, adminPassword, 1);
        } else
        {
            String userLogin = txt_userLogin.getText();
            String userPassword = encryptThisString(txt_userPassword.getText());
            model.createUser(userLogin, userPassword, 0);
        }
    }

    @FXML
    private void createOpgave(ActionEvent event) throws ModelException
    {
        int projektId = projektComboBox.getSelectionModel().getSelectedItem().getId();
        Task selectedTask = null;

        if (betaltCheckBox.isSelected() == true)
        {
            selectedTask = model.createTask(titelField.getText(), projektId, 0, LocalDate.now().toString(), beskrivelseTextArea.getText(), 1, projektComboBox.getSelectionModel().getSelectedItem().getProjektNavn(), 1, this.selectedUser.getId());
        } else
        {
            selectedTask = model.createTask(titelField.getText(), projektId, 0, LocalDate.now().toString(), beskrivelseTextArea.getText(), 0, projektComboBox.getSelectionModel().getSelectedItem().getProjektNavn(), 1, this.selectedUser.getId());
        }

        opgaveComboBox.getItems().add(selectedTask);
        opgaveComboBox.getSelectionModel().select(selectedTask);
        filteredTaskList.add(selectedTask);

//        opgaveComboBox.getSelectionModel().select(titelField.getText());
    }

    @FXML
    private void handleCreateProjekt(ActionEvent event) throws ModelException
    {
        Kunde selectedClient = clientComboBox.getSelectionModel().getSelectedItem();
        Project selectedProject = null;
        Double doubleHourlyRate = Double.parseDouble(txt_HourlyRateProject.getText());
        

        if (!txt_projektNavn.getText().isEmpty() || selectedClient != null || !txt_HourlyRateProject.getText().isEmpty())
        {
            selectedProject = model.createProjekt(txt_projektNavn.getText(), model.getKundeId(selectedClient.getKundeNavn()), LocalDate.now().toString(), 0, 1, selectedClient.getKundeNavn(), doubleHourlyRate);
            projektComboBox.getItems().add(selectedProject);
        } else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Could not create project. Please fill out both Project name and select a client.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleUpdateTime(ActionEvent event) throws ModelException
    {
        opgaverTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Task selectedTask = opgaverTableView.getSelectionModel().getSelectedItem();
        Project selectedProject = null;
        long gammelBrugtTid = selectedTask.getBrugtTid();
        int nyBrugtTid = Integer.parseInt(txt_nyBrugtTid.getText());

//        String projektNavn = selectedTask.getProjektNavn();
        try
        {

            selectedTask.setBrugtTid(nyBrugtTid);
            model.updateTask(selectedTask);

            for (int i = 0; i < projekterTableView.getItems().size(); i++)
            {
                if (selectedTask.getProjektId() == projekterTableView.getItems().get(i).getId())
                {
                    selectedProject = projekterTableView.getItems().get(i);
                    model.updateProjectTime(selectedProject);
                }

            }

        } catch (ModelException ex)
        {
            System.out.println("yikes");
        }

    }

    @FXML
    private void deleteUser(ActionEvent event) throws DalException, ModelException
    {
        userView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        User selectedUser = userView.getSelectionModel().getSelectedItem();
        model.deleteUser(selectedUser);
    }

    private void dateFilter()
    {
        try
        {
            List<Task> taskNames = model.getAllTasksProjektNavn();
            ObservableList<Task> result = FXCollections.observableArrayList();
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate.getValue().toString());

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(start);
            calendar2.add(Calendar.DATE, 1);
            Date sDate = calendar2.getTime();

            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate.getValue().toString());
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(end);
            calendar3.add(Calendar.DATE, 1);
            Date eDate = calendar3.getTime();

            for (Task tasks : taskNames)
            {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(tasks.getDato());

                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(date1);
                calendar1.add(Calendar.DATE, 1);
                Date x = calendar1.getTime();
                if (x.after(sDate) && x.before(eDate) && tasks.getUserId() == this.selectedUser.getId() || x.equals(sDate) || x.equals(eDate))
                {
                    result.add(tasks);
                }
            }

            opgaverTableView.setItems(result);
        } catch (ParseException ex)
        {
            Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ModelException ex)
        {
            Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void taskClearFilter(ActionEvent event) throws ModelException
    {
        opgaverTableView.setItems(filteredTaskList);
        startDate.setValue(null);
        endDate.setValue(null);
    }

    @FXML
    private void updateUserRole(ActionEvent event) throws ModelException
    {
        userView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        User selectedUser = userView.getSelectionModel().getSelectedItem();

        if (userComboBox.getSelectionModel().getSelectedItem().equals("Admin"))
        {
            selectedUser.setAdminRights(userComboBox.getSelectionModel().getSelectedItem());
            model.updateUserRoles(selectedUser);
        } else if (userComboBox.getSelectionModel().getSelectedItem().equals("User"))
        {
            selectedUser.setAdminRights(userComboBox.getSelectionModel().getSelectedItem());
            model.updateUserRoles(selectedUser);
        }
    }

    @FXML
    private void setOngoing(ActionEvent event) throws ModelException
    {

        setProjectTable();

    }

    @FXML
    private void handleArchiveProject(ActionEvent event) throws ModelException
    {
        projekterTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Project selectedProject = projekterTableView.getSelectionModel().getSelectedItem();
        selectedProject.setOngoing(0);
        model.archiveProject(selectedProject);
        allProjectsFilteredList.remove(selectedProject);
        projekterTableView.setItems(allProjectsFilteredList);
//        setProjectTable();
    }

    @FXML
    private void handleEditTask(ActionEvent event) throws ModelException
    {

        Task selectedTask = opgaveComboBox.getSelectionModel().getSelectedItem();
        Task selectedTaskTwo = null;

        for (int i = 0; i < opgaverTableView.getItems().size(); i++)
        {

            if (opgaverTableView.getItems().get(i).getId() == selectedTask.getId())
            {
                selectedTaskTwo = opgaverTableView.getItems().get(i);
            }
        }

        if (betaltCheckBox.isSelected() == true)
        {
            int betalt = 1;
            selectedTask.setOpgaveNavn(titelField.getText());
            selectedTask.setBeskrivelse(beskrivelseTextArea.getText());
            selectedTask.setBetalt(betalt);
            selectedTaskTwo.setOpgaveNavn(titelField.getText());
            model.editTask(selectedTask);
            opgaveComboBox.getItems().remove(selectedTask);
            opgaveComboBox.getItems().add(selectedTask);
            opgaveComboBox.setValue(selectedTask);

        } else if (betaltCheckBox.isSelected() == false)
        {
            int betalt = 0;
            selectedTask.setOpgaveNavn(titelField.getText());
            selectedTask.setBeskrivelse(beskrivelseTextArea.getText());
            selectedTask.setBetalt(betalt);
            selectedTaskTwo.setOpgaveNavn(titelField.getText());
            model.editTask(selectedTask);
            opgaveComboBox.getItems().remove(selectedTask);
            opgaveComboBox.getItems().add(selectedTask);
            opgaveComboBox.setValue(selectedTask);
        }

    }

    @FXML
    private void handleCreateClient(ActionEvent event) throws ModelException
    {
        String kundeNavn = txt_Client.getText();
        String contactPerson = txt_Contact.getText();
        String email = txt_Email.getText();
        Double hourlyRate = Double.parseDouble(txt_ClientHourlyRate.getText());

        model.createKunde(kundeNavn, contactPerson, email, hourlyRate);

    }

    private void fillChart() throws ModelException
    {
        Project selectedProject = projektComboBox2.getSelectionModel().getSelectedItem();
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                int number = -1;
                try
                {
                    XYChart.Series set1 = new XYChart.Series<>();
                    barChart.setAnimated(false);
                    Platform.runLater(() -> barChart.getData().addAll(set1));
                    for (Task allTasks : model.getAllTasks())
                    {
                        number = number + 1;
                        set1.getData().add(new BarChart.Data(allTasks.getOpgaveNavn(), allTasks.getBrugtTid()));
                    }

                } catch (ModelException ex)
                {
                    Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        thread.start();
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Project> projectList = model.getAllProjects();
        for (Project project : projectList)
            if (project.getId() == selectedProject.getId())
            {
                
            }
    }
    
    @FXML
    private void handleDeleteProject(ActionEvent event) throws ModelException
    {
        projekterTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Project selectedProject = projekterTableView.getSelectionModel().getSelectedItem();
        model.deleteProject(selectedProject);

        for (int i = 0; i < projektComboBox.getItems().size(); i++)
        {
            if (selectedProject.getId() == projektComboBox.getItems().get(i).getId())
            {
                try
                {
                    projektComboBox.getItems().remove(i);
                    projektComboBox.getSelectionModel().clearSelection();
                    kundeField.clear();
                } catch (NullPointerException ex)
                {

                }
            }
        }
    }

    @FXML
    private void handleDeleteTask(ActionEvent event) throws ModelException
    {
        opgaverTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Task selecetedTask = opgaverTableView.getSelectionModel().getSelectedItem();
        model.deleteTask(selecetedTask);
    }

    @FXML
    private void handleDeleteClient(ActionEvent event) throws ModelException
    {
        clientTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Kunde selectedClient = clientTableView.getSelectionModel().getSelectedItem();
        model.deleteKunde(selectedClient);
        
    }
}
