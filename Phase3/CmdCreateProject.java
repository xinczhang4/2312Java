public class CmdCreateProject extends RecordedCommand{
    private Project p;

    @Override
    public void execute(String[] cmdParts) {
        try{
            if (cmdParts.length<3)
                throw new ExInsufficientArguments();
            Company company = Company.getInstance();
            if(company.findProject(cmdParts[1]) != null)
                throw new ExDuplicateProject();
            if(Integer.parseInt(cmdParts[2])<=0)
                throw new ExNegativeNumber(Integer.parseInt(cmdParts[2]));
            
            p = company.createProject(cmdParts[1],Integer.parseInt(cmdParts[2]));
            addUndoCommand(this); // <====== store this command (addUndoCommand is implemented in
                                    // RecordedCommand.java)
            clearRedoList(); // <====== There maybe some commands stored in the redo list. Clear them.    
            System.out.println("Done.");
            
                
            
        } catch (ExDuplicateProject e) {
			System.out.println(e.getMessage());
        } catch (ExNegativeNumber e) {
			System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
			System.out.println("Wrong number format -- Please enter an integer.");
        } catch(ExInsufficientArguments e){
            System.out.println(e.getMessage());
        }
       
    }
    @Override
    public void undoMe()
	{
		Company company = Company.getInstance();
		company.removeProject(p);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		Company company = Company.getInstance();
		company.addProject(p);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
    }
    
}
