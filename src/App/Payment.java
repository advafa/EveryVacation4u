package App;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payment {

    private String seller_email;
    private String buyer_email;
    private int vacation_id;
    private LocalDate payment_date;

    public Payment(String seller_email, String buyer_email, int vacation_id){
        this.seller_email=seller_email;
        this.buyer_email=buyer_email;
        this.vacation_id=vacation_id;
        this.payment_date=LocalDate.now();
    }


    //Get Functions
    public String getSeller_email() {return this.seller_email;}
    public String getBuyer_email() {return this.buyer_email; }
    public int getVacation_id() {return this.vacation_id;}
    public LocalDate getPayment_date() {return this.payment_date;}

    public String toStringPayment_date () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return this.payment_date.format(formatter);
    }

    //Set Functions


    public void setSeller_email(String seller_email) {this.seller_email = seller_email; }
    public void setBuyer_email(String buyer_email) {this.buyer_email = buyer_email; }
    public void setVacation_id(int vacation_id) {this.vacation_id = vacation_id;}
    public void setPayment_date(LocalDate payment_date) {this.payment_date = payment_date; }

}




