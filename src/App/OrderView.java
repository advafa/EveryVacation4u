package App;

public class OrderView {

//    private String seller_email;
//    private String buyer_email;
    private int vacation_id;
    private String Name;
    private String status;//seller: true=approve, false=decline// buyer: true=paid, false=submit
    private String ReqStatus;
    private String buyer_email;

        public OrderView(int vacation_id, String name, String status){
            this.Name=name+"";
            this.vacation_id=vacation_id;
            this.status=status+"";
            this.ReqStatus="";
            this.buyer_email="";
        }


    public OrderView(int vacation_id, String name, String status, String ReqStatus,String buyer_email){
        this.Name=name+"";
        this.vacation_id=vacation_id;
        this.status=status+"";
        this.ReqStatus=ReqStatus+"";
        this.buyer_email=buyer_email;

    }
        //Get Functions

        public String getName(){return this.Name;}
        public int getVacation_id(){return this.vacation_id;}
        public String getStatus(){return this.status;}
        public String getReqStatus(){return this.ReqStatus;}
        public String getBuyer_email(){return this.buyer_email;}


    //Set Functions
        public void setName(String name) {this.Name = name;}
        public void setVacation_id(int vacation_id) {this.vacation_id = vacation_id;}
        public void setStatus(String status) {this.status = status; }
        public void setReqStatus(String status){this.ReqStatus=status;}
        public void setBuyer_email(String buyer_email) {this.buyer_email = buyer_email;}

    }
