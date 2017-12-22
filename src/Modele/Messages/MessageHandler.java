package Modele.Messages;

import java.util.ArrayList;
import java.util.HashMap;


public class MessageHandler {
    private HashMap<String,ArrayList<Message>> conversations;

    public MessageHandler(){
        conversations = new HashMap<>();

    }

    public void start_conversation(String ipAddress) {
        // Create new conversation, a conversation is created at the first message
        conversations.put(ipAddress,new ArrayList<Message>());
    }

    public void end_conversation(String ipAddress) {
        if(conversations.containsKey(ipAddress)) {
            create_history(ipAddress);
            conversations.remove(ipAddress);
        }
    }
    private String create_file_name(Message message){
        return "History-"+message.getSender().getNickname()+"-"+message.getStamp();
    }
    private String create_header(ArrayList<Message> conversation){
        Message fm = conversation.get(0);
        return "User : "+fm.getSender().getNickname()+";"+"Ip : "+fm.getSender().getIpAddress()+";"+"Start Time : "+fm.getStamp();
    }
    private String format_conversation(ArrayList<Message> conversation){
        String formated = "";
        for(Message message : conversation )
            formated += message.getStamp()+";"+message.getSender().getNickname()+";"+message.getMessage()+"\n";
        return formated;
    }
    private void create_history(String ipAddress){
        // TO DO
        if(conversations.containsKey(ipAddress)) {
            ArrayList<Message> conversation = conversations.get(ipAddress);

            String history = create_header(conversation);
            history += format_conversation(conversation);

            Message message = conversation.get(0);
            HistoryWriter.writeFile(create_file_name(message),history);
        }
    }

    public void addMessage(String ipAddress, Message message)
    {
        if(!conversations.containsKey(ipAddress))
            start_conversation(ipAddress);
        conversations.get(ipAddress).add(message);
        // Add event for Controller
    }
}
