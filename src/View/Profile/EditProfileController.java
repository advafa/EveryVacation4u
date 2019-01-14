package View.Profile;

import java.net.URL;
import java.util.ResourceBundle;

import App.User;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;


public class EditProfileController implements Initializable {

    @FXML
    public javafx.scene.control.TextField first_name;
    public javafx.scene.control.TextField last_name;
    public javafx.scene.control.TextField email;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField confirm_password;
    public javafx.scene.control.DatePicker birth_date;
    public javafx.scene.control.TextField city;
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
    public MenuItem buyer_req_menu;
    public MenuItem inbox_traderequests_menu;
    public MenuItem outbox_traderequests_menu;
    public MenuItem SignIn_menu;
    public MenuItem SignOut_menu;
    public MenuItem exit_menu;


    private ViewModel viewModel;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //*********  Menu Functions **************///
        SignUp_menu.setOnAction(e -> {viewModel.goToSignUp();});
        View_profile_menu.setOnAction(e -> {viewModel.goToProfileView();});
        EditProfile_menu.setOnAction(e -> {viewModel.goToEditProfile();});
        Delete_profile_menu.setOnAction(e -> {viewModel.goTODeleteProfile();});;
        addVac_menu.setOnAction(e -> {viewModel.goToAddVacation();});
        seller_vacations_menu.setOnAction(e -> {viewModel.goToSellerVacationsView("View");});
        seller_req_menu.setOnAction(e -> {viewModel.goToSellerRequest();});
        search_menu.setOnAction(e -> {viewModel.goToSearchView();});
        buyer_req_menu.setOnAction(e -> {viewModel.goToBuyerVacationsView();});
        inbox_traderequests_menu.setOnAction(e -> {viewModel.goToInbox_traderequests();});
        outbox_traderequests_menu.setOnAction(e -> {viewModel.goToOutbox_traderequests();});
        SignIn_menu.setOnAction(e -> {viewModel.goToSignIn();});
        SignOut_menu.setOnAction(e -> {viewModel.SignOut();});
        exit_menu.setOnAction(e -> {System.exit(0);});




    }

    public void setViewModel(ViewModel viewModel) {this.viewModel = viewModel; }

    public void loadUserView() {

        this.user = viewModel.getUser();
        this.first_name.setText(user.getFirst_name());
        this.last_name.setText(user.getLast_name());
        this.email.setText(user.getEmail());
        this.birth_date.setValue(user.getBirth_date());
        this.city.setText(user.getCity());

        this.email.setEditable(false);
    }


    public void EditProfile(MouseEvent mouseEvent) {
        this.user = new User(first_name.getText(), last_name.getText(), email.getText(), password.getText(), birth_date.getValue(), city.getText());

        if (validateform()) {
            viewModel.setUser(this.user);
            viewModel.popAlertinfo("You profile successfully update!");
            reset(mouseEvent);
            viewModel.goToSearchView();
        }

    }


    private boolean validateform() {
        if (!validateNotEmpty()) {
            popAlerterror("One or more fields is empty!");
            return false;
        }

        if (!confirm_password.getText().equals(password.getText())) {
            popAlerterror("The passwords don't match!");
            return false;
        }

        if (password.getText().length() != 8) {
            popAlerterror("The passwords have to contain 8 characters!");
            return false;
        }
        if ((LocalDate.now().getYear() - birth_date.getValue().getYear())  < 18) {
            popAlerterror("You are too young!");
            return false;
        }


        if (!first_name.getText().chars().allMatch(Character::isLetter)) {
            popAlerterror("First Name have contain only letters!");
            return false;
        }

        if (!last_name.getText().chars().allMatch(Character::isLetter)) {
            popAlerterror("Last Name have contain only letters!");
            return false;
        }
        if (!city.getText().chars().allMatch(Character::isLetter)) {
            popAlerterror("City have contain only letters!");
            return false;
        }
        return true;
    }


    private boolean validateNotEmpty() {
        if (first_name.getText() == null || first_name.getText().trim().isEmpty()) {
            return false;
        }

        if (last_name.getText() == null || last_name.getText().trim().isEmpty()) {
            return false;
        }

        if (email.getText() == null || email.getText().trim().isEmpty()) {
            return false;
        }


        if (password.getText() == null || password.getText().trim().isEmpty()) {
            return false;
        }

        if (confirm_password.getText() == null || confirm_password.getText().trim().isEmpty()) {
            return false;
        }

        if (city.getText() == null || city.getText().trim().isEmpty()) {
            return false;
        }

        if (birth_date.getValue() == null) {
            return false;
        }
        return true;
    }



    private void popAlertinfo(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    private void popAlerterror(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    public void reset(MouseEvent mouseEvent) {
        first_name.setText("");
        last_name.setText("");
        password.setText("");
        confirm_password.setText("");
        city.setText("");
        birth_date.setValue(null);
        email.setText("");
    }



}