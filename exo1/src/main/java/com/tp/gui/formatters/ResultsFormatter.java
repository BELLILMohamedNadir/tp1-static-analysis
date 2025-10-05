package com.tp.tp1_part2.gui.formatters;

import com.tp.tp1_part2.analyzer.ClassInfo;
import java.util.List;
import java.util.Map;

public class ResultsFormatter {
    
    public static String formatResults(Map<String, Object> stats, int threshold) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("RÉSULTATS DE L'ANALYSE\n");
        sb.append("═══════════════════════════════════════════════════════════════\n\n");
        
        appendGlobalMetrics(sb, stats);
        appendAverages(sb, stats);
        appendTopMethodClasses(sb, stats);
        appendTopAttributeClasses(sb, stats);
        appendBothCategories(sb, stats);
        appendThresholdClasses(sb, stats, threshold);
        appendTopMethods(sb, stats);
        appendMaxParameters(sb, stats);
        
        return sb.toString();
    }
    
    private static void appendGlobalMetrics(StringBuilder sb, Map<String, Object> stats) {
        sb.append("MÉTRIQUES GLOBALES\n\n");
        sb.append(String.format("  %-45s %s\n", "Classes", stats.get("totalClasses")));
        sb.append(String.format("  %-45s %s\n", "Lignes de code", stats.get("totalLines")));
        sb.append(String.format("  %-45s %s\n", "Méthodes", stats.get("totalMethods")));
        sb.append(String.format("  %-45s %s\n", "Packages", stats.get("totalPackages")));
        sb.append("\n");
    }
    
    private static void appendAverages(StringBuilder sb, Map<String, Object> stats) {
        sb.append("MOYENNES\n\n");
        sb.append(String.format("  %-45s %.2f\n", "Méthodes/classe", stats.get("avgMethodsPerClass")));
        sb.append(String.format("  %-45s %.2f\n", "Lignes/méthode", stats.get("avgLinesPerMethod")));
        sb.append(String.format("  %-45s %.2f\n", "Attributs/classe", stats.get("avgAttributesPerClass")));
        sb.append("\n");
    }
    
    private static void appendTopMethodClasses(StringBuilder sb, Map<String, Object> stats) {
        sb.append("TOP 10% - CLASSES AVEC PLUS DE MÉTHODES\n\n");
        @SuppressWarnings("unchecked")
        List<ClassInfo> topMethods = (List<ClassInfo>) stats.get("topMethodClasses");
        if (topMethods.isEmpty()) {
            sb.append("  Aucune classe\n");
        } else {
            for (ClassInfo c : topMethods) {
                sb.append(String.format("  %-45s %d méthodes\n", c.name, c.getMethodCount()));
            }
        }
        sb.append("\n");
    }
    
    private static void appendTopAttributeClasses(StringBuilder sb, Map<String, Object> stats) {
        sb.append("TOP 10% - CLASSES AVEC PLUS D'ATTRIBUTS\n\n");
        @SuppressWarnings("unchecked")
        List<ClassInfo> topAttrs = (List<ClassInfo>) stats.get("topAttributeClasses");
        if (topAttrs.isEmpty()) {
            sb.append("  Aucune classe\n");
        } else {
            for (ClassInfo c : topAttrs) {
                sb.append(String.format("  %-45s %d attributs\n", c.name, c.getAttributeCount()));
            }
        }
        sb.append("\n");
    }
    
    private static void appendBothCategories(StringBuilder sb, Map<String, Object> stats) {
        sb.append("CLASSES DANS LES DEUX CATÉGORIES\n\n");
        @SuppressWarnings("unchecked")
        List<ClassInfo> both = (List<ClassInfo>) stats.get("inBothCategories");
        if (both.isEmpty()) {
            sb.append("  Aucune classe\n");
        } else {
            for (ClassInfo c : both) {
                sb.append("  ").append(c.name).append("\n");
            }
        }
        sb.append("\n");
    }
    
    private static void appendThresholdClasses(StringBuilder sb, Map<String, Object> stats, int threshold) {
        sb.append(String.format("CLASSES AVEC PLUS DE %d MÉTHODES\n\n", threshold));
        @SuppressWarnings("unchecked")
        List<ClassInfo> above = (List<ClassInfo>) stats.get("classesAboveThreshold");
        if (above.isEmpty()) {
            sb.append("  Aucune classe\n");
        } else {
            for (ClassInfo c : above) {
                sb.append(String.format("  %-45s %d méthodes\n", c.name, c.getMethodCount()));
            }
        }
        sb.append("\n");
    }
    
    private static void appendTopMethods(StringBuilder sb, Map<String, Object> stats) {
        sb.append("TOP 10% - MÉTHODES AVEC PLUS DE LIGNES\n\n");
        @SuppressWarnings("unchecked")
        Map<String, List<String>> topMethodsPerClass = (Map<String, List<String>>) stats.get("topMethodsPerClass");
        if (topMethodsPerClass.isEmpty()) {
            sb.append("  Aucune méthode\n");
        } else {
            for (Map.Entry<String, List<String>> entry : topMethodsPerClass.entrySet()) {
                sb.append("  Classe ").append(entry.getKey()).append("\n");
                for (String m : entry.getValue()) {
                    sb.append("    • ").append(m).append("\n");
                }
            }
        }
        sb.append("\n");
    }
    
    private static void appendMaxParameters(StringBuilder sb, Map<String, Object> stats) {
        sb.append("PARAMÈTRES MAXIMUM\n\n");
        sb.append(String.format("  %-45s %s\n", "Maximum", stats.get("maxParameters")));
        sb.append("\n  Méthodes :\n");
        @SuppressWarnings("unchecked")
        List<String> maxParams = (List<String>) stats.get("maxParameterMethods");
        for (String m : maxParams) {
            sb.append("    • ").append(m).append("\n");
        }
    }
    
    public static String getWelcomeMessage() {
        return "ANALYSEUR STATIQUE DE CODE JAVA\n" +
               "═══════════════════════════════════════════════════════════════\n\n" +
               "INSTRUCTIONS\n\n" +
               "  1. Sélectionnez un répertoire de code Java\n" +
               "  2. Définissez le seuil de méthodes (Question 11)\n" +
               "  3. Cliquez sur 'Analyser le Code'\n\n" +
               "Les 13 métriques apparaîtront ici.\n";
    }
}
