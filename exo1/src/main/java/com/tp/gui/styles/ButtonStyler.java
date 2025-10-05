package com.tp.tp1_part2.gui.styles;

import javafx.scene.control.Button;

public class ButtonStyler {
    
    public static void stylePrimaryButton(Button btn) {
        btn.setStyle(
            "-fx-background-color: " + UIConstants.PRIMARY + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: 600;" +
            "-fx-padding: 10px;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        );
        
        btn.setOnMouseEntered(e -> {
            if (!btn.isDisabled()) {
                btn.setStyle(
                    "-fx-background-color: #333333;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: 600;" +
                    "-fx-padding: 10px;" +
                    "-fx-background-radius: 6px;" +
                    "-fx-cursor: hand;"
                );
            }
        });
        
        btn.setOnMouseExited(e -> {
            if (!btn.isDisabled()) {
                stylePrimaryButton(btn);
            }
        });
    }
    
    public static void styleSecondaryButton(Button btn) {
        btn.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: " + UIConstants.PRIMARY + ";" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: 600;" +
            "-fx-padding: 8px;" +
            "-fx-background-radius: 6px;" +
            "-fx-border-color: " + UIConstants.PRIMARY + ";" +
            "-fx-border-radius: 6px;" +
            "-fx-border-width: 1.5px;" +
            "-fx-cursor: hand;"
        );
        
        btn.setOnMouseEntered(e -> {
            btn.setStyle(
                "-fx-background-color: " + UIConstants.PRIMARY + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 600;" +
                "-fx-padding: 8px;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
            );
        });
        
        btn.setOnMouseExited(e -> styleSecondaryButton(btn));
    }
    
    public static void styleDangerButton(Button btn) {
        btn.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: " + UIConstants.DANGER + ";" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: 600;" +
            "-fx-padding: 8px;" +
            "-fx-background-radius: 6px;" +
            "-fx-border-color: #fca5a5;" +
            "-fx-border-radius: 6px;" +
            "-fx-border-width: 1.5px;" +
            "-fx-cursor: hand;"
        );
        
        btn.setOnMouseEntered(e -> {
            btn.setStyle(
                "-fx-background-color: " + UIConstants.DANGER + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 600;" +
                "-fx-padding: 8px;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
            );
        });
        
        btn.setOnMouseExited(e -> styleDangerButton(btn));
    }
}
