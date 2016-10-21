package com.ekk.drag1;

//import android.util.Log;

/*
 * money[] should be moved here instead. can later be turned to a function instead of writing it in each activity
 * 
 * need to eventually take block from CarFix player[i].P[1] = player[i].car.P1 and put it in here or Car class ?
 */

public class Player {
	
	private String name = "";
	
	private int lane;//lane 1 or 2 (switches between races)
	
	private double savings = 250;//amount of money to spend (variable feet was originally called S)
	
	private double distanceInFeet = 0;//originally X
	private double value;//value of car
	//     ^^  was VL ?
	
	//car info
	private double H = 0;
	private double[] P = new double[10];//really it is 1 thru 9 but for now [0] will be left blank
	private double feet = 0;//feet in feet per second, originally called S
	private double maxAccel = 0;//max acceleration without burning or might not be (was this B?)
	private double PV;
	private double HS;
	private double LT;
	private double Ei;//renamed from E, was messing up code by changing to player[i].equals()
	private double M;
	private double D;
	private double W;
	private double[] weight = new double[4];//not sure why this is an array, using 1-3
	private double L3;//player[1].L3 is L13, player[2].L3 is L23
	private double L4;//player[1].L4 is L14, player[2].L4 is L24
	private double[] ratio = new double [6];//[0] = L5, [1] = L1, [2] = L2, [3] = L3, [4] = L4, but for now l5 = [5]
	// ^^^^^^^^^ if l1>0 then sp1=l1 and sp3=l3 per line 10235 in tutorial (starts at 14255) ^^^^^^^^^^
	
	private Car car;
	private Engine engine;
	
	private int Q = 0;
	private int NTR;
	private int NC;//see CarFix comment just before VLCD conditional
	private int NB;//see CarFix comment just before VLCD conditional
	private int CN;//see CarFix comment just before VLCD conditional
	private int CR;//see CarFix comment just before VLCD conditional
	private int BRTH;//see CarFix comment just before VLCD conditional
	private int VLCD;//same as vlcx in car (not needed here)
	private int YR;
	private int PGS;
	private int gears;//number of gears. if gears = 0 then the clutch is out; if gears < 3 then transmission needs work (14560 sub p1x)
	
	private String transType = "";//this is the empty string at the end of each car in files like JUNK0CAR
	private String Mfr1;
	private String CAM;
	private String DSCR; // line 11425 = player[i].DSCR = player[i].car.desc1 therefore this is not needed
	private String SM;//there is another of this in CarFix.java this is only used on one line
	private String MFR;//what is this for ?
	private String JOB;
	private String TR = "";
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lane
	 */
	public int getLane() {
		return lane;
	}

	/**
	 * @param lane the lane to set
	 */
	public void setLane(int lane) {
		this.lane = lane;
	}

	/**
	 * @return the savings
	 */
	public double getSavings() {
		return savings;
	}

	/**
	 * @param savings the savings to set
	 */
	public void setSavings(double savings) {
		this.savings = savings;
	}

	/**
	 * @return the distanceInFeet
	 */
	public double getDistanceInFeet() {
		return distanceInFeet;
	}

