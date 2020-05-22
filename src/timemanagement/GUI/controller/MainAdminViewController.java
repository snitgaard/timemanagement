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
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import timemanagement.BE.Client;
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
    private JFXComboBox<Project> projectComboBox2;
    @FXML
    private JFXTextField txt_projectNavn;
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
    private JFXTextField txt_nyBrugtTid;
    private User selectedUser;
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
    private Label loginTextField;
    private LoginController controller;
    private String username;
    ObservableList<User> allUsersResultList = FXCollections.observableArrayList();
    ObservableList<Project> allProjectsFilteredList = FXCollections.observableArrayList();
    ObservableList<Task> filteredTaskList = FXCollections.observableArrayList();
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
    String alertString = "Generic Warning";
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
        // TODO
        try
        {
            calculateCostPrice();
            timeLoggerPane.toFront();

            model = model.getInstance();
            setProjects();

            ObservableList<String> roles = FXCollections.observableArrayList("Admin", "User");
            userComboBox.setItems(roles);

//            projekterTableView.setItems(model.getProjectClientName());
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
        List<JFXButton> dashBoardButtons = new ArrayList<>();
        dashBoardButtons.add(timeLoggerButton);
        dashBoardButtons.add(opgaverButton);
        dashBoardButtons.add(analyseButton);
        dashBoardButtons.add(projekterButton);
        dashBoardButtons.add(opretBrugerButton);
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
        if (actionEvent.getSource() == opgaverButton)
        {
            opgaverPane.toFront();
            opgaverButton.getStyleClass().removeAll("pane-button");
            opgaverButton.getStyleClass().add("pane-buttonSelected");
        }
        if (actionEvent.getSource() == analyseButton)
        {
            analysePane.toFront();
            analyseButton.getStyleClass().removeAll("pane-button");
            analyseButton.getStyleClass().add("pane-buttonSelected");
        }
        if (actionEvent.getSource() == projekterButton)
        {
            projektPane.toFront();
            projekterButton.getStyleClass().removeAll("pane-button");
            projekterButton.getStyleClass().add("pane-buttonSelected");
        }
        if (actionEvent.getSource() == opretBrugerButton)
        {
            opretBrugerPane.toFront();
            opretBrugerButton.getStyleClass().removeAll("pane-button");
            opretBrugerButton.getStyleClass().add("pane-buttonSelected");
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
        projektComboBox.setItems(model.getProjectClientName());
        projectComboBox2.setItems(model.getAllProjects());
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
        List<Task> taskList = model.getAllTasksProjectName();

        for (Task task : taskList)
        {
            if (task.getUserId() == selectedUser.getId())
            {
                filteredTaskList.add(task);
            }
        }

        opgaverTableView.setItems(filteredTaskList);

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
        userViewRolle.setCellValueFactory(cellData -> cellData.getValue().adminRightsProperty());
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

        projekterTableView.setItems(model.getProjectClientName());

        projektNavnAdminColumn.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        kundeColumn.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        brugtTidAdminColumn.setCellValueFactory(cellData -> cellData.getValue().usedTimeObservable());
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
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                startTidField.setText(sdf.format(date));
                slutTidField.clear();
                brugtTidField.clear();
            } catch (Exception e)
            {
                alertString = "Could not format time difference.";
                showAlert();
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
            Task selectedTask = opgaveComboBox.getSelectionModel().getSelectedItem();
            Project selectedProject = projektComboBox.getSelectionModel().getSelectedItem();
            long gammelBrugtTid = selectedTask.getUsedTime();
            long gammelProjektTid = selectedProject.getUsedTime();

            java.util.Date date = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            slutTidField.setText(sdf.format(date));

            brugtTidField.setText(model.timeFormatter(startTidField.getText(), slutTidField.getText()));
            System.out.println(opgaveComboBox.getSelectionModel().getSelectedItem().getId() + "HEJ ER DET HER?????");
            System.out.println(selectedProject + "DET ER HER");

            if (selectedProject.getRounded() == 0)
            {
                System.out.println(opgaveComboBox.getSelectionModel().getSelectedItem().getId() + "HEJ ER DET HER?????");
                model.addTime(model.timeCalculator(startTidField.getText(), slutTidField.getText()), opgaveComboBox.getSelectionModel().getSelectedItem().getId());
            } else
            {
                System.out.println(model.timeCalculator(startTidField.getText(), slutTidField.getText()));
                model.addRoundedTime(model.timeCalculator(startTidField.getText(), slutTidField.getText()), opgaveComboBox.getSelectionModel().getSelectedItem().getId());
            }

            for (int i = 0; i < opgaverTableView.getItems().size(); i++)
            {
                if (selectedTask.getId() == opgaverTableView.getItems().get(i).getId())
                {
                    if (selectedProject.getRounded() == 0)
                    {
                        opgaverTableView.getItems().get(i).setUsedTime(gammelBrugtTid + model.timeCalculator(startTidField.getText(), slutTidField.getText()));
                    } else
                    {
                        double time = model.timeCalculator(startTidField.getText(), slutTidField.getText());
                        Double roundedTime = Math.ceil(time / 15) * 15;
                        long realRoundedTime = roundedTime.longValue();

                        opgaverTableView.getItems().get(i).setUsedTime(gammelBrugtTid + realRoundedTime);

                    }

                }
            }

            for (int i = 0; i < projekterTableView.getItems().size(); i++)
            {
                if (selectedTask.getProjectId() == projekterTableView.getItems().get(i).getId())
                {
                    projekterTableView.getItems().get(i).setUsedTime(gammelProjektTid + model.timeCalculator(startTidField.getText(), slutTidField.getText()));
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
        Project selectedProject = projektComboBox.getSelectionModel().getSelectedItem();

        opgaveComboBox.getItems().clear();

        if (projektComboBox.getSelectionModel().getSelectedItem() != null)
        {
            kundeField.setText(selectedProject.getClientName());
            buttonState = false;
            disableTimeLoggerButtons();

            for (Task tasks : filteredTaskList)
            {
                if (tasks.getProjectId() == selectedProject.getId())
                {
                    opgaveComboBox.getItems().add(tasks);
                }
            }
        }
    }

    private void disableTimeLoggerButtons()
    {
        opgaveComboBox.setDisable(buttonState);
        titelField.setDisable(buttonState);
        beskrivelseTextArea.setDisable(buttonState);
        betaltCheckBox.setDisable(buttonState);
        nyOpgaveButton.setDisable(buttonState);
        btn_start.setDisable(buttonState);
        editButton.setDisable(buttonState);
        startTidField.setDisable(buttonState);
        slutTidField.setDisable(buttonState);
        brugtTidField.setDisable(buttonState);
    }

    /**
     * Calls the opgaveData method as an action event, and disables start button
     * if titlefield and beskrivelsetextarea is not empty.
     *
     * @throws ModelException
     */
    @FXML
    private void setOpgaveData(ActionEvent event) throws ModelException
    {
        opgaveData();
        Task selectedTask = opgaveComboBox.getSelectionModel().getSelectedItem();
        if (titelField.getText() != null && beskrivelseTextArea.getText() != null)
        {
            buttonState = false;
            disableTimeLoggerButtons();
        } else if (titelField.getText() == null && beskrivelseTextArea.getText() == null && selectedTask == null)
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
        Task selectedTask = opgaveComboBox.getSelectionModel().getSelectedItem();

        if (opgaveComboBox.getSelectionModel().getSelectedItem() != null)
        {
            titelField.setText(selectedTask.getTaskName());

//            long hours = (result.get(0).getUsedTime() - result.get(0).getUsedTime() % 3600) / 3600;
//            long minutes = (result.get(0).getUsedTime() % 3600 - result.get(0).getUsedTime() % 3600 % 60) / 60;
//            long seconds = result.get(0).getUsedTime() % 3600 % 60;
//            NumberFormat f = new DecimalFormat("00");
            beskrivelseTextArea.setText(selectedTask.getDescription());

            if (selectedTask.getPayed() == 1)
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
            if (opretAdminCheckBox.isSelected() && !userLogin.isEmpty() && !txt_userPassword.getText().isEmpty() && !email.isEmpty() && !fullName.isEmpty())
            {
                model.createUser(userLogin, userPassword, 1, email, fullName);
            } else if (!opretAdminCheckBox.isSelected() && !txt_userLogin.getText().isEmpty() && !txt_userPassword.getText().isEmpty() && !email.isEmpty() && !fullName.isEmpty())
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
    @FXML
    private void createOpgave(ActionEvent event) throws ModelException
    {
        int projektId = projektComboBox.getSelectionModel().getSelectedItem().getId();
        Task selectedTask = null;

        if (betaltCheckBox.isSelected() == true && !titelField.getText().isEmpty() && !beskrivelseTextArea.getText().isEmpty())
        {
            selectedTask = model.createTask(titelField.getText(), projektId, 0, LocalDate.now().toString(), beskrivelseTextArea.getText(),
                    1, projektComboBox.getSelectionModel().getSelectedItem().getProjectName(), 0, this.selectedUser.getId());

            opgaveComboBox.getItems().add(selectedTask);
            opgaveComboBox.getSelectionModel().select(selectedTask);
            filteredTaskList.add(selectedTask);
        } else if (betaltCheckBox.isSelected() == false && !titelField.getText().isEmpty() && !beskrivelseTextArea.getText().isEmpty())
        {
            selectedTask = model.createTask(titelField.getText(), projektId, 0, LocalDate.now().toString(), beskrivelseTextArea.getText(), 0,
                    projektComboBox.getSelectionModel().getSelectedItem().getProjectName(), 0, this.selectedUser.getId());

            opgaveComboBox.getItems().add(selectedTask);
            opgaveComboBox.getSelectionModel().select(selectedTask);
            filteredTaskList.add(selectedTask);
        } else
        {
            alertString = "Could not create task. Plesae try again.";
            showAlert();
        }

//        opgaveComboBox.getSelectionModel().select(titelField.getText());
    }

    /**
     * Creates a project with all the inputted information
     *
     * @throws ModelException
     */
    @FXML
    private void handleCreateProjekt(ActionEvent event) throws ModelException
    {
        Client selectedClient = clientComboBox.getSelectionModel().getSelectedItem();
        Project selectedProject = null;
        double doubleHourlyRate = 0;

        try
        {
            if (!txt_projectNavn.getText().isEmpty() && selectedClient != null)
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
                    selectedProject = model.createProject(txt_projectNavn.getText(), model.getClientId(selectedClient.getClientName()), LocalDate.now().toString(), 0, 1, selectedClient.getClientName(), doubleHourlyRate, 1);
                } else
                {
                    selectedProject = model.createProject(txt_projectNavn.getText(), model.getClientId(selectedClient.getClientName()), LocalDate.now().toString(), 0, 1, selectedClient.getClientName(), doubleHourlyRate, 0);
                }

                projektComboBox.getItems().add(selectedProject);
                projectComboBox2.getItems().add(selectedProject);
                allProjectsFilteredList.add(selectedProject);
            } else if (txt_projectNavn.getText().isEmpty() || selectedClient == null)
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
        opgaverTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Task selectedTask = opgaverTableView.getSelectionModel().getSelectedItem();
        Project selectedProject = null;

        try
        {
            if (selectedTask != null && !txt_nyBrugtTid.getText().isEmpty())
            {
                int nyBrugtTid = Integer.parseInt(txt_nyBrugtTid.getText());
                selectedTask.setUsedTime(nyBrugtTid);
                model.updateTask(selectedTask);
                for (int i = 0; i < projekterTableView.getItems().size(); i++)
                {
                    if (selectedTask.getProjectId() == projekterTableView.getItems().get(i).getId())
                    {
                        selectedProject = projekterTableView.getItems().get(i);
                        model.updateProjectTime(selectedProject);
                    }
                }
            } else if (selectedTask == null && txt_nyBrugtTid.getText().isEmpty())
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

            opgaverTableView.setItems(result);
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
            opgaverTableView.setItems(filteredTaskList);
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
     *
     *
     * @throws ModelException DalException
     */
    private void setOngoing(ActionEvent event) throws ModelException
    {
        setProjectTable();
    }

    /**
     *
     *
     * @throws ModelException DalException
     */
    private void handleArchiveProject(ActionEvent event) throws ModelException
    {
        try
        {
            projekterTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            Project selectedProject = projekterTableView.getSelectionModel().getSelectedItem();

            if (selectedProject.getIsDeleted() != 0)
            {
                selectedProject.setIsDeleted(0);
                model.archiveProject(selectedProject);
                allProjectsFilteredList.remove(selectedProject);
            } else if (selectedProject.getIsDeleted() == 0)
            {
                selectedProject.setIsDeleted(1);
                model.archiveProject(selectedProject);
                allProjectsFilteredList.add(selectedProject);
            }

        } catch (Exception e)
        {
            alertString = "Could not archive project. Please try again.";
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

        Task selectedTask = opgaveComboBox.getSelectionModel().getSelectedItem();
        Task selectedTaskTwo = null;

        if (titelField.getText().isEmpty() && beskrivelseTextArea.getText().isEmpty())
        {
            alertString = "Could not edit task. Please try again.";
            showAlert();
        }

        for (int i = 0; i < opgaverTableView.getItems().size(); i++)
        {

            if (opgaverTableView.getItems().get(i).getId() == selectedTask.getId())
            {
                selectedTaskTwo = opgaverTableView.getItems().get(i);
            }
        }

        if (betaltCheckBox.isSelected() == true)
        {
            int payed = 1;
            selectedTask.setTaskName(titelField.getText());
            selectedTask.setDescription(beskrivelseTextArea.getText());
            selectedTask.setPayed(payed);
            selectedTaskTwo.setTaskName(titelField.getText());
            model.editTask(selectedTask);
            opgaveComboBox.getItems().remove(selectedTask);
            opgaveComboBox.getItems().add(selectedTask);
            opgaveComboBox.setValue(selectedTask);

        } else if (betaltCheckBox.isSelected() == false)
        {
            int payed = 0;
            selectedTask.setTaskName(titelField.getText());
            selectedTask.setDescription(beskrivelseTextArea.getText());
            selectedTask.setPayed(payed);
            selectedTaskTwo.setTaskName(titelField.getText());
            model.editTask(selectedTask);
            opgaveComboBox.getItems().remove(selectedTask);
            opgaveComboBox.getItems().add(selectedTask);
            opgaveComboBox.setValue(selectedTask);
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
                int number = -1;
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
                        number = number + 1;
                        if (allTasks.getPayed() == 1)
                        {
                            set1.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        } else if (allTasks.getPayed() == 0)
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
     *
     *
     * @throws ModelException DalException
     */
    @FXML
    private void handleDeleteProject(ActionEvent event) throws ModelException
    {
        projekterTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Project selectedProject = projekterTableView.getSelectionModel().getSelectedItem();
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
                            for (ListIterator<Task> iterator = filteredTaskList.listIterator(); iterator.hasNext();)
                            {
                                Task task1 = iterator.next();
                                if (task.getId() == task1.getId())
                                {
                                    iterator.remove();
                                }
                            }
                        }

                        for (int i = 0; i < projektComboBox.getItems().size(); i++)
                        {
                            if (selectedProject.getId() == projektComboBox.getItems().get(i).getId())
                            {
                                projektComboBox.getItems().remove(i);
                                projektComboBox.getSelectionModel().clearSelection();
                                kundeField.clear();
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
                        opgaveComboBox.getSelectionModel().clearSelection();

                    } catch (ModelException ex)
                    {
                        Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
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
     *
     *
     * @throws ModelException DalException
     */
    @FXML
    private void handleDeleteTask(ActionEvent event) throws ModelException
    {
        opgaverTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Task selectedTask = opgaverTableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null)
        {
            model.deleteTask(selectedTask, 1);
            filteredTaskList.remove(selectedTask);
            projektComboBox.getSelectionModel().clearSelection();
            opgaveComboBox.getSelectionModel().clearSelection();
            
        } else
        {
            alertString = "Could not delete task. Please try again.";
            showAlert();
        }
    }

    /**
     *
     *
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

                        for (Project project : projekterTableView.getItems())
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
                            for (ListIterator<Task> iterator = filteredTaskList.listIterator(); iterator.hasNext();)
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
                            for (ListIterator<Project> iterator = projektComboBox.getItems().listIterator(); iterator.hasNext();)
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
                            for (ListIterator<Project> iterator = projectComboBox2.getItems().listIterator(); iterator.hasNext();)
                            {
                                Project project1 = iterator.next();
                                if (project.getId() == project1.getId())
                                {
                                    iterator.remove();
                                }
                            }
                        }
                        
                        projektComboBox.getSelectionModel().clearSelection();
                        projectComboBox2.getSelectionModel().clearSelection();
                        opgaveComboBox.getSelectionModel().clearSelection();
                        
                        

                    } catch (ModelException ex)
                    {
                        Logger.getLogger(MainAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
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
     *
     *
     * @throws ModelException DalException
     */
    @FXML
    private void handleFilterCharts(ActionEvent event) throws ModelException, ParseException
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
                System.out.println("hvad er det her  = " + selectedProject.getId());

                if (chartStartDate.getValue() != null || chartEndDate.getValue() != null)
                {
                    for (Task allTasks : analyseChartFilter())
                    {
                        if (allTasks.getProjectId() == selectedProject.getId() && allTasks.getPayed() == 1)
                        {
                            set2.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        } else if (allTasks.getProjectId() == selectedProject.getId() && allTasks.getPayed() == 0)
                        {
                            set3.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        }
                    }
                } else
                {
                    for (Task allTasks : model.getAllTasks())
                    {
                        if (allTasks.getProjectId() == selectedProject.getId() && allTasks.getPayed() == 1)
                        {
                            set2.getData().add(new BarChart.Data((allTasks.getTaskName() + " - " + allTasks.getUsedTime()), allTasks.getUsedTime()));
                        } else if (allTasks.getProjectId() == selectedProject.getId() && allTasks.getPayed() == 0)
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
     *
     *
     *
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
        projekterTableView.setOnMousePressed((MouseEvent event) ->
        {
            try
            {
                costPrice.clear();
                double usedTime = projekterTableView.getSelectionModel().getSelectedItem().getUsedTime();
                double hourlyRate = projekterTableView.getSelectionModel().getSelectedItem().getHourlyRate() / 60;
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

}
