package View.AddVacation;

import App.User;
import App.Vacation;
import Main.ViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.*;



public class AddVacationController implements Initializable {


        private String seller_email;

        public DatePicker checkin;
        public DatePicker checkout;

        public ComboBox<String> from;
        public ComboBox<String> to;

        public TextField airline;
        public javafx.scene.control.CheckBox back_flight;
        public ComboBox<String> hand_bag;
        public ComboBox<String> checked_bag;
        public TextField num_of_tickets;
        public TextField original_price;
        public TextField sale_price;


        public ComboBox<String> connec_flight;
        public ComboBox<String> vacation_type;
        public ComboBox<String> ticket_type;
        public ComboBox<String> hotel_type;
        public ComboBox<String> hotel_raiting;

        public javafx.scene.control.CheckBox hotel_in;
        public javafx.scene.control.CheckBox separately;

        private ViewModel viewModel;
        private Vacation vacation;


        private int num;
        private int org_p;
        private int sal_p;
        private int off;
        int hotelR;


        public void initialize(URL url, ResourceBundle rb) {
            hotel_type.setEditable(true);
            hotel_raiting.setEditable(true);
        }

        public void AddVacation(MouseEvent mouseEvent){

            if(hotel_in.isSelected()){
                try{
                    hotelR=Integer.parseInt(hotel_raiting.getValue());}
                catch (Exception e) {
                    hotelR=-1;}}

            seller_email=viewModel.getUser().getEmail();
            if(validateform()){
                vacation=new Vacation (seller_email, from.getValue(),to.getValue() ,checkin.getValue(), checkout.getValue(),
                        airline.getText(),back_flight.isSelected(),hand_bag.getValue(),checked_bag.getValue(),
                        connec_flight.getValue(), vacation_type.getValue(), ticket_type.getValue(), hotel_in.isSelected(),
                        hotel_type.getValue(),hotelR, num,
                        separately.isSelected(), org_p,sal_p,off);

                if((num>1 && !separately.isSelected()) || num==1)
                this.viewModel.AddVacation(vacation);
                else {
                    if(num>1 && separately.isSelected()) {
                        vacation.setNum_of_tickets(1);
                    for(int i=0;i<num;i++)
                        this.viewModel.AddVacation(vacation);
                    } }
                this.viewModel.popAlertinfo("Your Vacation successfully saved!");
                this.reset();
            }
        }

        private boolean validateform() {
            if (!validateNotEmpty()) {
                this.viewModel.popAlertinfo("One or more fields is empty!");
                return false;
            }

            try {
                org_p=Integer.parseInt(original_price.getText());
                sal_p=Integer.parseInt(sale_price.getText());
                off=100*(org_p-sal_p)/org_p;
            }
            catch (Exception e) {
                viewModel.popAlertinfo("Prices must be integer");
                return false;
            }
            try{
                num=Integer.parseInt(num_of_tickets.getText());}
            catch (Exception e) {
                viewModel.popAlertinfo("Number of tickets must be integer");
                return false;
            }

            if(num<=0) {
                viewModel.popAlertinfo("Number of tickets must be greater than 0 !");
                return false;
            }



            if(org_p<sal_p){
                this.viewModel.popAlertinfo("Sale price must by smallest than Original price!");
                return false;
            }
            if(!this.checkin.getValue().isAfter(LocalDate.now().plusDays(1))){
                this.viewModel.popAlertinfo("Invalidate Checkin date. It must be at least one day after today");
                return false;}
            if(!this.checkout.getValue().isAfter(this.checkin.getValue())){
                this.viewModel.popAlertinfo("Invalidate Checkout date. It must be after checkin date");
                return false;
            }
            if(!this.checkout.getValue().isAfter(LocalDate.now())) {
                this.viewModel.popAlertinfo("Invalidate Checkout date. Checkout date already past!");
                return false;
            }
            return true;
        }





        private boolean validateNotEmpty() {
            if (from.getValue() == null || to.getValue()==null) {
                return false;
            }

            if (airline.getText() == null || airline.getText().trim().isEmpty()) {
                return false;
            }


            if (hand_bag.getValue() == null || checked_bag.getValue()==null) {
                return false;
            }

            if (num_of_tickets.getText() == null || num_of_tickets.getText().trim().isEmpty()) {
                return false;
            }

            if (original_price.getText() == null || original_price.getText().trim().isEmpty()) {
                return false;
            }
            if (sale_price.getText() == null || sale_price.getText().trim().isEmpty()) {
                return false;
            }

            if (checkin.getValue() == null || checkout.getValue()==null) {
                return false;
            }
            if (connec_flight.getValue() == null || vacation_type.getValue()==null || ticket_type.getValue() == null) {
                return false;
            }

            if(hotel_in.isSelected()){
                if (hotel_type.getValue() == null || hotel_raiting.getValue()==null) {
                    return false;
                }
            }
            return true;
        }

        public void setViewModel(ViewModel viewModel) {this.viewModel = viewModel; }



    public void isHotel (ActionEvent event) {
        hotel_type.setValue(null);
        hotel_raiting.setValue(null);
        hotel_type.setEditable(!hotel_in.isSelected());
        hotel_raiting.setEditable(!hotel_in.isSelected());
    }


    private void reset(){

        checkin.setValue(null);
        checkout.setValue(null);
        from.setValue(null);
        to.setValue(null);
        airline.setText("");
        hand_bag.setValue(null);
        checked_bag.setValue(null);
        num_of_tickets.setText("");
        original_price.setText("");
        sale_price.setText("");
        connec_flight.setValue(null);
        vacation_type.setValue(null);
        ticket_type.setValue(null);
        hotel_type.setValue(null);
        hotel_raiting.setValue(null);

        hotel_in.setSelected(false);
        separately.setSelected(false);
        back_flight.setSelected(false);



    }
    }