	/**
	 * @param distanceInFeet the distanceInFeet to set
	 */
	public void setDistanceInFeet(double distanceInFeet) {
		this.distanceInFeet = distanceInFeet;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return the h
	 */
	public double getH() {
		return H;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(double h) {
		H = h;
	}

	/**
	 * @return the p
	 */
	public double[] getP() {
		return P;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(double[] p) {
		P = p;
	}

	/**
	 * @return the feet
	 */
	public double getFeet() {
		return feet;
	}

	/**
	 * @param feet the feet to set
	 */
	public void setFeet(double feet) {
		this.feet = feet;
	}

	/**
	 * @return the maxAccel
	 */
	public double getMaxAccel() {
		return maxAccel;
	}

	/**
	 * @param maxAccel the maxAccel to set
	 */
	public void setMaxAccel(double maxAccel) {
		this.maxAccel = maxAccel;
	}

	/**
	 * @return the pV
	 */
	public double getPV() {
		return PV;
	}

	/**
	 * @param pV the pV to set
	 */
	public void setPV(double pV) {
		PV = pV;
	}

	/**
	 * @return the hS
	 */
	public double getHS() {
		return HS;
	}

	/**
	 * @param hS the hS to set
	 */
	public void setHS(double hS) {
		HS = hS;
	}

	/**
	 * @return the lT
	 */
	public double getLT() {
		return LT;
	}

	/**
	 * @param lT the lT to set
	 */
	public void setLT(double lT) {
		LT = lT;
	}

	/**
	 * @return the ei
	 */
	public double getEi() {
		return Ei;
	}

	/**
	 * @param ei the ei to set
	 */
	public void setEi(double ei) {
		Ei = ei;
	}

	/**
	 * @return the m
	 */
	public double getM() {
		return M;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(double m) {
		M = m;
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
	 * @return the weight
	 */
	public double[] getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double[] weight) {
		this.weight = weight;
	}

	/**
	 * @return the l3
	 */
	public double getL3() {
		return L3;
	}

	/**
	 * @param l3 the l3 to set
	 */
	public void setL3(double l3) {
		L3 = l3;
	}

	/**
	 * @return the l4
	 */
	public double getL4() {
		return L4;
	}

	/**
	 * @param l4 the l4 to set
	 */
	public void setL4(double l4) {
		L4 = l4;
	}

	/**
	 * @return the ratio
	 */
	public double[] getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(double[] ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
		DSCR = car.getDesc1();
		VLCD = car.getRepairsNeeded();
		YR = car.getYear();
		value = car.getPrice();
		MFR = car.getManufacturer();
		NTR = car.getNumOfGears();
		ratio[5] = car.getRearEndRatio();
		ratio[4] = car.getFourthGearRatio();
		ratio[3] = car.getThirdGearRatio();
		ratio[2] = car.getSecondGearRatio();
		ratio[1] = car.getFirstGearRatio();
		gears = car.getNumOfGears();
		W = car.getW();
		D = car.getD();
		//NTR = car.getNumOfGears();
		L4 = ratio[4];
		L3 = ratio[3];
	}

	/**
	 * @return the q
	 */
	public int getQ() {
		return Q;
	}

	/**
	 * @param q the q to set
	 */
	public void setQ(int q) {
		Q = q;
	}

	/**
	 * @return the nTR
	 */
	public int getNTR() {
		return NTR;
	}

	/**
	 * @param nTR the nTR to set
	 */
	public void setNTR(int nTR) {
		NTR = nTR;
	}

	/**
	 * @return the nC
	 */
	public int getNC() {
		return NC;
	}

	/**
	 * @param nC the nC to set
	 */
	public void setNC(int nC) {
		NC = nC;
	}

	/**
	 * @return the nB
	 */
	public int getNB() {
		return NB;
	}

	/**
	 * @param nB the nB to set
	 */
	public void setNB(int nB) {
		NB = nB;
	}

	/**
	 * @return the cN
	 */
	public int getCN() {
		return CN;
	}

	/**
	 * @param cN the cN to set
	 */
	public void setCN(int cN) {
		CN = cN;
	}

	/**
	 * @return the cR
	 */
	public int getCR() {
		return CR;
	}

	/**
	 * @param cR the cR to set
	 */
	public void setCR(int cR) {
		CR = cR;
	}

	/**
	 * @return the bRTH
	 */
	public int getBRTH() {
		return BRTH;
	}

	/**
	 * @param bRTH the bRTH to set
	 */
	public void setBRTH(int bRTH) {
		BRTH = bRTH;
	}

	/**
	 * @return the vLCD
	 */
	public int getVLCD() {
		return VLCD;
	}

	/**
	 * @param vLCD the vLCD to set
	 */
	public void setVLCD(int vLCD) {
		VLCD = vLCD;
	}

	/**
	 * @return the yR
	 */
	public int getYR() {
		return YR;
	}

	/**
	 * @param yR the yR to set
	 */
	public void setYR(int yR) {
		YR = yR;
	}

	/**
	 * @return the pGS
	 */
	public int getPGS() {
		return PGS;
	}

	/**
	 * @param pGS the pGS to set
	 */
	public void setPGS(int pGS) {
		PGS = pGS;
	}

	/**
	 * @return the gears
	 */
	public int getGears() {
		return gears;
	}

	/**
	 * @param gears the gears to set
	 */
	public void setGears(int gears) {
		this.gears = gears;
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

	/**
	 * @return the mfr1
	 */
	public String getMfr1() {
		return Mfr1;
	}

	/**
	 * @param mfr1 the mfr1 to set
	 */
	public void setMfr1(String mfr1) {
		Mfr1 = mfr1;
	}

	/**
	 * @return the cAM
	 */
	public String getCAM() {
		return CAM;
	}

	/**
	 * @param cAM the cAM to set
	 */
	public void setCAM(String cAM) {
		CAM = cAM;
	}

	/**
	 * @return the dSCR
	 */
	public String getDSCR() {
		return DSCR;
	}

	/**
	 * @param dSCR the dSCR to set
	 */
	public void setDSCR(String dSCR) {
		DSCR = dSCR;
	}

	/**
	 * @return the sM
	 */
	public String getSM() {
		return SM;
	}

	/**
	 * @param sM the sM to set
	 */
	public void setSM(String sM) {
		SM = sM;
	}

	/**
	 * @return the mFR
	 */
	public String getMFR() {
		return MFR;
	}

	/**
	 * @param mFR the mFR to set
	 */
	public void setMFR(String mFR) {
		MFR = mFR;
	}

	/**
	 * @return the jOB
	 */
	public String getJOB() {
		return JOB;
	}

	/**
	 * @param jOB the jOB to set
	 */
	public void setJOB(String jOB) {
		JOB = jOB;
	}

	/**
	 * @return the tR
	 */
	public String getTR() {
		return TR;
	}

	/**
	 * @param tR the tR to set
	 */
	public void setTR(String tR) {
		TR = tR;
	}
	
	public void setEngine(Engine e) {
		engine = e;
		P[1] = engine.getP1();
		P[2] = engine.getP2();
		P[3] = engine.getP3();
		P[4] = engine.getP4();
		P[5] = engine.getP5();
		P[6] = engine.getP6();
		P[7] = engine.getP7();
		NC = engine.getNC();
		NB = engine.getNB();
		CR = engine.getCR();
		CAM = engine.getCAM();
		BRTH = engine.getBRTH();
	}
	
	public Engine getEngine() {
		return engine;
	}
}