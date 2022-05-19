package project_IPL;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io. *;

public class IPL {

	public static void main(String[] args) {
		String matches = "/home/victor/Downloads/matches.csv";
	    String deliveries ="/home/victor/Downloads/deliveries.csv";
	    String line = "";
	    
	    try {
	    BufferedReader mReader = new BufferedReader(new FileReader(matches));
	    BufferedReader dReader = new BufferedReader(new FileReader(deliveries));
	    while ((line = mReader.readLine()) != null ) {
	    	System.out.println(line);
	    }
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
