import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Dimension;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PullMsgSrvUI extends PullMessageUI {

	private static PullMsgSrvUI self;

	private JLabel lblGroupID;
	private JTextField txtGroupID;
	private JLabel lblFetch;
	private JSlider sldFetch;
	private JLabel lblFSize;
	private JPanel groupIDPanel;
	private JPanel fetchPanel;
	private JPanel upperPanel;
	private Messenger messager;
	
	public static PullMsgSrvUI getInstance()
	{
		if(self == null)
			self = new PullMsgSrvUI();
		return self;
	}
	
	private PullMsgSrvUI() {
		super();
	}

	protected void pullMessage(){
		if(txtGroupID.getText() == null || txtGroupID.getText().trim().equals(""))
		{
			JOptionPane.showMessageDialog(this,"Please specify a group.");
		}
		else
		{
			int groupID = Integer.parseInt(txtGroupID.getText());
			String[] msgs = messager.getLatestMessages(groupID, sldFetch.getValue());
			if(msgs.length==0)
			{
				txtMsg.setText("No new message fetched for group:"+groupID);
			}
			else
			{
				String outputStr = "";
				for(int i=0; i<msgs.length; i++)
				{
					String[] tmp;
					tmp = msgs[i].split("&");
					outputStr+=(tmp[2]+" - Group:"+tmp[1]+" | "+tmp[0]+"\n");
				}
				txtMsg.setText(outputStr);
			}
		}
	}
	
	protected void initialize() {
		this.setSize(500, 450);
		this.setContentPane(getJContentPane());
		this.setResizable(false);
		this.setTitle("Pull MsgSrv Message User Interface");
		messager = new Messenger();
	}

	protected JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jContentPane = new JPanel();
			jContentPane.setLayout(new FlowLayout(FlowLayout.LEFT));

			jContentPane.add(getUpperPanel(), null);
			jContentPane.add(getActionBtn(), null);
			jContentPane.add(getTxtMsg(), null);
			//jContentPane.add(getTxtScrollPane(), null);
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

	private JSlider getSldFetch() {
		if (sldFetch == null) {
			sldFetch = new JSlider();
			sldFetch.setValue(10);
			sldFetch.setMinimum(1);
			sldFetch.setMaximum(20);
			sldFetch.setPreferredSize(new Dimension(100, 28));
			sldFetch.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					lblFSize.setText(sldFetch.getValue()+"");
					
				}
			});
		}
		return sldFetch;
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

	private JPanel getFetchPanel() {
		if (fetchPanel == null) {
			fetchPanel = new JPanel();
			fetchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			lblFSize = new JLabel();
			
			lblFetch = new JLabel();
			lblFetch.setText("Fetch Size:");			
			
			fetchPanel.add(lblFetch, null);
			fetchPanel.add(getSldFetch(), null);
			fetchPanel.add(lblFSize, null);
			
			lblFSize.setText(sldFetch.getValue()+"");
		}
		return fetchPanel;
	}

	private JPanel getUpperPanel() {
		if (upperPanel == null) {
			upperPanel = new JPanel();
			upperPanel.setLayout(new GridLayout(1,2));
			
			upperPanel.add(getGroupIDPanel(), null);
			upperPanel.add(getFetchPanel(), null);
		}
		return upperPanel;
	}
}
