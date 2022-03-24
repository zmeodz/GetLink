/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.sun.javafx.application.PlatformImpl;
import entities.Profile;
import entities.User;
import impl.ProfileDaoImpl;
import impl.UserDaoImpl;
import interfaces.BaseDao;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author TheDuc
 */
public class SignInController extends Application implements Initializable {

    ToggleGroup group = new ToggleGroup();

    Stage stage;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtEmail;

    @FXML
    private RadioButton rdMale;

    @FXML
    private ToggleGroup tgGender;

    @FXML
    private RadioButton rdFemale;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnComplete;

    @FXML
    private TextField txtPhone;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtReEnterPassword;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker dpBirthday;

    @FXML
    private Button btnBack;

    public String getTxtUsername() {
        return txtUsername.getText();
    }

    public String getTxtFirstname() {
        return txtFirstname.getText();
    }

    public String getTxtLastname() {
        return txtLastname.getText();
    }

    public String getTxtEmail() {
        return txtEmail.getText();
    }

    public String getTxtPhone() {
        return txtPhone.getText();
    }

    public String getTxtAddress() {
        return txtAddress.getText();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SignIn.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SignIn Account");
        stage.show();
        this.stage = stage;
    }

    @FXML
    void handleButtonActionCancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Would You Like To Cancel Your Console Output?");
        alert.setContentText("Please choose an option.");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            System.exit(0);
        } else {
            event.consume();
        }
    }

    @FXML
    void handleButtonActionComplete(ActionEvent event) {
        if (validate("First Name", getTxtFirstname(), "[a-zA-Z]+") 
                && validate("User Name", getTxtUsername(), "[a-zA-Z]+")
                &&validate("Phone", getTxtPhone(), "[0-9]*")
                && validate("Last Name", getTxtLastname(), "[a-zA-Z]+")
                && emptyValidation("DOB", dpBirthday.getEditor().getText().isEmpty())) {
            String pass_new = DigestUtils.sha1Hex((txtPassword.getText().trim()));
            String retypepass = DigestUtils.sha1Hex((txtReEnterPassword.getText().trim()));
            if (validate("Email", getTxtEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
                        && emptyValidation("Password", pass_new.isEmpty()) && newPassword(pass_new, retypepass))
            {
                int i = (int) (new Date().getTime() / 1000);
                UserDaoImpl user1 = new UserDaoImpl();
                if (user1.testUserAdd(getTxtUsername())) 
                {
                    User uss = new User(getTxtUsername(), pass_new, i, i, true);
                    user1.insert(uss);
                    LocalDate lcaldate = dpBirthday.getValue();
                    Date da = Date.from(lcaldate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                Date da = java.sql.Date.valueOf(lcaldate);
                    String GT = rdMale.isSelected() ? "Male" : "Famale";
                    Profile pf = new Profile(uss.getId(), getTxtFirstname(), getTxtLastname(), getTxtPhone(), getTxtPhone(), da, getTxtEmail(), GT, i, i, true);
                    BaseDao profile1 = new ProfileDaoImpl();
                    profile1.insert(pf);
                    showAlert("Succes", "Account succesfully created!");
                    resetFieldUser();
                } else 
                    showAlert("Confirmation Dialog", "retype username  ");               
            }
        }
    }

    @FXML
    void Back(ActionEvent event) {
        try {
            PlatformImpl.startup(() -> {
                try {
                    LoginController menu = new LoginController();
                    menu.start(new Stage());
                    stage.close();
                } catch (Exception e) {
                    System.out.println("Cant load new window");
                }
            });
            this.stage.close();

        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean newPassword(String newPassword, String retypePassword) {
        if (newPassword.equals(retypePassword)) {
            return true;
        } else {
            showAlert("Confirmation Dialog", "password retype not match");
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (empty) {
            alert.setContentText("Please Enter " + field);
        } else {
            alert.setContentText("Please Enter Valid " + field);
        }
        alert.showAndWait();
    }

    private boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    void resetFieldUser() {
        txtFirstname.setText("");
        txtLastname.setText("");
        txtUsername.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtReEnterPassword.setText("");
        dpBirthday.getEditor().clear();
    }
}
