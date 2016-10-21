package com.ekk.drag1;

/*
 * L = ratio 
 * P = hp or cid
 * 
 * reading from car files:
 * NOX,P1,P3,PR,DESC1$,DESC2$,DESC3$,O,D,W,N,L4,L3,L2,L1,L5,MFGR$,VLCX,YRX,T$
 * 
 * initializing main variables:
 * P(PL,1)=P1:P(PL,3)=P3:O(PL,1)=O:D(PL)=D:W(PL)=W:N(PL)=N
 * 11425 NTR(PL)=N(PL):DSCR$(PL)=DESC1$
 * 11430 L(PL,4)=L4:L(PL,3)=L3:L(PL,2)=L2:L(PL,1)=L1:L(PL,5)=L5
 * 11432 VLCD(PL)=VLCX:YR(PL)=YRX
 * 
 * MFR$(PL)=MFGR$:NTR(PL)=N(PL):TR$(PL)=T$
 * 
 * reading from engines file (ENGINEX3):
 * MFGR$,P1,P2,P3,P4,P5,P6,P7,NC,NB,CR,CAM$,BRTH
 * 
 * initializing main variables:
 * P(L,1)=P1:P(L,2)=P2:P(L,3)=P3:P(L,4)=P4:P(L,5)=P5:P(L,6)=P6:P(L,7)=P7
 * 10442 NC(L)=NC:NB(L)=NB:CR(L)=CR:CAM$(L)=CAM$:BRTH(L)=BRTH
 */

//import android.util.Log;

public class Car {
	
	//read from car files:
	private int carNum;//NOX
	private int price;//PR
	private int numOfGears;//N
	private int repairsNeeded;//VLCX
	private int year;//YRX
	
	private double hp;//P1
	private double cid;//P3  cubic inch displacement
	private double weight;//O
	private double D;//D
	private double W;//W
	private double firstGearRatio;//L1
	private double secondGearRatio;//L2
	private double thirdGearRatio;//L3
	private double fourthGearRatio;//L4
	private double rearEndRatio;//L5
	
	private String desc1;//DESC1$
	private String desc2;//DESC2$
	private String desc3;//DESC3$
	private String manufacturer;//MFGR$
	private String transType;//T$
	
	
	
	
	public Car(int i1, double d1, double d2, int i2, String s1, String s2, String s3, double d3, double d4, double d5, int i3, double d6, double d7, double d8, double d9, double d10, String s4, int i4, int i5, String s5) {
		setCarNum(i1);
		setPrice(i2);
		setNumOfGears(i3);
		setRepairsNeeded(i4);
		setYear(i5);
		
		setHp(d1);
		setCid(d2);
		setWeight(d3);
		setD(d4);
		setW(d5);
		setFourthGearRatio(d6);
		setThirdGearRatio(d7);
		setSecondGearRatio(d8);
		setFirstGearRatio(d9);
		setRearEndRatio(d10);
		
		setDesc1(s1);
		setDesc2(s2);
		setDesc3(s3);
		setManufacturer(s4);
		setTransType(s5);
	}
	
	/**
	 * @return the carNum
	 */
	public int getCarNum() {
		return carNum;
	}

	/**
	 * @param carNum the carNum to set
	 */
	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the numOfGears
	 */
	public int getNumOfGears() {
		return numOfGears;
	}

	/**
	 * @param numOfGears the numOfGears to set
	 */
	public void setNumOfGears(int numOfGears) {
		this.numOfGears = numOfGears;
	}

	/**
	 * @return the repairsNeeded
	 */
	public int getRepairsNeeded() {
		return repairsNeeded;
	}

	/**
	 * @param repairsNeeded the repairsNeeded to set
	 */
	public void setRepairsNeeded(int repairsNeeded) {
		this.repairsNeeded = repairsNeeded;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the hp
	 */
	public double getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(double hp) {
		this.hp = hp;
	}

	/**
	 * @return the cid
	 */
	public double getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(double cid) {
		this.cid = cid;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the d
	 */
	public double getD() {
		return D;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(double d) {
		D = d;
	}

	/**
	 * @return the w
	 */
	public double getW() {
		return W;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(double w) {
		W = w;
	}

	/**
	 * @return the firstGearRatio
	 */
	public double getFirstGearRatio() {
		return firstGearRatio;
	}

	/**
	 * @param firstGearRatio the firstGearRatio to set
	 */
	public void setFirstGearRatio(double firstGearRatio) {
		this.firstGearRatio = firstGearRatio;
	}

	/**
	 * @return the secondGearRatio
	 */
	public double getSecondGearRatio() {
		return secondGearRatio;
	}

	/**
	 * @param secondGearRatio the secondGearRatio to set
	 */
	public void setSecondGearRatio(double secondGearRatio) {
		this.secondGearRatio = secondGearRatio;
	}

	/**
	 * @return the thirdGearRatio
	 */
	public double getThirdGearRatio() {
		return thirdGearRatio;
	}

	/**
	 * @param thirdGearRatio the thirdGearRatio to set
	 */
	public void setThirdGearRatio(double thirdGearRatio) {
		this.thirdGearRatio = thirdGearRatio;
	}

	/**
	 * @return the fourthGearRatio
	 */
	public double getFourthGearRatio() {
		return fourthGearRatio;
	}

	/**
	 * @param fourthGearRatio the fourthGearRatio to set
	 */
	public void setFourthGearRatio(double fourthGearRatio) {
		this.fourthGearRatio = fourthGearRatio;
	}

	/**
	 * @return the rearEndRatio
	 */
	public double getRearEndRatio() {
		return rearEndRatio;
	}

	/**
	 * @param rearEndRatio the rearEndRatio to set
	 */
	public void setRearEndRatio(double rearEndRatio) {
		this.rearEndRatio = rearEndRatio;
	}

	/**
	 * @return the desc1
	 */
	public String getDesc1() {
		return desc1;
	}

	/**
	 * @param desc1 the desc1 to set
	 */
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	/**
	 * @return the desc2
	 */
	public String getDesc2() {
		return desc2;
	}

	/**
	 * @param desc2 the desc2 to set
	 */
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	/**
	 * @return the desc3
	 */
	public String getDesc3() {
		return desc3;
	}

	/**
	 * @param desc3 the desc3 to set
	 */
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}

	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
}