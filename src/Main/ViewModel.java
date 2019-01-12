/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import App.*;
import Model.Model;

//import Views
import View.AddVacation.AddVacationController;
import View.BuyerRequests.BuyerRequestsController;
import View.BuyerRequests.BuyerVacationDetailsController;
import View.Profile.EditProfileController;
import View.Profile.ProfileViewController;
import View.Search.SearchViewController;
import View.Search.SearchVacationDetailsController;
import View.SellerVacation.SellerVacationController;
import View.SellerVacation.SellerVacationDetailsController;
import View.SignIn.SignInController;
import View.SignUp.SignUpController;
import View.SellerRequests.SellerRequestsController;
import View.SellerRequests.RequestDetailsController;
import View.TraderRequest.TraderRequestController;
import View.TraderRequest.TradeRequestDetailsController;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.Optional;

import static javafx.scene.input.KeyCode.V;

public class ViewModel extends Application {

    //init xy offsets
    private double xOffset = 0;
    private double yOffset = 0;
    private Model model;
    private Stage stage;
    private User user = null;
    private Vacation vacation;


    //************************Vacation4U********************************
    private Scene AddVacationScene;
    private AddVacationController addVacationController;

    private Scene BuyerRequestsViewScene;
    private BuyerRequestsController buyerRequestsController;

    private Scene BuyerVacationDetailsScene;
    private BuyerVacationDetailsController buyerVacationDetailsController;

    private Scene EditProfileScene;
    private EditProfileController editProfileController;

    private Scene ProfileViewScene;
    private ProfileViewController profileViewController;

    private Scene SearchViewScene;
    private SearchViewController searchViewController;

    private Scene SearchVacationDetailsScene;
    private SearchVacationDetailsController searchVacationDetailsController;

    private Scene SellerRequestViewScene;
    private SellerRequestsController sellerRequestsController;

    private Scene RequestDetailsViewScene;
    private RequestDetailsController requestDetailsController;

    private Scene SellerVacationiewScene;
    private SellerVacationController sellerVacationController;

    private Scene SellerVacationDetailsScene;
    private SellerVacationDetailsController sellerVacationDetailsController;


    private Scene signInScene;
    private SignInController signInController;

    private Scene signUpScene;
    private SignUpController signUpController;

    private Scene TraderRequestScene;
    private TraderRequestController traderRequestController;

    private Scene TradeRequestDetailsScene;
    private TradeRequestDetailsController tradeRequestDetailsController;


    private String goTo;

    //*********************************************************************
    private Order orderforSeller;
    private Order orderforBuyer;
    private Order CurrentReq;


    private int VacationID4Trade;
    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader addVacationLoader = new FXMLLoader(getClass().getResource("../View/AddVacation/AddVacationView.fxml"));
        Parent addVacationRoot = (Parent) addVacationLoader.load();

        FXMLLoader BuyerRequestsViewLoader = new FXMLLoader(getClass().getResource("../View/BuyerRequests/BuyerRequestsView.fxml"));
        Parent BuyerRequestsViewRoot = (Parent) BuyerRequestsViewLoader.load();

        FXMLLoader buyerVacationDetailViewLoader = new FXMLLoader(getClass().getResource("../View/BuyerRequests/BuyerVacationDetailsView.fxml"));
        Parent buyerVacationDetailViewRoot = (Parent) buyerVacationDetailViewLoader.load();

        FXMLLoader EditProfileViewLoader = new FXMLLoader(getClass().getResource("../View/Profile/EditProfileView.fxml"));
        Parent EditProfileViewRoot = (Parent) EditProfileViewLoader.load();

        FXMLLoader ProfileViewLoader = new FXMLLoader(getClass().getResource("../View/Profile/ProfileView.fxml"));
        Parent ProfileViewRoot = (Parent) ProfileViewLoader.load();


        FXMLLoader searchViewLoader = new FXMLLoader(getClass().getResource("../View/Search/SearchView.fxml"));
        Parent searchViewRoot = (Parent) searchViewLoader.load();

        FXMLLoader SearchVacationDetailsLoader = new FXMLLoader(getClass().getResource("../View/Search/SearchVacationDetailsView.fxml"));
        Parent SearchVacationDetailsRoot = (Parent) SearchVacationDetailsLoader.load();

        FXMLLoader SellerRequestViewLoader = new FXMLLoader(getClass().getResource("../View/SellerRequests/SellerRequestsView.fxml"));
        Parent SellerRequestRoot = (Parent) SellerRequestViewLoader.load();

