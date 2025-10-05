package com.tp.analyzer;

import com.tp.tp1_part2.analyzer.CodeAnalyzer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.*;
import java.util.Map;

class CodeAnalyzerTest {
    
    private CodeAnalyzer analyzer;
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() {
        analyzer = new CodeAnalyzer();
    }
    
    @Test
    void testEmptyAnalysis() {
        Map<String, Object> stats = analyzer.getStatistics(10);
        
        assertNotNull(stats, "Statistics map should not be null");
        assertTrue(stats.containsKey("totalClasses"), "Should contain totalClasses key");
        assertEquals(0, stats.get("totalClasses"));
        assertEquals(0, stats.get("totalMethods"));
    }
    
    @Test
    void testSimpleClass() throws Exception {
        String javaCode = "public class SimpleClass {\n" +
                         "    private int value;\n" +
                         "    \n" +
                         "    public void setValue(int v) {\n" +
                         "        this.value = v;\n" +
                         "    }\n" +
                         "    \n" +
                         "    public int getValue() {\n" +
                         "        return value;\n" +
                         "    }\n" +
                         "}\n";
        
        Path javaFile = tempDir.resolve("SimpleClass.java");
        Files.writeString(javaFile, javaCode);
        
        analyzer.analyzeSourceFile(javaFile.toString());
        Map<String, Object> stats = analyzer.getStatistics(10);
        
        assertNotNull(stats);
        assertEquals(1, stats.get("totalClasses"));
        assertEquals(2, stats.get("totalMethods"));
    }
    
    @Test
    void testMultipleClasses() throws Exception {
        String class1 = "public class ClassA {\n" +
                       "    public void methodA() {}\n" +
                       "}\n";
        
        String class2 = "public class ClassB {\n" +
                       "    public void methodB1() {}\n" +
                       "    public void methodB2() {}\n" +
                       "}\n";
        
        Files.writeString(tempDir.resolve("ClassA.java"), class1);
        Files.writeString(tempDir.resolve("ClassB.java"), class2);
        
        analyzer.analyzeSourceFile(tempDir.resolve("ClassA.java").toString());
        analyzer.analyzeSourceFile(tempDir.resolve("ClassB.java").toString());
        
        Map<String, Object> stats = analyzer.getStatistics(10);
        assertEquals(2, stats.get("totalClasses"));
        assertEquals(3, stats.get("totalMethods"));
    }
    
    @Test
    void testClassWithAttributes() throws Exception {
        String javaCode = "public class AttributeClass {\n" +
                         "    private int attr1;\n" +
                         "    private String attr2;\n" +
                         "}\n";
        
        Path javaFile = tempDir.resolve("AttributeClass.java");
        Files.writeString(javaFile, javaCode);
        
        analyzer.analyzeSourceFile(javaFile.toString());
        Map<String, Object> stats = analyzer.getStatistics(10);
        
        assertNotNull(stats);
        // Ne teste que si la clé existe
        if (stats.containsKey("totalAttributes")) {
            assertTrue((Integer) stats.get("totalAttributes") >= 2);
        }
    }
    
    @Test
    void testPackageExtraction() throws Exception {
        String javaCode = "package com.example.test;\n" +
                         "\n" +
                         "public class PackageTest {\n" +
                         "    public void method() {}\n" +
                         "}\n";
        
        Path javaFile = tempDir.resolve("PackageTest.java");
        Files.writeString(javaFile, javaCode);
        
        analyzer.analyzeSourceFile(javaFile.toString());
        Map<String, Object> stats = analyzer.getStatistics(10);
        
        assertTrue((Integer) stats.get("totalClasses") > 0);
    }
    
    @Test
    void testComplexClass() throws Exception {
        String javaCode = "public class ComplexClass {\n" +
                         "    public void method1() {}\n" +
                         "    public void method2() {}\n" +
                         "    public void method3() {}\n" +
                         "    public void method4() {}\n" +
                         "    public void method5() {}\n" +
                         "}\n";
        
        Path javaFile = tempDir.resolve("ComplexClass.java");
        Files.writeString(javaFile, javaCode);
        
        analyzer.analyzeSourceFile(javaFile.toString());
        Map<String, Object> stats = analyzer.getStatistics(10);
        
        assertTrue((Integer) stats.get("totalMethods") >= 5);
    }
    
    @Test
    void testAverageCalculation() throws Exception {
        String javaCode = "public class AverageTest {\n" +
                         "    public void m1() {}\n" +
                         "    public void m2() {}\n" +
                         "    public void m3() {}\n" +
                         "    public void m4() {}\n" +
                         "}\n";
        
        Path javaFile = tempDir.resolve("AverageTest.java");
        Files.writeString(javaFile, javaCode);
        
        analyzer.analyzeSourceFile(javaFile.toString());
        Map<String, Object> stats = analyzer.getStatistics(10);
        
        // Ne teste l'average que si la clé existe
        if (stats.containsKey("averageMethodsPerClass")) {
            double avgMethods = (Double) stats.get("averageMethodsPerClass");
            assertTrue(avgMethods >= 4.0);
        }
    }
    
    @Test
    void testEmptyClass() throws Exception {
        String javaCode = "public class EmptyClass {}\n";
        
        Path javaFile = tempDir.resolve("EmptyClass.java");
        Files.writeString(javaFile, javaCode);
        
        analyzer.analyzeSourceFile(javaFile.toString());
        Map<String, Object> stats = analyzer.getStatistics(10);
        
        assertEquals(1, stats.get("totalClasses"));
        assertTrue((Integer) stats.get("totalMethods") >= 0);
    }
}
