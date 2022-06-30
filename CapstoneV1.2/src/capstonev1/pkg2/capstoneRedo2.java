package capstonev1.pkg2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import java.util.Set;

import java.util.ArrayList;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import static javafx.geometry.Orientation.VERTICAL;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.JComboBox;
import oracle.jdbc.pool.OracleDataSource;
import javafx.scene.layout.TilePane;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

//import oracle.jdbc.pool.OracleDataSource;
public class capstoneRedo2 extends Application {

    //Making a comment fknlslfm;ammk
    /*
    TO DO:
    1. Add specilization and social pages
    2. Add database functionality and populate boxes
    3. Continue to work on making prettier
    (THIS IS JUST A GUI AS OF NOW)
    
    NOTES:
    1. Many methods specific to this application to save time and make code
    prettier, make sure to read through to familiarize yourself  before 
    beginning edits. 
     */
    BorderPane pane = new BorderPane();
    GridPane centerPane = new GridPane();

    Button backButton = new Button("Home Screen");
    ToolBar toolBar = new ToolBar();
    ToolBar toolBarA = new ToolBar();
    Button applicationBackButton = new Button("Back");
    Button backAnimals = new Button("Back");
    String user;
    int loggedInVolID; //Use this to get the id number of the volunteer that is logged in

    String fontStyle = "garamond";

    String buttonStyle = "-fx-background-color: #2F4F4F; -fx-text-fill: #FFFFFF;"
            + "-fx-font-family: \"" + fontStyle + "\";";
    String reportButtonStyle = "-fx-background-color: #66CDAA; -fx-text-fill: #FFFFFF;"
            + "-fx-font-family: \"" + fontStyle + "\";";

    Button report1 = new Button("Volunteer Hours");
    Button report2 = new Button("Event Attendance");
    Button report3 = new Button("Volunteer \nSpecialization");
    Button report4 = new Button("Volunteer \nContact \nInformation");


    Button saveButton = new Button("Save");
    TableView tableHome = new TableView();
    ComboBox<String> cboStatus = new ComboBox<>();
    ComboBox<String> cboAnimal = new ComboBox<>();

