package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class ProductView extends JFrame {


    private JPanel contentPane;
    private JTable table;
    private JTextField productNameTextField;
    private JTextField productPriceTextField;
    private JTextField productQuantityTextField;
    private JButton btnAddProduct;
    private JButton btnEditProduct;
    private JButton btnViewAllProducts;
    private JButton btnDeleteProduct;

    public ProductView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Product Table");
        lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(237, 10, 239, 54);
        contentPane.add(lblNewLabel);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        { "Id", "Name", "Price", "Quantity"},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "Id", "Name", "Price", "Quantity"
                }
        ));
        table.setBounds(24, 74, 411, 272);
        contentPane.add(table);

        btnAddProduct = new JButton("Add Product");
        btnAddProduct.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAddProduct.setBounds(50, 391, 123, 35);
        contentPane.add(btnAddProduct);

        btnEditProduct = new JButton("Edit Product");
        btnEditProduct.setFont(new Font("Calibri", Font.BOLD, 14));
        btnEditProduct.setBounds(200, 391, 123, 35);
        contentPane.add(btnEditProduct);

        btnViewAllProducts = new JButton("View All");
        btnViewAllProducts.setFont(new Font("Calibri", Font.BOLD, 14));
        btnViewAllProducts.setBounds(500, 391, 123, 35);
        contentPane.add(btnViewAllProducts);

        btnDeleteProduct = new JButton("Delete Product");
        btnDeleteProduct.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDeleteProduct.setBounds(350, 391, 123, 35);
        contentPane.add(btnDeleteProduct);

        productNameTextField = new JTextField();
        productNameTextField.setBounds(573, 120, 138, 27);
        contentPane.add(productNameTextField);
        productNameTextField.setColumns(10);

        productPriceTextField = new JTextField();
        productPriceTextField.setBounds(573, 201, 138, 27);
        contentPane.add(productPriceTextField);
        productPriceTextField.setColumns(10);

        productQuantityTextField = new JTextField();
        productQuantityTextField.setBounds(573, 280, 138, 27);
        contentPane.add(productQuantityTextField);
        productQuantityTextField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Name:");
        lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(445, 123, 93, 24);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Price:");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNewLabel_2.setBounds(445, 204, 93, 24);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Quantity:");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNewLabel_3.setBounds(445, 283, 93, 24);
        contentPane.add(lblNewLabel_3);
    }

    public void btnDeleteProductListener(ActionListener actionListener){
        this.btnDeleteProduct.addActionListener(actionListener);
    }

    public String getProductNameInput(){
        return this.productNameTextField.getText();
    }

    public String getProductPriceInput(){
        return this.productPriceTextField.getText();
    }

    public String getProductQuantityInput(){
        return this.productQuantityTextField.getText();
    }

    public void btnAddProductListener(ActionListener actionListener) {
        this.btnAddProduct.addActionListener(actionListener);
    }

    public void btnEditProductListener(ActionListener actionListener){
        this.btnEditProduct.addActionListener(actionListener);
    }

    public void btnViewAllProductsListener(ActionListener actionListener){
        this.btnViewAllProducts.addActionListener(actionListener);
    }

    public int getIdFromTable(){
        int value;
        int column = 0;
        int row = table.getSelectedRow();
        value = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
        return value;
    }

    public void setProductTableText(Object t, int row, int col) {
        table.getModel().setValueAt(t, row, col);
    }

}