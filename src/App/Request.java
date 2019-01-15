package App;

public class Request {

    private String seller_email;
    private String searcher_email;
    private int vacation_id;
    private Boolean seller_status;//true=approve, false=decline
    public Boolean searcher_status;//true=paid, false=submit



    public Request(String seller_email, String searcher_email, int vacation_id, Boolean searcher_status){
        this.seller_email=seller_email;
        this.searcher_email=searcher_email;
        this.vacation_id=vacation_id;
        this.searcher_status=searcher_status;
    }




    //Get Functions
    public String getSeller_email(){return this.seller_email;}
    public String getSearcher_email(){return this.searcher_email;}
    public int getVacation_id(){return this.vacation_id;}
    public Boolean getSeller_status(){return this.seller_status;}//true=approve, false=decline
    public Boolean getSearcher_status(){return this.searcher_status;}//true=paid, false=submit
    public String toStringSellerStatus(){
        if (this.seller_status == null)
        return  "Submit";
        return this.seller_status? "Approved" : "Declined";
    }

    public String toStringSearcher_status(){
        if (this.searcher_status == null)
            return  "";
        return this.searcher_status? "Paid" : "Submit";
    }

    //Set Functions
    public void setSeller_email(String seller_email) {this.seller_email = seller_email;}
    public void setSearcher_email(String searcher_email) {this.searcher_email = searcher_email;}
    public void setVacation_id(int vacation_id) {this.vacation_id = vacation_id;}
    public void setSearcher_status(Boolean searcher_status) {this.searcher_status = searcher_status; }
    public void setSeller_status(Boolean seller_status) {this.seller_status = seller_status;}


}
