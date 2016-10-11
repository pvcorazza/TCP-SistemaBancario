package bank.ui.graphic.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import bank.business.AccountManagementService;
import bank.ui.TextManager;
import bank.ui.graphic.BankGraphicInterface;

/**
 * 
 **/

public class CheckDepositAction extends BankAction {

	
	private static final long serialVersionUID = 3464305499773865806L;
	
	private AccountManagementService accountManagementService;

	public CheckDepositAction(BankGraphicInterface bankInterface, 
			TextManager textManager,
			AccountManagementService accountManagementService) {
		
		super(bankInterface, textManager);
		
		this.accountManagementService = accountManagementService;
		super.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		super.putValue(Action.NAME,
				textManager.getText("action.deposit.check"));
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub

	}

}
