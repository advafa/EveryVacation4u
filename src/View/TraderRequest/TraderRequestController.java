package View.TraderRequest;



import View.TableViewClass;
import App.Vacation;
import App.TradeRequest;
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



public class TraderRequestController implements Initializable {

    private TableViewClass clickedRow;
    private ViewModel viewModel;
    private TradeRequest req;

    @FXML
    public TableView<TableViewClass> RradeRequstsTable;
    public TableColumn<TableViewClass, String> colCheckin;
    public TableColumn<TableViewClass, String> colCheckout;
    public TableColumn<TableViewClass, String> colFrom;
    public TableColumn<TableViewClass, String> colTo;
    public TableColumn<TableViewClass, Integer> colPrice;
    public TableColumn<TableViewClass, String> colCheckinT;
    public TableColumn<TableViewClass, String> colCheckoutT;
    public TableColumn<TableViewClass, String> colFromT;
    public TableColumn<TableViewClass, String> colToT;
    public TableColumn<TableViewClass, Integer> colPriceT;
    public TableColumn<TableViewClass, String> colRequestStatus;
    private ObservableList<TableViewClass> RradeRequsts;


    //MenuItems
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

    public Button app_btn;
    public Button dec_btn;
    public Button detailsout_btn;
    public Button detailsin_btn;



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

        colCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colCheckinT.setCellValueFactory(new PropertyValueFactory<>("checkinT"));
        colCheckoutT.setCellValueFactory(new PropertyValueFactory<>("checkoutT"));
        colFromT.setCellValueFactory(new PropertyValueFactory<>("fromT"));
        colToT.setCellValueFactory(new PropertyValueFactory<>("toT"));
        colPriceT.setCellValueFactory(new PropertyValueFactory<>("priceT"));
        colRequestStatus.setCellValueFactory(new PropertyValueFactory<>("seller_status"));

        colCheckinT.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckoutT.setStyle("-fx-alignment: BASELINE_CENTER");
        colFromT.setStyle("-fx-alignment: BASELINE_CENTER");
        colToT.setStyle("-fx-alignment: BASELINE_CENTER");
        colPriceT.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckin.setStyle("-fx-alignment: BASELINE_CENTER");
        colCheckout.setStyle("-fx-alignment: BASELINE_CENTER");
        colFrom.setStyle("-fx-alignment: BASELINE_CENTER");
        colTo.setStyle("-fx-alignment: BASELINE_CENTER");
        colPrice.setStyle("-fx-alignment: BASELINE_CENTER");
        colRequestStatus.setStyle("-fx-alignment: BASELINE_CENTER");

        RradeRequsts = FXCollections.observableArrayList();

        RradeRequstsTable.setRowFactory(tv -> {
            TableRow<TableViewClass> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                    this.clickedRow = row.getItem();
                    this.req= new TradeRequest(clickedRow.getSeller_email(), clickedRow.getTrader_email(), clickedRow.getVacation_id(), clickedRow.getVacation_idT(),true);



                }
            });
            return row;
        });


    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }



     public void loadTraderRequestInbox(){

         this.app_btn.setVisible(true);
         this.dec_btn.setVisible(true);
         this.detailsout_btn.setVisible(false);
         this.detailsin_btn.setVisible(true);

         this.clickedRow = null;
         List<TradeRequest> TradeRequstList = viewModel.getTradeRequestByseller_email();
         loadRequests(TradeRequstList);
     }

     public void loadTraderRequestOutBox(){
         this.app_btn.setVisible(false);
         this.dec_btn.setVisible(false);
         this.detailsout_btn.setVisible(true);
         this.detailsin_btn.setVisible(false);

        this.clickedRow = null;
        List<TradeRequest> TradeRequstList = viewModel.getTradeRequestByTrader_email();
        loadRequests(TradeRequstList);
             }



private void loadRequests(List<TradeRequest> TradeRequstList){
    RradeRequstsTable.setItems(FXCollections.observableArrayList());
    RradeRequsts = FXCollections.observableArrayList();
    String stat;
    Vacation vacation, vacation4trade ;
    TableViewClass addrow;
    for (TradeRequest currentReq : TradeRequstList) {

        vacation = viewModel.getVacation(currentReq.getVacation_id());
        vacation4trade=viewModel.getVacation(currentReq.getVacationtoTrade_id());
        stat=currentReq.toStringSellerStatus();
            addrow = new TableViewClass(vacation.getVacation_id(), vacation.toStringCheckin(),vacation.toStringCheckout(),vacation.getFrom(),vacation.getto());
            addrow.setPrice(vacation.getSale_price());
            addrow.setCheckinT(vacation4trade.toStringCheckin());
            addrow.setCheckoutT(vacation4trade.toStringCheckout());
            addrow.setFromT(vacation4trade.getFrom());
            addrow.setTooT(vacation4trade.getto());
            addrow.setPriceT(vacation4trade.getSale_price());
            addrow.setVacation_idT(vacation4trade.getVacation_id());
            addrow.setSeller_status(stat);


            RradeRequsts.add(addrow);
        }



    RradeRequstsTable.setItems(RradeRequsts);

}



    public void ApproveTradeRequest(MouseEvent mouseEvent){
        if (this.clickedRow == null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else{
            if(!viewModel.getVacationStatus(clickedRow.getVacation_id())){
                viewModel.popAlerterror("Vacation is NOT Available!!!");
                return;
            }
            if(!viewModel.getVacationStatus(clickedRow.getVacation_idT())){
                viewModel.popAlerterror("Your Vacation is NOT Available!!!");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you agree to this trade?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.req.setSeller_status(true);
                this.viewModel.setSellerStatus(this.req,true);
                this.viewModel.setVacationStatus(clickedRow.getVacation_id(), false);
                this.viewModel.setVacationStatus(clickedRow.getVacation_idT(), false);
                this.viewModel.popAlertinfo("Your Trade successfully saved!");
                this.loadTraderRequestInbox();
            }


        }}

    public void DeclineTradeRequest(MouseEvent mouseEvent) {
        if (this.clickedRow == null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else {

            if(!viewModel.getVacationStatus(clickedRow.getVacation_id())){
                viewModel.popAlerterror("Vacation is NOT Available!!!");
                return;
            }
            if(!viewModel.getVacationStatus(clickedRow.getVacation_idT())){
                viewModel.popAlerterror("Your Vacation is NOT Available!!!");
                return;
            }
            this.req.setSeller_status(false);
            this.viewModel.setSellerStatus(this.req,false);
            this.viewModel.popAlertinfo("Your rejection successfully saved!");
            this.loadTraderRequestInbox();

        }
    }

    public void goToDetailsOutBox (MouseEvent mouseEvent) {
        if (this.clickedRow == null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else{
            if(!this.clickedRow.getSeller_status().equals("Submit"))
                this.req.setSeller_status(this.clickedRow.getSeller_status().equals("Approved"));
            viewModel.goToDetailsOutBox(this.req);
    }}

    public void goToDetailsIntBox (MouseEvent mouseEvent) {
        if (this.clickedRow == null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else
        if(!this.clickedRow.getSeller_status().equals("Submit"))
            this.req.setSeller_status(this.clickedRow.getSeller_status().equals("Approved"));
            viewModel.goToDetailsIntBox (this.req);
    }


}
