package com.ekk.drag1;

/*
 * this will eventually be the main screen
 * right now all it does is call IntroActivity
 *
 * 1. after player2 enters name and taps ok then there needs to be a loading... notice
 * 
 * am working from p1x and p1a instead of p1t because p1t starts with newer
 * cars than the other 2 files do. hopefully the other 2 will catch up.
 * 
 * in CarList when creating new Player name sent to constructor is n but should be actual player's name
 * (save name in sqlite db?) ****------***********--------- future
 * 
 * car is chosen in CarList.java then that car is car1 from car class and set variables.
 * this should be done at error line in CarList. cars are not arrays yet so need a conditional.
 * ****------***********--------- future (this has been temporarily fixed)
 * 
 * http://developer.android.com/guide/topics/manifest/application-element.html#debug
 * 
 * http://www.ehow.com/how_12229930_add-end-user-license-agreement-android-app.html
 * 
 * http://code.google.com/p/libgdx/wiki/MeshColorTexture
 * 
 * commented code at bottem was in this activity but has been moved to IntroActivity
 */

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
//import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	//used (means they are used in application):
	Player[] player = new Player[3];//players 1 and 2
		
	EditText mEnterName;
		
	Button mNamed;
		
	TextView mParagraph;
		
	//Context ctx;
		
	int gameLoop = 1;
	int lane;//was L
	int counter;
		
	String cct;//for use in carChooserText
	String TR;// what is this? (should this have been TR2 instead?)
		
	float fl2;// what are these?
	float fl1;//was 200000! instead of a variable
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	        
        begin();
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void begin()
	{
    	Intent i = new Intent(MainActivity.this, IntroActivity.class);
    	startActivity(i);
    }
		     
	
		    /*    for (int i = 1; i < 3; i++) {
		        	player[i] = new Player();
		        }
		        ctx = this;
		        counter = 0;
		        mEnterName = (EditText) findViewById(R.id.tname);
		        mParagraph = (TextView) findViewById(R.id.view1);
		        TR = "";
		        lane = 1; // should change at one point
		        fl2 = 120000;
		        fl1 = 200000;
		        mNamed = (Button) findViewById(R.id.bname);
		        
		        mNamed.setOnClickListener(new View.OnClickListener() {
		        	public void onClick(View v) {
		        		if (counter == 0 || counter == 2) {
		        			if (mEnterName.getText() != null) {
		        				player[gameLoop].name = mEnterName.getText().toString();
		        				player[gameLoop].lane = lane;
		        				counter++;
		        				carChooserText();
		        			}
		        		}
		        		else if (counter == 1 || counter == 3) {
		            		mParagraph.setText("");
		            		mEnterName.setVisibility(View.VISIBLE);
		            		mNamed.setText(getString(R.string.done));
		            		counter++;
		            		firstTimeThru();
		            	}
		        	}
		        });
		    }
		    
		    public void carChooserText() {
		    	if (counter < 4) {
		    		mEnterName.setText("");
		    		cct = "Welcome " + player[gameLoop].name + ". You will start with $250. You can choose a car from among the available junkers and jalopies for the basic vehicle. Everything runs 'as is' but may need work soon or you will blow the engine. Various mechanical parts will be available to you and you may have an opportunity to perform body work that might improve the value of your project. Watch for the phrase 'ENGINE WORK NEEDED' to tell which engines are likely to self-destruct soon. The computer will give you the cost of body and paint repairs if they are needed to attain 'full value'.";
		    		mEnterName.setVisibility(View.GONE);
		    		mParagraph.setText(cct);
		    		mNamed.setText(this.getString(R.string.ok));
		    		mParagraph.setMovementMethod(ScrollingMovementMethod.getInstance());
		    		mainLoop();
		    	}
		    }
		    
		    public void mainLoop() {
		    	gameLoop++; //specifies current player (needs to be reset)
		    }
		    
		    public void firstTimeThru() {
		    	if (counter == 4) {
		    		mEnterName.setVisibility(View.GONE);
		    		mNamed.setVisibility(View.GONE);
		    		Intent i = new Intent(ctx, CarList.class);
		    		startActivity(i);
		    	}
		    }
		    
		    */

	
}