package at.ac.fhcampuswien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ControllerMgmt {

    ObservableList<String> cbList = FXCollections.observableArrayList("Leicht", "Mittel", "Schwer");



    @FXML
    private ChoiceBox cb;

    @FXML
    private TextField tfFrage, neueAntwortA, neueAntwortB, neueAntwortC, neueAntwortD;

    @FXML
    private Button neueFrageSend;

    private static int schwierigkeit;
    private static boolean isSendPressed = false;
    private static String Frage;
    private static String richtigeAntwort;
    private static String falscheAntwort1;
    private static String falscheAntwort2;
    private static String falscheAntwort3;




    @FXML
    private void initialize(){
        cb.setValue("Leicht"); // damit man gleich eine Möglichkeit angezeigt bekommt.
        cb.setItems(cbList);
    }

    @FXML
    public void einlesen(ActionEvent actionEvent){
        setIsSendPressed(true);

        if(cb.getValue().equals("Leicht")){
            schwierigkeit = 0;
        }else if(cb.getValue().equals("Mittel")){
            schwierigkeit = 1;
        }else if(cb.getValue().equals("Schwer")){
            schwierigkeit = 2;
        }



        //System.out.println(schwierigkeit);
        setFrage(tfFrage.getText());
        setRichtigeAntwort(neueAntwortA.getText());
        setFalscheAntwort1(neueAntwortB.getText());
        setFalscheAntwort2(neueAntwortC.getText());
        setFalscheAntwort3(neueAntwortD.getText());

        JsonWriter.JsonWriter();
    }

    @FXML
    private void verwerfen(ActionEvent actionEvent){ // um Text aus TextFields zu löschen.
        tfFrage.setText("");
        neueAntwortA.setText("");
        neueAntwortB.setText("");
        neueAntwortC.setText("");
        neueAntwortD.setText("");
        cb.setValue("Leicht");
    }

    @FXML
    private void goBack(ActionEvent actionEvent) throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("UserInt.fxml"));
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(start));
    }



    public static int getSchwierigkeit(){
        return schwierigkeit;
    }

    public static boolean getIsSendPressed() {
        return isSendPressed;
    }

    public static void setIsSendPressed(boolean isSendPressed) {
        ControllerMgmt.isSendPressed = isSendPressed;
    }

    public static String getFrage() {
        return Frage;
    }

    public static void setFrage(String frage) {
        Frage = frage;
    }

    public static String getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public static void setRichtigeAntwort(String richtigeAntwort) {
        ControllerMgmt.richtigeAntwort = richtigeAntwort;
    }

    public static String getFalscheAntwort1() {
        return falscheAntwort1;
    }

    public static void setFalscheAntwort1(String falscheAntwort1) {
        ControllerMgmt.falscheAntwort1 = falscheAntwort1;
    }

    public static String getFalscheAntwort2() {
        return falscheAntwort2;
    }

    public static void setFalscheAntwort2(String falscheAntwort2) {
        ControllerMgmt.falscheAntwort2 = falscheAntwort2;
    }

    public static String getFalscheAntwort3() {
        return falscheAntwort3;
    }

    public static void setFalscheAntwort3(String falscheAntwort3) {
        ControllerMgmt.falscheAntwort3 = falscheAntwort3;
    }
}
