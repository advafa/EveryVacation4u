package View.Search;


import App.Vacation;
import Main.ViewModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;


public class SearchVacationDetailsController implements Initializable {



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

    public Label seller_email;
    public Label name;

    public Button buyReq_btn;
    public Button trade_btn;

    private ViewModel viewModel;
    private Vacation vacation;


    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setViewModel(ViewModel viewModel) {this.viewModel = viewModel; }

    public void setButtonsON() {
        buyReq_btn.setVisible(true);
        trade_btn.setVisible(true);
    }


    public void setButtonsOff() {
        buyReq_btn.setVisible(false);
        trade_btn.setVisible(false);
    }
    public void addReq (MouseEvent mouseEvent) {

        this.viewModel.addReq(this.vacation);
    }

    public void loadVacationView(Vacation vacation) {
        this.vacation = vacation;
        //Set Labels

        this.check_in.setText(vacation.toStringCheckin());
        this.check_out.setText(vacation.toStringCheckout());
        this.from.setText(vacation.getFrom());
        this.to.setText(vacation.getto());
        this.airline.setText(vacation.getAirline());

        if(vacation.getBackFlight()){
            this.backf.setText("Back flight Included");}
        else{
            this.backf.setText("Back flight *NOT* Included");}

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
        this.name.setText(viewModel.getUserNameByEmail(this.vacation.getSeller()));

        }


    public void goToback (MouseEvent mouseEvent) {

        this.viewModel.goToSearchView();
    }


    }

