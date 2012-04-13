package sece.server;
import java.util.Scanner;

public class CommandParser {
	
	String input;
	Scanner parser;
	String httpMethod;
	String action;
	String httpProtocol;
	
	CommandParser(String input){
		this.input = input;
		parser = new Scanner(input).useDelimiter("\\s \\s|\\s?\\s");
	}

	public void parseCommand(){
		
		httpMethod = parser.next();
		action = parser.next();
		httpProtocol = parser.next();
		
	}
	
	public String getAction(){
		return action;
	}
	
	public String getProtocol(){
		return httpProtocol;
	}
	
	public String getMethod(){
		return httpMethod;
	}
	
}
