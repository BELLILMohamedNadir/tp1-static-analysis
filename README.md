# 📊 TP1 - Static Analysis & Code Restructuring

> Advanced Java static analysis tools with modern JavaFX interfaces

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-17-blue.svg)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Two powerful static analysis tools for Java code analysis and visualization, developed for **HAI913I - Software Comprehension and Restructuring** course.

---

## 🚀 Quick Start

```
# Clone & Build
git clone https://github.com/BELLILMohamedNadir/tp1-static-analysis.git
cd tp1-static-analysis

# Exercise 1: Metrics Analyzer
cd exo1 && mvn clean install && mvn javafx:run

# Exercise 2: Call Graph Builder
cd exo2 && mvn clean install && mvn javafx:run
```

---

## 📝 Exercise 1: Static Code Analyzer

**Computes 13 software metrics from Java source code**

### Features
- 📊 Total classes, methods, lines, attributes
- 📈 Averages: methods/class, lines/method, attributes/class
- 🏆 Top 10% complex classes
- 📦 Package distribution analysis

### Usage
```
cd exo1
mvn javafx:run
# Browse → Select folder → Analyze
```

---

## 🔗 Exercise 2: Call Graph Builder

**Builds and visualizes method call graphs**

### Features
- 🌐 Complete call graph construction
- 📊 Entry points & leaf methods detection
- 🖼️ Graphviz visualization (PNG export)
- 📄 DOT format export

### Usage
```
cd exo2
mvn javafx:run
# Browse → Analyze & Visualize → View graph
```

### Manual Graph Generation
```
mvn exec:java -Dexec.mainClass="com.tp.callgraph.CallGraphDemo"
dot -Tpng callgraph.dot -o callgraph.png
xdg-open callgraph.png  # View image
```

---

## 🛠️ Installation

### Prerequisites
```
java -version    # Java 11+
mvn -version     # Maven 3.6+
dot -V           # Graphviz (for Exercise 2)
```

### Install Graphviz
```
# Ubuntu/Debian
sudo apt install graphviz

# macOS
brew install graphviz

# Windows
choco install graphviz
```

---

## 🧪 Testing

```
# All tests
./test-all.sh

# Individual exercises
cd exo1 && mvn test
cd exo2 && mvn test
```

**Expected Output:**
```
✅ Exercice 1: PASSED (3 tests)
✅ Exercice 2: PASSED (5 tests)
🎉 ALL TESTS PASSED!
```

---

## 🏗️ Project Structure

```
tp1/
├── exo1/                    # Exercise 1: Metrics Analyzer
│   ├── src/main/            # Analysis engine + JavaFX GUI
│   ├── src/test/            # JUnit 5 tests
│   └── pom.xml
├── exo2/                    # Exercise 2: Call Graph Builder
│   ├── src/main/            # Graph builder + GUI with visualization
│   ├── src/test/            # JUnit 5 tests
│   ├── callgraph.dot        # Generated graph (DOT format)
│   ├── callgraph.png        # Rendered graph (PNG)
│   └── pom.xml
├── README.md                # This file
└── test-all.sh              # Automated test script
```

---

## 🛠️ Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 11+ | Core language |
| JavaFX | 17.0.2 | GUI framework |
| Eclipse JDT | 3.32.0 | AST parsing |
| JUnit 5 | 5.10.0 | Testing |
| Graphviz | 2.x | Graph visualization |
| Maven | 3.6+ | Build automation |

---

## 📚 Documentation

### Exercise 1: Metrics Explained

- **Lines of Code (LOC)**: Total non-empty lines
- **Methods/Class Avg**: Complexity indicator (lower is better)
- **Top 10% Classes**: High-complexity hotspots requiring attention

### Exercise 2: Graph Statistics

```
=== CALL GRAPH ===

Total Methods  : 19    ← Nodes in graph
Total Calls    : 13    ← Edges (invocations)

Entry Points   : 18    ← Never called (external API/unused)
Leaf Methods   : 6     ← Don't call others (terminal ops)
```

**Interpretation:**
- **Entry Points** → Potential API methods or dead code
- **Leaf Methods** → Terminal operations (I/O, calculations)
- **Low Call/Method Ratio** → Good design with low coupling ✅

---

## 👨‍💻 Author

**Mohamed Nadir BELLIL**  
🎓 Master 2 Software Engineering  
🏛️ University of Montpellier

📧 [mohamed-nadir.bellil@etu.umontpellier.fr](mailto:mohamed-nadir.bellil@etu.umontpellier.fr)  
🔗 [GitHub Profile](https://github.com/BELLILMohamedNadir)

---

## 🤝 Contributing

Contributions welcome! Fork the repo, create a feature branch, and open a PR.

---

**⭐ Star this repo if you find it helpful!**

<p align="center">Made for HAI913I - Software Engineering</p>
