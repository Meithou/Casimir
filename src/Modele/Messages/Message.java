package Modele.Messages;

import java.io.Serializable;
import java.util.Date;
import Modele.User.*;

public class Message implements Serializable{

    private final String message;
    private final Date stamp;
    private final User sender;

    public Message(String message, User sender)
    {
        this.message = message;
        this.stamp = new Date();
        this.sender = sender;

    }

    public String getMessage()
    {
        return this.message;

    }

    public Date getStamp()
    {
        return this.stamp;

    }

    public User getSender() {
        return sender;
    }
}
