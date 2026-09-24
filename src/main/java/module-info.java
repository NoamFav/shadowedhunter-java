module com.ShadowedHunter {
    // UI and audio
    requires javafx.controls;
    requires javafx.media;

    // External dependencies
    requires org.slf4j; // For logging API
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;

    // JavaFX instantiates the application class reflectively
    exports com.shadowedhunter;
    exports com.shadowedhunter.ui to javafx.graphics;
}
