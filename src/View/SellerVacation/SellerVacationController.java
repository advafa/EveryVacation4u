package View.SellerVacation;

import App.Order;
import App.User;
import App.OrderView;
import App.Vacation;
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
import java.util.Optional;
import java.util.ResourceBundle;


public class SellerVacationController implements Initializable {

//seller_status true=approve, false=decline

    public TableView<OrderView> SaleRequstTable;
    public TableColumn<OrderView, String> colVacationId;
    public TableColumn<OrderView, String> colSellerStatus;
    public TableColumn<OrderView, String> colBuyerName;
    public TableColumn<OrderView, String> colPaymentStatus;
    private ObservableList<OrderView> SaleRequst;


    private OrderView clickedRow;

    public Button app_btn;
    public Button dec_btn;

    private ViewModel viewModel;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        user = viewModel.getUser();
        if (this.viewModel.isUserExists(user)) {
            colVacationId.setCellValueFactory(new PropertyValueFactory<>("VacationCode"));
            colBuyerName.setCellValueFactory(new PropertyValueFactory<>("BuyerName"));
            colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("PaymentStatus"));
            colSellerStatus.setCellValueFactory(new PropertyValueFactory<>("RequestStatus"));

            SaleRequst = FXCollections.observableArrayList();

            SaleRequstTable.setRowFactory(tv -> {
                TableRow<OrderView> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                        this.clickedRow = row.getItem();

                        if(this.clickedRow.getStatus()=="Approved"){
                            app_btn.setDisable(false);
                            dec_btn.setDisable(false);}
                        }
                        else{
                            app_btn.setDisable(true);
                            dec_btn.setDisable(true);
                        }

                    });
                return row;
            });

        } else {
            this.viewModel.popAlertinfo("Please Sign in!");
            this.viewModel.goToSignIn();
        }


    }


    public void loadSellerVacations(User user) {

        if (this.viewModel.isUserExists(user)) {
            SaleRequstTable.setItems(FXCollections.observableArrayList());
            SaleRequst = FXCollections.observableArrayList();
            List<Order> SaleRequstList = viewModel.getOrdersByseller_email();

            String buyerName;
            int vacation_id;
            String sellerStatus;
            String buyerStatus;

            for (Order ord : SaleRequstList) {
                buyerName=viewModel.getUserNameByEmail(ord.getBuyer_email());
                vacation_id=ord.getVacation_id();
                sellerStatus=ord.getSeller_status()?"Approved":"Declined";
                buyerStatus=ord.getBuyer_status()?"Paid":"Not yet";
                OrderView orderView = new OrderView(vacation_id, buyerName, sellerStatus,buyerStatus,ord.getBuyer_email());
                    SaleRequst.add(orderView);
            }
            SaleRequstTable.setItems(SaleRequst);
        } else {
            this.viewModel.popAlertinfo("Please Sign in!");
            this.viewModel.goToSignIn();
        }
    }


    public void goToDetails(MouseEvent mouseEvent) {
        Order ord;

        if(this.clickedRow.getStatus()=="Approved"){
            ord=new Order(user.getEmail(),clickedRow.getBuyer_email(),clickedRow.getVacation_id(),true);
            viewModel.goToSellerVacationDetails(this.clickedRow.getVacation_id(),ord);}
        else{
            ord=new Order(user.getEmail(),clickedRow.getBuyer_email(),clickedRow.getVacation_id(),false);
            viewModel.goToSellerVacationDetails(this.clickedRow.getVacation_id(),ord);}

    }

    public void goToPay(MouseEvent mouseEvent) {
        if(this.clickedRow.getStatus()=="Declined") {
            viewModel.popAlerterror("This vacation is not available!");
            return;
        }
        if(!viewModel.getVacationStatus(clickedRow.getVacation_id())){
            viewModel.popAlerterror("This vacation Sold Out!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure this buyer pay in cash?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.viewModel.setVacationStatus(clickedRow.getVacation_id(),false);
            Order ord=new Order(user.getEmail(),clickedRow.getBuyer_email(),clickedRow.getVacation_id(),true);
            this.viewModel.setBuyerStatus(ord,true);
            }
    }


    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void setUser(User user) {
        this.user = user;
    }



    public void Aprrove(MouseEvent mouseEvent){
        if(!viewModel.getVacationStatus(clickedRow.getVacation_id())){
            viewModel.popAlerterror("This vacation Sold Out!");
            return;
        }
        Order ord=new Order(user.getEmail(),clickedRow.getBuyer_email(),clickedRow.getVacation_id(),false);
        this.viewModel.setSellerStatus(ord,true);

    }

    public void Decline(MouseEvent mouseEvent){
        if(!viewModel.getVacationStatus(clickedRow.getVacation_id())){
            viewModel.popAlerterror("This vacation Sold Out!");
            return;
        }
        Order ord=new Order(user.getEmail(),clickedRow.getBuyer_email(),clickedRow.getVacation_id(),false);
        this.viewModel.setSellerStatus(ord,false);

    }


}


