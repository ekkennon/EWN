package com.ekk.drag1;

public class Engine {
	
	//read from engines file:
	private int BRTH; //***line 10810
	private int NC; //***line 10810
	private int NB; //***line 10810
	private int CR; //***line 10810
	
	private String mfgrE;//manufacturer from engine file (line 10390 from phase 1)
	private String CAM;
	
	private double P1;
	private double P2;
	private double P3;
	private double P4;
	private double P5;
	private double P6;
	private double P7;
	
	public Engine(String s1, double d1, double d2, double d3, double d4, double d5, double d6, double d7, int i1, int i2, int i3, String s2, int i4) {
		setMfgrE(s1);
		setP1(d1);
		setP2(d2);
		setP3(d3);
		setP4(d4);
		setP5(d5);
		setP6(d6);
		setP7(d7);
		setNC(i1);
		setNB(i2);
		setCR(i3);
		setCAM(s2);
		setBRTH(i4);
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
	 * @return the mfgrE
	 */
	public String getMfgrE() {
		return mfgrE;
	}

	/**
	 * @param mfgrE the mfgrE to set
	 */
	public void setMfgrE(String mfgrE) {
		this.mfgrE = mfgrE;
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
	 * @return the p1
	 */
	public double getP1() {
		return P1;
	}

	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(double p1) {
		P1 = p1;
	}

	/**
	 * @return the p2
	 */
	public double getP2() {
		return P2;
	}

	/**
	 * @param p2 the p2 to set
	 */
	public void setP2(double p2) {
		P2 = p2;
	}

	/**
	 * @return the p3
	 */
	public double getP3() {
		return P3;
	}

	/**
	 * @param p3 the p3 to set
	 */
	public void setP3(double p3) {
		P3 = p3;
	}

	/**
	 * @return the p4
	 */
	public double getP4() {
		return P4;
	}

	/**
	 * @param p4 the p4 to set
	 */
	public void setP4(double p4) {
		P4 = p4;
	}

	/**
	 * @return the p5
	 */
	public double getP5() {
		return P5;
	}

	/**
	 * @param p5 the p5 to set
	 */
	public void setP5(double p5) {
		P5 = p5;
	}

	/**
	 * @return the p6
	 */
	public double getP6() {
		return P6;
	}

	/**
	 * @param p6 the p6 to set
	 */
	public void setP6(double p6) {
		P6 = p6;
	}

	/**
	 * @return the p7
	 */
	public double getP7() {
		return P7;
	}

	/**
	 * @param p7 the p7 to set
	 */
	public void setP7(double p7) {
		P7 = p7;
	}
}
