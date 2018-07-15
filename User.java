public class User implements GroupInterface, FollowerInterface
{
    private String ID;
    private Set<User> followings
    private Set<User> followers
    private List<Twitt> newsFeeds
    private String groupID;
    private final int MAX_LENGTH=200;

    public User(String in)
    {
        followers=new HashSet<User>();
        followings=new HAshSet<User>();
        newsFeeds=new ArrayList<Twitt>();
        //check ID duplicate
        //upload to server
    }

    public void setID(String in)
    {
        //check ID duplicate
        //upload to server
    }
    
    @override
    public String getID()
    {
        return ID;
    }

    @override
    public int hashCode()
    {
        return ID.hashCode();
    }

    @override
    public boolean equals(GroupInterface otherUser)
    {
        return ID.equals(otherUser.getID());
    }

    @override
    public void setGroup(String in)
    {
        groupID=in;
    }

    @override
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

    public void follow(User following)
    {
        if(!followings.contains(following))
        {
            followings.add(following);
            following.attach(this);
        }
    }


    public void unfollow(User unfollowUser)
    {
        if(followings.contains(unfollowUser))
        {
            followings.remove(unfollowUser);
            unfollowUser.detach(this);
        }
    }

    @override
    public void update(Twitt newTwitt)
    {
        //update newsfeed
        newsFeeds.add(newTwitt)
    }

    public void twitt(String content)
    {
        //check content length
        if(content.length()<=MAX_LENGTH)
        {
            Twitt newTwitt=new Twitt(ID);
            newTwitt.setContent(content);
            newsFeeds.add(newTwitt);
            //update followers
            Iterator<FollowerInterface> iterator=followers.iterator();
            while(iterator.hasNest())
            {
                FollowerInterface follower=iterator.next();
                follower.update(newTwitt)
            }
            AdminServer admin=AdminServer.getInstance();
            admin.accTwittNum();
            //positive twitt
        }
        else //SizeLimitExceededException
        
    }

    public void deleteTwitt(int index)
    {
        newsFeeds.remove(index);
        //update followers
        //update server
    }

    public void notify()
    {
        //iterate followers and call update()
    }

    public List<Twitt> getFeedList()
    {
        List<Twitt> result=newsFeeds.clone();
        return result;
    }
    public Iterator getFollowerIterator()
    {
        return followers.iterator();
    }
    public Iterator getFollowingIterator()
    {
        return followings.iterator();
    }
}