package com.ekkapps.engineworkneeded;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class RaceActivity extends Activity {
	
	int gameLoop = 1;
	int N;
	int TMR;
	int TM2;
	int TNVL;
	int Z9 = 0;
	int T1;
	int RVL;
	int LWIN;
	int WIN1;
	int SWT18;
	int SWT13;
	int SWT17;
	int SWT14;
	int SWT16;
	int SWT15;
	int SWT12;
	int T2;
	int Z11 = 1;
	int ROUND;
	int lane = 1;
	int J = 1;
	int Z10 = 1;
	
	Player[] player = new Player[3];
	
	String money;
	String TR1;
	String TR2;
	String raceInfo;
	
	double T;
	double L14;
	double L13;
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
	double maxAccel;
	double Ri;
	double WS;
	double LWINT;
	double PT1;
	double W;
	double WR;
	double PT2;
	double LOT;
	
	TextView ci;
	TextView r;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race);
        
        TR1 = "";
        ci = (TextView) findViewById(R.id.ci);
        r = (TextView) findViewById(R.id.race);
        TNVL = 1000;
        
        for (int num = 1; num < 3; num++) {
        	player[num] = new Player();
        	player[num].name = "player" + Integer.toString(num);
        	money = player[num].name + " -- SAVINGS = $ " + player[num].savings;
        	player[num].car = new Car(3,75,201,29,"41 PLYMOUTH COUPE","NEEDS RIGHT FRONT FENDER & MINOR RUST REPAIR - OTHERWISE DECENT BODY","ENGINE SMOKES AND YOU SUSPECT IT 'needs repair' BUT IT IS QUIET",2934,2.3,5,3,0,2.76,1.55,1,4.11,"MOPAR",2,41,"");
        	player[num].car.mfgrE = "MOPAR";
			player[num].car.P1 = 75;
			player[num].car.P2 = 4200;
			player[num].car.P3 = 201;
			player[num].car.P4 = 1;
			player[num].car.P5 = 3600;
			player[num].car.P6 = 150;
			player[num].car.P7 = 1800;
			player[num].car.NC = 1;
			player[num].car.NB = 1;
			player[num].car.CR = 6;
			player[num].car.CAM = "S/F";
			player[num].car.BRTH = 3;
			player[num].P[1] = player[num].car.P1;
			player[num].P[2] = player[num].car.P2;
			player[num].P[3] = player[num].car.P3;
			player[num].P[4] = player[num].car.P4;
			player[num].P[5] = player[num].car.P5;
			player[num].P[6] = player[num].car.P6;
			player[num].P[7] = player[num].car.P7;
			player[num].NC = player[num].car.NC;
			player[num].NB = player[num].car.NB;
			player[num].CR = player[num].car.CR;
			player[num].CAM = player[num].car.CAM;
			player[num].BRTH = player[num].car.BRTH;
			player[num].DSCR = player[num].car.desc1;
			player[num].savings -= player[num].car.price;
			player[num].VLCD = player[num].car.repairsNeeded;
			player[num].YR = player[num].car.year;
			player[num].value = player[num].car.price;
			player[num].MFR = player[num].car.manufacturer;
			player[num].NTR = player[num].car.numOfGears;
			player[num].ratio[5] = player[num].car.rearEndRatio;
			player[num].ratio[4] = player[num].car.fourthGearRatio;
			player[num].ratio[3] = player[num].car.thirdGearRatio;
			player[num].ratio[2] = player[num].car.secondGearRatio;
			player[num].ratio[1] = player[num].car.firstGearRatio;
			player[num].gears = player[num].car.numOfGears;
			player[num].W = player[num].car.W;
			player[num].D = player[num].car.D;
			gameLoop = num;
			Z11 = num;
        	mainLoop();
        }
        race();
	}
	
	public void mainLoop() {
		T = 0;
		T1 = 0;
		T2 = 0;
		player[gameLoop].H = 0;
		Ri = 0;
		player[gameLoop].Q = 0;
		player[gameLoop].feet = 0;
		player[gameLoop].distanceInFeet = 0;
		player[gameLoop].maxAccel = 0;
		player[gameLoop].NTR = player[gameLoop].car.numOfGears;
		player[gameLoop].L4 = player[gameLoop].ratio[4];
		player[gameLoop].L3 = player[gameLoop].ratio[3];
		TR1 = player[gameLoop].TR;
		
		if (player[gameLoop].TR == "SLIP") {
			player[gameLoop].TR = "";
		}
		player[gameLoop].P[8] = 0;
		player[gameLoop].weight[2] = 0;
		if (player[Z11] != null) {
			if (player[Z11].TR == "PG") {
				player[Z11].ratio[4] = 3.62;
				lane = Z11;
			}
		}
			
		ci.setText("HP = " + player[Z11].car.hp + " @ " + player[Z11].car.P5 + " TORQUE = " + player[Z11].car.P6 + " @ " + player[Z11].car.P7 + " DISP = " + player[Z11].car.cid);
		ci.setMovementMethod(ScrollingMovementMethod.getInstance());
		raceInfo += "Lane " + lane + " is programmed to shift at " + player[Z11].car.P2/10 + " RPM. In a future version you will have the option to change this.";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
	}
	public void race() {
		raceInfo += "This is a test of machines--both drivers are equal";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		raceInfo += player[1].car.hp +  " HP  " + player[1].car.cid + " CI " + player[1].weight[1] + " LBS  " + player[1].ratio[5] + " | LOW TIME  " + LOT + " | " + player[2].car.hp + " HP  " + player[2].car.cid + " CI " + player[2].weight[1] + " LBS  " + player[2].ratio[5];
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		raceInfo += "THREE";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		raceInfo += "TWO";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		raceInfo += "ONE";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		raceInfo += "                              ||  GO  GO  GO   ||";
		r.setText(raceInfo);
		r.setMovementMethod(ScrollingMovementMethod.getInstance());
		for (J = 1; J < 3; J++) {
			player[J].P[9] = player[J].P[1];
			if (player[J].P[4] == 0) {
				player[J].P[4] = 1;
			}
			player[J].P[1] = player[J].P[1] * player[J].P[4];
			player[J].M = (player[J].weight[1] * 1.5 + player[J].P[1] + player[J].P[3] + (player[J].D * player[J].W))/33;
			player[J].maxAccel = 15 + 28 * player[J].W * player[J].D / (player[J].W + 6) * (player[J].D + 1);
		}
		
		for (J = 1; J < 3; J++) {
			if (J == 1) {
				if (player[1].car.numOfGears > 1) {
					if (player[2].distanceInFeet > 1320) {
						seventeeneighty();
					}
					else {
						raceInfo += "Shifting - " + player[J].H + " RPM, " + player[J].savings * 3600 / 5280 + " MPH  ||  " + (T + (T1 / 10)) + "  ||  " + player[2].distanceInFeet + " Feet";
						r.setText(raceInfo);
						r.setMovementMethod(ScrollingMovementMethod.getInstance());
						player[1].gears--;
						player[1].Ei = player[1].ratio[player[1].gears] * player[1].ratio[5];
						if (player[J].TR == "A") {
							if (player[J].TR == "SLIP") {
								sub6000();
							}
						}
						if (player[1].H == 0 && player[1].Ei < 11) {
							player[J].TR = "SLIP";
						}
						twelvefifty();
					}
				}
				else {
					if (player[1].H > player[1].P[2] * 1.07) {
						raceInfo += "CAR #1 OVERREVED AT " + player[1].H + "RPM" + player[1].distanceInFeet + " FT." + player[1].feet * 3600 /5280 + " MPH";
						r.setText(raceInfo);
						r.setMovementMethod(ScrollingMovementMethod.getInstance());
						player[1].car.hp = 0;
						twelvefifty();
					}
					else {
						if (player[1].gears == 1) {
							twelvefifty();
						}
						else {
							if (player[2].distanceInFeet > 1320) {
								seventeeneighty();
							}
							else {
								if (J == 1) {
									raceInfo += "SHIFTING - " + player[J].H + " RPM, " + player[J].feet * 3600 / 5280 + " MPH  ||     " + (T+(T1/10)) + "     ||            " + player[2].distanceInFeet + " FEET ";
									r.setText(raceInfo);
									r.setMovementMethod(ScrollingMovementMethod.getInstance());
									player[1].gears --;
									player[1].Ei = player[1].ratio[player[1].gears] * player[1].ratio[5];
									if (player[J].TR == "A") {
										if (player[J].TR == "SLIP") {
											sub6000();
										}
										else {
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
			else if (J == 2) {
				if (player[2].H < player[2].P[2] * 1.0001) {
					twelvefifty();
				}
				else {
					if (player[2].gears > 1) {
						if (player[1].distanceInFeet > 1320) {
							seventeeneighty();
						}
					}
					else {
						if (player[2].H > (player[2].P[2] * 1.07)) {
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
			r.setText(raceInfo);
			r.setMovementMethod(ScrollingMovementMethod.getInstance());
		}
	}
	
	public void raceOver() {
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
		if (player[J].feet > 66) {
			WR=.24+(player[J].feet/1000);
		}
		else {
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
		if (player[J].P[8]>player[J].P[3]*1.5) {
			maxAccel=((player[J].P[8]*1.33)*(player[J].Ei)*(2.2/player[J].D))/((player[J].weight[1]/8)+(player[J].feet*WR));
		}
		else if (player[J].P[8] <= player[J].P[3]*1.5) {
			maxAccel=((player[J].P[8]+(player[J].P[3]/2))*(player[J].Ei*(2.2/player[J].D)))/((player[J].weight[1]/8)+(player[J].feet*WR));
		}
		player[J].feet=player[J].feet+maxAccel*(.099-(player[J].feet*.00028));
		if (player[J].maxAccel == 1) {
			player[J].distanceInFeet=player[J].distanceInFeet+player[J].feet*.075;
		}
		else if (player[J].maxAccel != 1) {
			player[J].distanceInFeet=player[J].distanceInFeet+player[J].feet*.098;
		}
	}
	
	public void sub6000() {
	}
	
	public void twelvefifty() {
		Ri = 60 * player[J].feet * player[J].Ei / (3.14159 * player[J].D);
		player[J].H = Ri;
		if (player[J].Q != 0) {
			player[J].Q = 1;
			if (player[J].H > (1000 / player[J].P[7])) {
				if (player[J].H > player[J].P[7]) {
					if (player[J].H > player[J].P[5]) {
						player[J].P[8] = player[J].P[8] + (player[J].P[1] - ((player[J].P[1] * ((player[J].H - player[J].P[5]) / player[J].P[5]))) * ((player[J].P[1] / player[J].P[3]) * .99));
						twentyEight();
					}
					else {
						PT1 = player[J].P[6] / player[J].P[3];
						if (PT1 > .95) {
							PT1 = .95;
						}
						if (PT1 < .8) {
							PT1 = .8;
							PT2 = 1 - PT1;
							player[J].P[8] = player[J].P[8] + (((player[J].H / player[J].P[5])*(player[J].H / player[J].P[5])) * player[J].P[1]);
							twentyEight();
						}
					}
				}
				else {
					player[J].P[8] = ((player[J].H / player[J].P[7]) * player[J].P[6]) + ((player[J].H / player[J].P[5]) * player[J].P[1]);
					if (player[J].TR == "PG") {
						twentyEight();
					}
					else {
						if (player[J].P[8] < player[J].P[3] * 1.1) {
							player[J].P[8] = player[J].P[3] * 1.1;
						}
						twentyEight();
					}
				}
			}
			else {
				player[J].P[8] = ((1000 / player[J].P[7]) * player[J].P[6]) + ((1000 / player[J].P[5]) * player[J].P[1]);
				twentyEight();
			}
		}
		else {
			BW1 = player[J].ratio[player[J].gears] * player[J].ratio[5];
			if (BW1 > 11) {
			}
			else {
				BW2 = 14 * (player[J].car.hp + player[J].P[3]);
				BW3 = player[J].car.hp / player[J].car.cid;
				BW4 = player[J].car.cid / (player[J].weight[1] * .085);
				if (BW3 > BW4) {
					BURN1 = BW2 * BW3;
					BURN2 = (BURN1 * 2.5) / player[J].D;
					BURN3 = (BURN2 - player[J].weight[1]) / 100;
					BURN4 = BURN3 - player[J].W;
					if (player[J].TR == "PG") {
						BURN4 = (BURN3 - player[J].W) / 3;
					}
					if (BURN4 < player[J].feet) {
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
						if (player[J].H > (1000 / player[J].P[7])) {
						}
						else {
							player[J].P[8]=((1000/player[J].P[7])*player[J].P[6])+((1000/player[J].P[5])*player[J].P[1]);
							twentyEight();
						}
					}
					else {
						maxAccel=((player[J].P[1]+player[J].P[3])*(player[J].Ei*(2.9/player[J].D)))/((player[J].weight[1]/12)+(player[J].feet*.7));
						if (maxAccel > player[J].W * 5) {
							maxAccel = player[J].W * 4.5;
						}
						XX1=(player[J].P[1]/player[J].P[3])*6000;
						if (T < 2) {
							maxAccel=maxAccel*1.2*(3000/player[J].weight[1]);
						}
						if (player[J].H>XX1*.34) {
							if (player[J].H>(XX1*(5/6))) {
								if (player[J].H > XX1) {
									player[J].feet+=maxAccel*((.049*(XX1/player[J].H)));
									if (player[J].maxAccel == 1) {
										player[J].distanceInFeet+=player[J].feet*.075;
									}
									else {
										player[J].distanceInFeet+=player[J].feet*.098;
									}
								}
								else {
									player[J].feet+=maxAccel*((.052*((XX1*(5/6))/player[J].H)));
									player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
									if (player[J].maxAccel == 1) {
										player[J].distanceInFeet+=player[J].feet*.075;
									}
									else {
										player[J].distanceInFeet+=player[J].feet*.098;
									}
								}
							}
							else {
								player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.053);
								player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
								if (player[J].maxAccel == 1) {
									player[J].distanceInFeet+=player[J].feet*.075;
								}
								else {
									player[J].distanceInFeet+=player[J].feet*.098;
								}
							}
						}
						else {
							player[J].feet+=maxAccel*.037;
							player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.025);
							player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
							if (player[J].maxAccel == 1) {
								player[J].distanceInFeet+=player[J].feet*.075;
							}
							else {
								player[J].distanceInFeet+=player[J].feet*.098;
							}
						}
					}
				}
				else {
					if (player[J].distanceInFeet > (50 - (player[J].weight[1] / 100))) {
						BURN1 = BW2 * BW3;
						BURN2 = (BURN1 * 2.5) / player[J].D;
						BURN3 = (BURN2 - player[J].weight[1]) / 100;
						BURN4 = BURN3 - player[J].W;
						if (player[J].TR == "PG") {
							BURN4 = (BURN3 - player[J].W) / 3;
						}
						if (BURN4 < player[J].feet) {
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
							if (player[J].H > (1000 / player[J].P[7])) {
							}
							else {
								player[J].P[8]=((1000/player[J].P[7])*player[J].P[6])+((1000/player[J].P[5])*player[J].P[1]);
								twentyEight();
							}
						}
						else {
							maxAccel=((player[J].P[1]+player[J].P[3])*(player[J].Ei*(2.9/player[J].D)))/((player[J].weight[1]/12)+(player[J].feet*.7));
							if (maxAccel > player[J].W * 5) {
								maxAccel = player[J].W * 4.5;
							}
							XX1=(player[J].P[1]/player[J].P[3])*6000;
							if (T < 2) {
								maxAccel=maxAccel*1.2*(3000/player[J].weight[1]);
							}
							if (player[J].H>XX1*.34) {
								if (player[J].H>(XX1*(5/6))) {
									if (player[J].H > XX1) {
										player[J].feet+=maxAccel*((.049*(XX1/player[J].H)));
										if (player[J].maxAccel == 1) {
											player[J].distanceInFeet+=player[J].feet*.075;
										}
										else {
											player[J].distanceInFeet+=player[J].feet*.098;
										}
									}
									else {
										player[J].feet+=maxAccel*((.052*((XX1*(5/6))/player[J].H)));
										player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
										if (player[J].maxAccel == 1) {
											player[J].distanceInFeet+=player[J].feet*.075;
										}
										else {
											player[J].distanceInFeet+=player[J].feet*.098;
										}
									}
								}
								else {
									player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.053);
									player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
									if (player[J].maxAccel == 1) {
										player[J].distanceInFeet+=player[J].feet*.075;
									}
									else {
										player[J].distanceInFeet+=player[J].feet*.098;
									}
								}
							}
							else {
								player[J].feet+=maxAccel*.037;
								player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.025);
								player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
								if (player[J].maxAccel == 1) {
									player[J].distanceInFeet+=player[J].feet*.075;
								}
								else {
									player[J].distanceInFeet+=player[J].feet*.098;
								}
							}
						}
					}
					else {
						BURN1 = BW2 * BW4;
						if (T > 2) {
							BURN1 = BW2 * BW3;
						}
						BURN2 = (BURN1 * 2.5) / player[J].D;
						BURN3 = (BURN2 - player[J].weight[1]) / 100;
						BURN4 = BURN3 - player[J].W;
						if (player[J].TR == "PG") {
							BURN4 = (BURN3 - player[J].W) / 3;
						}
						if (BURN4 < player[J].feet) {
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
							if (player[J].H > (1000 / player[J].P[7])) {
							}
							else {
								player[J].P[8]=((1000/player[J].P[7])*player[J].P[6])+((1000/player[J].P[5])*player[J].P[1]);
								twentyEight();
							}
						}
						else {
							maxAccel=((player[J].P[1]+player[J].P[3])*(player[J].Ei*(2.9/player[J].D)))/((player[J].weight[1]/12)+(player[J].feet*.7));
							if (maxAccel > player[J].W * 5) {
								maxAccel = player[J].W * 4.5;
							}
							XX1=(player[J].P[1]/player[J].P[3])*6000;
							if (T < 2) {
								maxAccel=maxAccel*1.2*(3000/player[J].weight[1]);
							}
							if (player[J].H>XX1*.34) {
								if (player[J].H>(XX1*(5/6))) {
									if (player[J].H > XX1) {
										player[J].feet+=maxAccel*((.049*(XX1/player[J].H)));
										if (player[J].maxAccel == 1) {
											player[J].distanceInFeet+=player[J].feet*.075;
										}
										else {
											player[J].distanceInFeet+=player[J].feet*.098;
										}
									}
									else {
										player[J].feet+=maxAccel*((.052*((XX1*(5/6))/player[J].H)));
										player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
										if (player[J].maxAccel == 1) {
											player[J].distanceInFeet+=player[J].feet*.075;
										}
										else {
											player[J].distanceInFeet+=player[J].feet*.098;
										}
									}
								}
								else {
									player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.053);
									player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
									if (player[J].maxAccel == 1) {
										player[J].distanceInFeet+=player[J].feet*.075;
									}
									else {
										player[J].distanceInFeet+=player[J].feet*.098;
									}
								}
							}
							else {
								player[J].feet+=maxAccel*.037;
								player[J].feet+=maxAccel*((player[J].H/(XX1*(5/6)))*.025);
								player[J].feet+=(maxAccel*((player[J].H/XX1)*.053));
								if (player[J].maxAccel == 1) {
									player[J].distanceInFeet+=player[J].feet*.075;
								}
								else {
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
		T3 = (player[1].distanceInFeet - 5280 / 4) / player[1].feet;
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
		ROUND++;
		for (int i = 1; i < 3; i++) {
			player[i].gears = player[i].NTR;
			player[i].ratio[3] = player[i].L3;
			player[i].ratio[4] = player[i].L4;
		}
		player[1].TR = TR1;
		player[2].TR = TR2;
		raceOver();
	}
}