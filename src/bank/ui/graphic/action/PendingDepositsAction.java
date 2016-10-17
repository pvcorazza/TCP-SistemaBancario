package bank.ui.graphic.action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

import bank.business.AccountManagementService;
import bank.business.domain.Deposit;
import bank.ui.TextManager;
import bank.ui.graphic.BankGraphicInterface;
import bank.ui.graphic.GUIUtils;

public class PendingDepositsAction extends BankAction {

	private static final long serialVersionUID = 5568655004864764509L;
	private final AccountManagementService accountManagementService;
	private JTable depositsTable;

	public PendingDepositsAction(BankGraphicInterface bankInterface, TextManager textManager,
			AccountManagementService accountManagementService) {
		super(bankInterface, textManager);

		this.accountManagementService = accountManagementService;

		super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		super.putValue(Action.NAME, textManager.getText("action.deposits.pending"));
	}

	private class TransactionTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 2497950520925208080L;
		private String[] columnNames = { "Data", "Detalhes", "Local", "Valor" };
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
				val = NumberFormat.getCurrencyInstance().format((t.getAmount()));
			default:
				assert false;
				break;
			}
			return val;
		}

	}

	@Override
	public void execute() throws Exception {

		if (accountManagementService.hasPendingDeposits()) {

			final JDialog dialog = new JDialog();
			
			dialog.setTitle("Depósitos Pendentes");
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JPanel transactionsPanel = new JPanel();
			transactionsPanel.setLayout(new BorderLayout());
			transactionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			depositsTable = new JTable();
			JScrollPane scrollPane = new JScrollPane(depositsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setPreferredSize(new Dimension(500,500));
			transactionsPanel.add(scrollPane, BorderLayout.NORTH);

			dialog.add(transactionsPanel, BorderLayout.SOUTH);
			
			JButton btnUpdate = new JButton("Atualizar");
			//btnUpdate.setBounds(204, 150, 100, 25);
			btnUpdate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
						try {
							dialog.dispose();
							execute();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}

			});
			transactionsPanel.add(btnUpdate);

			dialog.pack();
			dialog.setVisible(true);

			List<Deposit> deposits = accountManagementService.getAllPendingDeposits();
			this.depositsTable.setModel(new TransactionTableModel(deposits));
			
		}

	}

}
