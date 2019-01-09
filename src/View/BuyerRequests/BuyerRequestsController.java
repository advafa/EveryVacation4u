package View.BuyerRequests;

import App.Order;
import App.TableViewClass;
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
import java.util.ResourceBundle;


public class BuyerRequestsController implements Initializable {

//seller_status true=approve, false=decline

    public TableView<TableViewClass> SaleRequstTable;
    public TableColumn<TableViewClass, Integer> colVacationId;
    public TableColumn<TableViewClass, String> colFrom;
    public TableColumn<TableViewClass, String> colTo;
    public TableColumn<TableViewClass, String> colCheckin;
    public TableColumn<TableViewClass, String> colCheckout;
    public TableColumn<TableViewClass, String> colSellerName;
    public TableColumn<TableViewClass, String> colRequestStatus;


    private ObservableList<TableViewClass> SaleRequst;

    private TableViewClass clickedRow;

    private ViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colVacationId.setCellValueFactory(new PropertyValueFactory<>("vacation_id"));
        colFrom.setCellValueFactory(new PropertyValueFactory<> ("from"));
        colTo.setCellValueFactory(new PropertyValueFactory<> ("to"));
        colCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        colSellerName.setCellValueFactory(new PropertyValueFactory<> ("name"));
        colRequestStatus.setCellValueFactory(new PropertyValueFactory<> ("seller_status"));



        colVacationId.setStyle("-fx-alignment: BASELINE_CENTER");
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
        viewModel.goToBuyerVacationDetails(this.clickedRow.getVacation_id(), this.clickedRow.getSeller_status());

    }







    public void loadBuyerVacations() {

            SaleRequstTable.setItems(FXCollections.observableArrayList());
            SaleRequst = FXCollections.observableArrayList();
            List<Order> SaleRequstList = viewModel.getOrdersByBuyer_email();
            Vacation vacation;
            String checkin;
        String checkout;
        String from;
        String to;
        String name;
        String stat;
        TableViewClass addrow;
            for(Order order : SaleRequstList){
                vacation=viewModel.getVacation(order.getVacation_id());
                from=vacation.getFrom();
                to=vacation.getto();
                checkin=vacation.toStringCheckin();
                checkout=vacation.toStringCheckout();
                name=viewModel.getUserNameByEmail(order.getSeller_email());
                if(order.getSeller_status()==null) {stat="Submit";}
                else {stat=order.getSeller_status()?"Approved":"Declined";}
                addrow=new TableViewClass(order.getVacation_id(),checkin,checkout, from,to,name,stat);
              SaleRequst.add(addrow);
            }


        SaleRequstTable.setItems(SaleRequst);
    }



}


