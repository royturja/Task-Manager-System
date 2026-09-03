package com.mycompany.task_manager_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskManager extends JFrame implements ActionListener {

    JPanel panel;

    JLabel taskLabel;
    JTextField taskField;

    JLabel deadlineLabel;
    JTextField deadlineField;

    JButton addButton;
    JButton completeButton;//Complete button declare kora hocce
    JButton deleteButton;//delete button declare kora hocce

    JTable table;//table jeta screen e dekhi
    JScrollPane scrollPane;
    DefaultTableModel model;
    //jtable r data rakhte default..model-temporary data storage

    public TaskManager() {

        setTitle("Task Manager System");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);

        taskLabel = new JLabel("Task:");
        taskLabel.setBounds(20, 20, 50, 30);
         //JTextField-Swing r text box(jkhne user lekhe) bananor class
         //new -new object banao,kon typer?JTextField() → new TextField banao
         //new JTextField()-new text field(box)/object toiri korse..
         //..eta k taskField variable e rakhce-panel diye gui te dekhabe
         //proposal text-tokn taskfield r vitor thake 
         //add click korle...recieve then..model e jtable r data rakhe-temporary storage..
        taskField = new JTextField();
        taskField.setBounds(80, 20, 200, 30);
        //Text Field r position o size-(x, y, width, height)

        deadlineLabel = new JLabel("Deadline:");
        deadlineLabel.setBounds(20, 60, 70, 30);

        deadlineField = new JTextField();
        deadlineField.setBounds(80, 60, 200, 30);

        addButton = new JButton("Add Task");
        addButton.setBounds(350, 20, 120, 30);
        addButton.addActionListener(this);
         
        completeButton = new JButton("Complete");
        completeButton.setBounds(350, 60, 120, 30);
        completeButton.addActionListener(this);
//addActionListener()→ Method-Complete buttonr sathe ActionListener-Interface k connect kore,action listen kore
// button e click korle actionPerformed() method call hobe
// "this"- TaskManager object-present classr object-sei object k listener deya hocce
         //
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(490, 20, 100, 30);
        deleteButton.addActionListener(this);
    //DefaultTableModel-JTabler data/model rakhte use hoy/thake..
    //..tabler data,row,column manage kore
    //jtable r data model toiri korse
    //model-variable,defa..model k model var r vitor rakhse
    //new DefaultTableModel-new def..model object toiri hocce
    //model r data i table screen e dekhay
        model = new DefaultTableModel(
                new Object[]{"Task Name", "Deadline", "Status"}, 0
        )//0-suru te kono data row thakbe na
      //3 ta column ase
//object array toiri hocce-3 ta value ase-task name,..                
      //Object[]-table r data different type hote pare
        // JTable r cell editable checking
// DefaultTableModel r isCellEditable() method override kora hocce
//- table r any cell directly user edit korte parbe na
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                
        //false return-table r row,column r cell editable na
            }
        };

        table = new JTable(model);
      // Column width
        // 1st column r  preferred width 220 pixel

        table.getColumnModel().getColumn(0).setPreferredWidth(220);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
       // Row height
        //every row r height 30 pixel

        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 110, 590, 300);

        panel.add(taskLabel);
        panel.add(taskField);
        //text/task field ta panel e dekhabe

        panel.add(deadlineLabel);
        panel.add(deadlineField);

        panel.add(addButton);
        panel.add(completeButton);
        //panel e add kora
        //panel r madhome button gui window te display hobe
        //panel-container-gui component rakha jay
        panel.add(deleteButton);

        panel.add(scrollPane);

        add(panel);
//panel k jframe(application r main window)r vitor rakho
//panel jframe e na thakle window te egula dekha jabe na

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//ei method-button click hole ki kaj hobe seta bole
//actionPerformed()-button click action hole-method r vitor r code execute hobe
//(ActionEvent e)-method r parameter
//ActionEvent-ki event hocce seta bujhar class/type
//e-sei event r variable/reference-kon event hocce jana jay
        if (e.getSource() == addButton) {
            addTask();
        }
          // event r source Complete button hole
//completeTask() method call hobe
//e.getSource()-kon button click hoise..
        if (e.getSource() == completeButton) {
//selected task k completed korbe
            completeTask();
        }

        if (e.getSource() == deleteButton) {
            deleteTask();//
        }
    }

    private void addTask() {
              // TextField theke task neya..
        
        // taskField e user ja likhse segula
        // getText() diye segula nissi
        
        // trim() diye unnecessary space baad jay
        String task = taskField.getText().trim();
        String deadlineText = deadlineField.getText().trim();

        if (task.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a task.");
            return;
        }

        if (deadlineText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a deadline date.");
            return;
        }

        try {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate deadline =
                    LocalDate.parse(deadlineText, formatter);

            if (deadline.isBefore(LocalDate.now())) {

                JOptionPane.showMessageDialog(this,
                        "Deadline cannot be a past date.");

                return;
            }
                     // Task + Deadline + Status- table a add hobe
            //model.addRow() use kore jtable r modhe new row add hocce
            
            //object r 3 ta value-3 ta column r modhe jabe
//table e new task k row hiseb e add kora hocce
            model.addRow(new Object[]{
                    task,
                    deadline,
                    "Pending"
            });

            taskField.setText("");
            deadlineField.setText("");
//Deadline lekhar TextField
//.setText()-textfield r lekha set/change korar method
//""-khali string-deadline field khali koro
//task r copy model r vitor ase tai table r task delete hoy na
            JOptionPane.showMessageDialog(this,
                    "Task added successfully.");

        } catch (DateTimeParseException ex) {

            JOptionPane.showMessageDialog(this,
                    "Invalid date! Please use YYYY-MM-DD format.");
        }
    }

    private void completeTask() { 
//completeTask() method toiri hocce
//method r kaj selected task k Completed kora
        int selectedRow = table.getSelectedRow();
  // JTable r kon row selected seta ber kora
    // selected row r  index selectedRow variable e rakha
        if (selectedRow == -1) {
        //jodi row select na kore
    // getSelectedRow() -1 return korbe
            JOptionPane.showMessageDialog(this,
                    "Please select a task first.");
            //task select na korle method off hoye jabe 
            return;
        }
//selected row r Status k status column e neya hocce
    // column index 2 karon 
    // 0 = Task Name
    // 2 = Status
        String status = model.getValueAt(selectedRow, 2).toString();
     //jodi already completed hoy taile..
        if (status.equals("Completed")) {

            JOptionPane.showMessageDialog(this,
                    "This task is already completed.");
              //abar completed r no need, tai method sesh
            return;
        }
//status pending theke completed-update kora hocce
        model.setValueAt("Completed", selectedRow, 2);

        JOptionPane.showMessageDialog(this,
                "Task marked as completed.");
    }
//
    private void deleteTask() {
       //method..
        int selectedRow = table.getSelectedRow();
     //jodi select na thake
        if (selectedRow == -1) {
       
            JOptionPane.showMessageDialog(this,
                    "Please select a task to delete.");
     //task select na thakle method off hoye jabe
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this task?",
                "Delete Task",//dialog box r title
                JOptionPane.YES_NO_OPTION//yes no 2 ta option ase
        );
      //jodi yes chap dey
        if (choice == JOptionPane.YES_OPTION) {
//deleting
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this,
                    "Task deleted successfully.");
        }
    }

    public static void main(String[] args) {
    new TaskManager();
}
}