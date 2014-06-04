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
 * Servlet implementation class TopicsList
 */
@WebServlet("/TopicsList")
public class TopicsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicsList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TopicStorage Storage = new TopicStorage(); // initialize topic storage
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='ISO-8859-1'>");
		out.println("<title>Message Board Topics</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Make a new topic.");
		out.println("<form method='POST' action='/SimpleMessageBoard/NewTopic'>");
		out.println("<p>");
		out.println("Topic Title: <input type='TEXT' name='topicTitle' size='30'>");
		out.println("</p>");
		out.println("<p>");
		out.println("First Message: <input type='TEXT' name='message' size='60'>");
		out.println("</p>");
		out.println("<p>");
		out.println("<input type='SUBMIT' value='Submit'> <input type='reset'>");
		out.println("</p>");
		out.println("</form>");

		out.println("Current Topics");
		
		out.println("<form method='POST' action='/SimpleMessageBoard/ViewTopic'>");
        
        //out.println("<input type='HIDDEN' name='message' value=''>"); // don't add a new message
        
		List<Topic> allTopics = TopicStorage.getAllTopics();
		
		for (int i=0; i<allTopics.size(); i++){ // loop through topics and display
			Topic singleTopic = allTopics.get(i);
			String topicTitle = singleTopic.getTitle();
			if (i == 0){ // automatically check the first topic
				out.println("<input type='RADIO' name='topicTitle' value='"+topicTitle+"' checked>");
			} else{
				out.println("<input type='RADIO' name='topicTitle' value='"+topicTitle+"'>");
			}
			out.println(singleTopic.getTitle()+"<br>");
		}
		
		
		out.println("<p><input type='SUBMIT' value='View Topic'></p>");
        out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
