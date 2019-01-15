package View;

public class TableViewClass {

    private String seller_email;
    private String searcher_email;
    private int vacation_id;
    private String seller_status;//true=approve, false=decline
    private String searcher_status;//true=paid, false=submit

    private int vacation_idT;
    public String name;

    private String from;
    private String to;
    private String checkin;
    private String checkout;
    private String vac_status;


    private int price;
    private int off;

    private String checkinT;
    private String checkoutT;
    private String fromT;
    private String toT;
    private int priceT;


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

    public TableViewClass(int vacation_id, String checkin, String checkout,String from,String to){
        this.vacation_id=vacation_id;
        this.checkin= checkin;
        this.checkout=checkout;
        this.from= from;
        this.to= to;

    }

    //Get Functions
    public int getVacation_id(){return this.vacation_id;}
    public int getVacation_idT(){return this.vacation_idT;}

    public String getCheckin(){return this.checkin;}
    public String getCheckout(){return this.checkout;}
    public String getFrom(){return this.from;}
    public String getTo(){return this.to;}
    public String getName(){return this.name;}
    public String getSeller_status(){return this.seller_status;}
    public String getVac_status(){return this.vac_status;}
    public String getSearcher_email(){return this.searcher_email;}
    public String getSeller_email(){return this.seller_email;}
    public String getSearcher_status(){return this.searcher_status;}//true=paid, false=submit
    public  int getOff(){return this.off;}
    public  int getPrice(){return this.price;}
    public Boolean getSellerStatus(){
        if (this.seller_status.equals("Approved")) return true;
        else return false;
    }
    public String getTrader_email(){return this.searcher_email;}

    public String getCheckinT(){return this.checkinT;}
    public String getCheckoutT(){return this.checkoutT;}
    public String getFromT(){return this.fromT;}
    public String getToT(){return this.toT;}
    public  int getPriceT(){return this.priceT;}

    //Set Functions
    public void setVacation_id(int vacation_id){this.vacation_id=vacation_id;}
    public void setVacation_idT(int vacation_idT){this.vacation_idT=vacation_idT;}

    public void setCheckin(String checkin){this.checkin= checkin;}
    public void setCheckout(String checkout){this.checkout=checkout;}
    public void setFrom(String from){this.from= from;}
    public void setToo(String to){this.to= to;}
    public void setName(String name){this.name= name;}
    public void setSeller_status(String seller_status){this.seller_status= seller_status;}
    public void setVac_status(String vac_status){this.vac_status=vac_status;}
    public void setSearcher_email(String searcher_email) {this.searcher_email = searcher_email;}
    public void setSeller_email(String seller_email) {this.seller_email = seller_email;}
    public void setSearcher_status(String searcher_status) {this.searcher_status = searcher_status; }
    public  void setPrice(int price){this.price= price;}
    public  void setOff(int off){this.off= off;}

    public void setTrader_email(String trader_email) {this.searcher_email = trader_email;}


    public void setCheckinT(String checkin){this.checkinT= checkin;}
    public void setCheckoutT(String checkout){this.checkoutT=checkout;}
    public void setFromT(String from){this.fromT= from;}
    public void setTooT(String to){this.toT= to;}
    public  void setPriceT(int price){this.priceT= price;}





}
