package at.ac.fhcampuswien;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Random;


public class ControllerGame {


    private Stage window = new Stage();

    boolean secondChance = false; // Man hat keine Second Chance.

    @FXML
    private Button answerA,answerB,answerC,answerD,giveUpBtn;

    @FXML
    private Label labques;

    @FXML
    private Polyline triangle;

    GameLoop newQuestion = new GameLoop();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreen.fxml"));

    @FXML
    public void initialize() throws IOException {
        newQuestion.nextQuestion(labques,answerA,answerB,answerC,answerD);
    }


    @FXML
    private void answerAPressed(ActionEvent actionEvent) {


        //Buttons werden deaktiviert
        answerA.setOnAction(null);
        answerB.setOnAction(null);
        answerC.setOnAction(null);
        answerD.setOnAction(null);
        giveUpBtn.setDisable(true);

        //Farbe wird auf Orange gesetzt
        answerA.setStyle("-fx-background-color:#ffa500;");

        //2 Sekunden warten
        PauseTransition wait = new PauseTransition(Duration.seconds(2));
        wait.setOnFinished(event -> {
            if (newQuestion.checkQuestion(answerA)){ //Frage richtig beantwortet
                answerA.setStyle("-fx-background-color:#00b300;"); //Farbe Grün
                PauseTransition wait2 = new PauseTransition(Duration.seconds(2));
                wait2.setOnFinished(event1 -> {
                    answerA.setStyle("-fx-background-color:#0a58a1;"); //Farbe Blau

                    //Nächste Frage wird geladen
                    try {
                        newQuestion.nextQuestion(labques, answerA, answerB, answerC, answerD);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    secondChance = false; // Falls Frage trotz Joker richtig beantwortet wurde.

                    if (newQuestion.getQuestion() < 17) {
                        //Gelbes Dreieck nach oben verschoben
                        triangle.setTranslateY(triangle.getTranslateY() - 23);
                    } else {
                        Parent end = null;
                        try {
                            end = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Endscreen wird geladen und angezeigt
                        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(new Scene(end));

                        //Controller für EndScreen wird geladen
                        ControllerEnd controller = loader.getController();

                        //Label für moneyWon wird gesetzt
                        controller.setMoneyWon(newQuestion.getScore());
                    }
                    //Buttons werden reaktiviert
                    answerA.setOnAction(this::answerAPressed);
                    answerB.setOnAction(this::answerBPressed);
                    answerC.setOnAction(this::answerCPressed);
                    answerD.setOnAction(this::answerDPressed);
                    giveUpBtn.setDisable(false);
                });
                wait2.play();
            }else{ // Frage falsch beantwortet.
                //Button wird Rot
                answerA.setStyle("-fx-background-color:#ff0000;");

                if (newQuestion.checkOnly(answerB) && secondChance==false){
                    answerB.setStyle("-fx-background-color:#00b300;");
                    answerB.setOpacity(15);
                }
                if (newQuestion.checkOnly(answerC) && secondChance==false){
                    answerC.setStyle("-fx-background-color:#00b300;");
                    answerC.setOpacity(15);
                }
                if (newQuestion.checkOnly(answerD) && secondChance==false){
                    answerD.setStyle("-fx-background-color:#00b300;");
                    answerD.setOpacity(15);
                }


                PauseTransition wait2 = new PauseTransition(Duration.seconds(2));
                wait2.setOnFinished(event1 -> {
                    if (secondChance) {
                        answerA.setStyle("-fx-background-color:#0a58a1;"); // Antwortbutton wieder blau.
                        answerA.setText(null);
                        secondChance = false;
                        //Buttons werden reaktiviert
                        answerB.setOnAction(this::answerBPressed);
                        answerC.setOnAction(this::answerCPressed);
                        answerD.setOnAction(this::answerDPressed);
                        giveUpBtn.setDisable(false);
                    } else {
                        try {
                            //Endscreen wird geladen und angezeigt
                            Parent end = loader.load();
                            window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window.setScene(new Scene(end));
                            //Controller für Endscreen und moneywon wird gesetzt
                            ControllerEnd controller = loader.getController();
                            controller.setMoneyWon(newQuestion.getScoreloose());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                wait2.play();
            }
        });
        wait.play();
    }

    @FXML
    private void answerBPressed(ActionEvent actionEvent) {
        answerA.setOnAction(null);
        answerB.setOnAction(null);
        answerC.setOnAction(null);
        answerD.setOnAction(null);
        giveUpBtn.setDisable(true);
        answerB.setStyle("-fx-background-color:#ffa500;");
        PauseTransition wait = new PauseTransition(Duration.seconds(2));
        wait.setOnFinished(event -> {
            if (newQuestion.checkQuestion(answerB)){
                answerB.setStyle("-fx-background-color:#00b300;");
                PauseTransition wait2 = new PauseTransition(Duration.seconds(2));
                wait2.setOnFinished(event1 -> {
                    answerB.setStyle("-fx-background-color:#0a58a1;");
                    try {
                        newQuestion.nextQuestion(labques, answerA, answerB, answerC, answerD);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    secondChance = false;
                    if (newQuestion.getQuestion() < 17) {
                        triangle.setTranslateY(triangle.getTranslateY() - 23);
                    } else {
                        Parent end = null;
                        try {
                            end = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(new Scene(end));
                        ControllerEnd controller = loader.getController();
                        controller.setMoneyWon(newQuestion.getScore());
                    }
                    answerA.setOnAction(this::answerAPressed);
                    answerB.setOnAction(this::answerBPressed);
                    answerC.setOnAction(this::answerCPressed);
                    answerD.setOnAction(this::answerDPressed);
                    giveUpBtn.setDisable(false);
                });
                wait2.play();
            }else{
                answerB.setStyle("-fx-background-color:#ff0000;");


                if (newQuestion.checkOnly(answerA) && secondChance==false){
                    answerA.setStyle("-fx-background-color:#00b300;");
                    answerA.setOpacity(15);
                }
                if (newQuestion.checkOnly(answerC) && secondChance==false){
                    answerC.setStyle("-fx-background-color:#00b300;");
                    answerC.setOpacity(15);
                }
                if (newQuestion.checkOnly(answerD) && secondChance==false){
                    answerD.setStyle("-fx-background-color:#00b300;");
                    answerD.setOpacity(15);
                }


                PauseTransition wait2 = new PauseTransition(Duration.seconds(2));
                wait2.setOnFinished(event1 -> {
                    if (secondChance) {
                        answerB.setStyle("-fx-background-color:#0a58a1;"); // Antwortbutton wieder blau.
                        answerB.setText(null);
                        secondChance = false;
                        answerA.setOnAction(this::answerAPressed);
                        answerC.setOnAction(this::answerCPressed);
                        answerD.setOnAction(this::answerDPressed);
                        giveUpBtn.setDisable(false);
                    } else {
                        try {
                            Parent end = loader.load();
                            window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window.setScene(new Scene(end));
                            ControllerEnd controller = loader.getController();
                            controller.setMoneyWon(newQuestion.getScoreloose());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                wait2.play();
            }
        });
        wait.play();
    }

    @FXML
    private void answerCPressed(ActionEvent actionEvent) {
        answerA.setOnAction(null);
        answerB.setOnAction(null);
        answerC.setOnAction(null);
        answerD.setOnAction(null);
        giveUpBtn.setDisable(true);
        answerC.setStyle("-fx-background-color:#ffa500;");
        PauseTransition wait = new PauseTransition(Duration.seconds(2));
        wait.setOnFinished(event -> {
            if (newQuestion.checkQuestion(answerC)){
                answerC.setStyle("-fx-background-color:#00b300;");
                PauseTransition wait2 = new PauseTransition(Duration.seconds(2));
                wait2.setOnFinished(event1 -> {
                    answerC.setStyle("-fx-background-color:#0a58a1;");
                    try {
                        newQuestion.nextQuestion(labques, answerA, answerB, answerC, answerD);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    secondChance = false;
                    if (newQuestion.getQuestion() < 17) {
                        triangle.setTranslateY(triangle.getTranslateY() - 23);
                    } else {
                        Parent end = null;
                        try {
                            end = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(new Scene(end));
                        ControllerEnd controller = loader.getController();
                        controller.setMoneyWon(newQuestion.getScore());
                    }
                    answerA.setOnAction(this::answerAPressed);
                    answerB.setOnAction(this::answerBPressed);
                    answerC.setOnAction(this::answerCPressed);
                    answerD.setOnAction(this::answerDPressed);
                    giveUpBtn.setDisable(false);
                });
                wait2.play();
            }else{
                answerC.setStyle("-fx-background-color:#ff0000;");


                if (newQuestion.checkOnly(answerA) && secondChance==false){
                    answerA.setStyle("-fx-background-color:#00b300;");
                    answerA.setOpacity(15);
                }
                if (newQuestion.checkOnly(answerB) && secondChance==false){
                    answerB.setStyle("-fx-background-color:#00b300;");
                    answerB.setOpacity(15);
                }
                if (newQuestion.checkOnly(answerD) && secondChance==false){
                    answerD.setStyle("-fx-background-color:#00b300;");
                    answerD.setOpacity(15);
                }


                PauseTransition wait2 = new PauseTransition(Duration.seconds(2));
                wait2.setOnFinished(event1 -> {
                    if (secondChance) {
                        answerC.setStyle("-fx-background-color:#0a58a1;"); // Antwortbutton wieder blau.
                        answerC.setText(null);
                        secondChance = false;
                        answerA.setOnAction(this::answerAPressed);
                        answerB.setOnAction(this::answerBPressed);
                        answerD.setOnAction(this::answerDPressed);
                        giveUpBtn.setDisable(false);
                    } else {
                        try {
                            Parent end = loader.load();
                            window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window.setScene(new Scene(end));
                            ControllerEnd controller = loader.getController();
                            controller.setMoneyWon(newQuestion.getScoreloose());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                wait2.play();
            }
        });
        wait.play();
    }




    @FXML
    private void answerDPressed(ActionEvent actionEvent) {
        answerA.setOnAction(null);
        answerB.setOnAction(null);
        answerC.setOnAction(null);
        answerD.setOnAction(null);
        giveUpBtn.setDisable(true);
        answerD.setStyle("-fx-background-color:#ffa500;");
        PauseTransition wait = new PauseTransition(Duration.seconds(2));
        wait.setOnFinished(event -> {
            if (newQuestion.checkQuestion(answerD)){
                answerD.setStyle("-fx-background-color:#00b300;");
                PauseTransition wait2 = new PauseTransition(Duration.seconds(2));
                wait2.setOnFinished(event1 -> {
                    answerD.setStyle("-fx-background-color:#0a58a1;");
                    try {
                        newQuestion.nextQuestion(labques, answerA, answerB, answerC, answerD);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    secondChance = false;
                    if (newQuestion.getQuestion() < 17) {
                        triangle.setTranslateY(triangle.getTranslateY() - 23);
                    } else {
                        Parent end = null;
                        try {
                            end = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(new Scene(end));
                        ControllerEnd controller = loader.getController();
                        controller.setMoneyWon(newQuestion.getScore());
                    }
                    answerA.setOnAction(this::answerAPressed);
                    answerB.setOnAction(this::answerBPressed);
                    answerC.setOnAction(this::answerCPressed);
                    answerD.setOnAction(this::answerDPressed);
                    giveUpBtn.setDisable(false);
                });
                wait2.play();
            }else{
                answerD.setStyle("-fx-background-color:#ff0000;");

                if (newQuestion.checkOnly(answerA) && secondChance==false){
                    answerA.setStyle("-fx-background-color:#00b300;");
                    answerA.setOpacity(15);
                }
                if (newQuestion.checkOnly(answerB) && secondChance==false){
                    answerB.setStyle("-fx-background-color:#00b300;");
                    answerB.setOpacity(15);
                }
                if (newQuestion.checkOnly(answerC) && secondChance==false){
                    answerC.setStyle("-fx-background-color:#00b300;");
                    answerC.setOpacity(15);
                }

                PauseTransition wait2 = new PauseTransition(Duration.seconds(2));
                wait2.setOnFinished(event1 -> {
                    if (secondChance) {
                        answerD.setStyle("-fx-background-color:#0a58a1;"); // Antwortbutton wieder blau.
                        answerD.setText(null);
                        secondChance = false;
                        answerA.setOnAction(this::answerAPressed);
                        answerB.setOnAction(this::answerBPressed);
                        answerC.setOnAction(this::answerCPressed);
                        giveUpBtn.setDisable(false);
                    } else {
                        try {
                            Parent end = loader.load();
                            window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window.setScene(new Scene(end));
                            ControllerEnd controller = loader.getController();
                            controller.setMoneyWon(newQuestion.getScoreloose());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                wait2.play();
            }
        });
        wait.play();
    }


    // JOKER

    @FXML
    private Button fiftyJoker, pubJoker, secondJoker;

    @FXML
    private void secondJokerPressed(ActionEvent actionEvent) {
        secondJoker.setOnAction(null);
        secondJoker.setStyle("-fx-background-color:#D0D3D4;");
        secondChance = true;
    }

    @FXML
    private void pubJokerPressed(ActionEvent actionEvent) throws IOException {
        pubJoker.setOnAction(null);
        pubJoker.setStyle("-fx-background-color:#D0D3D4;");
        Random rand = new Random();
        int random1, random2, random3, random4;

        if (newQuestion.getQuestion() < 7) {
            random1 = rand.nextInt(25);
            random2 = rand.nextInt(25);
            random3 = rand.nextInt(25);
            random4 = 100 - random1 - random2 - random3;
        }else if (newQuestion.getQuestion() < 12){
            random1 = rand.nextInt(30);
            random2 = rand.nextInt(30);
            random3 = rand.nextInt(30);
            random4 = 100 - random1 - random2 - random3;
        }else {
            random1 = rand.nextInt(35);
            random2 = rand.nextInt(35);
            random3 = rand.nextInt(35);
            random4 = 100 - random1 - random2 - random3;
        }

        FXMLLoader load = new FXMLLoader(getClass().getResource("Publikum.fxml"));
        Parent pub = load.load();
        Stage publikum = new Stage();
        publikum.setScene(new Scene(pub));
        publikum.setTitle("Das Publikum sagt...");
        publikum.show();

        ControllerPublikum publi = load.getController();

        if (newQuestion.checkOnly(answerA)) {
            publi.setText(answerA.getText(), answerB.getText(), answerC.getText(), answerD.getText(), random4, random2, random3, random1);
        }else if (newQuestion.checkOnly(answerB)){
            publi.setText(answerA.getText(), answerB.getText(), answerC.getText(), answerD.getText(), random2, random4, random3, random1);
        }else if (newQuestion.checkOnly(answerC)){
            publi.setText(answerA.getText(), answerB.getText(), answerC.getText(), answerD.getText(), random3, random2, random4, random1);
        }else if (newQuestion.checkOnly(answerD)){
            publi.setText(answerA.getText(), answerB.getText(), answerC.getText(), answerD.getText(), random1, random2, random3, random4);
        }
    }

    @FXML
    private void fiftyJokerPressed(ActionEvent actionEvent) {
        fiftyJoker.setOnAction(null);
        fiftyJoker.setStyle("-fx-background-color:#D0D3D4;");
        if (newQuestion.checkOnly(answerA)) {
            if (answerD.getText() == null) {
                answerB.setText(null);
                answerB.setOnAction(null);
                answerC.setText(null);
                answerC.setOnAction(null);
            } else if (answerB.getText() == null){
                answerC.setText(null);
                answerC.setOnAction(null);
                answerD.setText(null);
                answerD.setOnAction(null);
            } else if (answerC.getText() == null){
                answerB.setText(null);
                answerB.setOnAction(null);
                answerD.setText(null);
                answerD.setOnAction(null);
            } else {
                answerB.setText(null);
                answerB.setOnAction(null);
                answerC.setText(null);
                answerC.setOnAction(null);
            }
        }
        else if (newQuestion.checkOnly(answerB)){
            if (answerA.getText() == null) {
                answerD.setText(null);
                answerD.setOnAction(null);
                answerC.setText(null);
                answerC.setOnAction(null);
            } else if (answerC.getText() == null){
                answerA.setText(null);
                answerA.setOnAction(null);
                answerD.setText(null);
                answerD.setOnAction(null);
            } else if (answerD.getText() == null){
                answerA.setText(null);
                answerA.setOnAction(null);
                answerC.setText(null);
                answerC.setOnAction(null);
            } else {
                answerD.setText(null);
                answerD.setOnAction(null);
                answerC.setText(null);
                answerC.setOnAction(null);
            }
        }
        else if (newQuestion.checkOnly(answerC)){
            if (answerA.getText() == null) {
                answerB.setText(null);
                answerB.setOnAction(null);
                answerD.setText(null);
                answerD.setOnAction(null);
            } else if (answerB.getText() == null){
                answerA.setText(null);
                answerA.setOnAction(null);
                answerD.setText(null);
                answerD.setOnAction(null);
            } else if (answerD.getText() == null){
                answerB.setText(null);
                answerB.setOnAction(null);
                answerA.setText(null);
                answerA.setOnAction(null);
            } else {
                answerB.setText(null);
                answerB.setOnAction(null);
                answerD.setText(null);
                answerD.setOnAction(null);
            }
        }
        else if (newQuestion.checkOnly(answerD)){
            if (answerA.getText() == null) {
                answerB.setText(null);
                answerB.setOnAction(null);
                answerC.setText(null);
                answerC.setOnAction(null);
            } else if (answerB.getText() == null){
                answerC.setText(null);
                answerC.setOnAction(null);
                answerA.setText(null);
                answerA.setOnAction(null);
            } else if (answerC.getText() == null){
                answerB.setText(null);
                answerB.setOnAction(null);
                answerA.setText(null);
                answerA.setOnAction(null);
            } else {
                answerB.setText(null);
                answerB.setOnAction(null);
                answerC.setText(null);
                answerC.setOnAction(null);
            }
        }

    }


    @FXML
    private void giveUp(ActionEvent actionEvent) throws IOException {
        Parent end = loader.load();
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(end));
        ControllerEnd controller = loader.getController();
        controller.setMoneyWon(newQuestion.getScore());
    }
}
