package com.luchtvaartforum.daggeld;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.*;

public class StopOver {
	private String airport;
	private Calendar onbdt = Calendar.getInstance();
	private Calendar offbdt = Calendar.getInstance();
	private int onbth;
	private int onbtm;
	private int offbth;
	private int offbtm;
	//private Double sundries = 0d;
	private Double sundries_24 = 0d;
	private Double lunch_allowance = 0d;
	private Double diner_allowance = 0d;
	
	public StopOver(String airport, Calendar on_blocks, Calendar off_blocks, 
			Double sundries_24, Double lunch_allowance, Double diner_allowance) {
		this.airport = airport;
		this.sundries_24 = sundries_24;
		this.lunch_allowance = lunch_allowance;
		this.diner_allowance = diner_allowance;
		setOnBlocks(on_blocks);
		setOffBlocks(off_blocks);
	}
	
	public StopOver() {
//		onbdt = Calendar.getInstance();
//		offbdt = Calendar.getInstance();
//		airport = "";
//		sundries_24 = 0d;
//		lunch_allowance = 0d;
//		diner_allowance = 0d;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//// SET METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setStopOver(String airport, Calendar on_blocks, Calendar off_blocks, 
			Double sundries_24, Double lunch_allowance, Double diner_allowance) {
		
			setOnBlocks(on_blocks);
			setOffBlocks(off_blocks);
			this.airport = airport;
			this.sundries_24 = sundries_24;
			this.lunch_allowance = lunch_allowance;
			this.diner_allowance = diner_allowance;
		}
		
	public void setAirport(String airport) {
		this.airport = airport;
	}
	
	public void setOnBlocks(Calendar on_blocks) {
		onbdt.set(Calendar.YEAR, on_blocks.get(Calendar.YEAR));
		onbdt.set(Calendar.MONTH, on_blocks.get(Calendar.MONTH));
		onbdt.set(Calendar.DAY_OF_MONTH, on_blocks.get(Calendar.DAY_OF_MONTH));	
		onbdt.set(Calendar.HOUR_OF_DAY, on_blocks.get(Calendar.HOUR_OF_DAY));
		onbdt.set(Calendar.MINUTE, on_blocks.get(Calendar.MINUTE));
		onbth = onbdt.get(Calendar.HOUR_OF_DAY);
		onbtm = onbdt.get(Calendar.MINUTE);
		offbth = offbdt.get(Calendar.HOUR_OF_DAY);
		offbtm = offbdt.get(Calendar.MINUTE);
	}
	
	public void setOnBlocksTimeHour(int onbth) {
		this.onbth = onbth;
		onbdt.set(Calendar.HOUR_OF_DAY, onbth);
	}
	
	public void setOnBlocksTimeMinute(int onbtm) {
		this.onbtm = onbtm;
		onbdt.set(Calendar.MINUTE, onbtm);
	}
	
	public void setOffBlocks(Calendar off_blocks) {
		offbdt.set(Calendar.YEAR, off_blocks.get(Calendar.YEAR));
		offbdt.set(Calendar.MONTH, off_blocks.get(Calendar.MONTH));
		offbdt.set(Calendar.DAY_OF_MONTH, off_blocks.get(Calendar.DAY_OF_MONTH));	
		offbdt.set(Calendar.HOUR_OF_DAY, off_blocks.get(Calendar.HOUR_OF_DAY));
		offbdt.set(Calendar.MINUTE, off_blocks.get(Calendar.MINUTE));
	}

	public void setOffBlocksTimeHour(int offbth) {
		this.offbth = offbth;
		offbdt.set(Calendar.HOUR_OF_DAY, offbth);
	}

	public void setOffBlocksTimeMinute(int offbtm) {
		this.offbtm = offbtm;
		offbdt.set(Calendar.MINUTE, offbtm);
	}
	
	public void setSundries_24(Double sundries_24) {
		this.sundries_24 = sundries_24;
	}
	
	public void setLunchAllowance(Double lunch_allowance) {
		this.lunch_allowance = lunch_allowance;
	}
	
