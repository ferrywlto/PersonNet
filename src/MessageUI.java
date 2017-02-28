import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

abstract class MessageUI extends JFrame {
	protected JPanel jContentPane;
	protected JButton btnAction;
	protected JTextArea txtMsg;
	
	public MessageUI() {
		super(); 
		initialize();
	}
	
	protected abstract void initialize();
	protected abstract JTextArea getTxtMsg();
	protected abstract JButton getActionBtn();
	protected abstract JPanel getJContentPane();
}