        FXMLLoader RequestDetailsViewLoader = new FXMLLoader(getClass().getResource("../View/SellerRequests/RequestDetailsView.fxml"));
        Parent RequestDetailstRoot = (Parent) RequestDetailsViewLoader.load();


        FXMLLoader SellerVacationLoader = new FXMLLoader(getClass().getResource("../View/SellerVacation/SellerVacationsView.fxml"));
        Parent SellerVacationRoot = (Parent) SellerVacationLoader.load();

        FXMLLoader SellerVacationDetailsLoader = new FXMLLoader(getClass().getResource("../View/SellerVacation/SellerVacationDetailsView.fxml"));
        Parent SellerVacationDetailsRoot = (Parent) SellerVacationDetailsLoader.load();

        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("../View/SignUp/SignUpView.fxml"));
        Parent signUpRoot = (Parent) signUpLoader.load();

        FXMLLoader signInLoader = new FXMLLoader(getClass().getResource("../View/SignIn/SignInView.fxml"));
        Parent signInRoot = (Parent) signInLoader.load();

        FXMLLoader TraderRequestLoader = new FXMLLoader(getClass().getResource("../View/TraderRequest/TraderRequestView.fxml"));
        Parent TraderRequestRoot = (Parent) TraderRequestLoader.load();

        FXMLLoader TradeRequestDetailsLoader = new FXMLLoader(getClass().getResource("../View/TraderRequest/TradeRequestDetailsView.fxml"));
        Parent TradeRequestDetailsRoot = (Parent) TradeRequestDetailsLoader.load();

        this.stage = stage;
        this.stage.initStyle(StageStyle.UNDECORATED);

        //set mouse pressed
        setDraggable(stage, addVacationRoot);
        setDraggable(stage, BuyerRequestsViewRoot);
        setDraggable(stage, buyerVacationDetailViewRoot);
        setDraggable(stage, EditProfileViewRoot);
        setDraggable(stage, ProfileViewRoot);
        setDraggable(stage, searchViewRoot);
        setDraggable(stage, SearchVacationDetailsRoot);
        setDraggable(stage, SellerRequestRoot);
        setDraggable(stage, RequestDetailstRoot);
        setDraggable(stage, SellerVacationRoot);
        setDraggable(stage, SellerVacationDetailsRoot);
        setDraggable(stage, signUpRoot);
        setDraggable(stage, signInRoot);
        setDraggable(stage, TraderRequestRoot);
        setDraggable(stage, TradeRequestDetailsRoot);
//


        AddVacationScene = new Scene(addVacationRoot);
        BuyerRequestsViewScene = new Scene(BuyerRequestsViewRoot);
        BuyerVacationDetailsScene = new Scene(buyerVacationDetailViewRoot);
        EditProfileScene = new Scene(EditProfileViewRoot);
        ProfileViewScene = new Scene(ProfileViewRoot);
        SearchViewScene = new Scene(searchViewRoot);
        SearchVacationDetailsScene = new Scene(SearchVacationDetailsRoot);
        SellerRequestViewScene = new Scene(SellerRequestRoot);
        RequestDetailsViewScene = new Scene(RequestDetailstRoot);
        SellerVacationiewScene = new Scene(SellerVacationRoot);
        SellerVacationDetailsScene = new Scene(SellerVacationDetailsRoot);
        signUpScene = new Scene(signUpRoot);
        signInScene = new Scene(signInRoot);
        TraderRequestScene = new Scene(TraderRequestRoot);
        TradeRequestDetailsScene= new Scene(TradeRequestDetailsRoot);

        Model model = new Model();
        setModel(model);

        addVacationController = addVacationLoader.getController();
        addVacationController.setViewModel(this);

        buyerRequestsController = BuyerRequestsViewLoader.getController();
        buyerRequestsController.setViewModel(this);

        buyerVacationDetailsController = buyerVacationDetailViewLoader.getController();
        buyerVacationDetailsController.setViewModel(this);

        editProfileController = EditProfileViewLoader.getController();
        editProfileController.setViewModel(this);

        profileViewController = ProfileViewLoader.getController();
        profileViewController.setViewModel(this);

        searchViewController = searchViewLoader.getController();
        searchViewController.setViewModel(this);

        searchVacationDetailsController = SearchVacationDetailsLoader.getController();
        searchVacationDetailsController.setViewModel(this);

        sellerRequestsController = SellerRequestViewLoader.getController();
        sellerRequestsController.setViewModel(this);

        requestDetailsController = RequestDetailsViewLoader.getController();
        requestDetailsController.setViewModel(this);

        sellerVacationController = SellerVacationLoader.getController();
        sellerVacationController.setViewModel(this);

        sellerVacationDetailsController = SellerVacationDetailsLoader.getController();
        sellerVacationDetailsController.setViewModel(this);

        signInController = signInLoader.getController();
        signInController.setViewModel(this);

        signUpController = signUpLoader.getController();
        signUpController.setViewModel(this);

        traderRequestController=TraderRequestLoader.getController();
        traderRequestController.setViewModel(this);

        tradeRequestDetailsController=TradeRequestDetailsLoader.getController();
        tradeRequestDetailsController.setViewModel(this);
