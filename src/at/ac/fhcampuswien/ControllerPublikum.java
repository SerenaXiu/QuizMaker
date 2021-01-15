package at.ac.fhcampuswien;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerPublikum {

    @FXML
    private Label answerA, answerB, answerC, answerD, percentA, percentB, percentC, percentD;

    public void setText(String a, String b, String c, String d, int aper, int bper, int cper, int dper){
        answerA.setText(a);
        answerB.setText(b);
        answerC.setText(c);
        answerD.setText(d);
        percentA.setText(aper + "%");
        percentB.setText(bper + "%");
        percentC.setText(cper + "%");
        percentD.setText(dper + "%");
    }
}
