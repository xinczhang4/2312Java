import java.util.ArrayList;
import java.util.Collections; //Provides sorting
public class Company {

    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;

    private static Company instance = new Company();

    private Company() 
    {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
        allProjects = new ArrayList<>();
    }

    public static Company getInstance() 
    {
        return instance;
    }

    public void listTeams() 
    {
        Team.list(allTeams);
    }

    public void listProjects(){
        Project.list(allProjects);
    }

    public Employee createEmployee(String name) // See how it is called in main()
    {
        Employee e = new Employee(name);
        allEmployees.add(e);
        Collections.sort(allEmployees);
        return e;
    }

    public void addEmployee(Employee e){
        allEmployees.add(e);
        Collections.sort(allEmployees);
    }

    public Team createTeam(String tname, String ename) // See how it is called in main()
    {
        Employee e = Employee.searchEmployee(allEmployees,ename);
        Team t = new Team(tname,e);
        e.setTeam(t);
        e.addLeaderMembership(t,SystemDate.getInstance().clone());
        allTeams.add(t);
        Collections.sort(allTeams); //allTeams
        return t; //Why return?  Ans: Later you'll find it useful for undoable comments.
    }

    public Project createProject(String pname, int esMp) // See how it is called in main()
    {
        Project p = new Project(pname, esMp);
        allProjects.add(p);
        Collections.sort(allProjects); //allProjects
        return p; //Why return?  Ans: Later you'll find it useful for undoable comments.
    }

    public void addTeam(Team t){
        allTeams.add(t);
        Collections.sort(allTeams);
    }

    public void addProject(Project p){
        allProjects.add(p);
        Collections.sort(allProjects);
    }

    public Employee findEmployee(String name)
    {
        for(Employee e: allEmployees)
            if(e.getName().equals(name))
                return e;
        return null;
    }

    public Team findTeam(String name)
    {
        for(Team t: allTeams)
            if(t.getName().equals(name))
                return t;
        return null;
    }

    public boolean findLeader(String name)
    {
        for(Team t: allTeams)
            if(t.getLeader().getName().equals(name))
                return true;
        return false;
    }

    public boolean findMember(String name)
    {
        for(Team t: allTeams)
         {
            if(t.getLeader().getName().equals(name))
                return true;
            ArrayList<Employee> members = t.getMember();
            for(Employee e: members)
            {
                if(e.getName().equals(name))
                    return true;
            }
        }
        return false;
    }

    public Project findProject(String name)
    {
        for(Project p: allProjects)
            if(p.getCode().equals(name))
                return p;
        return null;
    }


    public void removeEmployee(Employee e)
    {
        allEmployees.remove(e);
    }

    public void removeTeam(Team t)
    {
        allTeams.remove(t);
    }

    public void removeProject(Project p)
    {
        allProjects.remove(p);
    }

    public void listEmployees()
    {
        for(Employee e: allEmployees)
        {
            if(e.getTeamName() == null){
                System.out.println(e.getName());
            }
            else
                System.out.println(e.getName()+" (" + e.getTeamName() + ")");

        }
            
    }

    public void joinTeam(String ename, String tname){
        Employee e = findEmployee(ename);
        Team t = findTeam(tname);
        t.addMember(e);
        t.addAllMember(e);
        e.setTeam(t);
        e.addMembership(t,SystemDate.getInstance().clone());
    }

    public void changeTeam(String ename, String tname){
        Employee e = findEmployee(ename);
        Team t = findTeam(tname);
        t.addMember(e);
        t.addAllMember(e);
        e.setTeam(t);
        e.setLeaveDay(SystemDate.getInstance().clone());
        e.addMembership(t,SystemDate.getInstance().clone());
        
    }

    public String quitTeam(String ename){
        Employee e = findEmployee(ename);
        String tname = e.getTeamName();
        Team t = findTeam(tname);
        t.removeMember(e);
        return tname;
    }

