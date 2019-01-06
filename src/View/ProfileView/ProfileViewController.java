package View.ProfileView;



import java.net.URL;
import java.util.ResourceBundle;

import App.User;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
//javafx.scene.control.Label
public class ProfileViewController implements Initializable{

        @FXML
        public Label first_name;
        public Label last_name;
        public Label email;
        public Label password;
        public Label birth_date;
        public Label city;

    private ViewModel viewModel;
    private User user;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            this.first_name.setText(user.getFirst_name());
            this.last_name.setText(user.getLast_name());
            this.email.setText(user.getEmail());
            this.password.setText(user.getPassword());
            this.birth_date.setText((user.getBirth_date().toString()));
            this.city.setText(user.getCity());
        }
//        public void setUserView(){
//        this.first_name.setText(user.getFirst_name());
//        this.last_name.setText(user.getLast_name());
//        this.email.setText(user.getEmail());
//        this.password.setText(user.getPassword());
//        this.birth_date.setText((user.getBirth_date().toString()));
//        this.city.setText(user.getCity());}

        public void setUser(User currentUser){this.user=currentUser;}
        public void setViewModel(ViewModel viewModel) { this.viewModel = viewModel; }


}
