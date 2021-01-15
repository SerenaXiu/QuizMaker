package at.ac.fhcampuswien;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerEnd {

    @FXML
    private Label moneyWon;

    public void setMoneyWon(String money) {
        moneyWon.setText(money);
    }
}
