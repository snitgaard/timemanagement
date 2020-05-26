/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import timemanagement.BE.*;
import timemanagement.DAL.DalException;
import timemanagement.gui.model.Model;
import timemanagement.gui.model.ModelException;

import java.net.URL;
import java.text.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utilities.encryptThisString.encryptThisString;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class TimeLoggerViewController implements Initializable
{

    ObservableList<User> allUsersResultList = FXCollections.observableArrayList();
    ObservableList<Project> allProjectsFilteredList = FXCollections.observableArrayList();
    ObservableList<Task> filteredTaskList = FXCollections.observableArrayList();
    String alertString = "Generic Warning";
    @FXML
    private TableColumn<Client, String> clientNameColumn;
    @FXML
    private TableColumn<Client, String> clientContactColumn;
    @FXML
    private TableColumn<Client, String> clientEmailColumn;
    @FXML
    private TableColumn<Client, Double> clientHourlyRateColumn;
    @FXML
    private JFXButton timeLoggerButton;
    @FXML
    private JFXButton tasksButton;
    @FXML
    private JFXButton analyseButton;
    @FXML
    private JFXButton projectsButton;
    @FXML
    private StackPane stackPane;
    @FXML
    private SplitPane projectPane;
    @FXML
    private SplitPane analysePane;
    @FXML
    private SplitPane tasksPane;
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
    private JFXComboBox<Project> projectComboBox;
    @FXML
    private JFXComboBox<Task> taskComboBox;
    @FXML
    private JFXTextField startTimeField;
    @FXML
    private JFXTextField endTimeField;
    @FXML
    private JFXTextField usedTimeField;
    @FXML
    private JFXCheckBox paidCheckBox;
    @FXML
    private JFXTextField clientField;
    @FXML
    private JFXTextField titelField;
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private FontAwesomeIconView startIcon;
    private Model model;
    @FXML
    private ImageView btn_close;
    @FXML
    private TableView<Task> tasksTableView;
    @FXML
    private JFXComboBox<Project> projectComboBox2;
    @FXML
    private JFXTextField txt_projectName;
    @FXML
    private JFXButton btn_start;
    @FXML
    private JFXTextField txt_userPassword;
    @FXML
    private JFXTextField txt_userLogin;
    @FXML
    private JFXButton createUserButton;
    @FXML
    private SplitPane createUserPane;
    @FXML
    private TableView<Project> projectsTableView;
    @FXML
    private TableColumn<Project, String> clientColumn;
    @FXML
    private TableColumn<Project, String> projectNameAdminColumn;
    @FXML
    private TableColumn<Project, Long> usedTimeAdminColumn;
    @FXML
    private JFXCheckBox createAdminCheckBox;
    @FXML
    private JFXButton newTaskButton;
    @FXML
    private TableView<User> userView;
    @FXML
    private TableColumn<User, String> userViewEmail;
    @FXML
    private JFXTextField txt_newUsedTime;
    private User selectedUser;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXButton btnTaskClearFilter;
    @FXML
    private TableColumn<User, String> userViewRole;
    @FXML
    private JFXComboBox<String> userComboBox;
    @FXML
    private Label loginTextField;
    private LoginController controller;
    private String username;
    @FXML
    private JFXButton clientButton;
    @FXML
    private TableView<Client> clientTableView;
    @FXML
    private JFXTextField txt_Contact;
    @FXML
    private JFXTextField txt_Email;
    @FXML
    private JFXTextField txt_Client;
    @FXML
    private SplitPane clientPane;
    private JFXTextField txt_HourlyRate;
    @FXML
    private JFXTextField txt_ClientHourlyRate;
    @FXML
    private JFXComboBox<Client> clientComboBox;
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
    @FXML
    private JFXButton editButton;

    private boolean buttonState = true;
    @FXML
    private JFXTextField costPrice;
    @FXML
    private TableColumn<User, String> userViewFullName;
    @FXML
    private JFXTextField txt_userEmail;
    @FXML
    private JFXTextField txt_userFullName;
    @FXML
    private TableColumn<User, String> userViewUsername;
    @FXML
    private JFXCheckBox quartersCheckBox;
    @FXML
    private JFXDatePicker chartStartDate;
    @FXML
    private JFXDatePicker chartEndDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            calculateCostPrice();
            timeLoggerPane.toFront();

            model = model.getInstance();
            setProjects();

            ObservableList<String> roles = FXCollections.observableArrayList("Admin", "User");
            userComboBox.setItems(roles);


            fillChart();

        } catch (ModelException ex)
        {
            Logger.getLogger(TimeLoggerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Transfers the selectedUser from one controller to the other
     * Sets the text for loginTextField and fills all the tableviews.
     * @param selectedUser
     * @throws ModelException 
     */
    public void applyImportantData(User selectedUser) throws ModelException
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
        List<JFXButton> dashBoardButtons = new ArrayList<>();
        dashBoardButtons.add(timeLoggerButton);
        dashBoardButtons.add(tasksButton);
        dashBoardButtons.add(analyseButton);
        dashBoardButtons.add(projectsButton);
        dashBoardButtons.add(createUserButton);
        dashBoardButtons.add(clientButton);

        for (JFXButton dashBoardButton : dashBoardButtons)
        {
            dashBoardButton.getStyleClass().removeAll("pane-buttonSelected");
            dashBoardButton.getStyleClass().add("pane-button");
        }

        if (actionEvent.getSource() == timeLoggerButton)
        {
            timeLoggerPane.toFront();
            timeLoggerButton.getStyleClass().removeAll("pane-button");
            timeLoggerButton.getStyleClass().add("pane-buttonSelected");
        }
        if (actionEvent.getSource() == tasksButton)
        {
            tasksPane.toFront();
            tasksButton.getStyleClass().removeAll("pane-button");
            tasksButton.getStyleClass().add("pane-buttonSelected");
        }
        if (actionEvent.getSource() == analyseButton)
        {
            analysePane.toFront();
            analyseButton.getStyleClass().removeAll("pane-button");
            analyseButton.getStyleClass().add("pane-buttonSelected");
        }
        if (actionEvent.getSource() == projectsButton)
        {
            projectPane.toFront();
            projectsButton.getStyleClass().removeAll("pane-button");
            projectsButton.getStyleClass().add("pane-buttonSelected");
        }
        if (actionEvent.getSource() == createUserButton)
        {
            createUserPane.toFront();
            createUserButton.getStyleClass().removeAll("pane-button");
            createUserButton.getStyleClass().add("pane-buttonSelected");
        }
        if (actionEvent.getSource() == clientButton)
        {
            clientPane.toFront();
            clientButton.getStyleClass().removeAll("pane-button");
            clientButton.getStyleClass().add("pane-buttonSelected");
        }
    }

    /**
     * Makes the admin buttons visible for admins only
     */
    public void showAdminButtons()
    {
        analyseButton.setVisible(true);
        projectsButton.setVisible(true);
        createUserButton.setVisible(true);
        clientButton.setVisible(true);
    }

    /**
     * Fills the project combobox with projects
     *
     * @throws ModelException
     */
    private void setProjects() throws ModelException
    {
        projectComboBox.setItems(model.getProjectClientName());
        projectComboBox2.setItems(model.getAllProjects());
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
        List<Task> taskList = model.getAllTasksProjectName();

        for (Task task : taskList)
        {
            if (task.getUserId() == selectedUser.getId())
            {
                filteredTaskList.add(task);
            }
        }

        tasksTableView.setItems(filteredTaskList);

        opgaveNavnColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
        projektNavnColumn.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        brugtTidColumn.setCellValueFactory(cellData -> cellData.getValue().usedTimeObservableValue());
        datoColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        //Projekter tableview
        setProjectTable();

        //User & Admin views
        fillUserAdminViews();

        //Client view
        fillClientView();
    }

    /**
     * Fills the columns in the ClientView with proper data. Seperates the
     * columns and the data within the columns.
     *
     * @throws ModelException
     */
    private void fillClientView() throws ModelException
    {
        clientTableView.setItems(model.getAllClients());
        clientNameColumn.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        clientContactColumn.setCellValueFactory(cellData -> cellData.getValue().contactPersonProperty());
        clientEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        clientHourlyRateColumn.setCellValueFactory(cellData -> cellData.getValue().hourlyRateObservable());
        clientHourlyRateColumn.setCellFactory(tc -> new TableCell<Client, Double>()
        {
            @Override
            protected void updateItem(Double hourlyRate, boolean empty)
            {
                super.updateItem(hourlyRate, empty);
                if (empty)
                {
                    setText(null);
                } else
                {
                    setText(String.format("%.2f", hourlyRate.doubleValue()));
                }
            }
        });
    }

    /**
     * Fills the columns in the UserAdminView with proper data. Seperates the
     * columns and the data within the columns.
     *
     * @throws ModelException
     */
    private void fillUserAdminViews() throws ModelException
    {
        userView.setItems(model.getAllUsers());

        userViewUsername.setCellValueFactory(cellData -> cellData.getValue().userLoginProperty());
        userViewRole.setCellValueFactory(cellData -> cellData.getValue().adminRightsProperty());
        userViewEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        userViewFullName.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
    }

    /**
     * Fills the columns in the Projectview with proper data. Seperates the
     * columns and the data within the columns.
     *
     * @throws ModelException
     */
    private void setProjectTable() throws ModelException
    {
        if (clientComboBox.getSelectionModel().getSelectedItem() == null)
        {
            clientComboBox.setItems(model.getAllClients());
        }

        projectsTableView.setItems(model.getProjectClientName());

        projectNameAdminColumn.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        usedTimeAdminColumn.setCellValueFactory(cellData -> cellData.getValue().usedTimeObservable());
        hourlyRateAdminColumn.setCellValueFactory(cellData -> cellData.getValue().hourlyRateObservable());
        hourlyRateAdminColumn.setCellFactory(tc -> new TableCell<Project, Double>()
        {
            @Override
            protected void updateItem(Double hourlyRate, boolean empty)
            {
                super.updateItem(hourlyRate, empty);
                if (empty)
                {
                    setText(null);
                } else
                {
                    setText(String.format("%.2f", hourlyRate.doubleValue()));
                }
            }
        });

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
        if (taskComboBox.getSelectionModel().getSelectedItem() == null)
        {
            alertString = "Can not start time if a task has not been selected. Please select a task and try again.";
            showAlert();
        } else
        {
            if (startIcon.getGlyphName().equals("PAUSE"))
            {
                startIcon.setIcon(FontAwesomeIcon.PLAY);
                btn_start.setText("Start time");
                taskComboBox.setDisable(false);
                projectComboBox.setDisable(false);
                editButton.setDisable(false);
                newTaskButton.setDisable(false);
                paidCheckBox.setDisable(false);
                stopTidMethod();
            } else if (startIcon.getGlyphName().equals("PLAY"))
            {
                startIcon.setIcon(FontAwesomeIcon.PAUSE);
                btn_start.setText("Stop time");
                taskComboBox.setDisable(true);
                projectComboBox.setDisable(true);
                editButton.setDisable(true);
                newTaskButton.setDisable(true);
                paidCheckBox.setDisable(true);
                try
                {
                    java.util.Date date = new java.util.Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    startTimeField.setText(sdf.format(date));
                    endTimeField.clear();
                    usedTimeField.clear();
                } catch (Exception e)
                {
                    alertString = "Could not format time difference.";
                    showAlert();
                }
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
            Task selectedTask = taskComboBox.getSelectionModel().getSelectedItem();
            Project selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
            long oldUsedTime = selectedTask.getUsedTime();
            long oldProjectTime = selectedProject.getUsedTime();

            java.util.Date date = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            endTimeField.setText(sdf.format(date));

            usedTimeField.setText(model.timeFormatter(startTimeField.getText(), endTimeField.getText()));

            if (selectedProject.getRounded() == 0)
            {
                model.addTime(model.timeCalculator(startTimeField.getText(), endTimeField.getText()), taskComboBox.getSelectionModel().getSelectedItem().getId());
            } else
            {
                model.addRoundedTime(model.timeCalculator(startTimeField.getText(), endTimeField.getText()), taskComboBox.getSelectionModel().getSelectedItem().getId());
            }

            for (int i = 0; i < tasksTableView.getItems().size(); i++)
            {
                if (selectedTask.getId() == tasksTableView.getItems().get(i).getId())
                {
                    if (selectedProject.getRounded() == 0)
                    {
                        tasksTableView.getItems().get(i).setUsedTime(oldUsedTime + model.timeCalculator(startTimeField.getText(), endTimeField.getText()));
                    } else
                    {
                        double time = model.timeCalculator(startTimeField.getText(), endTimeField.getText());
                        Double roundedTime = Math.ceil(time / 15) * 15;
                        long realRoundedTime = roundedTime.longValue();

                        tasksTableView.getItems().get(i).setUsedTime(oldUsedTime + realRoundedTime);

                    }

                }
            }

            for (int i = 0; i < projectsTableView.getItems().size(); i++)
            {
                if (selectedTask.getProjectId() == projectsTableView.getItems().get(i).getId())
                {
                    projectsTableView.getItems().get(i).setUsedTime(oldProjectTime + model.timeCalculator(startTimeField.getText(), endTimeField.getText()));
                    model.updateProjectTime(selectedProject);
                }
            }

        } catch (ModelException e)
        {
            alertString = "Could not calculate the time used on task.";
            showAlert();
        } catch (NullPointerException e)
        {
            alertString = "Can not stop time if a task has not been selected. Please try again.";
            showAlert();
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
     * Calls the projectData method as an action event.
     *
     * @throws ModelException
     */
    @FXML
    private void setProjectData(ActionEvent event) throws ModelException
    {
        projectData();
    }

    /**
     * Displays the customer data from the selected project and enable/disables
     * buttons, textfield and checkbox depending on the true/false outcome.
     *
     * @throws ModelException
     */
    private void projectData() throws ModelException
    {
        Project selectedProject = projectComboBox.getSelectionModel().getSelectedItem();

        taskComboBox.getItems().clear();

        if (projectComboBox.getSelectionModel().getSelectedItem() != null)
        {
            clientField.setText(selectedProject.getClientName());
            buttonState = false;
            disableTimeLoggerButtons();

            for (Task tasks : filteredTaskList)
            {
                if (tasks.getProjectId() == selectedProject.getId())
                {
                    taskComboBox.getItems().add(tasks);
                }
            }
        }
    }

    /**
     * Enables/Disables the buttons and fields in the time logger view
     */
    private void disableTimeLoggerButtons()
    {
        taskComboBox.setDisable(buttonState);
        titelField.setDisable(buttonState);
        descriptionTextArea.setDisable(buttonState);
        paidCheckBox.setDisable(buttonState);
        newTaskButton.setDisable(buttonState);
        btn_start.setDisable(buttonState);
        editButton.setDisable(buttonState);
        startTimeField.setDisable(buttonState);
        endTimeField.setDisable(buttonState);
        usedTimeField.setDisable(buttonState);
    }

    /**
     * Calls the opgaveData method as an action event, and disables start button
     * if titlefield and beskrivelsetextarea is not empty.
     *
     * @throws ModelException
     */
    private void setOpgaveData(ActionEvent event) throws ModelException
    {
        opgaveData();
        Task selectedTask = taskComboBox.getSelectionModel().getSelectedItem();
        if (titelField.getText() != null && descriptionTextArea.getText() != null)
        {
            buttonState = false;
            disableTimeLoggerButtons();
        } else if (titelField.getText() == null && descriptionTextArea.getText() == null && selectedTask == null)
        {
            buttonState = true;
            disableTimeLoggerButtons();
        }
    }

    /**
     * If there is a selected item in the combobox, sets the textfield to the
     * name of the task and description. And check if the checkbox is checked.
     * Else clear the title and description.
     *
     * @throws ModelException
     */
    private void opgaveData() throws ModelException
    {
        Task selectedTask = taskComboBox.getSelectionModel().getSelectedItem();

        if (taskComboBox.getSelectionModel().getSelectedItem() != null)
        {
            titelField.setText(selectedTask.getTaskName());

            descriptionTextArea.setText(selectedTask.getDescription());

            if (selectedTask.getPaid() == 1)
            {
                paidCheckBox.setSelected(true);
            }
        } else
        {
            titelField.clear();
            descriptionTextArea.clear();
            paidCheckBox.setSelected(false);
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
        try
        {
            for (int i = 0; i < userView.getItems().size(); i++)
            {
                if (userView.getItems().get(i).toString().equalsIgnoreCase(txt_userLogin.getText()))
                {
                    alertString = "Could not create user, user already exists.";
                    showAlert();
                    return;
                }
            }
            String userLogin = txt_userLogin.getText();
            String userPassword = encryptThisString(txt_userPassword.getText());
            String email = txt_userEmail.getText();
            String fullName = txt_userFullName.getText();
            if (createAdminCheckBox.isSelected() && !userLogin.isEmpty() && !txt_userPassword.getText().isEmpty() && !email.isEmpty() && !fullName.isEmpty())
            {
                model.createUser(userLogin, userPassword, 1, email, fullName);
            } else if (!createAdminCheckBox.isSelected() && !txt_userLogin.getText().isEmpty() && !txt_userPassword.getText().isEmpty() && !email.isEmpty() && !fullName.isEmpty())
            {
                model.createUser(userLogin, userPassword, 0, email, fullName);
            } else if (txt_userLogin.getText().isEmpty() || txt_userPassword.getText().isEmpty() || email.isEmpty() || fullName.isEmpty())
            {
                alertString = "Could not create user or admin. Please try again";
                showAlert();
            }
        } catch (Exception e)
        {
            alertString = "Could not create user or admin. Please try again";
            showAlert();
        }
    }

    /**
     * Creates a task, making it a paid or unpaid task depending if the checkbox
     * is checked or not.
     *
     * @throws ModelException
     */
    private void createOpgave(ActionEvent event) throws ModelException
    {
        int projectId = projectComboBox.getSelectionModel().getSelectedItem().getId();
        Task selectedTask = null;

        if (paidCheckBox.isSelected() == true && !titelField.getText().isEmpty() && !descriptionTextArea.getText().isEmpty())
        {
            selectedTask = model.createTask(titelField.getText(), projectId, 0, LocalDate.now().toString(), descriptionTextArea.getText(),
                    1, projectComboBox.getSelectionModel().getSelectedItem().getProjectName(), 0, this.selectedUser.getId());

            taskComboBox.getItems().add(selectedTask);
            taskComboBox.getSelectionModel().select(selectedTask);
            filteredTaskList.add(selectedTask);
        } else if (paidCheckBox.isSelected() == false && !titelField.getText().isEmpty() && !descriptionTextArea.getText().isEmpty())
        {
            selectedTask = model.createTask(titelField.getText(), projectId, 0, LocalDate.now().toString(), descriptionTextArea.getText(), 0,
                    projectComboBox.getSelectionModel().getSelectedItem().getProjectName(), 0, this.selectedUser.getId());

            taskComboBox.getItems().add(selectedTask);
            taskComboBox.getSelectionModel().select(selectedTask);
            filteredTaskList.add(selectedTask);
        } else
        {
            alertString = "Could not create task. Plesae try again.";
            showAlert();
        }

    }

    /**
     * Creates a project with all the inputted information
     *
     * @throws ModelException
     */
    private void handleCreateProjekt(ActionEvent event) throws ModelException
    {
        Client selectedClient = clientComboBox.getSelectionModel().getSelectedItem();
        Project selectedProject = null;
        double doubleHourlyRate = 0;

        try
        {
            if (!txt_projectName.getText().isEmpty() && selectedClient != null)
            {
                if (txt_HourlyRateProject.getText().isEmpty())
                {
                    doubleHourlyRate = selectedClient.getHourlyRate();

                } else
                {
                    doubleHourlyRate = Double.parseDouble(txt_HourlyRateProject.getText());
                }

                if (quartersCheckBox.isSelected())
                {
                    selectedProject = model.createProject(txt_projectName.getText(), model.getClientId(selectedClient.getClientName()), LocalDate.now().toString(), 0, 1, selectedClient.getClientName(), doubleHourlyRate, 1);
                } else
                {
                    selectedProject = model.createProject(txt_projectName.getText(), model.getClientId(selectedClient.getClientName()), LocalDate.now().toString(), 0, 1, selectedClient.getClientName(), doubleHourlyRate, 0);
                }

                projectComboBox.getItems().add(selectedProject);
                projectComboBox2.getItems().add(selectedProject);
                allProjectsFilteredList.add(selectedProject);
            } else if (txt_projectName.getText().isEmpty() || selectedClient == null)
            {
                alertString = "Could not create project. Please try again";
                showAlert();
            }
        } catch (NumberFormatException e)
        {
            alertString = "Could not create project as a non-numeric number was detected. Please try again.";
            showAlert();
        }
    }

    /**
     * Updates / replaces the time used of the selected task.
     *
     * @throws ModelException
     */
    @FXML
    private void handleUpdateTime(ActionEvent event) throws ModelException
    {
        tasksTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Task selectedTask = tasksTableView.getSelectionModel().getSelectedItem();
        Project selectedProject = null;

        try
        {
            if (selectedTask != null && !txt_newUsedTime.getText().isEmpty())
            {
                int nyBrugtTid = Integer.parseInt(txt_newUsedTime.getText());
                selectedTask.setUsedTime(nyBrugtTid);
                model.updateTask(selectedTask);
                for (int i = 0; i < projectsTableView.getItems().size(); i++)
                {
                    if (selectedTask.getProjectId() == projectsTableView.getItems().get(i).getId())
                    {
                        selectedProject = projectsTableView.getItems().get(i);
                        model.updateProjectTime(selectedProject);
                    }
                }
            } else if (selectedTask == null && txt_newUsedTime.getText().isEmpty())
            {
                alertString = "Could not update time. Please try again.";
                showAlert();
            }
        } catch (NumberFormatException e)
        {
            alertString = "Could not udpate time as a non-numeric input was detected. Please try again.";
            showAlert();
        }
    }

    /**
     * Deletes the user by changing the int valvue in the DB
     *
     * @throws ModelException DalException
     */
    @FXML
    private void deleteUser(ActionEvent event) throws DalException, ModelException
    {
        userView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        User selectedUser = userView.getSelectionModel().getSelectedItem();

        if (selectedUser != null)
        {
            model.deleteUser(selectedUser);
        } else
        {
            alertString = "Could not delete user. Please try again.";
            showAlert();
        }
    }

    /**
     * Sorting the task list, based on the selected dates.
     *
     * @throws ModelException DalException
     */
    private void dateFilter()
    {
        try
        {
            List<Task> taskNames = model.getAllTasksProjectName();
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
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(tasks.getDate());

                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(date1);
                calendar1.add(Calendar.DATE, 1);
                Date x = calendar1.getTime();
                if (x.after(sDate) && x.before(eDate) && tasks.getUserId() == this.selectedUser.getId() || x.equals(sDate) || x.equals(eDate))
                {
                    result.add(tasks);
                }
            }

            tasksTableView.setItems(result);
        } catch (ParseException ex)
        {
            alertString = "Could not parse to date format. Please try again.";
            showAlert();

        } catch (ModelException ex)
        {
            alertString = "Could not get all tasks from database. Please try again.";
            showAlert();
        }
    }

    /**
     * Clears the task by re-setting the list task list.
     *
     * @throws ModelException DalException
     */
    @FXML
    private void taskClearFilter(ActionEvent event) throws ModelException
    {
        if (startDate.getValue() != null && endDate.getValue() != null)
        {
            tasksTableView.setItems(filteredTaskList);
            startDate.setValue(null);
            endDate.setValue(null);
        } else
        {
            alertString = "Could not clear filter. Please try again.";
            showAlert();
        }
    }

    /**
     * Update the selected user role, from the selcted role in combobox.
     *
     * @throws ModelException DalException
     */
    @FXML
    private void updateUserRole(ActionEvent event) throws ModelException
    {
        userView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        User selectedUserView = userView.getSelectionModel().getSelectedItem();
        String selectedUserCombobox = userComboBox.getSelectionModel().getSelectedItem();

        if (selectedUserView != null && selectedUserCombobox == "Admin")
        {
            selectedUserView.setAdminRights(selectedUserCombobox);
            model.updateUserRoles(selectedUserView, 1);
        } else if (selectedUserView != null && selectedUserCombobox == "User")
        {
            selectedUserView.setAdminRights(selectedUserCombobox);
            model.updateUserRoles(selectedUserView, 0);
        } else
        {
            alertString = "Could not update user role. Please try again.";
            showAlert();
        }
    }

    /**
     * Edits and saves the changes to the selectd task.
     *
     * @throws ModelException DalException
     */
    @FXML
    private void handleEditTask(ActionEvent event) throws ModelException
    {

        Task selectedTask = taskComboBox.getSelectionModel().getSelectedItem();
        Task selectedTaskTwo = null;

        if (titelField.getText().isEmpty() && descriptionTextArea.getText().isEmpty())
        {
            alertString = "Could not edit task. Please try again.";
            showAlert();
        }

        for (int i = 0; i < tasksTableView.getItems().size(); i++)
        {

            if (tasksTableView.getItems().get(i).getId() == selectedTask.getId())
            {
                selectedTaskTwo = tasksTableView.getItems().get(i);
            }
        }

        if (paidCheckBox.isSelected() == true)
        {
            int paid = 1;
            selectedTask.setTaskName(titelField.getText());
            selectedTask.setDescription(descriptionTextArea.getText());
            selectedTask.setPaid(paid);
            selectedTaskTwo.setTaskName(titelField.getText());
            model.editTask(selectedTask);
            taskComboBox.getItems().remove(selectedTask);
            taskComboBox.getItems().add(selectedTask);
            taskComboBox.setValue(selectedTask);

        } else if (paidCheckBox.isSelected() == false)
        {
            int paid = 0;
            selectedTask.setTaskName(titelField.getText());
            selectedTask.setDescription(descriptionTextArea.getText());
            selectedTask.setPaid(paid);
            selectedTaskTwo.setTaskName(titelField.getText());
            model.editTask(selectedTask);
            taskComboBox.getItems().remove(selectedTask);
            taskComboBox.getItems().add(selectedTask);
            taskComboBox.setValue(selectedTask);
        }

    }

    /**
     * Checks the textfields, if they are not empty, takes the indput and
     * creates a client based on the indput.
     *
     * @throws ModelException DalException
     */
    @FXML
    private void handleCreateClient(ActionEvent event) throws ModelException
    {
        try
        {
            Client selectedKunde = null;
            if (!txt_Client.getText().isEmpty() && !txt_Contact.getText().isEmpty() && !txt_Contact.getText().isEmpty() && !txt_ClientHourlyRate.getText().isEmpty())
            {
                {
                    String kundeNavn = txt_Client.getText();
                    String contactPerson = txt_Contact.getText();
                    String email = txt_Email.getText();
                    Double hourlyRate = Double.parseDouble(txt_ClientHourlyRate.getText());
                    selectedKunde = model.createClient(kundeNavn, contactPerson, email, hourlyRate, 0);
                    clientComboBox.getItems().add(selectedKunde);

                }
            } else
            {
                alertString = "Could not creat client. Please try again.";
                showAlert();
            }
        } catch (NumberFormatException e)
        {
            alertString = "Could not create client as a non-numeric number was detected. Please try again.";
            showAlert();
        }
    }

    /**
     * Create data chart series, and adds them to the charts. While running the
     * method on a seperate thred.
     *
     * @throws ModelException DalException
     */
    private void fillChart() throws ModelException
    {
        barChart.setAnimated(false);
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    XYChart.Series set1 = new XYChart.Series<>();
                    XYChart.Series set2 = new XYChart.Series<>();
                    barChart.setAnimated(false);
                    barChart.setBarGap(-10);
                    set1.setName("Paid task");
                    set2.setName("Not paid task");
                    Platform.runLater(() -> barChart.getData().addAll(set1, set2));
                    for (Task allTasks : model.getAllTasks())
                    {
                        if (allTasks.getPaid() == 1)
                        {
                            set1.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        } else if (allTasks.getPaid() == 0)
                        {
                            set2.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        }
                    }
                } catch (ModelException ex)
                {
                    alertString = "Could not fill charts on startup. Please try again.";
                    showAlert();
                }
            }

        });
        thread.start();
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException ex)
        {
            alertString = "Could not fill charts on startup. Please try again.";
            showAlert();
        }
    }

    /**
     * Deletes a project from the view, and archives it in the database.
     * Archives every task belonging to the selected project.
     * Removes the project as well as the tasks from the comboboxes. 
     * @throws ModelException DalException
     */
    @FXML
    private void handleDeleteProject(ActionEvent event) throws ModelException
    {
        projectsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Project selectedProject = projectsTableView.getSelectionModel().getSelectedItem();
        List<Task> toBeDeleted = new ArrayList();
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                if (selectedProject != null)
                {
                    try
                    {
                        model.deleteProject(selectedProject, 1);

                        for (Task task : filteredTaskList)
                        {
                            if (task.getProjectId() == selectedProject.getId())
                            {
                                toBeDeleted.add(task);
                            }
                            model.deleteTaskOnProject(task, 1, selectedProject.getId());
                        }

                        for (Task task : toBeDeleted)
                        {
                            for (ListIterator<Task> iterator = filteredTaskList.listIterator(); iterator.hasNext(); )
                            {
                                Task task1 = iterator.next();
                                if (task.getId() == task1.getId())
                                {
                                    iterator.remove();
                                }
                            }
                        }

                        for (int i = 0; i < projectComboBox.getItems().size(); i++)
                        {
                            if (selectedProject.getId() == projectComboBox.getItems().get(i).getId())
                            {
                                projectComboBox.getItems().remove(i);
                                projectComboBox.getSelectionModel().clearSelection();
                                clientField.clear();
                            }
                        }

                        for (int i = 0; i < projectComboBox2.getItems().size(); i++)
                        {
                            if (selectedProject.getId() == projectComboBox2.getItems().get(i).getId())
                            {
                                projectComboBox2.getItems().remove(i);
                                projectComboBox2.getSelectionModel().clearSelection();
                            }
                        }

                        allProjectsFilteredList.remove(selectedProject);
                        taskComboBox.getSelectionModel().clearSelection();

                    } catch (ModelException ex)
                    {
                        Logger.getLogger(TimeLoggerViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else
                {
                    alertString = "Could not delete project. Please try again.";
                    showAlert();
                }

            }
        });
        thread.start();
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException ex)
        {
            alertString = "Could not delete Project. Please try again.";
            showAlert();
        }
    }

    /**
     * Deletes a task from the view, and archives it in the database.
     * Removes the task from every combobox in the program.
     * @throws ModelException DalException
     */
    @FXML
    private void handleDeleteTask(ActionEvent event) throws ModelException
    {
        tasksTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Task selectedTask = tasksTableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null)
        {
            model.deleteTask(selectedTask, 1);
            filteredTaskList.remove(selectedTask);
            projectComboBox.getSelectionModel().clearSelection();
            taskComboBox.getSelectionModel().clearSelection();

        } else
        {
            alertString = "Could not delete task. Please try again.";
            showAlert();
        }
    }

    /**
     * Deletes a client from the view, and archives it in the database.
     * Archives every project and task belonging to the given client. 
     * Removes the client, projects and tasks from all the comboboxes. 
     * @throws ModelException DalException
     */
    @FXML
    private void handleDeleteClient(ActionEvent event) throws ModelException
    {
        clientTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Client selectedClient = clientTableView.getSelectionModel().getSelectedItem();
        ArrayList<Project> tempDeletedList = new ArrayList<>();
        ArrayList<Task> toBeDeleted = new ArrayList<>();
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {

                if (selectedClient != null)
                {
                    try
                    {
                        model.deleteClient(selectedClient, 1);

                        for (Project project : projectsTableView.getItems())
                        {
                            if (selectedClient.getId() == project.getClientId())
                            {
                                tempDeletedList.add(project);
                            }

                        }

                        for (Project project1 : tempDeletedList)
                        {
                            for (Task task : filteredTaskList)
                            {
                                if (project1.getId() == task.getProjectId())
                                {
                                    toBeDeleted.add(task);
                                    model.deleteTaskOnProject(task, 1, project1.getId());
                                }

                            }
                            model.deleteProjectOnClient(project1, 1, selectedClient.getId());
                        }

                        for (Task task : toBeDeleted)
                        {
                            for (ListIterator<Task> iterator = filteredTaskList.listIterator(); iterator.hasNext(); )
                            {
                                Task task1 = iterator.next();
                                if (task.getId() == task1.getId())
                                {
                                    iterator.remove();
                                }
                            }
                        }

                        for (int i = 0; i < clientComboBox.getItems().size(); i++)
                        {
                            if (selectedClient.getId() == clientComboBox.getItems().get(i).getId())
                            {
                                clientComboBox.getItems().remove(i);
                                clientComboBox.getSelectionModel().clearSelection();
                            }
                        }

                        for (Project project : tempDeletedList)
                        {
                            for (ListIterator<Project> iterator = projectComboBox.getItems().listIterator(); iterator.hasNext(); )
                            {
                                Project project1 = iterator.next();
                                if (project.getId() == project1.getId())
                                {
                                    iterator.remove();
                                }
                            }
                        }

                        for (Project project : tempDeletedList)
                        {
                            for (ListIterator<Project> iterator = projectComboBox2.getItems().listIterator(); iterator.hasNext(); )
                            {
                                Project project1 = iterator.next();
                                if (project.getId() == project1.getId())
                                {
                                    iterator.remove();
                                }
                            }
                        }

                        projectComboBox.getSelectionModel().clearSelection();
                        projectComboBox2.getSelectionModel().clearSelection();
                        taskComboBox.getSelectionModel().clearSelection();

                    } catch (ModelException ex)
                    {
                        Logger.getLogger(TimeLoggerViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else
                {
                    alertString = "Could not delete client. Please try again.";
                    showAlert();
                }
            }
        });
        thread.start();
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException ex)
        {
            alertString = "Could not delete client. Please try again.";
            showAlert();
        }
    }

    /**
     * Filters the tasks displayed in the analysis tab depending on the dates selected.
     */
    private ObservableList<Task> analyseChartFilter() throws ModelException, ParseException
    {
        List<Task> taskNames = model.getAllTasksProjectName();
        ObservableList<Task> result = FXCollections.observableArrayList();
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(chartStartDate.getValue().toString());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(start);
        calendar2.add(Calendar.DATE, 1);
        Date sDate = calendar2.getTime();

        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(chartEndDate.getValue().toString());
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(end);
        calendar3.add(Calendar.DATE, 1);
        Date eDate = calendar3.getTime();

        for (Task tasks : taskNames)
        {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(tasks.getDate());

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            calendar1.add(Calendar.DATE, 1);
            Date x = calendar1.getTime();
            if (x.after(sDate) && x.before(eDate) || x.equals(sDate) || x.equals(eDate))
            {
                result.add(tasks);
            }
        }
        return result;
    }

    /**
     * Calls the filterChart method
     * @throws ModelException DalException
     */
    @FXML
    private void handleFilterCharts(ActionEvent event) throws ModelException, ParseException
    {
        filterChartMethod();
    }

    /**
     * Displays an alert for the user if an error happens.
     */
    private void showAlert()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, alertString, ButtonType.OK);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
        stage.toFront();
    }

    /**
     * Gets the selected project, and calculate the estimated chost price, from
     * the hourly rate and the time used Formated correctly.
     */
    private void calculateCostPrice()
    {
        projectsTableView.setOnMousePressed((MouseEvent event) ->
        {
            try
            {
                costPrice.clear();
                double usedTime = projectsTableView.getSelectionModel().getSelectedItem().getUsedTime();
                double hourlyRate = projectsTableView.getSelectionModel().getSelectedItem().getHourlyRate() / 60;
                double estimatedChostPrice = usedTime * hourlyRate;

                String pattern = "####,####,###.##";
                DecimalFormat decimalFormat = new DecimalFormat(pattern);
                String number = decimalFormat.format(estimatedChostPrice);
                costPrice.setText(number);
            } catch (NullPointerException ex)
            {
            }
        });
    }

    /**
     * Sets the data for the chart in the analysis tab.
     * If a specific project is selected, loads all the tasks from the project.
     * Otherwise loads data for every project in the program.
     * @throws ModelException
     * @throws ParseException 
     */
    private void filterChartMethod() throws ModelException, ParseException
    {
        try
        {
            Project selectedProject = projectComboBox2.getSelectionModel().getSelectedItem();
            if (selectedProject != null)
            {
                barChart.getData().clear();
                XYChart.Series set2 = new XYChart.Series<>();
                XYChart.Series set3 = new XYChart.Series<>();
                barChart.setAnimated(false);
                set2.setName("Paid task");
                set3.setName("Not paid task");
                barChart.setBarGap(-10);

                if (chartStartDate.getValue() != null || chartEndDate.getValue() != null)
                {
                    for (Task allTasks : analyseChartFilter())
                    {
                        if (allTasks.getProjectId() == selectedProject.getId() && allTasks.getPaid() == 1)
                        {
                            set2.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        } else if (allTasks.getProjectId() == selectedProject.getId() && allTasks.getPaid() == 0)
                        {
                            set3.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        }
                    }
                } else
                {
                    for (Task allTasks : model.getAllTasks())
                    {
                        if (allTasks.getProjectId() == selectedProject.getId() && allTasks.getPaid() == 1)
                        {
                            set2.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        } else if (allTasks.getProjectId() == selectedProject.getId() && allTasks.getPaid() == 0)
                        {
                            set3.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        }
                    }
                }

                barChart.getData().addAll(set2);
                barChart.getData().addAll(set3);
            } else
            {
                alertString = "Could not filter charts. Please select a project and try again.";
                showAlert();
            }
        } catch (NullPointerException e)
        {

        }
    }

    /**
     * Clears the datefilters in the analysis tab.
     * @param event
     * @throws ModelException
     * @throws ParseException 
     */
    @FXML
    private void clearAnalysisDate(ActionEvent event) throws ModelException, ParseException
    {
        if (chartStartDate.getValue() != null && chartEndDate.getValue() != null)
        {
            barChart.getData().clear();
            filterChartMethod();
            chartStartDate.setValue(null);
            chartEndDate.setValue(null);
        } else
        {
            alertString = "Could not clear filter. Please try again.";
            showAlert();
        }
    }

    @FXML
    private void handleCreateProject(ActionEvent event)
    {
    }

    @FXML
    private void setTaskData(ActionEvent event)
    {
    }

    @FXML
    private void createTask(ActionEvent event)
    {
    }
}
