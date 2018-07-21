package Server;
import java.io.IOException;
import java.util.*;


public class User implements GroupInterface, FollowerInterface
{
    private String ID;
    private Set<User> followings;
    private Set<User> followers;
    private List<Twitt> newsFeeds;
    private String groupID;
    private final int MAX_LENGTH=200;
    private final long createTime; 
    private long lastUpdate;
    public User(String in)
    {
        followers=new HashSet<User>();
        followings=new HashSet<User>();
        newsFeeds=new ArrayList<Twitt>();
        ID=in;
        createTime=System.currentTimeMillis();
    }

    public void setID(String in)
    {
        ID=in;
    }
    
    @Override
    public String getID()
    {
        return ID;
    }

    @Override
    public int hashCode()
    {
        return ID.hashCode();
    }
    
    public boolean equals(GroupInterface otherUser)
    {
        return ID.equals(otherUser.getID());
    }

    @Override
    public void setGroup(String in)
    {
        groupID=in;
    }

    @Override
    public String getGroupID()
    {
        return groupID;
    }
    @Override
    public void removeGroup()
    {
        groupID=null;
    } 

    public void attach(User newFollower)
    {
        followers.add(newFollower);
    }

    public void detach(User unfollower)
    {
        followers.remove(unfollower);
    }

    public void follow(User following)throws IOException
    {
        if(!followings.contains(following))
        {
            followings.add(following);
            following.attach(this);
        }else throw new IOException();
    }


    public void unfollow(User unfollowUser)
    {
        if(followings.contains(unfollowUser))
        {
            followings.remove(unfollowUser);
            unfollowUser.detach(this);
        }
    }

    @Override
    public void update(Twitt newTwitt)
    {
        //update newsfeed
        newsFeeds.add(newTwitt);
        lastUpdate=System.currentTimeMillis();
    }

    public void twitt(String content) throws IOException
    {
        //check content length
        if(content.length()<=MAX_LENGTH)
        {
            Twitt newTwitt=new Twitt(ID);
            newTwitt.setContent(content);
            newsFeeds.add(newTwitt);
            lastUpdate=System.currentTimeMillis();
            //update followers
            Iterator<User> iterator=followers.iterator();
            while(iterator.hasNext())
            {
                User follower=iterator.next();
                follower.update(newTwitt);
            }
            AdminServer admin=AdminServer.getInstance();
            admin.accTwittNum();
            admin.setLastUser(ID);
            //positive twitt
            content+=" ";
            int f=0;
            for(int i=0;i<content.length();i++)
            {
                if(content.charAt(i)==' ')
                {
                    String sub=content.substring(f,i);
                    if(sub.equalsIgnoreCase("good")||sub.equalsIgnoreCase("nice")||sub.equalsIgnoreCase("cool"));
                    {
                        admin.accPositive();
                    }
                    f=i+1;
                }
            }
        }
        else throw new IOException();//SizeLimitExceededException
    }

    public void deleteTwitt(int index)
    {
        newsFeeds.remove(index);
        //update followers
        //update server
    }


    public Twitt[] getFeedList()
    {
        if(newsFeeds.isEmpty())
        {
            return null;
        }    
        Twitt[] result=new Twitt[newsFeeds.size()];
        newsFeeds.toArray(result);
        return result;
    }
    public String[] getFollowerArray()
    {
        if(followers.isEmpty())
        {
            String[] result={"Empty"};
            return result;
        }
        String[] result=new String[followers.size()];
        Iterator<User> i=followers.iterator();
        int j=0;
        while(i.hasNext())
            {
                result[j]=i.next().getID();
                j++;
            }
        return result;
    }
    public String[] getFollowingArray()
    {
        if(followings.isEmpty())
        {
            String[] result={"Empty"};
            return result;
        }
        String[] result=new String[followings.size()];
        Iterator<User> i=followings.iterator();
        int j=0;
        while(i.hasNext())
            {
                result[j]=i.next().getID();
                j++;
            }
        return result;
    }
    public long getCreateTime()
    {
        return createTime;
    }
    public long getLastUpdate()
    {
        return lastUpdate;
    }
}
