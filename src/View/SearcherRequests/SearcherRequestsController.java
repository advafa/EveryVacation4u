package View.SearcherRequests;

import App.Request;
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
import java.util.List;
import java.util.ResourceBundle;


public class SearcherRequestsController implements Initializable {

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


//seller_status true=approve, false=decline

    public TableView<TableViewClass> SaleRequstTable;
    public TableColumn<TableViewClass, String> colCheckin;
    public TableColumn<TableViewClass, String> colCheckout;
    public TableColumn<TableViewClass, String> colFrom;
    public TableColumn<TableViewClass, String> colTo;
    public TableColumn<TableViewClass, String> colSellerName;
    public TableColumn<TableViewClass, String> colRequestStatus;


    private ObservableList<TableViewClass> SaleRequst;

    private TableViewClass clickedRow=null;

    private ViewModel viewModel;

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


        colFrom.setCellValueFactory(new PropertyValueFactory<> ("from"));
        colTo.setCellValueFactory(new PropertyValueFactory<> ("to"));
        colCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        colSellerName.setCellValueFactory(new PropertyValueFactory<> ("name"));
        colRequestStatus.setCellValueFactory(new PropertyValueFactory<> ("seller_status"));



        colFrom.setStyle("-fx-alignment: BASELINE_CENTER");
        colTo.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckin.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckout.setStyle("-fx-alignment: BASELINE_CENTER");
        colSellerName.setStyle("-fx-alignment: BASELINE_CENTER");
        colRequestStatus.setStyle("-fx-alignment: BASELINE_CENTER");


        SaleRequst = FXCollections.observableArrayList();

            SaleRequstTable.setRowFactory(tv -> {
                TableRow<TableViewClass> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                        this.clickedRow = row.getItem();


                    }
                });
                return row;
            });



    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void goToDetails(MouseEvent mouseEvent) {
        if(this.clickedRow==null)
    viewModel.popAlertinfo("Please pick a Request row from the Table!");
else
        viewModel.goToSearcherVacationDetails(this.clickedRow.getVacation_id(), this.clickedRow.getSeller_status());

    }







    public void loadSearcherVacations() {

            SaleRequstTable.setItems(FXCollections.observableArrayList());
            SaleRequst = FXCollections.observableArrayList();
            List<Request> SaleRequstList = viewModel.getRequestsBySearcher_email();
            Vacation vacation;
            String checkin;
        String checkout;
        String from;
        String to;
        String name;
        String stat;
        TableViewClass addrow;
            for(Request request : SaleRequstList){
                vacation=viewModel.getVacation(request.getVacation_id());
                from=vacation.getFrom();
                to=vacation.getto();
                checkin=vacation.toStringCheckin();
                checkout=vacation.toStringCheckout();
                name=viewModel.getUserNameByEmail(request.getSeller_email());
                if(request.getSeller_status()==null) {stat="Submit";}
                else {stat=request.getSeller_status()?"Approved":"Declined";}
                addrow=new TableViewClass(request.getVacation_id(),checkin,checkout, from,to,name,stat);
              SaleRequst.add(addrow);
            }


        SaleRequstTable.setItems(SaleRequst);
    }



}


