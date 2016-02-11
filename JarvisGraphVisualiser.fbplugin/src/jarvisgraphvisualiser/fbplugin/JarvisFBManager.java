package jarvisgraphvisualiser.fbplugin;

import jarvis.graph.visualiser.source.INode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

public class JarvisFBManager {
	
	public static Integer BROJ_PRIJATELJA = 20;
	static String ACCESS_TOKEN = null;
	static String USER_ID = null;
	static Integer noFriends = 15;
	FacebookClient facebookClient = null;
	FBNode node = null;
	User me = null;

	public JarvisFBManager(String ACCESS_TOKEN, String USER_ID, String noFriends) 
	{
		if(ACCESS_TOKEN == null || USER_ID == null)
		{
			
		}
		Integer prijatelji = tryParse(noFriends);
		if(prijatelji > 250 || prijatelji < 0)
		{
			this.noFriends = 50;
		}
		else
		{
			this.noFriends = prijatelji;
		}
		
		facebookClient = new DefaultFacebookClient(ACCESS_TOKEN);
		me = facebookClient.fetchObject(USER_ID, com.restfb.types.User.class, Parameter.with("metadata", 1));
	}
	
	private Integer tryParse(String noFriends)
	{
		 try  
	     {  
			 Integer i = Integer.parseInt(noFriends);  
	         return i;  
	      } catch(NumberFormatException nfe)  
	      {  
	          return JarvisFBManager.BROJ_PRIJATELJA;  
	      }  
	}
	
	public FBNode getPrimaryNode()
	{
		if(node != null)
			return node;
		
		node = new FBNode(me.getName(), 
				me.getFirstName(), 
				me.getMiddleName(), 
				me.getLastName(), 
				me.getId(), 
				me.getBirthday(), 
				getPicture(me.getId()), 
				me.getEmail(), 
				me.getHometownName(), 
				getFriends());
		return node;
	}
	
	
	public String getPicture(String id)
	{
		return "http://graph.facebook.com/" + id + "/picture";
	}
	
	public ArrayList<INode> getFriends() 
	{

		Connection<User> myFriends = facebookClient.fetchConnection(
				"me/friends", User.class, Parameter.with("fields",
						"birthday,name,email,about,picture,id,first_name,middle_name,last_name,email,hometown,mutualfriends"));
		
		ArrayList<INode> friends = new ArrayList<INode>();
		FBNode friendNode = null;
		int i = 0;
		for (User friend : myFriends.getData()) 
		{
			if(i == JarvisFBManager.noFriends)
				break;
			i++;
			friendNode = new FBNode(friend.getName(), 
					friend.getFirstName(), 
					friend.getMiddleName(), 
					friend.getLastName(), 
					friend.getId(), 
					friend.getBirthday(), 
					getPicture(friend.getId()), 
					friend.getEmail(), 
					friend.getHometownName(), 
					getMutualFriends(friend.getId())); 
			friends.add(friendNode);
		}
		return friends;
	}
	
	
	/**
	 * Optimizovati metodu
	 * @param id
	 * @return
	 */
	public ArrayList<INode> getMutualFriends(String id)
	{
		ArrayList<INode> listMutualFriends = new ArrayList<INode>();
		
		Connection mutualFriends = facebookClient.fetchConnection(
				"me/mutualfriends/" + id, User.class, Parameter.with("fields",
						"birthday,name,email,about,picture,id,first_name,middle_name,last_name,email,hometown,mutualfriends"));
		List<User> listMutual = mutualFriends.getData();
		for (User mutualFriend : listMutual) {
			System.out.println("Mutual friends :" + mutualFriend.getName());
			listMutualFriends.add(createFBNode(mutualFriend));
		}
		return listMutualFriends;
	}
	
	
	private FBNode createFBNode(User friend)
	{
		FBNode friendNode 
				= new FBNode(friend.getName(), 
						friend.getFirstName(), 
						friend.getMiddleName(), 
						friend.getLastName(), 
						friend.getId(), 
						friend.getBirthday(), 
						getPicture(friend.getId()), 
						friend.getEmail(), 
						friend.getHometownName(), //proveriti
						null); //proveriti i videti da ova poziva mutual friends sa aktualnim korisnikom
		System.out.println(friend.getName() + " " + friend.getHometownName());
		return friendNode;
	}
	
