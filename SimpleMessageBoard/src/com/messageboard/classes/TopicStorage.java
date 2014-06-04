package com.messageboard.classes;

import java.util.List;
import java.util.ArrayList;

public class TopicStorage {
	// List of Topics to Save
	private static List<Topic> BoardTopics;
	
	public TopicStorage(){
		if (BoardTopics == null){
			BoardTopics = new ArrayList<Topic>();
		}
	}
	
	// retrieves all topic
	public static List<Topic> getAllTopics() {
		return BoardTopics;
	}
			
	// stores the topic and returns the index
	public static int storeNewTopic(Topic newTopic){
		BoardTopics.add(newTopic);
		return BoardTopics.size()-1;
	}
	// stores the existing topic
	public static void storeExistingTopic(int index, Topic modifiedTopic){
		BoardTopics.set(index, modifiedTopic);
	}

	// retrieves the existing topic
	public static Topic getTopic(int index) {
		Topic retrievedTopic = BoardTopics.get(index);
		return retrievedTopic;
	}
	// retrieves all topic
	public static List<Topic> getAllTopics(int index) {
		return BoardTopics;
	}
		
	public static int findTopicIndex(String topicTitle){
		for (int i=0; i<BoardTopics.size(); i++){ // loop through topics and display
			if(BoardTopics.get(i).getTitle().equals(topicTitle)){ // topic exists at index i
				return i;
			}
		}
		return -1; // if the topic was not found
	}
}
