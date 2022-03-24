/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.sun.javafx.application.PlatformImpl;
import entities.History;
import entities.Website;
import impl.HistoryDaoImpl;
import impl.WebsiteDaoImpl;
import interfaces.BaseDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TheDuc
 */
public class HistoryWebsiteController extends Application implements Initializable {
    Website website;
    
    Stage stage;
    
    public HistoryWebsiteController(Website website) {
        this.website = website;
    }
    private final ObservableList<History> dataHistory = FXCollections.observableArrayList();
    @FXML
    private TableView<History> tableHistory;

    @FXML
    private TableColumn<History, String> colTitleHistoryWebsite;

    @FXML
    private TableColumn<History, Integer> colIdHistory;

    @FXML
    private TableColumn<History, Boolean> colStatus;

    @FXML
    private TableColumn<History, String> colDateCreate;

    @FXML
    private TableColumn<History, String> colDateUpdate;

    @FXML
    private MenuItem Link;

    @FXML
    private MenuItem Image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadHistoryDetails();
       setColumnProperties();
    }
    
    private void setColumnProperties() {
        colTitleHistoryWebsite.setCellValueFactory(new PropertyValueFactory<>("idWebsite"));
        colIdHistory.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDateCreate.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        colDateUpdate.setCellValueFactory(new PropertyValueFactory<>("updateAt"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void loadHistoryDetails() {
        dataHistory.clear();
        HistoryDaoImpl u = new HistoryDaoImpl();
        dataHistory.addAll(u.getWebsiteById(website.getId()));
        tableHistory.setItems(dataHistory);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HistoryWebsite.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("History Website");
        stage.show();
        this.stage = stage;
    }
    @FXML
    void historyLink(ActionEvent event){
            History history1 = tableHistory.getSelectionModel().getSelectedItem();
            try {
                PlatformImpl.startup(() -> {
                    try {
                        LinkByIdHistoryController history = new LinkByIdHistoryController(history1, website);
                        history.start(new Stage());
                    } catch (IOException ex) {
                        Logger.getLogger(LinkByIdHistoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (Exception e) {
                System.out.println("Cant load new window");
            }
    }
    @FXML
    void historyImage(ActionEvent event){
        History history1 = tableHistory.getSelectionModel().getSelectedItem();
        try {
                PlatformImpl.startup(() -> {
                    try {
                        ImageByIdHistoryController history = new ImageByIdHistoryController(history1, website);
                        history.start(new Stage());
                    } catch (IOException ex) {
                        Logger.getLogger(ImageByIdHistoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (Exception e) {
                System.out.println("Cant load new window");
            }
    }
}
