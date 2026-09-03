package com.mycompany.task_manager_system;

import javax.swing.*;

import java.awt.event.*;

public class TaskManager extends JFrame implements ActionListener {

    JPanel panel;

    JLabel taskLabel;
    JTextField taskField;
    JButton addButton;

    JTable table;
    
    

    public TaskManager() {

        setTitle("Task Manager System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

         
        panel = new JPanel();
        panel.setLayout(null);

       
        taskLabel = new JLabel("Task:");
        taskLabel.setBounds(20, 20, 50, 30);

        
        taskField = new JTextField();
        taskField.setBounds(80, 20, 250, 30);

        
        addButton = new JButton("Add Task");
        addButton.setBounds(350, 20, 120, 30);
        addButton.addActionListener(this);

        
       

        

        
        panel.add(taskLabel);
        panel.add(taskField);
        panel.add(addButton);
        

        
        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String task = taskField.getText();

        if (!task.isEmpty()) {
            
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please enter a task.");
        }
    }

    public static void main(String[] args) {
        new TaskManager();
    }
}