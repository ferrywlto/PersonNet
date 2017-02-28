import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

public class PersonNetUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton pullMsgSrvBtn = null;
	private JButton pullTwitterBtn = null;
	private JButton postMsgSrvBtn = null;
	private JButton postTwitterBtn = null;
	private PullMessageUI uiPullMsgSrv = null;
	private PullMessageUI uiPullTwitter = null;
	private PostMessageUI uiPostMsgSrv = null;
	private PostMessageUI uiPostTwitter = null;
	
	public PersonNetUI() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(450, 150);
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("PersonNet User Interface");
		this.setResizable(false);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridLayout(2,2));
			jContentPane.add(getPullMsgSrvBtn(), null);
			jContentPane.add(getPullTwitterBtn(), null);
			jContentPane.add(getPostMsgSrvBtn(), null);
			jContentPane.add(getPostTwitterBtn(), null);
			
			uiPullMsgSrv = PullMsgSrvUI.getInstance();
			uiPullMsgSrv.setVisible(false);
			uiPullTwitter = PullTwitterUI.getInstance();
			uiPullTwitter.setVisible(false);
			uiPostMsgSrv = PostMsgSrvUI.getInstance();
			uiPostMsgSrv.setVisible(false);
			uiPostTwitter = PostTwitterUI.getInstance();
			uiPostTwitter.setVisible(false);
		}
		return jContentPane;
	}

	private JButton getPullMsgSrvBtn() {
		if (pullMsgSrvBtn == null) {
			pullMsgSrvBtn = new JButton();
			pullMsgSrvBtn.setText("Open Pull MsgSrv User Interface");
			pullMsgSrvBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					uiPullMsgSrv.setVisible(true);
					
				}
			});
		}
		return pullMsgSrvBtn;
	}

	private JButton getPullTwitterBtn() {
		if (pullTwitterBtn == null) {
			pullTwitterBtn = new JButton();
			pullTwitterBtn.setText("Open Pull Twitter User Interface");
			pullTwitterBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					uiPullTwitter.setVisible(true);
				}
			});
		}
		return pullTwitterBtn;
	}

	private JButton getPostMsgSrvBtn() {
		if (postMsgSrvBtn == null) {
			postMsgSrvBtn = new JButton();
			postMsgSrvBtn.setText("Open Post MsgSrv User Interface");
			postMsgSrvBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					uiPostMsgSrv.setVisible(true);
				}
			});
		}
		return postMsgSrvBtn;
	}
	private JButton getPostTwitterBtn() {
		if (postTwitterBtn == null) {
			postTwitterBtn = new JButton();
			postTwitterBtn.setText("Open Post Twitter User Interface");
			postTwitterBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					uiPostTwitter.setVisible(true);
				}
			});
		}
		return postTwitterBtn;
	}
}
