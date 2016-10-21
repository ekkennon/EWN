package com.ekk.drag1;

/*
 * P(player,1) = hp
 * P(player,3) = cid
 * 
 *  carfix.xml will need to be changed, was copied from main.xml
 */

import android.app.Activity;
//import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.widget.TextView;

/*
 * 14000 REM: IF N(L)=0 THEN CLUTCH IS OUT (14510)
 * 14010 REM: IF N(L)<3 THEN TRANSMISSION NEEDS WORK (14560 - SUBROUTINE)
 * 14020 REM: IF L(L,5)=0 THEN REAR END IS OUT (14800)
 * 
 * 11400 will tell values of variables ****************
 * 
 * 14255 IF P(L,1)=0 THEN 14260 ELSE GOSUB 10200 = 10200 REM: GET ENGINE DATA ****
 * 14260 GOSUB 12030 your car info
 * 14270 GOSUB 12020 press return to continue
 * 14280 IF VLCD(L)<2 THEN GOSUB 21000 = 21000 REM BODY WORK
 * 14300 IF P(L,1)>0 THEN 14500
 * 14310 PRINT "Since your car won't even run, you'd better look into buying an engine."
 * *********** ^^^^^^^^^^^^^^^^^^ engine routine after this ^^^^^^ ****************
 * 
 * 14500 REM
 * 14510 IF N(L)>0 THEN 14550
 * 14520 PRINT USING "& , WHEN YOU TRY TO DRIVE THE CAR, YOU FIND THE CLUTCH IS OUT";N$(L)
 * 
 */

public class CarFix extends Activity {
	
	int FINYR = 54; // earliest car year player can finance car
	int gameLoop = 1;
	int WORK1;//amount of money spending for work done? (player1?)
	int LL;
	int CL;
	
	Player[] player = new Player[3];//players 1 and 2
	
	double SP1;//hp?
	double SP3;//ci?
	double rCost;//repair cost
	
	//Context ctx;
	
	TextView tv;//other
	TextView sv;//savings
	TextView ci;//car info
	
