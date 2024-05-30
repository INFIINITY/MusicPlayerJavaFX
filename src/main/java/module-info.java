module infinity.reborn.musicplayerjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens infinity.reborn.musicplayerjavafx to javafx.fxml;
    exports infinity.reborn.musicplayerjavafx;
}