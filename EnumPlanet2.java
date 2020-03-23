import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;
/**
* This program calculates the relative distances of the Planets.
*
* @author  Christina Ngwa
* @version 1.0
* @since   2020-03-16
*/


public class EnumPlanet2 {
  /**
  * This enum has the distances of each planet.
  */
  public enum Planets {
  
    // Stars and Planets and their distances from the sun in AU
    Mercury(69.471), Venus(107.481), Earth(149.113), Mars(222.443), 
    Jupiter(777.534), Saturn(1499.802), Uranus(2963.301), Neptune(4477.701);
    
    public final double dis;
 
    private Planets(double dis) {
      this.dis = dis;
    }

    private double distance() {
      return dis; 
    }
  }
  
  static HashMap<String, Double> map = new HashMap<String, Double>();

  /**
  * This method calculates the distance differences.
  */
  public static void calculateRelaDist(String userPlanet) {

    Planets userPlanet2 = Planets.valueOf(userPlanet);
    for (Planets p : Planets.values()) {
      double diff = Math.round(Math.abs(p.distance() - userPlanet2.distance()));
      map.put(p.toString(), Double.valueOf(diff));
    }
  }
  
  /**
  * This method asks for a planet and shows the relative distances.
  */
  @SuppressWarnings("unchecked")
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
        Planets userPlanet2 = Planets.valueOf(userPlanet);
        break;
      } catch (IllegalArgumentException e) {
        System.err.println("No such planet. Try again.");
      }
    }

    // call method
    calculateRelaDist(userPlanet);
    
    System.out.println("\nSorting...\n");
    // sorting in ascending order
    List l = new LinkedList(map.entrySet());
    Collections.sort(l, new Comparator() {
      public int compare(Object obj1, Object obj2) {
        return ((Comparable) ((Map.Entry) (obj1)).getValue())
                .compareTo(((Map.Entry) (obj2)).getValue());
      }
    });

    // putting in map to keep it's order
    Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();

    for (Iterator i = l.iterator(); i.hasNext();) {
      Map.Entry element = (Map.Entry) i.next();
      sortedMap.put((String) element.getKey(), (Double) element.getValue());
    }
    
    // output
    for (String x: sortedMap.keySet()) {
      String key = x.toString();
      double value = sortedMap.get(x);
      if (value > 0.0) {
        System.out.println(key + " is " + value + " million km away from " 
                           + userPlanet);
      }
    }
  }
}