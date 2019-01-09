package View.Search;

import Main.ViewModel;
import View.AbstractController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class SearchViewController extends AbstractController {

    public DatePicker start_date;
    public DatePicker end_date;
    public TextField City;
    public ComboBox Categories;
    public TextField Neighborhood;
    public TextField Street;


    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setButtonsON() {
    }


    public void setButtonsOff() {
    }

}