    public void takeProject(String tname,String pname,String startDate){
        Project p = findProject(pname);
        Team t = findTeam(tname);
        p.setTeam(t);
        t.addProject(p);
        int timeInterval = 0;
        if(p.getEsMp()%t.getNum()==0)
            timeInterval = p.getEsMp()/t.getNum();
        else
            timeInterval = p.getEsMp()/t.getNum()+1;
        p.setDayPeriod(startDate, timeInterval);
    }

    public void deleteProject(String tname, String pname){
        Project p = findProject(pname);
        Team t = findTeam(tname);
        p.setTeam(null);
        p.resetDayPeriod();
        t.removeProject(p);
    }  

    public String checkAvailable(String tname, String pname, String sDate){
        String result = null;
        Project p = findProject(pname);
        Team t = findTeam(tname);
        if(t.getProjects() == null)
            result = null;
        else{
            Day startDay = new Day(sDate);
            int pStartDate = startDay.getDayNum();
            int timeInterval = 0;
            if(p.getEsMp()%t.getNum()==0)
                timeInterval = p.getEsMp()/t.getNum();
            else
                timeInterval = p.getEsMp()/t.getNum()+1;
            Day endDay = startDay.getEndDate(timeInterval);
            int pEndDate = endDay.getDayNum();
            ArrayList<Project> allProjects = t.getProjects();
            for(Project pro: allProjects){
                if(pname.equals(pro.getCode())){
                    continue;
                }
                else{
                    int tStartDate = pro.getDayPeriod().getStartDayNum();
                    int tEndDate = pro.getDayPeriod().getEndDayNum();          
                    if(tEndDate < pStartDate || pEndDate < tStartDate)
                        result = null;
                    else{
                        result =  "(" + startDay.toString() + " to " + endDay.toString() + ")." ;
                        break;
                    }
                }    
            }
            
        }
        return result;
    }

    public String suggestTeam(String pname){
        Project p = findProject(pname);
        Day endDate = new Day(2300,12,12);
        String result = "";
        ArrayList<Team> suggestTeams = new ArrayList<>();
        ArrayList<Project> allProjects;
        for(Team t: allTeams)
        {
            String tname = t.getName();
            int timeInterval = 0;
            if(p.getEsMp()%t.getNum()==0)
                timeInterval = p.getEsMp()/t.getNum();
            else
                timeInterval = p.getEsMp()/t.getNum()+1;
            if(checkAvailable(tname, pname, SystemDate.getInstance().clone().getEndDate(2).toString()) == null){
                Day endDate1 = SystemDate.getInstance().clone().getEndDate(1+timeInterval);
                if(endDate1.getDayNum()==endDate.getDayNum()){
                    t.setStartDay(SystemDate.getInstance().clone().getEndDate(2));
                    suggestTeams.add(t);
                }
                if(endDate1.getDayNum()<endDate.getDayNum()){
                    endDate = endDate1;
                    t.setStartDay(SystemDate.getInstance().clone().getEndDate(2));
                    suggestTeams.clear();
                    suggestTeams.add(t);
                }
            }
            else{
                allProjects = t.getProjects();
                for(Project pro: allProjects){
                    Day startDate1 = pro.getDayPeriod().getEDay().getEndDate(2);
                    if(checkAvailable(tname, pname, startDate1.toString())==null){
                        Day endDate2 = startDate1.getEndDate(timeInterval);
                        if(endDate2.getDayNum()==endDate.getDayNum()){
                            t.setStartDay(startDate1);
                            suggestTeams.add(t);
                        }
                        else if(endDate2.getDayNum()<endDate.getDayNum()){
                            endDate = endDate2;
                            t.setStartDay(startDate1);
                            suggestTeams.clear();
                            suggestTeams.add(t);
                        }  
                    }
                        
                }
            }
             
        }
        for(Team t1: suggestTeams){
            result += t1.getName() + " (Work period: " + t1.getStartDay().toString() + " to " + endDate.toString() + ")\n";
        }
        return result;
    }

}
