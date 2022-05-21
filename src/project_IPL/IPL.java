package project_IPL;
import java.util.HashMap;
import java.io.FileReader;
import java.io. *;

public class IPL {
	
	public static void matchesPerYear() {
		
	}

	public static void main(String[] args) {
		String matches = "/home/victor/Downloads/matches.csv";
	    String deliveries ="/home/victor/Downloads/deliveries.csv";
	    String line = "";
	    
	    
	    try {
	    BufferedReader mReader = new BufferedReader(new FileReader(matches));
	    BufferedReader dReader = new BufferedReader(new FileReader(deliveries));
	    HashMap<String,Integer> matches_per_season = new HashMap<String,Integer>();
	    int matches_played=1;
	    while ((line = mReader.readLine()) != null) {
	    	String [] values = line.split(",");
	    	if (matches_per_season.get(values[1])==null) {
	    	matches_played=1;
	    	matches_per_season.put(values[1], matches_played);  		
	    	}else {
	    	matches_played = matches_played +1;
	    	matches_per_season.put(values[1], matches_played);
	    	}}
	    System.out.println("Scenario:1");
	    System.out.println("Total matches per each year = " + matches_per_season);
	    
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    
	}

}
