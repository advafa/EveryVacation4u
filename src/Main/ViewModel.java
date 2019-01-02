/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import App.Order;
import App.Vacation;
import App.User;
import Model.Model;

//import Views
import View.AddVacation.AddVacationController;
import View.BuyerVacation.BuyerVacationsController;
import View.BuyerVacationDetails.BuyerVacationDetailsController;
//import View.Payment.PaymentController;
import View.ProfileView.EditProfileController;
import View.ProfileView.ProfileViewController;
import View.SearchView.SearchViewController;
import View.SearchVacationDetails.SearchVacationDetailsController;
import View.SellerVacation.SellerVacationController;
import View.SellerVacationDetails.SellerVacationDetailsController;
import View.SignInScreenView.SignInController;
import View.SignUpScreenView.SignUpController;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ViewModel extends Application {

    //init xy offsets
    private double xOffset = 0;
    private double yOffset = 0;
    private Model model;
    private Stage stage;
    private User user = null;
    private Vacation vacation=null;


    //************************Vacation4U********************************
    private Scene AddVacation;
    private AddVacationController addVacationController;

    private Scene BuyerVacationsView;
    private BuyerVacationsController buyerVacationsController;

    private Scene BuyerVacationDetails;
    private BuyerVacationDetailsController buyerVacationDetailsController;

    private Scene EditProfile;
    private EditProfileController editProfileController;

    private Scene ProfileView;
    private ProfileViewController profileViewController;

    private Scene SearchView;
    private SearchViewController searchViewController;

    private Scene SearchVacationDetails;
    private SearchVacationDetailsController searchVacationDetailsController;

    private Scene  SellerVacationiew;
    private SellerVacationController sellerVacationController;

    private Scene SellerVacationDetails;
    private SellerVacationDetailsController sellerVacationDetailsController;

    private Scene signInScene;
    private SignInController signInController;
    private Scene signUpScene;
    private SignUpController signUpController;





//*********************************************************************
    private Order orderforSeller;
    private Order orderforBuyer;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("../View/SignUpScreenView/SignUpScreen.fxml"));
        Parent signUpRoot = (Parent) signUpLoader.load();

        FXMLLoader signInLoader = new FXMLLoader(getClass().getResource("../View/SignInScreenView/SignInScreen.fxml"));
        Parent signInRoot = (Parent) signInLoader.load();

//        FXMLLoader ProfileViewLoader = new FXMLLoader(getClass().getResource("../View/ProfileView/ProfileView.fxml"));
//        Parent ProfileViewRoot = (Parent) ProfileViewLoader.load();

//        FXMLLoader EditProfileViewLoader = new FXMLLoader(getClass().getResource("../View/ProfileView/EditProfileView.fxml"));
//        Parent EditProfileViewRoot = (Parent) EditProfileViewLoader.load();

//        FXMLLoader BuyerVacationViewLoader = new FXMLLoader(getClass().getResource("../View/BuyerVacation/BuyerVacation.fxml"));
//        Parent BuyerVacationViewRoot = (Parent) BuyerVacationViewLoader.load();

//        FXMLLoader buyerVacationDetailViewLoader = new FXMLLoader(getClass().getResource("../View/BuyerVacationDetails/BuyerVacationDetailsView.fxml"));
//        Parent buyerVacationDetailViewRoot = (Parent) buyerVacationDetailViewLoader.load();
//
//        FXMLLoader addVacationLoader = new FXMLLoader(getClass().getResource( "../View/AddVacation/AddVacationView.fxml"));
//        Parent addVacationRoot = (Parent) addVacationLoader.load();

//        FXMLLoader searchViewLoader = new FXMLLoader(getClass().getResource("../View/SearchView/SearchScreen.fxml"));
//        Parent searchViewRoot = (Parent) searchViewLoader.load();

//        FXMLLoader SearchVacationDetailsLoader = new FXMLLoader(getClass().getResource("../View/SearchVacationDetails/SearchVacationDetailsView.fxml"));
//        Parent SearchVacationDetailsRoot = (Parent) SearchVacationDetailsLoader.load();

