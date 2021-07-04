public class Day implements Cloneable {

	private int year;
	private int month;
	private int day;

	// Constructor
	public Day(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
	}

	public int getDayNum(){
		return day + month*100 + year*10000;
	}

	// check if a given year is a leap year
	static public boolean isLeapYear(int y) {
		if (y % 400 == 0)
			return true;
		else if (y % 100 == 0)
			return false;
		else if (y % 4 == 0)
			return true;
		else
			return false;
	}

	// check if y,m,d valid
	static public boolean valid(int y, int m, int d) {
		if (m < 1 || m > 12 || d < 1)
			return false;
		switch (m) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return d <= 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return d <= 30;
			case 2:
				if (isLeapYear(y))
					return d <= 29;
				else
					return d <= 28;
		}
		return false;
	}


	// check if y,m,d valid
	public boolean valid() {
		if (month < 1 || month > 12 || day < 1)
			return false;
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return day <= 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return day <= 30;
			case 2:
				if (isLeapYear(year))
					return day <= 29;
				else
					return day <= 28;
		}
		return false;
	}


	// Return a string for the day like dd MMM yyyy
	/*
	 * public String toString() {
	 * 
	 * final String[] MonthNames = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
	 * "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	 * 
	 * return day+" "+ MonthNames[month-1] + " "+ year; }
	 */

	private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";

	public void set (String sDay)//Set year,month,day based on a string like 01-Jan-2020
	{		
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
		this.year = Integer.parseInt(sDayParts[2]); 
		this.month = MonthNames.indexOf(sDayParts[1])/3+1;//return -1
	}

	public Day(String sDay){
		set(sDay);
	} 

	public Day getEndDate(int timeInterval){
		Day endDay = this.clone();
		for(int i=0;i<timeInterval-1;i++){
			if(valid(endDay.year, endDay.month, endDay.day+1)){
				endDay.day++;
			}
			else if(valid(endDay.year, endDay.month+1, endDay.day)){
				endDay.month++;
				endDay.day = 1;
			}
			else{
				endDay.year++;
				endDay.month = 1;
				endDay.day = 1;
			}
		}
		return endDay;
	}

	public Day getLastDay(){
		Day lastDay = this.clone();

		if(valid(lastDay.year, lastDay.month, lastDay.day-1)){
			lastDay.day--;
		}
		else if(valid(lastDay.year, lastDay.month-1, lastDay.day)){
			lastDay.month--;
			if(lastDay.month == 1 || lastDay.month == 3 || lastDay.month == 5 
			|| lastDay.month == 7 || lastDay.month == 8 || lastDay.month == 10
			|| lastDay.month == 12)
				lastDay.day = 31;
			else if(lastDay.month == 4|| lastDay.month == 6 || lastDay.month == 9 || lastDay.month == 11)
				lastDay.day = 30;
			else if(lastDay.month == 2)
				if(isLeapYear(lastDay.year))
					lastDay.day = 29;
				else 
					lastDay.day = 28;
		}
		else{
			lastDay.year--;
			lastDay.month = 12;
			lastDay.day = 31;
		}
		
		return lastDay;
	}

	@Override
	public String toString() {
		return day + "-" + MonthNames.substring((month - 1) * 3, (month) * 3) + "-" + year;
	}

	@Override
	public Day clone() {
		Day copy = null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return copy;
	}
}
