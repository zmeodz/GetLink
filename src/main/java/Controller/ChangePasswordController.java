package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import hibernate.HibernateUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author TheDuc
 */
public class ChangePasswordController extends Application implements Initializable {

    @FXML
    private PasswordField pwdNewPassword;

    @FXML
    private PasswordField pwdCurrent;

    @FXML
    private PasswordField pwdRePassword;


    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session;
    /**
     * Initializes the controller class.
     */
    Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public void update(String currentPassword, String newPassword, String retypePassword) {
        if (LoginController.currentUser.getPassword().equals(currentPassword)) {
            if(newPassword.equals(retypePassword))
            {
                session = sessionFactory.openSession();
                LoginController.currentUser.setPassword(newPassword);
                session.beginTransaction();
                session.update(LoginController.currentUser);
                session.getTransaction().commit();
                session.close();
                showAlert("Confirmation Dialog", "Sussces Password");
                stage.close();
            }
            else
            {
                showAlert("Confirmation Dialog", "password retype not match");
            }
        }
        else
        {
                showAlert("Confirmation Dialog", "Wrong Password");
        }
    }
    @FXML
    void updatePassword(ActionEvent event) {
        String currentPass = DigestUtils.sha1Hex((pwdCurrent.getText().trim()));
        String newPassword = DigestUtils.sha1Hex((pwdNewPassword.getText().trim()));
        String retypePass = DigestUtils.sha1Hex((pwdRePassword.getText().trim()));
        update(currentPass, newPassword, retypePass);
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChangePassword.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Change Password");
        stage.show();
        this.stage = stage;
    }
}