//        FXMLLoader SellerVacationLoader = new FXMLLoader(getClass().getResource("../View/SellerVacation/SellerVacationView.fxml"));
//        Parent SellerVacationRoot = (Parent) SellerVacationLoader.load();

//        FXMLLoader SellerVacationDetailsLoader = new FXMLLoader(getClass().getResource("../View/SellerVacationDetails/SellerVacationDetailsView.fxml"));
//        Parent SellerVacationDetailsRoot = (Parent) SellerVacationDetailsLoader.load();
//
//        FXMLLoader PaymentViewLoader = new FXMLLoader(getClass().getResource("../View/Payment/Payment.fxml"));
//        Parent PaymentRoot = (Parent) PaymentViewLoader.load();


        this.stage = stage;
        this.stage.initStyle(StageStyle.UNDECORATED);

        //set mouse pressed
        setDraggable(stage, signUpRoot);
        setDraggable(stage, signInRoot);
//        setDraggable(stage, ProfileViewRoot);
//        setDraggable(stage, EditProfileViewRoot);
//        setDraggable(stage, BuyerVacationViewRoot);
//        setDraggable(stage, buyerVacationDetailViewRoot);
//        setDraggable(stage, addVacationRoot);
//        setDraggable(stage, searchViewRoot);
//        setDraggable(stage, SearchVacationDetailsRoot);
//        setDraggable(stage, SellerVacationRoot);
//        setDraggable(stage, SellerVacationDetailsRoot);
//        setDraggable(stage, PaymentRoot);


        signUpScene = new Scene(signUpRoot);
        signInScene = new Scene(signInRoot);
//        AddVacation = new Scene(addVacationRoot);
//        BuyerVacationsView = new Scene(BuyerVacationViewRoot);
//        BuyerVacationDetails = new Scene(buyerVacationDetailViewRoot);
//        Payment = new Scene(PaymentRoot);
//        EditProfile = new Scene(EditProfileViewRoot);
//        ProfileView = new Scene(ProfileViewRoot);
//        SearchView = new Scene(searchViewRoot);
//        SearchVacationDetails = new Scene(SearchVacationDetailsRoot);
//        SellerVacationiew = new Scene(SellerVacationRoot);
//        SellerVacationDetails = new Scene(SellerVacationDetailsRoot);





        Model model = new Model();
        setModel(model);

        signInController = signInLoader.getController();
        signInController.setViewModel(this);

        signUpController= signUpLoader.getController();
        signUpController.setViewModel(this);

//        addVacationController = addVacationLoader.getController();
//        addVacationController.setViewModel(this);

//        buyerVacationsController = BuyerVacationViewLoader.getController();
//        buyerVacationsController.setViewModel(this);

//        buyerVacationDetailsController = buyerVacationDetailViewLoader.getController();
//        buyerVacationDetailsController.setViewModel(this);

//        editProfileController =EditProfileViewLoader.getController();
//        editProfileController.setViewModel(this);

//        profileViewController = ProfileViewLoader.getController();
//        profileViewController.setViewModel(this);

//        searchViewController = searchViewLoader.getController();
//        searchViewController.setViewModel(this);

//        searchVacationDetailsController = SearchVacationDetailsLoader.getController();
//        searchVacationDetailsController.setViewModel(this);

//        sellerVacationController = SellerVacationLoader.getController();
//        sellerVacationController.setViewModel(this);

