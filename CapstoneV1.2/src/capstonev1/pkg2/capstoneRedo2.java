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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    Button applicationBackButton = new Button("Back");

    String fontStyle = "garamond";

    String buttonStyle = "-fx-background-color: #2F4F4F; -fx-text-fill: #FFFFFF;"
            + "-fx-font-family: \"" + fontStyle + "\";";
    String reportButtonStyle = "-fx-background-color: #66CDAA; -fx-text-fill: #FFFFFF;"
            + "-fx-font-family: \"" + fontStyle + "\";";

    public void start(Stage primaryStage) {
        pane.setTop(heading("WELCOME TO THE BARK DATABASE"));
        welcomeModule();

        BorderPane.setMargin(centerPane, new Insets(30, 30, 30, 30));

        pane.setBackground(new Background(new BackgroundFill(
                Color.MEDIUMAQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

        Button report1 = new Button("Report 1");
        Button report2 = new Button("Report 2");
        Button report3 = new Button("Report 3");
        report1.setStyle(reportButtonStyle);
        report2.setStyle(reportButtonStyle);
        report3.setStyle(reportButtonStyle);

        toolBar.getItems().addAll(report1, report2, report3);

        centerPane.setHgap(10.0);

        Scene scene = new Scene(pane, 400, 500); //Create a scene
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

    public void welcomeModule() {

        pane.getChildren().remove(applicationBackButton);
        refreshCenterPane(centerPane);

        TextField userNameTF = new TextField();
        TextField passwordTF = new TextField();
        Button loginButton = new Button("Login");
        Button appButton = new Button("Volunteer Application");

        loginButton.setStyle(buttonStyle);
        appButton.setStyle(buttonStyle);

        loginButton.setOnAction(e -> homeScreen());
        appButton.setOnAction(e -> applicationScreen());

        centerPane.add(subHeading("VOLUNTEER LOG IN"), 0, 0);
        centerPane.add(labelText("User Name"), 0, 1);
        centerPane.add(labelText("Password"), 0, 2);
        centerPane.add(userNameTF, 1, 1);
        centerPane.add(passwordTF, 1, 2);
        centerPane.add(loginButton, 1, 3);

        centerPane.add(subHeading("\n\n\nNEW VOLUNTEERS"), 0, 10);
        centerPane.add(appButton, 0, 11);

        GridPane.setMargin(loginButton, new Insets(5));
        GridPane.setMargin(appButton, new Insets(5));
        pane.setCenter(centerPane);

    }

    public void refreshCenterPane(GridPane gP) {
        gP.getChildren().clear();
    }

    public void applicationScreen() {
        refreshCenterPane(centerPane);

        pane.setTop(heading("Volunteer Application"));

        TextField applicationFirstName = new TextField();
        TextField applicationLastName = new TextField();
        DatePicker applicationDOB = new DatePicker();
        TextField applicationEmail = new TextField();
        TextField applicationPhone = new TextField();
        TextField applicationAddress = new TextField();
        ComboBox<String> applicationExperience = new ComboBox<>();
        ObservableList<String> experienceList = FXCollections.observableArrayList("Novice", "Intermediate", "Expert");

        Button applicationSubmit = new Button("Submit Application");
        applicationSubmit.setStyle(buttonStyle);

        applicationBackButton.setStyle(buttonStyle);
        applicationBackButton.setOnAction(e -> welcomeModule());

        centerPane.add(subHeading("Complete all Fields"), 0, 0);
        centerPane.add(labelText("First Name:"), 0, 1);
        centerPane.add(labelText("Last Name:"), 0, 2);
        centerPane.add(labelText("Birth Date:"), 0, 3);
        centerPane.add(labelText("Phone Number:"), 0, 4);
        centerPane.add(labelText("Email"), 0, 5);
        centerPane.add(labelText("Address:"), 0, 6);
        centerPane.add(labelText("Experience:"), 0, 7);

        centerPane.add(applicationFirstName, 1, 1);
        centerPane.add(applicationLastName, 1, 2);
        centerPane.add(applicationDOB, 1, 3);
        centerPane.add(applicationPhone, 1, 4);
        centerPane.add(applicationEmail, 1, 5);
        centerPane.add(applicationAddress, 1, 6);
        centerPane.add(applicationExperience, 1, 7);
        centerPane.add(applicationSubmit, 1, 10);
        applicationExperience.setItems(experienceList);

        applicationSubmit.setOnAction(e -> {
            DBConnection conn = new DBConnection();
            String query = "Select * from application";
            try {
                conn.sendDBCommand(query);
                while (conn.dbResults.next()) { //get database data
                    App dbApp = new App();
                    dbApp.setAppID(conn.dbResults.getInt(1));
                    dbApp.setAFirst(conn.dbResults.getString(2));
                    dbApp.setALast(conn.dbResults.getString(3));
                    dbApp.setDOB(conn.dbResults.getString(4));
                    dbApp.setPhone(conn.dbResults.getString(5));
                    dbApp.setEmail(conn.dbResults.getString(6));
                    dbApp.setAddress(conn.dbResults.getString(7));
                    dbApp.setExperience(conn.dbResults.getString(8));
                }
                conn.dbResults.close();
            } catch (SQLException ex) {
            }
            //give error if any fields are empty
            if (applicationFirstName.getText().isEmpty() || applicationLastName.getText().isEmpty() || applicationDOB.getValue() == null
                    || applicationPhone.getText().isEmpty() || applicationEmail.getText().isEmpty() || applicationAddress.getText().isEmpty()
                    || applicationExperience.getValue().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter all data");
                alert.showAndWait();
            } else {
                App submittedApp = new App( //create new application 
                        applicationFirstName.getText(),
                        applicationLastName.getText(),
                        applicationDOB.getValue().format(DateTimeFormatter.ISO_DATE),
                        applicationPhone.getText(),
                        applicationEmail.getText(),
                        applicationAddress.getText(),
                        applicationExperience.getValue());
                String insert = "INSERT INTO APPLICATION (ApplicationID,FirstName,LastName,DOB,phone,email,address,experience) VALUES (" + submittedApp.appID + ", '"
                        + applicationFirstName.getText() + "', '" + applicationLastName.getText() + "', TO_DATE('" + applicationDOB.getValue() + "','yyyy-mm-dd'), '" + applicationPhone.getText() + "', '"
                        + applicationEmail.getText() + "', '" + applicationAddress.getText() + "', '" + applicationExperience.getValue() + "')";
                conn.sendDBCommand(insert);
                String commit = "commit";
                conn.sendDBCommand(commit);

                //clear text fields after submission
                applicationFirstName.setText("");
                applicationLastName.setText("");
                applicationDOB.setValue(null);
                applicationPhone.setText("");
                applicationEmail.setText("");
                applicationAddress.setText("");
                applicationExperience.setValue("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that application was submitted
                alert.setHeaderText("Application Submitted!");
                alert.showAndWait();
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

        volunteerCheckInButton.setStyle(buttonStyle);
        volunteerCheckOutButton.setStyle(buttonStyle);
        reportsButton.setStyle(buttonStyle);
        socialButton.setStyle(buttonStyle);
        logEventButton.setStyle(buttonStyle);
        assignSpecButton.setStyle(buttonStyle);

        refreshCenterPane(centerPane);

        centerPane.add(subHeading("Please Choose From Menu"), 0, 0);
        centerPane.add(volunteerCheckInButton, 0, 1);
        centerPane.add(volunteerCheckOutButton, 0, 2);
        centerPane.add(reportsButton, 0, 3);
        centerPane.add(socialButton, 0, 4);
        centerPane.add(logEventButton, 0, 5);
        centerPane.add(assignSpecButton, 0, 6);

        volunteerCheckInButton.setOnAction(e -> {
            try {
                volunteerCheckIO("In");
            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        volunteerCheckOutButton.setOnAction(e -> {
            try {
                volunteerCheckIO("Out");
            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        logEventButton.setOnAction(e -> {
            try {
                logEvent();
            } catch (SQLException ex) {
                Logger.getLogger(capstoneRedo2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        reportsButton.setOnAction(e -> reports());
        assignSpecButton.setOnAction(e -> assignSpec());

        centerPane.setVgap(8);
        pane.setCenter(centerPane);

    }

    public void assignSpec() {
        DBConnection conn = new DBConnection();
        refreshCenterPane(centerPane);

        Button assignSpecButton = new Button("Submit Specialization");
        assignSpecButton.setStyle(buttonStyle);

        pane.setTop(heading("Assign a Specialization"));

        centerPane.add(subHeading("Make Selection"), 0, 0);
        centerPane.add(labelText("Volunteer:"), 0, 1);
        centerPane.add(labelText("Specialization:"), 0, 2);
        ComboBox<String> specializations = new ComboBox<>();
        ComboBox<Volunteer> volunteersList = new ComboBox<>();
        try {
            String query = "Select distinct specialization from volunteers";
            conn.sendDBCommand(query);
            while (conn.dbResults.next()) {
                specializations.getItems().add(conn.dbResults.getString("specialization"));
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
                        "10:00 A.M",
                        "11:00 A.M",
                        "12:00 A.M",
                        "1:00 P.M",
                        "2:00 P.M",
                        "3:00 P.M",
                        "4:00 P.M",
                        "5:00 P.M"
                );

        ComboBox<String> cboTimein = new ComboBox<>(Times);
        ComboBox<String> cboTasks = new ComboBox<>();
        ComboBox<String> cboLocation = new ComboBox<>();
        ComboBox<String> cboTimeout = new ComboBox<>(Times);

        pane.setTop(heading("Volunteer Check " + type));

        if (type.equals("In")) {
            centerPane.add(labelText("Available Tasks: "), 0, 0);
            centerPane.add(labelText("Check-In Location: "), 0, 1);
            centerPane.add(labelText("Time In: "), 0, 2);
            centerPane.add(cboTasks, 1, 0);
            centerPane.add(cboLocation, 1, 1);
            centerPane.add(cboTimein, 1, 2);
        } else if (type.equals("Out")) {
            centerPane.add(labelText("Event: "), 0, 0);
            centerPane.add(labelText("Time Out: "), 0, 1);
            centerPane.add((new ComboBox<Object>()), 1, 0);
            centerPane.add(cboTimeout, 1, 1);
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

        // maybe add a "New Locations" button
        ComboBox<String> cboLocation = new ComboBox<>();
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        Connection con = ds.getConnection("javauser", "javapass");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rsLocation = statement.executeQuery("select distinct location from Events");
        while (rsLocation.next()) {
            cboLocation.getItems().add(rsLocation.getString(1));

            final TextField txtMileage = new TextField();
            Button addEvent = new Button("Add New");
            Label lblOr = new Label("---Or Add New Event Location---");
            cboLocation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue ov, Object t, Object t1) {
                    switch (t1.toString()) {
                        case "Charlottesville":
                            txtMileage.setText("62"); // also try and populate miles txtField from db
                            break;
                        case "Luray":
                            txtMileage.setText("33");
                            break;
                        case "Lynchburg":
                            txtMileage.setText("97");
                            break;
                        case "Richmond":
                            txtMileage.setText("130");
                            break;
                        case "Washington":
                            txtMileage.setText("132");
                            break;
                    }
                }
            });

            refreshCenterPane(centerPane);

            pane.setTop(heading("LOG EVENT"));
            Button submitEvent = new Button("Submit Event!");

            centerPane.add(labelText("Location:"), 0, 0);
            centerPane.add(labelText("Mileage:"), 0, 1);
            centerPane.add(cboLocation, 1, 0);
            centerPane.add(txtMileage, 1, 1);
            centerPane.add(submitEvent, 0, 2);
            centerPane.add(lblOr, 1, 5);
            centerPane.add(addEvent, 1, 7);
            submitEvent.setStyle(buttonStyle);
            addEvent.setStyle(buttonStyle);
            addBackButton();

            addEvent.setOnAction((ActionEvent e) -> {
                refreshCenterPane(centerPane);
                TextField txtNewLoc = new TextField();
                TextField txtNewMileage = new TextField();
                TextField txtNewTask = new TextField();
                Button btnSubmitNew = new Button("Submit New Event!");
                final ComboBox<Integer> cboMaxV = new ComboBox<Integer>();
                cboMaxV.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

                pane.setTop(heading("ADD NEW EVENT"));
                centerPane.add(labelText("Location:"), 0, 0);
                centerPane.add(txtNewLoc, 1, 0);
                centerPane.add(labelText("Mileage"), 0, 1);
                centerPane.add(txtNewMileage, 1, 1);
                centerPane.add(labelText("Task:"), 0, 2);
                centerPane.add(txtNewTask, 1, 2);
                centerPane.add(labelText("Maximum Volunteers:"), 0, 3);
                centerPane.add(cboMaxV, 1, 3);
                centerPane.add(btnSubmitNew, 0, 4);
                btnSubmitNew.setStyle(buttonStyle);

                addBackButton(); // can add previous button to get back to existing locations

            });

            submitEvent.setOnAction((ActionEvent e) -> {
                DBConnection conn = new DBConnection();
                String query = "Select location, mileage from events";
                try {
                    conn.sendDBCommand(query);
                    while (conn.dbResults.next()) { //get database data
                        Event dbEvent = new Event();
     //                   dbEvent.setEventID(conn.dbResults.getInt(1));
                        dbEvent.setLocation(conn.dbResults.getString(1));
                        dbEvent.setMileage(conn.dbResults.getString(2));
//                    dbEvent.setTask(conn.dbResults.getString(4));
//                    dbEvent.setMaxVolunteers(conn.dbResults.getInt(5));

                    }
                    conn.dbResults.close();
                } catch (SQLException ex) {
                }
                //give error if information isn't complete
                if (txtMileage.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Please enter all data");
                    alert.showAndWait();
                } else {
//                
//                Event submittedEvent = new Event( //create new event
//                        cboLocation.getValue(),
//                        txtMileage.getText()
//                        );
//                String insert = "INSERT INTO EVENTS (EventID, Location, Mileage) VALUES (" + submittedEvent.EventID + ", '"
//                        + cboLocation.getValue() + "', '" + txtMileage.getText() + "')";
//                conn.sendDBCommand(insert);
//                String commit = "commit";
//                conn.sendDBCommand(commit);
//
//                //clear text fields after submission
//                cboLocation.setValue(null);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //give alert that application was submitted
                    alert.setHeaderText("Event Logged, \n"
                            + "Thank You For Your Help!");
                    alert.showAndWait();
                }
            });
        }
    }

    public void reports() {
        refreshCenterPane(centerPane);

        pane.setTop(heading("VIEW REPORT"));

        toolBar.setOrientation(VERTICAL);
        toolBar.setBackground(new Background(new BackgroundFill(
                Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        pane.setLeft(toolBar);

        addBackButton();

        TableView reportTV = new TableView();

        pane.setCenter(reportTV);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
