package project_IPL;
import java.util. *;
import java.io. *;

public class IPL {
	   
	
	public static void main(String[] args) {
		List<Matches> matches = getMatchesData();
		List<Deliveries> deliveries = getDeliveriesData();
		
	    findMatchesPerYear(matches);
	    findMatchesWonByEachTeamOverYears(matches);
	    findExtraRunsConceededPerTeamIn2016(matches,deliveries);
	    findEconomicalBowlerOf2015(matches,deliveries);
	    findTotalNumberOfMatchesPlayedInIpl(matches);
	}
	
	public static List<Matches> getMatchesData() {
		String path = "src/matches.csv";
		String line ="";
		List<Matches> matchList = new ArrayList<> ();
		try {
			BufferedReader dataReader = new BufferedReader(new FileReader(path));
			while((line = dataReader.readLine()) != null) {
				String[] matchData = line.split(",");
				Matches match = new Matches();
				
				int matchId = Integer.parseInt(matchData[0]);
				int season = Integer.parseInt(matchData[1]);
				int winByRuns = Integer.parseInt(matchData[11]);
				int winByWickets = Integer.parseInt(matchData[12]);
				
				
				match.setMatchId(matchId);
				match.setYear(season);
				match.setWinByRuns(winByRuns);
				match.setWinByWickets(winByWickets);
				match.setVenue(matchData[2]);
				match.setDate(matchData[3]);
				match.setTeam1(matchData[4]);
				match.setTeam2(matchData[5]);
				match.setTossWinner(matchData[6]);
				match.setWinner(matchData[10]);
				matchList.add(match);
				
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		  catch(Exception e) {
		    	System.out.println("An IO Exception occured");
	}
		return matchList;
		}
	public static List<Deliveries> getDeliveriesData(){
		String path = "src/deliveries.csv";
		String line ="";
		List<Deliveries> deliveriesList = new ArrayList<Deliveries>();
		try {
			BufferedReader dataReader = new BufferedReader(new FileReader(path));
			while((line = dataReader.readLine()) != null) {
				String[] deliveriesData = line.split(",");
				Deliveries deliveries = new Deliveries();
				
				int matchId = Integer.parseInt(deliveriesData[0]);
				int innings = Integer.parseInt(deliveriesData[1]);
				int extraRuns = Integer.parseInt(deliveriesData[16]);
				int totalRuns = Integer.parseInt(deliveriesData[17]);
				
				deliveries.setExtraRuns(extraRuns);
				deliveries.setTotalRuns(totalRuns);
				deliveriesList.add(deliveries);
				
			}
			}
			catch(FileNotFoundException e) {
				System.out.println("File not found");
			}
		    catch(Exception e) {
		    	System.out.println("An IO Exception occured");
		    }
		return deliveriesList;
	}
	
public static void findMatchesPerYear(List<Matches> matches) {
	
 Map<Integer,Integer> matchesPerSeason = new HashMap<Integer,Integer>();
 Iterator<Matches> totalMatches = matches.iterator();
 int count = 0;
 while (totalMatches.hasNext()) {
	    int year = totalMatches.next().getYear();
    	if ( matchesPerSeason.containsKey(year)){
    	matchesPerSeason.put(year, count + 1);  		
    	}else {
    	matchesPerSeason.put(year, count);
        }}
    System.out.println("Matches Played Per Season");
    System.out.println(matchesPerSeason);

    	}
  
public static void findMatchesWonByEachTeamOverYears(List<Matches> matches) {
	 Map<String,Integer> matchesWonByTeam = new HashMap<String,Integer>();
	 Iterator<Matches> totalMatches = matches.iterator();
	 int count = 0;
	 while (totalMatches.hasNext()) {
		    String wins = totalMatches.next().getWinner();
	    	if ( matchesWonByTeam .containsKey(wins)){
	    		matchesWonByTeam .put(wins, count + 1);  		
	    	}else {
	    		matchesWonByTeam .put(wins, 1);
	        }}
	    System.out.println("Matches won by teams over years");
	    System.out.println(matchesWonByTeam );
}

public static void findExtraRunsConceededPerTeamIn2016(List<Matches> matches,List<Deliveries> deliveries) {
	HashMap<String, Integer> extraRuns = new HashMap<String, Integer>();
	Iterator<Matches> matchIterator = matches.iterator();
	Iterator<Deliveries> deliveryIterator = deliveries.iterator();
	while (matchIterator.hasNext()) {
		Matches match = matchIterator.next();
		if (match.getYear() == 2016) {
			while (deliveryIterator.hasNext()) {
				Deliveries delivery = deliveryIterator.next();
				if(delivery.getMatchId() == match.getMatchId()) {
					if (extraRuns.containsKey(delivery.getBowlingTeam())) {
						extraRuns.put(delivery.getBowlingTeam(), extraRuns.get(delivery.getBowlingTeam()) + delivery.getExtraRuns());
					}else extraRuns.put(delivery.getBowlingTeam(), delivery.getExtraRuns());
					
				}
			}
		}
	}
	System.out.println("Extra runs conceeded in 2016");
	System.out.println(extraRuns);
}

public static void  findEconomicalBowlerOf2015(List<Matches> matches,List<Deliveries> deliveries) {
	
}

public static void findTotalNumberOfMatchesPlayedInIpl(List<Matches> matches) {
	
}
}
