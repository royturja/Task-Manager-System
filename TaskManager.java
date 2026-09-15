package com.mycompany.task_manager_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskManager extends JFrame implements ActionListener {

    JPanel panel;

    JLabel taskLabel;
    JTextField taskField;

    JLabel deadlineLabel;
    JTextField deadlineField;

    JButton addButton;
    JButton completeButton;
    JButton deleteButton;

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;

    ArrayList<String[]> tasks = new ArrayList<>();
    String fileName = "tasks.txt";

    public TaskManager() {

        setTitle("Task Manager System");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);


      
        panel = new JPanel();

       
        panel.setLayout(null);


        taskLabel = new JLabel("Task:");
        taskLabel.setBounds(20, 20, 50, 30);


        taskField = new JTextField();
        taskField.setBounds(80, 20, 200, 30);


        deadlineLabel = new JLabel("Deadline:");
        deadlineLabel.setBounds(20, 60, 70, 30);


        deadlineField = new JTextField();
        deadlineField.setBounds(80, 60, 200, 30);


        addButton = new JButton("Add Task");
        addButton.setBounds(320, 20, 110, 30);

        
        addButton.addActionListener(this);
       
        completeButton = new JButton("Complete");
        completeButton.setBounds(440, 20, 110, 30);

        
        completeButton.addActionListener(this);


        deleteButton = new JButton("Delete");
        deleteButton.setBounds(560, 20, 100, 30);

        
        deleteButton.addActionListener(this);


        model = new DefaultTableModel(
                new Object[]{"Task Name", "Deadline", "Status"}, 0
        ) {

           
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        
        table = new JTable(model);


       
        table.getColumnModel().getColumn(0).setPreferredWidth(220);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);


        
        table.setRowHeight(30);

        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 110, 690, 350);


        panel.add(taskLabel);
        panel.add(taskField);

        panel.add(deadlineLabel);
        panel.add(deadlineField);

        panel.add(addButton);
        panel.add(completeButton);
        panel.add(deleteButton);

        panel.add(scrollPane);

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
    }


    
    
    
   

    private void addTask() {

       
        String task = taskField.getText().trim();


        
        String deadlineText = deadlineField.getText().trim();


        
        if (task.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a task."
            );

            return;
        }


        
        if (deadlineText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a deadline date."
            );

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