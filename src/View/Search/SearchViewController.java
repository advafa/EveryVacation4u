package View.Search;

import View.TableViewClass;
import App.Vacation;
import Main.ViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class SearchViewController implements Initializable {

    //MenuItems
    @FXML
    public MenuItem SignUp_menu;
    public MenuItem View_profile_menu;
    public MenuItem EditProfile_menu;
    public MenuItem Delete_profile_menu;
    public MenuItem addVac_menu;
    public MenuItem seller_vacations_menu;
    public MenuItem seller_req_menu;
    public MenuItem search_menu;
    public MenuItem buyer_req_menu;
    public MenuItem inbox_traderequests_menu;
    public MenuItem outbox_traderequests_menu;
    public MenuItem SignIn_menu;
    public MenuItem SignOut_menu;
    public MenuItem exit_menu;

//seller_status true=approve, false=decline

    public TableView<TableViewClass> VacationsListTable;
    public TableColumn<TableViewClass, String> colCheckin;
    public TableColumn<TableViewClass, String> colCheckout;
    public TableColumn<TableViewClass, String> colFrom;
    public TableColumn<TableViewClass, String> colTo;
    public TableColumn<TableViewClass, Integer> colPrice;
    public TableColumn<TableViewClass, Integer> colff;
    public TableColumn<TableViewClass, String> colSellerName;

    public DatePicker checkin;
    public DatePicker checkout;
    public ComboBox<String> from;
    public ComboBox<String> to;

    public Button buyReq_btn;
    public Button trade_btn;

    private ObservableList<TableViewClass> VacationsList;

    private TableViewClass clickedRow;

    private ViewModel viewModel;

    private List<Vacation> VacationResultssList;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //*********  Menu Functions **************///
        SignUp_menu.setOnAction(e -> {viewModel.goToSignUp();});
        View_profile_menu.setOnAction(e -> {viewModel.goToProfileView();});
        EditProfile_menu.setOnAction(e -> {viewModel.goToEditProfile();});
        Delete_profile_menu.setOnAction(e -> {viewModel.goTODeleteProfile();});;
        addVac_menu.setOnAction(e -> {viewModel.goToAddVacation();});
        seller_vacations_menu.setOnAction(e -> {viewModel.goToSellerVacationsView("View");});
        seller_req_menu.setOnAction(e -> {viewModel.goToSellerRequest();});
        search_menu.setOnAction(e -> {viewModel.goToSearchView();});
        buyer_req_menu.setOnAction(e -> {viewModel.goToBuyerVacationsView();});
        inbox_traderequests_menu.setOnAction(e -> {viewModel.goToInbox_traderequests();});
        outbox_traderequests_menu.setOnAction(e -> {viewModel.goToOutbox_traderequests();});
        SignIn_menu.setOnAction(e -> {viewModel.goToSignIn();});
        SignOut_menu.setOnAction(e -> {viewModel.SignOut();});
        exit_menu.setOnAction(e -> {System.exit(0);});


        colFrom.setCellValueFactory(new PropertyValueFactory<> ("from"));
        colTo.setCellValueFactory(new PropertyValueFactory<> ("to"));
        colCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        colPrice.setCellValueFactory(new PropertyValueFactory<> ("price"));
        colff.setCellValueFactory(new PropertyValueFactory<> ("off"));
        colSellerName.setCellValueFactory(new PropertyValueFactory<> ("name"));


        colFrom.setStyle("-fx-alignment: BASELINE_CENTER");
        colTo.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckin.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckout.setStyle("-fx-alignment: BASELINE_CENTER");
        colPrice.setStyle("-fx-alignment: BASELINE_CENTER");
        colff.setStyle("-fx-alignment: BASELINE_CENTER");
        colSellerName.setStyle("-fx-alignment: BASELINE_CENTER");

        VacationsList = FXCollections.observableArrayList();

        VacationsListTable.setRowFactory(tv -> {
            TableRow<TableViewClass> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                    this.clickedRow = row.getItem();


                }
            });
            return row;
        });
        this.VacationsListTable.setItems(null);



    }
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void goToDetails(MouseEvent mouseEvent) {

        if(this.clickedRow==null)
            viewModel.popAlertinfo("Please pick a Vacation from Table!");
        else
            this.viewModel.goToSearchVacationDetails(this.clickedRow.getVacation_id());
    }

    public void setAirports() {

        ObservableList<String> AirportsList = FXCollections.observableArrayList();
        List<String> Airports = this.viewModel.getAirports();
        AirportsList.addAll(Airports);
        from.setItems(AirportsList);
        to.setItems(AirportsList);

    }





    public void loadVacationsResults() {

        this.VacationsListTable.setItems(FXCollections.observableArrayList());
        this.VacationsList = FXCollections.observableArrayList();
        TableViewClass addrow;
        for(Vacation vacation : this.VacationResultssList){
            addrow=new TableViewClass(vacation.getVacation_id(),vacation.toStringCheckin(),vacation.toStringCheckout(), vacation.getFrom(),vacation.getto());
            addrow.setPrice(vacation.getSale_price());
            addrow.setOff(vacation.getOff());
            addrow.setName(this.viewModel.getUserNameByEmail(vacation.getSeller()));
            this.VacationsList.add(addrow);
        }


        this.VacationsListTable.setItems(this.VacationsList);
    }


    public void  SimpleSearch(MouseEvent mouseEvent){

        if ((from.getValue() == null)&& (to.getValue()==null)&& (checkin.getValue()==null)&& (checkout.getValue()==null)){
            this.VacationResultssList=this.viewModel.getAllAvailableVacations();
            this.loadVacationsResults();
            this.reset();
        }else {
            if (ValidateSearch()) {
                Vacation aVacation = new Vacation(from.getValue(), to.getValue(), checkin.getValue(), checkout.getValue());
                this.VacationResultssList = this.viewModel.getVacationBySimpleSearch(aVacation);
                if (this.VacationResultssList.isEmpty())
                    this.viewModel.popAlertinfo("Not Found Vacations for you search");
                this.loadVacationsResults();
                this.reset();
            }
        }

    }


    private void reset() {

        checkin.setValue(null);
        checkout.setValue(null);
        from.setValue(null);
        to.setValue(null);
        this.clickedRow=null;
    }

    private boolean ValidateSearch(){

        if (from.getValue() == null){
            this.viewModel.popAlertinfo("Please insert All or Nothing");
            this.reset();
            return false;
        }
        if (to.getValue() == null){
            this.viewModel.popAlertinfo("Please insert All or Nothing");
            this.reset();
            return false;
        }
        if (checkin.getValue() == null){
            this.viewModel.popAlertinfo("Please insert All or Nothing");
            this.reset();
            return false;
        }
        if (checkout.getValue() == null){
            this.viewModel.popAlertinfo("Please insert All or Nothing");
            this.reset();
            return false;
        }


        if(!this.checkin.getValue().isAfter(LocalDate.now().plusDays(1))){
            this.viewModel.popAlertinfo("Invalidate Checkin date. It must be at least one day after today");
            return false;}
        if(!this.checkout.getValue().isAfter(this.checkin.getValue())){
            this.viewModel.popAlertinfo("Invalidate Checkout date. It must be after checkin date");
            return false;
        }
        if(!this.checkout.getValue().isAfter(LocalDate.now())) {
            this.viewModel.popAlertinfo("Invalidate Checkout date. Checkout date already past!");
            return false;
        }
        return true;

    }


    public void addReq (MouseEvent mouseEvent) {
        if (!this.viewModel.isUserExists()) {
            this.viewModel.popAlertinfo("Please Sign in!");
        } else {
            if (this.clickedRow == null)
                viewModel.popAlertinfo("Please pick a Vacation from Table!");
            else {
                this.viewModel.addReq(this.clickedRow.getVacation_id());
                this.reset();
            }
        }
    }
    public void addTradeReq (MouseEvent mouseEvent) {

        if (!this.viewModel.isUserExists()) {
            this.viewModel.popAlertinfo("Please Sign in!");
        } else
            {
            if(this.viewModel.getAvailableVacationsByseller_email().isEmpty())
                viewModel.popAlertinfo("You don't have any vacation for trade!");
            else{
            if (this.clickedRow == null)
                viewModel.popAlertinfo("Please pick a Vacation from Table!");
            else {
                this.viewModel.addTradeReq(this.clickedRow.getVacation_id(), false);
                this.reset();
            }
        }
    }}

}