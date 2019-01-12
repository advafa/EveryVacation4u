package View.TraderRequest;



import App.TradeRequest;
import App.Vacation;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;


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


public class TradeRequestDetailsController implements Initializable {

    @FXML
    public Label check_in;
    public Label check_out;
    public Label from;
    public Label to;
    public Label airline;
    public Label backf;
    public Label handbag;
    public Label checkbag;
    public Label conectin;
    public Label vac_type;
    public Label tic_type;
    public Label hotel_in;
    public Label hotel_type;
    public Label hotel_raiting;
    public Label num;
    public Label price;
    public Label original_price;
    public Label off;
    public Label vac_stat;

    public Label check_in1;
    public Label check_out1;
    public Label from1;
    public Label to1;
    public Label airline1;
    public Label backf1;
    public Label handbag1;
    public Label checkbag1;
    public Label conectin1;
    public Label vac_type1;
    public Label tic_type1;
    public Label hotel_in1;
    public Label hotel_type1;
    public Label hotel_raiting1;
    public Label num1;
    public Label price1;
    public Label off1;
    public Label vac_stat1;
    public Label name;

    public Label sellerStatus;
    public Label seller_email;



    public Button app_btn;
    public Button dec_btn;
    public Button backInBox_btn;
    public Button backOutBox_btn;


    private ViewModel viewModel;
    private Vacation Myvacation;
    private Vacation vacationTrade;
    private TradeRequest tradeRequest;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void loadTraderRequestInbox(TradeRequest tradeRequestDetails){
        this.app_btn.setVisible(true);
        this.dec_btn.setVisible(true);
        this.backInBox_btn.setVisible(true);
        this.backOutBox_btn.setVisible(false);
        this.loadRequestsDetails(tradeRequestDetails);
        if(this.vacationTrade.getVacation_status()&& this.Myvacation.getVacation_status() && this.tradeRequest.getSeller_status())
        {
            seller_email.setVisible(true);
            seller_email.setText("Contact Trader: "+this.tradeRequest.getTrader_email());
            }
        else
            seller_email.setVisible(false);

        }
    public void loadTraderRequestOutBox(TradeRequest tradeRequestDetails){

        this.app_btn.setVisible(false);
        this.dec_btn.setVisible(false);
        this.backInBox_btn.setVisible(false);
        this.backOutBox_btn.setVisible(true);
        this.loadRequestsDetails(tradeRequestDetails);
        if(this.vacationTrade.getVacation_status()&& this.Myvacation.getVacation_status() && this.tradeRequest.getSeller_status())
        {seller_email.setVisible(true);
            seller_email.setText("Contact Seller: "+this.tradeRequest.getSeller_email());}
            else
            seller_email.setVisible(false);


    }

