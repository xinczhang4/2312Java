

public class CmdShowProjectWorkerDetails implements Command{
    @Override
    public void execute(String[] cmdParts)
    {
        Company c = Company.getInstance();
        c.findProject(cmdParts[1]).showDetails();
    }

}

