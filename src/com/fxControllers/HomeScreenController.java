package com.fxControllers;

import com.DeliveryService.*;
import com.DeliveryService.Enums.DeliveryStatus;
import com.hibernate.*;
import com.users.Driver;
import com.users.Manager;
import com.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    @FXML
    public Button viewButton;

    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @FXML
    public ListView driverList;
    @FXML
    public ListView managerList;

    @FXML
    public Tab manageUsersTab;
    @FXML
    public TabPane allTabs;
    public Tab myDeliveriesTab;
    public TableView<DriversTableParameters> driversTable;
    public TableColumn<DriversTableParameters, String> colDriverId;
    public TableColumn<DriversTableParameters, String>colDriverName;
    public TableColumn<DriversTableParameters, String> colDriverSurname;
    public TableColumn<DriversTableParameters, String> colDriverUsername;
    public TableColumn<DriversTableParameters, String> colDriverPassword;
    public TableColumn<DriversTableParameters, String> colDriverEmail;
    public MenuItem deleteItem;
    public MenuItem updateItem;
    public Button editManagerBtn;
    public Button deleteManagerBtn;

    private int count = 0;
    public TreeView<Comment> commentTree;
    public ListView<Delivery> deliveryList;
    public TableView<DeliveryTableParameters> deliveryTable;
    public TableColumn<DeliveryTableParameters, String> deliveryName;
    public TableColumn<DeliveryTableParameters, String> deliveryStatus;
    public TableColumn<DeliveryTableParameters, String> deliveryType;
    public TableColumn<DeliveryTableParameters, String> deliverySize;
    public TableColumn<DeliveryTableParameters, String> deliveryClient;
    public TableColumn<DeliveryTableParameters, String> deliveryDriver;
    public TableColumn<DeliveryTableParameters, String> deliveryCrDate;
    public TableColumn<DeliveryTableParameters, String> deliveryDeadline;
    public Button deleteDeliveryButton;
    public Button createDeliveryButton;
    public Tab manageDriversTab;
    public Button quitButton;
    public ListView truckListView;
    public Button createTruckButton;
    public Button updateTruckButton;
    public Button deleteTruckButton;
    public Button refreshTrucksButton;
    public ListView cargoList;
    public Button ceateCargoButton;
    public Button updateCargoButton;
    public Button deleteCargoButton;
    public Button refreshButton;
    public Button refreshCargoButton;
    public ListView<Delivery> deliveriesListView;
    public ListView checkpointListView;
    public Button createCpButton;
    public Button updateCpButton;
    public Button deleteCpButton;
    public Button refreshCpButton;
    public Tab ODdeliveryManager;
    public Tab ODmanageDriversTab;
    public Tab MyDeliveries;
    public Tab AllDeliveries;
    public Tab ODForum;
    public Tab vehicleTab;
    public Tab cargoTab;
    public ListView freeDeliveriesListView;


    public Button asigneBtn;
    public TextField acountName;
    public TextField acountLName;
    public TextField acountUserName;
    public TextField AcountPass;
    public TextField acountEmail;
    public DatePicker acountBday;
    public Button updateAcountBtn;
    public Button updateBtn;
    public TreeView <Comment>forumTreeView;
    public Tab unasignedDeliveriesTab;
    public DatePicker dDelStartDateFilter;
    public DatePicker dDelEndDateFilter;
    public ComboBox dDelStatusFilter;
    public Button filterDriverDeliveriesBtn;
    public Button refreshDeliveriesListBtn;
    public RadioButton showButton;
    public DatePicker datePickEnd;
    public DatePicker datePickStart;
    public ComboBox lovStatus;
    public ComboBox lovVehicles;
    public ComboBox lovUsers;
    public Button deleteDeliveryBtn;
    public Button editDeliveryBtn;
    public Button createDeliveryBtn;
    public Button deleteCpButton1;
    public Button updateCpButton1;
    public Button createCpButton1;
    public ListView<Delivery> ManagerDeliveryListView;
    public ListView ManagerCheckpointListView;
    public ListView <Forum> ForumListView;
    public BarChart barChart;

    //prideti birthday
    private EntityManagerFactory entityManagerFactory;
    private UserHib userHib;
    private CommentHib commentHib;
    private DeliveryHib deliveryHib;
    private VehicleHib vehicleHib;
    private CheckpointHib checkpointHib;
    private CargoHib cargoHib;

    private ForumHib forumHib;
    private User user;

    private ObservableList<DriversTableParameters> driversData = FXCollections.observableArrayList();
    private ObservableList<DeliveryTableParameters> deliveryData = FXCollections.observableArrayList();

    public void setData(EntityManagerFactory entityManagerFactory, User user) {
        this.entityManagerFactory = entityManagerFactory;
        this.userHib = new UserHib(entityManagerFactory);
        this.deliveryHib = new DeliveryHib(entityManagerFactory);
        this.vehicleHib = new VehicleHib(entityManagerFactory);
        this.cargoHib = new CargoHib(entityManagerFactory);
        this.checkpointHib = new CheckpointHib(entityManagerFactory);
        this.commentHib = new CommentHib(entityManagerFactory);
        this.forumHib = new ForumHib(entityManagerFactory);
        this.user = user;
        fillAllLists();
        disableData();
    }
    private void disableData() {
        if (user.getClass() == Driver.class) {
            allTabs.getTabs().remove(manageUsersTab);
            allTabs.getTabs().remove(manageDriversTab);
            allTabs.getTabs().remove(ODdeliveryManager);
            allTabs.getTabs().remove(ODmanageDriversTab);
            allTabs.getTabs().remove(ODForum);
            allTabs.getTabs().remove(AllDeliveries);
            allTabs.getTabs().remove(vehicleTab);
            allTabs.getTabs().remove(cargoTab);
        }
        else if (user.getClass() == Manager.class) {
            allTabs.getTabs().remove(myDeliveriesTab);
            allTabs.getTabs().remove(ODdeliveryManager);
            allTabs.getTabs().remove(ODmanageDriversTab);
            allTabs.getTabs().remove(ODForum);
            allTabs.getTabs().remove(MyDeliveries);
            allTabs.getTabs().remove(unasignedDeliveriesTab);

            Manager manager = userHib.getManagerById(user.getId());

            if(!manager.isAdmin()){
                editButton.setVisible(false);
                deleteButton.setVisible(false);
                editManagerBtn.setVisible(false);
                deleteManagerBtn.setVisible(false);
            }
        }
    }
    private void fillAllLists() {
        fillAcountInfo();

        dDelStatusFilter.getItems().addAll(DeliveryStatus.CANCELED, DeliveryStatus.WAITING, DeliveryStatus.DELIVERING, DeliveryStatus.COMPLETED);

        List<Driver> allDrivers = userHib.getAllDrivers();
        allDrivers.forEach(c -> driverList.getItems().add(c));

        List<Manager> allManagers = userHib.getAllManagers();
        allManagers.forEach(c -> managerList.getItems().add(c));

        List<Delivery> allDeliveries = deliveryHib.getAllDeliveries();

        fillVehicleList();
        fillCargoList();
        fillDeliveryList();
        fillManagerDeliveryList();
        fillManagerDeliveriesFilters();
        fillForumNameViewList();
        fillChart();

        for (Driver d : allDrivers) {
            DriversTableParameters driversTableParameters = new DriversTableParameters();
            driversTableParameters.setDriverId(String.valueOf(d.getId()));
            driversTableParameters.setDriverLogin(d.getUsername());
            driversTableParameters.setDriverPsw(d.getPassword());
            driversTableParameters.setDriverName(d.getName());
            driversTableParameters.setDriverSurn(d.getSurname());
            driversData.add(driversTableParameters);
        }
        for(Delivery d : allDeliveries){
            DeliveryTableParameters dtp = new DeliveryTableParameters();
            dtp.setDeliveryId(String.valueOf(d.getId()));
            dtp.setDeliveryName(d.getName());
            if(d.getDriver() != null){
                dtp.setDeliveryDriver(d.getDriverUsername());
            }
            dtp.setDeliveryStatus(String.valueOf(d.getDeliveryStatus()));
            deliveryData.add(dtp);
        }

        driversTable.setItems(driversData);
        deliveryTable.setItems(deliveryData);

        List<Delivery> deliveries = deliveryHib.getAllDeliveries();
        deliveries.forEach(p -> deliveryList.getItems().add(p));
    }

    private void fillVehicleList(){
        List<Vehicle> allVehicles = vehicleHib.getAllVehicles();
        allVehicles.forEach(m -> truckListView.getItems().add(m));
    }
    private void fillCargoList(){
        List<Cargo> allCargo = cargoHib.getAllCargo();
        allCargo.forEach(m -> cargoList.getItems().add(m));
    }

    private void fillManagerDeliveryList(){
        List<Delivery> allDeliveries = deliveryHib.getAllDeliveries();

        for(Delivery d : allDeliveries){
            ManagerDeliveryListView.getItems().add(d);
        }
    }

    private void fillManagerDeliveriesFilters(){
        List<Vehicle> allVehicles = vehicleHib.getAllVehicles();
        allVehicles.forEach(m -> lovVehicles.getItems().add(m));

        List<Driver> allDrivers = userHib.getAllDrivers();
        allDrivers.forEach(m -> lovUsers.getItems().add(m));

        lovStatus.getItems().addAll(DeliveryStatus.CANCELED, DeliveryStatus.WAITING, DeliveryStatus.DELIVERING, DeliveryStatus.COMPLETED);
    }

    private void fillDeliveryList(){
        List<Delivery> allDeliveries = deliveryHib.getAllDeliveries();

        //get all values to list
        /*
        allDeliveries.forEach(d -> deliveriesListView.getItems().add(d));

         */
        for(Delivery d : allDeliveries){
            if(d.getDriver() != null){
                if(d.getDriver().getId() == user.getId()){
                    deliveriesListView.getItems().add(d);
                }
            }

        }
        for(Delivery d : allDeliveries){
            if(d.getDriver() == null){
                freeDeliveriesListView.getItems().add(d);
            }
        }
    }

    @FXML
    private void fillCheckpointList(){

        if(user.getClass() == Driver.class){
            checkpointListView.getItems().clear();
            if(!deliveriesListView.equals(null)) {
                List<Checkpoint> allCheckpoints = deliveryHib.getDeliveryById(deliveriesListView.getSelectionModel().getSelectedItem().getId()).getCheckpoints();
                allCheckpoints.forEach(d -> checkpointListView.getItems().add(d));
            }
        }
        if(user.getClass() == Manager.class){
            ManagerCheckpointListView.getItems().clear();
            if(!ManagerDeliveryListView.equals(null)) {
                List<Checkpoint> allCheckpoints = deliveryHib.getDeliveryById(ManagerDeliveryListView.getSelectionModel().getSelectedItem().getId()).getCheckpoints();
                allCheckpoints.forEach(d -> ManagerCheckpointListView.getItems().add(d));
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void viewDetails() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/viewDetails.fxml"));
        Parent parent = fxmlLoader.load();
        //UserInfoController userInfoController = fxmlLoader.getController();
        //userInfoController.setData(entityManagerFactory, user, (Driver) driverList.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void editUserInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/userInfo.fxml"));
        Parent parent = fxmlLoader.load();
        UserInfoController userInfoController = fxmlLoader.getController();
        userInfoController.setData(entityManagerFactory, user, (Driver) driverList.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initOwner(managerList.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void editManagerInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/EditManager.fxml"));
        Parent parent = fxmlLoader.load();
        EditManager userInfoController = fxmlLoader.getController();
        userInfoController.setData(entityManagerFactory, user, (Manager) managerList.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        //stage.initOwner(managerList.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void deleteUser() {
        if(driverList.getSelectionModel().getSelectedItem() != null && driverList.getSelectionModel().getSelectedItem().getClass() == Driver.class){
            userHib.deleteDriver((Driver) driverList.getSelectionModel().getSelectedItem());
            driverList.getItems().remove((Driver) driverList.getSelectionModel().getSelectedItem());
            driverList.getSelectionModel().clearSelection();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Drivers management table
        driversTable.setEditable(true);
        colDriverId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        colDriverUsername.setCellValueFactory(new PropertyValueFactory<>("driverLogin"));
        colDriverUsername.setCellFactory(TextFieldTableCell.forTableColumn());

        colDriverUsername.setOnEditCommit(t -> {
            t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setDriverLogin(t.getNewValue());
            //Noriu kad eitu i db update
            Driver driver = userHib.getDriverById(Integer.parseInt(t.getTableView().getItems().get(t.getTablePosition().getRow()).getDriverId()));
            driver.setUsername(t.getTableView().getItems().get(t.getTablePosition().getRow()).getDriverLogin());
            userHib.updateUser(driver);
        });

        colDriverName.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        colDriverSurname.setCellValueFactory(new PropertyValueFactory<>("driverSurn"));
        colDriverPassword.setCellValueFactory(new PropertyValueFactory<>("driverPsw"));

        //Delivery management table

        deliveryTable.setEditable(true);
        deliveryName.setCellValueFactory(new PropertyValueFactory<>("deliveryName"));
        deliveryDriver.setCellValueFactory(new PropertyValueFactory<>("deliveryDriver"));
        deliveryName.setCellFactory(TextFieldTableCell.forTableColumn());
        deliveryDriver.setCellFactory(TextFieldTableCell.forTableColumn());

        deliveryName.setOnEditCommit(t -> {
            t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setDeliveryName(t.getNewValue());
            //Noriu kad eitu i db update
            Delivery delivery = deliveryHib.getDeliveryById(Integer.parseInt(t.getTableView().getItems().get(t.getTablePosition().getRow()).getDeliveryId()));
            delivery.setName(t.getTableView().getItems().get(t.getTablePosition().getRow()).getDeliveryName());
            deliveryHib.updateDelivery(delivery);
        });
        deliveryDriver.setOnEditCommit(t -> {
            t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setDeliveryDriver(t.getNewValue());
            //Noriu kad eitu i db update
            Delivery delivery = deliveryHib.getDeliveryById(Integer.parseInt(t.getTableView().getItems().get(t.getTablePosition().getRow()).getDeliveryId()));
            String driverName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getDeliveryDriver();
            if(userHib.getDriverByUsername(driverName) != null) {
                delivery.setDriver(userHib.getDriverByUsername(driverName));
                deliveryHib.updateDelivery(delivery);
            }
            else{
                utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Driver not found!", "No such Driver or wrong Username");
            }
        });
    }

    public void fillForumNameViewList(){
        List<Forum> forums = forumHib.getAllForums();
        forums.forEach(f -> ForumListView.getItems().add(f));
    }

    public void deleteComment(ActionEvent actionEvent) {
        if(forumTreeView.getSelectionModel().getSelectedItem() == null){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Can't Delete a comment!", "Please select a comment to delete it.");
        }else{
            Comment comment = forumTreeView.getSelectionModel().getSelectedItem().getValue();
            System.out.println(comment);
            commentHib.deleteComment(comment);
        }
    }

    public void updateComment(ActionEvent actionEvent) throws IOException {
        if(forumTreeView.getSelectionModel().getSelectedItem() == null){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Can't Edit comment", "Please select comment to edit");
        }else{
            Comment comment = forumTreeView.getSelectionModel().getSelectedItem().getValue();

            FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/UpdateComment.fxml"));
            Parent parent = fxmlLoader.load();

            UpdateComment controller = fxmlLoader.getController();
            controller.setData(entityManagerFactory, comment);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }

    }

    public void createComment() throws IOException {
        if(ForumListView.getSelectionModel().getSelectedItem() == null){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Add a comment", "Please click on a forum if you want to add a comment.");
        }else{
            Forum forum = (Forum) ForumListView.getSelectionModel().getSelectedItem();

            FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/CommentCreation.fxml"));
            Parent parent = fxmlLoader.load();
            CommentCreation controller = fxmlLoader.getController();
            controller.setData(entityManagerFactory, user, forum);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }

    public void loadComments() {
        List<Comment> comments = ForumListView.getSelectionModel().getSelectedItem().getComments();
        forumTreeView.setRoot(new TreeItem<>(new Comment()));
        forumTreeView.setShowRoot(false);
        forumTreeView.getRoot().setExpanded(true);
        comments.forEach(comment -> {
            addTreeItem(comment, forumTreeView.getRoot());
        });
    }
    private void addTreeItem(Comment comment, TreeItem parent) {
        TreeItem<Comment> treeItem = new TreeItem<>(comment);
        treeItem.setExpanded(true);
        parent.getChildren().add(treeItem);
        comment.getReplies().forEach(r -> addTreeItem(r, treeItem));
    }

    public void DeleteDeliveryAction(ActionEvent actionEvent) {
        if(deliveryTable.getSelectionModel().getSelectedItem() != null && deliveryTable.getSelectionModel().getSelectedItem().getClass() == DeliveryTableParameters.class){
            deliveryHib = new DeliveryHib(entityManagerFactory);
            deliveryTable.getItems().removeAll(deliveryTable.getSelectionModel().getSelectedItem());
            deliveryTable.getSelectionModel().clearSelection();
            deliveryHib.deleteDelivery(deliveryHib.getDeliveryById(Integer.parseInt(deliveryTable.getSelectionModel().getSelectedItem().getDeliveryId())));
        }
    }

    public void createDeliveryAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/DeliveryCreation.fxml"));
        Parent parent = fxmlLoader.load();
        CreateDeliveryController deliveryController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void QuitApp() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    private void clearVehicleList() {truckListView.getItems().clear();}

    private  void clearCargoList(){cargoList.getItems().clear();}

    public void createTruckAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/createTruck.fxml"));
        Parent parent = fxmlLoader.load();
        TruckController truckController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void updateTruckAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/updateTruck.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateTruckController updateTruckControl = fxmlLoader.getController();
        updateTruckControl.setData(entityManagerFactory, user, (Vehicle) truckListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initOwner(managerList.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void deleteTruckAction() {
        if(truckListView.getSelectionModel().getSelectedItem() != null && truckListView.getSelectionModel().getSelectedItem().getClass() == Vehicle.class){
            vehicleHib.deleteVehicle((Vehicle) truckListView.getSelectionModel().getSelectedItem());
            truckListView.getItems().remove((Vehicle) truckListView.getSelectionModel().getSelectedItem());
            truckListView.getSelectionModel().clearSelection();
        }
    }

    public void createCargoAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/createCargo.fxml"));
        Parent parent = fxmlLoader.load();
        CargoController cargoController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void updateCargoAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/updateCargo.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateCargoController updateCargoControl = fxmlLoader.getController();
        updateCargoControl.setData(entityManagerFactory, user, (Cargo) cargoList.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initOwner(managerList.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void deleteCargoAction() {
        if(cargoList.getSelectionModel().getSelectedItem() != null && cargoList.getSelectionModel().getSelectedItem().getClass() == Cargo.class){
            cargoHib.deleteCargo((Cargo) cargoList.getSelectionModel().getSelectedItem());
            cargoList.getItems().remove(cargoList.getSelectionModel().getSelectedItem());
            cargoList.getSelectionModel().clearSelection();
        }
    }

    public void refreshVehicleAction() {
        clearVehicleList();
        fillVehicleList();
    }


    public void refreshCargoAction(ActionEvent actionEvent) {
        clearCargoList();
        fillCargoList();
    }

    public void createCheckpointAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/createCheckpoint.fxml"));
        Parent parent = fxmlLoader.load();
        CreateCheckpointController Control = fxmlLoader.getController();
        Control.setData(entityManagerFactory, user, (Delivery) deliveriesListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        //stage.initOwner(managerList.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void updateCheckpointAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/updateCheckpoint.fxml"));
        Parent parent = fxmlLoader.load();
        updateCheckpoint Control = fxmlLoader.getController();
        Control.setData(entityManagerFactory, user, (Checkpoint) checkpointListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        //stage.initOwner(managerList.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void deleteCheckpointAction() {
        if(checkpointListView.getSelectionModel().getSelectedItem() != null && checkpointListView.getSelectionModel().getSelectedItem().getClass() == Checkpoint.class){
            checkpointHib.deleteCheckpoint((Checkpoint) checkpointListView.getSelectionModel().getSelectedItem());
            checkpointListView.getItems().remove((Checkpoint) checkpointListView.getSelectionModel().getSelectedItem());
            checkpointListView.getSelectionModel().clearSelection();
        }
    }

    public void assignAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/assignDeliveryWindow.fxml"));
        Parent parent = fxmlLoader.load();
        AssignDeliveryWindow controller = fxmlLoader.getController();
        controller.setData(entityManagerFactory, user, (Delivery) freeDeliveriesListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void fillAcountInfo(){
        acountName.setText(user.getName());
        acountLName.setText(user.getSurname());
        acountUserName.setText(user.getUsername());
        AcountPass.setText(user.getPassword());
        acountBday.setValue(user.getBirthday());
        acountEmail.setText(user.getEmail());
    }

    public void updateAcountAction(ActionEvent actionEvent) {

        User acountUser = user;
        acountUser.setName(acountName.getText());
        acountUser.setSurname(acountLName.getText());
        acountUser.setUsername(acountUserName.getText());
        acountUser.setPassword(AcountPass.getText());
        acountUser.setEmail(acountEmail.getText());
        acountUser.setBirthday(acountBday.getValue());
        userHib.updateUser(user);
        fillAcountInfo();
    }

    public void UpdateStatusWindowOpen(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/updateStatusWindow.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateStatusWindow controller = fxmlLoader.getController();
        controller.setData(entityManagerFactory, (Delivery) deliveriesListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void filteDriverDelAction(ActionEvent actionEvent) {
        List<Delivery> allDeliveries = deliveryHib.getSelected(dDelStartDateFilter.getValue(), dDelEndDateFilter.getValue(), dDelStatusFilter.getSelectionModel().getSelectedItem().toString());
        deliveriesListView.getItems().clear();

        for(Delivery e : allDeliveries)
        {
           deliveriesListView.getItems().add(e);
        }
    }

    public void refreshDeliveryListAction(ActionEvent actionEvent) {
        deliveriesListView.getItems().clear();
        freeDeliveriesListView.getItems().clear();
        fillDeliveryList();

    }

    public void updateDeliveryCheckpointAction(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/updateCheckpoint.fxml"));
        Parent parent = fxmlLoader.load();
        updateCheckpoint Control = fxmlLoader.getController();
        Control.setData(entityManagerFactory, user, (Checkpoint) ManagerCheckpointListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        //stage.initOwner(managerList.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void createDeliveryCheckpointAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/createCheckpoint.fxml"));
        Parent parent = fxmlLoader.load();
        CreateCheckpointController Control = fxmlLoader.getController();
        Control.setData(entityManagerFactory, user, (Delivery) ManagerDeliveryListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        //stage.initOwner(managerList.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void deleteDeliveryCheckpointAction(ActionEvent actionEvent) {
        if(ManagerCheckpointListView.getSelectionModel().getSelectedItem() != null && ManagerCheckpointListView.getSelectionModel().getSelectedItem().getClass() == Checkpoint.class){
            checkpointHib.deleteCheckpoint((Checkpoint) ManagerCheckpointListView.getSelectionModel().getSelectedItem());
            ManagerCheckpointListView.getItems().remove((Checkpoint) ManagerCheckpointListView.getSelectionModel().getSelectedItem());
            ManagerCheckpointListView.getSelectionModel().clearSelection();
        }
    }

    public void createNewDeliveryAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/createDelivery.fxml"));
        Parent parent = fxmlLoader.load();
        CreateDelivery controller = fxmlLoader.getController();
        controller.setData(entityManagerFactory, user);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void EditExistingDeliveryAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/ManageDeliveryWindow.fxml"));
        Parent parent = fxmlLoader.load();
        ManageDeliveryWindow controller = fxmlLoader.getController();
        controller.setData(entityManagerFactory, user, (Delivery) ManagerDeliveryListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void deleteDeliveryAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/deleteDeliveryWindow.fxml"));
        Parent parent = fxmlLoader.load();
        DeleteDeliveryWindow controller = fxmlLoader.getController();
        controller.setData(entityManagerFactory, (Delivery) ManagerDeliveryListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void RefreshAllDeliveriesAction(ActionEvent actionEvent) {
        ManagerDeliveryListView.getItems().clear();

        if(showButton.isSelected()){
            List<Delivery> allDeliveries = deliveryHib.getAllDeliveries();
            for(Delivery d : allDeliveries){
                if(d.getResponsiblePerson() != null){
                    if(d.getResponsiblePerson().getId() == user.getId()){
                        ManagerDeliveryListView.getItems().add(d);
                    }
                }
            }
        }else if(!showButton.isSelected()){
            fillManagerDeliveryList();
        }
    }

    public void showManagerDeliveriesAction(ActionEvent actionEvent) {
        /*ManagerDeliveryListView.getItems().clear();

        if(showButton.isSelected()){
            List<Delivery> allDeliveries = deliveryHib.getAllDeliveries();
            for(Delivery d : allDeliveries){
                if(d.getResponsiblePerson() != null){
                    if(d.getResponsiblePerson().getId() == user.getId()){
                        deliveriesListView.getItems().add(d);
                    }
                }
            }
        }else if(showButton.isSelected()){
            fillManagerDeliveryList();
        }*/
    }

    public void fillDeliveryCheckpointList(MouseEvent mouseEvent) {
        //ManagerCheckpointListView
        ManagerCheckpointListView.getItems().clear();
        if(!ManagerDeliveryListView.getItems().isEmpty()) {
            List<Checkpoint> allCheckpoints = deliveryHib.getDeliveryById(ManagerDeliveryListView.getSelectionModel().getSelectedItem().getId()).getCheckpoints();
            allCheckpoints.forEach(d -> ManagerCheckpointListView.getItems().add(d));
        }

    }

    public void filterManagersDeliveries(ActionEvent actionEvent) {
        List<Delivery> allDeliveries = deliveryHib.getSelectedByAll(datePickStart.getValue(), datePickEnd.getValue(), (Driver) lovUsers.getSelectionModel().getSelectedItem(),(Vehicle) lovVehicles.getSelectionModel().getSelectedItem(), lovStatus.getSelectionModel().getSelectedItem().toString());
        ManagerDeliveryListView.getItems().clear();

        for(Delivery e : allDeliveries)
        {
            ManagerDeliveryListView.getItems().add(e);
        }
    }

    public void replyToComment(ActionEvent actionEvent) throws IOException {
        if(forumTreeView.getSelectionModel().getSelectedItem() == null){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "No comment selected!", "Please click on a comment if you want to reply to it.");
        }else{
            Comment parentComment = forumTreeView.getSelectionModel().getSelectedItem().getValue();

            FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/ReplyComment.fxml"));
            Parent parent = fxmlLoader.load();
            ReplyComment controller = fxmlLoader.getController();

            controller.setData(entityManagerFactory, parentComment, user);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }

    public void openNewForumForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/ForumCreator.fxml"));
        Parent parent = fxmlLoader.load();
        ForumCreator controller = fxmlLoader.getController();

        controller.setData(entityManagerFactory);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void deleteForm(ActionEvent actionEvent) {
        if(ForumListView.getSelectionModel().getSelectedItem() == null){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Can't Delete a forum!", "Please select forum to delete it");
        }else{
            Forum forum = ForumListView.getSelectionModel().getSelectedItem();
            forumHib.deleteForum(forum);
        }
    }

    public void openEditForumForm(ActionEvent actionEvent) throws IOException {
        if(ForumListView.getSelectionModel().getSelectedItem() == null){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Can't Update forum!", "Please select a forum to edit.");
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("views/ForumUpdater.fxml"));
            Parent parent = fxmlLoader.load();
            ForumUpdater controller = fxmlLoader.getController();


            Forum forum = ForumListView.getSelectionModel().getSelectedItem();

            controller.setData(entityManagerFactory, forum);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }

    public void RefreshForum(ActionEvent actionEvent) {
        ForumListView.getItems().clear();
        fillForumNameViewList();
    }

    public void deleteManager(ActionEvent actionEvent) {
        if(managerList.getSelectionModel().getSelectedItem() != null && managerList.getSelectionModel().getSelectedItem().getClass() == Manager.class) {
            userHib.deleteManager((Manager) managerList.getSelectionModel().getSelectedItem());
            managerList.getItems().remove((Manager) managerList.getSelectionModel().getSelectedItem());
            managerList.getSelectionModel().clearSelection();
        }

    }


    private void fillChart(){
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Checkpoints");
        List<Delivery> allDeliveries = deliveryHib.getAllDeliveries();
        List<Checkpoint> allCheckpoints = checkpointHib.getAllCheckpoints();

        count = 0;


        for(Delivery d: allDeliveries){
            String deliveryName = d.getName();
            for(Checkpoint c: allCheckpoints){
                if(c.getDelivery().getId() == d.getId()){
                    count++;
                }
            }
            series1.getData().add(new XYChart.Data(deliveryName, count));
            count = 0;
        }
        barChart.getData().addAll(series1);
    }
    private BarChart<String,Integer> BarChart;
}
