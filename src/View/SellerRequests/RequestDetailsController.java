package View.SellerRequests;

import App.Vacation;
import Main.ViewModel;

public class RequestDetailsController {


    private ViewModel viewModel;
    private Vacation vacation;

public void loadVacationRequestView(Vacation vacation) {
    this.vacation = vacation;}

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