//        loadUser("Tal@gmail.com", "1111");
        loadUser("Niv@gmail.com", "12345678");
//

//        Order o1= new Order("Tal@gmail.com","Niv@gmail.com",4,false);
//        Order o2= new Order("Tal@gmail.com","Niv@gmail.com",5,false);
//
//        Order o3= new Order("Tal@gmail.com","Niv@gmail.com",6,false);
//
//        addReq(o1);
//        addReq(o2);
//        addReq(o3);
//
//        setSellerStatus(o2, true);
//        setSellerStatus(o3,false);
        TradeRequest t2=new TradeRequest("Niv@gmail.com","Hilel@gmail.com",2,3,true);
t2.setSeller_status(true);
        TradeRequest t1=new TradeRequest("Niv@gmail.com","Hilel@gmail.com",3,4,true);
t1.setSeller_status(false);
        //this.addReq(t1);
      //  this.addReq(t2);
        //        TradeRequest t2=new TradeRequest("Hilel@gmail.com","Niv@gmail.com")
        stage.setScene(this.signUpScene);
        stage.show();

    }


    //****************************************
//******************************

    public static void main(String[] args) {
        launch(args);
    }

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


    //******************** LoadUser ************************
    public Boolean loadUser(String email, String pass) {
        this.user = model.loadUser(email);
        if(this.user!=null && pass.equals(this.user.getPassword()))
            return true;
        else
            return false;
    }

    //***************** Set Functions ************************
    public void setModel(Model model) {
        this.model = model;
    }

    public void setUser(User user) {
        model.UpdateUser(user);
    }

    public void setSellerStatus(Order ord, boolean sellerStatus) {
        model.UpdateOrdersSellerStatus(ord, sellerStatus);
    }
    public void setSellerStatus(TradeRequest req, boolean sellerStatus) {
        model.UpdateTradeRequestSellerStatus(req,sellerStatus);
    }

    public void setBuyerStatus(Order ord, boolean buyerStatus) {
        model.UpdateOrdersBuyerStatus(ord, buyerStatus);
    }

    public void setVacationStatus(int vacation_id, boolean vac_status) {
        model.UpdatVacationStatus(vacation_id, vac_status);
    }


    //***************** Get Functions ************************
    public User getUser() { return this.user; }
    public String getUserNameByEmail(String email) { return model.getUserNameByEmail(email); }
//    public Vacation getVacation(){ return this.vacation; }

