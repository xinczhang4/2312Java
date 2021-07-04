public class CmdSetupTeam extends RecordedCommand{
    private Team t;

    @Override
    public void execute(String[] cmdParts) {
        try{
            if (cmdParts.length<3)
                throw new ExInsufficientArguments();
            Company company = Company.getInstance();
            if(company.findTeam(cmdParts[1]) != null)
                throw new ExDuplicateTeam();
            if(company.findLeader(cmdParts[2]))
                throw new ExEmployeeJoinedAlready();
            if(company.findEmployee(cmdParts[2]) == null)
                throw new ExEmployeeNotExist();
        
            t = company.createTeam(cmdParts[1],cmdParts[2]);
            addUndoCommand(this); // <====== store this command (addUndoCommand is implemented in
                                    // RecordedCommand.java)
            clearRedoList(); // <====== There maybe some commands stored in the redo list. Clear them.
    
            System.out.println("Done.");
            
        } catch(ExInsufficientArguments e){
            System.out.println(e.getMessage());
        } catch(ExDuplicateTeam e){
            System.out.println(e.getMessage());
        } catch(ExEmployeeJoinedAlready e){
            System.out.println(e.getMessage());
        } catch(ExEmployeeNotExist e){
            System.out.println(e.getMessage());
        } 
        
    }
    @Override
    public void undoMe()
	{
		Company company = Company.getInstance();
		company.removeTeam(t);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		Company company = Company.getInstance();
		company.addTeam(t);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
    }
}
