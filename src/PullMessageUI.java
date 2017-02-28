import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class PullMessageUI extends MessageUI {

	public PullMessageUI() {
		super();
	}

	protected abstract void pullMessage();

	protected JButton getActionBtn() {
		if (btnAction == null) {
			btnAction = new JButton();
			btnAction.setText("Pull New Message");
			btnAction.setPreferredSize(new Dimension(this.getWidth()-10,30));
			btnAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pullMessage();
				}
			});
		}
		return btnAction;
	}

	protected JTextArea getTxtMsg() {
		if (txtMsg == null) {
			txtMsg = new JTextArea();
			txtMsg.setText("");
			txtMsg.setRows(20);
			txtMsg.setEditable(false);
			txtMsg.setPreferredSize(new Dimension(this.getWidth()-10, 100));
		}
		return txtMsg;
	}
}