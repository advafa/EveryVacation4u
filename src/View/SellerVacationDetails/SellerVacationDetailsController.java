package View.SellerVacationDetails;

import App.Order;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import App.User;
import App.Vacation;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;





public class SellerVacationDetailsController implements Initializable{

    @FXML
    public javafx.scene.control.Label check_in;
    public javafx.scene.control.Label check_out;
    public javafx.scene.control.Label from;
    public javafx.scene.control.Label to;
    public javafx.scene.control.Label airline;
    public javafx.scene.control.Label backf;
    public javafx.scene.control.Label handbag;
    public javafx.scene.control.Label checkbag;
    public javafx.scene.control.Label conectin;
    public javafx.scene.control.Label vac_type;
    public javafx.scene.control.Label tic_type;
    public javafx.scene.control.Label hotel_in;
    public javafx.scene.control.Label hotel_type;
    public javafx.scene.control.Label hotel_raiting;
    public javafx.scene.control.Label num;
    public javafx.scene.control.Label price;
    public javafx.scene.control.Label off;
    public javafx.scene.control.Label buyerStatus;
    public javafx.scene.control.Label vac_status;

    private ViewModel viewModel;
    private User user;
    private Vacation vacation;
    private Order order;

    public Button back;
    public Button dec_btn;
    public Button app_btn;
    public Button payment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(this.viewModel.isUserExists(user)){

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
            this.off.setText(vacation.getOff()+"%");

            if(vacation.getVacation_status()){
                this.vac_status.setText("Available for sale");
                dec_btn.setDisable(false);
                app_btn.setDisable(false);
                payment.setDisable(false);
            }

            else {this.vac_status.setText("Sold out");
                dec_btn.setDisable(true);
                app_btn.setDisable(true);
                payment.setDisable(true);}

            if(this.order.getBuyer_status()){
                this.buyerStatus.setText("Paid");}
            else{
                this.buyerStatus.setText("Not yet");
                }}
        else{
            viewModel.popAlerterror("Please Sign in!");
        }
    }


    public void Aprrove(MouseEvent mouseEvent){
        if(!vacation.getVacation_status()){
            viewModel.popAlerterror("This vacation Sold Out!");
            return;
        }
        this.viewModel.setSellerStatus(order,true);

    }

    public void Decline(MouseEvent mouseEvent){
        if(!vacation.getVacation_status()){
            viewModel.popAlerterror("This vacation Sold Out!");
            return;
        }
        this.viewModel.setSellerStatus(order,false);

    }

    public void Back (MouseEvent mouseEvent){viewModel.goToSellerVacationsView();}

    public void goToPay (MouseEvent mouseEvent) {
        if (vacation.getVacation_status() && !order.getBuyer_status() && order.getSeller_status()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure this buyer pay in cash?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.viewModel.setVacationStatus(vacation.getVacation_id(),false);
                this.viewModel.setBuyerStatus(order,true);
            }        }
        else {
            if(!vacation.getVacation_status())
                viewModel.popAlerterror("This vacation Sold Out!");
            if(order.getBuyer_status())
                viewModel.popAlerterror("This buyer already pay!");
            if(!order.getSeller_status())
                viewModel.popAlerterror("Yoy declined this request!");


        }
    }




    public void setViewModel(ViewModel viewModel) { this.viewModel = viewModel; }
    public  void setVacation(Vacation vacation){this.vacation=vacation;}
    public  void setUser(User user){this.user=user;}
    public void setOrder(Order ord){this.order=ord;}


}
