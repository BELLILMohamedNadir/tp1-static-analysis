package com.tp.tp1_part2.gui;

import com.tp.tp1_part2.analyzer.CodeAnalyzer;
import com.tp.tp1_part2.gui.components.*;
import com.tp.tp1_part2.gui.formatters.ResultsFormatter;
import com.tp.tp1_part2.gui.styles.UIConstants;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.util.Map;

public class AnalyzerGUI extends Application {
    
    private CodeAnalyzer analyzer;
    private ControlPanel controlPanel;
    private ResultsPanel resultsPanel;
    private FooterPanel footerPanel;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Analyseur Java - HAI913I");
        
        controlPanel = new ControlPanel(primaryStage);
        resultsPanel = new ResultsPanel();
        footerPanel = new FooterPanel();
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + UIConstants.BG + ";");
        
        root.setTop(HeaderPanel.create());
        root.setCenter(createMainContent());
        root.setBottom(footerPanel.create());
        
        controlPanel.setOnAnalyze(this::analyzeCode);
        
        Scene scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(UIConstants.MIN_WIDTH);
        primaryStage.setMinHeight(UIConstants.MIN_HEIGHT);
        primaryStage.show();
    }
    
    private HBox createMainContent() {
        HBox mainContent = new HBox(18);
        mainContent.setPadding(new Insets(UIConstants.PADDING_LARGE));
        
        VBox leftPanel = controlPanel.create();
        leftPanel.setPrefWidth(UIConstants.LEFT_PANEL_WIDTH);
        leftPanel.setMinWidth(UIConstants.LEFT_PANEL_MIN_WIDTH);
        
        VBox rightPanel = resultsPanel.create();
        HBox.setHgrow(rightPanel, Priority.ALWAYS);
        
        mainContent.getChildren().addAll(leftPanel, rightPanel);
        return mainContent;
    }
    
    private void analyzeCode() {
        String path = controlPanel.getPath();
        
        if (path.isEmpty()) {
            showAlert("Attention", "Veuillez sélectionner un répertoire.", Alert.AlertType.WARNING);
            return;
        }
        
        File file = new File(path);
        if (!file.exists()) {
            showAlert("Erreur", "Le chemin n'existe pas.", Alert.AlertType.ERROR);
            return;
        }
        
        int threshold = controlPanel.getThreshold();
        
        controlPanel.setAnalyzing(true);
        footerPanel.updateStatus("Analyse en cours...");
        resultsPanel.clear();
        
        try {
            analyzer = new CodeAnalyzer();
            
            if (file.isDirectory()) {
                analyzer.analyzeDirectory(path);
            } else if (path.endsWith(".java")) {
                analyzer.analyzeSourceFile(path);
            } else {
                showAlert("Erreur", "Sélectionnez un répertoire ou fichier .java.", Alert.AlertType.WARNING);
                return;
            }
            
            displayResults(threshold);
            footerPanel.updateStatus("✓ Analyse terminée avec succès");
            
        } catch (Exception e) {
            showAlert("Erreur", "Erreur : " + e.getMessage(), Alert.AlertType.ERROR);
            footerPanel.updateStatus("✗ Échec de l'analyse");
            e.printStackTrace();
        } finally {
            controlPanel.setAnalyzing(false);
        }
    }
    
    private void displayResults(int threshold) {
        Map<String, Object> stats = analyzer.getStatistics(threshold);
        String formattedResults = ResultsFormatter.formatResults(stats, threshold);
        resultsPanel.setText(formattedResults);
    }
    
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
