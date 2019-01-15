package App;

public class TradeRequest extends Request {

    private int vacationtoTrade_id;



    public TradeRequest(String seller_email, String trader_email, int vacation_id, int vacationtoTrade_id, Boolean trader_status){
        super(seller_email, trader_email, vacation_id, trader_status);
        this.vacationtoTrade_id=vacationtoTrade_id;
    }

    public int getVacationtoTrade_id(){return this.vacationtoTrade_id;}
    public void setVacationtoTrade_id(int vacation_id) {this.vacationtoTrade_id = vacation_id;}


public String getTrader_email(){
        return this.getSearcher_email();
}


}
