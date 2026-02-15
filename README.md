# Gantt Manager

## Description
**Gantt Manager** is a Java desktop application for managing and visualizing project tasks using a Gantt-style workflow.  
The system allows users to load task data, filter and analyze tasks, and generate reports in multiple formats.  
It was developed as an academic software engineering project with emphasis on **object-oriented design, MVC architecture, and UML modeling**.

---

## Key Features
- Task creation and management
- Filtering tasks by prefix, ID, or hierarchy
- Multiple report formats (Text, Markdown, HTML)
- Table and raster visualization of task data
- Modular backend logic with controller abstraction
- Desktop GUI built with **Java Swing**

---

## Architecture
The project follows a **layered / MVC-inspired architecture**:

### GUI Layer
- `JFrameLevel00RootFrame`
- `JTableViewer`
- Desktop interaction and visualization

### Application Layer
- `AppController`
- Bridges GUI and backend logic

### Backend Layer
- `IMainController` interface
- `MainController` implementation
- `MainControllerFactory`
- `ReportType` enumeration

### Domain Layer
- `Task` entity and data models

The design is supported by **UML Class Diagrams and Package Diagrams** demonstrating separation of concerns and modularity.

---

## Technologies
- **Java**
- Java Swing (GUI)
- Object-Oriented Programming
- UML Modeling
- MVC Design Principles

---

## How to Run

### Requirements
- Java JDK 11 or higher

### Steps
1. Compile the project:
```bash
javac -d bin src/**/*.java
```

2. Run the application:
```bash
java -cp bin app.gui.AppStarter
```

*(Adjust main class if needed depending on your IDE setup.)*

---

## Project Structure
```
gantt-manager
├── app.gui
├── app
├── backend
├── parser
├── dom
├── reporter
└── diagrams
```

---

## Educational Purpose
This project demonstrates:
- MVC Architecture
- Interface-based design
- Factory Pattern
- Report generation strategies
- UML documentation
- GUI and backend separation
