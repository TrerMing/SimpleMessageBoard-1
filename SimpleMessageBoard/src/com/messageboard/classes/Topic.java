package com.messageboard.classes;

import java.util.ArrayList;
import java.util.List;

public class Topic{
	private String title;
    private List<Message> messages;
    
//    public Topic(){
//    	this.title = "";
//    	String rootMessage = "";
//    	this.messages = new ArrayList<Message>();
//    	Message firstMessage = new Message(rootMessage);
//    	this.messages.add(firstMessage);
//    }
    public Topic(String title, String rootMessage){
    	// If no title or message was entered, set defaults
    	if (title == null) {
    		title = "No Title";
    	}
    	if (rootMessage == null) {
    		rootMessage = "";
    	}
    			
    	this.title = title;
        this.messages = new ArrayList<Message>();
        Message firstMessage = new Message(rootMessage);
        this.messages.add(firstMessage);
    }
    
    public String getTitle(){
    	return title;
    }
    public List<Message> getMessages(){
    	return messages;
    }
    public void addMessage(String newMessageString){
    	Message newMessage = new Message(newMessageString);
    	messages.add(newMessage);
    }
    
    public static class Message{
        private String messageString;
        public Message(String messageString){
        	this.messageString = messageString;
        }
        public String getString(){
            return messageString;
        }
        
    }
}
