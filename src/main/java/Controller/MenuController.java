/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.sun.javafx.application.PlatformImpl;
import entities.History;
import entities.Image;
import entities.Link;
import entities.Profile;
import entities.User;
import entities.Website;
import hibernate.HibernateUtil;
import impl.HistoryDaoImpl;
import impl.ImageDaoImpl;
import impl.LinkDaoImpl;
import impl.ProfileDaoImpl;
import impl.UserDaoImpl;
import impl.WebsiteDaoImpl;
import interfaces.BaseDao;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.hibernate.SessionFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * FXML Controller class
 *
 * @author TheDuc
 */
public class MenuController extends Application implements Initializable {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Website website;
    private User user;
    ImageDaoImpl imaimpl = new ImageDaoImpl();
    LinkDaoImpl linkimpl = new LinkDaoImpl();
    private final ObservableList<Website> dataWebsite = FXCollections.observableArrayList();
    private final ObservableList<Profile> dataProfile = FXCollections.observableArrayList();

//    private final String status = "";
    private int row = 0;

    private int solan = 0;

    private int count = 0;

    private final int countduration = 1;

    Stage stage;

    @FXML
    private TableView<Website> homeTableView;

    @FXML
    private TableColumn<Website, Integer> colIdHome;

    @FXML
    private TableColumn<Website, String> colTitleWebsiteHome;

    @FXML
    private TableColumn<Website, String> colUrlWebsiteHome;

    @FXML
    private TableColumn<Website, Integer> colStatusHome;

    @FXML
    private TableColumn<Website, Integer> colDateCreateHome;

    @FXML
    private TableColumn<Website, Integer> colDateUpdateHome;

    @FXML
    private TableColumn<Website, Integer> colDurationWebsiteHome;

    @FXML
    private TextField txtUrlWebsite;

    @FXML
    private Spinner<Integer> sprDuration;

    @FXML
    private TableView<Website> websiteTable;

    @FXML
    private TableColumn<Website, Integer> colIdWebsite;

    @FXML
    private TableColumn<Website, String> colTitleWebsite;

    @FXML
    private TableColumn<Website, String> colUrlWebsite;

    @FXML
    private TableColumn<Website, Boolean> colStatusWebsite;

    @FXML
    private TableColumn<Website, Integer> colDateCreateWebsite;

    @FXML
    private TableColumn<Website, Integer> colDateUpdateWebsite;

    @FXML
    private TableColumn<Website, Integer> colDurationWebiste;

    @FXML
    private TableView<Website> websiteTable1;

    @FXML
    private TableColumn<Website, Integer> colIdWebsite1;

    @FXML
    private TableColumn<Website, String> colTitleWebsite1;

    @FXML
    private TableColumn<Website, String> colUrlWebsite1;

    @FXML
    private TableColumn<Website, Boolean> colStatusWebsite1;

    @FXML
    private TableColumn<Website, Integer> colDateCreateWebsite1;

    @FXML
    private TableColumn<Website, Integer> colDateUpdateWebsite1;

    @FXML
    private TableColumn<Website, Integer> colDurationWebiste1;

    @FXML
    private Button btnSaveWebsite;

    @FXML
    private Button btnEditWebsite;

    @FXML
    private Button btnNewWebiste;

    @FXML
    private Button btnNewUser;

    @FXML
    private CheckBox chkWorking;

    @FXML
    private TextField txtTitleWebsite;

    @FXML
    private TableView<Profile> userTable;

    @FXML
    private TableColumn<Profile, Integer> colUserId;

    @FXML
    private TableColumn<Profile, String> colFirstname;

    @FXML
    private TableColumn<Profile, String> colLastname;

    @FXML
    private TableColumn<Profile, Date> colDOB;

    @FXML
    private TableColumn<Profile, String> colGender;

    @FXML
    private TableColumn<Profile, String> colAddress;

    @FXML
    private TableColumn<Profile, String> colPhone;

    @FXML
    private TableColumn<Profile, String> colEmail;

    @FXML
    private TableColumn<Profile, Boolean> colEdit;

    @FXML
    private Button btnGetLink;

    @FXML
    private Button btnPause;

    @FXML
    private Label userId;

    @FXML
    private Label userName;

    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private DatePicker txtDob;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnChangePassword;

    @FXML
    private Button btnSaveUser;

    @FXML
    private Button User;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCancel;

    @FXML
    private RadioButton rdImage;

