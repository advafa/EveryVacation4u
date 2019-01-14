package App;

import java.time.LocalDate;

public class Buyer extends User{

    public Buyer(String first_name, String last_name, String email, String password, LocalDate birth_date, String city, LocalDate sign_up_date) {
        super(first_name, last_name, email, password, birth_date, city, sign_up_date);
    }
}
