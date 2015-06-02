package com.ekkapps.engineworkneeded;

public class Car {
	
	int carNum;
	int price;
	int numOfGears;
	int repairsNeeded;
	int year;
	
	double hp;
	double cid;
	double weight;
	double D;
	double W;
	double firstGearRatio;
	double secondGearRatio;
	double thirdGearRatio;
	double fourthGearRatio;
	double rearEndRatio;
	
	String desc1;
	String desc2;
	String desc3;
	String manufacturer;
	String transType;
	
	int BRTH;
	int NC;
	int NB;
	int CR;
	
	String mfgrE;
	String CAM;
	
	double P1;
	double P2;
	double P3;
	double P4;
	double P5;
	double P6;
	double P7;
	
	public Car(int i1, double d1, double d2, int i2, String s1, String s2, String s3, double d3, double d4, double d5, int i3, double d6, double d7, double d8, double d9, double d10, String s4, int i4, int i5, String s5) {
		carNum = i1;
		price = i2;
		numOfGears = i3;
		repairsNeeded = i4;
		year = i5;
		
		hp = d1;
		cid = d2;
		weight = d3;
		D = d4;
		W = d5;
		fourthGearRatio = d6;
		thirdGearRatio = d7;
		secondGearRatio = d8;
		firstGearRatio = d9;
		rearEndRatio = d10;
		
		desc1 = s1;
		desc2 = s2;
		desc3 = s3;
		manufacturer = s4;
		transType = s5;
	}
}