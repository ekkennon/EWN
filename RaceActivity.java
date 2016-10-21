package com.ekk.drag1;

/*
 * line 232 raceInfo = ... (player[Z11].car.P2/10) * 10 ... (take out the / and *)
 * 
 * is 1320 the total number of feet in the race?
 * 
 * player 2	junk1.dg1 car# 3
 * player 1	junk2.dg1 car# 5
 * 
 * player 2 wins
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
//import android.util.Log;
import android.widget.TextView;

public class RaceActivity extends Activity {
	
	int gameLoop = 1;
	int N;//number of gears (0 means the clutch is out, N<3 means the transmission needs work)
	int TMR;//timer
	int TM2;//timer 2
	int TNVL;//timer integer value (counts up to this number for a delay) (was read in from file)
	int Z9 = 0;//for looper timer
	int T1;
	int RVL;
	int LWIN;// winning lane?
	int WIN1;//winner?
	int SWT18;
	int SWT13;
	int SWT17;
	int SWT14;
	int SWT16;
	int SWT15;
	int SWT12;
	int T2;
	int Z11 = 1;//for looper
	int ROUND;// round of race?
	int lane = 1;
	int J = 1;//for looper
	int Z10 = 1;//for looper not
	
	Player[] player = new Player[3];//players 1 and 2
	
	String money;
	String TR1;// what is this?
	String TR2;//in Dr2Activity the String TR maybe was this and I wrote it wrong
	String raceInfo;
	
	double T;
	double L14;//was numbered this way so dad knew which part of a dimensional array these were from player[1].ratio[4], player[1].ratio[3], player[2].ratio[4], player[2].ratio[3]
	double L13;//these are not being used right now, now using L3[] and L4[] from player
	double L24;
	double L23;
	double T3;
	double D;
	double TSAV;
	double XX1;
	double BW1;
	double BW2;
	double BW3;
	double BW4;
	double BURN1;
	double BURN2;
	double BURN3;
	double BURN4;
	double maxAccel;//originally called A
	double Ri;//originally R but that caused an error (RPM)
	double WS;
	double LWINT;
	double PT1;
	double W;
	double WR;
	double PT2;
	double LOT;//low time
	
	TextView ci;
	TextView r;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race);
        
        TR1 = "";//comes from beginning of phase 1
        ci = (TextView) findViewById(R.id.ci);
        r = (TextView) findViewById(R.id.race);
        TNVL = 1000;
        
        for (int num = 1; num < 3; num++) {
        	player[num] = new Player();
        	player[num].setName("player" + Integer.toString(num));
        	money = player[num].getName() + " -- SAVINGS = $ " + player[num].getSavings();
        	/*player[1].car = new Car(5,70,229,39,"40 OLDS 6-CYL SEDAN","ENGINE SMOKES A LITTLE","PAINT IS SOMEWHAT FADED AND BODY SHOWS A LITTLE RUST",3065,2.3,4.7,3,0,2.65,1.51,1,3.93,"OLDS",4,40,"");
        	if (num == 2) {
        		player[2].car = new Car(3,75,216,39,"40 CHEVY COUPE","ORIGINAL 85 HP ENGINE STILL RUNS PRETTY GOOD","SHOWS QUITE A BIT OF RUST AROUND THE BOTTOM",2925,2.3,5,3,0,2.94,1.68,1,4.11,"CHEV",5,40,"");
        	}*/
        	
        	//*******************************************************************
        	//temp line to create car, future will be created when player picks car and passed thru intents
        	Car c = new Car(3,75,201,29,"41 PLYMOUTH COUPE","NEEDS RIGHT FRONT FENDER & MINOR RUST REPAIR - OTHERWISE DECENT BODY","ENGINE SMOKES AND YOU SUSPECT IT 'needs repair' BUT IT IS QUIET",2934,2.3,5,3,0,2.76,1.55,1,4.11,"MOPAR",2,41,"");
        	player[num].setCar(c);
        	Engine e = new Engine("MOPAR", 75, 4200, 201, 1, 3600, 150, 1800, 1, 1, 6, "S/F", 3);
        	player[num].setEngine(e);
			
			player[num].setSavings(player[num].getSavings() - player[num].getCar().getPrice());
			
			//Log.i("player " + Integer.toString(num), "gears " + Integer.toString(player[num].gears));
			
			gameLoop = num;
			Z11 = num;
        	mainLoop();
        }
        race();
	}
	
	public void mainLoop() {
		//Log.i("race", "mainloop");
		T = 0;
		T1 = 0;
		T2 = 0;
		
		
        //891 T=0:T1=0:T2=0:H(1)=0:H(2)=0
		Ri = 0;
		
		
		
		
        //892 R=0:Q(1)=0:Q(2)=0:S(1)=0:S(2)=0:B(1)=0:B(2)=0:X(1)=0:X(2)=0
        //893 NTR(1)=N(1):NTR(2)=N(2):L14=L(1,4):L13=L(1,3):L24=L(2,4):L23=L(2,3):TR1$=TR$(1):TR2$=TR$(2)
		
		TR1 = player[gameLoop].getTR();
		
		if (player[gameLoop].getTR() == "SLIP") {
			player[gameLoop].setTR("");
		}
		player[gameLoop].P[8] = 0;
		player[gameLoop].weight[2] = 0;
		
		//894 IF TR$(1)="SLIP" THEN:
			//TR$(1)=""
		//IF TR$(2)="SLIP" THEN:
			//TR$(2)=""
		//895 P(1,8)=0:P(2,8)=0:O(1,2)=0:O(2,2)=0
		
		//for (Z11 = 1; Z11 < 3 ;Z11++) {
		//900 FOR Z11 = 1 TO 2
			//904 IF TR$(Z11)="PG" THEN:
			//Log.i("z11", Integer.toString(Z11));
			if (player[Z11] != null) {
				//Log.i("tr", player[Z11].TR + " tr");
				if (player[Z11].getTR() == "PG") {
					player[Z11].ratio[4] = 3.62;
					lane = Z11;
				}
			}
			
			/*
			10020 IF L(L,4)=2.94 THEN N(L)=3:L(L,2)=1.68:L(L,3)=2.94
					10030 IF L(L,4)=3.06 THEN N(L)=4:L(L,3)=1.63:L(L,2)=1.05:L(L,1)=1:O(L,1)=O(L,1)+150
					10040 IF L(L,4)=2.85 THEN N(L)=4:L(L,2)=1.35:L(L,3)=2.02:O(L,1)=O(L,1)+25
					10050 IF L(L,4)=2.47 THEN N(L)=3:L(L,2)=1.53:L(L,3)=2.47
					10052 IF L(L,4)=2.58 THEN N(L)=3:L(L,2)=1.48:L(L,3)=2.58
					10060 IF L(L,4)=3.62 THEN N(L)=2:L(L,2)=1.1:L(L,2)=3.62:O(L,1)=O(L,1)+95:TR$(L)="PG"
					10062 IF L(L,4)=3.96 THEN N(L)=4:L(L,2)=1.53:L(L,3)=2.63:O(L,1)=O(L,1)+198:TR$(L)="HYD"
					10063 IF L(L,4)=3.97 THEN N(L)=4:L(L,2)=1.33:L(L,3)=2.23:O(L,1)=O(L,1)+198:TR$(L)="HYD"
					10064 IF L(L,4)=2.5 THEN N(L)=3:L(L,2)=1.55:L(L,3)=2.5:O(L,1)=O(L,1)+125:TR$(L)="TH"
					10070 IF L(L,4)=2.21 THEN N(L)=3:L(L,2)=1.33:L(L,3)=2.21
					10080 IF L(L,4)=2.2 THEN N(L)=4:L(L,2)=1.31:L(L,3)=1.64:O(L,1)=O(L,1)+25
					10090 IF L(L,4)=2.54 THEN N(L)=4:L(L,2)=1.66:L(L,3)=1.91:O(L,1)=O(L,1)+25
					10092 IF L(L,4)=2.56 THEN N(L)=4:L(L,2)=1.48:L(L,3)=1.91:O(L,1)=O(L,1)+25
					10100 IF L(L,4)=2.65 THEN N(L)=3:L(L,1)=1:L(L,2)=1.51:L(L,3)=2.65
					10110 IF L(L,4)=2.39 THEN L(L,3)=2.39:L(L,2)=1.53:N(L)=3
					10190 IF L(L,2)=0 THEN 10010
					10192 IF L(L,1)=0 THEN L(L,1)=1
					10195 RETURN
					*/
			//Log.i("ratio 4", Double.toString(player[lane].ratio[4]));
			if (player[lane].ratio[4] == 3.06) {
				//Log.i("line 200", "ratio 4 = 3.06");
			}
				//L(Z11,4)=3.62
				//L=Z11
			//GOSUB 10020
			//910 GOSUB 3170
			/*
			 * 3170 PRINT USING "HP = ###  @ ####  TORQUE = ### @ ####  DISP = ### ";P(Z11,1);P(Z11,5);P(Z11,6);P(Z11,7);P(Z11,3)
3190 RETURN
			 */
			
			//Log.i("player[Z11].car.P7", Double.toString(player[Z11].car.P7));
			//Log.i("player[Z11].car.P6", Double.toString(player[Z11].car.P6));
			//Log.i("player[Z11].car.P5", Double.toString(player[Z11].car.P5));
			//Log.i("player[Z11].car.hp", Double.toString(player[Z11].car.hp));
			//Log.i("player[Z11].car.cid", Double.toString(player[Z11].car.cid));
			ci.setText("HP = " + player[Z11].getCar().getHp() + " @ " + player[Z11].getEngine().getP5() + " TORQUE = " + player[Z11].getEngine().getP6() + " @ " + player[Z11].getEngine().getP7() + " DISP = " + player[Z11].getCar().getCid());
			ci.setMovementMethod(ScrollingMovementMethod.getInstance());
			raceInfo += "Lane " + lane + " is programmed to shift at " + (player[Z11].getEngine().getP2()/10) * 10 + " RPM. In a future version you will have the option to change this.";
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
			//932 PRINT USING "LANE # IS PROGRAMMED TO SHIFT AT ###0 RPM";Z11;P(Z11,2)/10
			//933 PRINT "IF YOU WANT TO CHANGE THIS, INPUT YOUR DESIRED RPM"
			//940 INPUT Z10
			
			//*****************************
			//need to do line 960 after user can enter desired rpm
			//*****************************
			
			if (Z10 > 0) {
				
			}
			//950 IF Z10>0 THEN:
				//960 
			else {
				//next z11
			}
			//ELSE:
				//1025
			//960 IF P(Z11,3)=216 THEN:
				//GOSUB 20000
			//965 IF P(Z11,2)>5999 AND P(Z11,1)>P(Z11,3)*.96 THEN:
				//XZ1=1.11
			//ELSE:
				//XZ1=1.04
			//970 IF Z10<P(Z11,2)*1.2 THEN:
				//990
			//980 IF Z10>XZ1*((P(Z11,1)/P(Z11,3))*6200) THEN:
				//1010
			//ELSE:
				//990
			//990 P(Z11,2)=Z10
			//1000 GOTO 1025
			//1010 PRINT "RPM TOO HIGH FOR THIS ENGINE - TRY AGAIN"
			//1020 GOTO 932
		//end for loop (z11)
		//}
	}
	public void race() {
		//Log.i("race", "race");
		raceInfo += "This is a test of machines--both drivers are equal";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		//1040 PRINT"THIS IS A TEST OF MACHINES--BOTH DRIVERS ARE EQUAL"
		raceInfo += player[1].getCar().getHp() +  " HP  " + player[1].getCar().getCid() + " CI " + player[1].weight[1] + " LBS  " + player[1].ratio[5] + " | LOW TIME  " + LOT + " | " + player[2].getCar().getHp() + " HP  " + player[2].getCar().getCid() + " CI " + player[2].weight[1] + " LBS  " + player[2].ratio[5];
		r.setText(raceInfo);
		Log.i("weight line 282", Double.toString(player[1].getWeight[1]));
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		raceInfo += "THREE";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		//1060 LOCATE 25:PRINT USING "### HP  ### CI #### LBS  #.## | LOW TIME  ##.## | ### HP  ### CI #### LBS  #.## ";P(1,1);P(1,3);O(1,1);L(1,5);LOT;P(2,1);P(2,3);O(2,1);L(2,5)
		//1070 PRINT:PRINT "                                      THREE"
		/*
		for (Z9 = 1; Z9 < 500; Z9++) {
			//timer
		}*/
		// (timer) 1080 FOR Z9=1 TO 500:NEXT Z9
		raceInfo += "TWO";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		//1090 PRINT:PRINT "                                       TWO"
		// (timer) 1100 FOR Z9=1 TO 500:NEXT Z9
		/*
		for (Z9 = 1; Z9 < 500; Z9++) {
			//timer
		}*/
		raceInfo += "ONE";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		//1110 PRINT:PRINT "                                       ONE"
		raceInfo += "                              ||  GO  GO  GO   ||";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		for (J = 1; J < 3; J++) {
			player[J].P[9] = player[J].P[1];
			if (player[J].P[4] == 0) {
				player[J].P[4] = 1;
			}
			player[J].P[1] = player[J].P[1] * player[J].P[4];
			player[J].setM((player[J].weight[1] * 1.5 + player[J].P[1] + player[J].getP[3] + (player[J].getD() * player[J].getW()))/33);
			//next is 1180
			player[J].setMaxAccel(15 + 28 * player[J].getW() * player[J].getD() / (player[J].getW() + 6) * (player[J].getD() + 1));
		}
		//1130 FOR J=1 TO 2
			//1135 P(J,9)=P(J,1)
			//1140 IF P(J,4)=0 THEN:
				//P(J,4)=1
			//1150 P(J,1)=P(J,1)*P(J,4)
			//1160 M(J)=(O(J,1)*1.5+P(J,1)+P(J,3)+(D(J)*W(J)))/33
			//1170 REM: B IS MAX ACCEL W/O BURNING
			//1180 B(J)=15+28*W(J)*D(J)/((W(J)+6)*(D(J)+1))
		//1190 NEXT J
		/*
		for (T = 0; T < 101; T++) {
			for (T1 = 1; T1 < 11; T1++) {
				for (TMR = 1; TMR < TNVL; TMR++) {
					for (TM2 = 1; TM2 < TNVL; TM2++) {
						//timer
					}
					//timer
				}
			}
		}*/
		for (J = 1; J < 3; J++) {
			if (J == 1) {//2090
				if (player[1].getCar().getNumOfGears() > 1) {
					if (player[2].getDistanceInFeet() > 1320) {//why is this for player 2 ?
						seventeeneighty();
					}
					else {//2120
						//if J == 1 then print
						raceInfo += "Shifting - " + player[J].getH() + " RPM, " + player[J].getFeet() * 3600 / 5280 + " MPH  ||  " + (T + (T1 / 10)) + "  ||  " + player[2].getDistanceInFeet() + " Feet";
						r.setText(raceInfo);
						r.setMovementMethod(ScrollingMovementMethod.getInstance());
						player[1].setGears(player[1].getGears()-1);
						//Log.i("player[1].gears", Integer.toString(player[1].gears));
						//Log.i("player[1].ratio[5]", Double.toString(player[1].ratio[5]));
						//Log.i("player[1].ratio", Double.toString(player[1].ratio[player[1].gears]));
						player[1].setEi(player[1].ratio[player[1].getGears()] * player[1].ratio[5]);
						if (player[J].getTR() == "A") {//automatic
							//2176
							if (player[J].getTR() == "SLIP") {
								//6000
								//???? this doesn't exist
								//this is in P1T not P1X
								//Log.i("add", "line 6000");
								sub6000();
							}
						}
						if (player[1].getH() == 0 && player[1].getEi() < 11) {
							player[J].setTR("SLIP");
						}
						twelvefifty();
					}
				}
				else {//2100
					if (player[1].H > player[1].P[2] * 1.07) {//2190
						raceInfo += "CAR #1 OVERREVED AT " + player[1].H + "RPM" + player[1].distanceInFeet + " FT." + player[1].feet * 3600 /5280 + " MPH";
						r.setText(raceInfo);
						r.setMovementMethod(ScrollingMovementMethod.getInstance());
						player[1].car.hp = 0;
						twelvefifty();
					}
					else {
						if (player[1].gears == 1) {//1250
							twelvefifty();
						}
						else {
							if (player[2].distanceInFeet > 1320) {//1780
								seventeeneighty();
							}
							else {
								if (J == 1) {//probably unnecessary
									raceInfo += "SHIFTING - " + player[J].H + " RPM, " + player[J].feet * 3600 / 5280 + " MPH  ||     " + (T+(T1/10)) + "     ||            " + player[2].distanceInFeet + " FEET ";
									r.setText(raceInfo);
									r.setMovementMethod(ScrollingMovementMethod.getInstance());
									player[1].gears --;
									player[1].Ei = player[1].ratio[player[1].gears] * player[1].ratio[5];
									//2174
									if (player[J].TR == "A") {//automatic
										//2176
										if (player[J].TR == "SLIP") {
											//goto sub 6000
											//Log.i("add","sub 6000");
											sub6000();
										}
										else {
											//1250
											twelvefifty();
										}
									}
									else {
										if (player[1].H == 0 && player[1].Ei < 11) {
											player[1].TR = "SLIP";
										}
									}
								}
							}
						}
					}
				}
			}
			else if (J == 2) {//2220
				if (player[2].H < player[2].P[2] * 1.0001) {
					//1250
					twelvefifty();
				}
				else {
					//2250
					if (player[2].gears > 1) {
						//2275
						if (player[1].distanceInFeet > 1320) {
							seventeeneighty();
						}
					}
					else {
						if (player[2].H > (player[2].P[2] * 1.07)) {
							//2350
							raceInfo += "CAR #2 OVERREVED AT "+ player[2].H + "RPM" + player[2].distanceInFeet + " FT." + player[2].feet*3600/5280 + " MPH";
							r.setText(raceInfo);
							r.setMovementMethod(ScrollingMovementMethod.getInstance());
							raceInfo += "CAR #2 -- GEAR =" + player[2].gears + " FINAL=" + player[2].Ei + "  J=" + J;
							r.setText(raceInfo);
							r.setMovementMethod(ScrollingMovementMethod.getInstance());
							player[2].P[1] = 0;
							twelvefifty();
						}
						else {
							if (player[2].gears == 1) {
								twelvefifty();
							}
							else {
								if (player[1].distanceInFeet > 1320) {
									seventeeneighty();
								}
								else {
									//2280
									raceInfo += "           " + player[1].distanceInFeet + " FEET          ||     " + (T+(T1/10)) + "     ||SHIFTING - " + player[J].H + " RPM, " + player[J].feet*3600/5280 + " MPH ";
									r.setText(raceInfo);
									r.setMovementMethod(ScrollingMovementMethod.getInstance());
									player[2].gears--;
									player[2].Ei = player[2].ratio[player[2].gears] * player[2].ratio[5];
									if (player[2].H == 0 && player[2].Ei < 11) {
										player[J].TR = "SLIP";
									}
									if (player[2].TR == "SLIP") {
										sub6000();
									}
									else {
										twelvefifty();
									}
								}
							}
						}
					}
				}
			}
			raceInfo += player[1].feet * 3600 / 5280 + " MPH  " + player[1].distanceInFeet + " Feet  " + player[1].H + " RPM  ||  " + (T + 1) + "  ||  " + player[2].feet * 3600 / 5280 + " MPH  " + player[2].distanceInFeet + " Feet" + player[2].H + " RPM";//1880
			//"##S(1)*3600/5280#.## MPH ##X(1)## FEET ##H(1)## RPM ||     #T+1#.##     ||##S(2)*3600/5280#.## MPH  ##X(2)## FEET ##H(2)## RPM"
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
		//1200 FOR T=0 TO 100
			//1210 FOR T1 = 1 TO 10
				//1211 FOR TMR=1 TO TNVL
					//1212 FOR TM2=1 TO TNVL
					//1218 NEXT TM2
				//1219 NEXT TMR
				//1220 FOR J=1 TO 2
					//1230 IF J=1 THEN 2090
					//1240 IF J=2 THEN 2220
					//1250 REM: R IS FOR RPM
					//1260 R=60*S(J)*E(J)/(3.14159*D(J))
					//1270 IF J=2 THEN H(2)=R
					//1280 IF J=1 THEN H(1)=R
					//1290 REM: new burnout routine at 1400
					//1300 GOTO 2390
					//1310 REM: A=ACCELERATION
					//1320 IF Q(J)<>0 THEN 2590
					//1330 IF R=0 THEN 1400
					//1340 IF T1<(P(J,1)*2+P(J,3))*E(J)*.001*(P(J,9)/2)/(O(J,1)*.8)THEN 1400
					//1350 IF T1<(P(J,1)*2+P(J,3))*E(J)*.001*(P(J,9)/2)/(O(J,1)*.8)THEN 1400
					//1360 IF J=1 THEN PRINT USING "STOPS BURNING AT  #### FEET   ||     ##.##     ||            #### FEET";X(1);T+(T1/10);X(2)
					//1370 IF J=2 THEN PRINT USING "           #### FEET          ||     ##.##     ||STOPS BURNING AT  #### FEET";X(1);T+(T1/10);X(2)
					//1380 Q(J)=1
					//1390 GOTO 2590
					//1400 REM: BURN ACCEL
					//1410 A=((P(J,1)+P(J,3))*(E(J)*(2.9/D(J))))/((O(J,1)/12)+(S(J)*.7))
					//1420 REM: S IS FT IN FT/SEC
					//1440 IF A>W(J)*5 THEN A=W(J)*4.5
					//1450 XX1=(P(J,1)/P(J,3))*6000
					//1455 IF T<2 THEN A=A*1.2*(3000/O(J,1))
					//1460 IF H(J)>XX1*.34 THEN 1490 ELSE 1470
					//1470 S(J)=S(J)+A*.037
					//1480 S(J)=S(J)+A*((H(J)/(XX1*(5/6)))*.025):GOTO 1540
					//1490 IF H(J)>(XX1*(5/6)) THEN 1510 ELSE 1500
					//1500 S(J)=S(J)+A*((H(J)/(XX1*(5/6)))*.053):GOTO 1540
					//1510 IF H(J)>XX1 THEN 1520 ELSE 1530
					//1520 S(J)=S(J)+A*((.049*(XX1/H(J)))):GOTO 1550
					//1530 S(J)=S(J)+A*((.052*((XX1*(5/6))/H(J))))
					//1540 S(J)=S(J)+(A*((H(J)/XX1)*.053))
					//1550 REM: X IS DISTANCE IN FT
					//1560 IF B(J)=1 THEN 1570 ELSE 1590
					//1570 X(J)=X(J)+S(J)*.075
					//1580 GOTO 1600
					//1590 X(J)=X(J)+S(J)*.098
				//1600 NEXT J
				//1610 REM: TEST FOR FINISH
				//1620 IF X(1)<5280/4 AND X(2)< 5280/4 THEN 1870
				//1630 IF X(1)>X(2) THEN 1780
				//1640 T3=(X(2)-5280/4)/S(2)
				//1650 T=T+(T1/10)-T3
				//1660 X(2)=X(2)-S(2)*T3
				//1670 X(1)=X(1)-S(1)*T3
				//1680 PRINT USING "           #### FEET          ||     ##.## --->||  LANE 2 WINNER";X(1);T
				//1682 WS=S(2)*3600/5280
				//1684 WIN2=WIN2+1
				//1685 LWIN=2:LWINT=T
				//1690 PRINT USING " ###.## MPH    #### RPM       ||               ||   ###.## MPH  #### RPM";S(1)*3600/5280;H(1);S(2)*3600/5280;H(2)
				//1700 REM
				//1705 INPUT "PRESS ENTER TO CONTINUE";I$
				//1710 ROUND=ROUND+1
				//1720 N(1)=NTR(1):N(2)=NTR(2):L(1,4)=L14:L(1,3)=L13:L(2,4)=L24:L(2,3)=L23:TR$(1)=TR1$:TR$(2)=TR2$
				//1730 GOTO 11000
	}
	
	public void raceOver() {
		//Log.i("race", "raceover");
		//this starts at line 11000
		if (LOT == 0) {
			LOT = 99.99;
		}
		if (TSAV == 0) {
			TSAV = 99.99;
		}
		if (LWINT < LOT) {
			LOT = LWINT;
		}
		if (LWINT < TSAV) {
			TSAV = LWINT;
		}
		else if (LWINT >= TSAV) {
			sub11500();
		}
		swt18();
	}
	
	public void swt18() {
		if (SWT18 > 0) {
			swt17();
		}
		else {
			lwint18();
		}
	}
	
	public void lwint18() {
		if (LWINT < 18) {
			SWT18=LWIN;
			player[LWIN].value+=50;
			player[LWIN].PV+=.5;
		}
		swt17();
	}
	
	public void swt17() {
		if (SWT17 > 0) {
			swt16();
		}
	}
	
	public void lwint17() {
		if (LWINT < 17) {
			SWT17=LWIN;
			player[LWIN].value+=75;
			RVL+=25;
			player[LWIN].PV+=1;
		}
		swt16();
	}
	
	public void swt16() {
		if (SWT16 > 0) {
			swt15();
		}
		else {
			lwint16();
		}
	}
	
	public void lwint16() {
		if (LWINT < 16) {
			SWT16=LWIN;
			player[LWIN].value+=75;
			RVL=RVL+25;
			player[LWIN].PV+=1.3;
		}
	}
	
	public void swt15() {
		if (SWT15 > 0) {
			swt14();
		}
		else {
			lwint15();
		}
	}
	
	public void lwint15() {
		if (LWINT < 15) {
			SWT15=LWIN;
			player[LWIN].value+=100;
			RVL=RVL+50;
			player[LWIN].PV+=1.8;
		}
		lwintg14();
	}
	
	public void lwintg14() {
		if (LWINT > 14) {
			sub11500();
		}
	}
	
	public void swt14() {
		if (SWT14 > 0) {
			swt13();
		}
		lwintl14();
	}
	
	public void lwintl14() {
		if (LWINT < 14) {
			SWT14=LWIN;
			player[LWIN].value+=125;
			RVL=RVL+100;
			player[LWIN].PV+=2.1;
		}
		lwintg13();
	}
	
	public void lwintg13() {
		if (LWINT > 13) {
			sub11500();
		}
		else {
			swt13();
		}
	}
	
	public void swt13() {
		if (SWT13 > 0) {
			swt12();
		}
		else {
			lwintl13();
		}
	}
	
	public void lwintl13() {
		if (LWINT < 13) {
			SWT13=LWIN;
			player[LWIN].value+=150;
			RVL=RVL+150;
			player[LWIN].PV+=3;
		}
		lwintg12();
	}
	
	public void lwintg12() {
		if (LWINT > 12) {
			sub11500();
		}
		else {
			swt12();
		}
	}
	
	public void swt12() {
		if (SWT12 > 0) {
			sub11500();
		}
		else {
			lwintl12();
		}
	}
	
	public void lwintl12() {
		if (LWINT < 12) {
			SWT12=LWIN;
			player[LWIN].value+=200;
			RVL=RVL+200;
			player[LWIN].PV+=4;
		}
		sub11500();
	}
	
	public void sub11500() {
		//Log.i("race", "11500");
		//starts at 11501
		//some lines are being left out here. 
		//after writing these lines the return statement will take you to line 11005
		if (SWT18 > 0) {
			raceInfo += "FIRST PERSON UNDER 18 SECONDS IS" + player[SWT18].name;
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
		else if (SWT17 > 0) {
			raceInfo += "FIRST PERSON UNDER 17 SECONDS IS" + player[SWT17].name;
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
		else if (SWT16 > 0) {
			raceInfo += "FIRST PERSON UNDER 16 SECONDS IS" + player[SWT16].name;
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
		else if (SWT15 > 0) {
			raceInfo += "FIRST PERSON UNDER 15 SECONDS IS" + player[SWT15].name;
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
		else if (SWT14 > 0) {
			raceInfo += "FIRST PERSON UNDER 14 SECONDS IS" + player[SWT14].name;
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
		else if (SWT13 > 0) {
			raceInfo += "FIRST PERSON UNDER 13 SECONDS IS" + player[SWT13].name;
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
		else if (SWT12 > 0) {
			raceInfo += "FIRST PERSON UNDER 12 SECONDS IS" + player[SWT12].name;
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
		else {
			raceInfo += "There is no winner for this race. TIE";
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
			ci.setText("TIE");
			ci.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
	}
	
	public void twentyEight() {
		//Log.i("race", "2800");
		//this starts at line 2800
		if (player[J].feet > 66) {
			WR=.24+(player[J].feet/1000);
		}
		else {
			//2880
			if (player[J].feet<=66) {
				WR=.12+(player[J].feet/1000);
			}
		}
		if (player[J].feet > 99) {
			WR=.45+(player[J].feet/1000);
		}
		else if (player[J].feet> 119) {
			WR = .55+(player[J].feet / 1000);
		}
		else if (player[J].feet>138) {
			WR =.65+(player[J].feet/1000);
		}
		else if (player[J].feet> 147) {
			WR = .75+ (player[J].feet/1000);
		}
		else if (player[J].feet>157) {
			WR = .85 + (player[J].feet/1000);
		}
		else if (player[J].feet>169) {
			WR = .95 + (player[J].feet/1000);
		}
		else if (player[J].feet <= 66) {
			WR=.12+(player[J].feet/1000);
		}
		//2890
		if (player[J].P[8]>player[J].P[3]*1.5) {
			//2910
			maxAccel=((player[J].P[8]*1.33)*(player[J].Ei)*(2.2/player[J].D))/((player[J].weight[1]/8)+(player[J].feet*WR));
		}
		else if (player[J].P[8] <= player[J].P[3]*1.5) {
			//2930
			maxAccel=((player[J].P[8]+(player[J].P[3]/2))*(player[J].Ei*(2.2/player[J].D)))/((player[J].weight[1]/8)+(player[J].feet*WR));
		}
		player[J].feet=player[J].feet+maxAccel*(.099-(player[J].feet*.00028));
		//1550
		if (player[J].maxAccel == 1) {
			//1570
			player[J].distanceInFeet=player[J].distanceInFeet+player[J].feet*.075;
		}
		else if (player[J].maxAccel != 1) {
			//1590
			player[J].distanceInFeet=player[J].distanceInFeet+player[J].feet*.098;
		}
		//after this is Next J
	}
	
	public void sub6000() {
		//this is in P1T not P1X
	}
	
	public void twelvefifty() {
		//Log.i("race", "1250");
		Ri = 60 * player[J].feet * player[J].Ei / (3.14159 * player[J].D);
		player[J].H = Ri;
		//goto 2390
		if (player[J].Q != 0) {
			//Log.i("1250", "if1");
			//1380
			player[J].Q = 1;
			//2590
			if (player[J].H > (1000 / player[J].P[7])) {
				//Log.i("1250", "if10");
				//2640
				if (player[J].H > player[J].P[7]) {
					//2700
					if (player[J].H > player[J].P[5]) {
						//2750
						//2760 P(J,8)=P(J,6)-((P(J,6)*((H(J)-P(J,7))/P(J,7)))*((P(J,1)/P(J,3)*(H(J)/(P(J,7)*1.2)))))
						player[J].P[8] = player[J].P[8] + (player[J].P[1] - ((player[J].P[1] * ((player[J].H - player[J].P[5]) / player[J].P[5]))) * ((player[J].P[1] / player[J].P[3]) * .99));
						//2800
						twentyEight();
					}
					else {//2720
						PT1 = player[J].P[6] / player[J].P[3];
						if (PT1 > .95) {
							PT1 = .95;
						}
						if (PT1 < .8) {
							PT1 = .8;
							PT2 = 1 - PT1;
							//2725 P(J,8)=P(J,6)-((P(J,6)*PT2)*(H(J)/P(J,5)))
							player[J].P[8] = player[J].P[8] + (((player[J].H / player[J].P[5])*(player[J].H / player[J].P[5])) * player[J].P[1]);
							//goto 2800
							twentyEight();
						}
					}
				}
				else {//2660
					player[J].P[8] = ((player[J].H / player[J].P[7]) * player[J].P[6]) + ((player[J].H / player[J].P[5]) * player[J].P[1]);
					if (player[J].TR == "PG") {
						twentyEight();
					}
					else {//2680
						if (player[J].P[8] < player[J].P[3] * 1.1) {
							player[J].P[8] = player[J].P[3] * 1.1;
						}
						twentyEight();
					}
				}
			}
			else {
				//player[J].P[8] = (1000 / player[J].P[7]);
				player[J].P[8] = ((1000 / player[J].P[7]) * player[J].P[6]) + ((1000 / player[J].P[5]) * player[J].P[1]);
				//2800
				twentyEight();
			}
		}
		else {//2410
			BW1 = player[J].ratio[player[J].gears] * player[J].ratio[5];
			if (BW1 > 11) {
				//2500
			}
			else {
				BW2 = 14 * (player[J].car.hp + player[J].P[3]);
				BW3 = player[J].car.hp / player[J].car.cid;
				BW4 = player[J].car.cid / (player[J].weight[1] * .085);
				if (BW3 > BW4) {
					//2470
					BURN1 = BW2 * BW3;
					//2510
					BURN2 = (BURN1 * 2.5) / player[J].D;
					BURN3 = (BURN2 - player[J].weight[1]) / 100;
					BURN4 = BURN3 - player[J].W;
					if (player[J].TR == "PG") {
						//2560
						BURN4 = (BURN3 - player[J].W) / 3;
					}
					//2570
					if (BURN4 < player[J].feet) {
						//1360
						if (J == 1) {
							raceInfo += "STOPS BURNING AT  " + player[1].distanceInFeet + " FEET   ||     " + (T+(T1/10)) + "     ||            " + player[2].distanceInFeet +" FEET";
							r.setText(raceInfo);
							r.setMovementMethod(ScrollingMovementMethod.getInstance());
						}
						else {
							raceInfo += "           "+ player[1].distanceInFeet +" FEET          ||     " + (T+(T1/10)) + "     ||STOPS BURNING AT  " + player[2].distanceInFeet + " FEET";
							r.setText(raceInfo);
							r.setMovementMethod(ScrollingMovementMethod.getInstance());
						}
						player[J].Q = 1;
						//2590
						if (player[J].H > (1000 / player[J].P[7])) {
							//2640
						}
						else {
							player[J].P[8]=((1000/player[J].P[7])*player[J].P[6])+((1000/player[J].P[5])*player[J].P[1]);
							twentyEight();
						}
					}
					else {
						//1400
						maxAccel=((player[J].P[1]+player[J].P[3])*(player[J].Ei*(2.9/player[J].D)))/((player[J].weight[1]/12)+(player[J].feet*.7));
						if (maxAccel > player[J].W * 5) {
							maxAccel = player[J].W * 4.5;
						}
						XX1=(player[J].P[1]/player[J].P[3])*6000;
						if (T < 2) {
							maxAccel=maxAccel*1.2*(3000/player[J].weight[1]);
						}
						if (player[J].H>XX1*.34) {
							//1490
							if (player[J].H>(XX1*(5/6))) {
								//1510
								if (player[J].H > XX1) {
									//1520
									player[J].feet+=maxAccel*((.049*(XX1/player[J].H)));
									//1550
									if (player[J].maxAccel == 1) {
										//1570
										player[J].distanceInFeet+=player[J].feet*.075;
									}
									else {
										//1590
										player[J].distanceInFeet+=player[J].feet*.098;
									}
								}
								else {
									//1530
									player[J].feet+=maxAccel*((.052*((XX1*(5/6))/player[J].H)));
									//1540
									player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
									//1550
									if (player[J].maxAccel == 1) {
										//1570
										player[J].distanceInFeet+=player[J].feet*.075;
									}
									else {
										//1590
										player[J].distanceInFeet+=player[J].feet*.098;
									}
								}
							}
							else {
								//1500
								player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.053);
								//1540
								player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
								//1550
								if (player[J].maxAccel == 1) {
									//1570
									player[J].distanceInFeet+=player[J].feet*.075;
								}
								else {
									//1590
									player[J].distanceInFeet+=player[J].feet*.098;
								}
							}
						}
						else {
							//1470
							player[J].feet+=maxAccel*.037;
							player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.025);
							//1540
							player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
							//1550
							if (player[J].maxAccel == 1) {
								//1570
								player[J].distanceInFeet+=player[J].feet*.075;
							}
							else {
								//1590
								player[J].distanceInFeet+=player[J].feet*.098;
							}
						}
					}
				}
				else {
					if (player[J].distanceInFeet > (50 - (player[J].weight[1] / 100))) {
						//THEN 2470 
						BURN1 = BW2 * BW3;
						//2510
						BURN2 = (BURN1 * 2.5) / player[J].D;
						BURN3 = (BURN2 - player[J].weight[1]) / 100;
						BURN4 = BURN3 - player[J].W;
						if (player[J].TR == "PG") {
							//2560
							BURN4 = (BURN3 - player[J].W) / 3;
						}
						//2570
						if (BURN4 < player[J].feet) {
							//1360
							if (J == 1) {
								raceInfo += "STOPS BURNING AT  " + player[1].distanceInFeet + " FEET   ||     " + (T+(T1/10)) + "     ||            " + player[2].distanceInFeet +" FEET";
								r.setText(raceInfo);
								r.setMovementMethod(ScrollingMovementMethod.getInstance());
							}
							else {
								raceInfo += "           "+ player[1].distanceInFeet +" FEET          ||     " + (T+(T1/10)) + "     ||STOPS BURNING AT  " + player[2].distanceInFeet + " FEET";
								r.setText(raceInfo);
								r.setMovementMethod(ScrollingMovementMethod.getInstance());
							}
							player[J].Q = 1;
							//2590
							if (player[J].H > (1000 / player[J].P[7])) {
								//2640
							}
							else {
								player[J].P[8]=((1000/player[J].P[7])*player[J].P[6])+((1000/player[J].P[5])*player[J].P[1]);
								twentyEight();
							}
						}
						else {
							//1400
							maxAccel=((player[J].P[1]+player[J].P[3])*(player[J].Ei*(2.9/player[J].D)))/((player[J].weight[1]/12)+(player[J].feet*.7));
							if (maxAccel > player[J].W * 5) {
								maxAccel = player[J].W * 4.5;
							}
							XX1=(player[J].P[1]/player[J].P[3])*6000;
							if (T < 2) {
								maxAccel=maxAccel*1.2*(3000/player[J].weight[1]);
							}
							if (player[J].H>XX1*.34) {
								//1490
								if (player[J].H>(XX1*(5/6))) {
									//1510
									if (player[J].H > XX1) {
										//1520
										player[J].feet+=maxAccel*((.049*(XX1/player[J].H)));
										//1550
										if (player[J].maxAccel == 1) {
											//1570
											player[J].distanceInFeet+=player[J].feet*.075;
										}
										else {
											//1590
											player[J].distanceInFeet+=player[J].feet*.098;
										}
									}
									else {
										//1530
										player[J].feet+=maxAccel*((.052*((XX1*(5/6))/player[J].H)));
										//1540
										player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
										//1550
										if (player[J].maxAccel == 1) {
											//1570
											player[J].distanceInFeet+=player[J].feet*.075;
										}
										else {
											//1590
											player[J].distanceInFeet+=player[J].feet*.098;
										}
									}
								}
								else {
									//1500
									player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.053);
									//1540
									player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
									//1550
									if (player[J].maxAccel == 1) {
										//1570
										player[J].distanceInFeet+=player[J].feet*.075;
									}
									else {
										//1590
										player[J].distanceInFeet+=player[J].feet*.098;
									}
								}
							}
							else {
								//1470
								player[J].feet+=maxAccel*.037;
								player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.025);
								//1540
								player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
								//1550
								if (player[J].maxAccel == 1) {
									//1570
									player[J].distanceInFeet+=player[J].feet*.075;
								}
								else {
									//1590
									player[J].distanceInFeet+=player[J].feet*.098;
								}
							}
						}
					}
					else {
						BURN1 = BW2 * BW4;
						if (T > 2) {
							//2470
							BURN1 = BW2 * BW3;
						}
						//2510
						BURN2 = (BURN1 * 2.5) / player[J].D;
						BURN3 = (BURN2 - player[J].weight[1]) / 100;
						BURN4 = BURN3 - player[J].W;
						if (player[J].TR == "PG") {
							//2560
							BURN4 = (BURN3 - player[J].W) / 3;
						}
						//2570
						if (BURN4 < player[J].feet) {
							//1360
							if (J == 1) {
								raceInfo += "STOPS BURNING AT  " + player[1].distanceInFeet + " FEET   ||     " + (T+(T1/10)) + "     ||            " + player[2].distanceInFeet +" FEET";
								r.setText(raceInfo);
								r.setMovementMethod(ScrollingMovementMethod.getInstance());
							}
							else {
								raceInfo += "           "+ player[1].distanceInFeet +" FEET          ||     " + (T+(T1/10)) + "     ||STOPS BURNING AT  " + player[2].distanceInFeet + " FEET";
								r.setText(raceInfo);
								r.setMovementMethod(ScrollingMovementMethod.getInstance());
							}
							player[J].Q = 1;
							//2590
							if (player[J].H > (1000 / player[J].P[7])) {
								//2640
							}
							else {
								player[J].P[8]=((1000/player[J].P[7])*player[J].P[6])+((1000/player[J].P[5])*player[J].P[1]);
								twentyEight();
							}
						}
						else {
							//1400
							maxAccel=((player[J].P[1]+player[J].P[3])*(player[J].Ei*(2.9/player[J].D)))/((player[J].weight[1]/12)+(player[J].feet*.7));
							if (maxAccel > player[J].W * 5) {
								maxAccel = player[J].W * 4.5;
							}
							XX1=(player[J].P[1]/player[J].P[3])*6000;
							if (T < 2) {
								maxAccel=maxAccel*1.2*(3000/player[J].weight[1]);
							}
							if (player[J].H>XX1*.34) {
								//1490
								if (player[J].H>(XX1*(5/6))) {
									//1510
									if (player[J].H > XX1) {
										//1520
										player[J].feet+=maxAccel*((.049*(XX1/player[J].H)));
										//1550
										if (player[J].maxAccel == 1) {
											//1570
											player[J].distanceInFeet+=player[J].feet*.075;
										}
										else {
											//1590
											player[J].distanceInFeet+=player[J].feet*.098;
										}
									}
									else {
										//1530
										player[J].feet+=maxAccel*((.052*((XX1*(5/6))/player[J].H)));
										//1540
										player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
										//1550
										if (player[J].maxAccel == 1) {
											//1570
											player[J].distanceInFeet+=player[J].feet*.075;
										}
										else {
											//1590
											player[J].distanceInFeet+=player[J].feet*.098;
										}
									}
								}
								else {
									//1500
									player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.053);
									//1540
									player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
									//1550
									if (player[J].maxAccel == 1) {
										//1570
										player[J].distanceInFeet+=player[J].feet*.075;
									}
									else {
										//1590
										player[J].distanceInFeet+=player[J].feet*.098;
									}
								}
							}
							else {
								//1470
								player[J].feet+=maxAccel*.037;
								player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.025);
								//1540
								player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
								//1550
								if (player[J].maxAccel == 1) {
									//1570
									player[J].distanceInFeet+=player[J].feet*.075;
								}
								else {
									//1590
									player[J].distanceInFeet+=player[J].feet*.098;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void seventeeneighty() {
		//Log.i("race", "1780");
		T3 = (player[1].distanceInFeet - 5280 / 4) / player[1].feet;//1780
		T = T + (T1 / 10) - T3;
		player[1].distanceInFeet = 5200 / 4;
		player[2].distanceInFeet = player[2].distanceInFeet - player[2].feet * T3;
		raceInfo += "Lane 1 Winner  || <---- " + T + " || " + player[2].distanceInFeet;
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		raceInfo += player[1].feet * 3600 / 5280 + " MPH  " + player[1].H + " RPM  ||  || " + player[2].feet * 3600 / 5280 + " MPH  " + player[2].H;
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		WS = player[1].feet * 3600 / 5280;
		WIN1++;
		LWIN = 1;
		LWINT = T;
		//goto 1705
		ROUND++;
		//N(1)=NTR(1):N(2)=NTR(2):L(1,4)=L14:L(1,3)=L13:L(2,4)=L24:L(2,3)=L23:TR$(1)=TR1$:TR$(2)=TR2$
		for (int i = 1; i < 3; i++) {
			player[i].gears = player[i].NTR;
			player[i].ratio[3] = player[i].L3;
			player[i].ratio[4] = player[i].L4;
		}
		//L13,L14,L23,L24 are not being used so most likely the variables are already set
		player[1].TR = TR1;
		player[2].TR = TR2;
		raceOver();
	}
}