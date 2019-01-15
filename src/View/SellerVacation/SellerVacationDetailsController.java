package View.SellerVacation;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import App.Vacation;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;





public class SellerVacationDetailsController implements Initializable {

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
    public Label vac_status;
    public Label seller_email;
    public Label name;

    public Button done_btn;
    public Button del_btn;
    public Button edit_btn;
    public Button back1_btn;
    public Button back_btn;

    private ViewModel viewModel;
    private Vacation vacation;


    //    buyertitle
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }




    public void Back(MouseEvent mouseEvent) {
        viewModel.goToSellerVacationsView();
    }


    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void loadVacationView(Vacation vacation) {
        this.vacation = vacation;


        this.check_in.setText(vacation.toStringCheckin());
        this.check_out.setText(vacation.toStringCheckout());
        this.from.setText(vacation.getFrom());
        this.to.setText(vacation.getto());
        this.airline.setText(vacation.getAirline());

        if (vacation.getBackFlight()) {
            this.backf.setText("Back flight Included");
        } else {
            this.backf.setText("Back flight *NOT* Included");
        }

        this.handbag.setText(vacation.getHand_bag());
        this.checkbag.setText(vacation.getChecked_bag());
        this.conectin.setText(vacation.getConnec_flight());
        this.vac_type.setText(vacation.getVacation_type());
        this.tic_type.setText(vacation.getTicket_type());


        if (vacation.getHotel()) {
            this.hotel_in.setText("Hotel Included:");
            this.hotel_type.setText(vacation.getHotel_type());
            this.hotel_raiting.setText("Hotel Rating: " + vacation.getHotel_raiting() + " Stars");
        } else {
            this.hotel_in.setText("Hotel *NOT* Included");
            this.hotel_type.setText("");
            this.hotel_raiting.setText("");
        }


        this.num.setText("" + vacation.getNum_of_tickets());
        this.price.setText(vacation.getSale_price() + "$");
        this.original_price.setText(vacation.getOriginal_price() + "$");


        if (vacation.getVacation_status())
                this.vac_status.setText("Vacation Available!!");
        else
                this.vac_status.setText("Vacation is NOT Available!!");


    }
    public void setButtonsON() {
        del_btn.setVisible(false);
        edit_btn.setVisible(false);
        back1_btn.setVisible(false);

        back_btn.setVisible(true);
        done_btn.setVisible(true);

    }


    public void setButtonsOff() {
        del_btn.setVisible(true);
        edit_btn.setVisible(true);
        back1_btn.setVisible(true);

        back_btn.setVisible(false);
        done_btn.setVisible(false);
    }


    public void goToDone (MouseEvent mouseEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("In this send Requst you agree to trade in");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.viewModel.addTradeReq(this.vacation.getVacation_id(),true);


            }}


    public void goToDeleteVacation (MouseEvent mouseEvent) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete this Vacation?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.viewModel.DeleteVacation(this.vacation.getVacation_id());
                this.viewModel.popAlertinfo("Your Vacation successfully deleted !");
                viewModel.goToSellerVacationsView();
            }}

    public void goToEditVacation (MouseEvent mouseEvent) {
        if(!this.vacation.getVacation_status())
            this.viewModel.popAlertinfo("This Vacation is NOT Avalible !!!");
        else
        this.viewModel.goToEditVacation(this.vacation.getVacation_id()); }
    }


