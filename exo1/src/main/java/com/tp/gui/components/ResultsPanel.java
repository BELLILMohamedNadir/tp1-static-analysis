package com.tp.tp1_part2.gui.components;

import com.tp.tp1_part2.gui.formatters.ResultsFormatter;
import com.tp.tp1_part2.gui.styles.UIConstants;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ResultsPanel {
    
    private final TextArea resultsArea;
    
    public ResultsPanel() {
        resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setWrapText(false);
        
        // Forcer la police monospace
        resultsArea.setFont(Font.font("Monospaced", 14));
        
        resultsArea.setStyle(
            "-fx-font-family: 'Monospaced', 'Courier New', 'Consolas', monospace; " +
            "-fx-font-size: 14px; " +
            "-fx-background-color: #fefefe; " +
            "-fx-control-inner-background: #fefefe; " +
            "-fx-text-fill: " + UIConstants.TEXT + "; " +
            "-fx-padding: 20px;"
        );
        resultsArea.setText(ResultsFormatter.getWelcomeMessage());
    }
    
    public VBox create() {
        VBox resultsPanel = new VBox(12);
        
        Label resultsTitle = new Label("Résultats d'Analyse");
        resultsTitle.setStyle(
            "-fx-font-size: 16px; " +
            "-fx-font-weight: 600; " +
            "-fx-text-fill: " + UIConstants.TEXT + ";"
        );
        
        VBox resultsCard = new VBox();
        resultsCard.setStyle(
            "-fx-background-color: " + UIConstants.CARD + ";" +
            "-fx-background-radius: 8px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);"
        );
        
        resultsCard.getChildren().add(resultsArea);
        VBox.setVgrow(resultsCard, Priority.ALWAYS);
        VBox.setVgrow(resultsArea, Priority.ALWAYS);
        
        resultsPanel.getChildren().addAll(resultsTitle, resultsCard);
        VBox.setVgrow(resultsPanel, Priority.ALWAYS);
        
        return resultsPanel;
    }
    
    public void setText(String text) {
        resultsArea.setText(text);
    }
    
    public void clear() {
        resultsArea.clear();
    }
}