    private void loadRequestsDetails(TradeRequest tradeRequestDetails){

        this.tradeRequest=tradeRequestDetails;
        this.Myvacation= this.viewModel.getVacation(tradeRequestDetails.getVacationtoTrade_id());
        this.vacationTrade=this.viewModel.getVacation(tradeRequestDetails.getVacation_id());

        this.check_in.setText(Myvacation.toStringCheckin());
        this.check_out.setText(Myvacation.toStringCheckout());
        this.from.setText(Myvacation.getFrom());
        this.to.setText(Myvacation.getto());
        this.airline.setText(Myvacation.getAirline());
        if(Myvacation.getBackFlight()){
            this.backf.setText("Back flight Included");}
        else{this.backf.setText("Back flight *NOT* Included");}
        this.handbag.setText(Myvacation.getHand_bag());
        this.checkbag.setText(Myvacation.getChecked_bag());
        this.conectin.setText(Myvacation.getConnec_flight());
        this.vac_type.setText(Myvacation.getVacation_type());
        this.tic_type.setText(Myvacation.getTicket_type());
        if(Myvacation.getHotel()){
            this.hotel_in.setText("Hotel Included:");
            this.hotel_type.setText(Myvacation.getHotel_type());
            this.hotel_raiting.setText("Hotel Rating: "+ Myvacation.getHotel_raiting()+" Stars");
        }else{
            this.hotel_in.setText("Hotel *NOT* Included");
            this.hotel_type.setText("");
            this.hotel_raiting.setText("");
        }

        this.num.setText(""+Myvacation.getNum_of_tickets());
        this.price.setText(Myvacation.getSale_price()+"$");
        this.off.setText(Myvacation.getOff()+"%");
        this.vac_stat.setText(Myvacation.toStringVacation_status());


        this.check_in1.setText(vacationTrade.toStringCheckin());
        this.check_out1.setText(vacationTrade.toStringCheckout());
        this.from1.setText(vacationTrade.getFrom());
        this.to1.setText(vacationTrade.getto());
        this.airline1.setText(vacationTrade.getAirline());
        if(vacationTrade.getBackFlight()){
            this.backf1.setText("Back flight Included");}
        else{this.backf1.setText("Back flight *NOT* Included");}
        this.handbag1.setText(vacationTrade.getHand_bag());
        this.checkbag1.setText(vacationTrade.getChecked_bag());
        this.conectin1.setText(vacationTrade.getConnec_flight());
        this.vac_type1.setText(vacationTrade.getVacation_type());
        this.tic_type1.setText(vacationTrade.getTicket_type());
        if(vacationTrade.getHotel()){
            this.hotel_in1.setText("Hotel Included:");
            this.hotel_type1.setText(vacationTrade.getHotel_type());
            this.hotel_raiting1.setText("Hotel Rating: "+ vacationTrade.getHotel_raiting()+" Stars");
        }else{
            this.hotel_in1.setText("Hotel *NOT* Included");
            this.hotel_type1.setText("");
            this.hotel_raiting1.setText("");
        }

        this.num1.setText(""+vacationTrade.getNum_of_tickets());
        this.price1.setText(vacationTrade.getSale_price()+"$");
        this.original_price.setText(vacationTrade.getOriginal_price()+"$");
        this.off1.setText(vacationTrade.getOff()+"%");
        this.vac_stat1.setText(vacationTrade.toStringVacation_status());

        this.name.setText(viewModel.getUserNameByEmail(this.vacationTrade.getSeller()));
        this.sellerStatus.setText(this.tradeRequest.toStringBuyer_status());


    }





    public void ApproveTradeRequest(MouseEvent mouseEvent){

            if(!viewModel.getVacationStatus(this.tradeRequest.getVacation_id())){
                viewModel.popAlerterror("Vacation is NOT Available!!!");
                return;
            }
            if(!viewModel.getVacationStatus(this.tradeRequest.getVacationtoTrade_id())){
                viewModel.popAlerterror("Your Vacation is NOT Available!!!");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you agree to this trade?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.tradeRequest.setSeller_status(true);
                this.viewModel.setSellerStatus(this.tradeRequest,true);
                this.viewModel.setVacationStatus(this.tradeRequest.getVacation_id(), false);
                this.viewModel.setVacationStatus(this.tradeRequest.getVacationtoTrade_id(), false);
                this.viewModel.popAlertinfo("Your Trade successfully saved!");
                this.loadTraderRequestInbox(this.tradeRequest);
            }

    }

    public void DeclineTradeRequest(MouseEvent mouseEvent) {


            if(!viewModel.getVacationStatus(this.tradeRequest.getVacation_id())){
                viewModel.popAlerterror("Vacation is NOT Available!!!");
                return;
            }
            if(!viewModel.getVacationStatus(this.tradeRequest.getVacationtoTrade_id())){
                viewModel.popAlerterror("Your Vacation is NOT Available!!!");
                return;
            }
            this.tradeRequest.setSeller_status(false);
            this.viewModel.setSellerStatus(this.tradeRequest,false);
            this.viewModel.popAlertinfo("Your rejection successfully saved!");
            this.loadTraderRequestInbox(this.tradeRequest);

        }

    public void  BackToOutBox (MouseEvent mouseEvent) {
        this.viewModel.goToOutbox_traderequests();
    }

    public void BackToIntBox (MouseEvent mouseEvent) {
        this.viewModel.goToInbox_traderequests();
    }


}



