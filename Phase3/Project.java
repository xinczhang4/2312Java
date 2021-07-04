import java.util.*;

public class Project implements Comparable<Project>{
    private String code;
    private int esManpower;
    private Team asTeam;//assigned
    private DayPeriod period;


    public Project(String pname, int esMp) {
        code = pname;
        esManpower = esMp;
    }

    public String getCode() {
        return code;
    }
    
    public int getEsMp() {
        return esManpower;
    }

    public void setTeam(Team t){
        asTeam = t;
    }

    public boolean hasTeam(){
        return asTeam != null;
    }

    public void setDayPeriod(String startDate, int timeInterval){
        period = new DayPeriod(startDate, timeInterval);
    }

    public DayPeriod getDayPeriod(){
        return period;
    }

    public void resetDayPeriod(){
        period = null;
    }

    public void showDetails(){
        System.out.println("Est manpower : " + esManpower + " man-days");
        System.out.println("Team         : " + asTeam.getName() + " (Leader is " + asTeam.getLeader().getName() + ")");
        System.out.println("Work period  : " + period.getStartDay() + " to " + period.getEndDay());
        System.out.println("\nMembers: ");
        ArrayList<Employee> members = asTeam.getAllMember();
        for(Employee e: members){
            Day eJoinDate = e.getJoinDate(asTeam.getName());
            Day eLeaveDate = e.getLeaveDate(asTeam.getName());
            String start = period.getStartDay();
            String end = period.getEndDay();
            if(eJoinDate.getDayNum()>period.getEndDayNum())
                continue;
            if(eJoinDate.getDayNum()>period.getStartDayNum())
                start = eJoinDate.toString();
            if(eLeaveDate != null)
                if(eLeaveDate.getDayNum()<period.getStartDayNum())
                    continue;
                else if(eLeaveDate.getDayNum()<period.getEndDayNum())
                    end = eLeaveDate.toString();
            System.out.println(e.getName() + " (" + start + " to " + end + ")");
        }
    }

    public static void list(ArrayList<Project> list){
        System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", "Project", "Est manpower", "Team", "Start Day", "End Day");
        for (Project p : list)
            if(p.asTeam == null)
                System.out.printf("%-9s%-14s%-13s\n", p.code, p.esManpower +
                            " man-days", "(Not Assigned)");
            else
                System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", p.code, p.esManpower + " man-days", p.asTeam.getName(), p.period.getStartDay(), p.period.getEndDay());
    }


	@Override
    public int compareTo(Project another) {
        return this.code.compareTo(another.code);
    }
    
}
