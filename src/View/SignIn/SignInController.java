package View.SignIn;

import java.net.URL;
import java.util.ResourceBundle;

import Main.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class SignInController implements Initializable {

    public TextField email;
    public PasswordField password;
    private ViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            viewModel.goToPage();
        }
            else
                viewModel.popAlerterror("Email or password are incorrect");

        }
    }
}
