package com.messageboard.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.messageboard.classes.Topic;
import com.messageboard.classes.TopicStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ViewTopic
 */
@WebServlet("/ViewTopic")
public class ViewTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTopic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get inputs from text boxes
        String topicTitle = request.getParameter("topicTitle");
        String message = request.getParameter("message");
        String topicIndexStr = request.getParameter("topicIndex");
        if (topicIndexStr == null){ // no topic index was entered
        	int topicIndex = TopicStorage.findTopicIndex(topicTitle);
        	if (topicIndex ==-1){ // the topic does not exist
        		 PrintWriter out = response.getWriter();
        			out.println("<!DOCTYPE html>");
        	        out.println("<html><head>");
        	        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        	        out.println("<title>" + topicTitle + "</title></head>");
        	        out.println("Topic does not exist.");
        	        out.println("<p>Return to <a href='/SimpleMessageBoard/TopicsList'>Topics List</a>");
        	        out.close();
        	        return;
        	}else{
        		topicIndexStr = String.valueOf(topicIndex);
        	}
        }
        
        int topicIndex = Integer.parseInt(topicIndexStr); // convert to int
        
        // Make and store Topic Object
        Topic revisedTopic = TopicStorage.getTopic(topicIndex);
        if (message != null){ // A message was not added
        	revisedTopic.addMessage(message);
        }
        TopicStorage.storeExistingTopic(topicIndex, revisedTopic);
        
        PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>" + topicTitle + "</title></head>");
        out.println("<body>");
        out.println("<h1>" + topicTitle + "</h1>");
        
        // View messages in Topic
        List<Topic.Message> messageList = revisedTopic.getMessages();
        for (int i=0; i<messageList.size(); i++){ // loop through messages in this topic and display
        	String singleMessage = messageList.get(i).getString();
        	out.println("<p>");
        	out.println(singleMessage);
        	out.println("</p>");
        }
        
        // Post a new message to this topic
     // Post a new message to this topic
        out.println("<form method='POST' action='/SimpleMessageBoard/ViewTopic'>");
        out.println("<p>New Message: <input type='TEXT' name='message' size='60'></p>");
        out.println("<input type='HIDDEN' name='topicTitle' value='"+topicTitle+"'>");
        out.println("<input type='HIDDEN' name='topicIndex' value='"+String.valueOf(topicIndex)+"'>");
        out.println("<p><input type='SUBMIT' value='Submit'></p>");
        out.println("</form>");
        
        out.println("<p>Return to <a href='/SimpleMessageBoard/TopicsList'>Topics List</a>");
        out.close();
	}

}
