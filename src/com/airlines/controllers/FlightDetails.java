package com.airlines.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightLocal_ejb13;
import com.airline.service.FlightServiceStatelessBean;

/**
 * Servlet implementation class FlightDetails
 */
@WebServlet("/FlightDetails")
public class FlightDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FlightLocal_ejb13 fs = null;
	//
	private FlightLocal_ejb13 fStateful = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		pw.println("The flights details servlet has been called...");
		//
		try {
			Context context = new InitialContext();
			Object fsObject = context.lookup("java:glogal/ejb10/flightStateless!com.airline.service.FlightLocal_ejb10");
			this.fs = (FlightLocal_ejb13) fsObject;
			Object fStatefulObject = context.lookup("java:glogal/ejb10/flightStateful!com.airline.service.FlightLocal_ejb10");
			this.fStateful = (FlightLocal_ejb13) fStatefulObject;
		} catch (NamingException e) {
			System.out.println("Naming exception has occured in the lookup of our FlightService EJBs");
			e.printStackTrace();
		}
		//
		pw.println("Flight Details: " + fs.getFrom() + " " + fs.getTo());
		//
		pw.println("Flight Details: " + fStateful.getFrom() + " " + fStateful.getTo());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
