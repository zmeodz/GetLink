/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Image;
import entities.Website;
import impl.ImageDaoImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TheDuc
 */
public class ImageHistoryController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    private final ObservableList<Image> dataImage = FXCollections.observableArrayList();
    
    Website website;
    
    Stage stage;
    
    public ImageHistoryController(Website website) {
        this.website = website;
    }

    @FXML
    private TableView<Image> tableImage;

    @FXML
    private TableColumn<Image, String> colUrl;


    @FXML
    private TableColumn<Image, Integer> colIdImage;
    
    @FXML
    private TableColumn<Image, Boolean> colStatus;
    

    @FXML
    private TableColumn<Image, Integer> colDateCreateImage;

    @FXML
    private TableColumn<Image, Integer> colDateUpdateImage;
    
    @FXML
    private TableColumn<Image, Integer> colIdWebsite;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadImageDetails();
        setColumnProperties();
    }
    private void loadImageDetails() {
        dataImage.clear();
        ImageDaoImpl u = new ImageDaoImpl();
        dataImage.addAll(u.getAllImageById(website.getId()));
        tableImage.setItems(dataImage);
    }    
    private void setColumnProperties() {
        colIdImage.setCellValueFactory(new PropertyValueFactory<>("idHistory"));
        colIdWebsite.setCellValueFactory(new PropertyValueFactory<>("idWebsite"));
        colUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDateCreateImage.setCellValueFactory(new PropertyValueFactory<>("createAt"));  
        colDateUpdateImage.setCellValueFactory(new PropertyValueFactory<>("updateAt"));  
    }

    @Override
    public void start(Stage stage) throws IOException  {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ImageHistory.fxml"));

//        Parent root;
//        try {
//            root = (Parent) loader.load();
//            //stage.setResizable(false);
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            //stage.initStyle(StageStyle.UNDECORATED);
//
//            stage.setTitle("MENU");
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        loader.setController(this);
        Parent root = (Parent) loader.load();
        //stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        //scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Image History");
        stage.show();
        this.stage = stage;
    }
}