    @FXML
    private ToggleGroup tgKind;

    @FXML
    private RadioButton rdLink;

    @FXML
    private Button btnReload;

    @FXML
    private PasswordField txtPassword;

    private String getGenderTitle(String gender) {
        return (gender.equals("Male")) ? "his" : "her";
    }

    public String getFirstName() {
        return txtFirstname.getText();
    }

    public String getLastName() {
        return txtLastname.getText();
    }

    public Date getDob() {
        LocalDate lcaldate = txtDob.getValue();
        Date da = Date.from(lcaldate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return da;
//        return txtDob.getValue();
    }

    public String getGender() {
        return rbMale.isSelected() ? "Male" : "Female";
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getPassword() {
        return txtPassword.getText();
    }

    public String getAddress() {
        return txtAddress.getText();
    }

    public String getPhone() {
        return txtPhone.getText();
    }

    public String getTitleWebsite() {
        return txtTitleWebsite.getText();
    }

    public String getUrlWebsite() {
        return txtUrlWebsite.getText();
    }

    @FXML
    void newUser(ActionEvent event) {
        setFieldDisableProfile(false);
        resetFieldUser();
    }

    @FXML
    void deleteUsers(ActionEvent event) {
        List<Profile> profile = userTable.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();
        BaseDao proimpl = new ProfileDaoImpl();
        ProfileDaoImpl u1 = new ProfileDaoImpl();
        UserDaoImpl userimpl = new UserDaoImpl();
        if (action.get() == ButtonType.OK) {
            for (Profile i : profile) {
                if (LoginController.currentUser.getId() == i.getIdUser()) {
                    try {
                        PlatformImpl.startup(() -> {
                            try {
                                LoginController menu = new LoginController();
                                menu.start(new Stage());
                                stage.close();
                            } catch (IOException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        this.stage.close();

                    } catch (Exception e) {
                        System.out.println("Cant load new window");
                    }
                    proimpl.getById(i.getId());
                    userimpl.DeleteUserById(i.getIdUser());
                    u1.delete(i.getId());
                } else {
                    proimpl.getById(i.getId());
                    userimpl.DeleteUserById(i.getIdUser());
                    u1.delete(i.getId());
                    loadProfileDetails();
                }
            }
        }
    }

    @FXML
    void deleteWebsites(ActionEvent event) {
        List<Website> web = websiteTable.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();
        BaseDao u1 = new WebsiteDaoImpl();
        LinkDaoImpl link1 = new LinkDaoImpl();
        ImageDaoImpl image1 = new ImageDaoImpl();
        HistoryDaoImpl history1 = new HistoryDaoImpl();
        if (action.get() == ButtonType.OK) {
            for (Website i : web) {
                u1.delete(i.getId());
                link1.DeleteHistoryById(i.getId());
                image1.DeleteHistoryById(i.getId());
                history1.DeleteHistoryById(i.getId());
                loadWebsiteDetails();
            }
        }
    }

    @FXML
    void editWebsite(ActionEvent event) {
        setFieldDisableWebiste(false);
        btnUpdate.setVisible(true);
        btnNewWebiste.setVisible(false);
        btnEditWebsite.setVisible(false);
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    ScheduledExecutorService a;

    @FXML
    void getLinkAll(ActionEvent event) {
        btnPause.setVisible(true);
        btnGetLink.setVisible(false);
        BaseDao u = new WebsiteDaoImpl();
        List<Website> us = u.getAll();
        System.out.println(us.size());
        a = Executors.newScheduledThreadPool(us.size());
        a.scheduleWithFixedDelay(new MyWorker(), 0, 1, TimeUnit.MINUTES);
    }

    public class MyWorker implements Runnable {

        @Override
        public void run() {
            System.out.println("Meo con");
            BaseDao u = new WebsiteDaoImpl();
            List<Website> us = u.getAll();
            for (Website t : us) {
                if (count % t.getDuration() == 0 && t.getStatus() == true) {
                    try {
                        new Thread(() -> {
                            try {
                                getLink(t.getUrl(), t.getId());
                            } catch (IOException ex) {
                                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }) {
                            {
                                start();
                            }
                        }.join();
                    } catch (InterruptedException ex) {
                        System.out.println("Can get all link");
                    }
                }
            }
            count++;
        }
    }

    @FXML
    void logout(ActionEvent event) {
        LoginController.currentUser = null;
        try {
            PlatformImpl.startup(() -> {
                try {
                    LoginController menu = new LoginController();
                    menu.start(new Stage());
                    stage.close();
                } catch (IOException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            this.stage.close();
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    @FXML
    void newWebsite(ActionEvent event) {
        btnSaveWebsite.setVisible(true);
        btnNewWebiste.setVisible(false);
        btnEditWebsite.setVisible(false);
        setFieldDisableWebiste(false);
        setFieldReset();
    }

    @FXML
    void pauseAllGetLink(ActionEvent event) throws InterruptedException {
        btnGetLink.setVisible(true);
        btnPause.setVisible(false);
        BaseDao u = new WebsiteDaoImpl();
        List<Website> us = u.getAll();
        System.out.println(us.size());
        Thread one = new Thread() {
            public void run() {
                try {
                    a.shutdown();
                    a.awaitTermination(1, TimeUnit.MINUTES);
                } catch (InterruptedException ex) {
                    System.out.println("Cant get link");
                }
            }
        };
        one.start();
        System.out.println("Finished all threads");
    }

    @FXML
    void resetFieldUser() {
        txtFirstname.setText("");
        txtLastname.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }

    @FXML
    void saveUser(ActionEvent event) {
        int i = (int) (new Date().getTime() / 1000);
        if (validate("First Name", getFirstName(), "[a-zA-Z]+")
                && validate("Phone", getPhone(), "[0-9]*")
                && validate("Last Name", getLastName(), "[a-zA-Z]+")
                && emptyValidation("DOB", txtDob.getEditor().getText().isEmpty())
                && validate("Email", getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
            ProfileDaoImpl loadProfile = new ProfileDaoImpl();
            Profile profile1 = loadProfile.getById(Integer.parseInt(userId.getText()));
            profile1.setFirstName(getFirstName());
            profile1.setLastName(getLastName());
            profile1.setBirthday(getDob());
            profile1.setGender(getGender());
            updateAlert(profile1);
            profile1.setEmail(getEmail());
            profile1.setCreateAt(profile1.getCreateAt());
            profile1.setUpdateAt(i);
            profile1.setStatus(true);
            loadProfile.update(profile1);
            clearProfileFields();
            loadProfileDetails();
            setFieldDisableProfile(true);
        }
    }

    @FXML
    void changePasswordUser(ActionEvent event) {
        try {
            PlatformImpl.startup(() -> {
                try {
                    ChangePasswordController menu = new ChangePasswordController();
                    menu.start(new Stage());
                } catch (IOException ex) {
                    Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    @FXML
    void saveWebsite(ActionEvent event) {
        if (emptyValidation("Url Website", txtUrlWebsite.getText().isEmpty())
                && emptyValidation("Title Website", txtTitleWebsite.getText().isEmpty()) && emptyValidation("Duration", sprDuration.getEditor().getText().isEmpty())) {
            if (isValidURL(txtUrlWebsite.getText())) {
                int i = (int) (new Date().getTime() / 1000);
                BaseDao websiteimpl = new WebsiteDaoImpl();
                Boolean ST = chkWorking.isSelected() ? true : false;
                website = new Website(txtUrlWebsite.getText(), txtTitleWebsite.getText(), Integer.valueOf(sprDuration.getEditor().getText()), i, i, ST);
                websiteimpl.insert(website);
                loadWebsiteDetails();
                loadHomeDetails();
                loadWebsite1Details();
                setFieldDisableWebiste(true);
                setFieldReset();
                loadWebsiteDetails();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Error");
                alert.setHeaderText(null);
                alert.setContentText("Please Type URL");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void tableWebsiteClick(MouseEvent event) {
        if (MouseButton.PRIMARY == event.getButton()) {
            website = websiteTable.getSelectionModel().getSelectedItem();
            btnEditWebsite.setVisible(true);
            txtUrlWebsite.setText(website.getUrl());
            txtTitleWebsite.setText(website.getTitle());
            sprDuration.getEditor().setText(String.valueOf(website.getDuration()));
            row = websiteTable.getSelectionModel().getSelectedIndex();
            if (website.getStatus()) {
                chkWorking.setSelected(true);
            } else {
                chkWorking.setSelected(false);
            }
            int k = website.getDuration();
            setFieldDisableWebiste(true);
            initSpinner(k);
        }
    }

    @FXML
    void tableWebsite1Click(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            Website website1 = websiteTable1.getSelectionModel().getSelectedItem();
            //System.out.println(website1);
            if (rdImage.isSelected()) {
                try {
                    PlatformImpl.startup(() -> {
                        try {
                            ImageHistoryController imagehistory = new ImageHistoryController(website1);
                            imagehistory.start(new Stage());
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                } catch (Exception e) {
                    System.out.println("Cant load new window");
                }
            } else {
                try {
                    PlatformImpl.startup(() -> {
                        try {
                            LinkHistoryController linkhistory = new LinkHistoryController(website1);
                            linkhistory.start(new Stage());
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                } catch (Exception e) {
                    System.out.println("Cant load new window");
                }
            }
        }
    }

    @FXML
    void updateWebsite(ActionEvent event) {
        if (emptyValidation("Url Website", txtUrlWebsite.getText().isEmpty())
                && emptyValidation("Title Website", txtTitleWebsite.getText().isEmpty()) && emptyValidation("Duration", sprDuration.getEditor().getText().isEmpty())) {
            int i = (int) (new Date().getTime() / 1000);
            WebsiteDaoImpl webimpl = new WebsiteDaoImpl();
            Boolean ST = chkWorking.isSelected() ? true : false;
            webimpl.update(website.getId(), txtUrlWebsite.getText(), txtTitleWebsite.getText(), Integer.valueOf(sprDuration.getEditor().getText()), i, i, ST);
            loadWebsiteDetails();
            loadHomeDetails();
            loadWebsite1Details();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("MENU");
        stage.show();
        this.stage = stage;
    }

    private void setColumnProperties() {
        colIdWebsite.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUrlWebsite.setCellValueFactory(new PropertyValueFactory<>("url"));
        colTitleWebsite.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDateCreateWebsite.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        colDateUpdateWebsite.setCellValueFactory(new PropertyValueFactory<>("updateAt"));
        colStatusWebsite.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDurationWebiste.setCellValueFactory(new PropertyValueFactory<>("duration"));

        colIdWebsite1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUrlWebsite1.setCellValueFactory(new PropertyValueFactory<>("url"));
        colTitleWebsite1.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDateCreateWebsite1.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        colDateUpdateWebsite1.setCellValueFactory(new PropertyValueFactory<>("updateAt"));
        colStatusWebsite1.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDurationWebiste1.setCellValueFactory(new PropertyValueFactory<>("duration"));

        colIdHome.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUrlWebsiteHome.setCellValueFactory(new PropertyValueFactory<>("url"));
        colTitleWebsiteHome.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDateCreateHome.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        colDateUpdateHome.setCellValueFactory(new PropertyValueFactory<>("updateAt"));
        colStatusHome.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDurationWebsiteHome.setCellValueFactory(new PropertyValueFactory<>("duration"));

        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEdit.setCellFactory(cellFactory);
    }
    Callback<TableColumn<Profile, Boolean>, TableCell<Profile, Boolean>> cellFactory
            = (final TableColumn<Profile, Boolean> param) -> {
                final TableCell<Profile, Boolean> cell = new TableCell<Profile, Boolean>() {
            javafx.scene.image.Image imgEdit = new javafx.scene.image.Image(getClass().getResourceAsStream("/images/edit.png"));
            final Button btnEdit = new Button();

            @Override
            public void updateItem(Boolean check, boolean empty) {
                super.updateItem(check, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btnEdit.setOnAction(e -> {
                        Profile profile = getTableView().getItems().get(getIndex());
                        updateUser(profile);
                    });

                    btnEdit.setStyle("-fx-background-color: transparent;");
                    ImageView iv = new ImageView();
                    iv.setImage(imgEdit);
                    iv.setPreserveRatio(true);
                    iv.setSmooth(true);
                    iv.setCache(true);
                    btnEdit.setGraphic(iv);

                    setGraphic(btnEdit);
                    setAlignment(Pos.CENTER);
                    setText(null);
                }
            }

            private void updateUser(Profile profile) {
                userId.setText(Integer.toString(profile.getId()));
                txtFirstname.setText(profile.getFirstName());
                txtLastname.setText(profile.getLastName());
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Instant instant = profile.getBirthday().toInstant();
                LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
                txtDob.setValue(localDate);
                if (profile.getGender().equals("Male")) {
                    rbMale.setSelected(true);
                } else {
                    rbFemale.setSelected(true);
                }
                txtAddress.setText(profile.getAddress());
                txtPhone.setText(profile.getPhone());
                txtEmail.setText(profile.getEmail());
                btnSaveUser.setVisible(true);
                setFieldDisableProfile(false);
            }
        };
                return cell;
            };

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        websiteTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();

        // Add all into table
        initSpinner(countduration);
        loadWebsiteDetails();
        loadHomeDetails();
        loadProfileDetails();
        setFieldDisableWebiste(true);
        //setFieldDisableProfile(true);
        loadWebsite1Details();
        setFieldDisableProfile(true);
    }

    private void loadWebsiteDetails() {
        dataWebsite.clear();
        BaseDao u = new WebsiteDaoImpl();
        dataWebsite.addAll(u.getAll());
        websiteTable.setItems(dataWebsite);
        btnSaveWebsite.setVisible(false);
        btnUpdate.setVisible(false);
        btnNewWebiste.setVisible(true);
        btnEditWebsite.setVisible(false);
    }

    private void loadWebsite1Details() {
        dataWebsite.clear();
        BaseDao u = new WebsiteDaoImpl();
        dataWebsite.addAll(u.getAll());
        websiteTable1.setItems(dataWebsite);
    }

    private void loadProfileDetails() {
        dataProfile.clear();
        BaseDao u = new ProfileDaoImpl();
        dataProfile.addAll(u.getAll());
        userTable.setItems(dataProfile);
        btnSaveUser.setVisible(false);
        setFieldDisableProfile(true);

    }

    private void loadHomeDetails() {
        dataWebsite.clear();
        BaseDao u = new WebsiteDaoImpl();
        dataWebsite.addAll(u.getAll());
        homeTableView.setItems(dataWebsite);
    }

    private void setFieldDisableWebiste(boolean check) {
        txtUrlWebsite.setDisable(check);
        txtTitleWebsite.setDisable(check);
        sprDuration.setDisable(check);
        chkWorking.setDisable(check);
    }

    private void setFieldReset() {
        txtUrlWebsite.setText("");
        sprDuration.getEditor().setText("");
        txtTitleWebsite.setText("");
    }

    public void getLink(String getLinkUrl, int idWebsite) throws IOException {
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = null;
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        options.setHeadless(true);

        //  Khởi tạo  Firefox driver
        try {
            Thread.sleep(3000);
            driver = new FirefoxDriver(options);
            //Thời gian đợi  , trường hợp này là đợi 10s  để trình duyệt load xong 
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //  Mở trang
            driver.get(getLinkUrl);

            Document doc = Jsoup.parse(driver.getPageSource(), getLinkUrl);
//            int i = (int) (new Date().getTime() / 1000);
//            History history = new History(idWebsite, i, i, true);
//            BaseDao hisimpl = new HistoryDaoImpl();
//            hisimpl.insert(history);
            System.out.println("All Link Image img and src");
            List<String> currentUrlImages = new ArrayList<>();
            // Elements extends ArrayList<Element>.
            Elements aElements = doc.getElementsByTag("img");

            for (Element aElement1 : aElements) {
                String src = aElement1.absUrl("src");
                if (!(src == null || src.trim().equals(""))) {
                    System.out.println(src);
//                    Image ima1 = new Image(src, idWebsite, history.getId(), i, i, true);
                    currentUrlImages.add(src);
                }
            }
            List<String> newImages = new ArrayList<>();
            for (String ima2 : currentUrlImages) {
//                if (imaimpl.TestLinkImage(ima2.getUrl())) {
                if (imaimpl.TestLinkImage(ima2)) {
                    newImages.add(ima2);
                    solan++;
                }
            }

            System.out.println("All Link tag a and href");
            List<String> currentUrlLinks = new ArrayList<>();
            // Elements extends ArrayList<Element>.
            Elements aElements2 = doc.getElementsByTag("a");

            for (Element aElement2 : aElements2) {
                String href = aElement2.absUrl("href");
                if (!(href == null || href.trim().equals(""))) {
                    System.out.println(href);
//                    Image ima1 = new Image(src, idWebsite, history.getId(), i, i, true);
                    currentUrlLinks.add(href);
                }
            }
            List<String> newLinks = new ArrayList<>();
            for (String link2 : currentUrlLinks) {
//                if (imaimpl.TestLinkImage(ima2.getUrl())) {
                if (linkimpl.TestLink(link2)) {
                    newLinks.add(link2);
                    solan++;
                }
            }

            if (solan > 0) {
                int i = (int) (new Date().getTime() / 1000);
                History history = new History(idWebsite, i, i, true);
                BaseDao hisimpl = new HistoryDaoImpl();
                hisimpl.insert(history);
                for (String diffElement1 : newImages) {
                    Image ima1 = new Image(diffElement1, idWebsite, history.getId(), i, i, true);
                    imaimpl.insert(ima1);
                }
                for (String diffElement2 : newLinks) {
                    Link lik = new Link(diffElement2, idWebsite, history.getId(), i, i, true);
                    linkimpl.insert(lik);
                }
            }

//            System.out.println("All Link tag a and href");
//            List<Link> currentLinks = new ArrayList<>();
//            // Elements extends ArrayList<Element>.
//            Elements aElements2 = doc.getElementsByTag("a");
//
//            for (Element aElement2 : aElements2) {
//                String href = aElement2.absUrl("href");
//                if (!(href == null || href.trim().equals(""))) {
//                    System.out.println(href);
//                    Link lik = new Link(href, idWebsite, history.getId(), i, i, true);
//                    currentLinks.add(lik);
//                }
//            }
//            List<Link> newLinks = new ArrayList<>();
//            for (Link link1 : currentLinks) {
//                if (linkimpl.TestLink(link1.getUrl())) {
//                    newLinks.add(link1);
//                }
//            }
//            for (Link diffElement2 : newLinks) {
//                linkimpl.insert(diffElement2);
//            }
        } catch (InterruptedException e) {
            System.out.println("Cant get link");
        } finally {
            // Close the browser
            driver.close();
        }
    }

    @FXML
    void userTableClick(MouseEvent event) {
        if (MouseButton.PRIMARY == event.getButton()) {
            Profile profile = userTable.getSelectionModel().getSelectedItem();
            userId.setText(Integer.toString(profile.getId()));
            txtFirstname.setText(profile.getFirstName());
            txtLastname.setText(profile.getLastName());
            txtDob.getEditor().setText(String.valueOf(profile.getBirthday()));
            txtAddress.setText(profile.getAddress());
            txtPhone.setText(profile.getPhone());
            txtEmail.setText(profile.getEmail());
            row = userTable.getSelectionModel().getSelectedIndex();
            setFieldDisableProfile(true);
        }

    }

    private void setFieldDisableProfile(boolean check) {
        txtFirstname.setDisable(check);
        txtLastname.setDisable(check);
        txtDob.setDisable(check);
        txtAddress.setDisable(check);
        txtPhone.setDisable(check);
        txtEmail.setDisable(check);
        rbFemale.setDisable(check);
        rbMale.setDisable(check);
        txtPassword.setDisable(check);
    }

    private void initSpinner(int count) {
        sprDuration.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, count));
    }

    @FXML
    void Cancel(ActionEvent event) {
        setFieldDisableWebiste(true);
        setFieldReset();
        loadWebsiteDetails();
    }

    @FXML
    void resetUser(ActionEvent event) {
        clearProfileFields();
        setFieldDisableProfile(true);
        btnSaveWebsite.setVisible(false);
        btnUpdate.setVisible(false);
        btnNewWebiste.setVisible(true);
        btnEditWebsite.setVisible(true);
    }

    @FXML
    void Reload(ActionEvent event) {
        loadWebsiteDetails();
    }

    private void clearProfileFields() {
        txtFirstname.clear();
        txtLastname.clear();
        txtAddress.clear();
        txtDob.getEditor().clear();
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        txtEmail.clear();
        txtPassword.clear();
        userId.setText("");
        userName.setText("");
        txtPhone.clear();
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

    public boolean isValidURL(String url) {

        URL u = null;

        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }

        try {
            u.toURI();
        } catch (URISyntaxException e) {
            return false;
        }

        return true;
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

    private void updateAlert(Profile profile) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The profile " + profile.getFirstName() + " " + profile.getLastName() + " has been updated.");
        alert.showAndWait();
    }

    @FXML
    void historyWebsites(ActionEvent event) {
            Website website1 = websiteTable1.getSelectionModel().getSelectedItem();
            try {
                PlatformImpl.startup(() -> {
                    try {
                        HistoryWebsiteController history = new HistoryWebsiteController(website1);
                        history.start(new Stage());
                    } catch (IOException ex) {
                        Logger.getLogger(HistoryWebsiteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (Exception e) {
                System.out.println("Cant load new window");
            }
    }
}
