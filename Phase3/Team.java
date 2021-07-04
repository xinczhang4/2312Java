import java.util.*;
public class Team implements Comparable<Team> {

	private String teamName;
	private Employee leader;
    private Day dateSetup;
    private Day suggestStartDay;
    private ArrayList<Employee> members;
    private ArrayList<Employee> allMembersNoRemove;
    private ArrayList<Project> allProjects;

    public Team(String n, Employee ld) {
        teamName = n;
        leader = ld;
        dateSetup = SystemDate.getInstance().clone();
        members = new ArrayList<>();
        allMembersNoRemove = new ArrayList<>();
        allProjects = new ArrayList<>();
    }

    public String getName(){
        return teamName;
    }

    public Employee getLeader(){
        return leader;
    }

    public ArrayList<Employee> getMember(){
        return members;
    }

    public ArrayList<Employee> getAllMember(){
        return allMembersNoRemove;
    }

    public String getMemberString(){
        String result = "";
        for(Employee e: members){
            result += e.getName() + " ";
        }
        return result.trim();
    }

    public void addMember(Employee e){
        members.add(e);
        Collections.sort(members);
    }

    public void addAllMember(Employee e){
        allMembersNoRemove.add(e);
        Collections.sort(allMembersNoRemove);
    }

    public void removeMember(Employee e){
        members.remove(e);
    }

    public int getNum(){
        return members.size()+1;
    }

    public void addProject(Project p){
        allProjects.add(p);
    }

    public void removeProject(Project p){
        allProjects.remove(p);
    }

    public ArrayList<Project> getProjects(){
        return allProjects;
    }

    public void setStartDay(Day d){
        suggestStartDay = d;
    }

    public Day getStartDay(){
        return suggestStartDay;
    }

    public static void list(ArrayList<Team> list) {
        System.out.printf("%-15s%-10s%-14s%-20s \n", "Team Name", "Leader", "Setup Date", "Members");
        for (Team t : list)
            if(t.members.size()==0)
                System.out.printf("%-15s%-10s%-14s%-20s \n", t.teamName, t.leader.getName(), t.dateSetup, "(no member)");
            else
                System.out.printf("%-15s%-10s%-14s%-20s \n", t.teamName, t.leader.getName(), t.dateSetup, t.getMemberString()); //display t.teamName, etc..
    }

    @Override
    public int compareTo(Team another){
        return this.teamName.compareTo(another.teamName);
    }
}
