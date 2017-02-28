import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PostMsgSrvUI extends PostMessageUI {
	
	private static PostMsgSrvUI self;

	private JLabel lblGroupID;
	private JTextField txtGroupID;
	private JPanel groupIDPanel;
	private JPanel upperPanel;
	private JLabel lblMessage;
	private JLabel lblStatus;
	private Messenger messager;
	
	public static PostMsgSrvUI getInstance()
	{
		if(self == null)
			self = new PostMsgSrvUI();
		return self;
	}
	
	private PostMsgSrvUI() {
		super();
	}

	protected void postMessage(){
		lblStatus.setText("");
		
		if(txtMsg.getText() == null || txtMsg.getText().trim().equals(""))
		{
			JOptionPane.showMessageDialog(this,"Nothing to send.");
		}
		else if(txtGroupID.getText() == null || txtGroupID.getText().trim().equals(""))
		{
			JOptionPane.showMessageDialog(this,"Please specify a group.");
		}
		else
		{
			int groupID = Integer.parseInt(txtGroupID.getText());
			messager.addMessage(groupID, txtMsg.getText());
			txtMsg.setText("");
			lblStatus.setText("Message Sent.");
		}
	}
	
	protected void initialize() {
		this.setSize(500, 570);
		this.setContentPane(getJContentPane());
		this.setTitle("Post MsgSrv Message User Interface");
		this.setResizable(false);
		messager = new Messenger();
		file_IO  = new fileIO();
	}

	protected JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jContentPane = new JPanel();
			jContentPane.setLayout(new FlowLayout(FlowLayout.LEFT));

			jContentPane.add(getUpperPanel(), null);
			jContentPane.add(getSearchText(), null);
			jContentPane.add(getSearchBtn(), null);
			lblMessage = new JLabel("Message:");
			jContentPane.add(lblMessage, null);
			jContentPane.add(getTxtMsg(), null);
			lblStatus = new JLabel("");
			jContentPane.add(lblStatus, null);
			jContentPane.add(getActionBtn(), null);
			jContentPane.add(getStoreBtn(), null);
		}
		return jContentPane;
	}

	private JTextField getTxtGroupID() {
		if (txtGroupID == null) {
			txtGroupID = new JTextField();
			txtGroupID.setText("100");
			txtGroupID.setPreferredSize(new Dimension(100, 28));
		}
		return txtGroupID;
	}

	private JPanel getGroupIDPanel() {
		if (groupIDPanel == null) {
			groupIDPanel = new JPanel();
			groupIDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			lblGroupID = new JLabel();
			lblGroupID.setText("Group ID:");
			groupIDPanel.add(lblGroupID, null);
			groupIDPanel.add(getTxtGroupID(), null);
		}
		return groupIDPanel;
	}


	private JPanel getUpperPanel() {
		if (upperPanel == null) {
			upperPanel = new JPanel();
			upperPanel.setLayout(new GridLayout(1,2));
			
			upperPanel.add(getGroupIDPanel(), null);
		}
		return upperPanel;
	}
	
	protected void searchMessage(){
		int searchStart=0;
		txtMsg.setText("");
		String s = searchText.getText();
		if (s.length() <= 0) {
			JOptionPane.showMessageDialog(this,"Nothing to serach.");
		}
		else{
			String content="";
			try{
			content = file_IO.getFileContent();
			}
			catch(Exception ex) {
				System.out.print("Open search error");
			}
			
			int index = content.indexOf(s, searchStart);
			while (index>=0){
					int start=-1,end=-1;
					for(int i=0;start==-1;i++){
						if(content.substring(index-i,index-i+3).matches("@o@"))
							start = index-i;
					}
					for(int i=0;end==-1;i++){
						if(content.substring(index+i,index+i+3).matches("@e@"))
							end = index+i;
					}

					String c = content.substring(start+3,end);
					searchStart=end-1;
					txtMsg.setText(txtMsg.getText()+c+"\n");
					index = content.indexOf(s, searchStart);
			}
			lblStatus.setText("Message Search.");
		}
	}

	protected void storeMessage(){

			if(txtMsg.getText() == null || txtMsg.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Nothing to store.");
			}
			else{
				try{
					file_IO.saveFileContent(txtMsg.getText());
				}
				catch(Exception ex) {
					System.out.print("Seved file error");
				}
				System.out.print("File saved");
				lblStatus.setText("Message Stored");
			}
	}

}
