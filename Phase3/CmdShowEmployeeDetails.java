

public class CmdShowEmployeeDetails implements Command{
    @Override
    public void execute(String[] cmdParts)
    {
        Company c = Company.getInstance();
        c.findEmployee(cmdParts[1]).showDetails();
    }

}