//
    public List<Order> getRequestsByBuyer_email() {return model.getRequestsByBuyer_email(user.getEmail()); }
    public List<Order> getRequestsByseller_email() { return model.getRequestsByseller_email(user.getEmail()); }
    public boolean getVacationStatus(int vacationID){ return model.getVacationByVacationId(vacationID).getVacation_status(); }
    public Vacation getVacation(int vacationID){ return model.getVacationByVacationId(vacationID);}
    public List<Vacation> getVacationsByseller_email(){ return model.getVacationsByseller_email(this.user.getEmail()); }
    public List<Vacation> getAvailableVacationsByseller_email(){ return model.getAvailableVacationsByseller_email(this.user.getEmail()); }
    public List<Vacation> getAllAvailableVacations() {return model.getAllAvailableVacations();}
    public List<Vacation> getVacationBySimpleSearch(Vacation aVacation){return model.getVacationBySimpleSearch(aVacation);}

    public List<TradeRequest> getTradeRequestByseller_email(){return model.getTradeRequestByseller_email(this.user.getEmail());}
    public List<TradeRequest> getTradeRequestByTrader_email(){ return model.getTradeRequestByTrader_email(this.user.getEmail());}

    //*****************  Add functions ******************************
    public void AddUser(User user) {
        model.addUser(user);
    }

    public void AddVacation(Vacation vacation) {
        model.addVacation(vacation);
    }

    public void addReq(Order order) {
        model.addReq(order);
    }

    public void addReq(Vacation avacation) {
        if (avacation.getSeller().equals(this.user.getEmail())) {
            this.popAlerterror("You can't Buy yours Vacation !");}
            else {
            Order req = new Order(avacation.getSeller(), this.user.getEmail(), avacation.getVacation_id(), false);
            if (this.isReqExists(req)) {
                this.popAlerterror("You already sent a request for this Vacation !");
            } else {
                this.addReq(req);
                popAlertinfo("Your Request successfully sent!");
            }
        }
    }

    public void addReq(int avacationID) {
        this.addReq(this.getVacation(avacationID));
    }


    public void addReq(Vacation avacation,Vacation avacationtoTade) {
        if (avacation.getSeller().equals(this.user.getEmail())) {
            this.popAlerterror("You can't Trades your Vacation with your Vacation !");
            }
        else {
            TradeRequest req = new TradeRequest(avacation.getSeller(), this.user.getEmail(), avacation.getVacation_id(), avacationtoTade.getVacation_id(),true);
            if (this.isReqExists(req)) {
                this.popAlerterror("You already sent a request for this Vacation !");
            } else {
                this.addReq(req);
                popAlertinfo("Your Request successfully sent!");
            }
            this.goToSearchView();
        }
    }


    public void addPayment(Payment pay) {model.addPayment(pay);
    }


    public void addReq(TradeRequest tradeRequest) {model.addReq(tradeRequest);}

    public void addTradeReq(int avacationID, boolean isChoosen){
        if(!isChoosen) {
            this.VacationID4Trade = avacationID;
            this.goToSellerVacationsView("Trade");
        }
        else{
            this.addReq(this.getVacation(this.VacationID4Trade),this.getVacation(avacationID));

        }
    }


    //  ******************** Check if Exists *****************************
    public Boolean isUserExists(User user) {
        return model.isUserExists(user);
    }

    public Boolean isReqExists (Order req) {
        return model.isSaleReqExists (req.getSeller_email(), req.getBuyer_email(), req.getVacation_id());}


    public Boolean isReqExists (TradeRequest req) {
        return model.isSaleReqExists (req.getVacation_id(), req.getVacationtoTrade_id());}


    //*************** Delete ******************************

    public void DeleteProfile() {


        model.deleteRequestsByBuyer(user.getEmail());
        model.deleteRequestsBySeller(user.getEmail());
        model.deleteTradeRequestsByTrader(user.getEmail());
        model.deleteTradeRequestBySeller(user.getEmail());
        model.deleteVacationsBySeller(user.getEmail());
        model.deleteUser(this.user);
        this.user = null;




    }


    public void DeleteVacation(Vacation vacation) {
        model.deleteVacation(vacation);
    }


//    eleteVacation(Vacation aVacation) {db.del
//        deleteUser(User user){db.deleteUser(user);
//            deleteVacationsBySeller(String userEmail){


    ///*********************goTo Functions**********************


    ///**************** Profile ***********************
    public void goToSignUp() {
        goTo="goToSearchView";
        if (this.user == null)
            stage.setScene(signUpScene);
        else {
            popAlertinfo("You Already sign in! \n" +
                    "for new Profile please sign out first");
        }
    }

    public void goToProfileView() {
        goTo = "goToProfileView";
        if (isUserExists(this.user)) {
            profileViewController.loadUserView();
            stage.setScene(ProfileViewScene);
        } else {
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        }
    }

    public void goToEditProfile() {
        goTo = "goToEditProfile";
        if (isUserExists(this.user)) {
            editProfileController.loadUserView();
            stage.setScene(EditProfileScene);
        } else {
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        } }




    public void goTODeleteProfile(){
        goTo="goToSearchView";
        if (isUserExists(this.user)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete your proflie?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.DeleteProfile();
                this.goToSearchView();
            }
        }
        else {
            this.popAlertinfo("Please Sign in!");
            this.goToSignIn();
        } }


    ///**************** Seller ***********************

    public void goToAddVacation() {
        goTo="goToAddVacation";
        if (isUserExists(this.user)) {
            stage.setScene(AddVacationScene);
        }
        else {
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        } }



    public void goToSellerVacationsView(String page) {

        goTo=page;//View or Trade
        if (isUserExists(this.user)) {
            if(page=="View")
                sellerVacationController.loadAllSellerVacations();
            if(page=="Trade")
                sellerVacationController.loadAvailableSellerVacations();
            stage.setScene(SellerVacationiewScene);
        } else {
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        } }

    public void goToSellerVacationsView(){this.goToSellerVacationsView(goTo);}



    public void goToSellerRequest(){
        goTo="goToSellerRequest";
        if (isUserExists(this.user)) {
            sellerRequestsController.loadSellerRequests();
            stage.setScene(SellerRequestViewScene);
        } else {
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        }
    }



    public void goToSellerVacationDetails(int vacationID) {
        if(goTo=="View")
            sellerVacationDetailsController.setButtonsOff();
        if(goTo=="Trade")
            sellerVacationDetailsController.setButtonsON();

        this.vacation = model.getVacationByVacationId(vacationID);
        sellerVacationDetailsController.loadVacationView(this.vacation);
        stage.setScene(SellerVacationDetailsScene);
    }

    public void goToRequestDetails(Order req) {
        goTo="goToSearchView";
        this.vacation = this.getVacation(req.getVacation_id());
        requestDetailsController.loadVacationRequestView(this.vacation,req);
        stage.setScene(RequestDetailsViewScene);
    }

