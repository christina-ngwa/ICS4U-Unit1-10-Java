import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
/**
* This program calculates the relative distances of the planets.
*
* @author  Christina Ngwa
* @version 1.0
* @since   2020-03-16
*/


public class EnumPlanet {
  /**
  * This enum has the distances of each planet
  */
  public enum planets {
  
    // Stars and planets and their distances from the sun in AU
    // Sun (), 
    Mercury (0.39), Venus (0.723), Earth (1), Mars (1.524), Jupiter (5.203),
    Saturn (9.539), Uranus (19.18), Neptune (30.06);
    
    public final double dis;
 
    private planets(double dis) {
        this.dis = dis;
    }
    private double distance() { return dis; }
  }
  
  // initialize hashmap
  static HashMap<String, Double> map = new HashMap<String, Double>();
  public static void calculateRelaDist(String userPlanet) {
    
    planets userPlanet2 = planets.valueOf(userPlanet);
    for (planets p : planets.values()) {
      double gap = Math.abs(p.distance() - userPlanet2.distance());
      map.put(p.toString(), Double.valueOf(gap));
    }
  }

  /**
  * This method asks for a planet.
  */
  public static void main(String[] args) throws IOException {
    
    // variables
    String input;
    String userPlanet;
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println("Let's find the relative distances of a planet.\n");
    while (true) {
      // input
      System.out.print("Enter a planet: ");
      input = br.readLine();
      userPlanet = input.substring(0, 1).toUpperCase() + input.substring(1);
      try {
          planets userPlanet2 = planets.valueOf(userPlanet);
          break;
      } catch ( IllegalArgumentException e ) {
          System.err.println( "No such planet. Try again." );
      }
    }
    calculateRelaDist(userPlanet);
    
    for (String name: map.keySet()) {
      String key = name.toString();
      Double value = map.get(name);
      if (value > 0.0) {
        System.out.println(key + " is " + value + " AU away from " + userPlanet);
      }
    }
  }
}