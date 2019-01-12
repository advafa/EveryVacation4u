package View.BuyerRequests;





import java.net.URL;
import java.util.ResourceBundle;

import App.Vacation;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class BuyerVacationDetailsController implements Initializable {

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
    public Label off;
    public Label sellerStatus;
    public Label vac_status;
    public Label seller_email;
    public Label name;
    private ViewModel viewModel;
    private Vacation vacation;
    private Boolean status;
    private String statusString;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    public void Back (MouseEvent mouseEvent){viewModel.goToBuyerVacationsView();}



    public void setViewModel(ViewModel viewModel) { this.viewModel = viewModel; }



    public void loadVacation(Vacation vacation, String status){

        this.vacation=vacation;
        this.statusString=status;

        if(status== "Approved") this.status=true;
        if(status== "Declined") this.status=false;
        if(status== "") {
            this.status=null;
            this.statusString="submitted";
        }



        //Set Labels

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
        this.sellerStatus.setText(this.statusString);

        if(vacation.getVacation_status()){
            if(this.status){
                this.vac_status.setText("Vacation Available for sale !!");
                seller_email.setVisible(true);
                seller_email.setText("Contact Seller: "+ vacation.getSeller());}
            else{
                this.vac_status.setText("Your Request "+this.statusString+" for this Vacation");
                seller_email.setVisible(false);

            }}
        else {this.vac_status.setText("Sold out");
            seller_email.setVisible(false);
        }
        this.name.setText(viewModel.getUserNameByEmail(this.vacation.getSeller()));

    }




}
