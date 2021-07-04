
public class Membership {
    Team joinedTeam;
    Day joinDay;
    Day leaveDay;

    public Membership(Team t, Day d){
        joinedTeam = t;
        joinDay = d;
        leaveDay = null;
    }

    public String getDetail(){
        if(leaveDay == null)
            return joinedTeam.getName() + " (" + joinDay.toString() + " to --)";
        else 
            return joinedTeam.getName() + " (" + joinDay.toString() + " to " + leaveDay.toString() + ")";
    }

    public void setLeaveDay(Day d){
        leaveDay = d.getLastDay();
    }

    public String getJoinedTeam(){
        return joinedTeam.getName();
    }

    public String getJoinDay(){
        return joinDay.toString();
    }

    public Day getJoinDate(){
        return joinDay;
    }

    public Day getLeaveDate(){
        return leaveDay;
    }

}
