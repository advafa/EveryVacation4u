package View.Search;

import App.Vacation;
import Main.ViewModel;

public class SearchVacationDetailsController {
    private ViewModel viewModel;
private Vacation vacation;
    public void setViewModel(ViewModel viewModel) {this.viewModel = viewModel; }

    public void setButtonsON() {
    }


    public void setButtonsOff() {
    }

    public void loadVacationView(Vacation vacation) {
        this.vacation = vacation;}
}
