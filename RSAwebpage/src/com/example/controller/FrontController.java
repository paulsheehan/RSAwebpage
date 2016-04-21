package com.example.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet(name ="front", urlPatterns={"/FrontController"})
@MultipartConfig

public class FrontController extends HttpServlet{

	public static final long serialVersionUID = 1L;
	public static final String WELCOME_ACTION = "UserIsWelcomed";
	public static final String STRING_INPUT_ACTION = "UserSubmitsString";
	public static final String FILE_INPUT_ACTION = "UserSubmitsFile";
	public static final String STRING_DECRYPT_ACTION = "UserDecryptsString";
	public static final String FILE_DECRYPT_ACTION = "UserDecryptsFile";
	
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest (request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest (request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		String action = request.getParameter("action");
		String forwardToJsp = new String("/mainpage.html");
		RSAController rsa = RSAController.getInstance();
		
		if (action.equalsIgnoreCase(WELCOME_ACTION) )	//When the user presses enter on the first page
		{
			rsa.onWelcome();
			forwardToJsp = new String("/cryptographyPage.jsp");
			//EuclideanController extendedEuch = new EuclideanController(BigInteger.valueOf(40), BigInteger.valueOf(7));
			//System.out.println(extendedEuch.extendedEuch(BigInteger.valueOf(40), BigInteger.valueOf(7), BigInteger.valueOf(0), 0));
		}
		if (action.equalsIgnoreCase(STRING_INPUT_ACTION))	//When the user submits a string
		{
			String input = request.getParameter("message");
			
			rsa.onStringInput(request, input);
			forwardToJsp = "/stringEncrypted.jsp";
		}
		if(action.equalsIgnoreCase(FILE_INPUT_ACTION))	//When the user submits a file
		{
			Part filePart;
			InputStream fileContent;
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] bytes = new byte[16384];
			
			try {
				int i;	//for the loop indexes
				
				filePart = request.getPart("file");
				fileContent = filePart.getInputStream();
				
				try {
					
					while ((i = fileContent.read(bytes, 0, bytes.length)) != -1) 
					{
						buffer.write(bytes, 0, i);
					}
					
					bytes = buffer.toByteArray();
					rsa.onFileInput(request, bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // Retrieves <input type="file" name="file">
			forwardToJsp = "/fileEncrypted.jsp";
		}
		if(action.equalsIgnoreCase(STRING_DECRYPT_ACTION))
		{
			//rsa.onStringDecrypt(request);
			forwardToJsp = "/cryptographyPage.jsp";
		}
		forwardToPage(request, response, forwardToJsp);
	}
	
	private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
	{
		//Get the request dispatcher object and forward the request to the appropriate JSP page...
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
				try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}
}
