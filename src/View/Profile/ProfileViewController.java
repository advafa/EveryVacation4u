package View.Profile;



import App.User;
import Main.ViewModel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class ProfileViewController implements Initializable{

    private ViewModel viewModel;
    private User user;

    @FXML
    public Label first_name;
    public Label last_name;
    public Label email;
    public Label birth_date;
    public Label city;



    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    public void setViewModel(ViewModel viewModel) { this.viewModel = viewModel; }

    public void loadUserView(){

        this.user=viewModel.getUser();
        this.first_name.setText(this.user.getFirst_name());
        this.last_name.setText(this.user.getLast_name());
        this.email.setText(this.user.getEmail());
        this.birth_date.setText(this.user.toStringBirth_date());
        this.city.setText(this.user.getCity());
    }

}


