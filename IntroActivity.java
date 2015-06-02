package com.ekkapps.engineworkneeded;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends Activity {
	
	Player[] player = new Player[3];
			
	Button mNamed;
			
	TextView mParagraph;
			
	int gameLoop = 1;
	int lane;
	int counter;
			
	String cct;
	String TR;
			
	float fl2;
	float fl1;
			
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        
        for (int i = 1; i < 3; i++) {
        	player[i] = new Player();
        	player[i].name = "player" + Integer.toString(i);
        }
        
        counter = 0;
        mParagraph = (TextView) findViewById(R.id.view1);
        TR = "";
        lane = 1;
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
    		cct = "Welcome " + player[gameLoop].name + ". You will start with $250. You can choose a car from among the available junkers and jalopies for the basic vehicle. Everything runs 'as is' but may need work soon or you will blow the engine. Various mechanical parts will be available to you and you may have an opportunity to perform body work that might improve the value of your project. Watch for the phrase 'ENGINE WORK NEEDED' to tell which engines are likely to self-destruct soon. The computer will give you the cost of body and paint repairs if they are needed to attain 'full value'.";
    		mParagraph.setText(cct);
    		mNamed.setText(this.getString(R.string.ok));
    		mParagraph.setMovementMethod(ScrollingMovementMethod.getInstance());
    		mainLoop();
    	}
    }
    
    public void mainLoop() {
    	gameLoop++;
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