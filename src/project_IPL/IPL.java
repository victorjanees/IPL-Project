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
		int header = 1;
		List<Matches> matchList = new ArrayList<> ();
		try {
			BufferedReader dataReader = new BufferedReader(new FileReader(path));
			while((line = dataReader.readLine()) != null) {
				if (header == 1) {
					header = header + 1;
					continue;
				}
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
		    	e.printStackTrace();
	}
		return matchList;
		}
	public static List<Deliveries> getDeliveriesData(){
		String path = "src/deliveries.csv";
		String line ="";
		int header = 1;
		List<Deliveries> deliveriesList = new ArrayList<Deliveries>();
		try {
			BufferedReader dataReader = new BufferedReader(new FileReader(path));
			while((line = dataReader.readLine()) != null) {
				if (header == 1) {
				header = header + 1;
				continue;
			}
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
		    	e.printStackTrace();
		    }
		return deliveriesList;
	}
	
public static void findMatchesPerYear(List<Matches> matches) {
	
 Map<Integer,Integer> matchesPerSeason = new HashMap<Integer,Integer>();
 Iterator<Matches> totalMatches = matches.iterator();
 while (totalMatches.hasNext()) {
	    int year = totalMatches.next().getYear();
    	if ( matchesPerSeason.containsKey(year)){
    	matchesPerSeason.put(year, matchesPerSeason.get(year) + 1);  		
    	}else {
    	matchesPerSeason.put(year, 1);
        }}
    System.out.println("Matches Played Per Season");
    System.out.println(matchesPerSeason);

    	}
  
public static void findMatchesWonByEachTeamOverYears(List<Matches> matches) {
	 Map<String,Integer> matchesWonByTeam = new HashMap<String,Integer>();
	 Iterator<Matches> totalMatches = matches.iterator();
	 while (totalMatches.hasNext()) {
		    String wins = totalMatches.next().getWinner();
	    	if ( matchesWonByTeam .containsKey(wins)){
	    		matchesWonByTeam .put(wins, matchesWonByTeam.get(wins) + 1);  		
	    	}else {
	    		matchesWonByTeam .put(wins, 1);
	        }}
	    System.out.println("Matches won by teams over years");
	    System.out.println(matchesWonByTeam );
}

public static void findExtraRunsConceededPerTeamIn2016(List<Matches> matches,List<Deliveries> deliveries) {
	HashMap<String, Integer> extraRuns = new HashMap<String, Integer>();
	Iterator<Matches> matchIterator = matches.iterator();
	while (matchIterator.hasNext()) {
		Matches match = matchIterator.next();
		if (match.getYear() == 2016) {
			Iterator<Deliveries> deliveryIterator = deliveries.iterator();
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
    HashMap<String, Integer> runsGiven = new HashMap<String, Integer>();
    HashMap<String, Double> oversBowled = new HashMap<String, Double>();
    SortedMap<String, Double> economyMap = new TreeMap<String, Double>();
    Map<Double, String> economy = new TreeMap<>();

   
    Iterator<Matches> matchesPlayed = matches.iterator();
    while (matchesPlayed.hasNext()) {
        Matches match = matchesPlayed.next();
        if (match.getYear() == 2015) {
            Iterator<Deliveries> deliveriesIterator = deliveries.iterator();
            while (deliveriesIterator.hasNext()) {
                Deliveries delivery = deliveriesIterator.next();
                if (delivery.getMatchId() == match.getMatchId()) {
                    if (runsGiven.containsKey(delivery.getBowler())) {
                        runsGiven.put(delivery.getBowler(), runsGiven.get(delivery.getBowler()) + delivery.getTotalRuns());
                    } else runsGiven.put(delivery.getBowler(), delivery.getTotalRuns());
                }
            }
        }
    }
    Iterator<Matches> matchesIterator2 = matches.iterator();
    while (matchesIterator2.hasNext()) {
        Matches match = matchesIterator2.next();
        if (match.getYear() == 2015) {
            Iterator<Deliveries> deliveriesIterator = deliveries.iterator();
            while (deliveriesIterator.hasNext()) {
                Deliveries delivery = deliveriesIterator.next();
                if (delivery.getMatchId() == match.getMatchId()) {
                    if (oversBowled.containsKey(delivery.getBowler())) {
                        oversBowled.put(delivery.getBowler(), oversBowled.get(delivery.getBowler()) + 1.0);
                    } else oversBowled.put(delivery.getBowler(), 1.0);
                }
            }
        }
    }
    String[] bowler = oversBowled.keySet().toArray(new String[0]);
    for (int i = 0; i < bowler.length; i++) {
        if (oversBowled.containsKey(bowler[i])) {
            double runs = oversBowled.get(bowler[i]);
            oversBowled.put(bowler[i], (runs / 6));
        }
    }
    for (int j = 0; j < bowler.length; j++) {
        double economyRate = runsGiven.get(bowler[j]) / oversBowled.get(bowler[j]);
        economyMap.put(bowler[j], economyRate);
        economy.put(economyRate,bowler[j]);
    }
    System.out.println("Most Economical Bowler of 2015");
    System.out.println(economyMap);
}

public static void findTotalNumberOfMatchesPlayedInIpl(List<Matches> matches) {
	
}
}
