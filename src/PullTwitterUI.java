import java.awt.FlowLayout;

import javax.swing.JPanel;

import twitter4j.TwitterException;
import twitter4j.examples.twitterLib;


public class PullTwitterUI extends PullMessageUI {

	private static PullTwitterUI self;
	private twitterLib twitter;

	public static PullTwitterUI getInstance()
	{
		if(self == null)
			self = new PullTwitterUI();
		return self;
	}
	
	private PullTwitterUI() {
		super();
	}

	protected void initialize() {
		this.setSize(500, 400);
		this.setContentPane(getJContentPane());
		this.setTitle("Pull Twitter Message User Interface");
		this.setResizable(false);
		twitter = new twitterLib();
   		twitter.setkey("q994Yu3N6ztOokr4QyhiXw", "Gs2EhcxGH5KZZhd0J5kMMHq7fuPhcDksiKL3dwaWRsM");
		twitter.setToken("44373900-qIPSm1MDIxqp1bVhxFPynrfhzGCKffFEwrymzNBCm", "rXcO0jZa4GE5ZYySAPQ4pxQIFVaJ3PrWo4ZjKckBA");
	}

	protected JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jContentPane = new JPanel();
			jContentPane.setLayout(new FlowLayout(FlowLayout.LEFT));

			jContentPane.add(getActionBtn(), null);
			jContentPane.add(getTxtMsg(), null);
		}
		return jContentPane;
	}
	
	protected void pullMessage()
	{
		twitter.accessTwitter();
		try
		{
			txtMsg.setText(twitter.getLastestStatus());
		}
		catch(TwitterException twe)
		{
			twe.printStackTrace();
			txtMsg.setText("Error Occurred.");
		}
	}
}