	public void setDinerAllowance(Double diner_allowance) {
		this.diner_allowance = diner_allowance;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//// GET METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getAirport() {
		return airport;
	}
	
	public Calendar getOnBlocksDatetime() {
		return onbdt;
	}
	
	public int getOnBlockTimeHour() {
		return onbth;
	}

	public int getOnBlockTimeMinute() {
		return onbtm;
	}
	
	public Calendar getOffBlocksDatetime() {
		return offbdt;
	}
	
	public int getOffBlockTimeHour() {
		return offbth;
	}

	public int getOffBlockTimeMinute() {
		return offbtm;
	}
	
	public double getAllowance() {
		return getSundries() + (calcNrLunch() * lunch_allowance) + (calcNrDiner() * diner_allowance);		
	}

	protected Double getSundries_24() {
		return sundries_24;
	}

	protected Double getLunchAllowance() {
		return lunch_allowance;
	}

	protected Double getDinerAllowance() {
		return diner_allowance;
	}

	protected Double getSundries() {
		return calcSundries();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	//// CALCULATING GET METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean isLunch() {
		if (calcMinutesOnGround() < 180) 
			return false;
			
		else if ((onbdt.get(Calendar.HOUR_OF_DAY) < 13) && (offbdt.get(Calendar.HOUR_OF_DAY) >= 13))
			return true;
			
		else if (calcMinutesMinusDaysOnGround() <= (11 * 60))
			return false;
			
		else if ((onbdt.get(Calendar.HOUR_OF_DAY) >= 13) && (offbdt.get(Calendar.HOUR_OF_DAY) >= 13))
			return true;
			
		else if ((onbdt.get(Calendar.HOUR_OF_DAY) < 13) && (offbdt.get(Calendar.HOUR_OF_DAY) < 13))
			return true;
			
		return false;
	}

	private boolean isDiner() {
		if (calcMinutesOnGround() < 180) 
			return false;

		else if ((onbdt.get(Calendar.HOUR_OF_DAY) < 20) && (offbdt.get(Calendar.HOUR_OF_DAY) >= 20))
			return true;

		else if (calcMinutesMinusDaysOnGround() <= (4 * 60))
			return false;
			
		else if ((onbdt.get(Calendar.HOUR_OF_DAY) >= 20) && (offbdt.get(Calendar.HOUR_OF_DAY) >= 20))
			return true;

		else if ((onbdt.get(Calendar.HOUR_OF_DAY) < 20) && (offbdt.get(Calendar.HOUR_OF_DAY) < 20))
			return true;

		return false;
		}

	private Double calcSundries() {
		Double s;
		switch(calcHoursMinusDaysOnGround()) {
			case 0: case 1: case 2:
				s = 0.0;
				break;
			case 3: case 4: case 5:
				s = 0.25;
				break;
			case 6: case 7: case 8: case 9: case 10: case 11:
				s = 0.50;
				break;
			case 12: case 13: case 14: case 15: case 16: case 17:
				s = 0.75;
				break;
			case 18: case 19: case 20: case 21: case 22: case 23:
				s = 1.0;
				break;
			default:
				throw new IllegalArgumentException();
		}
		return (s + calcDaysOnGround()) * sundries_24;
	}
	
	public Integer calcHoursMinusDaysOnGround() {
		return calcMinutesMinusDaysOnGround() / 60;
	}
	
	public int calcDaysOnGround() {		
		return (calcMinutesOnGround() / 1440);
	}
	
	public Integer calcMinutesMinusDaysOnGround() {
		return (calcMinutesOnGround() - (calcDaysOnGround() * 1440));
	}
	
	public Integer calcMinutesOnGround() {
		if (offbdt.before(onbdt))
			return 0;
		return (int) (offbdt.getTimeInMillis() - onbdt.getTimeInMillis()) / 60000;
	}
	
	public Integer calcNrLunch() {
		if(isLunch())
			return calcDaysOnGround() + 1;
		else 
			return calcDaysOnGround();
	}
	
	public Integer calcNrDiner() {
		if(isDiner())
			return calcDaysOnGround() + 1;
		else 
			return calcDaysOnGround();
	}
}
