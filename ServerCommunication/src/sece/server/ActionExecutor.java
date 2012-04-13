package sece.server;
import android.content.Context;
import android.os.Vibrator;

public class ActionExecutor {
	
	Context context;
	String action;
	
	ActionExecutor(String action, Context context){
		
		this.context = context;
		this.action = action;
		doAction();
	}
	
	public boolean doAction(){
		if(action.contains("vibrate")){
			return vibrate();
		}
		else return false;
	}
	

	public boolean vibrate(){
		
		Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
		v.vibrate(300);
		
		return true;
		
	}
	
}
