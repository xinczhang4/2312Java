public class CmdStartNewDay extends RecordedCommand{
	private String d1 = SystemDate.getInstance().clone().toString();
	private String d2;
    public void execute(String[] cmdParts){
		
		d2 = cmdParts[1];
		SystemDate.getInstance().set(d2);
		addUndoCommand(this);
		clearRedoList(); 
		System.out.println("Done.");
		
		
	}
	
	@Override
    public void undoMe()
	{
		SystemDate.getInstance().set(d1);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)

	}
	
	@Override
	public void redoMe()
	{
		SystemDate.getInstance().set(d1);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}

}