package infinity.reborn.musicplayerjavafx;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MusicPlayerController {

    @FXML
    private Label infoLabel;

    @FXML
    private TextField searchField;

    @FXML
    private VBox resultsContainer;

    @FXML
    private Button searchButton;

    private final DeezerService deezerService = new DeezerService();
    private MediaPlayer mediaPlayer;

    public void initialize() {
        try {
            String info = deezerService.getDeezerInfo();
            infoLabel.setText(info);
        } catch (IOException e) {
            infoLabel.setText("Failed to fetch Deezer info");
            e.printStackTrace();
        }

        // Ustawienie ikony dla przycisku szukania
        searchButton.setText("\uf002"); // Unicode dla ikony Font Awesome "search"
        searchButton.setStyle("-fx-font-family: 'Font Awesome 6 Free'; -fx-font-weight: 900; -fx-font-size: 14px;");
    }

    @FXML
    public void onSearchButtonClick() {
        String query = searchField.getText();
        try {
            String response = deezerService.searchTracks(query);
            updateResults(response);
        } catch (IOException e) {
            infoLabel.setText("Failed to search tracks");
            e.printStackTrace();
        }
    }

    private void updateResults(String response) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        Type trackListType = new TypeToken<List<Track>>() {}.getType();
        List<Track> tracks = gson.fromJson(jsonObject.get("data"), trackListType);

        resultsContainer.getChildren().clear();
        for (Track track : tracks) {
            VBox trackBox = new VBox();
            trackBox.setSpacing(5);
            trackBox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 1; -fx-border-insets: 5; -fx-border-radius: 5; -fx-border-color: grey;");

            Label label = new Label(track.getTitle() + " - " + track.getArtist().getName());
            label.setStyle("-fx-font-weight: bold;");

            HBox buttonBox = new HBox();
            buttonBox.setSpacing(10);

            Button playButton = new Button("\uf04b"); // Unicode dla ikony Font Awesome "play"
            playButton.setStyle("-fx-font-family: 'Font Awesome 6 Free'; -fx-font-weight: 900; -fx-font-size: 14px;");
            playButton.setOnAction(event -> onPlayPreviewClick(track.getPreview()));

            Button stopButton = new Button("\uf04d"); // Unicode dla ikony Font Awesome "stop"
            stopButton.setStyle("-fx-font-family: 'Font Awesome 6 Free'; -fx-font-weight: 900; -fx-font-size: 14px;");
            stopButton.setOnAction(event -> onStopPreviewClick());

            buttonBox.getChildren().addAll(playButton, stopButton);

            Rating rating = new Rating();
            rating.setOnMouseClicked(event -> {
            });

            trackBox.getChildren().addAll(label, buttonBox, rating);
            resultsContainer.getChildren().add(trackBox);
        }
    }

    @FXML
    public void onPlayPreviewClick(String previewUrl) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media media = new Media(previewUrl);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @FXML
    public void onStopPreviewClick() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
