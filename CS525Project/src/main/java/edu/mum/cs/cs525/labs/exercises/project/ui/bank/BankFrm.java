package edu.mum.cs.cs525.labs.exercises.project.ui.bank;

import java.awt.*;
import java.util.ArrayList;

import edu.mum.cs.cs525.labs.exercises.project.framework.Account;
import edu.mum.cs.cs525.labs.exercises.project.framework.AccountService;
import edu.mum.cs.cs525.labs.exercises.project.framework.AccountServiceImpl;
import edu.mum.cs.cs525.labs.exercises.project.framework.AccountType;
import edu.mum.cs.cs525.labs.exercises.project.framework.CreateAbstractFactory;
import edu.mum.cs.cs525.labs.exercises.project.framework.CreateAccountTO;
import edu.mum.cs.cs525.labs.exercises.project.ui.framework.*;//.MainFrm;
import edu.mum.cs.cs525.labs.exercises.project.bank.*;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

/**
 * A basic JFC based application.
 */

public class BankFrm extends MainFrm {
	/****
	 * init variables in the object
	 ****/
	String accountnr, clientType;

	javax.swing.JButton JButton_PerAC = new javax.swing.JButton();
	javax.swing.JButton JButton_CompAC = new javax.swing.JButton();
	javax.swing.JButton JButton_Addinterest = new javax.swing.JButton();

	BankFrm myframe;

	public BankFrm() {
		super();
		myframe = this;

		setTitle("Bank Application.");
		// setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
		// getContentPane().setLayout(new BorderLayout(0,0));
		// setSize(575,310);
		// setVisible(false);
		// JPanel1.setLayout(null);
		// getContentPane().add(BorderLayout.CENTER, JPanel1);
		// JPanel1.setBounds(0,0,575,310);
		/*
		 * /Add five buttons on the pane /for Adding personal account, Adding company
		 * account /Deposit, Withdraw and Exit from the system
		 */
		// JScrollPane1 = new JScrollPane();
		model = new DefaultTableModel();

		initModelCol();
		rowdata = new Object[8];
		newaccount = false;
		JTable1 = new JTable(model);

		JPanel1.add(JScrollPane1);
		// JScrollPane1.setBounds(12,92,444,160);
		JScrollPane1.getViewport().add(JTable1);
		JTable1.setBounds(0, 0, 420, 0);
		// rowdata = new Object[8];

		JButton_PerAC.setText("Add personal account");
		JPanel1.add(JButton_PerAC);
		JButton_PerAC.setBounds(24, 20, 192, 33);
		JButton_CompAC.setText("Add company account");
		JButton_CompAC.setActionCommand("jbutton");
		JPanel1.add(JButton_CompAC);
		JButton_CompAC.setBounds(240, 20, 192, 33);
		JButton_Addinterest.setBounds(448, 20, 106, 33);
		JButton_Addinterest.setText("Add interest");
		JPanel1.add(JButton_Addinterest);

		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		JButton_Exit.addActionListener(lSymAction);
		JButton_PerAC.addActionListener(lSymAction);
		JButton_CompAC.addActionListener(lSymAction);
		JButton_Deposit.addActionListener(lSymAction);
		JButton_Withdraw.addActionListener(lSymAction);
		JButton_Addinterest.addActionListener(lSymAction);

	}

	private void initModelCol() {
		model.addColumn("AccountNr");
		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("P/C");
		model.addColumn("Ch/S");
		model.addColumn("Amount");
	}

