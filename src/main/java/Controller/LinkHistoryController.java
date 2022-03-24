/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Link;
import entities.Website;
import impl.LinkDaoImpl;
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
public class LinkHistoryController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
private final ObservableList<Link> dataLink = FXCollections.observableArrayList();
    
    Website website;
    
    Stage stage;
    
    public LinkHistoryController(Website website) {
        this.website = website;
    }

    @FXML
    private TableView<Link> tableLink;

    @FXML
    private TableColumn<Link, String> colUrl;

    @FXML
    private TableColumn<Link, Integer> colIdLink;
    
    @FXML
    private TableColumn<Link, Boolean> colStatus;
    

    @FXML
    private TableColumn<Link, Integer> colDateCreateLink;

    @FXML
    private TableColumn<Link, Integer> colDateUpdateLink;
    
    @FXML
    private TableColumn<Link, Integer> colIdWebsite;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadLinkDetails();
        setColumnProperties();
    }
    private void loadLinkDetails() {
        dataLink.clear();
        LinkDaoImpl u = new LinkDaoImpl();
        dataLink.addAll(u.getAllLinkById(website.getId()));
        tableLink.setItems(dataLink);
    }    
    private void setColumnProperties() {
        colIdLink.setCellValueFactory(new PropertyValueFactory<>("idHistory"));
        colIdWebsite.setCellValueFactory(new PropertyValueFactory<>("idWebsite"));
        colUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDateCreateLink.setCellValueFactory(new PropertyValueFactory<>("createAt"));  
        colDateUpdateLink.setCellValueFactory(new PropertyValueFactory<>("updateAt"));  
    }

    @Override
    public void start(Stage stage) throws IOException  {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LinkHistory.fxml"));

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
        stage.setTitle("Link History");
        stage.show();
        this.stage = stage;
    } 
    
}
