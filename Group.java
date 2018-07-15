public class Group implements GroupInterface
{
    private String ID;
    private String groupID;
    private Set<GroupInterface> groupMem;

    public Group(String in)
    {
        ID=in;
        groupMem=new HashSet<GroupInterface>();
    }
    
    public void addMember(GroupInterface newMember)
    {
        if(newMember.getGroupID()==null)
        {
            groupMem.add(newMember);
            newMember.setGroup(ID);
        }
    }

    @override
    public void setGroup(String newGroup)
    {
        groupID=newGroup;
    }

    @override
    public void removeGroup()
    {
        groupID=null;
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

    public Iterator<GroupInterface> getMemIterator()
    {
        return groupMem.iterator();
    }
}