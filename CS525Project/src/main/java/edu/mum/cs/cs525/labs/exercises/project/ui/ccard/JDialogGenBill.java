package edu.mum.cs.cs525.labs.exercises.project.ui.ccard;
/*
		A basic implementation of the JDialog class.
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.mum.cs.cs525.labs.exercises.project.creditcard.CreditCardAccountService;
import edu.mum.cs.cs525.labs.exercises.project.creditcard.CreditReport;
import edu.mum.cs.cs525.labs.exercises.project.framework.AccountService;
import edu.mum.cs.cs525.labs.exercises.project.framework.Report;

public class JDialogGenBill extends javax.swing.JDialog
{
    String billstring;
    
	public JDialogGenBill(Frame parent)
	{
		super(parent);
		
		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.
		//{{INIT_CONTROLS
		getContentPane().setLayout(null);
		setSize(800,500);
		setVisible(false);
		getContentPane().add(JScrollPane1);
		JScrollPane1.setBounds(10,5,770,450);
		//JScrollPane1.getViewport().add(JTextField1);
		//JTextField1.setBounds(0,0,355,237);
		
		
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("AccountNumber");
		model.addColumn("UserName");
		model.addColumn("Incoming");
		model.addColumn("Outcoming");
		model.addColumn("Total");
		model.addColumn("MinimumPayment");
		model.addColumn("Start");
		model.addColumn("End");
		
		
		Object[] rowdata;
		CreditCardAccountService service = new CreditCardAccountService();
		for(CreditReport report : service.getReport()) {
			rowdata = new Object[8];
			rowdata[0] = report.getAccountNumber();
			rowdata[1] = report.getUserName();
			rowdata[2] = report.getIncoming();
			rowdata[3] = report.getOutcoming();
			rowdata[4] = report.getTotal();
			rowdata[5] = Math.round(report.getMinimumPayment() * 100.0 ) / 100.0;
			rowdata[6] = report.getStart();
			rowdata[7] = report.getEnd();			
			model.addRow(rowdata);
		}
		JTable JTable1 = new JTable(model);
		
		// JScrollPane1.setBounds(12,92,444,160);
		JScrollPane1.getViewport().add(JTable1);
		JTable1.setBounds(0, 0, 420, 0);
		
		JButton_OK.setText("OK");
		JButton_OK.setActionCommand("OK");
		getContentPane().add(JButton_OK);
		JButton_OK.setBounds(156,276,96,24);

		// generate the string for the monthly bill
		billstring = "Name= John White\r\n";
		billstring += "Address= 1000 Main, Fairfield, IA, 52556\r\n";
		billstring += "CC number= 2341 3421 4444 5689\r\n";
		billstring += "CC type= GOLD\r\n";
		billstring += "Previous balance = $ 100.00\r\n";
		billstring += "Total Credits = $ 25.00\r\n";
		billstring += "Total Charges = $ 560.00\r\n";
		billstring += "New balance = $ 638.75\r\n";
		billstring += "Total amount due = $ 63.88\r\n";		
		billstring += "\r\n";		
		billstring += "\r\n";		
		billstring += "Name= Frank Summer\r\n";
		billstring += "Address= 1000 N, 4th St, Fairfield, IA, 52556\r\n";
		billstring += "CC number= 0099 3421 4321 6577\r\n";
		billstring += "CC type= BRONZE\r\n";
		billstring += "Previous balance = $ 200.00\r\n";
		billstring += "Total Credits = $ 45.00\r\n";
		billstring += "Total Charges = $ 150.00\r\n";
		billstring += "New balance = $ 313.53\r\n";
		billstring += "Total amount due = $ 34.49\r\n";
		JTextField1.setText(billstring);
		//}}
	
		//{{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		JButton_OK.addActionListener(lSymAction);
		//}}
	}

	public JDialogGenBill()
	{
		this((Frame)null);
	}



	//{{DECLARE_CONTROLS
	javax.swing.JScrollPane JScrollPane1 = new javax.swing.JScrollPane();
	javax.swing.JTextField JTextField1 = new javax.swing.JTextField();
	javax.swing.JButton JButton_OK = new javax.swing.JButton();
	//}}


	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_OK)
				JButtonOK_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event)
	{
		dispose();
			 
	}
}
