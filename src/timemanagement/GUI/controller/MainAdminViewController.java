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
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
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
import timemanagement.BE.User;
import timemanagement.DAL.DalException;
import timemanagement.DAL.database.UserDAO;
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
    @FXML
    private TableView<User> userView;
    @FXML
    private TableColumn<User, Integer> userViewId;
    @FXML
    private TableColumn<User, String> userViewEmail;
    @FXML
    private JFXTextField txt_hourlyRate;
    @FXML
    private TableColumn<User, Long> userViewRate;
    @FXML
    private JFXTextField txt_nyBrugtTid;
    private User user;
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

    ObservableList<User> allUsersResultList = FXCollections.observableArrayList();
    @FXML
    private JFXCheckBox ongoingCheckbox;

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

            projekterTableView.setItems(model.getProjectKundeNavn());
            fillColumns();

        } catch (ModelException ex)
        {
            Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    /**
     * Makes the admin buttons visible for admins only
     */
    public void showAdminButtons()
    {
        analyseButton.setVisible(true);
        projekterButton.setVisible(true);
        opretBrugerButton.setVisible(true);
    }

    /**
     * Fills the project combobox with projects
     *
     * @throws ModelException
     */
    private void setProjects() throws ModelException
    {
        for (Project projects : model.getAllProjects())
        {
            projektComboBox.getItems().add(projects.getProjektNavn());
        }
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
        opgaverTableView.setItems(model.getAllTasksProjektNavn());
        opgaveNavnColumn.setCellValueFactory(new PropertyValueFactory<>("opgaveNavn"));
        projektNavnColumn.setCellValueFactory(new PropertyValueFactory<>("projektNavn"));
        brugtTidColumn.setCellValueFactory(new PropertyValueFactory<>("brugtTid"));
        datoColumn.setCellValueFactory(new PropertyValueFactory<>("dato"));

        //Projekter tableview
        
        setProjectTable();

        //User & Admin views
        fillUserAdminViews();
    }

    private void fillUserAdminViews() throws ModelException
    {
        List<User> allUsersList = model.getAllUsers();
        String isAdminString = "";

        for (User users1 : allUsersList)
        {
            if (users1.getIsAdmin() == 0)
            {
                users1.setAdminRights("User");
            } else
            {
                users1.setAdminRights("Admin");
            }
            allUsersResultList.add(users1);
        }
        userView.setItems(allUsersResultList);

        userViewId.setCellValueFactory(new PropertyValueFactory<>("id"));
        userViewEmail.setCellValueFactory(new PropertyValueFactory<>("userLogin"));
        userViewRate.setCellValueFactory(new PropertyValueFactory<>("hourlyRate"));
        userViewRolle.setCellValueFactory(cellData -> cellData.getValue().adminRighsProperty());
    }
    
    private void setProjectTable() throws ModelException
    {
        List<Project> allProjectsList = model.getProjectKundeNavn();
        ObservableList<Project> allProjectsResultList = FXCollections.observableArrayList();
        ObservableList<Project> allProjectsFilteredList = FXCollections.observableArrayList();
        int brugtTidMinutter = 0;
        for (Project project1 : allProjectsList)
        {
            
            project1.setBrugtTidMinutter(project1.getBrugtTid() * 15);
            
            if (project1.getOngoing() == 1)
            {
                allProjectsFilteredList.add(project1);
            } 
            
            allProjectsResultList.add(project1);
            
            
            
        }
        
        if (ongoingCheckbox.isSelected() == true)
        {
           projekterTableView.setItems(allProjectsFilteredList);
        
        }
        
        else
        {
            projekterTableView.setItems(allProjectsResultList);
            
        }
        
        
        projektNavnAdminColumn.setCellValueFactory(new PropertyValueFactory<>("projektNavn"));
        kundeColumn.setCellValueFactory(new PropertyValueFactory<>("kundeNavn"));
        
        brugtTidAdminColumn.setCellValueFactory(cellData -> cellData.getValue().brugtTidMinutter());
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
        dateFilter();
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
            long variableNumber = input / 60 / 15;

            
            if (variableNumber == 0)
            {
                variableNumber = 1;
            }
            brugtTidField.setText(hours + " Hours  " + minutes + " Minutes  " + seconds + " Seconds  ");
            model.addTime(variableNumber, opgaveComboBox.getSelectionModel().getSelectedItem());
            model.addProjectTime(variableNumber, projektComboBox.getSelectionModel().getSelectedItem());
            opgaveData();
//            opgaverTableView.setItems(model.refreshTasks());
//            projekterTableView.setItems(model.refreshProjects());

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
        List<Project> projectNames = model.getProjectKundeNavn();
        List<Project> result = new ArrayList<>();

        for (Project projects : projectNames)
        {
            if (projects.getProjektNavn().equals(projektComboBox.getSelectionModel().getSelectedItem()))
            {

                result.add(projects);
            }
        }

        sagsNrField.setText(result.get(0).getId() + "");
        kundeField.setText(result.get(0).getKundeNavn() + "");
        LocalDate localDate = LocalDate.parse(result.get(0).getStartDato());
        datePicker.setValue(localDate);

        if (projektComboBox.getSelectionModel().getSelectedItem() != null)
        {
            opgaveComboBox.setDisable(false);
            titelField.setDisable(false);
            timeField.setDisable(false);
            beskrivelseTextArea.setDisable(false);
            betaltCheckBox.setDisable(false);
            nyOpgaveButton.setDisable(false);
        } else
        {
            opgaveComboBox.setDisable(true);
            titelField.setDisable(true);
            timeField.setDisable(true);
            beskrivelseTextArea.setDisable(true);
            betaltCheckBox.setDisable(true);
            nyOpgaveButton.setDisable(true);
        }

        opgaveComboBox.getItems().clear();

        for (Task tasks : model.getAllTasksByProject(result.get(0).getId()))
        {
            opgaveComboBox.getItems().add(tasks.getOpgaveNavn());
        }
    }

    @FXML
    private void setOpgaveData(ActionEvent event) throws ModelException
    {
        opgaveData();
        if (titelField.getText() != null && timeField.getText() != null && beskrivelseTextArea.getText() != null)
        {
            btn_start.setDisable(false);
        }
    }

    private void opgaveData() throws ModelException
    {
        List<Task> taskNames = model.getAllTasks();
        List<Task> result = new ArrayList<>();

        for (Task tasks : taskNames)
        {
            if (tasks.getOpgaveNavn().equals(opgaveComboBox.getSelectionModel().getSelectedItem()))
            {

                result.add(tasks);
            }
        }

        if (opgaveComboBox.getSelectionModel().getSelectedItem() != null)
        {
            titelField.setText(result.get(0).getOpgaveNavn());

            long hours = (result.get(0).getBrugtTid() - result.get(0).getBrugtTid() % 3600) / 3600;
            long minutes = (result.get(0).getBrugtTid() % 3600 - result.get(0).getBrugtTid() % 3600 % 60) / 60;
            long seconds = result.get(0).getBrugtTid() % 3600 % 60;
            NumberFormat f = new DecimalFormat("00");
            timeField.setText(f.format(hours) + ":" + f.format(minutes) + ":" + f.format(seconds));
            beskrivelseTextArea.setText(result.get(0).getBeskrivelse());

            if (result.get(0).getBetalt() == 1)
            {
                betaltCheckBox.setSelected(true);
            }
        } else
        {
            titelField.clear();
            timeField.clear();
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
            long hourlyRate = Long.parseLong(txt_hourlyRate.getText());
            model.createUserAdmin(adminLogin, encryptThisString(adminPassword), 1, hourlyRate);
            userView.setItems(model.getAllUsers());
        } else
        {
            String userLogin = txt_userLogin.getText();
            String userPassword = encryptThisString(txt_userPassword.getText());
            long hourlyRate = Long.parseLong(txt_hourlyRate.getText());
            model.createUser(userLogin, encryptThisString(userPassword), 0, hourlyRate);
            userView.setItems(model.getAllUsers());
        }
    }

    @FXML
    private void createOpgave(ActionEvent event) throws ModelException
    {

        int projektId = Integer.parseInt(sagsNrField.getText());

        if (betaltCheckBox.isSelected() == true)
        {
            model.createTask(titelField.getText(), projektId, 0, LocalDate.now().toString(), beskrivelseTextArea.getText(), 1);
        } else
        {
            model.createTask(titelField.getText(), projektId, 0, LocalDate.now().toString(), beskrivelseTextArea.getText(), 0);
        }

        projectData();
        opgaveComboBox.getSelectionModel().select(titelField.getText());
        fillColumns();

    }

    @FXML
    private void handleCreateProjekt(ActionEvent event) throws ModelException
    {
        model.createProjekt(txt_projektNavn.getText(), model.getKundeId(txt_kundeNavn.getText()), LocalDate.now().toString(), 0);
        projekterTableView.setItems(model.refreshProjects());
        setProjects();
    }

    @FXML
    private void handleUpdateTime(ActionEvent event) throws ModelException
    {
        opgaverTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Task selectedTask = opgaverTableView.getSelectionModel().getSelectedItem();
        int brugtTid = Integer.parseInt(txt_nyBrugtTid.getText());
        int id = selectedTask.getId();
        try
        {
            model.updateTask(brugtTid, id);
            opgaverTableView.setItems(model.refreshTasks());
        } catch (ModelException ex)
        {

        }

        List<Project> allProjects = model.getAllProjects();
        List<Project> result = new ArrayList();

        for (Project projects : allProjects)
        {
            if (projects.getId() == selectedTask.getProjektId())
            {
                result.add(projects);
            }
        }

        result.get(0).setBrugtTid(0);

        for (Task tasks : model.getAllTasksByProject(result.get(0).getId()))
        {
            if (tasks.getProjektId() == result.get(0).getId())
            {
                model.addProjectTime(tasks.getBrugtTid(), result.get(0).getProjektNavn());
            }
        }

        projekterTableView.setItems(model.refreshProjects());

    }

    @FXML
    private void deleteUser(ActionEvent event) throws DalException, ModelException
    {
        userView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        User selectedUser = userView.getSelectionModel().getSelectedItem();
        model.deleteUser(selectedUser);
        allUsersResultList.clear();
        fillUserAdminViews();
    }

    private void dateFilter()
    {
        try
        {
            List<Task> taskNames = model.getAllTasks();
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
                if (x.after(sDate) && x.before(eDate) || x.equals(sDate) || x.equals(eDate))
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
        opgaverTableView.setItems(model.getAllTasksProjektNavn());
    }


    @FXML
    private void updateUserRole(ActionEvent event) throws ModelException
    {
        userView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        User selectedUserRole = userView.getSelectionModel().getSelectedItem();

        if (userComboBox.getSelectionModel().getSelectedItem().equals("Admin"))
        {
            model.updateUserRoles(1, selectedUserRole.getId());
            allUsersResultList.clear();
            fillUserAdminViews();
        } else if (userComboBox.getSelectionModel().getSelectedItem().equals("User"))
        {
            model.updateUserRoles(0, selectedUserRole.getId());
            allUsersResultList.clear();
            fillUserAdminViews();
        }
    }
    
    @FXML
    private void setOngoing(ActionEvent event) throws ModelException {

        setProjectTable();

    }

    @FXML
    private void handleArchiveProject(ActionEvent event) throws ModelException {
        projekterTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Project selectedProject = projekterTableView.getSelectionModel().getSelectedItem();
        model.archiveProject(selectedProject.getId(), 0);
        setProjectTable();
    }

}