//        sellerVacationDetailsController = SellerVacationDetailsLoader.getController();
//        sellerVacationDetailsController.setViewModel(this);




        stage.setScene(signUpScene);
        stage.show();
    }


    //****************************************

    //*****************USER*******************
    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        model.UpdateUser(user);
    }

    public void addUser(User user) { model.addUser(user); }
    public void deleteUser(User user) {
        model.deleteUser(user);
    }

    public Boolean isUserExists(User user) {
        return model.isUserExists(user);
    }

    public Boolean loadUser(String email, String pass) {
        User u = new User(email, pass);
        if (model.isUserExists(u)) {
            this.user = model.loadUser(u);
            return true;
        }
        return false;
    }

    public void loguotUser() {
        this.user = null;
    }

    public void loadUser(User u) {
        this.user = u;
    }

    //*****************Update************************

    public void setSellerStatus(Order ord, boolean sellerStatus) {
        model.UpdateOrdersSellerStatus(ord, sellerStatus);
    }

    public void setBuyerStatus(Order ord, boolean buyerStatus) {
        model.UpdateOrdersBuyerStatus(ord, buyerStatus);
    }

    public void setVacationStatus(int vacation_id, boolean vac_status) {
        model.UpdatVacationStatus(vacation_id, vac_status);
    }

    public List<Order> getOrdersByBuyer_email() {
        return model.getOrdersByBuyer_email(user.getEmail());
    }

    public List<Order> getOrdersByseller_email() {
        return model.getOrdersByseller_email(user.getEmail());
    }

    public void addVacation(Vacation vacation) {
        model.addVacation(vacation);
    }

//    public void saveVacation() {
//        if (aPackage != null && aPackage.getProducts().size() > 0) {
//            model.addPackage(aPackage);
//            userViewController.addToTable(aPackage);
//        }
//        aPackage = null;
//    }
    //******************************?????????????

    public void setDraggable(Stage stage, Parent parent) {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //set mouse drag
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    /////?????????????????????????????????????



    ///*********************goTo Functions**********************




    public void goToSignUp() {
        if(user==null)
        stage.setScene(signUpScene);
        else{
            popAlertinfo("You Already sign in!" +
                    "for new Profile please sign out first");
        }

    }

    public void goToSignIn() {
        user = null;
        stage.setScene(signInScene);
    }

    public void goToAddVacation() {
        stage.setScene(AddVacation);
    }
    public void goToProfileView(){stage.setScene(ProfileView);}
    public void goToEditProfile(){stage.setScene(EditProfile);}
    public void goToSearchView() {
        stage.setScene(SearchView);
    }



    public void goToSellerVacationsView() {
        sellerVacationController.setUser(user);
        sellerVacationController.loadSellerVacations(user);
        stage.setScene(SellerVacationiew); }


    public void goToBuyerVacationsView() {
        buyerVacationsController.setUser(user);
        buyerVacationsController.loadBuyerVacations(user);
        stage.setScene(BuyerVacationsView); }



//SellerVacationDetails
    //SearchVacationDetails
    //BuyerVacationDetails
    //Payment



    public String getUserNameByEmail(String email) {
       return model.getUserNameByEmail(email); }

    public void goToBuyerVacationDetails(int vacationID,boolean status) {
        Vacation aVacation = model.getVacationByVacationId(vacationID);
        buyerVacationDetailsController.setUser(user);
        buyerVacationDetailsController.setVacation(aVacation);
        buyerVacationDetailsController.setSellerStatus(status);
        stage.setScene(BuyerVacationDetails);
    }

    public void goToSellerVacationDetails(int vacationID,Order order) {
        //TODO
        Vacation aVacation = model.getVacationByVacationId(vacationID);
        sellerVacationDetailsController.setUser(user);
        sellerVacationDetailsController.setVacation(aVacation);
        sellerVacationDetailsController.setOrder(order);
        stage.setScene(SellerVacationDetails);
    }


    ///***********************************

//    public void searchVacationsBy(List<Vacation> vacationsList) {
//        packageDescriptionViewController.addPackagesToTable(packagesList);
//        if (user != null)
//            packageDescriptionViewController.setUserLoggedIn();
//        else
//            packageDescriptionViewController.setUserLoggedOut();
//        stage.setScene(PackageDescriptionView);
//    }
//
//    public List<Vacation> searchVacationsByDate(LocalDate startDateValue, LocalDate endDateValue) {
//        return model.getVacationsBy(startDateValue, endDateValue);
//    }

    public boolean getVacationStatus(int vacationID){
        return model.getVacationByVacationId(vacationID).getVacation_status();

    }

public Vacation getVacation(int vacationID){
    Vacation aVacation = model.getVacationByVacationId(vacationID);
        return aVacation;
}

    ///***********Alert************
    public void popAlerterror(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    public void popAlertinfo(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }


}

