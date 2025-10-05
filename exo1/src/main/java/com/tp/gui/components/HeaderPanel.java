package com.tp.tp1_part2.gui.components;

import com.tp.tp1_part2.gui.styles.UIConstants;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HeaderPanel {
    
    public static VBox create() {
        VBox header = new VBox(5);
        header.setPadding(new Insets(18, 25, 18, 25));
        header.setStyle(
            "-fx-background-color: " + UIConstants.CARD + ";" +
            "-fx-border-color: #cbd5e1; " +
            "-fx-border-width: 0 0 1px 0;"
        );
        
        Label title = new Label("Analyseur Statique Java");
        title.setStyle(
            "-fx-font-size: 22px; " +
            "-fx-font-weight: 600; " +
            "-fx-text-fill: " + UIConstants.PRIMARY + ";"
        );
        
        Label subtitle = new Label("HAI913I - TP1 Partie 2");
        subtitle.setStyle(
            "-fx-font-size: 12px; " +
            "-fx-text-fill: " + UIConstants.TEXT_LIGHT + ";"
        );
        
        header.getChildren().addAll(title, subtitle);
        return header;
    }
}
