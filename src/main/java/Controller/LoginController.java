package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.javafx.application.PlatformImpl;
import impl.UserDaoImpl;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author TheDuc
 */
public class LoginController extends Application implements Initializable {

    Stage stage;
    
    public static User currentUser = null;
    
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField pwdPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignin;

    @FXML
    private void handleButtonActionLogIn(ActionEvent event) {
        System.out.println(this.stage);
        String username = txtUser.getText();
        String password = DigestUtils.sha1Hex((pwdPass.getText().trim()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Login");
        alert.setHeaderText(null);
        
        if (!username.isEmpty() && !password.isEmpty()) {
            //thanh cong {     
            UserDaoImpl login = new UserDaoImpl();
            currentUser = login.getByUsernameAndPassword(username, password);
            if (currentUser != null) {
                alert.setContentText("Login success!");
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    System.out.println("Ok pressed");
                    try {
                        PlatformImpl.startup(() -> {
                            try {
                                MenuController menu = new MenuController();
                                menu.start(new Stage());
                            } catch (IOException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        this.stage.close();

                    } catch (Exception e) {
                        System.out.println("Cant load new window");
                    }
                } else {
                    System.out.println("canceled");
                }
            } else {
                alert.setContentText("Login false");
                alert.showAndWait();
            }
        } else {
            alert.setContentText("Username or Password is null!");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleButtonActionSignIn(ActionEvent event) {

        try {
            PlatformImpl.startup(() -> {
                try {
                    SignInController menu = new SignInController();
                    menu.start(new Stage());
                    stage.close();
                } catch (Exception ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            this.stage.close();

        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        //stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        //scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Login Account");
        stage.show();
        this.stage = stage;
    }
}
