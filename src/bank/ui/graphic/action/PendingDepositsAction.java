package bank.ui.graphic.action;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import bank.business.AccountManagementService;
import bank.business.domain.Branch;
import bank.business.domain.CurrentAccountId;
import bank.business.domain.Deposit;
import bank.business.domain.Transaction;
import bank.business.domain.Transfer;
import bank.business.domain.Withdrawal;
import bank.business.domain.Deposit.DepositStatus;
import bank.ui.TextManager;
import bank.ui.graphic.BankGraphicInterface;
import bank.ui.graphic.GUIUtils;

import bank.ui.graphic.action.StatementAction.StatementType;
import bank.ui.text.UIUtils;

public class PendingDepositsAction extends BankAction {

	private static final long serialVersionUID = 5568655004864764509L;
	private final AccountManagementService accountManagementService;
	private JDialog dialog;
	private JTable transactions;
	private JPanel cards;

	public PendingDepositsAction(BankGraphicInterface bankInterface, 
			TextManager textManager,
			AccountManagementService accountManagementService) {
		super(bankInterface, textManager);
		
		this.accountManagementService = accountManagementService;
		
		super.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		super.putValue(Action.NAME,
				textManager.getText("action.deposits.pending"));
	}
	
	private class TransactionTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 2497950520925208080L;

		private String[] columnNames = {"Data",
                "Detalhes",
                "Location",
                "Quantia"};
		
		private List<Deposit> deposits;

		public TransactionTableModel(List<Deposit> deposits) {
	
			this.deposits = new ArrayList<>(deposits);
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public int getRowCount() {	
			return deposits.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Deposit t = deposits.get(rowIndex);
			Object val = null;
			switch (columnIndex) {
			case 0:
				val = GUIUtils.DATE_TIME_FORMAT.format(t.getDate());
				break;
			case 1:
				val = ((Deposit) t).getEnvelope();
				
				break;
			case 2:
				val = t.getLocation();
				break;
			case 3:
				val = "+ " + t.getAmount();

			default:
				assert false;
				break;
			}
			return val;
		}

	}

	@Override
	public void execute() throws Exception {
//		JPanel transactionsPanel = new JPanel();
//		
//		
//		transactionsPanel.add(subpanel, BorderLayout.CENTER);
//		
//		transactionsPanel
//				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//		transactions = new JTable();
//		JScrollPane scrollPane = new JScrollPane(transactions,
//				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		transactionsPanel.add(scrollPane);
//
//		JPanel mainPanel = new JPanel(new BorderLayout());
//		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//
//		JPanel pane = new JPanel(new BorderLayout());
//		pane.add(mainPanel, BorderLayout.NORTH);
//		pane.add(transactionsPanel, BorderLayout.CENTER);
//
//		
//
//		this.dialog = GUIUtils.INSTANCE.createDialog(bankInterface.getFrame(),
//				"action.deposits.pending", pane);
//		this.dialog.setVisible(true);
		
		
		JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel ();
        panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                            "DEP�SITOS PENDENTES",
                                                            TitledBorder.CENTER,
                                                            TitledBorder.TOP));

		JPanel transactionsPanel = new JPanel();
		
		transactionsPanel
				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		transactions = new JTable();
		JScrollPane scrollPane = new JScrollPane(transactions,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		transactionsPanel.add(scrollPane);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(mainPanel, BorderLayout.NORTH);
		pane.add(transactionsPanel, BorderLayout.CENTER);
		
		
		
        
        //JTable table = new JTable (3, 3);

        panel.add (transactionsPanel);

        frame.add (panel);

        frame.setLocationRelativeTo (null);
        frame.pack ();
        frame.setVisible (true);
        
       
		
	}
	
	public void showPendingDeposits(){
		
		//this.transactions.setModel(new TransactionTableModel(deposits));
	}



}
