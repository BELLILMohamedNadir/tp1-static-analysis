package com.tp.tp1_part2.gui.components;

import com.tp.tp1_part2.gui.styles.UIConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FooterPanel {
    
    private final Label statusLabel;
    
    public FooterPanel() {
        statusLabel = new Label("Prêt - Sélectionnez un répertoire pour commencer");
        statusLabel.setStyle(
            "-fx-font-size: 11px; " +
            "-fx-text-fill: " + UIConstants.TEXT_LIGHT + ";"
        );
    }
    
    public HBox create() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(12, 25, 12, 25));
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setStyle(
            "-fx-background-color: " + UIConstants.CARD + ";" +
            "-fx-border-color: #cbd5e1; " +
            "-fx-border-width: 1px 0 0 0;"
        );
        
        footer.getChildren().add(statusLabel);
        return footer;
    }
    
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }
}
