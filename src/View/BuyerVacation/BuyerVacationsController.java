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

    public TableView<Order> SaleRequstTable;
    public TableColumn<Order, Integer> colVacationId;
    public TableColumn<Order, String> colSellerName;
    public TableColumn<Order, String> colRequestStatus;
    private ObservableList<Order> SaleRequst;

    private Order clickedRow;



    private ViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            colVacationId.setCellValueFactory(new PropertyValueFactory<>("vacation_id"));
            colSellerName.setCellValueFactory(new PropertyValueFactory<> ("buyer_email"));
            colRequestStatus.setCellValueFactory(new PropertyValueFactory<> ("seller_email"));

            SaleRequst = FXCollections.observableArrayList();

            SaleRequstTable.setRowFactory(tv -> {
                TableRow<Order> row = new TableRow<>();
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
        viewModel.goToBuyerVacationDetails(this.clickedRow.getVacation_id(),this.clickedRow.getSeller_status());}







    public void loadBuyerVacations() {

            SaleRequstTable.setItems(FXCollections.observableArrayList());
            SaleRequst = FXCollections.observableArrayList();
            List<Order> SaleRequstList = viewModel.getOrdersByBuyer_email();

            for(Order order : SaleRequstList){

                SaleRequst.add(order);
            }


        SaleRequstTable.setItems(SaleRequst);
    }



}


