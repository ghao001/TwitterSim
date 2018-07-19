package Server;
import java.util.*;
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

    @Override
    public void setGroup(String newGroup)
    {
        groupID=newGroup;
    }

    @Override
    public void removeGroup()
    {
        groupID=null;
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
    
    @Override
    public String getGroupID()
    {
        return groupID;
    }

    public boolean equals(GroupInterface otherUser)
    {
        return ID.equals(otherUser.getID());
    }

    public Iterator<GroupInterface> getMemIterator()
    {
        return groupMem.iterator();
    }
}