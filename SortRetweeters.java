import twitter4j.conf.*;
import twitter4j.api.*;
import twitter4j.*;
import twitter4j.util.*;
import java.io.*;
import java.util.*;

public class SortRetweeters
{
  public static Twitter configure() throws Exception
  {
    File file = new File("C:\\Users\\Nayil\\Documents\\TwitterAPIcreds\\credentials.txt");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String key = br.readLine(); 
    String secretKey = br.readLine();
    String token = br.readLine();
    String secretToken = br.readLine();
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(key)
      .setOAuthConsumerSecret(secretKey)
      .setOAuthAccessToken(token)
      .setOAuthAccessTokenSecret(secretToken);
    TwitterFactory tf = new TwitterFactory(cb.build());
    Twitter twitter = tf.getInstance();
    br.close();
    return twitter;
  }
  public static void testPrintTweet() throws Exception
  {
    Scanner input = new Scanner(System.in);
    Twitter twitter = configure();
    System.out.println("Input Tweet ID.");
    long tweetID = input.nextLong();
    try {
        Status status = twitter.showStatus(tweetID);
        if (status == null) { // 
            // don't know if needed - T4J docs are very bad
        } else {
            System.out.println("@" + status.getUser().getScreenName()
                        + " - " + status.getText());
        }
    } catch (TwitterException e) {
        System.err.print("Failed to search tweets: " + e.getMessage());
        // e.printStackTrace();
        // DON'T KNOW IF THIS IS THROWN WHEN ID IS INVALID
    }
    input.close();
  }
  
  public static void sortRetweeters() throws Exception
  {
    Scanner input = new Scanner(System.in);
    Twitter twitter = configure();
    System.out.println("Input Tweet ID.");
    long tweetID = input.nextLong();
    Paging paging = new Paging();
    IDs object = twitter.getRetweeterIds(tweetID, 1000, -1);
    long[] id = object.getIDs();
    TreeMap<Integer,String> tm = new TreeMap<Integer, String>();
    try
    {
      for(int k=0;k<200;k++)
      {
        User user = twitter.showUser(id[k]);
        int followers = user.getFollowersCount();
        String name = user.getScreenName();
        tm.put(followers, name );
      }
    }
    catch(Exception e)
    {
      System.out.println(e.toString());
    }
    System.out.println("Done treemap");

    //Reverse the order of the treemap 
    System.out.println("Reversing treemap");
    NavigableMap<Integer,String> reverseTreeMap = tm.descendingMap();
    
    //Put treemap values in string array
    Collection c = reverseTreeMap.values();
    System.out.println(c);
    Set<Integer> keys = reverseTreeMap.keySet();
    System.out.println(keys);
  }
  public static void main(String[]args) throws Exception
  {
    sortRetweeters();
  }
}