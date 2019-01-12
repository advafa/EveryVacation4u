package Model;

import App.*;
import App.Vacation;
import DB.SqliteDB;
import java.util.List;

public class Model {

    private final SqliteDB db;

    public Model() {
        this.db = new SqliteDB();
        db.connectToDB("myDB.db",false);
    }

    //  ******************** Add functions ***********************************
    public void addUser(User user) {db.addUser(user); }
    public void addVacation(Vacation aVacation) {db.addVacation(aVacation); }
    public void addReq(Order order) { db.addReq(order); }
    public void addPayment(Payment payment) {db.addPayment(payment);}
    public void addReq (TradeRequest tradeRequest) { db.addReq(tradeRequest); }


    //  ******************** Check if Exists ***********************************
    public Boolean isUserExists(User user) { return db.isUserExists(user);}
    public Boolean  isSaleReqExists (String seller_email,String buyer_email,int VacationId) { return db. isSaleReqExists (seller_email, buyer_email,VacationId);}
    public Boolean  isSaleReqExists (int VacationId, int VacationtoTrade_id) { return db. isSaleReqExists (VacationId,VacationtoTrade_id);}

    //  ******************** Delete ***********************************
    public void deleteUser(User user){db.deleteUser(user);}
    public void deleteVacation(Vacation aVacation) {db.deleteVacation(aVacation);}
    public void deleteOrder(Order order){db.deleteOrder(order);}

    public void deleteVacationsBySeller(String userEmail){db.deleteVacationsBySeller(userEmail);}
    public void deleteRequestsBySeller(String selllerEmail){db.deleteRequestsBySeller(selllerEmail);}
    public void deleteRequestsByBuyer(String buyerEmail){db.deleteRequestsByBuyer(buyerEmail);}


    public void deleteOrderByVacationID (int vacation_id) {db.deleteOrderByVacationID (vacation_id);}
    public void deleteTradeRequestBySeller(String seller_email) { db.deleteTradeRequestBySeller(seller_email);}
    public void deleteTradeRequestsByTrader(String trader_email) { db.deleteTradeRequestsByTrader(trader_email);}
    public void deleteTradeRequestByVacationID (int vacation_id) { db.deleteTradeRequestByVacationID (vacation_id);}
    public void deleteTradeRequestByTradeVacationID (int vacationtoTrade_id) { db.deleteTradeRequestByTradeVacationID (vacationtoTrade_id);}

    //  ******************** Get ***********************************
    public User loadUser(String userEmail) {return db.getUserByEmail(userEmail);}
    public String getUserNameByEmail(String email){return db.getUserNameByEmail(email);}
    public Vacation getVacationByVacationId(int VacationId){return db.getVacationByVacationId(VacationId);}
    public List<Order> getRequestsByseller_email(String seller_email){return db.getRequestsByseller_email(seller_email);}
    public List<Order> getRequestsByBuyer_email(String buyer_email){return db.getRequestsByBuyer_email(buyer_email);}
    public List<Vacation> getAllAvailableVacations(){return db.getAllAvailableVacations();}
    public List<Vacation> getVacationBySimpleSearch(Vacation aVacation){return db.getVacationBySimpleSearch(aVacation);}
    public List<Vacation> getVacationsByseller_email(String seller_email){return db.getVacationsByseller_email(seller_email);}
    public List<Vacation> getAvailableVacationsByseller_email(String seller_email){return db.getAvailableVacationsByseller_email(seller_email);}

    public List<TradeRequest> getTradeRequestByseller_email(String seller_email){return db.getTradeRequestByseller_email(seller_email);}
    public List<TradeRequest> getTradeRequestByTrader_email(String trader_email){return db.getTradeRequestByTrader_email(trader_email);}

    //*****************Update************************
    public void UpdateUser(User user){db.UpdateUser(user);}
    public void UpdateOrdersSellerStatus(Order ord, boolean sellerStatus) {db.UpdateOrdersSellerStatus(ord,sellerStatus);};
    public void UpdateOrdersBuyerStatus(Order ord, boolean buyerStatus) {db.UpdateOrdersBuyerStatus(ord,buyerStatus);}
    public void UpdatVacationStatus(int vacation_id, boolean vac_status) {db.UpdatVacationStatus(vacation_id,vac_status);}
    public void UpdateVacation(Vacation vac){db.UpdateVacation(vac);}

    public void UpdateTradeRequestSellerStatus(TradeRequest req, boolean sellerStatus) {db.UpdateTradeRequestSellerStatus(req,sellerStatus);}
}
