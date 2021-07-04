import java.util.*;
public class Employee implements Comparable<Employee> {

    private String name;
    private Team inTeam;
    private Membership[] memberships;
    private int index = 0;

    public Employee(String n) {
        name = n;
        inTeam = null;
        memberships = new Membership[100];
    }

    public String getName() {
         return name;
    }
    public static Employee searchEmployee(ArrayList<Employee> list,String nameToSearch) {
        for(Employee e: list)
        {
            if(e.getName().equals(nameToSearch))
            {
                return e;
            }
        }
        return null;
    }

    public String getTeamName(){
        if(inTeam == null)
            return null;
        else
            return inTeam.getName();
    }


    public void setTeam(Team t){
        inTeam = t;
    }

    public void addMembership(Team t, Day d){
        memberships[index] = new Membership(t,d);
        index++;
    }

    public void addLeaderMembership(Team t, Day d){
        memberships[index] = new LeaderMembership(t,d);
        index++;
    }

    public void setLeaveDay(Day d){
        memberships[index-1].setLeaveDay(d);
    }

    public void showDetails(){
        String result = "The teams that " + name + " has joined:\n";
        for(int i=0;i<index;i++){
            result += memberships[i].getDetail() + "\n";
        }
        System.out.println(result);
    }

    public Day getJoinDate(String tname){
        for(Membership m: memberships){
            if(m.getJoinedTeam().equals(tname))
                return m.getJoinDate();
        }
        return null;
    }

    public Day getLeaveDate(String tname){
        for(Membership m: memberships){
            if(m.getJoinedTeam().equals(tname))
                return m.getLeaveDate();
        }
        return null;
    }

    @Override
    public int compareTo(Employee another) 
    {
        if (this.name.equals(another.name)) return 0;
        else if (this.name.compareTo(another.name)>0) return 1;
        else return -1;
    }

    /*public int compareTo(Employee another) 
    {
        return this.name.compareTo(another.name);
    }*/

}
