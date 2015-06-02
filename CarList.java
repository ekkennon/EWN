package com.ekkapps.engineworkneeded;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CarList extends ListActivity {
	
	Car car1 = new Car(1,70,216,39,"40 CHEVY BUSINESS COUPE","PRIMERED BODY IS COMPLETE AND SOLID - NEEDS ONLY PAINT","SMOKES SOME AND HAS LOUD EXHAUST THAT COVERS ROD KNOCKS - 'engine needs work'.",2865,2.3,5,0,0,2.94,1.68,1,4.11,"CHEV",4,40,"");
	Car car2 = new Car(2,80,239,59,"41 MERCURY COUPE","BODY IS PARTLY PRIMERED AND MISSING ONLY A FEW TRIM PIECES","RUNS POORLY BUT HAS DUAL EXHAUST AND IS ALMOST 'PRESENTABLE'",3034,2.23,5,2,0,2.82,1.6,1,3.9,"FF",3,41,"");
	Car car3 = new Car(3,75,201,29,"1941 COUPE","NEEDS RIGHT FRONT FENDER & MINOR RUST REPAIR - OTHERWISE DECENT BODY","ENGINE SMOKES AND YOU SUSPECT IT 'needs repair' BUT IT IS QUIET",2934,2.3,5,3,0,2.76,1.55,1,4.11,"MOPAR",2,41,"");
	Car car4 = new Car(4,0,248,29,"42 PONTIAC 'TORPEDO' COUPE","THE CAR IS NO LONGER RUNNING - THE OLD STRAIGHT 8 LAYS DEAD UNDER THE HOOD.","OLDS GRILLE - NO PONTIAC STRIPES - GOOD PAINT WITH FLAMES",3320,2.3,5,2,0,2.94,1.68,1,4.55,"PONT",6,42,"");
	
	TextView carInfo;
	
	Player[] player = new Player[3];
	
	int counter = 1;
	
	AlertDialog.Builder chooseCar;
	
	String carS1;
	String carS2;
	String carS3;
	String carS4;
	String[] money = new String[3];
	String[] values;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carlist);

        carInfo = (TextView) findViewById(R.id.eachcar);
        for (int num = 1; num < 3; num++) {
        	player[num] = new Player();
        	player[num].name = "player" + Integer.toString(num);
        	money[num] = player[num].name + " -- SAVINGS = $ " + player[num].savings;
        }
        fillData();
	}
	
	@Override
    protected void onListItemClick(ListView lv, View v, int pos, long id) {//can use either pos or id
    	super.onListItemClick(lv, v, pos, id);
    	chooseCar = new AlertDialog.Builder(this);
    	if (pos != 1) {
    		String title = "";
    		String message = "";
    		if (pos == 0) {
    			message = car3.desc3;
    			title = car3.desc1;
    		}
    		if (pos == 1) {
    			message = car2.desc3;
    			title = car2.desc1;
    		}
    		if (pos == 2) {
    			message = car3.desc3;
    			title = car3.desc1;
    		}
    		if (pos == 3) {
    			message = car4.desc3;
    			title = car4.desc1;
    		}
    	
    		chooseCar.setMessage(message + ". Is this the car you want?");
    		chooseCar.setTitle(title);
    		chooseCar.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface d, int i) {
    				player[counter].car = new Car(1,70,216,39,"40 CHEVY BUSINESS COUPE","PRIMERED BODY IS COMPLETE AND SOLID - NEEDS ONLY PAINT","SMOKES SOME AND HAS LOUD EXHAUST THAT COVERS ROD KNOCKS - 'engine needs work'.",2865,2.3,5,0,0,2.94,1.68,1,4.11,"CHEV",4,40,"");//from JUNK0CAR.DG1
    				counter++;
    				if (counter == 2) {
    					fillData();
    				}
    				if (counter == 3) {
    					Intent intent = new Intent(CarList.this, CarFix.class);
    					startActivity(intent);
    				}
    			}
    		});
  
    		chooseCar.setNegativeButton("No", new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface d, int i) {
    			}
    		});
    		chooseCar.show();
    	}
    }
	
	public void fillData() {
		carS1 = car1.desc1;
		carS1 = carS1 + ", LISTED AT  " + car1.hp +" HP, " + car1.cid +" CID, WILL COST YOU $" + car1.price;
		carS1 = carS1 + " " + car1.desc2;
		carS2 = car2.desc1;
		carS2 = carS2 + ", LISTED AT  " + car2.hp +" HP, " + car2.cid +" CID, WILL COST YOU $" + car2.price;
		carS2 = carS2 + " " + car2.desc2;
		carS3 = car3.desc1;
		carS3 = carS3 + ", LISTED AT  " + car3.hp +" HP, " + car3.cid +" CID, WILL COST YOU $" + car3.price;
		carS3 = carS3 + " " + car3.desc2;
		carS4 = car4.desc1;
		carS4 = carS4 + ", LISTED AT  " + car4.hp +" HP, " + car4.cid +" CID, WILL COST YOU $" + car4.price;
		carS4 = carS4 + " " + car4.desc2;
		values = new String[] {carS3, money[counter]};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(CarList.this, R.layout.carrow, R.id.eachcar, values);
		setListAdapter(adapter);
	}
}