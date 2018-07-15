public class AdminServer
{
    private Set<User> registedUsers;
    private Set<Group> groupSet;
    private int twittNum;
    private int positiveNum;
    private Group root;
    private static final AdminServer instance=new AdminServer();

    public AdminServer()
    {
        registedUsers=new HashSet<User>();
        groupSet=new HashSet<Group>();
        twittNum=0;
        positiveNum=0;
        root=new Group("Root");
    }

    public void getInstance()
    {
        return instance;
    }

    public boolean isIDUsed(String newID)
    {
        return registedUsers.contains(newID);
    }
    public void addUser(String newID)
    {
        if(!isIDUsed(newID))
        {
            registedUsers.add(new User(newID));
            root.addMember(new User(newID))
        }
        else //reenter ID
    }
    
    public void addGroup(newID)
    {
        if(!groupSet.contains(newID))
        {
            groupSet.add(new Group(newID));
            root.addMember(new Group(newID));
        }
        else //reenter ID
    }

    public User getUser(inID)
    {
        return registedUsers.get(inID);
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