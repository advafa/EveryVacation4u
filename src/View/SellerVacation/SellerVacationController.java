package View.SellerVacation;


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
import javafx.scene.control.Button;

public class SellerVacationController implements Initializable {



//seller_status true=approve, false=decline

        public TableView<TableViewClass> SaleRequstTable;
        public TableColumn<TableViewClass, Integer> colVacationId;
        public TableColumn<TableViewClass, String>  colFrom;
        public TableColumn<TableViewClass, String>  colTo;
        public TableColumn<TableViewClass, String>  colCheckin;
        public TableColumn<TableViewClass, String>  colCheckout;
        public TableColumn<TableViewClass, String>  colVacatinStatus;
        public Button done_btn;

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
            colVacatinStatus.setCellValueFactory(new PropertyValueFactory<>("vac_status"));

            colVacationId.setStyle("-fx-alignment: BASELINE_CENTER");
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
        else
            if(this.clickedRow.getVac_status()== "Available")
            viewModel.addReq(this.clickedRow.getVacation_id());
            else
                viewModel.popAlertinfo("This Vacation is NOT Available!");
    }


        public void loadAllSellerVacations() {
            this.done_btn.setVisible(false);
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
        this.done_btn.setVisible(true);
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


    }
