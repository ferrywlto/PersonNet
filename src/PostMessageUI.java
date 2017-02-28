import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

abstract class PostMessageUI extends MessageUI {
	protected JButton postBtn;
	protected JButton storeMsg;
	protected JButton searchBtn;
	protected JTextField searchText;
	protected fileIO file_IO;
	
	public PostMessageUI() {
		super();
	}
	protected abstract void postMessage();
	protected abstract void storeMessage();
	protected abstract void searchMessage();
	
	protected JButton getActionBtn() {
		if (btnAction == null) {
			btnAction = new JButton();
			btnAction.setText("Post Message");
			btnAction.setPreferredSize(new Dimension(this.getWidth()-10,30));
			btnAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					postMessage();
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
			txtMsg.setEditable(true);
			txtMsg.setPreferredSize(new Dimension(this.getWidth()-10, 100));
		}
		return txtMsg;
	}
	
	protected JTextField getSearchText() {
		if (searchText == null) {
			searchText = new JTextField();
			searchText.setPreferredSize(new Dimension(this.getWidth()/7, 30));
		}
		return searchText;
	}
	
	protected JButton getSearchBtn() {
		if (searchBtn == null) {
			searchBtn = new JButton();
			searchBtn.setText("Search Message");
			searchBtn.setPreferredSize(new Dimension(this.getWidth()/3+20,30));
			searchBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						searchMessage();
					}
					catch(Exception ex) {
						System.out.print("search error");
					}
				}
			});
		}
		return searchBtn;
	}
	
	protected JButton getStoreBtn() {
		if (storeMsg == null) {
			storeMsg = new JButton();
			storeMsg.setText("Store Message");
			storeMsg.setPreferredSize(new Dimension(this.getWidth()-10,30));
			storeMsg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						storeMessage();
					}
					catch(Exception ex) {
						System.out.print("error");
					}
				}
			});
		}
		return storeMsg;
	}
}
