import java.io.*;
//import javax.swing.JTextArea;

public class fileIO{
private String content;
	public fileIO(){
	content ="";
	}
	
	public String getFileContent() throws IOException{
		FileInputStream fstream = new FileInputStream("search.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		String test="";
		while ((strLine = br.readLine()) != null)   {
			test+=strLine;
		}
		return test;
	}
	
	public void saveFileContent(String content) throws IOException{
		FileInputStream fstream = new FileInputStream("search.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		String test="";

		while ((strLine = br.readLine()) != null)   {
			test+=strLine;
		}

		FileWriter first = new FileWriter(new File("search.txt"));
		first.write(test+"@o@"+content+"@e@\n");
		first.close();
	}
	
	
	
}