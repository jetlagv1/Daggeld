package com.luchtvaartforum.daggeld;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.*;
import android.os.Bundle;
import android.widget.Toast;


public class StopOver {
	private String airport;
	private Calendar onblocksdatetime = Calendar.getInstance();
	private Calendar offblocksdatetime = Calendar.getInstance();
	private Time onblockstime = new Time();
	private Time offblockstime = new Time();
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
		onblockstime.set(0, onblocksdatetime.get(Calendar.MINUTE), onblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		offblockstime.set(0, offblocksdatetime.get(Calendar.MINUTE), offblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		
	}
	
	public StopOver() {
		onblockstime.set(0, onblocksdatetime.get(Calendar.MINUTE), onblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		offblockstime.set(0, offblocksdatetime.get(Calendar.MINUTE), offblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		
	}
	
//	protected void onCreate(Bundle savedInstanceState) {
//		onblocksdatetime = Calendar.getInstance();
//		offblocksdatetime = Calendar.getInstance();
//		onblockstime.setToNow();//(0, onblocksdatetime.get(Calendar.MINUTE), onblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
//		offblockstime.setToNow();//(0, offblocksdatetime.get(Calendar.MINUTE), offblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
//	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//// SET METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setStopOver(String airport, Calendar on_blocks, Calendar off_blocks, 
							Double sundries_24, Double lunch_allowance, Double diner_allowance) {

		onblocksdatetime.setTime(on_blocks.getTime());
		offblocksdatetime.setTime(off_blocks.getTime());
		onblockstime.set(0, onblocksdatetime.get(Calendar.MINUTE), onblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		offblockstime.set(0, offblocksdatetime.get(Calendar.MINUTE), offblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		this.airport = airport;
		this.sundries_24 = sundries_24;
		this.lunch_allowance = lunch_allowance;
		this.diner_allowance = diner_allowance;
	}
		
	public void setAirport(String airport) {
		this.airport = airport;
	}
	
	public void setOnBlocks(Calendar on_blocks) {
		onblocksdatetime.setTime(on_blocks.getTime());
		onblockstime.set(0, onblocksdatetime.get(Calendar.MINUTE), onblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
	}
	
	public void setOnBlocksTimeHour(int onbth) {
		onblocksdatetime.set(Calendar.HOUR_OF_DAY, onbth);
		onblockstime.set (0, onblocksdatetime.get(Calendar.MINUTE), onbth, 0, 0, 0);
	}
	
	public void setOnBlocksTimeMinute(int onbtm) {
		onblocksdatetime.set(Calendar.MINUTE, onbtm);
		onblockstime.set (0, onbtm, onblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
	}
	
	public void setOffBlocks(Calendar off_blocks) {
		offblocksdatetime.setTime(off_blocks.getTime());
		offblockstime.set(0, offblocksdatetime.get(Calendar.MINUTE), offblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
	}

	public void setOffBlocksTimeHour(int offbth) {
		offblocksdatetime.set(Calendar.HOUR_OF_DAY, offbth);
		offblockstime.set (0, offblocksdatetime.get(Calendar.MINUTE), offbth, 0, 0, 0);
	}

	public void setOffBlocksTimeMinute(int offbtm) {
		offblocksdatetime.set(Calendar.MINUTE, offbtm);
		offblockstime.set (0, offbtm, offblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
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
		return onblocksdatetime;
	}
	
	public Integer getOnBlocksTime() {
		return onblockstime.hour;
	}
	
	public int getOnBlockTimeHour() {
		return onblocksdatetime.get(Calendar.HOUR_OF_DAY);
	}

	public int getOnBlockTimeMinute() {
		return onblocksdatetime.get(Calendar.MINUTE);
	}
	
	public Calendar getOffBlocksDatetime() {
		return offblocksdatetime;
	}
	
	public Integer getOffBlocksTime() {
		return offblockstime.hour;
	}
	
	public int getOffBlockTimeHour() {
		return offblocksdatetime.get(Calendar.HOUR_OF_DAY);
	}

	public int getOffBlockTimeMinute() {
		return offblocksdatetime.get(Calendar.MINUTE);
	}
	
	public Double getAllowance() {
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
			
		else if ((onblocksdatetime.get(Calendar.HOUR_OF_DAY) < 13) && (offblocksdatetime.get(Calendar.HOUR_OF_DAY) >= 13))
			return true;
			
		else if ((onblocksdatetime.get(Calendar.HOUR_OF_DAY) < 13) && (offblocksdatetime.get(Calendar.HOUR_OF_DAY) < 13) && offblockstime.before(onblockstime))
			return true;
			
		else if ((onblocksdatetime.get(Calendar.HOUR_OF_DAY) >= 13) && (offblocksdatetime.get(Calendar.HOUR_OF_DAY) >= 13) && (calcHoursMinusDaysOnGround() > 13))
			return true;
			
		return false;
	}

	private boolean isDiner() {
		
		if (calcMinutesOnGround() < 180)
			return false;
			
		else if ((onblocksdatetime.get(Calendar.HOUR_OF_DAY) < 20) && (offblocksdatetime.get(Calendar.HOUR_OF_DAY) >= 20))
			return true;

		else if ((onblocksdatetime.get(Calendar.HOUR_OF_DAY) < 20) && (offblocksdatetime.get(Calendar.HOUR_OF_DAY) < 20) && offblockstime.before(onblockstime))
			return true;
			
		else if ((onblocksdatetime.get(Calendar.HOUR_OF_DAY) >= 20) && (offblocksdatetime.get(Calendar.HOUR_OF_DAY) >= 20) && (calcHoursMinusDaysOnGround() > 4))
			return true;

		return false;
	}

	private Double calcSundries() {
		Double s = 0d;
		Integer hours = calcHoursMinusDaysOnGround();
		switch (hours) {
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
			
				throw new IllegalArgumentException("hours = " + hours.toString());
		}		
		return (s + calcDaysOnGround()) * sundries_24;
	}
	
	public Integer calcHoursMinusDaysOnGround() {
		return calcMinutesMinusDaysOnGround().intValue() / 60;
	}
	
	public Integer calcDaysOnGround() {		
		return (calcMinutesOnGround().intValue() / 1440);
	}
	
	public Long calcMinutesMinusDaysOnGround() {
		return (calcMinutesOnGround() - (calcDaysOnGround().longValue() * 1440));
	}
	
	public Long calcMinutesOnGround() {
		if (offblocksdatetime.before(onblocksdatetime))
			return 0l;
		return (Long) (offblocksdatetime.getTimeInMillis() - onblocksdatetime.getTimeInMillis()) / 60000;
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