///**************** Buyer ***********************


    public void goToSearchView() {
        goTo="goToSearchView";
        if (isUserExists(this.user))
            searchViewController.setButtonsON();
        else
            searchViewController.setButtonsOff();
        stage.setScene(SearchViewScene);
    }


    public void goToSearchVacationDetails(int vacationID) {
        goTo="goToSearchView";
        vacation = model.getVacationByVacationId(vacationID);

        if (isUserExists(this.user))
            searchVacationDetailsController.setButtonsON();
        else
            searchVacationDetailsController.setButtonsOff();

        searchVacationDetailsController.loadVacationView(vacation);
        stage.setScene(SearchVacationDetailsScene);

    }

    public void goToBuyerVacationsView() {
        goTo="goToBuyerVacationsView";
        if (isUserExists(this.user)) {
            buyerRequestsController.loadBuyerVacations();
            stage.setScene(BuyerRequestsViewScene);
        } else {
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        }
    }

    public void goToBuyerVacationDetails(int vacationID,String status) {
        goTo="goToSearchView";
        vacation = model.getVacationByVacationId(vacationID);
        buyerVacationDetailsController.loadVacation(vacation,status);
        stage.setScene(BuyerVacationDetailsScene);
    }


///************SignIn*********************

    public void goToSignIn() {
        goTo="goToSearchView";
        if (this.user==null)
            stage.setScene(signInScene);
        else
            popAlertinfo("Please Sign in!"); }



    public void SignOut() {
        goTo="goToSearchView";
        if (isUserExists(this.user)) {
            this.user = null;
            goToSearchView();
        }
        else
            popAlertinfo("Please Sign in!");
    }



///******************* Trade Requests *********************

    public void goToInbox_traderequests(){
        goTo="goToInbox_traderequests";
        if (isUserExists(this.user)) {
            traderRequestController.loadTraderRequestInbox();
            stage.setScene(TraderRequestScene);
        }
        else{
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        }
    }
    public void goToOutbox_traderequests(){
        goTo="goToOutbox_traderequests";
        if (isUserExists(this.user)) {
            traderRequestController.loadTraderRequestOutBox();
            stage.setScene(TraderRequestScene);
        }
        else{
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        }
    }


    public void goToDetailsOutBox(TradeRequest tradeRequest){
        if (isUserExists(this.user)) {
            tradeRequestDetailsController.loadTraderRequestOutBox(tradeRequest);
            stage.setScene(TradeRequestDetailsScene);
        }
        else{
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        }
    }
    public void goToDetailsIntBox(TradeRequest tradeRequest){
        if (isUserExists(this.user)) {
            tradeRequestDetailsController.loadTraderRequestInbox(tradeRequest);
            stage.setScene(TradeRequestDetailsScene);
        }
        else{
            popAlertinfo("Please Sign in!");
            stage.setScene(signInScene);
        }
    }



//************ goToPage **************


    public void goToPage(){
        if(this.goTo.equals("goToSearchView"))
            this.goToSearchView();
        if(this.goTo.equals("goToProfileView"))
            this.goToProfileView();
        if(this.goTo.equals("goToEditProfile"))
            this.goToEditProfile();
        if(this.goTo.equals("goToAddVacation"))
            this.goToAddVacation();
        if (this.goTo.equals("View"))
            this.goToSellerVacationsView("View");
        if(this.goTo.equals("Trade"))
            this.goToSellerVacationsView("Trade");
        if (this.goTo.equals("goToBuyerVacationsView"))
            this.goToBuyerVacationsView();
        if(this.goTo.equals("goToSellerVacationsView"))
            this.goToSellerVacationsView();
        if(this.goTo.equals("goToSellerRequest"))
            this.goToSellerRequest();
        if(this.goTo.equals("goToAddVacation"))
            this.goToAddVacation();
        if(this.goTo=="goToInbox_traderequests")
            this.goToInbox_traderequests();
        if(this.goTo=="goToOutbox_traderequests")
            this.goToOutbox_traderequests();


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

