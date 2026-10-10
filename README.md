# Task-Manager-System
Task Manager System is a Java GUI-based application for managing daily tasks efficiently. Users can add tasks with deadlines, view them in a real-time table, mark tasks as completed, delete tasks and receive reminders. It supports status tracking (Pending/Completed) and stores task data in a text file.

Project Overview

The Task Manager System is a desktop-based graphical user interface application developed using NetBeans IDE and Java Swing. The main purpose of this project is to help users manage their daily tasks and deadlines in a simple and organized way. The project was developed over a 10-week academic timeline. The application uses basic Object-Oriented Programming concepts, task management with JTable and ArrayList, file handling for permanent data storage, deadline validation, and a reminder system to help users monitor their tasks efficiently.

Features

Graphical User Interface: 
A simple and user-friendly interface built with Java Swing.

Task Management: 
Add new tasks, mark them as completed, and delete selected tasks.

Input Validation: 
Checks empty fields, validates the date format (yyyy-MM-dd), and prevents past deadlines.

Task Status: 
Displays tasks in a JTable with Pending or Completed status.

File Handling: 
Automatically saves tasks to tasks.txt and loads them when the application starts.

Task Statistics:
 Shows the total number of tasks, pending tasks, and completed tasks.

Visual Indicators: 
Uses different background colors for completed and overdue tasks.

Deadline Reminders: 
Notifies users about overdue tasks and tasks due today or tomorrow, with an option to check reminders at any time.

Tools and Technologies

The following tools and technologies are used to develop the Task Manager System:

Programming Language: Java

Development Environment: NetBeans IDE

GUI Technology: Java Swing

Main GUI Components: JFrame, JPanel, JLabel, JTextField, JButton, JTable and JScrollPane

Table Management: DefaultTableModel

Data Structure: ArrayList

Date Handling: LocalDate and DateTimeFormatter

File Handling: FileWriter, BufferedWriter, FileReader and BufferedReader

Notification: JOptionPane

Data Storage: Text file (tasks.txt)

Programming Concept: Event-driven programming and object-oriented programming

User Manual

Follow the instructions below to download, run and customize the Task Manager System on your computer.
Before running the project, make sure you have the following installed:
Java JDK: Required to compile and run the application.
Apache NetBeans IDE: Recommended for opening and running the project.
Git: Required only if you want to clone or contribute to the repository.

Download the Project

1.	Open this GitHub repository- 
https://github.com/royturja/Task-Manager-System
2.	Click the Code button.
3.	Select Download ZIP.
4.	Extract the downloaded ZIP file to your preferred location.

Clone the Repository

Open a terminal or command prompt and run:
git clone https://github.com/royturja/Task-Manager-System.git
Move into the project directory:
cd Task-Manager-System

Open the Project in NetBeans

1.	Open Apache NetBeans IDE.
2.	Browse to the downloaded or cloned project folder.
3.	Select the project and click Open Project.
4.	Wait for NetBeans to load the project and resolve its dependencies, if required.

Run the Application

1.	Locate the TaskManager.java file inside the package com.mycompany.task_manager_system.
2.	Right-click the project and select Run, or open TaskManager.java and run the application.
3.	The Task Manager System window will appear.
You can now add tasks, set deadlines, mark tasks as completed, delete tasks, and check deadline reminders.
You can modify the existing source code to suit your needs or develop additional features. You can edit TaskManager.java in NetBeans, make your changes, and run the application to test them.

Fork the Repository

If you want to keep your own copy of this project on GitHub and develop it independently:
1.	Visit this repository on GitHub.
2.	Click the Fork button in the upper-right corner.
3.	Select your GitHub account as the destination.
4.	GitHub will create a copy of the repository under your account.
5.	Clone your fork to your computer:
git clone https://github.com/YOUR-USERNAME/Task-Manager-System.git

Replace YOUR-USERNAME with your GitHub username.
You can now modify the project, add new features, and push your changes to your own repository.
If you would like to contribute:
1.	Fork the repository.
2.	Create a new branch for your changes.
3.	Make your modifications and test them.
4.	Commit and push your changes to your fork.
5.	Open a pull request if you would like to propose your changes to the original repository.
Example Git Commands
After cloning your fork, navigate to the project directory and run:
git checkout -b feature/your-feature
git add .
git commit -m "Add your feature"
git push origin feature/your-feature


How to Use

1.	Run the Application: Open and run the Task Manager System from NetBeans.
2.	Add a Task: Enter the task name and deadline in YYYY-MM-DD format, then click Add Task. Empty fields, invalid dates, and past deadlines are rejected.
3.	Complete a Task: Select a task from the table and click Complete to change its status to Completed.
4.	Delete a Task: Select a task, click Delete, and confirm the deletion.
5.	Check Reminders: Click Reminder to view overdue tasks, tasks due today, and tasks due tomorrow.
6.	Automatic Data Storage: Tasks are automatically saved to tasks.txt when added, completed, or deleted. Previously saved tasks are loaded when the application starts again.
7.	View Task Status: Different row background colors help identify completed, overdue, and normal pending tasks.
8.	Monitor Task Counters: Check the counters below the table to view the total number of tasks, pending tasks, and completed tasks.

Conclusion

The Java Task Manager System project successfully demonstrates how fundamental Java concepts can be combined to develop a practical graphical user interface application. The project was developed incrementally from GUI design and task table implementation to validation, file storage, reminder functionality, and final testing and debugging. As a result, the application provides a simple and user-friendly way to add, complete, delete, store, and monitor daily tasks and deadlines. 
