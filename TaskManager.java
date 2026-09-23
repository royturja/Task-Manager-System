package com.mycompany.task_manager_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskManager extends JFrame implements ActionListener {

    JPanel panel;
    JPanel headerPanel;

    JLabel headerTitle;
    JLabel headerSubtitle;

    JLabel taskLabel;
    JTextField taskField;

    JLabel deadlineLabel;
    JTextField deadlineField;

    JButton addButton;
    JButton completeButton;
    JButton deleteButton;
    JButton reminderButton;

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;

    JLabel totalLabel;
    JLabel pendingLabel;
    JLabel completedLabel;

    ArrayList<String[]> tasks = new ArrayList<>();

    String fileName = "tasks.txt";

    public TaskManager() {

        setTitle("Task Manager System");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));
           
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 800, 100);
        headerPanel.setBackground(new Color(220, 230, 240));
         
        headerTitle = new JLabel("TASK MANAGER SYSTEM");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 25));
        headerTitle.setHorizontalAlignment(SwingConstants.CENTER);
        headerTitle.setBounds(0, 15, 800, 35);
        
        headerSubtitle = new JLabel("Manage your daily tasks easily");
        headerSubtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        headerSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
        headerSubtitle.setBounds(0, 55, 800, 25);
         
        headerPanel.add(headerTitle);
        headerPanel.add(headerSubtitle);

        taskLabel = new JLabel("Task:");
        taskLabel.setFont(new Font("Arial", Font.BOLD, 14));
        taskLabel.setBounds(25, 115, 50, 30);

        taskField = new JTextField();
        taskField.setBounds(75, 115, 220, 30);

        deadlineLabel = new JLabel("Deadline:");
        deadlineLabel.setFont(new Font("Arial", Font.BOLD, 14));
        deadlineLabel.setBounds(25, 155, 70, 30);

        deadlineField = new JTextField();
        deadlineField.setBounds(95, 155, 200, 30);

        addButton = new JButton("Add Task");
        addButton.setBounds(320, 115, 110, 30);
        addButton.addActionListener(this);

        completeButton = new JButton("Complete");
        completeButton.setBounds(440, 115, 110, 30);
        completeButton.addActionListener(this);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(560, 115, 100, 30);
        deleteButton.addActionListener(this);
      
        reminderButton = new JButton("Reminder");
        reminderButton.setBounds(320, 155, 110, 30);
        reminderButton.addActionListener(this);

        model = new DefaultTableModel(
                new Object[]{"Task Name", "Deadline", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        table.setRowHeight(32);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(0, 32));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
             
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
              
                Component component = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                );
                
                if (isSelected) {
                    component.setBackground(new Color(180, 210, 240)); 
                } else {

                    String status = table.getValueAt(row, 2).toString();
                    String deadlineText = table.getValueAt(row, 1).toString();
                                       if (status.equals("Completed")) {

                        component.setBackground(new Color(210, 240, 215));

                    } else {

                        try { 

                            LocalDate deadline = LocalDate.parse(deadlineText);
                     
                            if (deadline.isBefore(LocalDate.now())) {
                               
                                component.setBackground(new Color(250, 215, 215));
                            } else {
                              
                                component.setBackground(Color.WHITE);
                            }

                        } catch (Exception ex) { 
                            
                            component.setBackground(Color.WHITE);
                        }
                    }
                }
            
                return component;
            }
        });

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 205, 735, 315);
          
        totalLabel = new JLabel("Total Tasks: 0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setBounds(25, 535, 180, 30);

        pendingLabel = new JLabel("Pending: 0");
        pendingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pendingLabel.setBounds(220, 535, 150, 30);         
        completedLabel = new JLabel("Completed: 0");
        completedLabel.setFont(new Font("Arial", Font.BOLD, 14));
      
        completedLabel.setBounds(390, 535, 170, 30);

        panel.add(headerPanel);

        panel.add(taskLabel);
        panel.add(taskField);

        panel.add(deadlineLabel);
        panel.add(deadlineField);

        panel.add(addButton);
        panel.add(completeButton);
        panel.add(deleteButton);
        panel.add(reminderButton);

        panel.add(scrollPane);

        panel.add(totalLabel);
        panel.add(pendingLabel);
        panel.add(completedLabel);

        add(panel);

        loadTasks();

        setVisible(true);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                saveTasks();
                System.exit(0);
            }
        });
           
        
        
       
        checkDeadlineReminders();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            addTask();
        }

        if (e.getSource() == completeButton) {
            completeTask();
        }

        if (e.getSource() == deleteButton) {
            deleteTask();
        }
      
        if (e.getSource() == reminderButton) {
            checkDeadlineReminders();
        }
    }

    private void addTask() {

        String task = taskField.getText().trim();
        String deadlineText = deadlineField.getText().trim();
        
        if (task.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a task."
            );

            taskField.requestFocus();
            return;
        }

        if (deadlineText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a deadline date."
            );

            deadlineField.requestFocus();
            return;
        }

        try {
          
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate deadline =
                    LocalDate.parse(deadlineText, formatter);
             
            if (deadline.isBefore(LocalDate.now())) {

                JOptionPane.showMessageDialog(
                        this,
                        "Deadline cannot be a past date."
                );

                deadlineField.requestFocus();
                return;
            }

            String[] taskData = {
                    task,
                    deadline.toString(),
                    "Pending"
            };

            tasks.add(taskData);

            refreshTable();

            saveTasks();

            taskField.setText("");
            deadlineField.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Task added successfully."
            );

        } catch (DateTimeParseException ex) {
           
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date! Please use YYYY-MM-DD format."
            );

            deadlineField.requestFocus();
        }
    }

    private void refreshTable() {

        model.setRowCount(0);

        for (String[] task : tasks) {

            model.addRow(new Object[]{
                    task[0],
                    task[1],
                    task[2]
            });
        }
        
        updateTaskCounter();
       
        table.repaint();
    }

    private void updateTaskCounter() {

        int total = tasks.size();
        int pending = 0;
        int completed = 0;

        for (String[] task : tasks) {

            if (task[2].equals("Completed")) {
                completed++;
            } else {
                pending++;
            }
        }

        totalLabel.setText("Total Tasks: " + total);
        pendingLabel.setText("Pending: " + pending);
        completedLabel.setText("Completed: " + completed);
    }

    private void completeTask() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a task first."
            );

            return;
        }

        if (tasks.get(selectedRow)[2].equals("Completed")) {

            JOptionPane.showMessageDialog(
                    this,
                    "This task is already completed."
            );

            return;
        }

        tasks.get(selectedRow)[2] = "Completed";

        refreshTable();

        saveTasks();

        JOptionPane.showMessageDialog(
                this,
                "Task marked as completed."
        );
    }

    private void deleteTask() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a task to delete."
            );

            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this task?",
                "Delete Task",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            tasks.remove(selectedRow);

            refreshTable();

            saveTasks();

            JOptionPane.showMessageDialog(
                    this,
                    "Task deleted successfully."
            );
        }
    }
     
    
    private void checkDeadlineReminders() {
       
        LocalDate today = LocalDate.now();
              LocalDate tomorrow = today.plusDays(1);

        String overdueTasks = "";
        String todayTasks = "";
        String tomorrowTasks = "";
         
        for (String[] task : tasks) {

            try {
                
                LocalDate deadline =
                        LocalDate.parse(task[1]);
             
                if (task[2].equals("Completed")) {
                    continue;
                }
            
                if (deadline.isBefore(today)) {

                    overdueTasks +=
                            "- " + task[0] +
                            " (" + task[1] + ")\n";
                }

                else if (deadline.equals(today)) {

                    todayTasks +=
                            "- " + task[0] +
                            " (" + task[1] + ")\n";
                }

                else if (deadline.equals(tomorrow)) {

                    tomorrowTasks +=
                            "- " + task[0] +
                            " (" + task[1] + ")\n";
                }

            } catch (DateTimeParseException ex) {
            } 
        }
          

        String reminderMessage = "";
          
        if (!overdueTasks.isEmpty()) {

            reminderMessage +=
                    "OVERDUE TASKS\n" +
                    "-------------------------\n" +
                    overdueTasks +
                    "\n";
        }
       
        if (!todayTasks.isEmpty()) {

            reminderMessage +=
                    "TASKS DUE TODAY\n" +
                    "-------------------------\n" +
                    todayTasks +
                    "\n";
        }
          
        if (!tomorrowTasks.isEmpty()) {

            reminderMessage +=
                    "TASKS DUE TOMORROW\n" +
                    "-------------------------\n" +
                    tomorrowTasks +
                    "\n";
        }
            
        if (reminderMessage.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No overdue or upcoming tasks.\n\n" +
                    "No tasks are due today or tomorrow.",
                    "Reminder",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {
          
            JOptionPane.showMessageDialog(
                    this,
                    reminderMessage,
                    "Task Reminder",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void saveTasks() {

        try {

            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String[] task : tasks) {

                bw.write(
                        task[0] + "," +
                        task[1] + "," +
                        task[2]
                );

                bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error while saving tasks."
            );
        }
    }

    private void loadTasks() {

        try {

            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            tasks.clear();

            String line;

            while ((line = br.readLine()) != null) {

                String[] taskData = line.split(",");

                if (taskData.length == 3) {
                    tasks.add(taskData);
                }
            }

            br.close();
            fr.close();

            refreshTable();

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error while loading tasks."
            );
        }
    }

    public static void main(String[] args) {

        new TaskManager();
    }
}