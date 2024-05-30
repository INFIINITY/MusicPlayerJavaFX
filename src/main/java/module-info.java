module infinity.reborn.musicplayerjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires okhttp3;
    requires com.google.gson;
    requires javafx.media;

    opens infinity.reborn.musicplayerjavafx to javafx.fxml, com.google.gson;
    exports infinity.reborn.musicplayerjavafx;
}