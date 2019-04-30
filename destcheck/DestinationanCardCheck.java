import java.util.*;
import java.io.*;


/**
 Route Check - Tests if a destination card route has been completed by a player.
 */
public class DestinationanCardCheck {
  
   // Destination card 
   private DestinationCard destCard = null;
   private String startingCity = "";
   private String endingCity = "";
   private boolean destTwoVisited;
  
  
  // create from getKeys() of generated hash table? or change to arraylist
   private ArrayList<String> ownedRoutes;
  
   private Hashtable<String, Hashtable<String, ArrayList<String>>> MASTER_LIST;
  //^ get from pre created object of full list?
  

   public DestinationCardCheck(DestinationCard destCard, ArrayList<String> ownedRoutes) {
      try {
         ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("MAP_HASHED.obj")));
         MASTER_LIST = (Hashtable<String, Hashtable<String, ArrayList<String>>>)ois.readObject();
         ois.close();
         //System.out.println(MASTER_LIST);
         //System.out.println(MASTER_LIST.keySet().size());
      } catch(Exception E) {
         System.err.println("Cannot find MAP_HASHED.obj");
      }
   
      this.destCard = destCard;
      this.ownedRoutes = ownedRoutes;
      destTwoVisited = false;
      startingCity = destCard.getLocationOne();
      endingCity = destCard.getLocationTwo();
   }
   
   public void newCard(DestinationCard destCard, ArrayList<String> ownedRoutes) {
      this.destCard = destCard;
      this.ownedRoutes = ownedRoutes;
      destTwoVisited = false;
      startingCity = destCard.getLocationOne();
      endingCity = destCard.getLocationTwo();
   }
   
   public boolean isCardCompleted() {
      return destTwoVisited;
   }
   
   public void verify(String currentCity) {
      System.out.println(MASTER_LIST.get(currentCity));
      Object[] currentRouteSet = MASTER_LIST.get(currentCity).keySet().toArray();
      for(int i = 0; i < currentRouteSet.length; i++) {
         String tempRoute = (String)currentRouteSet[i];
         String tempDest = MASTER_LIST.get(currentCity).get(tempRoute).get(0);
      
         
         if(ownedRoutes.indexOf(tempRoute) > -1) {
            System.out.println("TempRoute: " + tempRoute);
            System.out.println("TempDest: " + tempDest);
            ownedRoutes.remove(tempRoute);
            if(tempDest.equals(endingCity)) {
               destTwoVisited = true;
            }
            verify(tempDest);
         }
      }
   }
   


   public static void main(String[] args) {
      DestinationCard destCard = new DestinationCard("San Francisco", "Santa Fe");
      ArrayList<String> ownedRoutes = new ArrayList<String>();
      ownedRoutes.add("white_1");
      ownedRoutes.add("green_1");
      ownedRoutes.add("yellow_1"); 
      ownedRoutes.add("orange_3");
      ownedRoutes.add("yellow_4");
      ownedRoutes.add("blue_1");
      ownedRoutes.add("grey_5");
      ownedRoutes.add("purple_2");
      ownedRoutes.add("grey_13");
      ownedRoutes.add("yellow_3");
      DestinationCardCheck dcc = new DestinationCardCheck(destCard, ownedRoutes);
      
      dcc.verify(destCard.getLocationOne());
      
      System.out.println(dcc.isCardCompleted());
   }
   
   //
}