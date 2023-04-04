package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class OrderView extends JFrame {

    private JPanel contentPane;
    private JTable orderTable;
    private JTextField clientIdTextField;
    private JTextField productIdTextField;
    private JTextField qunatityTextField;
    private JButton btnOrder;
    private JButton btnViewOrders;

    public OrderView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        orderTable = new JTable();
        orderTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {"OrderId", "ClientId", "ProductId", "Quantity"},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},


                },
                new String[] {
                        "New column", "New column", "New column", "New column"
                }
        ));
        orderTable.setBounds(35, 70, 337, 215);
        contentPane.add(orderTable);

        clientIdTextField = new JTextField();
        clientIdTextField.setBounds(559, 93, 124, 29);
        contentPane.add(clientIdTextField);
        clientIdTextField.setColumns(10);

        productIdTextField = new JTextField();
        productIdTextField.setBounds(559, 154, 124, 29);
        contentPane.add(productIdTextField);
        productIdTextField.setColumns(10);

        btnOrder = new JButton("Execute Order");
        btnOrder.setFont(new Font("Calibri", Font.BOLD, 16));
        btnOrder.setBounds(96, 366, 180, 44);
        contentPane.add(btnOrder);

        JLabel lblNewLabel = new JLabel("Client ID:");
        lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(450, 96, 85, 26);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Product ID:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNewLabel_1.setBounds(450, 156, 85, 29);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Quantity:");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNewLabel_2.setBounds(450, 222, 85, 29);
        contentPane.add(lblNewLabel_2);

        qunatityTextField = new JTextField();
        qunatityTextField.setBounds(559, 220, 124, 29);
        contentPane.add(qunatityTextField);
        qunatityTextField.setColumns(10);

        btnViewOrders = new JButton("View Orders");
        btnViewOrders.setFont(new Font("Calibri", Font.BOLD, 16));
        btnViewOrders.setBounds(437, 366, 180, 44);
        contentPane.add(btnViewOrders);

        JLabel lblNewLabel_3 = new JLabel("Order Table");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 24));
        lblNewLabel_3.setBounds(225, 10, 296, 44);
        contentPane.add(lblNewLabel_3);
    }

    public void btnExecuteOrderListener(ActionListener actionListener){
        this.btnOrder.addActionListener(actionListener);
    }

    public void btnViewOrdersListener(ActionListener actionListener){
        this.btnViewOrders.addActionListener(actionListener);
    }

    public String getClientIdInput(){
        return this.clientIdTextField.getText();
    }

    public String getProductIdInput(){
        return this.productIdTextField.getText();
    }

    public String getQuantityInput(){
        return this.qunatityTextField.getText();
    }

    public void setOrderTableText(Object t, int row, int col) {
        orderTable.getModel().setValueAt(t, row, col);
    }

}
