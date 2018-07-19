package Server;
public class Twitt
{
    private String ID;
    private String content;

    public Twitt(String postID)
    {
        ID=postID;
    }

    public void setID(String postID)
    {
        ID=postID;
    }
    public String getID()
    {
        return ID;
    }

    public void setContent(String inContent)
    {
        content=inContent;
    }
    public String getContent()
    {
        return content;
    }
}