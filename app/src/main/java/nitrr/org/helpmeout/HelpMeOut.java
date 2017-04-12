package nitrr.org.helpmeout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HelpMeOut extends AppCompatActivity {
    Activity act = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer t1 = new Timer();
        TimerTask tt = new TimerTask() {
            public void run() {
                Intent i1 = new Intent(act, HelpMeOutMain.class);
                startActivity(i1);
                act.finish();
            }
        };
        //deleteDatabase("mydb");
        SQLiteDatabase db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
        String sql = "create table if not exists phone_book( id INTEGER primary key, " +
                " user_name varchar[50], phone_no varchar[15])";
        db.execSQL(sql);
        db.close();
        createPoliceStation();
        if(isExist()==false){
            populatePoliceStation();
        }
        t1.schedule(tt, 1500);
    }
    private void createPoliceStation(){
        SQLiteDatabase db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
        String sql = "create table if not exists police_station( id INTEGER primary key, " +
                " contact_no varchar[50], lat varchar[30], lng varchar[30])";
        db.execSQL(sql);
        db.close();
        Log.d(MySettings.PROJ_NAME, "station created");
    }
    private boolean isExist(){
        boolean res = true;
        try {
            SQLiteDatabase db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
            String sql = "select * from police_station";
            String sa[] = {};
            Cursor c1 = db.rawQuery(sql,sa);
            if(c1.getCount()==0){
                res=false;
            }
            db.close();
            Log.d(MySettings.PROJ_NAME, "res is "+ res + c1.getCount());
        }
        catch (Exception e){
            Log.e(MySettings.PROJ_NAME, "error!", e);
        }
        return res;
    }
    private void populatePoliceStation(){
        Log.d(MySettings.PROJ_NAME, "popu called");
        insertPoliceStation("7748906668", "21.2348995", "81.6418228");
        insertPoliceStation("9893267308", "21.2348544", "81.639982");
    }
    private void insertPoliceStation(String cn, String lat, String lng){
        SQLiteDatabase db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
        String sql = "insert into police_station( contact_no , lat, lng) values (?, ?, ?)";
        Object oa[] = new Object[3];
        oa[0]=cn;
        oa[1]=lat;
        oa[2]=lng;
        db.execSQL(sql, oa);
        db.close();
        Log.d(MySettings.PROJ_NAME, "populated! " + lat + " : " + lng);
    }
}