	/**
	 * Zadrzano radi backwards compatibility
	 * @return
	 */
	@Deprecated
	public HashMap<INode, ArrayList<INode>> getConnectedGraph()
	{
		//structure representing connected graph who is going to be deployed to the view.
		HashMap<INode, ArrayList<INode>> connectedGraph = new HashMap<INode, ArrayList<INode>>();
		
		//All Friends
		{
			ArrayList<INode> allFriends = node.friends; //switch to me.getFriends() after testing

			for(INode friend : allFriends)
			{
				FBNode f = (FBNode)friend;
//				System.out.println("My friend : " + f.getName());
//				ArrayList<INode> mutualFriends = f.getFriends();
				ArrayList<INode> mutualFriends = new ArrayList<INode>();
				ArrayList<IDTitlePair> connectedFriends = getConnectedFriends(f.getTitle(), f.getId());
				
				
//				for(String string : connectedFriends)
//				{
//					System.out.println("\tPovezan :" + string);
//				}
				
//				for(String name : connectedFriends)
//				{
//					for(INode nodee : node.getFriends())
//					{
//						if(nodee.getTitle().equalsIgnoreCase(name))
//						{
//							mutualFriends.add(nodee);
//							break;
//						}
//					}
//				}

				
				((FBNode) friend).setFriends(mutualFriends);
				connectedGraph.put(friend, mutualFriends);
			}
		}	
    
		return connectedGraph;
	}
	
	
	public List<INode> getConnectedNodeGraph()
	{
		//structure representing connected graph who is going to be deployed to the view.
		List<INode> connectedNodeGraph = new ArrayList<INode>();
		//All Friends
		{
			ArrayList<INode> allFriends = node.friends;

			for(INode friend : allFriends)
			{
				FBNode f = (FBNode)friend;
				System.out.println("My friend : " + f.getName());
				ArrayList<INode> mutualFriends = new ArrayList<INode>();
				ArrayList<IDTitlePair> connectedFriends = getConnectedFriends(f.getTitle(), f.getId());
				
				
				for(IDTitlePair string : connectedFriends)
				{
					System.out.println("\tPovezan :" + string.getTitle());
				}
				
				for(IDTitlePair pair : connectedFriends)
				{
					for(INode mutualFriendNode : node.getFriends())
					{
						FBNode fbMutualFriendNodePair = (FBNode)mutualFriendNode;
						if(mutualFriendNode.getTitle().equalsIgnoreCase(pair.getTitle()) &&  fbMutualFriendNodePair.getId().equals(pair.getId()))
						{
							mutualFriends.add(mutualFriendNode);
							break;
						}
					}
				}
	
				((FBNode) friend).setFriends(mutualFriends);
				connectedNodeGraph.add(friend);
			}
		}	
		return connectedNodeGraph;
	}
	
	
	
	
	protected ArrayList<IDTitlePair> getConnectedFriends(String friend, String id) {
		ArrayList<IDTitlePair> connectedTables = new ArrayList<IDTitlePair>();

		for(INode friendNode : node.friends)
		{
			FBNode fBfriendNode = (FBNode)friendNode;
			if(!friendNode.getTitle().equalsIgnoreCase(friend) && !fBfriendNode.getId().equals(id))
			{
				for(INode mutualFriendsNode : fBfriendNode.getFriends())
				{
					FBNode fBMutualFriendsNode = (FBNode)mutualFriendsNode;
					if(mutualFriendsNode.getTitle().equals(friend) && fBMutualFriendsNode.getId().equals(id))
					{
						connectedTables.add(new IDTitlePair(fBfriendNode.getId(), fBfriendNode.getTitle()));
					}
				}
			}
		}
		return connectedTables;
	}
	
	/*
	public HashMap<INode, ArrayList<INode>> getConnectedGraph()
	{
		//structure representing connected graph who is going to be deployed to the view.
		HashMap<INode, ArrayList<INode>> connectedGraph = new HashMap<INode, ArrayList<INode>>();
		
		//All Friends
		{
			ArrayList<INode> allFriends = node.friends; //switch to me.getFriends() after testing
			FBNode primary = getPrimaryNode();
//					connectedGraph.put(primary, primary.getFriends());
			for(INode friend : allFriends)
			{
				FBNode f = (FBNode)friend;
				System.out.println("My friend : " + f.getName());
				ArrayList<INode> mutualFriends = f.getFriends();
				for(INode n : mutualFriends)
				{
					FBNode m = (FBNode)n;
					System.out.println("My Mutual Friends with " + f.getName() + " : " + m.getName());
				}
				connectedGraph.put(friend, f.getFriends());
				FBNode f23 = (FBNode)friend;
				
			}
		}	
    
		return connectedGraph;
	}
	*/
}
