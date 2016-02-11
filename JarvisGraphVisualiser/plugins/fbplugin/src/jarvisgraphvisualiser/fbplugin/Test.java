package jarvisgraphvisualiser.fbplugin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Test {

	/**
	 * @param args
	 */
	
	 
	 
	 
	  //Close the output stream
	 
	
	public static void main(String[] args) {
		String MY_ACCESS_TOKEN = "CAACEdEose0cBAG1qhxiKmjp0557KFM68c2F94VbVo6Y6e2PQwOPTAxdtwHXkMsSProfN6WroY0NUiyZAZCgeeqm2HwZBNplCw0kTyU5A5pia1ypGcE0S1YHGngiYAIoHEwZCsAjqPeZAO0SfJhRoZCaX9RyRZB3WoEZD";
		FileWriter fstream = null;
		try {
			fstream = new FileWriter("out.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter out = new BufferedWriter(fstream);
        FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN);
 
        User user = facebookClient.fetchObject("me", User.class);
 
        System.out.println("User name: " + user.getName());
        Connection myFriends = facebookClient.fetchConnection("me/friends", User.class);
        Connection myFeed = facebookClient.fetchConnection("me/feed", Post.class);
 
        System.out.println("Count of my friends: " + myFriends.getData().size());
 
  
        
        processFriends(facebookClient, myFriends.getData(), true, "My Friend", out);
        
        try {
  			out.close();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

	}

	
	  private static void processFriends(FacebookClient facebookClient, List myFriend, boolean recurse, String pre, BufferedWriter out) {
		  List<User> l = myFriend;
		  
	        for (User u : l) {
	            System.out.println(pre + " : " + u.getName() + " " + u.getId());
	            try {
					out.write(pre + " : " + u.getName() + " " + u.getId() + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (recurse) {
	                Connection myMutualFriends = facebookClient.fetchConnection("me/mutualfriends/" + u.getId(), User.class);
	                processFriends(facebookClient, myMutualFriends.getData(), false, "My Mutual Friends with " + u.getName(), out);
	            }
	        }
	    }
	
}