	/*****************************************************
	 * The entry point for this application. Sets the Look and Feel to the System
	 * Look and Feel. Creates a new JFrame1 and makes it visible.
	 *****************************************************/
	static public void main(String args[]) {
		try {
			// Add the following code if you want the Look and Feel
			// to be set to the Look and Feel of the native system.

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			}

			// Create a new instance of our application's frame, and make it visible.
			(new BankFrm()).setVisible(true);
		} catch (Throwable t) {
			t.printStackTrace();
			// Ensure the application exits with an error condition.
			System.exit(1);
		}
	}

	class SymWindow extends java.awt.event.WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent event) {
			Object object = event.getSource();
			if (object == BankFrm.this)
				BankFrm_windowClosing(event);
		}
	}

	void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
		// to do: code goes here.

		BankFrm_windowClosing_Interaction1(event);
	}

	void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	class SymAction implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			Object object = event.getSource();
			if (object == JButton_Exit)
				JButtonExit_actionPerformed(event);
			else if (object == JButton_PerAC)
				JButtonPerAC_actionPerformed(event);
			else if (object == JButton_CompAC)
				JButtonCompAC_actionPerformed(event);
			else if (object == JButton_Deposit)
				JButtonDeposit_actionPerformed(event);
			else if (object == JButton_Withdraw)
				JButtonWithdraw_actionPerformed(event);
			else if (object == JButton_Addinterest)
				JButtonAddinterest_actionPerformed(event);

		}
	}

	void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * JDialog_AddPAcc type object is for adding personal information construct a
		 * JDialog_AddPAcc type object set the boundaries and show it
		 */

		JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		// fill the data table
		//
		AccountService accSer = new BankAccountService();
		ArrayList<BankAccount> accounts = (ArrayList<BankAccount>) accSer.getAccounts();
		//
		// model

		//model = new DefaultTableModel();
        //refresh model
		//initModelCol();
		for (BankAccount bankAccount : accounts) {
			rowdata[0] = bankAccount.getAccountNumber();
			rowdata[1] = bankAccount.getName();
			rowdata[2] = bankAccount.getCity();
			if (bankAccount instanceof PersonalAccount)
				rowdata[3] = AccountType.personal.toString();
			else
				rowdata[3] = AccountType.company.toString();
           if(bankAccount.getInterestType() instanceof CheckingInterest)
			rowdata[4] = "Checking";
           else
        	   rowdata[4] = "Saving";
			rowdata[5] = bankAccount.getBalance();
			model.addRow(rowdata);
			JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
			newaccount = false;
		}

		// for testing we are going to load all from list

		// if (newaccount){
		// // add row to table
		// rowdata[0] = accountnr;
		// rowdata[1] = clientName;
		// rowdata[2] = city;
		// rowdata[3] = "P";
		// rowdata[4] = accountType;
		// rowdata[5] = "0";
		// model.addRow(rowdata);
		// JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
		// newaccount=false;
		// }

	}

	void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * construct a JDialog_AddCompAcc type object set the boundaries and show it
		 */

		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myframe);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		if (newaccount) {
			// add row to table
			rowdata[0] = accountnr;
			rowdata[1] = clientName;
			rowdata[2] = city;
			rowdata[3] = "C";
			rowdata[4] = accountType;
			rowdata[5] = "0";
			model.addRow(rowdata);
			JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
			newaccount = false;
		}

	}

	// void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event)
	// {
	// // get selected name
	// int selection = JTable1.getSelectionModel().getMinSelectionIndex();
	// if (selection >=0){
	// String accnr = (String)model.getValueAt(selection, 0);
	//
	// //Show the dialog for adding deposit amount for the current mane
	// JDialog_Deposit dep = new JDialog_Deposit(myframe,accnr);
	// dep.setBounds(430, 15, 275, 140);
	// dep.show();
	//
	// // compute new amount
	// long deposit = Long.parseLong(amountDeposit);
	// String samount = (String)model.getValueAt(selection, 5);
	// long currentamount = Long.parseLong(samount);
	// long newamount=currentamount+deposit;
	// model.setValueAt(String.valueOf(newamount),selection, 5);
	// }
	//
	//
	// }

	// void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event)
	// {
	// // get selected name
	// int selection = JTable1.getSelectionModel().getMinSelectionIndex();
	// if (selection >=0){
	// String accnr = (String)model.getValueAt(selection, 0);
	//
	// //Show the dialog for adding withdraw amount for the current mane
	// JDialog_Withdraw wd = new JDialog_Withdraw(myframe,accnr);
	// wd.setBounds(430, 15, 275, 140);
	// wd.show();
	//
	// // compute new amount
	// long deposit = Long.parseLong(amountDeposit);
	// String samount = (String)model.getValueAt(selection, 5);
	// long currentamount = Long.parseLong(samount);
	// long newamount=currentamount-deposit;
	// model.setValueAt(String.valueOf(newamount),selection, 5);
	// if (newamount <0){
	// JOptionPane.showMessageDialog(JButton_Withdraw, " Account "+accnr+" : balance
	// is negative: $"+String.valueOf(newamount)+" !","Warning: negative
	// balance",JOptionPane.WARNING_MESSAGE);
	// }
	// }
	//
	//
	// }

	void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
		JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts",
				"Add interest to all accounts", JOptionPane.WARNING_MESSAGE);

	}
}
