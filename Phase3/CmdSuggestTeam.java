public class CmdSuggestTeam implements Command{

    @Override
    public void execute(String[] cmdParts) {

        Company company = Company.getInstance();
        String pname = cmdParts[1];
        String result = company.suggestTeam(pname);
        System.out.println(result);
            
    }


}
