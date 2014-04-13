package com.kushal.example;

import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.List;

import javax.servlet.http.*;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusRequestInitializer;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;



@SuppressWarnings("serial")
public class MySampleProjectExampleServlet extends HttpServlet {
	
	private static final String API_KEY = "AIzaSyAjqJ2E2MVPBNRK2poki1SJIIliCiagptA";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
		HttpTransport transport = new UrlFetchTransport();
		JsonFactory factory = new JacksonFactory();
		
		Plus plus = new Plus.Builder(transport, factory, null).setApplicationName("")
				.setGoogleClientRequestInitializer(new PlusRequestInitializer(API_KEY)).build();
		
		ActivityFeed feed = plus.activities().search("MKBHD").execute();
		List<Activity> activityList = feed.getItems();		
		resp.setContentType("text/html");
		resp.setStatus(200);
		Writer writer = resp.getWriter();
		writer.write("<ul>");
		for(Activity a : activityList){
			writer.write("<li>"+a.getTitle()+"</li>");
		}
		writer.write("</ul>");
	}
}
