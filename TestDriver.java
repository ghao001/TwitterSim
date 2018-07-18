import java.io.IOException;

public class TestDriver
{
    public static void main(String[] args) throws IOException
    {
        User user=new User("123");
        
        
        User user2=new User("344");
        user.follow(user2);
        String[] s=user.getFollowingArray();
        System.out.println(user.getID()+s[0]);
    }    
}