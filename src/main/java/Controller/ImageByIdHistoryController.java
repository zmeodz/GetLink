/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.History;
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
public class ImageByIdHistoryController extends Application implements Initializable {

    public ImageByIdHistoryController(History history, Website website) {
        this.history = history;
        this.website = website;
    }
    private final ObservableList<Image> dataImage = FXCollections.observableArrayList();
    History history;
    
    Website website;

    Stage stage;

    @FXML
    private TableView<Image> tableImage;

    @FXML
    private TableColumn<Image, Integer> colIdWebsite;

    @FXML
    private TableColumn<Image, String> colUrl;

    @FXML
    private TableColumn<Image, Integer> colIdImage;

    @FXML
    private TableColumn<Image,Boolean> colStatus;

    @FXML
    private TableColumn<Image, String> colDateCreateImage;

    @FXML
    private TableColumn<Image, String> colDateUpdateImage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadImageDetails();
        setColumnProperties();
    }

    private void setColumnProperties() {
        colIdWebsite.setCellValueFactory(new PropertyValueFactory<>("idWebsite"));
        colUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
        colIdImage.setCellValueFactory(new PropertyValueFactory<>("idHistory"));
        colDateCreateImage.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        colDateUpdateImage.setCellValueFactory(new PropertyValueFactory<>("updateAt"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ImageByIdHistory.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ImageByIdHistory");
        stage.show();
        this.stage = stage;
    }
    private void loadImageDetails() {
        dataImage.clear();
        ImageDaoImpl u = new ImageDaoImpl();
        dataImage.addAll(u.getImageByIdHistory(history.getId(), website.getId()));
        tableImage.setItems(dataImage);
    }
}
