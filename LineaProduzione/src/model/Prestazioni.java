package model;

import java.time.LocalDate;

public class Prestazioni {

	private double cycleTime;
	private double workInProcess;

	private LocalDate data;
	
	public Prestazioni( LocalDate data) {
		
		this.data = data;
		cycleTime=0;
		workInProcess=0;
	}

	

	public void setCycleTime(double cycleTime) {
		this.cycleTime = this.cycleTime+ cycleTime;
	}


	public double getCycleTime() {
		return cycleTime;
	}
	
	public double getThroughput() {
		return workInProcess/cycleTime;
	}

	public LocalDate getData() {
		return data;
	}
	
	public double getWorkInProcess() {
		return workInProcess;
	}

	public void setWorkInProcess(double workInProcess) {
		this.workInProcess =this.workInProcess + workInProcess;
	}
	
	
	
	
	
	
}
