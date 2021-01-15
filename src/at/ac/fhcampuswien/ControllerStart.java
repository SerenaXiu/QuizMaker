package at.ac.fhcampuswien;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class ControllerStart {

    private Stage window = new Stage();
    private String dirPath = System.getProperty("user.dir");
    File musicanfang = new File(dirPath + "//src//rsrc//Music//anfang.mp3");
    Media soundanfang = new Media(musicanfang.toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(soundanfang);




    @FXML
    private void startGame(ActionEvent actionEvent) throws Exception {
        Parent game = FXMLLoader.load(getClass().getResource("Gamescreen.fxml"));
        window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(game));
        mediaPlayer.play();
    }

    @FXML
    private void closeGame(ActionEvent actionEvent) {
        System.exit(0);
    }


    @FXML
    private void goToManagement(MouseEvent mouseEvent) throws IOException {
        Parent manage = FXMLLoader.load(getClass().getResource("Management.fxml"));
        window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(manage));
    }


}
