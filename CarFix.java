package com.ekkapps.engineworkneeded;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CarFix extends Activity {
	
	int FINYR = 54;
	int gameLoop = 1;
	int WORK1;
	int LL;
	int CL;
	
	Player[] player = new Player[3];
	
	double SP1;
	double SP3;
	double rCost;
	
	TextView tv;
	TextView sv;
	TextView ci;
	
	String[] money = new String[3];
	String SM;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carfix);
        
        tv  = (TextView) findViewById(R.id.view1);
        sv = (TextView) findViewById(R.id.savings);
        ci = (TextView) findViewById(R.id.carinfo);
        for (int num = 1; num < 3; num++) {
        	player[num] = new Player();
        	player[num].name = "player" + Integer.toString(num);
        	money[num] = player[num].name + " -- SAVINGS = $ " + player[num].savings;
        	player[num].car = new Car(3,75,201,29,"41 PLYMOUTH COUPE","NEEDS RIGHT FRONT FENDER & MINOR RUST REPAIR - OTHERWISE DECENT BODY","ENGINE SMOKES AND YOU SUSPECT IT 'needs repair' BUT IT IS QUIET",2934,2.3,5,3,0,2.76,1.55,1,4.11,"MOPAR",2,41,"");
        	mainLoop();
        }
        Intent i = new Intent(CarFix.this, RaceActivity.class);
		startActivity(i);
	}
	
	public void mainLoop() {
		if (player[gameLoop].car.price > player[gameLoop].savings) {
			if (player[gameLoop].car.year < FINYR) {
				tv.setText("THE CAR YOU HAVE CHOSEN IS TOO OLD TO BE FINANCED AND YOU CANNOT AFFORD TO PAY CASH FOR IT.");
			}
		}
		else {
			player[gameLoop].DSCR = player[gameLoop].car.desc1;
			player[gameLoop].savings -= player[gameLoop].car.price;
			player[gameLoop].VLCD = player[gameLoop].car.repairsNeeded;
			player[gameLoop].YR = player[gameLoop].car.year;
			player[gameLoop].value = player[gameLoop].car.price;
			player[gameLoop].MFR = player[gameLoop].car.manufacturer;
			player[gameLoop].NTR = player[gameLoop].car.numOfGears;
			player[gameLoop].ratio[5] = player[gameLoop].car.rearEndRatio;
			player[gameLoop].ratio[4] = player[gameLoop].car.fourthGearRatio;
			player[gameLoop].ratio[3] = player[gameLoop].car.thirdGearRatio;
			player[gameLoop].ratio[2] = player[gameLoop].car.secondGearRatio;
			player[gameLoop].ratio[1] = player[gameLoop].car.firstGearRatio;
			player[gameLoop].gears = player[gameLoop].car.numOfGears;
		}
		
		if (player[gameLoop].car.hp != 0) {
			SM = player[gameLoop].MFR;
			if (player[gameLoop].car.cid == 999) {
				player[gameLoop].SM = "";
			}
			if (player[gameLoop].car.hp > 0) {
				SP1 = player[gameLoop].car.hp;
				SP3 = player[gameLoop].car.cid;
				
				player[gameLoop].car.mfgrE = "MOPAR";
				player[gameLoop].car.P1 = 75;
				player[gameLoop].car.P2 = 4200;
				player[gameLoop].car.P3 = 201;
				player[gameLoop].car.P4 = 1;
				player[gameLoop].car.P5 = 3600;
				player[gameLoop].car.P6 = 150;
				player[gameLoop].car.P7 = 1800;
				player[gameLoop].car.NC = 1;
				player[gameLoop].car.NB = 1;
				player[gameLoop].car.CR = 6;
				player[gameLoop].car.CAM = "S/F";
				player[gameLoop].car.BRTH = 3;
				if (SP3 == player[gameLoop].car.P3) {
					if (SP1 == player[gameLoop].car.P1){
						player[gameLoop].P[1] = player[gameLoop].car.P1;
						player[gameLoop].P[2] = player[gameLoop].car.P2;
						player[gameLoop].P[3] = player[gameLoop].car.P3;
						player[gameLoop].P[4] = player[gameLoop].car.P4;
						player[gameLoop].P[5] = player[gameLoop].car.P5;
						player[gameLoop].P[6] = player[gameLoop].car.P6;
						player[gameLoop].P[7] = player[gameLoop].car.P7;
						player[gameLoop].NC = player[gameLoop].car.NC;
						player[gameLoop].NB = player[gameLoop].car.NB;
						player[gameLoop].CR = player[gameLoop].car.CR;
						player[gameLoop].CAM = player[gameLoop].car.CAM;
						player[gameLoop].BRTH = player[gameLoop].car.BRTH;
						ci.setText("MFGR " + player[gameLoop].car.mfgrE + " DISP=" + player[gameLoop].car.P3 + "HP=" + player[gameLoop].car.P1 + " @ " + player[gameLoop].car.P5 + "TORQUE=" + player[gameLoop].car.P6 + " @ " + player[gameLoop].car.P7);
					}
				}
			}
		}
		if (player[gameLoop].car.hp > 0) {
			if (player[gameLoop].car.numOfGears == 0) {
				rCost = player[gameLoop].ratio[1] / 3;
				player[gameLoop].savings -= rCost;
				player[gameLoop].value += 20;
			}
		}
    	gameLoop++;
    }
}