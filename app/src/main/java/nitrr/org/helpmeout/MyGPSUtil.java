package nitrr.org.helpmeout;

import android.content.Context;
import android.location.Location;
import android.util.Log;

public class MyGPSUtil {
	public static String tag = "MyGPSUtil";	
	
	public static double getArialFromMe(Context ctx, String lat2, String long2){
		Location L1 = MyService.gpsTrk.location;
		Location L2 = new Location("");
		Log.d(MySettings.PROJ_NAME, "lat is " + lat2);
		L2.setLatitude(Double.parseDouble(lat2));
		L2.setLongitude(Double.parseDouble(long2));
		double dist = L1.distanceTo(L2);
/*
		String disStr = dist + " Meters";
		if(dist>500){
			dist = dist/1000;
			disStr = dist + " KM's";
		}
		Log.d("afr", "Areal Diatance :" + dist+"");		
*/
		return dist;
	}
}
