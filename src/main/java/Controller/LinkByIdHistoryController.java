/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.History;
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
public class LinkByIdHistoryController extends Application implements Initializable {

    public LinkByIdHistoryController(History history, Website website) {
        this.history = history;
        this.website = website;
    }

    History history;
    
    Website website;
    Stage stage;
    
    private final ObservableList<Link> dataLink = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Link> tableLink;

    @FXML
    private TableColumn<Link, Integer> colIdWebsite;

    @FXML
    private TableColumn<Link, Integer> colIdLink;

    @FXML
    private TableColumn<Link, String> colUrl;

    @FXML
    private TableColumn<Link, Boolean> colStatus;

    @FXML
    private TableColumn<Link, String> colDateCreateLink;

    @FXML
    private TableColumn<Link, String> colDateUpdateLink;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setColumnProperties();
        loadLinkDetails();
    }

    private void setColumnProperties() {
        colIdWebsite.setCellValueFactory(new PropertyValueFactory<>("idWebsite"));
        colIdLink.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDateCreateLink.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        colDateUpdateLink.setCellValueFactory(new PropertyValueFactory<>("updateAt"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LinkByIdHistory.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("LinkByIdHistory");
        stage.show();
        this.stage = stage;
    }
    private void loadLinkDetails() {
        dataLink.clear();
        LinkDaoImpl u = new LinkDaoImpl();
        dataLink.addAll(u.getLinkByIdHistory(history.getId() ,website.getId()));
        tableLink.setItems(dataLink);
    }
}
