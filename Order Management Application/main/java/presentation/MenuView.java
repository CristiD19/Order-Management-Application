package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MenuView extends JFrame {

    private JPanel contentPane;
    private JButton btnProductTable;
    private JButton btnOrderTable;
    private JButton btnClientTable;
    public MenuView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("MENU");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        lblNewLabel.setBounds(155, 10, 209, 63);
        contentPane.add(lblNewLabel);

        btnClientTable = new JButton("Client Table");
        btnClientTable.setFont(new Font("Calibri", Font.BOLD, 15));
        btnClientTable.setBounds(34, 179, 121, 43);
        contentPane.add(btnClientTable);

        btnProductTable = new JButton("Product table");
        btnProductTable.setFont(new Font("Calibri", Font.BOLD, 15));
        btnProductTable.setBounds(207, 179, 121, 43);
        contentPane.add(btnProductTable);

        btnOrderTable = new JButton("Order Table");
        btnOrderTable.setFont(new Font("Calibri", Font.BOLD, 15));
        btnOrderTable.setBounds(380, 179, 121, 43);
        contentPane.add(btnOrderTable);
    }

    public void btnClientTableListener(ActionListener actionListener) {
        this.btnClientTable.addActionListener(actionListener);
    }

    public void btnProductTableListener(ActionListener actionListener) {
        this.btnProductTable.addActionListener(actionListener);
    }

    public void btnOrderTableListener(ActionListener actionListener) {
        this.btnOrderTable.addActionListener(actionListener);
    }

}

