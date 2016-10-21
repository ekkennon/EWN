package com.ekk.drag1;

/*
 * eventually the EditText should be put back here (code should be in MainActivity.java)
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends Activity
{
	
	//used:
	Player[] player = new Player[3];//players 1 and 2
			
	Button mNamed;
			
	TextView mParagraph;
			
	int gameLoop = 1;
	int lane;//was L
	int counter;
			
	String cct;//for use in carChooserText
	String TR;// what is this? (should this have been TR2 instead?)
			
	float fl2;// what are these?
	float fl1;//was 200000! instead of a variable
			
	//not used:
	int VLX;
	int NOX;
	int VLCX;// something to do with vehicle?
	int PR;
	int YRX;//a year (of vehicle?)
	int NCB; //***line 10810
	int SNB; //***line 10810
	int SNT; //***line 10810
	int SNC; //***line 10810
	int L5W;
	int playerLane;//was PL
	int WIN2; //winner?
	
	double MD;
	double VLPCT;
	double HIS;
	double L1;
	double L2;
	double L3;
	double L4;
	double L5;//if this = 0 from the car file then the rear end is out
	double weight;//originally called O (capital letter)
	double rpm;//not sure if this really exists in original file or if i created it because R is aka RPM
	double XZ1;
	
	String Tstring;
	String DESC1;
	String DESC2;
	String DESC3;
	String I;
	String name;//to get name of player (not needed because player[].name exists)
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        
        for (int i = 1; i < 3; i++) {
        	player[i] = new Player();
        	player[i].setName("player" + Integer.toString(i));
        }
        
        counter = 0;
        mParagraph = (TextView) findViewById(R.id.view1);
        TR = "";
        lane = 1; // should change at one point
        fl2 = 120000;
        fl1 = 200000;
        mNamed = (Button) findViewById(R.id.bname);
        carChooserText();
        mNamed.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
            	mParagraph.setText("");
            	mNamed.setText(getString(R.string.done));
            	counter++;
            	firstTimeThru();
            }
        });
    }
    
    public void carChooserText() {
    	if (counter < 2) {
    		cct = "Welcome " + player[gameLoop].getName() + ". You will start with $250. You can choose a car from among the available junkers and jalopies for the basic vehicle. Everything runs 'as is' but may need work soon or you will blow the engine. Various mechanical parts will be available to you and you may have an opportunity to perform body work that might improve the value of your project. Watch for the phrase 'ENGINE WORK NEEDED' to tell which engines are likely to self-destruct soon. The computer will give you the cost of body and paint repairs if they are needed to attain 'full value'.";
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
    	if (counter == 2) {
    		mNamed.setVisibility(View.GONE);
    		Intent i = new Intent(IntroActivity.this, CarList.class);
    		startActivity(i);
    	}
    	else {
    		carChooserText();
    	}
    }
}