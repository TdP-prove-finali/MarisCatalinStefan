package model;

public class BenchmarkOutput {
	
	private int countBoth;
	private int countTH;
	private int  countCT;
	
	public BenchmarkOutput(int countBoth, int countTH, int countCT) {
		
		this.countBoth = countBoth;
		this.countTH = countTH;
		this.countCT = countCT;
	}

	public int getCountBoth() {
		return countBoth;
	}

	public int getCountTH() {
		return countTH;
	}

	public int getCountCT() {
		return countCT;
	}
	
	
	

}
