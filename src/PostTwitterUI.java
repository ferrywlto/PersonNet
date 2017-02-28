import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import twitter4j.TwitterException;
import twitter4j.examples.twitterLib;

public class PostTwitterUI extends PostMessageUI {
	
	private static PostTwitterUI self;
	
	private JLabel lblMessage;
	private JLabel lblStatus;
	private twitterLib twitter;
	
	public static PostTwitterUI getInstance()
	{
		if(self == null)
			self = new PostTwitterUI();
		return self;
	}
	
	private PostTwitterUI() {
		super();
	}

	protected void postMessage(){
		lblStatus.setText("");
		if(txtMsg.getText().trim().equals("") || txtMsg.getText()==null)
		{
			JOptionPane.showMessageDialog(this,"Nothing to send.");
		}
		else
		{
    		twitter.accessTwitter();
    		try {
				twitter.tweet(txtMsg.getText());
			} catch (TwitterException e) {
				JOptionPane.showMessageDialog(this, "Error Occurred:"+e.getMessage());
			}
			txtMsg.setText("");
			lblStatus.setText("Message Sent.");
		}
	}
	
	protected void initialize() {
		this.setSize(new Dimension(500,530));
		this.setContentPane(getJContentPane());
		this.setTitle("Post Twitter Message User Interface");
		this.setResizable(false);
		twitter = new twitterLib();
   		twitter.setkey("q994Yu3N6ztOokr4QyhiXw", "Gs2EhcxGH5KZZhd0J5kMMHq7fuPhcDksiKL3dwaWRsM");
		twitter.setToken("44373900-qIPSm1MDIxqp1bVhxFPynrfhzGCKffFEwrymzNBCm", "rXcO0jZa4GE5ZYySAPQ4pxQIFVaJ3PrWo4ZjKckBA");
		file_IO  = new fileIO();
	}

	protected JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jContentPane = new JPanel();
			jContentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
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