    @Override
    public void start(Stage primaryStage) throws SQLException {
        pane.setTop(heading("WELCOME TO THE BARK DATABASE"));
        welcomeModule();

        BorderPane.setMargin(centerPane, new Insets(30, 30, 30, 30));

        pane.setBackground(new Background(new BackgroundFill(
                Color.MEDIUMAQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

        report1.setStyle(reportButtonStyle);
        report2.setStyle(reportButtonStyle);
        report3.setStyle(reportButtonStyle);
        report4.setStyle(reportButtonStyle);

        toolBar.getItems().addAll(report1, report2, report3, report4);

        // Animal functions
        saveButton.setStyle(buttonStyle);
        
        backAnimals.setOnAction(e -> {
            try {
                viewAnimals();

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        });
//        cboAge.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
//                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
//                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
//                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
//                41, 42, 43, 44, 45, 46, 47, 48, 49, 50);
        
        //cboWeight.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
//                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
//                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
//                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
//                41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
//                51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
//                61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
//                71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
//                81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
//                91, 92, 93, 94, 95, 96, 97, 98, 99, 100);
        // cboWeight.setEditable(true);
        centerPane.setHgap(10.0);

        Scene scene = new Scene(pane, 500, 500); //Create a scene
        primaryStage.setTitle("Bark"); //set stage title
        primaryStage.setScene(scene); //place scene on stage
        primaryStage.show();//display the stage
    }

    public Text subHeading(String string) {
        Text text = new Text(string);
        text.setFont(Font.font(fontStyle, FontWeight.BOLD, 15));
        //text.setStroke(Color.DARKSLATEGRAY);
        //text.setStrokeWidth(1);
        text.setFill(Color.DARKSLATEGRAY);
        return text;
    }

    public Text heading(String title) {
        Text text = new Text(title);
        text.setFont(Font.font(fontStyle, FontWeight.BOLD, 17));
        //text.setStroke(Color.DARKSLATEGRAY);
        //text.setStrokeWidth(1);
        text.setFill(Color.DARKSLATEGRAY);
        BorderPane.setMargin(text, new Insets(10, 10, 10, 10));
        BorderPane.setAlignment(text, Pos.CENTER);
        return text;
    }

    public Text labelText(String string) {
        Text text = new Text(string);
        text.setFont(Font.font(fontStyle, FontWeight.BOLD, 13));
        text.setFill(Color.DARKSLATEGRAY);
        return text;
    }

    public void addBackButton() {
        backButton.setStyle(buttonStyle);
        BorderPane.setMargin(backButton, new Insets(10));
        pane.setBottom(backButton);
        backButton.setOnAction(e -> {
            pane.getChildren().remove(toolBar);
            pane.getChildren().remove(backButton);
            homeScreen();
        });
    }

    public void welcomeModule() throws SQLException {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        pane.getChildren().remove(applicationBackButton);
        refreshCenterPane(centerPane);

        TextField userNameTF = new TextField();
        TextField passwordTF = new TextField();
        Button loginButton = new Button("Login");
        Button appButton = new Button("Volunteer Application");

        loginButton.setStyle(buttonStyle);
        appButton.setStyle(buttonStyle);
        backAnimals.setStyle(buttonStyle);
        BorderPane.setMargin(backAnimals, new Insets(10));

        // temporary
        Button t = new Button("Temp");
        t.setOnAction(e -> {
            homeScreen();
        });

        loginButton.setOnAction(e -> {
            user = userNameTF.getText();
            String pass = passwordTF.getText();
            try {
                ResultSet rs = statement.executeQuery("select a.password from application a, volunteers v "
                        + "where a.applicationid = v.applicationid and a.email = '" + user + "'");
                if (!rs.isBeforeFirst()) {
                    //System.out.println("User not found!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Invalid credentials");
                    alert.showAndWait();
                } else {
                    while (rs.next()) {
                        String retrievedPass = rs.getString("password");
                        if (retrievedPass.equals(pass)) {
                            homeScreen();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Invalid credentials");
                            alert.showAndWait();
                        }
                    }
                }
                ResultSet rsVolID = statement.executeQuery("select volunteerid "
                        + "from volunteers "
                        + "where applicationid = "
                        + "    (select applicationid "
                        + "        from application "
                        + "         where email = '" + user + "')");
                while (rsVolID.next()) {
                    int volID = rsVolID.getInt(1);
                    loggedInVolID = volID;
                }

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });

        appButton.setOnAction(e -> {
            try {
                applicationScreen();

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });

        centerPane.add(subHeading("VOLUNTEER LOG IN"), 0, 0);
        centerPane.add(labelText("User Name"), 0, 1);
        centerPane.add(labelText("Password"), 0, 2);
        centerPane.add(userNameTF, 1, 1);
        centerPane.add(passwordTF, 1, 2);
        centerPane.add(loginButton, 1, 3);
        centerPane.setVgap(5);

        centerPane.add(t, 1, 4); // temporary button to bypass login

        centerPane.add(subHeading("\n\n\nNEW VOLUNTEERS"), 0, 10);
        centerPane.add(appButton, 0, 11);

        GridPane.setMargin(loginButton, new Insets(5));
        GridPane.setMargin(appButton, new Insets(5));
        pane.setCenter(centerPane);

    }

    public void refreshCenterPane(GridPane gP) {
        gP.getChildren().clear();
    }

    public void applicationScreen() throws SQLException {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        refreshCenterPane(centerPane);

        pane.setTop(heading("Volunteer Application"));

        TextField applicationFirstName = new TextField();
        TextField applicationLastName = new TextField();
        DatePicker applicationDOB = new DatePicker();
        TextField applicationEmail = new TextField();
        TextField applicationPassword = new TextField();
        TextField applicationPhone = new TextField();
        TextField applicationAddress = new TextField();
        ComboBox<String> applicationExperience = new ComboBox<>();
        ObservableList<String> experienceList = FXCollections.observableArrayList();
        try {

            String query = "Select distinct experience from application";
            ResultSet rsExperience = statement.executeQuery(query);
            while (rsExperience.next()) {
                experienceList.add(rsExperience.getString("experience"));
            }
        } catch (SQLException ex) {
        }

        Button applicationSubmit = new Button("Submit Application");
        applicationSubmit.setStyle(buttonStyle);

        applicationBackButton.setStyle(buttonStyle);
        applicationBackButton.setOnAction(e -> {
            try {
                welcomeModule();

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });

        centerPane.add(subHeading("Complete all Fields"), 0, 0);
        centerPane.add(labelText("First Name:"), 0, 1);
        centerPane.add(labelText("Last Name:"), 0, 2);
        centerPane.add(labelText("Birth Date:"), 0, 3);
        centerPane.add(labelText("Phone Number:"), 0, 4);
        centerPane.add(labelText("Email"), 0, 5);
        centerPane.add(labelText("Password"), 0, 6);
        centerPane.add(labelText("Address:"), 0, 7);
        centerPane.add(labelText("Experience:"), 0, 8);

        centerPane.add(applicationFirstName, 1, 1);
        centerPane.add(applicationLastName, 1, 2);
        centerPane.add(applicationDOB, 1, 3);
        centerPane.add(applicationPhone, 1, 4);
        centerPane.add(applicationEmail, 1, 5);
        centerPane.add(applicationPassword, 1, 6);
        centerPane.add(applicationAddress, 1, 7);
        centerPane.add(applicationExperience, 1, 8);
        centerPane.add(applicationSubmit, 1, 10);
        applicationExperience.setItems(experienceList);

        String query = "Select * from application";
        try {
            try (ResultSet rsApplications = statement.executeQuery(query)) {
                while (rsApplications.next()) { //get database data
                    App dbApp = new App();
                    dbApp.setAppID(rsApplications.getInt(1));
                    dbApp.setAFirst(rsApplications.getString(2));
                    dbApp.setALast(rsApplications.getString(3));
                    dbApp.setDOB(rsApplications.getString(4));
                    dbApp.setPhone(rsApplications.getString(5));
                    dbApp.setEmail(rsApplications.getString(6));
                    dbApp.setPassword(rsApplications.getString(7));
                    dbApp.setAddress(rsApplications.getString(8));
                    dbApp.setExperience(rsApplications.getString(9));
                }
            }
        } catch (SQLException ex) {
        }
        applicationSubmit.setOnAction(e -> {

            //give error if any fields are empty
            if (applicationFirstName.getText().isEmpty() || applicationLastName.getText().isEmpty() || applicationDOB.getValue() == null
                    || applicationPhone.getText().isEmpty() || applicationEmail.getText().isEmpty() || applicationPassword.getText() == null
                    || applicationAddress.getText().isEmpty()
                    || applicationExperience.getValue().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter all data");
                alert.showAndWait();
            } else {
                try {
                    App submittedApp = new App( //create new application
                            applicationFirstName.getText(),
                            applicationLastName.getText(),
                            applicationDOB.getValue().format(DateTimeFormatter.ISO_DATE),
                            applicationPhone.getText(),
                            applicationEmail.getText(),
                            applicationPassword.getText(),
                            applicationAddress.getText(),
                            applicationExperience.getValue()
                    );
                    String insert = "INSERT INTO APPLICATION (ApplicationID,FirstName,LastName,DOB,phone,email,password, address,experience,status) VALUES (" + submittedApp.appID + ", '"
                            + applicationFirstName.getText() + "', '" + applicationLastName.getText() + "', TO_DATE('" + applicationDOB.getValue() + "','yyyy-mm-dd'), '" + applicationPhone.getText() + "', '"
                            + applicationEmail.getText() + "', '" + applicationPassword.getText() + "', '" + applicationAddress.getText() + "', '" + applicationExperience.getValue() + "', '"
                            + submittedApp.getStatus() + "')";
                    statement.execute(insert);
                    statement.execute("commit");

                    //clear text fields after submission
                    applicationFirstName.setText("");
                    applicationLastName.setText("");
                    applicationDOB.setValue(null);
                    applicationPhone.setText("");
                    applicationEmail.setText("");
                    applicationPassword.setText("");
                    applicationAddress.setText("");
                    applicationExperience.setValue("");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that application was submitted
                    alert.setHeaderText("Application Submitted!");
                    alert.showAndWait();

                } catch (SQLException ex) {
                    Logger.getLogger(capstoneRedo2.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pane.setBottom(applicationBackButton);

        pane.setCenter(centerPane);

    }

    public void homeScreen() {
        pane.setTop(heading("Volunteer Home Screen"));

        Button checkInOut = new Button("Check In/Out");
        Button reportsButton = new Button("Reports Page");
        Button socialButton = new Button("Social Page");
        Button logEventButton = new Button("Create A Task");
        Button assignSpecButton = new Button("Assign Specialization");
        Button applicationApproval = new Button("View Pending Applications");
        Button btnAnimals = new Button("View Animals");

        checkInOut.setStyle(buttonStyle);
        reportsButton.setStyle(buttonStyle);
        socialButton.setStyle(buttonStyle);
        logEventButton.setStyle(buttonStyle);
        assignSpecButton.setStyle(buttonStyle);
        applicationApproval.setStyle(buttonStyle);
        btnAnimals.setStyle(buttonStyle);

        refreshCenterPane(centerPane);

        centerPane.add(subHeading("Please Choose From Menu"), 0, 0);
        centerPane.add(checkInOut, 0, 1);
        centerPane.add(applicationApproval, 0, 2);
        centerPane.add(btnAnimals, 0, 3);
        centerPane.add(assignSpecButton, 0, 4);
        centerPane.add(logEventButton, 0, 5);
        centerPane.add(reportsButton, 0, 6);
        centerPane.add(socialButton, 0, 7);

        checkInOut.setOnAction(e -> {
            try {
                volunteerCheckIO();

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        logEventButton.setOnAction(e -> {
            try {
                logTask();

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        reportsButton.setOnAction(e -> reports());
        assignSpecButton.setOnAction(e -> {
            try {
                assignSpec();

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        applicationApproval.setOnAction(e -> {
            try {
                applicationApproval();

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnAnimals.setOnAction(e -> {
            try {
                viewAnimals();

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        });

        centerPane.setVgap(8);
        pane.setCenter(centerPane);

    }

    public void assignSpec() throws SQLException {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        refreshCenterPane(centerPane);

        Button assignSpecButton = new Button("Submit Specialization");
        assignSpecButton.setStyle(buttonStyle);

        pane.setTop(heading("Assign a Specialization"));

        centerPane.add(subHeading("Make Selection"), 0, 0);
        centerPane.add(labelText("Volunteer:"), 0, 1);
        centerPane.add(labelText("Specialization:"), 0, 2);
        ComboBox<String> specializations = new ComboBox<>();
        ComboBox<String> volunteersList = new ComboBox<>();
        try {
            String query = "Select distinct specialization from volunteers";
            ResultSet rsSpecializations = statement.executeQuery(query);
            while (rsSpecializations.next()) {
                specializations.getItems().add(rsSpecializations.getString("specialization"));
            }
        } catch (SQLException ex) {
        }
        try { //trying to get the volunteers names for the volunteer combobox
            String query = "Select v.volunteerID, a.firstName, a.lastName "
                    + "from volunteers v, application a "
                    + "where a.applicationID = v.applicationID";
            ResultSet rsVolunteers = statement.executeQuery(query);
            while (rsVolunteers.next()) {
                int volID = rsVolunteers.getInt(1);
                String firstName = rsVolunteers.getString(2); //getting first name from application table
                String lastName = rsVolunteers.getString(3); //getting last name from application table
                String volInfo = volID + " " + firstName + " " + lastName; //combining into one string to add to the combobox
                volunteersList.getItems().add(volInfo); //populate combo box for volunteers????
                assignSpecButton.setOnAction(e -> {
                    try {
                        //code to update sql database when button is clicked
                        String selectedVolunteer = volunteersList.getSelectionModel().getSelectedItem();
                        String[] info = selectedVolunteer.split(" ");
                        String id = info[0];
                        String selectedSpec = specializations.getSelectionModel().getSelectedItem();
                        String sql = "update volunteers set specialization= '" + selectedSpec + "' "
                                + "where volunteerID= " + id;
                        statement.executeQuery(sql);

                    } catch (SQLException ex) {
                        Logger.getLogger(capstoneRedo2.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that application was submitted
                    alert.setHeaderText("Specialization assigned");
                    alert.showAndWait();
                });

            }
        } catch (SQLException ex) {
        }
        specializations.setEditable(true);
        centerPane.add(volunteersList, 1, 1);
        centerPane.add(specializations, 1, 2);
        centerPane.add(assignSpecButton, 1, 3);

        addBackButton();

        pane.setCenter(centerPane);

    }

    //public void volunteerCheckIO(String type) {
    public void volunteerCheckIO() throws SQLException {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        refreshCenterPane(centerPane);

        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        String timeNow = time.format(new Date());
        Label timeLbl = new Label(timeNow);
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        String todaysDate = date.format(new Date());
        Label dateLbl = new Label(todaysDate);
        ComboBox<String> cboTasks = new ComboBox<>();
       // ComboBox<String> cboAnimal = new ComboBox<>();
        Button clockin = new Button("Check in");
        clockin.setStyle(buttonStyle);
        Button clockout = new Button("Check Out");
        clockout.setStyle(buttonStyle);

        pane.setTop(heading("Volunteer Check In or Out"));

        centerPane.add(labelText("Todays Date: "), 0, 1);
        centerPane.add(labelText("Current Time: "), 0, 2);
        centerPane.add(labelText("Available Tasks: "), 0, 3);
        centerPane.add(labelText("Animal Going: "), 0, 4);
//        centerPane.add(labelText("Check-In Location: "), 0, 4);

        centerPane.add(dateLbl, 1, 1);
        centerPane.add(timeLbl, 1, 2);
        centerPane.add(cboTasks, 1, 3);
        centerPane.add(cboAnimal, 1, 4);
        centerPane.add(clockin, 0, 7);
        centerPane.add(clockout, 1, 7);
        try {
            String query = "select animalID, name from animals";
            ResultSet rsAnimal = statement.executeQuery(query);
            while (rsAnimal.next()) {
                int animalID = rsAnimal.getInt(1);
                String animalName = rsAnimal.getString(2);
                String animalInfo = "ID: " + animalID + " \nName: " + animalName;
                cboAnimal.getItems().add(animalInfo);
            }
        } catch (SQLException ex) {
        }
        ResultSet rsTasks = statement.executeQuery("select taskid, description, location, Mileage from Tasks");
        while (rsTasks.next()) {
            int taskID = rsTasks.getInt(1);
            String des = rsTasks.getString(2);
            String loc = rsTasks.getString(3);
            String mil = rsTasks.getString(4);
            String taskInfo = "Task ID: " + taskID + " \n" + des + "\nLocation: " + loc + ",\n" + mil + " Miles away";
            cboTasks.getItems().add(taskInfo);

//        ResultSet rsLocation = statement.executeQuery("select distinct Location from Events");
//        while (rsLocation.next()) {
//            cboLocation.getItems().add(rsLocation.getString(1));
//        }
            Shifts newShift = new Shifts();
            clockin.setOnAction(e -> {
                String selectedTask = cboTasks.getSelectionModel().getSelectedItem();
                String[] tasks = selectedTask.split(" ");
                String tID = tasks[2];

                String selectedAnimal = cboAnimal.getSelectionModel().getSelectedItem();
                String[] animals = selectedAnimal.split(" ");
                String aID = animals[1];

                newShift.setVolID(loggedInVolID);
                newShift.setTimeIn(timeNow);
                newShift.setTimeOut(" ");
                newShift.setDate(todaysDate);
                newShift.setTaskID(Integer.valueOf(tID));
                newShift.setAniamlID(Integer.valueOf(aID));
//            Shifts newShift = new Shifts(
                System.out.println(newShift);
                String sqlQuery = "insert into shifts (volunteerid, timein, timeout, shiftDate, taskID, animalID)"
                        + " values (" + newShift.volID + ",'" + newShift.timein + "', '" + newShift.timeout
                        + "', TO_DATE('" + newShift.shiftDate + "','yyyy/MM/dd'), " + newShift.taskID + ", " + newShift.animalID + ")";
                try {
                    statement.executeQuery(sqlQuery);
                    statement.executeQuery("commit");
                } catch (SQLException ex) {
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("You are clocked in!");
                alert.showAndWait();

            });
        }
        clockout.setOnAction(e -> {
            String sqlQuery = "update shifts set timeout = '" + timeNow + "'"
                    + " where volunteerid = " + loggedInVolID + " and shiftdate = TO_DATE('" + todaysDate + "','yyyy/MM/dd')";
            try {
                statement.executeQuery(sqlQuery);
                statement.executeQuery("commit");
            } catch (SQLException ex) {
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You are clocked out!");
            alert.showAndWait();
        });

        addBackButton();
    }

    public void logTask() throws SQLException {
        ComboBox<String> cboLocation = new ComboBox<>();
        TextField txtMileage = new TextField();
        ComboBox<Integer> maxVol = new ComboBox<>();
        maxVol.getItems().addAll(1, 2, 3, 4, 5);
        ComboBox<String> cboDescription = new ComboBox<>();

        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        try {
            String query = "Select distinct location from tasks";
            ResultSet rsLocation = statement.executeQuery(query);
            while (rsLocation.next()) {
                cboLocation.getItems().add(rsLocation.getString("location"));
            }
        } catch (SQLException ex) {
        }
        try {
            String query = "Select distinct description from tasks";
            ResultSet rsTasks = statement.executeQuery(query);
            while (rsTasks.next()) {
                cboDescription.getItems().add(rsTasks.getString("description"));
            }
        } catch (SQLException ex) {
        }

        refreshCenterPane(centerPane);

        pane.setTop(heading("Create A New Task"));
        Button submitEvent = new Button("Submit Task!");
        cboLocation.setEditable(true);
        cboDescription.setEditable(true);

        centerPane.add(labelText("Description:"), 0, 0);
        centerPane.add(cboDescription, 1, 0);
        centerPane.add(labelText("Location:"), 0, 1);
        centerPane.add(cboLocation, 1, 1);
        centerPane.add(labelText("Mileage:"), 0, 2);
        centerPane.add(txtMileage, 1, 2);
        centerPane.add(labelText("Maximum Volunteers"), 0, 3);
        centerPane.add(maxVol, 1, 3);
        centerPane.add(submitEvent, 1, 4);
        submitEvent.setStyle(buttonStyle);
        addBackButton();
        String query = "Select * from tasks";
        try {
            try (ResultSet rsEvents = statement.executeQuery(query)) {
                while (rsEvents.next()) { //get database data
                    Task dbTask = new Task();
                    dbTask.setTaskID(rsEvents.getInt(1));
                    dbTask.setDescription(rsEvents.getString(1));
                    dbTask.setLocation(rsEvents.getString(2));
                    dbTask.setMileage(rsEvents.getString(4));
                    dbTask.setMaxVolunteers(rsEvents.getInt(5));
                }
            }
        } catch (SQLException ex) {
        }

        submitEvent.setOnAction((ActionEvent e) -> {
            //give error if information isn't complete
            if (cboLocation.getValue() == null || txtMileage.getText().isEmpty() || maxVol.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please Enter All Task Information");
                alert.showAndWait();
            } else {
                try {
                    Task submittedTask = new Task(
                            cboDescription.getValue(),
                            cboLocation.getValue(),
                            txtMileage.getText(),
                            maxVol.getValue()
                    );
                    System.out.println(submittedTask);
                    String insert = "INSERT INTO TASKS (TaskID, Description, Location, Mileage, MaxVolunteers) VALUES (" + submittedTask.taskID + ", '"
                            + cboDescription.getValue() + "', '" + cboLocation.getValue() + "', '" + txtMileage.getText() + "', "
                            + maxVol.getValue() + ")";
                    statement.execute(insert);
                    statement.execute("commit");
                    //clear text fields after submission
                    cboLocation.setValue(null);
                    txtMileage.setText("");
                    cboDescription.setValue(null);
                    maxVol.setValue(null);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that task was submitted
                    alert.setHeaderText("Task Created, \n"
                            + "Thank You For Your Help!");
                    alert.showAndWait();
                } catch (SQLException ex) {

                }
            }
        }
        );

        pane.setCenter(centerPane);

    }

    public void reports() {
        refreshCenterPane(centerPane);

        pane.setTop(heading("VIEW REPORT"));

        toolBar.setOrientation(VERTICAL);
        toolBar.setBackground(new Background(new BackgroundFill(
                Color.DARKSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        pane.setLeft(toolBar);

        report1.setOnAction(e -> {
            try {
                reportBuilder(1);

            } catch (Exception ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        report2.setOnAction(e -> {
            try {
                reportBuilder(2);

            } catch (Exception ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        report3.setOnAction(e -> {
            try {
                reportBuilder(3);

            } catch (Exception ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        report4.setOnAction(e -> {
            try {
                reportBuilder(4);

            } catch (Exception ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        addBackButton();

        pane.setCenter(subHeading("No reports displayed"));
    }

    public void reportBuilder(int selection) {
        try {
            String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
            OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
            ds.setURL(connectionString);
            Connection con = ds.getConnection("javauser", "javapass");
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ObservableList<String> list = FXCollections.observableArrayList();

            TilePane tilePane = new TilePane();
            tilePane.setVgap(5);

            if (selection == 1) {
                list.clear();
                ResultSet reportRS = statement.executeQuery("select FirstName, LastName, "
                        + "Hours from application, volunteers where "
                        + "application.applicationid = volunteers.applicationid order by lastname desc");
                while (reportRS.next()) {
                    for (int i = 0; i < 3; i++) {
                        list.add(i, reportRS.getString(i + 1));
                    }
                }
                tilePane.setPrefColumns(3);
                tilePane.getChildren().add(subHeading("First Name"));
                tilePane.getChildren().add(subHeading("Last Name"));
                tilePane.getChildren().add(subHeading("Hours"));
                pane.setTop(heading("Hours per Volunteer"));
            }
            if (selection == 2) {
                list.clear();
                ResultSet reportRS = statement.executeQuery("select * from attendance order by eventid desc");
                while (reportRS.next()) {
                    for (int i = 0; i < 2; i++) {
                        list.add(i, reportRS.getString(i + 1));
                    }
                }
                tilePane.setPrefColumns(2);
                tilePane.getChildren().add(subHeading("Event ID"));
                tilePane.getChildren().add(subHeading("Volunteer ID"));
                pane.setTop(heading("Volunteer Attendance"));
            }
            if (selection == 3) {
                list.clear();
                ResultSet reportRS = statement.executeQuery("select volunteerid, specialization from volunteers order by volunteerid desc");
                while (reportRS.next()) {
                    for (int i = 0; i < 2; i++) {
                        list.add(i, reportRS.getString(i + 1));
                    }
                }
                tilePane.setPrefColumns(2);
                tilePane.getChildren().add(subHeading("Volunteer"));
                tilePane.getChildren().add(subHeading("Specialization"));
                pane.setTop(heading("Volunteer Specialization"));
            }
            if (selection == 4) {
                list.clear();
                ResultSet reportRS = statement.executeQuery("select firstname, "
                        + "lastname, phone, email from application inner join "
                        + "volunteers on application.applicationid = volunteers.applicationid");
                while (reportRS.next()) {
                    for (int i = 0; i < 4; i++) {
                        list.add(i, reportRS.getString(i + 1));
                    }
                }
                tilePane.setPrefColumns(4);
                tilePane.getChildren().add(subHeading("First Name"));
                tilePane.getChildren().add(subHeading("Last Name"));
                tilePane.getChildren().add(subHeading("Phone Number"));
                tilePane.getChildren().add(subHeading("Email"));
                pane.setTop(heading("Volunteer Contact Information"));
            }

            refreshCenterPane(centerPane);

            int records = list.size();

            for (int i = 0; i < records; i++) {
                tilePane.getChildren().add(labelText(list.get(i)));
            }

            centerPane.add(tilePane, 0, 0);
            pane.setCenter(centerPane);

        } catch (SQLException e) {
        }
    }

    public void applicationApproval() throws SQLException {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        refreshCenterPane(centerPane);

        //Button saveButton = new Button("Save");
        saveButton.setStyle(buttonStyle);
        ObservableList status = FXCollections.observableArrayList("Approved", "Denied");
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        TableView tableView = new TableView();
        ComboBox<String> statusCB = new ComboBox<>();
        ComboBox<String> appList = new ComboBox<>();
        statusCB.setItems(status);

        pane.setTop(heading("Approve or Deny Applications"));
        //Label lblSelection = new Label("Make Selection");
        Label lblApplicant = new Label("Applicant \t");
        Label lblStatus = new Label("Approve or Deny \t");

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(lblApplicant, appList);

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(lblStatus, statusCB);

        String selectSql = "select * from volunteers";
        try (ResultSet rsVolunteers = statement.executeQuery(selectSql)) { //get all volunteers from db table
            while (rsVolunteers.next()) {
                Volunteer dbVolunteer = new Volunteer();
                dbVolunteer.setVolunteerID(rsVolunteers.getInt(1));
                dbVolunteer.setTitle(rsVolunteers.getString(2));
                dbVolunteer.setSpecialization(rsVolunteers.getString(3));
                dbVolunteer.setHours(rsVolunteers.getDouble(4));
                dbVolunteer.setAppID(rsVolunteers.getInt(5));
            }
        } catch (SQLException ex) {
        }
        try { //trying to get the applicant names for the application combobox
            String query = "Select applicationid, firstName, lastName, phone, email, experience, status"
                    + " from application a"
                    + " where status = 'Pending'";
            ResultSet rs = statement.executeQuery(query);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableView.getColumns().addAll(col);
            }
            while (rs.next()) {
                int appID = rs.getInt(1);
                String firstName = rs.getString(2); //getting first name from application table
                String lastName = rs.getString(3); //getting last name from application table
                String appInfo = appID + " " + firstName + " " + lastName; //combining into one string to add to the combobox
                appList.getItems().add(appInfo); //populate combo box for applications
                saveButton.setOnAction(e -> { //when save button is clicked
                    try {
                        //code to update the application status when button is clicked
                        String selectedAppliation = appList.getSelectionModel().getSelectedItem();
                        String[] info = selectedAppliation.split(" ");
                        String id = info[0];
                        String selectedDecision = statusCB.getSelectionModel().getSelectedItem();
                        String sql = "update application set status= '" + selectedDecision + "' "
                                + "where applicationID= " + id;
                        statement.executeQuery(sql);
                        if (selectedDecision.equals("Approved")) { //if the application status is changed to approved
                            //Create new volunteer object
                            Volunteer newVolunteer = new Volunteer("Volunteer in Training", "None", 0.0, Integer.valueOf(id));
                            // System.out.println(newVolunteer);
                            String sqlQuery = "insert into volunteers (volunteerid, title, specialization, hours, applicationid)"
                                    + " values (" + newVolunteer.volunteerID + ", '" + newVolunteer.title + "', '"
                                    + newVolunteer.specialization + "', " + newVolunteer.hours + ", " + newVolunteer.appID + ")";
                            statement.executeQuery(sqlQuery);
                            statement.executeQuery("commit");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText("Application Accepted");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Application Denied");
                            alert.showAndWait();
                        }
                    } catch (SQLException ex) {
                    }
                }); //getting the db data into the table view
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);
        } catch (SQLException ex) {
        }
        centerPane.add(hbox1, 0, 1);
        centerPane.add(hbox2, 0, 2);
        centerPane.add(saveButton, 0, 4);
        centerPane.add(tableView, 0, 5);
        addBackButton();
        pane.setCenter(centerPane);
    }

    public void viewAnimals() throws SQLException {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        Button updateStatus = new Button("Update Status");
        Button addAnimal = new Button("Add Animal");
        refreshCenterPane(centerPane);
        tableHome.getItems().clear();

        pane.setTop(heading("Current Animals"));
        

        HBox bottom = new HBox();
        bottom.getChildren().addAll(addAnimal, updateStatus);
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(saveButton);

        // Inital page
        String selectSql = "select * from animals";
        try (ResultSet rsAnimals = statement.executeQuery(selectSql)) { //get all animals from db
            while (rsAnimals.next()) {
                Animal dbAnimals = new Animal();
                dbAnimals.setAnimalID(rsAnimals.getInt(1));
                dbAnimals.setName(rsAnimals.getString(2));
                dbAnimals.setSpecies(rsAnimals.getString(3));
                dbAnimals.setBreed(rsAnimals.getString(4));
                dbAnimals.setAge(rsAnimals.getInt(5));
                dbAnimals.setGender(rsAnimals.getString(6));
                dbAnimals.setWeight(rsAnimals.getInt(7));
                dbAnimals.setStatus(rsAnimals.getString(8));
            }
        } catch (SQLException ex) {
        }

        try {
            // populating table with all animals
            String query = "Select * from animals";
            ResultSet rs = statement.executeQuery(query);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableHome.getColumns().addAll(col);
            }

            while (rs.next()) {
                int AnimalID = rs.getInt(1);
                String name = rs.getString(2); // getting first name from animals table
                String species = rs.getString(3); // getting species from animals table
                String breed = rs.getString(4); // getting breed from animals table
                int age = rs.getInt(5); // getting age from animals table
                String gender = rs.getString(6); // getting gender from animals table
                int weight = rs.getInt(7); // getting weight from animals table
                String Status = rs.getString(8); // getting status from animals table
                String animalInfo = AnimalID + " " + name + " " + species + " "
                        + breed + " " + age + " " + gender + " "
                        + weight + " " + Status; //combining into one string to add to the combobox

                cboAnimal.getItems().add(name); // populate combobox for animals

                //getting the db data into the table view
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);

            }
            tableHome.setItems(data);
        } catch (SQLException ex) {
        }
        //  refreshCenterPane(centerPane);

        // 'Add Animal' Button
        // takes you to addAnimals()
        addAnimal.setOnAction(e -> {
            try {
                addAnimals();
            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        // 'Update Animal' Button 
        // takes you to updateAnimal()
        updateStatus.setOnAction(e -> {
            try {
                updateAnimal();
            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });

        bottom.setAlignment(Pos.CENTER);

        bottom.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER);

        centerPane.add(tableHome, 0, 1);
        centerPane.add(bottom, 0, 3);
        pane.setCenter(centerPane);

        addBackButton();
        // 'Add Animal' Button
        // takes you to addAnimals()
        addAnimal.setOnAction(e -> {
            try {
                addAnimals();
            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        // 'Update Animal' Button 
        // takes you to updateAnimal()
        updateStatus.setOnAction(e -> {
            try {
                updateAnimal();
            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void addAnimals() throws SQLException {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        refreshCenterPane(centerPane);

        ComboBox<String> cboSpecies = new ComboBox<>();
        ComboBox<String> cboGender = new ComboBox<>();
        TextField txtAge = new TextField();
        TextField txtWeight = new TextField();
        TextField txtName = new TextField(); 
        TextField txtBreed = new TextField();
        TextField txtStatus = new TextField("Evaluating for adoption");
        
        Label lblName = new Label("Enter Animal Name");
        Label lblSpecies = new Label("Enter Species");
        Label lblBreed = new Label("Enter Animal Breed");
        Label lblAge = new Label("Enter Animal Age");
        Label lblWeight = new Label("Enter Animal Weight (lbs)");
        Label lblGender = new Label("Enter Gender");
        Label lblStatus = new Label("Animal Status");
        cboGender.getItems().addAll("Male", "Female");
       
        pane.setTop(heading("Add a New Animal"));
        centerPane.add(lblName, 0, 2);
        centerPane.add(txtName, 1, 2);
        centerPane.add(lblSpecies, 0, 3);
        centerPane.add(cboSpecies, 1, 3);
        centerPane.add(lblBreed, 0, 4);
        centerPane.add(txtBreed, 1, 4);
        centerPane.add(lblAge, 0, 5);
        centerPane.add(txtAge, 1, 5);
        centerPane.add(lblGender, 0, 6);
        centerPane.add(cboGender, 1, 6);
        centerPane.add(lblWeight, 0, 7);
        centerPane.add(txtWeight, 1, 7);
        centerPane.add(lblStatus, 0, 8);
        centerPane.add(txtStatus, 1, 8);
        centerPane.add(saveButton, 0, 10);
        pane.setBottom(backAnimals);

        saveButton.setOnAction(ee -> {
            //give error if any fields are empty
            if (txtName.getText().isEmpty() || cboSpecies.getValue() == null || txtBreed.getText().isEmpty() || txtAge.getText() == null
                    || cboGender.getValue() == null || txtWeight.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please Enter All Animal Information");
                alert.showAndWait();
            } else {
                try {
                    Animal submittedAnimal = new Animal( //create new animal
                            txtName.getText(),
                            cboSpecies.getValue(),
                            txtBreed.getText(),
                            Integer.valueOf(txtAge.getText()),
                            cboGender.getValue(),
                            Integer.valueOf(txtWeight.getText()),
                            txtStatus.getText()
                    );
                    String insert = "Insert into Animals (animalID, Name, Species, Breed, Age, Gender, Weight, Status) VALUES (" + submittedAnimal.animalID + ", '"
                            + txtName.getText() + "', '" + cboSpecies.getValue() + "', '" + txtBreed.getText() + "', "
                            + txtAge.getText() + ", '" + cboGender.getValue() + "', " + txtWeight.getText() + ", '" + txtStatus.getText() + "')";
                    statement.execute(insert);
                    statement.execute("commit");

                    //clear text fields after submission
                    txtName.setText("");
                    cboSpecies.setValue(null);
                    txtBreed.setText("");
                    txtAge.setText("");
                    cboGender.setValue(null);
                    txtWeight.setText("");
                    txtStatus.setText("");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that application was submitted
                    alert.setHeaderText("Animal Added To System!");
                    alert.showAndWait();

                } catch (SQLException ex) {
                    Logger.getLogger(capstoneRedo2.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void updateAnimal() throws SQLException {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        // controlls
        Button updateA = new Button("Update Animal Status");
        TextField name = new TextField();
        TextField spec = new TextField();
        TextField breed = new TextField();
        TextField age = new TextField();
        TextField gender = new TextField();
        TextField weight = new TextField();

        //  TextField id = new TextField();
        name.setEditable(false);
        spec.setEditable(false);
        breed.setEditable(false);
        age.setEditable(false);
        gender.setEditable(false);
        weight.setEditable(false);
        //  id.setEditable(false);
        ComboBox<String> animal = new ComboBox<>();
        TextField status = new TextField();
        status.setEditable(false);

        updateA.setStyle(buttonStyle);
        try {
            String query = "Select distinct status from animals where status ='Ready for adoption'";
            ResultSet rsSpecializations = statement.executeQuery(query);
            while (rsSpecializations.next()) {
                status.setText(rsSpecializations.getString("status"));
            }
        } catch (SQLException ex) {
        }

        ResultSet rsEval = statement.executeQuery("select animalId, Name from Animals where status= 'Evaluating for adoption'");
        while (rsEval.next()) {
            int aId = rsEval.getInt(1);
            String aName = rsEval.getString(2);
            String animalInfo = "ID: " + aId + " \nName: " + aName;
            animal.getItems().add(animalInfo);

            refreshCenterPane(centerPane);

            pane.setTop(heading("Ready For Adoption Update"));
            //animal.setItems(animalInfo);
            VBox root = new VBox();
            HBox top = new HBox();
            top.getChildren().addAll(labelText("Select Animal Being Evaluated: \t"), animal);
            HBox hbox = new HBox();
            hbox.getChildren().addAll(labelText("Updated Status: \t"), status);

            updateA.setOnAction(e -> {
                try {
                    //code to update sql database when button is clicked
                    String selectedAnimal = animal.getSelectionModel().getSelectedItem();
                    String[] info = selectedAnimal.split(" ");
                    String id = info[1];
                    String sql = "update animals set status= '" + status.getText() + "' "
                            + "where animalID = " + id;
                    statement.executeQuery(sql);

                } catch (SQLException ex) {
                    Logger.getLogger(capstoneRedo2.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that application was submitted
                alert.setHeaderText("Animal Is Ready For Adoption!");
                alert.showAndWait();
            });

            HBox bottom = new HBox();
            bottom.getChildren().add(updateA);

            root.getChildren().addAll(top, hbox, bottom);
            hbox.setSpacing(10);
            root.setSpacing(10);
            centerPane.add(root, 0, 0);
            centerPane.setHgap(10.0);
            centerPane.setVgap(10.0);
            pane.setBottom(backAnimals);
            pane.setCenter(centerPane);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
