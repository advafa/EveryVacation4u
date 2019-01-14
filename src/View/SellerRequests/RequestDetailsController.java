package View.SellerRequests;

import App.Request;
import App.Payment;
import App.Vacation;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;


import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



public class RequestDetailsController implements Initializable {

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
    public Label sellerStatus;
    public Label vac_status;
    public Label buyer_email;
    public Label buyer_status;
    public Label name;

    private ViewModel viewModel;
    private Vacation vacation;
    private Request sale_requst;


    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    public void setViewModel(ViewModel viewModel) { this.viewModel = viewModel; }



public void loadVacationRequestView(Vacation vacation, Request saleRequst) {
    this.vacation = vacation;
    this.sale_requst=saleRequst;

    this.check_in.setText(vacation.toStringCheckin());
    this.check_out.setText(vacation.toStringCheckout());
    this.from.setText(vacation.getFrom());
    this.to.setText(vacation.getto());
    this.airline.setText(vacation.getAirline());

    if(vacation.getBackFlight()){
        this.backf.setText("Back flight Included");}
    else{this.backf.setText("Back flight *NOT* Included");}

    this.handbag.setText(vacation.getHand_bag());
    this.checkbag.setText(vacation.getChecked_bag());
    this.conectin.setText(vacation.getConnec_flight());
    this.vac_type.setText(vacation.getVacation_type());
    this.tic_type.setText(vacation.getTicket_type());


    if(vacation.getHotel()){
        this.hotel_in.setText("Hotel Included:");
        this.hotel_type.setText(vacation.getHotel_type());
        this.hotel_raiting.setText("Hotel Rating: "+ vacation.getHotel_raiting()+" Stars");
    }else{
        this.hotel_in.setText("Hotel *NOT* Included");
        this.hotel_type.setText("");
        this.hotel_raiting.setText("");
    }

    this.num.setText(""+vacation.getNum_of_tickets());
    this.price.setText(vacation.getSale_price()+"$");
    this.original_price.setText(vacation.getOriginal_price()+"$");
    this.buyer_status.setText(saleRequst.toStringBuyer_status());
    this.sellerStatus.setText(this.sale_requst.toStringSellerStatus());

    if(vacation.getVacation_status()) {
        if (this.sale_requst.getSeller_status()) {
            this.buyer_email.setVisible(true);
            this.buyer_email.setText("Contact Buyer: " + this.sale_requst.getBuyer_email());
            this.vac_status.setText("You Approved this Request");
        } else{
            this.buyer_email.setVisible(false);
            if(this.sale_requst.getSeller_status()!=null)
                this.vac_status.setText("You Declined this Request");
            else
                this.vac_status.setText("Please Approve or Decline this Sale Request!");
    }}
    else {this.vac_status.setText("Sold out");
        this.buyer_email.setVisible(false);
    }

    this.name.setText(viewModel.getUserNameByEmail(this.sale_requst.getBuyer_email()));
}





    public void ApproveSaleRequest(MouseEvent mouseEvent){

    if(!this.vacation.getVacation_status()){
        viewModel.popAlerterror("This Vacation is Not Available!");}
    else{
        this.sale_requst.setSeller_status(true);
        this.viewModel.setSellerStatus(this.sale_requst,true);
        this.viewModel.popAlertinfo("Your Approvement successfully saved!");
        this.loadVacationRequestView(this.vacation,this.sale_requst); }
}

    public void DeclineSaleRequest(MouseEvent mouseEvent) {

        if(!this.vacation.getVacation_status()){
            viewModel.popAlerterror("This Vacation is Not Available!");}
        else{
            this.sale_requst.setSeller_status(false);
            this.viewModel.setSellerStatus(this.sale_requst,false);
            this.viewModel.popAlertinfo("Your rejection successfully saved!");
            this.loadVacationRequestView(this.vacation,this.sale_requst); }
    }


    public void ApprovePayment(MouseEvent mouseEvent) {
        if (this.sale_requst.getSeller_status() == null) {
            viewModel.popAlerterror("Please Approved This Sale Request first!");
            return;}
        if (!this.sale_requst.getSeller_status()) {
            this.viewModel.popAlerterror("You Declined This Sale Request !");
            return;
        }
        if (this.vacation.getVacation_status()&& this.sale_requst.getSeller_status()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure this buyer payed in cash?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    this.vacation.setVacation_status(false);
                    this.sale_requst.setBuyer_status(true);

                    this.viewModel.setVacationStatus(this.vacation.getVacation_id(), false);
                    this.viewModel.setBuyerStatus(this.sale_requst, true);
                    Payment payment=new Payment(this.sale_requst.getSeller_email(), this.sale_requst.getBuyer_email(), this.sale_requst.getVacation_id());
                    this.viewModel.addPayment(payment);
                    this.viewModel.popAlertinfo("Your Approve of Payment successfully saved!");
                    loadVacationRequestView(this.vacation,this.sale_requst);


            } }}


    public void Back (MouseEvent mouseEvent) {

        this.viewModel.goToSellerRequest();
    }

}
