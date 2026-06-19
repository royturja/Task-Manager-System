package com.mycompany.task_manager_system;

import javax.swing.*;
import java.awt.event.*;

public class TaskManager extends JFrame implements ActionListener {

    JLabel taskLabel;
    JTextField taskField;
    JButton addButton;
    JTable table;

    public TaskManager() {

        taskLabel = new JLabel("Task:");

        taskField = new JTextField(20);

        addButton = new JButton("Add Task");
        addButton.addActionListener(this);

        table = new JTable();

        setLayout(null);

        taskLabel.setBounds(20, 20, 50, 25);
        taskField.setBounds(80, 20, 200, 25);
        addButton.setBounds(300, 20, 120, 25);

        add(taskLabel);
        add(taskField);
        add(addButton);

        setTitle("Task Manager System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

        
            new TaskManager();
        
    }
}