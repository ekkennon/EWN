package com.ekk.drag1;

/*
 * 1 idea to fix this is to change the for loop to include fillData() and the click listener
 *  ^^^^^ seems wrong. added if (counter == 2)
 *  check if counter == 2 works
 * 
 * Steps in this file:
 * 1. player1 info displays
 * 2. player1 selects a car
 * 	a. if player1 says yes then move to player2
 * 	b. if player1 says no then keep player1 info displayed and keep counter at 1
 * 3. player2 info displays
 * 4. player2 selects a car
 * 	a. if player2 says yes then go to next activity
 * 	b. if player2 says no then keep player2 info displayed and keep counter at 2
 * 
 * this is not close to what is currently happening
 * 
 * if player says no then counter should not increase (many problems with this file)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
//import android.app.AlertDialog;
//import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
//import android.content.DialogInterface;
//import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.TextView;

/*
 * http://www.vogella.com/articles/AndroidListView/article.html
 * 
 * http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
 * 
 * http://sourceforge.net/projects/sqlitebrowser/
 * 
 * http://androidforums.com/application-development/36289-sqlite-records-pre-defined.html
 */

public class CarList extends Activity 
{
	Car car3 = new Car(3,75,201,29,"1941 COUPE"/*"41 PLYMOUTH COUPE"*/,"NEEDS RIGHT FRONT FENDER & MINOR RUST REPAIR - OTHERWISE DECENT BODY","ENGINE SMOKES AND YOU SUSPECT IT 'needs repair' BUT IT IS QUIET",2934,2.3,5,3,0,2.76,1.55,1,4.11,"MOPAR",2,41,"");
	String carS3;
	String[] money = new String[3];
	String[] values;
	int counter = 1;
	Player[] player = new Player[3];//players 1 and 2
	AlertDialog.Builder chooseCar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.carlist);
	    
	    final ListView listview = (ListView) findViewById(R.id.listview);
	    
	    for (int num = 1; num < 3; num++) {
        	player[num] = new Player();
        	player[num].setName("player" + Integer.toString(num));
        	money[num] = player[num].getName() + " -- SAVINGS = $ " + player[num].getSavings();
        }
	    
	    carS3 = car3.getDesc1();
		carS3 = carS3 + ", LISTED AT  " + car3.getHp() +" HP, " + car3.getCid() +" CID, WILL COST YOU $" + car3.getPrice();
		carS3 = carS3 + " " + car3.getDesc2();
		
		values = new String[] {carS3, money[counter]};
	    
	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }
	    
	    final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
	    
	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		      @Override
		      public void onItemClick(AdapterView<?> parent, final View view, int pos, long id) {
		    	  chooseCar = new AlertDialog.Builder(CarList.this);
		      	if (pos != 1) {
		      		String title = "";
		      		String message = "";
		      		if (pos == 0) {
		      			message = car3.desc3;
		      			title = car3.desc1;
		      		}
		      		if (pos == 2) {
		      			message = car3.desc3;
		      			title = car3.desc1;
		      		}
		      	
		      		chooseCar.setMessage(message + ". Is this the car you want?");
		      		chooseCar.setTitle(title);
		      		chooseCar.setPositiveButton("Yes", new DialogInterface.OnClickListener() {// ************** if player clicked "yes"
		    			public void onClick(DialogInterface d, int i) {
		    				//Log.i("car", Integer.toString(p));
		    				player[counter].car = new Car(1,70,216,39,"40 CHEVY BUSINESS COUPE","PRIMERED BODY IS COMPLETE AND SOLID - NEEDS ONLY PAINT","SMOKES SOME AND HAS LOUD EXHAUST THAT COVERS ROD KNOCKS - 'engine needs work'.",2865,2.3,5,0,0,2.94,1.68,1,4.11,"CHEV",4,40,"");//from JUNK0CAR.DG1
		    				counter++;
		    				if (counter == 2) {
		    					//fillData();
		    				}
		    				if (counter == 3) {
		    					//both players have a car so go to next activity
		    					Intent intent = new Intent(CarList.this, CarFix.class);
		    					startActivity(intent);
		    				}
		    			}
		    		});
		  
		    		chooseCar.setNegativeButton("No", new DialogInterface.OnClickListener() {
		    			public void onClick(DialogInterface d, int i) {
		    				//nothing is supposed to happen here
		    				//maybe stuff should happen here
		    			}
		    		});
		    		chooseCar.show();
		    	}
		      }

		    });
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    getActionBar().setSubtitle("Long press to start selection");
	}
	
	private class StableArrayAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }

	  }
}