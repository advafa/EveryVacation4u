package View.Vacation;

import App.Vacation;
import Main.ViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.*;



public class VacationController implements Initializable {


    //MenuItems
    @FXML
    public MenuItem SignUp_menu;
    public MenuItem View_profile_menu;
    public MenuItem EditProfile_menu;
    public MenuItem Delete_profile_menu;
    public MenuItem addVac_menu;
    public MenuItem seller_vacations_menu;
    public MenuItem seller_req_menu;
    public MenuItem search_menu;
    public MenuItem searcher_req_menu;
    public MenuItem inbox_traderequests_menu;
    public MenuItem outbox_traderequests_menu;
    public MenuItem SignIn_menu;
    public MenuItem SignOut_menu;
    public MenuItem exit_menu;


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

        public Button edit_btn;
        public Button res_btn;
        public Button can_btn;
        public Button add_bt;
        public Label title;
        private ViewModel viewModel;
        private Vacation vacation;


        private int num;
        private int org_p;
        private int sal_p;
        private int off;
        int hotelR;
        private String seller_email;
        private int vacation_id;


        public void initialize(URL url, ResourceBundle rb) {
            hotel_type.setEditable(true);
            hotel_raiting.setEditable(true);

            //*********  Menu Functions **************///
            SignUp_menu.setOnAction(e -> {viewModel.goToSignUp();});
            View_profile_menu.setOnAction(e -> {viewModel.goToProfileView();});
            EditProfile_menu.setOnAction(e -> {viewModel.goToEditProfile();});
            Delete_profile_menu.setOnAction(e -> {viewModel.goTODeleteProfile();});;
            addVac_menu.setOnAction(e -> {viewModel.goToAddVacation();});
            seller_vacations_menu.setOnAction(e -> {viewModel.goToSellerVacationsView("View");});
            seller_req_menu.setOnAction(e -> {viewModel.goToSellerRequest();});
            search_menu.setOnAction(e -> {viewModel.goToSearchView();});
            searcher_req_menu.setOnAction(e -> {viewModel.goToSearcherVacationsView();});
            inbox_traderequests_menu.setOnAction(e -> {viewModel.goToInbox_traderequests();});
            outbox_traderequests_menu.setOnAction(e -> {viewModel.goToOutbox_traderequests();});
            SignIn_menu.setOnAction(e -> {viewModel.goToSignIn();});
            SignOut_menu.setOnAction(e -> {viewModel.SignOut();});
            exit_menu.setOnAction(e -> {System.exit(0);});

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



    public void EditVacation(MouseEvent mouseEvent){
        if(hotel_in.isSelected()){
            try{
                hotelR=Integer.parseInt(hotel_raiting.getValue());}
            catch (Exception e) {
                hotelR=-1;}}


        if(validateform()){
            Vacation evacation=new Vacation (this.seller_email,this.vacation_id, from.getValue(),to.getValue() ,checkin.getValue(), checkout.getValue(),
                    airline.getText(),back_flight.isSelected(),hand_bag.getValue(),checked_bag.getValue(),
                    connec_flight.getValue(), vacation_type.getValue(), ticket_type.getValue(), hotel_in.isSelected(),
                    hotel_type.getValue(),hotelR, num,
                    separately.isSelected(), org_p,sal_p,off);

            if((num>1 && !separately.isSelected()) || num==1)
                this.viewModel.EditVacation(evacation);
            else {
                if(num>1 && separately.isSelected()) {
                    evacation.setNum_of_tickets(1);
                    this.viewModel.EditVacation(evacation);
                    for(int i=0;i<num-1;i++)
                        this.viewModel.AddVacation(evacation);

                } }
            this.viewModel.popAlertinfo("Your Vacation successfully saved!");
            this.reset();
            viewModel.goToSellerVacationsView("View");
        }



    }

    public void loadEditVacation(Vacation vac){

        edit_btn.setVisible(true);
        res_btn.setVisible(true);
        can_btn.setVisible(true);
        add_bt.setVisible(false);
        title.setText("Edit a Your Vacation4Sale");


            this.vacation=vac;
        this.seller_email=this.vacation.getSeller();
        this.vacation_id=this.vacation.getVacation_id();

        ObservableList<String> AirportsList = FXCollections.observableArrayList();
        List<String> Airports = this.viewModel.getAirports();
        AirportsList.addAll(Airports);
        from.setItems(AirportsList);
        to.setItems(AirportsList);

        checkin.setValue(this.vacation.getCheckin());
        checkout.setValue(this.vacation.getCheckout());
        from.setValue(this.vacation.getFrom());
        to.setValue(this.vacation.getto());
        airline.setText(this.vacation.getAirline());
        hand_bag.setValue(this.vacation.getHand_bag());
        checked_bag.setValue(this.vacation.getChecked_bag());
        num_of_tickets.setText(String.valueOf(this.vacation.getNum_of_tickets()));
        original_price.setText(String.valueOf(this.vacation.getOriginal_price()));
        sale_price.setText(String.valueOf(this.vacation.getSale_price()));
        connec_flight.setValue(this.vacation.getConnec_flight());
        vacation_type.setValue(this.vacation.getVacation_type());
        ticket_type.setValue(this.vacation.getTicket_type());

        if(this.vacation.getHotel()){
        hotel_in.setSelected(true);
        hotel_type.setValue(this.vacation.getHotel_type());
        hotel_raiting.setValue(String.valueOf(this.vacation.getHotel_raiting()));
        }else
            hotel_in.setSelected(false);

        separately.setSelected(this.vacation.getSeparately());
        back_flight.setSelected(this.vacation.getBackFlight());
    }


    public void Cancel(MouseEvent mouseEvent){
        this.reset();
        viewModel.goToSellerVacationsView("View");
    }

    public void reset(MouseEvent mouseEvent){
        this.reset();
    }

    public void loadAddVacation(){

        edit_btn.setVisible(false);
        res_btn.setVisible(false);
        can_btn.setVisible(false);
        add_bt.setVisible(true);

        title.setText("Add a New Vacation4Sale");
        ObservableList<String> AirportsList = FXCollections.observableArrayList();
        List<String> Airports = this.viewModel.getAirports();
        AirportsList.addAll(Airports);
        from.setItems(AirportsList);
        to.setItems(AirportsList);


    }


    }
