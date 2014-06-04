package com.messageboard.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class MessageBoard
 */
@WebServlet("/NewTopic")
public class NewTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTopic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try{
			out.println("<!DOCTYPE html>");
	         out.println("<html><head>");
	         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	         String title = "Chelley's Simple Message Board";
	         out.println("<title>" + title + "</title></head>");
	         out.println("<body>");
	         out.println("<h1>" + title + "</h1>");
	         
	         
	         // Hyperlink to refresh this page
	         out.println("<a href='" + request.getRequestURI() + "'>Reload</a>");
	         out.println("</body></html>");
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get inputs from text boxes
        String topicTitle = request.getParameter("topicTitle");
        String message = request.getParameter("message");
        
        
        
        // Make the new Topic Object
        Topic newTopic = new Topic(topicTitle, message);
        
        PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>" + topicTitle + "</title></head>");
        out.println("<body>");
        out.println("<h1>" + topicTitle + "</h1>");
        
        // don't allow duplicates
        if (TopicStorage.findTopicIndex(topicTitle)==-1){ // the topic does not exist
        	
        //store new Topic Object
        int topicIndex = TopicStorage.storeNewTopic(newTopic);
        
        
        // View messages in Topic
        List<Topic.Message> messageList = newTopic.getMessages();
        for (int i=0; i<messageList.size(); i++){ // loop through messages in this topic and display
        	String singleMessage = messageList.get(i).getString();
        	out.println("<p>");
        	out.println(singleMessage);
        	out.println("</p>");
        }
        
        // Post a new message to this topic
        out.println("<form method='POST' action='/SimpleMessageBoard/ViewTopic'>");
        out.println("<p>New Message: <input type='TEXT' name='message' size='60'></p>");
        out.println("<input type='HIDDEN' name='topicTitle' value='"+topicTitle+"'>");
        out.println("<input type='HIDDEN' name='topicIndex' value='"+String.valueOf(topicIndex)+"'>");
        out.println("<p><input type='SUBMIT' value='Submit'></p>");
        out.println("</form>");
        
        } else{ // the topic already exists
        	out.println("<p>The Topic Already exists.</p>");
        }
        
        out.println("<p>Return to <a href='/SimpleMessageBoard/TopicsList'>Topics List</a></p>");
        out.close();
        
	}

}
