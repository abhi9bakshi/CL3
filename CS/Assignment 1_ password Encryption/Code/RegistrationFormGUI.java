package com.pratiksha.cl3.assignment1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RegistrationFormGUI extends JFrame {

	Connection con;
	Statement stmt;

	PreparedStatement preStatement;

	JLabel title, idLabel, nameLabel, genderLabel, userLabel, passLabel;

	JTextField idField, nameField, genderField, userField, passField;

	JButton registerButton, exitButton, resetButton, refresh;

	JRadioButton male, female;
	ButtonGroup bg;

	JPanel panel;
	JTable table;

	DefaultTableModel model;

	JScrollPane scrollpane;

	public RegistrationFormGUI() {
		// TODO Auto-generated constructor stub

		super("REGISTRATION FORM");
		setSize(770, 420);
		setLayout(null);
		connect();

		title = new JLabel("Registration Form");
		title.setBounds(60, 7, 200, 30);

		nameLabel = new JLabel("Name");
		nameLabel.setBounds(30, 85, 60, 30);

		genderLabel = new JLabel("Gender");
		genderLabel.setBounds(30, 120, 60, 30);

		userLabel = new JLabel("Username");
		userLabel.setBounds(30, 155, 60, 30);

		passLabel = new JLabel("Password");
		passLabel.setBounds(30, 190, 60, 30);

		nameField = new JTextField();
		nameField.setBounds(95, 85, 130, 30);

		male = new JRadioButton("Male");
		male.setBounds(95, 120, 60, 30);

		female = new JRadioButton("Female");
		female.setBounds(155, 120, 70, 30);

		bg = new ButtonGroup();
		bg.add(male);
		bg.add(female);

		userField = new JTextField();
		userField.setBounds(95, 155, 130, 30);

		passField = new JTextField();
		passField.setBounds(95, 190, 130, 30);

		add(title);
		add(nameLabel);
		add(genderLabel);
		add(userLabel);
		add(passLabel);

		add(nameField);
		add(male);
		add(female);
		add(userField);
		add(passField);

		exitButton = new JButton("Exit");
		exitButton.setBounds(25, 250, 80, 25);

		registerButton = new JButton("Register");
		registerButton.setBounds(110, 250, 100, 25);

		resetButton = new JButton("Reset");
		resetButton.setBounds(60, 320, 100, 25);
		resetButton.setEnabled(false);

		add(exitButton);
		add(registerButton);
		add(resetButton);

		panel = new JPanel();
		panel.setLayout(new GridLayout());
		panel.setBounds(250, 20, 480, 330);
		panel.setBorder(BorderFactory.createDashedBorder(Color.blue));
		add(panel);

		refresh = new JButton("Refresh Table");
		refresh.setBounds(350, 350, 270, 15);
		add(refresh);

		model = new DefaultTableModel();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		model.addColumn("S.No");
		model.addColumn("Name");
		model.addColumn("Gender");
		model.addColumn("Username");
		model.addColumn("Password");
		model.addColumn("Password(O)");

		scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scrollpane);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			stmt = con.createStatement();
			preStatement = con.prepareStatement("insert into regform(name,gender,user,pass,passO) values(?,?,?,?,?)");
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}