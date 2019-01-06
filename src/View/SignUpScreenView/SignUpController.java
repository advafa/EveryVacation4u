package View.SignUpScreenView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import App.User;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;


public class SignUpController implements Initializable {

    @FXML
    public TextField first_name;
    public TextField last_name;
    public TextField email;
    public TextField password;
    public TextField confirm_password;
    public DatePicker birth_date;
    public TextField city;


    private ViewModel viewModel;
    private User newuser;


    //MenuItems
    public MenuItem exit_menu;
    public MenuItem SignIn_menu;
    public MenuItem SignOut_menu;
    public MenuItem addVac_menu;
    public MenuItem buyer_req_menu;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //*********  Menu Functions **************///
        exit_menu.setOnAction(e -> {System.exit(0);});
        SignIn_menu.setOnAction(e -> {viewModel.goToSignIn();});
        SignOut_menu.setOnAction(e -> {viewModel.loguotUser();});
        addVac_menu.setOnAction(e -> {viewModel.goToAddVacation();});
        buyer_req_menu.setOnAction(e -> {viewModel.goToBuyerVacationsView();});


    }

    public void AddUser (MouseEvent mouseEvent) {
        LocalDate birth_dateValue = birth_date.getValue();
        newuser = new User(first_name.getText(), last_name.getText(), email.getText(), password.getText(), birth_dateValue, city.getText());

        if (validateform()) {
            viewModel.AddUser(newuser);
            popAlertinfo("Registration successfully completed!");
            resetFields(mouseEvent);
            viewModel.goToSignIn();
        }

    }


    private boolean validateform() {
        if (!validateNotEmpty()) {
            popAlerterror("One or more fields is empty!");
            return false;
        }
        if (viewModel.isUserExists(newuser)) {
            popAlerterror("This email already exist!");
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


    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void resetFields(MouseEvent mouseEvent) {
        first_name.setText("");
        last_name.setText("");
        email.setText("");
        password.setText("");
        confirm_password.setText("");
        city.setText("");
        birth_date.setValue(null);
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

//*********  Menu Functions **************///

    public void goToSignUp(MouseEvent mouseEvent) {
        User currentUser = viewModel.getUser();
        if (this.viewModel.isUserExists(currentUser)) {
            this.viewModel.popAlertinfo("You already Sign in!"); }
        else {
            viewModel.goToSignUp();
        }
    }


    public void ProfileView(MouseEvent mouseEvent){
        User currentUser = viewModel.getUser();
        if (this.viewModel.isUserExists(currentUser)) {
            viewModel.goToProfileView();
        }
        else {
            this.viewModel.popAlertinfo("You are NOT Sign in!");
            viewModel.goToSignIn();
        }
    }

    public void EditProfile(MouseEvent mouseEvent){
        User currentUser = viewModel.getUser();
        if (this.viewModel.isUserExists(currentUser)) {
            viewModel.goToEditProfile();
        }
        else {
            this.viewModel.popAlertinfo("You are NOT Sign in!");
            viewModel.goToSignIn();
        }
    }

    public void DeleteProfile(){
        User currentUser= viewModel.getUser();
        if(this.viewModel.isUserExists(currentUser)){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete ypur proflie?");
            Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.viewModel.deleteUser(currentUser);
            this.viewModel.goToSearchView();
        }
        }
        else {
            this.viewModel.popAlertinfo("You are NOT Sign in!");
            viewModel.goToSignIn();
        }

    }




    public void goToSellerVacationsView(MouseEvent mouseEvent){
        User currentUser = viewModel.getUser();
        if (this.viewModel.isUserExists(currentUser)) {
            viewModel.goToSellerVacationsView();
        }
        else {
            this.viewModel.popAlertinfo("You are NOT Sign in!");
            viewModel.goToSignIn();
        }
    }


    public void goToBuyerVacationsView(MouseEvent mouseEvent){
        User currentUser = viewModel.getUser();
        if (this.viewModel.isUserExists(currentUser)) {
            viewModel.goToBuyerVacationsView();
        }
        else {
            this.viewModel.popAlertinfo("You are NOT Sign in!");
            viewModel.goToSignIn();
        }

         }

    public void goToSearch(MouseEvent mouseEvent) {
        viewModel.goToSearchView();
    }
//    public void exitApp(MouseEvent event) {
//        System.exit(0);
//    }

}