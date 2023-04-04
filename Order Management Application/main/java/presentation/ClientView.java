package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ClientView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField clientNameTextField ;
    private JTextField clientAddressTextField ;
    private JTextField clientEmailTextField ;
    private JTextField clientAgeTextField ;
    private JButton btnAddClient;
    private JButton btnEditClient;
    private JButton btnViewAllClients;
    private JButton btnDeleteClient;



    public ClientView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Client Table");
        lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(237, 10, 239, 54);
        contentPane.add(lblNewLabel);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        { "Id", "Name", "Address", "Email", "Age"},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                },
                new String[] {
                        "Id", "Name", "Address", "Email", "Age"
                }
        ));
        table.setBounds(24, 74, 400, 291);
        contentPane.add(table);

        btnAddClient = new JButton("Add Client");
        btnAddClient.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAddClient.setBounds(101, 397, 112, 35);
        contentPane.add(btnAddClient);

        btnEditClient = new JButton("Edit Client");
        btnEditClient.setFont(new Font("Calibri", Font.BOLD, 14));
        btnEditClient.setBounds(250, 397, 112, 35);
        contentPane.add(btnEditClient);

        btnViewAllClients = new JButton("View All");
        btnViewAllClients.setFont(new Font("Calibri", Font.BOLD, 14));
        btnViewAllClients.setBounds(550, 397, 112, 35);
        contentPane.add(btnViewAllClients);

        btnDeleteClient = new JButton("Delete Client");
        btnDeleteClient.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDeleteClient.setBounds(401, 397, 112, 35);
        contentPane.add(btnDeleteClient);

        JLabel clientNameLabel = new JLabel("Name:");
        clientNameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        clientNameLabel.setBounds(459, 105, 83, 28);
        contentPane.add(clientNameLabel);

        JLabel clientAddressLabel = new JLabel("Address:");
        clientAddressLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        clientAddressLabel.setBounds(459, 175, 83, 28);
        contentPane.add(clientAddressLabel);

        JLabel clientEmailLabel = new JLabel("Email:");
        clientEmailLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        clientEmailLabel.setBounds(459, 241, 83, 28);
        contentPane.add(clientEmailLabel);

        JLabel clientAgeLabel = new JLabel("Age:");
        clientAgeLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        clientAgeLabel.setBounds(459, 305, 83, 28);
        contentPane.add(clientAgeLabel);

        clientNameTextField = new JTextField();
        clientNameTextField .setBounds(572, 102, 125, 31);
        contentPane.add(clientNameTextField );
        clientNameTextField .setColumns(10);

        clientAddressTextField = new JTextField();
        clientAddressTextField.setBounds(572, 172, 125, 31);
        contentPane.add(clientAddressTextField);
        clientAddressTextField.setColumns(10);

        clientEmailTextField = new JTextField();
        clientEmailTextField.setBounds(572, 238, 125, 31);
        contentPane.add(clientEmailTextField);
        clientEmailTextField.setColumns(10);

        clientAgeTextField = new JTextField();
        clientAgeTextField.setBounds(572, 302, 125, 31);
        contentPane.add(clientAgeTextField);
        clientAgeTextField.setColumns(10);
    }

    public void btnAddClientListener(ActionListener actionListener) {
        this.btnAddClient.addActionListener(actionListener);
    }

    public void btnEditClientListener(ActionListener actionListener){
        this.btnEditClient.addActionListener(actionListener);
    }

    public void btnViewAllClientsListener(ActionListener actionListener){
        this.btnViewAllClients.addActionListener(actionListener);
    }

    public void btnDeleteClientListener(ActionListener actionListener){
        this.btnDeleteClient.addActionListener(actionListener);
    }

    public String getClientAddressInput(){
        return this.clientAddressTextField.getText();
    }

    public String getClientEmailInput(){
        return this.clientEmailTextField.getText();
    }

    public String getClientAgeInput(){
        return this.clientAgeTextField.getText();
    }

    public String getClientNameInput(){
        return this.clientNameTextField.getText();
    }

    public void setClientTableText(Object t, int row, int col) {
        table.getModel().setValueAt(t, row, col);
    }

   public int getIdFromTable(){
        int value;
        int column = 0;
         int row = table.getSelectedRow();
         value = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
         return value;
   }


}

