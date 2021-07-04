public class CmdChangeTeam extends RecordedCommand{
    String ename;
    String tname;
    String formertname;
    @Override
    public void execute(String[] cmdParts){
        try{
            if(cmdParts.length<3)
                throw new ExInsufficientArguments();
            Company company = Company.getInstance();
            ename = cmdParts[1];
            tname = cmdParts[2];

            if(company.findTeam(tname) == null)
                throw new ExTeamNotExist();
            if(company.findEmployee(ename) == null)
                throw new ExEmployeeNotExist();
            formertname = company.quitTeam(ename);
            if(tname.equals(formertname)){
                company.joinTeam(ename,formertname);
                throw new ExSameNewTeam();
            }
                
            company.changeTeam(ename,tname);
            addUndoCommand(this);
            clearRedoList();
            System.out.print("Done.");
        } catch(ExInsufficientArguments e){
            System.out.println(e.getMessage());
        } catch(ExTeamNotExist e){
            System.out.println(e.getMessage());
        } catch(ExEmployeeNotExist e){
            System.out.println(e.getMessage());
        } catch(ExSameNewTeam e){
            System.out.println(e.getMessage());
        } 
        
    }
                       
    
    @Override
    public void undoMe()
	{
		Company company = Company.getInstance();
        company.quitTeam(ename);
        company.joinTeam(ename,formertname);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		Company company = Company.getInstance();
        company.quitTeam(ename);
        company.joinTeam(ename, tname);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
    }
    
}
