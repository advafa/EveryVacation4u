package View.SellerVacation;


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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class SellerVacationController implements Initializable {



//seller_status true=approve, false=decline
        @FXML
        public TableView<TableViewClass> SaleRequstTable;
        public TableColumn<TableViewClass, String>  colFrom;
        public TableColumn<TableViewClass, String>  colTo;
        public TableColumn<TableViewClass, String>  colCheckin;
        public TableColumn<TableViewClass, String>  colCheckout;
        public TableColumn<TableViewClass, String>  colVacatinStatus;
        public Button done_btn;
        public Button edit_btn;
        public Button del_btn;
        public Button details;
        public Button can_btn;

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
            colVacatinStatus.setCellValueFactory(new PropertyValueFactory<>("vac_status"));

            colFrom.setStyle("-fx-alignment: BASELINE_CENTER");
            colTo.setStyle("-fx-alignment: BASELINE_CENTER");
            colCheckin.setStyle("-fx-alignment: BASELINE_CENTER");
            colCheckout.setStyle("-fx-alignment: BASELINE_CENTER");
            colVacatinStatus.setStyle("-fx-alignment: BASELINE_CENTER");



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
                viewModel.goToSellerVacationDetails(this.clickedRow.getVacation_id());

        }

    public void goToDone (MouseEvent mouseEvent) {
        if(this.clickedRow==null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("In this send Requst you agree to trade in");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                this.viewModel.addTradeReq(this.clickedRow.getVacation_id(),true);


            }}}


        public void loadAllSellerVacations() {
            this.clickedRow=null;
            this.done_btn.setVisible(false);
            this.details.setVisible(true);
            this.edit_btn.setVisible(true);
            this.del_btn.setVisible(true);
            can_btn.setVisible(false);

            SaleRequstTable.setItems(FXCollections.observableArrayList());
            SaleRequst = FXCollections.observableArrayList();
            List<Vacation> SellerVacationsList = viewModel.getVacationsByseller_email();
            int id;
            String checkin;
            String checkout;
            String from;
            String to;
            String stat;
            TableViewClass addrow;
            for(Vacation vacation : SellerVacationsList){
                id=vacation.getVacation_id();
                from=vacation.getFrom();
                to=vacation.getto();
                checkin=vacation.toStringCheckin();
                checkout=vacation.toStringCheckout();
                stat=vacation.getVacation_status()?"Available":"NOT Available";
                addrow=new TableViewClass(id,checkin,checkout, from,to,stat);
                SaleRequst.add(addrow);
            }


            SaleRequstTable.setItems(SaleRequst);
        }

    public void loadAvailableSellerVacations() {
        this.clickedRow=null;
        this.done_btn.setVisible(true);
        this.details.setVisible(true);
        this.edit_btn.setVisible(false);
        this.del_btn.setVisible(false);
        can_btn.setVisible(true);
        SaleRequstTable.setItems(FXCollections.observableArrayList());
        SaleRequst = FXCollections.observableArrayList();
        List<Vacation> SellerVacationsList = viewModel.getAvailableVacationsByseller_email();
        int id;
        String checkin;
        String checkout;
        String from;
        String to;
        String stat;
        TableViewClass addrow;
        for(Vacation vacation : SellerVacationsList){
            id=vacation.getVacation_id();
            from=vacation.getFrom();
            to=vacation.getto();
            checkin=vacation.toStringCheckin();
            checkout=vacation.toStringCheckout();
            stat=vacation.getVacation_status()?"Available":"NOT Available";
            addrow=new TableViewClass(id,checkin,checkout, from,to,stat);
            SaleRequst.add(addrow);
        }


        SaleRequstTable.setItems(SaleRequst);
    }

    public void goToEditVacation (MouseEvent mouseEvent) {
        if (this.clickedRow == null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else {
            if(!this.clickedRow.getVac_status().equals("Available"))
                this.viewModel.popAlertinfo("This Vacation is NOT Avalible !!!");
            else
            this.viewModel.goToEditVacation(this.clickedRow.getVacation_id());
        }
    }

    public void goToDeleteVacation (MouseEvent mouseEvent) {
        if(this.clickedRow==null)
            viewModel.popAlertinfo("Please pick a Request row from the Table!");
        else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete this Vacation?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.viewModel.DeleteVacation(this.clickedRow.getVacation_id());
                this.viewModel.popAlertinfo("Your Vacation successfully deleted !");
                this.loadAllSellerVacations();
            }}}

    public void goToCancel (MouseEvent mouseEvent){
        viewModel.goToSearchView();
    }

}
