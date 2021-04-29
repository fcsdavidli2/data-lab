// https://codehs.com/uploads/efd0a818c1548520c7a61435770b9d0a

/*
 * Arrays of objects
 */

import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Welcome03_List {
   public void runWelcome03() {
      DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml").load();
      ArrayList<WeatherStation> allstns = ds.fetchList("WeatherStation", "station/station_name", 
             "station/station_id", "station/state",
             "station/latitude", "station/longitude");
      System.out.println("Total stations: " + allstns.size());
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a state abbreviation: ");
      String state = sc.next();
      System.out.println("Stations in " + state);
      int numOfStation = 0;
      for (WeatherStation ws : allstns) {
         if (ws.isLocatedInState(state)) {
            System.out.println("  " + ws.getId() + ": " + ws.getName());
            numOfStation++;
         }
      }
      System.out.println("In " + state + " There are "+ numOfStation + " stations.");
      
     

      double smallestLat = 100000;
      String southernmost = "";
      for (WeatherStation ws : allstns) {
         if (ws.getLat()<smallestLat) {
            smallestLat = ws.getLat();
            southernmost = ws.getName();
         }
      }
      System.out.println("Southernmost station in " + southernmost);
   }
}