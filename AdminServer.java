import java.io.IOException;
import java.util.*;

public class AdminServer
{
    private Map<String,User> registedUsers;
    private Map<String,Group> groupSet;
    private int twittNum;
    private int positiveNum;
    private Group root;
    private static final AdminServer instance=new AdminServer();

    public AdminServer()
    {
        registedUsers=new HashMap<String,User>();
        groupSet=new HashMap<String,Group>();
        twittNum=0;
        positiveNum=0;
        root=new Group("Root");
    }

    public static AdminServer getInstance()
    {
        return instance;
    }

    public boolean isIDUsed(String newID)
    {
        return registedUsers.containsKey(newID);
    }
    public void addUser(String newID) throws IOException
    {
        if(!isIDUsed(newID))
        {
            User newUser=new User(newID);
            registedUsers.put(newID,newUser);
            root.addMember(newUser);
        }
        else throw new IOException();//reenter ID
    }
    
    public void addUser(String newID,String groupID) throws IOException
    {
        if(!isIDUsed(newID))
        {
            if(groupSet.containsKey(groupID))
            {
                User newUser=new User(newID);
                registedUsers.put(newID,newUser);
                groupSet.get(groupID).addMember(newUser);
            }
            else throw new IOException();
        }
        else throw new IOException();//reenter ID
    }

    public void addGroup(String newID) throws IOException
    {
        if(!groupSet.containsKey(newID))
        {
            Group newGroup=new Group(newID);
            groupSet.put(newID,newGroup);
            root.addMember(newGroup);
        }
        else throw new IOException();//reenter ID
    }

    public void addGroup(String newID, String groupID) throws IOException
    {
        if(!groupSet.containsKey(newID))
        {
            if(groupSet.containsKey(groupID))
            {
                Group newGroup=new Group(newID);
                groupSet.put(newID,newGroup);
                groupSet.get(groupID).addMember(newGroup);
            }
            else throw new IOException();
        }
        else throw new IOException();//reenter ID
    }

    public User getUser(String inID) throws IOException
    {
        if(registedUsers.containsKey(inID))
        {
            return registedUsers.get(inID);
        }
        else throw new IOException();
    }

    public Group getGroup(String inID)
    {
        return groupSet.get(inID);
    }    
    public int getUserNum()
    {
        return registedUsers.size();
    }

    public int getGroupNum()
    {
        return groupSet.size();
    }

    public void accTwittNum()
    {
        twittNum++;
    }

    public int getTwittNum()
    {
        return twittNum;
    }

    public void accPositive()
    {
        positiveNum++;
    }
    public int getPositive()
    {
        return positiveNum;
    }
}