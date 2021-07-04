public class CmdHire extends RecordedCommand{
    private Employee e;

    @Override
    public void execute(String[] cmdParts) {
        try{
            if (cmdParts.length<2)
				throw new ExInsufficientArguments();
            Company company = Company.getInstance();
            if(company.findEmployee(cmdParts[1]) != null)
                throw new ExDuplicateEmployee();
           
            e = company.createEmployee(cmdParts[1]);
            addUndoCommand(this); // <====== store this command (addUndoCommand is implemented in
                                    // RecordedCommand.java)
            clearRedoList(); // <====== There maybe some commands stored in the redo list. Clear them.
            System.out.println("Done.");
            
            
        } catch(ExInsufficientArguments e){
            System.out.println(e.getMessage());
        } catch(ExDuplicateEmployee e){
            System.out.println(e.getMessage());
        }
        
    }
    @Override
    public void undoMe()
	{
		Company company = Company.getInstance();
		company.removeEmployee(e);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		Company company = Company.getInstance();
		company.addEmployee(e);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
    }
    
}
