package App;

public class TableViewClass {

    private String seller_email;
    private String buyer_email;
    private int vacation_id;
    private String seller_status;//true=approve, false=decline
    private String buyer_status;//true=paid, false=submit

    public String name;

    private String from;
    private String to;
    private String checkin;
    private String checkout;
    private String vac_status;

    public TableViewClass(int vacation_id, String checkin, String checkout,String from,String to, String name, String seller_status){
        this.vacation_id=vacation_id;
        this.checkin= checkin;
        this.checkout=checkout;
        this.from= from;
        this.to= to;
        this.name=name;
        this.seller_status = seller_status;

    }

    public TableViewClass(int vacation_id, String checkin, String checkout,String from,String to, String vac_status){
        this.vacation_id=vacation_id;
        this.checkin= checkin;
        this.checkout=checkout;
        this.from= from;
        this.to= to;
        this.vac_status=vac_status;

    }

    //Get Functions
    public int getVacation_id(){return this.vacation_id;}
    public String getCheckin(){return this.checkin;}
    public String getCheckout(){return this.checkout;}
    public String getFrom(){return this.from;}
    public String getTo(){return this.to;}
    public String getName(){return this.name;}
    public String getSeller_status(){return this.seller_status;}
    public String getVac_status(){return this.vac_status;}
    public String getBuyer_email(){return this.buyer_email;}
    public String getSeller_email(){return this.seller_email;}
    public String getBuyer_status(){return this.buyer_status;}//true=paid, false=submit

    //Set Functions
    public void setVacation_id(int vacation_id){this.vacation_id=vacation_id;}
    public void setCheckin(String checkin){this.checkin= checkin;}
    public void setCheckout(String checkout){this.checkout=checkout;}
    public void setFrom(String from){this.from= from;}
    public void setToo(String to){this.to= to;}
    public void setName(String name){this.name= name;}
    public void setSeller_status(String seller_status){this.seller_status= seller_status;}
    public void setVac_status(String vac_status){this.vac_status=vac_status;}
    public void setBuyer_email(String buyer_email) {this.buyer_email = buyer_email;}
    public void setSeller_email(String seller_email) {this.seller_email = seller_email;}
    public void setBuyer_status(String buyer_status) {this.buyer_status = buyer_status; }

}
