package View.BuyerVacation;

import App.Order;
import App.User;
import App.OrderView;
import Main.ViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class BuyerVacationsController implements Initializable {

//seller_status true=approve, false=decline

    public TableView<OrderView> SaleRequstTable;
    public TableColumn<OrderView, String> colVacationId;
    public TableColumn<OrderView, String> colSellerName;
    public TableColumn<OrderView, String> colRequestStatus;
    private ObservableList<OrderView> SaleRequst;

    private OrderView clickedRow;

    public Button buy;

    private ViewModel viewModel;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            colVacationId.setCellValueFactory(new PropertyValueFactory<>("VacationCode"));
            colSellerName.setCellValueFactory(new PropertyValueFactory<>("SellerName"));
            colRequestStatus.setCellValueFactory(new PropertyValueFactory<>("RequestStatus"));

            SaleRequst = FXCollections.observableArrayList();

            SaleRequstTable.setRowFactory(tv -> {
                TableRow<OrderView> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                        this.clickedRow = row.getItem();

                        if(this.clickedRow.getStatus()=="Approved"){
                            buy.setDisable(false);}
                        else{
                            buy.setDisable(true);
                        }

                    }
                });
                return row;
            });



    }


    public void goToDetails(MouseEvent mouseEvent) {
        if(this.clickedRow.getStatus()=="Approved"){
        viewModel.goToBuyerVacationDetails(this.clickedRow.getVacation_id(),true);}
        else{
            viewModel.goToBuyerVacationDetails(this.clickedRow.getVacation_id(),false);}

    }

    public void goToPay(MouseEvent mouseEvent) {
        if(this.clickedRow.getStatus()=="Declined") {
            viewModel.popAlerterror("This vacation is not available4U!");
            return;
        }
        if(!viewModel.getVacationStatus(clickedRow.getVacation_id())){
            viewModel.popAlerterror("This vacation Sold Out!");
            return;
        }

//        viewModel.goToPayment(this.clickedRow.getVacation_id());

    }


    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void setUser(User user) {
        this.user = user;
    }




    public void loadBuyerVacations(User user) {

        if (this.viewModel.isUserExists(user)) {
            SaleRequstTable.setItems(FXCollections.observableArrayList());
            SaleRequst = FXCollections.observableArrayList();
            List<Order> SaleRequstList = viewModel.getOrdersByBuyer_email();

            String sellerName;
            int vacation_id;
            String sellerStatus;

            for (Order ord : SaleRequstList) {
                sellerName=viewModel.getUserNameByEmail(ord.getSeller_email());
                vacation_id=ord.getVacation_id();
                sellerStatus=ord.getSeller_status()?"Approved":"Declined";
                OrderView orderView = new OrderView(vacation_id, sellerName, sellerStatus);
                SaleRequst.add(orderView);
            }
            SaleRequstTable.setItems(SaleRequst);
        } else {
            this.viewModel.popAlertinfo("Please Sign in!");
            this.viewModel.goToSignIn();
        }
    }



}


