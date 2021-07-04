

public class DayPeriod {

    private Day startDay;
    private Day endDay;

    public DayPeriod(String startDate, int timeInterval){
        startDay = new Day(startDate);
        endDay = startDay.getEndDate(timeInterval);
    }

    public Day getEDay(){
        return endDay;
    }

    public String getStartDay(){
        return startDay.toString();
    }

    public String getEndDay(){
        return endDay.toString();
    }

    public int getStartDayNum(){
        return startDay.getDayNum();
    }

    public int getEndDayNum(){
        return endDay.getDayNum();
    }
}