	String[] money = new String[3];
	String SM;//sm=mfr; not sure what this is: sm=mfr; if ci=999 then sm(L)=""; sm=mfr  (line 14255 in phase1)
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carfix);
        
        //ctx = this;
        tv  = (TextView) findViewById(R.id.view1);
        sv = (TextView) findViewById(R.id.savings);
        ci = (TextView) findViewById(R.id.carinfo);
        for (int num = 1; num < 3; num++) {
        	player[num] = new Player();
        	player[num].setName("player" + Integer.toString(num));
        	money[num] = player[num].getName() + " -- SAVINGS = $ " + player[num].getSavings();
        	Car car = new Car(3,75,201,29,"41 PLYMOUTH COUPE","NEEDS RIGHT FRONT FENDER & MINOR RUST REPAIR - OTHERWISE DECENT BODY","ENGINE SMOKES AND YOU SUSPECT IT 'needs repair' BUT IT IS QUIET",2934,2.3,5,3,0,2.76,1.55,1,4.11,"MOPAR",2,41,"");
        	player[num].setCar(car);
        	mainLoop();
        }
        Intent i = new Intent(CarFix.this, RaceActivity.class);
		startActivity(i);
	}
	
	public void mainLoop() {
		if (player[gameLoop].getCar().getPrice() > player[gameLoop].getSavings()) {
			if (player[gameLoop].getCar().getYear() < FINYR) {
				tv.setText("THE CAR YOU HAVE CHOSEN IS TOO OLD TO BE FINANCED AND YOU CANNOT AFFORD TO PAY CASH FOR IT.");
				//11478 SV(PL)=SV(PL)-WORK1:VL(PL)=WORK1 
				//Log.i("carFixLoop", "unable to finance");
				//find out next step
			}
			//should do something on else but haven't found it yet
			//Log.i("carFixLoop", "financing car?");
		}
		else {
		/*
		 * 11420 P(PL,1)=P1:P(PL,3)=P3:O(PL,1)=O:D(PL)=D:W(PL)=W:N(PL)=N
		 * 11425 NTR(PL)=N(PL):DSCR$(PL)=DESC1$
		 * 11430 L(PL,4)=L4:L(PL,3)=L3:L(PL,2)=L2:L(PL,1)=L1:L(PL,5)=L5
		 * 11432 VLCD(PL)=VLCX:YR(PL)=YRX
		 * 11435 SV(PL)=SV(PL)-PR
		 * 11440 VL(PL)=PR
		 * 11450 MFR$(PL)=MFGR$:NTR(PL)=N(PL):TR$(PL)=T$
		 */
			//***************** extra variables
//			player[gameLoop].DSCR = player[gameLoop].car.desc1;
//			player[gameLoop].savings -= player[gameLoop].car.price;
//			player[gameLoop].VLCD = player[gameLoop].car.repairsNeeded;
//			player[gameLoop].YR = player[gameLoop].car.year;
//			player[gameLoop].value = player[gameLoop].car.price;
//			player[gameLoop].MFR = player[gameLoop].car.manufacturer;
//			player[gameLoop].NTR = player[gameLoop].car.numOfGears;
//			player[gameLoop].ratio[5] = player[gameLoop].car.rearEndRatio;
//			player[gameLoop].ratio[4] = player[gameLoop].car.fourthGearRatio;
//			player[gameLoop].ratio[3] = player[gameLoop].car.thirdGearRatio;
//			player[gameLoop].ratio[2] = player[gameLoop].car.secondGearRatio;
//			player[gameLoop].ratio[1] = player[gameLoop].car.firstGearRatio;
//			player[gameLoop].gears = player[gameLoop].car.numOfGears;
			// the line above maybe ntr should = car.numOfGears instead? not sure what gears = here (maybe nothing)
		}
		
		//the following assumes that the car hp is not 0. if it is zero goto line 14260 instead *******
		// should be an else under this if ??
		
		if (player[gameLoop].car.hp != 0) {
			SM = player[gameLoop].MFR;// i do not understand this (line 14255 in phase 1) maybe lookup meaning of "GOTO"
			if (player[gameLoop].car.cid == 999) {
				player[gameLoop].SM = "";// i do not understand this (line 14255 in phase 1) might be a loop
			}
			if (player[gameLoop].car.hp > 0) {
				SP1 = player[gameLoop].car.hp;
				SP3 = player[gameLoop].car.cid;
				
				//*********** next part should call engine() from Car class
				
				//find the engine from ENGINEX3 ?
				//"CHEV",70,3933.333,216,1,3400,158,1600,1,1,6,"S/O",3
				//MFGR$,P1,P2,P3,P4,P5,P6,P7,NC,NB,CR,CAM$,BRTH
				
				//"MOPAR",75,4200,201,1,3600,150,1800,1,1,6,"S/F",3
				
				Engine e = new Engine("MOPAR", 75, 4200, 201, 1, 3600, 150, 1800, 1, 1, 6, "S/F", 3);
	        	player[gameLoop].setEngine(e);
				
				//just probably use hp instead of sp1 and cid instead of sp3 ?
				//if sp3 == p3 (read(past tense) in from engine file) then
				if (SP3 == player[gameLoop].car.P3) {
					if (SP1 == player[gameLoop].car.P1){
//						player[gameLoop].P[1] = player[gameLoop].car.P1;
//						player[gameLoop].P[2] = player[gameLoop].car.P2;
//						player[gameLoop].P[3] = player[gameLoop].car.P3;
//						player[gameLoop].P[4] = player[gameLoop].car.P4;
//						player[gameLoop].P[5] = player[gameLoop].car.P5;
//						player[gameLoop].P[6] = player[gameLoop].car.P6;
//						player[gameLoop].P[7] = player[gameLoop].car.P7;
//						player[gameLoop].NC = player[gameLoop].car.NC;
//						player[gameLoop].NB = player[gameLoop].car.NB;
//						player[gameLoop].CR = player[gameLoop].car.CR;
//						player[gameLoop].CAM = player[gameLoop].car.CAM;
//						player[gameLoop].BRTH = player[gameLoop].car.BRTH;
						ci.setText("MFGR " + player[gameLoop].car.mfgrE + " DISP=" + player[gameLoop].car.P3 + "HP=" + player[gameLoop].car.P1 + " @ " + player[gameLoop].car.P5 + "TORQUE=" + player[gameLoop].car.P6 + " @ " + player[gameLoop].car.P7);
						//10452 is the next line, not sure whether to include it
					}
				}
				  //if sp1 == p1 (read in from engine file) then
				    /*
				     * 10440 P(L,1)=P1:P(L,2)=P2:P(L,3)=P3:P(L,4)=P4:P(L,5)=P5:P(L,6)=P6:P(L,7)=P7
10442 NC(L)=NC:NB(L)=NB:CR(L)=CR:CAM$(L)=CAM$:BRTH(L)=BRTH
10450 PRINT USING "MFGR &, DISP=###, HP=### @ ####, TORQUE=### @ ####";MFGR$;P3;P1;P5;P6;P7
10452 IF P(L,5)=0 THEN 10460
				     */
			}
			
			//10300 why close file here (what file is open?)
			//10400 IF SM$="LIST" THEN display info (then what?) ****when does SM = "LIST" ? *********
			
			if (SM == player[gameLoop].car.mfgrE) {
				
				// ***********************
				
				//10420 IF SP3=P3 THEN 10430 ELSE end while loop and close file? (maybe just goto beginning of while loop)
				//10430 IF SP1=P1 THEN set variables ELSE end while loop and close file? (maybe just goto beginning of while loop)
				//after setting variables:
				////then set variables and display info
				
				if (player[gameLoop].ratio[5] == 0) {
					//10460 LL=LL+1
					//10470 IF LL<23 THEN (maybe just goto beginning of while loop)
					//10480 INPUT "PRESS ENTER FOR MORE";LX$
					//10482 LL=1
					//then goto beginning of while loop
				}
				else if (player[gameLoop].car.rearEndRatio != 0) {
				//else if (player[gameLoop].ratio[5] != 0) {
					//10453 CL=CL+1 , close file and return (where?)
				}
				// after this if and else if should be look at next car in file and see if it matches as line ***
				//assuming that at this part the engine has been chosen and this outer if will exit
			}
			//10410 IF SM$ != MFGR$ THEN end while loop and close file?
			
//************************************************************		
	//probably should not exist (copied wrong)		
			if (player[gameLoop].ratio[5] > 0) {
				//return
			}
			else {
			//10520 PRINT "YOU CHOSE AN INELLIGIBLE HP/DISPLACEMENT COMBINATION - TRY AGAIN (PRESS ENTER)
			//10530 INPUT I$
			//10535 P(L,1)=0:P(L,3)=0
			//10540 GOTO 10200 (start over here?)
			}
//*************************************************************			
			
		}
		//should probably be an else after this (if hp != 0, else hp = 0)
		
		// looks like 10810 thru 10895 do not get used but maybe can use later (BBL= barrel as in carburator)
		if (player[gameLoop].VLCD < 2) {// goto 21000 ; return to 14280
			//body work
		}
		if (player[gameLoop].car.hp > 0) {
			// goto 14500 ; return to 14300
			if (player[gameLoop].car.numOfGears == 0) {//in gwbasic game this is stated as if N > 0 goto (only guessing that == 0 is only other option)
				//when you try to drive the car you find the clutch is out
				//14530  just gets replaced
				rCost = player[gameLoop].ratio[1] / 3;
				//it will cost you $rCost to replace it
				player[gameLoop].savings -= rCost;
				//output savings to verify
				player[gameLoop].value += 20;
				//output value to verify
			}
			if (player[gameLoop].car.numOfGears < 3) {//because the number of gears of the car will be 3, 4, or 5.
				if (player[gameLoop].car.numOfGears == 1) {
					if (player[gameLoop].car.year > 49) {
							//then return
					}
					else {//14600
							//open junk0-TR
							//find a car where player[gameLoop].mfgr == manufacturer read in from the file
							//if weight (O) of car read in != 0 then
							//14660 PRINT:PRINT USING "& , # SPEED, LOW GEAR = ##.##, WILL COST YOU $####";DESC1$,N(3),L(3,N(3)),PR
							//14670 PRINT USING "THE ADDED WEIGHT OF THIS TRANSMISSION WILL BE #### POUNDS";O
							//14710 PRINT:INPUT "INPUT THE LOW GEAR RATIO OF THE TRANSMISSION YOU WANT";LLW
							//14745 O(L,1)=O(L,1)+O
							//14750 L(L,1)=L(3,1):L(L,2)=L(3,2):L(L,3)=L(3,3):L(L,4)=L(3,4)
							//14755 IF L(3,5)>0 THEN L(L,5)=L(3,5)
							//14760 N(L)=N(3):SV(L)=SV(L)-PR
							//14770 IF N(L)=4 THEN VL(L)=VL(L)+50 ELSE VL(L)=VL(L)+20
					}
				}
				else {
					if (player[gameLoop].getCar().getYear() > 49) {
							//then return
					}
					else {
							//14565 PRINT USING "YOUR TRANSMISSION CRUNCHES IN FIRST GEAR.  IT WILL COST YOU $#### TO REBUILD    IT NOW - OR YOU CAN LOOK FOR ANOTHER TRANSMISSION";P(L,3)/9
							//14570 INPUT "DO YOU WANT TO REBUILD (R) OR LOOK FOR ANOTHER TRANSMISSION (L)";I$
							//if answer = look then:
								//14580 IF I$="L" THEN 14600 (this is the if statement from above)
							//else if answer = rebuild then:
								//14585 SV(L)=SV(L)-L(L,3)/9
								//14590 N(L)=3:VL(L)=VL(L)+10
								//14595 GOTO 14795 (end this player's turn goto race)
						
					}
				}
			}
		}
		
		
		
		if (player[gameLoop].getCar().getHp() == 0) {
			/*
			 * 14350 OPEN "I",#1,"junk0ENG.DG1
14360 WHILE NOT EOF(1)
14365 INPUT#1,P1,P3,PR,DESC1$,O,MFGR$,MFGS$
14370 IF MFR$(L)=MFGS$ THEN 14380
14375 GOTO 14390
14380 PRINT USING "### HP, ### CID, WILL COST YOU $ ####";P1;P3;PR
14381 IF O=0 THEN 14385
14383 PRINT USING "THE ADDED WEIGHT OF THIS ENGINE WILL BE #### POUNDS";O
14385 PRINT DESC1$
14388 INPUT "PRESS ENTER FOR NEXT ENGINE";I$
14390 WEND
14400 CLOSE #1
14410 PRINT:INPUT "INPUT THE HORSEPOWER OF THE ENGINE YOU WANT";P1W
14420 OPEN "I",#1,"junk0ENG.DG1
14425 WHILE NOT EOF(1)
14430 INPUT#1,P1,P3,PR,DESC1$,O,MFGR$,MFGS$
14435 IF MFR$(L)=MFGS$ THEN 14440 ELSE 14490
14440 IF P1W=P1 THEN 14450 ELSE 14490
14450 P(L,1)=P1:MFR$(L)=MFGR$:O(L,1)=O(L,1)+O
14460 P(L,3)=P3:SV(L)=SV(L)-PR
14470 VL(L)=VL(L)+(P1*.5)+(P3*.2)-(O*.1)
14490 WEND
14495 CLOSE #1
14497 GOSUB 10200
14498 GOSUB 12030
			 */
		}
    	gameLoop++; //specifies current player (needs to be reset)
    	if (gameLoop == 3) {
    		
    	}
    }
}