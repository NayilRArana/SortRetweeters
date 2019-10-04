import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.io.*;

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
  public static void testPostingToTwitter() throws Exception{
    Twitter twitter = configure();
    String message="\"A Visit to Transylvania\" by Euromaxx: Lifestyle Europe (DW) \n http://bit.ly/1cHB7MH";
    Status status = twitter.updateStatus(message);
  }
  public static void main(String[]args) throws Exception
  {
    testPostingToTwitter();
  }
}