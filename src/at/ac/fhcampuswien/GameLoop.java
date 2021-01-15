package at.ac.fhcampuswien;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Random;

public class GameLoop {

    private int question = 1;


    public void nextQuestion(Label lab, Button b1, Button b2, Button b3, Button b4) throws IOException {

        int[] array = new int[4];
        verriegelungAntworten(array, 0, new Random(System.currentTimeMillis()));

            String Frage = "Frage " + question + ": " + JsonReader.frageAuslesen(question);
            lab.setText(Frage);
            b1.setText(JsonReader.antwortenAuslesen(array[0],question));
            b2.setText(JsonReader.antwortenAuslesen(array[1],question));
            b3.setText(JsonReader.antwortenAuslesen(array[2],question));
            b4.setText(JsonReader.antwortenAuslesen(array[3],question));
            if(array[0] == 0){
                b1.setOpacity(15);
            }
            else if(array[1] == 0){
                b2.setOpacity(15);
            }
            else if(array[2] == 0){
                b3.setOpacity(15);
            }
            else if(array[3] == 0){
                b4.setOpacity(15);
            }
            question++;
        }



    //Für Antwortbuttons
    public boolean checkQuestion(Button b){
        if (b.getOpacity() == 15){
            b.setOpacity(100);
            return true;
        }else{
            return false;
        }
    }

    //Nur für 50:50 Joker
    public boolean checkOnly(Button b){
        if (b.getOpacity() == 15){
            return true;
        }else {
            return false;
        }
    }


    public static void verriegelungAntworten(final int[] array, final int idx, final Random rnd) {
        array[idx] = rnd.nextInt(4);
        for (int i = 0; i < idx; i++) {
            if (array[i] == array[idx]) {
                // re-generate
                verriegelungAntworten(array, idx, rnd);
                // all next numbers are already generated
                // by the recursion so we can step out here
                return;
            }
        }

        if (idx < array.length - 1) {
            // generate next index
            verriegelungAntworten(array, idx + 1, rnd);
        }
    }


    public int getQuestion(){
        return question;
    }

    public String getScore(){
        String money = null;
        switch (question){
            case 1: money = "0 €";
            break;
            case 2: money = "0 €";
            break;
            case 3: money = "50 €";
            break;
            case 4: money = "100 €";
            break;
            case 5: money = "200 €";
                break;
            case 6: money = "300 €";
                break;
            case 7: money = "500 €";
                break;
            case 8: money = "1.000 €";
                break;
            case 9: money = "2.000 €";
                break;
            case 10: money = "4.000 €";
                break;
            case 11: money = "8.000 €";
                break;
            case 12: money = "16.000 €";
                break;
            case 13: money = "32.000 €";
                break;
            case 14: money = "64.000 €";
                break;
            case 15: money = "125.000 €";
                break;
            case 16: money = "500.000 €";
                break;
            case 17: money = "1 Million €";
                break;
        }
        return money;
    }
    public String getScoreloose(){
        String money;
        if (question < 7){
            money = "0 €";
        }else if (question < 12){
            money = "500 €";
        }else {
            money = "16.000 €";
        }
        return money;
    }

}
