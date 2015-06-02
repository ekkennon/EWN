package com.ekkapps.engineworkneeded;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	Player[] player = new Player[3];
	
	EditText mEnterName;
	
	Button mNamed;
		
	TextView mParagraph;
	
	int gameLoop = 1;
	int lane;
	int counter;
	
	String cct;
	String TR;
	
	float fl2;
	float fl1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		begin();
	}
	
	public void begin() {
    	Intent i = new Intent(MainActivity.this, IntroActivity.class);
    	startActivity(i);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
