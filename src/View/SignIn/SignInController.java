package View.SignIn;

import java.net.URL;
import java.util.ResourceBundle;

import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class SignInController implements Initializable {
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

    public TextField email;
    public PasswordField password;
    private ViewModel viewModel;

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

    public void setViewModel (ViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void goToSignUp (MouseEvent mouseEvent) {
        viewModel.goToSignUp();
    }


    public void login (MouseEvent mouseEvent) {

        if (email.getText() == null || email.getText().trim().isEmpty() || password.getText()==null ||password.getText().trim().isEmpty()) {
            viewModel.popAlerterror("Email or password are empty");
            return;
        }
        else {
        if(viewModel.loadUser(email.getText(), password.getText())) {
            System.out.println("loadUser");
            email.setText("");
            password.setText("");
            viewModel.goToPage();
        }
            else
                viewModel.popAlerterror("Email or password are incorrect");


        }
    }
}
