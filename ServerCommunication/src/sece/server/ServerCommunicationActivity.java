package sece.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

public class ServerCommunicationActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		char[] input = null;
		DataOutputStream outToServer = null;
		Socket s = null;
		InputStream fromServer = null;
		private static final String ipAddress = "bloo-blah";

		try {
			s = new Socket(ipAddress, 1234);
			outToServer = new DataOutputStream(s.getOutputStream());
			outToServer.writeBytes("Hello!\n");
			fromServer = s.getInputStream();

			input = new char[200];


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while(s != null){
			try {
				InputStreamReader server = new InputStreamReader(fromServer);
				server.read(input);
				String inputString = new String(input);
				
				CommandParser c = new CommandParser(inputString);
				c.parseCommand();
				
				ActionExecutor a = new ActionExecutor(c.getAction(),this);
				
				boolean actionStatus = a.doAction();
				
				if(actionStatus){
					
					Log.d("GOOD", "200 OK");
					outToServer.writeBytes("200 OK\n");
				}
				else if(c.getMethod().contains("quit")){
					s.close();
					break;
				}
				else{
					outToServer.writeBytes("404 NOT FOUND\n");
				}
				input = new char[200];
				
			}
			catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}


}