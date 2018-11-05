import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import java.util.Scanner;

@WebServlet("/Main")
public class Main extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
  private String head, tail; 
  public Main() {
    super();
    head = "<html><title>Not In The List</title><body><font color = blue><h1>";
    tail = "</h1></font></body></html>";
  }   	
	
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    //This method is complete. Do not change it.  
    String userName = request.getParameter("name");
    String password = request.getParameter("password");
    String newPassword = request.getParameter("newPassword");
    String reEnteredPassword = request.getParameter("reEnterPassword");
    displayCookies(request);
    if(isIn(request, userName, password))
      if(isTheSame(newPassword, reEnteredPassword))
        replace(request, response, userName, password, newPassword);
      else
        isNotTheSame(response, newPassword, reEnteredPassword);
    else
      isNotInTheList(response, userName, password);
    displayCookies(request);
  }
  
  private boolean isIn(HttpServletRequest request, String userName, String password){
	  boolean found = false;
	  Cookie[] cookies = request.getCookies();
	  if (cookies != null && cookies.length > 0) {
		  for (int i = 0; i < cookies.length; i++) {
			  Cookie c = cookies[i];
			  if((c.getName().equals("userName")) && (c.getValue().equals("password")))
			  {
				  found = true;
			  }else {
				  found = false;
			  }
		  }
	  }
    return found; 
  }  

  private boolean isTheSame(String newPassword, String reEnteredPassword){
	  boolean sam = false;
	  if(newPassword.equals(reEnteredPassword))
		  sam = true;
	  else
		  sam = false;
   
    return sam; 

  }
  
  private void replace(HttpServletRequest request, HttpServletResponse response, 
    String userName, String password, String newPassword){
	  Cookie[] cookies = request.getCookies();
	  if (cookies != null && cookies.length > 0) {
	  for (int i = 0; i < cookies.length; i++) {
		  Cookie c = cookies[i];
		  if((c.getName().equals("userName")) && (c.getValue().equals("password"))) {
			newPassword.equals(password);  
		  }
	  	}
	  }
}

  
  
  private void isNotInTheList(HttpServletResponse response, String userName, String password) throws IOException{
	  
	  String content;
	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  content = userName + " with the password: " + password + " is not in the list"; 
	  out.println("<html>\n"+
			  "<head><title>Processing cookies</titile></head>\n"+
			  "<body>>\n"+ content +"</body></html>"
			  );
  
}

  private void isNotTheSame(HttpServletResponse response, String newPassword, 
    String reEnteredPassword) throws IOException{
	  String content;
	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  content = newPassword + " must be the same as " + reEnteredPassword; 
	  out.println("<html>\n"+
			  "<head><title>Processing cookies</titile></head>\n"+
			  "<body>>\n"+ content +"</body></html>"
			  );
  }  
  
  private void success(HttpServletResponse response) throws IOException{
	  String content;
	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  content = "The password is changed successfully."; 
	  out.println("<html>\n"+
			  "<head><title>Processing cookies</titile></head>\n"+
			  "<body>>\n"+ content +"</body></html>"
			  );
  }  
  
  private void displayCookies(HttpServletRequest request){
    //This method is complete. Do not change it.
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0)
      for(int i=0; i<cookies.length; i++)
        System.out.println(cookies[i].getName() + ", " + cookies[i].getValue());
      System.out.println("********************************************************");  
  }
}

