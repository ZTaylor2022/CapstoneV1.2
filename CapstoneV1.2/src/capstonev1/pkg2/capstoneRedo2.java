package capstonev1.pkg2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

import java.util.Set;

import java.util.ArrayList;

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
    Button logEventBackButton = new Button("Back");
    Button backAnimals = new Button("Back");

    String fontStyle = "garamond";

    String buttonStyle = "-fx-background-color: #2F4F4F; -fx-text-fill: #FFFFFF;"
            + "-fx-font-family: \"" + fontStyle + "\";";
    String reportButtonStyle = "-fx-background-color: #66CDAA; -fx-text-fill: #FFFFFF;"
            + "-fx-font-family: \"" + fontStyle + "\";";

    Button report1 = new Button("Volunteer Hours");
    Button report2 = new Button("Event Attendance");
    Button report3 = new Button("Volunteer \nSpecialization");
    Button report4 = new Button("Volunteer \nContact \nInformation");

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

            try {
                String user = userNameTF.getText();
                String pass = passwordTF.getText();
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
            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });

        backAnimals.setOnAction(e -> {
            try {
                viewAnimals();
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

        Button volunteerCheckInButton = new Button("Volunteer Check In");
        Button volunteerCheckOutButton = new Button("Volunteer Check Out");
        Button reportsButton = new Button("Reports Page");
        Button socialButton = new Button("Social Page");
        Button logEventButton = new Button("Log Event");
        Button assignSpecButton = new Button("Assign Specialization");
        Button applicationApproval = new Button("View Pending Applications");
        Button btnAnimals = new Button("View Animals");

        volunteerCheckInButton.setStyle(buttonStyle);
        volunteerCheckOutButton.setStyle(buttonStyle);
        reportsButton.setStyle(buttonStyle);
        socialButton.setStyle(buttonStyle);
        logEventButton.setStyle(buttonStyle);
        assignSpecButton.setStyle(buttonStyle);
        applicationApproval.setStyle(buttonStyle);
        btnAnimals.setStyle(buttonStyle);

        refreshCenterPane(centerPane);

        centerPane.add(subHeading("Please Choose From Menu"), 0, 0);
        centerPane.add(volunteerCheckInButton, 0, 1);
        centerPane.add(volunteerCheckOutButton, 0, 2);
        centerPane.add(reportsButton, 0, 3);
        centerPane.add(socialButton, 0, 4);
        centerPane.add(logEventButton, 0, 5);
        centerPane.add(assignSpecButton, 0, 6);
        centerPane.add(applicationApproval, 0, 7);
        centerPane.add(btnAnimals, 0, 8);

        volunteerCheckInButton.setOnAction(e -> {
            try {
                volunteerCheckIO("In");

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        volunteerCheckOutButton.setOnAction(e -> {
            try {
                volunteerCheckIO("Out");

            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        logEventButton.setOnAction(e -> {
            try {
                logEvent();

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
    public void volunteerCheckIO(String type) throws SQLException {
        refreshCenterPane(centerPane);

        ObservableList<String> Times
                = FXCollections.observableArrayList(
                        "9:00 A.M",
                        "9:15 A.M",
                        "9:30 A.M",
                        "9:45 A.M",
                        "10:00 A.M",
                        "10:15 A.M",
                        "10:30 A.M",
                        "10:45 A.M",
                        "11:00 A.M",
                        "11:15 A.M",
                        "11:30 A.M",
                        "11:45 A.M",
                        "12:00 P.M",
                        "12:15 P.M",
                        "12:30 P.M",
                        "12:45 P.M",
                        "1:00 P.M",
                        "1:15 P.M",
                        "1:30 P.M",
                        "1:45 P.M",
                        "2:00 P.M",
                        "2:15 P.M",
                        "2:30 P.M",
                        "2:45 P.M",
                        "3:00 P.M",
                        "3:15 P.M",
                        "3:30 P.M",
                        "3:45 P.M",
                        "4:00 P.M",
                        "4:15 P.M",
                        "4:30 P.M",
                        "4:45 P.M",
                        "5:00 P.M"
                );

        ComboBox<String> cboTimein = new ComboBox<>(Times);
        ComboBox<String> cboTasks = new ComboBox<>();
        ComboBox<String> cboLocation = new ComboBox<>();
        ComboBox<String> cboTimeout = new ComboBox<>(Times);
        TextField tothours = new TextField();
        Button clockin = new Button("Check in");
        clockin.setStyle(buttonStyle);
        Button submithours = new Button("Check Out");
        submithours.setStyle(buttonStyle);

        pane.setTop(heading("Volunteer Check " + type));

        if (type.equals("In")) {
            centerPane.add(labelText("Available Tasks: "), 0, 0);
            centerPane.add(labelText("Check-In Location: "), 0, 1);
            centerPane.add(labelText("Time In: "), 0, 2);
            centerPane.add(cboTasks, 1, 0);
            centerPane.add(cboLocation, 1, 1);
            centerPane.add(cboTimein, 1, 2);
            centerPane.add(clockin, 0, 7);

            clockin.setOnAction(e -> {

                Alert userPrompt = new Alert(Alert.AlertType.NONE,
                        "You have checked in, Return to Home Screen when you wish to check out.",
                        ButtonType.OK);
                userPrompt.show();
            });

        } else if (type.equals("Out")) {
            centerPane.add(labelText("Time Out: "), 0, 0);
            centerPane.add(labelText("Enter total hours: "), 0, 1);
            centerPane.add(cboTimeout, 1, 0);
            centerPane.add(tothours, 1, 1);
            centerPane.add(submithours, 0, 5);

            submithours.setOnAction(e -> {
                tothours.clear();

                Alert userPrompt = new Alert(Alert.AlertType.NONE,
                        "Your hours have been logged!",
                        ButtonType.OK);
                userPrompt.show();
            });

        }

        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rsTasks = statement.executeQuery("select distinct Task from Events");
        while (rsTasks.next()) {
            cboTasks.getItems().add(rsTasks.getString(1));
        }

        ResultSet rsLocation = statement.executeQuery("select distinct Location from Events");
        while (rsLocation.next()) {
            cboLocation.getItems().add(rsLocation.getString(1));
        }

        addBackButton();
    }

    public void logEvent() throws SQLException {
        ComboBox<String> cboLocation = new ComboBox<>();
        TextField txtMileage = new TextField();
        ComboBox<Integer> cboMaxV = new ComboBox<>();
        cboMaxV.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ComboBox<String> cboTask = new ComboBox<>();

        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        try {
            String query = "Select distinct location from events";
            ResultSet rsLocation = statement.executeQuery(query);
            while (rsLocation.next()) {
                cboLocation.getItems().add(rsLocation.getString("location"));
            }
        } catch (SQLException ex) {
        }
        try {
            String query = "Select distinct task from events";
            ResultSet rsTasks = statement.executeQuery(query);
            while (rsTasks.next()) {
                cboTask.getItems().add(rsTasks.getString("task"));
            }
        } catch (SQLException ex) {
        }

//        cboLocation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

//            @Override
//            public void changed(ObservableValue ov, Object t, Object t1) {
//                switch (t1.toString()) {
//                    case "":
//                        txtMileage.setText(" ");
//                        break;
//                    case "Charlottesville":
//                        txtMileage.setText("62");
//                        break;
//                    case "Luray":
//                        txtMileage.setText("33");
//                        break;
//                    case "Lynchburg":
//                        txtMileage.setText("97");
//                        break;
//                    case "Richmond":
//                        txtMileage.setText("130");
//                        break;
//                    case "Washington":
//                        txtMileage.setText("132");
//                        break;
//                }
//            }
//        });

        refreshCenterPane(centerPane);

        pane.setTop(heading("LOG EVENT"));
        Button submitEvent = new Button("Submit Event!");
        cboLocation.setEditable(true);
        cboTask.setEditable(true);

        centerPane.add(labelText("Location:"), 0, 0);
        centerPane.add(cboLocation, 1, 0);
        centerPane.add(labelText("Mileage:"), 0, 1);
        centerPane.add(txtMileage, 1, 1);
        centerPane.add(labelText("Task:"), 0, 2);
        centerPane.add(cboTask, 1, 2);
        centerPane.add(labelText("Maximum Volunteers"), 0, 3);
        centerPane.add(cboMaxV, 1, 3);
        centerPane.add(submitEvent, 1, 4);
        submitEvent.setStyle(buttonStyle);
        addBackButton();
        String query = "Select * from events";
        try {
            try (ResultSet rsEvents = statement.executeQuery(query)) {
                while (rsEvents.next()) { //get database data
                    Event dbEvent = new Event();
                    dbEvent.setEventID(rsEvents.getInt(1));
                    dbEvent.setLocation(rsEvents.getString(1));
                    dbEvent.setMileage(rsEvents.getString(2));
                    dbEvent.setTask(rsEvents.getString(4));
                    dbEvent.setMaxVolunteers(rsEvents.getInt(5));
                }
            }
        } catch (SQLException ex) {
        }
        
        submitEvent.setOnAction((ActionEvent e) -> {
            //give error if information isn't complete
            if (cboLocation.getValue() == null || txtMileage.getText().isEmpty() || cboMaxV.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter all event information");
                alert.showAndWait();
            } else {
                try {
                    Event submittedEvent = new Event(
                            cboLocation.getValue(),
                            txtMileage.getText(),
                            cboTask.getValue(),
                            cboMaxV.getValue()
                    );
                    //System.out.println(submittedEvent);
                    String insert = "INSERT INTO EVENTS (EventID, Location, Mileage, Task, MaxVolunteers) VALUES (" + submittedEvent.eventID + ", '"
                            + cboLocation.getValue() + "', '" + txtMileage.getText() + "', '" + cboTask.getValue() + "', '"
                            + cboMaxV.getValue() + "')";
                    statement.execute(insert);
                    statement.execute("commit");

                    //clear text fields after submission
                    cboLocation.setValue(null);
                    txtMileage.setText("");
                    cboTask.setValue(null);
                    cboMaxV.setValue(null);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that application was submitted
                    alert.setHeaderText("Event Logged, \n"
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

        Button saveButton = new Button("Save");
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

        refreshCenterPane(centerPane);
        // top buttons
        Button updateStatus = new Button("Update Status");
        Button checkIn = new Button("Check In / Out");
        Button addAnimal = new Button("Add Animal");
        Button saveButton = new Button("Save");
        saveButton.setStyle(buttonStyle);
        ObservableList status = FXCollections.observableArrayList("Ready For Adoption", "Evaluating");
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        TableView tableHome = new TableView();
        ComboBox<String> cboStatus = new ComboBox<>();
        ComboBox<String> cboAnimal = new ComboBox<>();
        ComboBox<String> cboSpecies = new ComboBox<>();
        cboSpecies.getItems().addAll("Dog", "Cat", "Rabbit", "Fish", "Lizard", "Snake", "Hamster", "Ferret", "Chicken", "Pig");
        cboSpecies.setEditable(true);
        ComboBox<String> cboGender = new ComboBox<>();
        cboGender.getItems().addAll("Male", "Female");
        Label lblName = new Label("Enter Animal Name");
        TextField txtName = new TextField();
        Label lblSpecies = new Label("Select Species");
        Label lblBreed = new Label("Enter Animal Breed");
        TextField txtBreed = new TextField();
        Label lblAge = new Label("Enter Animal Age");
        ComboBox<Integer> cboAge = new ComboBox<>();
        cboAge.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50);
        Label lblWeight = new Label("Enter Animal Weight (lbs)");
        ComboBox<Integer> cboWeight = new ComboBox<>();
        cboWeight.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
                71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
                81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
                91, 92, 93, 94, 95, 96, 97, 98, 99, 100);
        cboWeight.setEditable(true);
        Label lblGender = new Label("Enter Gender");
        Label lblStatus = new Label("Animal Status");
        TextField txtStatus = new TextField("Evaluating for adoption");
        txtStatus.setEditable(false);
        cboStatus.setItems(status);

        pane.setTop(heading("Current Animals"));

        HBox top = new HBox();
        top.getChildren().addAll(addAnimal, updateStatus, checkIn);
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(saveButton);

        // Adding a new animal action
        addAnimal.setOnAction(e -> {
            
            refreshCenterPane(centerPane);
            pane.setTop(heading("Add a New Animal"));
            centerPane.add(lblName, 0, 2);
            centerPane.add(txtName, 1, 2);
            centerPane.add(lblSpecies, 0, 3);
            centerPane.add(cboSpecies, 1, 3);
            centerPane.add(lblBreed, 0, 4);
            centerPane.add(txtBreed, 1, 4);
            centerPane.add(lblAge, 0, 5);
            centerPane.add(cboAge, 1, 5);
            centerPane.add(lblGender, 0, 6);
            centerPane.add(cboGender, 1, 6);
            centerPane.add(lblWeight, 0, 7);
            centerPane.add(cboWeight, 1, 7);
            centerPane.add(lblStatus, 0, 9);
            centerPane.add(txtStatus, 1, 9);
            centerPane.add(saveButton, 0, 10);
            pane.setBottom(backAnimals);
            saveButton.setOnAction(ee -> {
            //give error if any fields are empty
            if (txtName.getText().isEmpty() || cboSpecies.getValue() == null || txtBreed.getText().isEmpty() ||cboAge.getValue() == null
                    || cboGender.getValue() == null || cboWeight.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please Enter All Animal Information");
                alert.showAndWait();
            } else {
 //               try {
//                    Animal submittedAnimal = new Animal( //create new animal
//                            txtName.getText(),
//                            cboSpecies.getValue(),
//                            txtBreed.getText(),
//                            cboAge.getValue(),
//                            cboGender.getValue(),
//                            cboWeight.getValue(),
//                            txtStatus.getText()
//                    );
//                    String insert = "Insert into Animals (animalID,Name,Species,BReed,Age,Gender,Weight,Status) VALUES (" + submittedAnimal.animalID + ", '"
//                            + txtName.getText() + "', '" + cboSpecies.getValue() + "', '" + txtBreed.getText() + "', '"
//                            + applicationEmail.getText() + "', '" + applicationPassword.getText() + "', '" + applicationAddress.getText() + "', '" + applicationExperience.getValue() + "', '"
//                            + submittedApp.getStatus() + "')";
//                    statement.execute(insert);
//                    statement.execute("commit");

                    //clear text fields after submission
                    txtName.setText("");
                    cboSpecies.setValue(null);
                    txtBreed.setText("");
                    cboAge.setValue(null);
                    cboGender.setValue(null);
                    cboWeight.setValue(null);
                    txtStatus.setText("Evaluating for adoption");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that application was submitted
                    alert.setHeaderText("Application Submitted!");
                    alert.showAndWait();

//                } catch (SQLException ex) {
//                    Logger.getLogger(capstoneRedo2.class
//                            .getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });
                
            });
            
            pane.setBottom(backAnimals);

        
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

        try { // populating table with all animals
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
                String age = rs.getString(5); // getting age from animals table
                String gender = rs.getString(6); // getting gender from animals table
                String weight = rs.getString(7); // getting weight from animals table
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
        refreshCenterPane(centerPane);

        top.setAlignment(Pos.CENTER);

        top.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER);

        centerPane.add(top, 0, 1);
        centerPane.add(tableHome, 0, 3);
        pane.setCenter(centerPane);

        addBackButton();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
