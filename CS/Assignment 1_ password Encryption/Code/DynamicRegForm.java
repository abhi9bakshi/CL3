package com.pratiksha.cl3.assignment1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;

public class DynamicRegForm extends RegistrationFormGUI {
	String passO = "";
	String gender = "";
	ResultSet rst, rstLast;
	Object[][] data;
	int serialNo;
	String SHOW = "Show";
	RegistrationFormGUI formGUIObject;

	DynamicRegForm() {
		nameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (nameField.getText().length() >= 15)
					e.consume();
			}
		});

		male.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Male";
			}
		});

		female.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Female";
			}
		});

		userField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (userField.getText().length() > 50)
					e.consume();
			}
		});

		passField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (passField.getText().length() > 9)
					e.consume();
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con.close();
					System.exit(0);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});

		registerButton.addActionListener(new AbstractAction(SHOW) {
			public void actionPerformed(ActionEvent ae) {
				try {
					if (ae.getSource() == registerButton) {
						if (nameField.getText().equals(""))
							JOptionPane.showMessageDialog(idField, "Please provide Name_Field");
						else if (userField.getText().equals(""))
							JOptionPane.showMessageDialog(idField, "Please provide Username_Field");
						else if (passField.getText().equals(""))
							JOptionPane.showMessageDialog(idField, "Please provide Password_Field");
						else if (gender.equals(""))
							JOptionPane.showMessageDialog(idField, "Please select Gender");
						else {
							// Fetching column values from Database
							preStatement.setString(1, nameField.getText());
							preStatement.setString(2, gender);
							preStatement.setString(3, userField.getText());
							String password = MD5Calculation.encrypt(passField.getText(), "MD5");
							passO = MD5Calculation.encrypt(passField.getText(), "MD5", userField.getText());
							preStatement.setString(4, password);
							preStatement.setString(5, passO);

							int i = preStatement.executeUpdate();
							if (i == 1) {
								JOptionPane.showMessageDialog(panel, "Successfully Registered");
							}
							rstLast = stmt.executeQuery("select *from regform");
							rstLast.last();
							String string = serialNo++ + "," + rstLast.getString(1) + "," + rstLast.getString(2) + ","
									+ rstLast.getString(3) + "," + rstLast.getString(4) + "," + rstLast.getString(5);
							Object[] row = null;
							row = string.split(",");
							model.addRow(row);
							panel.revalidate();

							blankFields();
						}
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// calling method resetFields()
				resetFields();
				registerButton.setEnabled(true);
				resetButton.setEnabled(false);
			}
		});

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// calling refresh() method
				refreshTable();
			}
		});

		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				int r = table.getSelectedRow();
				if (r >= 0) {
					resetButton.setEnabled(true);
					registerButton.setEnabled(false);
					idField.setText("" + table.getModel().getValueAt(r, 1));
					nameField.setText("" + table.getModel().getValueAt(r, 2));
					if (table.getModel().getValueAt(r, 3).equals("Male"))
						male.setSelected(true);
					else
						female.setSelected(true);
					userField.setText("" + table.getModel().getValueAt(r, 3));
					passField.setText("" + table.getModel().getValueAt(r, 4));
				}
			}

			// @Override
			public void mouseReleased(MouseEvent arg0) {
			}

			// @Override
			public void mousePressed(MouseEvent arg0) {
			}

			// @Override
			public void mouseExited(MouseEvent arg0) {
			}

			// @Override
			public void mouseEntered(MouseEvent arg0) {
			}
		});

		addRows();

	}

	private void addRows() {
		try {
			Object[] row = null;
			serialNo = 1;
			rst = stmt.executeQuery("select *from regform");
			while (rst.next()) {
				String string = serialNo++ + "," + rst.getString(1) + "," + rst.getString(2) + "," + rst.getString(3)
						+ "," + rst.getString(4) + "," + rst.getString(5);
				row = string.split(",");
				model.addRow(row);
			}
			panel.revalidate();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void resetFields() {
		blankFields();
	}

	private void refreshTable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		System.out.println("Refresh Table");
		addRows();
	}

	private void blankFields() {
		nameField.setText("");
		gender = "";
		bg.clearSelection();
		userField.setText("");
		passField.setText("");
	}

	public static void main(String[] args) {
		new DynamicRegForm();
	}
}