package com.tp.tp1_part2.gui.components;

import com.tp.tp1_part2.gui.styles.ButtonStyler;
import com.tp.tp1_part2.gui.styles.UIConstants;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.function.Consumer;

public class ControlPanel {
    
    private final TextField pathField;
    private final Spinner<Integer> thresholdSpinner;
    private final Button analyzeBtn;
    private final ProgressBar progressBar;
    private final Stage stage;
    
    public ControlPanel(Stage stage) {
        this.stage = stage;
        this.pathField = createPathField();
        this.thresholdSpinner = createThresholdSpinner();
        this.analyzeBtn = createAnalyzeButton();
        this.progressBar = createProgressBar();
    }
    
    public VBox create() {
        VBox controlPanel = new VBox(12);
        
        VBox configCard = createCard("Configuration", createConfigSection());
        VBox actionsCard = createCard("Actions", createActionsSection());
        
        controlPanel.getChildren().addAll(configCard, actionsCard);
        
        return controlPanel;
    }
    
    private VBox createConfigSection() {
        VBox config = new VBox(10);
        
        Label pathLabel = new Label("Chemin du Code Source");
        pathLabel.setStyle("-fx-font-weight: 600; -fx-font-size: 12px; -fx-text-fill: " + UIConstants.TEXT + ";");
        
        Button browseBtn = new Button("Parcourir");
        browseBtn.setMaxWidth(Double.MAX_VALUE);
        ButtonStyler.styleSecondaryButton(browseBtn);
        browseBtn.setOnAction(e -> browseDirectory());
        
        Label thresholdLabel = new Label("Seuil de Méthodes (Question 11)");
        thresholdLabel.setStyle("-fx-font-weight: 600; -fx-font-size: 12px; -fx-text-fill: " + UIConstants.TEXT + ";");
        
        config.getChildren().addAll(
            pathLabel, pathField, browseBtn,
            new Separator(),
            thresholdLabel, thresholdSpinner
        );
        
        return config;
    }
    
    private VBox createActionsSection() {
        VBox actions = new VBox(8);
        actions.getChildren().addAll(analyzeBtn, progressBar);
        return actions;
    }
    
    private TextField createPathField() {
        TextField field = new TextField();
        field.setPromptText("Sélectionner un répertoire...");
        field.setStyle(
            "-fx-padding: 8px 12px; " +
            "-fx-font-size: 12px; " +
            "-fx-background-color: " + UIConstants.BG + "; " +
            "-fx-background-radius: 6px; " +
            "-fx-border-color: #cbd5e1; " +
            "-fx-border-radius: 6px; " +
            "-fx-border-width: 1px;"
        );
        return field;
    }
    
    private Spinner<Integer> createThresholdSpinner() {
        Spinner<Integer> spinner = new Spinner<>(
            UIConstants.MIN_THRESHOLD, 
            UIConstants.MAX_THRESHOLD, 
            UIConstants.DEFAULT_THRESHOLD
        );
        spinner.setEditable(true);
        spinner.setMaxWidth(Double.MAX_VALUE);
        spinner.setStyle("-fx-font-size: 12px;");
        return spinner;
    }
    
    private Button createAnalyzeButton() {
        Button btn = new Button("▶  Analyser le Code");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(40);
        ButtonStyler.stylePrimaryButton(btn);
        return btn;
    }
    
    private ProgressBar createProgressBar() {
        ProgressBar bar = new ProgressBar();
        bar.setMaxWidth(Double.MAX_VALUE);
        bar.setPrefHeight(8);
        bar.setVisible(false);
        bar.setStyle("-fx-accent: " + UIConstants.PRIMARY + ";");
        return bar;
    }
    
    private VBox createCard(String title, VBox content) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(14));
        card.setStyle(
            "-fx-background-color: " + UIConstants.CARD + ";" +
            "-fx-background-radius: 8px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6, 0, 0, 1);"
        );
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle(
            "-fx-font-size: 13px; " +
            "-fx-font-weight: 600; " +
            "-fx-text-fill: " + UIConstants.TEXT + ";"
        );
        
        Separator separator = new Separator();
        
        card.getChildren().addAll(titleLabel, separator, content);
        return card;
    }
    
    private void browseDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Sélectionner le Répertoire");
        File dir = chooser.showDialog(stage);
        
        if (dir != null) {
            pathField.setText(dir.getAbsolutePath());
        }
    }
    
    public String getPath() {
        return pathField.getText().trim();
    }
    
    public int getThreshold() {
        return thresholdSpinner.getValue();
    }
    
    public void setOnAnalyze(Runnable action) {
        analyzeBtn.setOnAction(e -> action.run());
    }
    
    public void setAnalyzing(boolean analyzing) {
        analyzeBtn.setDisable(analyzing);
        analyzeBtn.setText(analyzing ? "⏳ Analyse en cours..." : "▶  Analyser le Code");
        progressBar.setVisible(analyzing);
        progressBar.setProgress(analyzing ? -1 : 0);
        
        if (!analyzing) {
            ButtonStyler.stylePrimaryButton(analyzeBtn);
        }
    }
}
