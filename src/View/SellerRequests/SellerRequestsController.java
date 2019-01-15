package View.SellerRequests;

import App.Request;
import View.TableViewClass;
import App.Vacation;
import App.Payment;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class SellerRequestsController implements Initializable {

//seller_status true=approve, false=decline

    public TableView<TableViewClass> SaleRequstTable;
    public TableColumn<TableViewClass, String> colFrom;
    public TableColumn<TableViewClass, String> colTo;
    public TableColumn<TableViewClass, String> colCheckin;
    public TableColumn<TableViewClass, String> colCheckout;
    public TableColumn<TableViewClass, String> colSearcherName;
    public TableColumn<TableViewClass, String> colRequestStatus;

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
    public MenuItem searcher_req_menu;
    public MenuItem inbox_traderequests_menu;
    public MenuItem outbox_traderequests_menu;
    public MenuItem SignIn_menu;
    public MenuItem SignOut_menu;
    public MenuItem exit_menu;



    private ObservableList<TableViewClass> SaleRequst;

    private TableViewClass clickedRow;

    private ViewModel viewModel;

    private Request req;

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
        searcher_req_menu.setOnAction(e -> {viewModel.goToSearcherVacationsView();});
        inbox_traderequests_menu.setOnAction(e -> {viewModel.goToInbox_traderequests();});
        outbox_traderequests_menu.setOnAction(e -> {viewModel.goToOutbox_traderequests();});
        SignIn_menu.setOnAction(e -> {viewModel.goToSignIn();});
        SignOut_menu.setOnAction(e -> {viewModel.SignOut();});
        exit_menu.setOnAction(e -> {System.exit(0);});


        colFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        colCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        colSearcherName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRequestStatus.setCellValueFactory(new PropertyValueFactory<>("seller_status"));

        colFrom.setStyle("-fx-alignment: BASELINE_CENTER");
        colTo.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckin.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckout.setStyle("-fx-alignment: BASELINE_CENTER");
        colSearcherName.setStyle("-fx-alignment: BASELINE_CENTER");
        colRequestStatus.setStyle("-fx-alignment: BASELINE_CENTER");

        colCheckin.setSortType(TableColumn.SortType.DESCENDING);
        colCheckout.setSortType(TableColumn.SortType.DESCENDING);

        SaleRequst = FXCollections.observableArrayList();

        SaleRequstTable.setRowFactory(tv -> {
            TableRow<TableViewClass> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                    this.clickedRow = row.getItem();
                    this.req= new Request(clickedRow.getSeller_email(), clickedRow.getSearcher_email(), clickedRow.getVacation_id(), false);
                    this.req.setSeller_status(clickedRow.getSellerStatus());

                }
            });
            return row;
        });


    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }




    public void loadSellerRequests() {
        this.clickedRow = null;
        SaleRequstTable.setItems(FXCollections.observableArrayList());
        SaleRequst = FXCollections.observableArrayList();
        List<Request> SaleRequstList = viewModel.getRequestsByseller_email();
        Vacation vacation;
        String checkin;
        String checkout;
        String from;
        String to;
        String searcher_name;
        String stat;
        TableViewClass addrow;
        for (Request currentReq : SaleRequstList) {
            vacation = viewModel.getVacation(currentReq.getVacation_id());
            if (vacation.getVacation_status()) {
                from = vacation.getFrom();
                to = vacation.getto();
                checkin = vacation.toStringCheckin();
                checkout = vacation.toStringCheckout();
                searcher_name = viewModel.getUserNameByEmail(currentReq.getSearcher_email());
                stat=currentReq.toStringSellerStatus();
                addrow = new TableViewClass(currentReq.getVacation_id(), checkin, checkout, from, to, searcher_name, stat);
                addrow.setSearcher_email(currentReq.getSearcher_email());
                addrow.setSeller_email(currentReq.getSeller_email());
                SaleRequst.add(addrow);
            }
        }


        SaleRequstTable.setItems(SaleRequst);

    }


    public void ApprovePayment(MouseEvent mouseEvent) {
        if (this.clickedRow == null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else{
        if (this.clickedRow.getSeller_status().equals("Declined")) {
            viewModel.popAlerterror("You Declined This Sale Request !");
            this.clickedRow = null;
            return;
        }
        if (this.clickedRow.getSeller_status().equals("")) {
            viewModel.popAlerterror("Please Approved This Sale Request first!");
            this.clickedRow = null;
            return;
        }
        if (this.clickedRow.getSeller_status().equals("Approved")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure this searcher payed in cash?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Payment payment=new Payment(clickedRow.getSeller_email(), clickedRow.getSearcher_email(), clickedRow.getVacation_id());
                this.viewModel.addPayment(payment);
                this.viewModel.setVacationStatus(clickedRow.getVacation_id(), false);
                this.viewModel.popAlertinfo("Your Approve of Payment successfully saved!");
                loadSellerRequests();
            }
        }

    }}



    public void ApproveSaleRequest(MouseEvent mouseEvent){
        if (this.clickedRow == null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else{
            if(!viewModel.getVacationStatus(clickedRow.getVacation_id())){
                viewModel.popAlerterror("This vacation Sold Out!");
                return;
            }
            this.req.setSeller_status(true);
            this.viewModel.setSellerStatus(this.req,true);
            loadSellerRequests();

        }}

        public void DeclineSaleRequest(MouseEvent mouseEvent) {
            if (this.clickedRow == null)
                viewModel.popAlertinfo("Please pick a Request row from the Table!");
            else {
                if (!viewModel.getVacationStatus(clickedRow.getVacation_id())) {
                    viewModel.popAlerterror("This vacation Sold Out!");
                    return;
                }
                this.req.setSeller_status(false);
                this.viewModel.setSellerStatus(this.req, false);
                loadSellerRequests();

            }
        }

        public void goToDetails(MouseEvent mouseEvent) {
        if (this.clickedRow == null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else
            viewModel.goToRequestDetails(this.req);
    }



}


