import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ExInvalidDate {

		Scanner in = new Scanner(System.in);

		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		Scanner inFile = null;
		try{
			inFile = new Scanner(new File(filepathname));
		
			//The first command in the file must be to set the system date 
			//(eg. "startNewDay|01-Jan-2020"); and it cannot be undone
			String cmdLine1 = inFile.nextLine();
			String[] cmdLine1Parts = cmdLine1.split("\\|");
			System.out.println("\n> "+cmdLine1);
			SystemDate.createTheInstance(cmdLine1Parts[1]);
			
			while (inFile.hasNext())		
			{
				String cmdLine = inFile.nextLine().trim();
				
				//Blank lines exist in data file as separators.  Skip them.
				if (cmdLine.equals("")) continue;  
	
				System.out.println("\n> "+cmdLine);
				
				// split the words in actionLine => create an array of word strings
				String[] cmdParts = cmdLine.split("\\|"); 
				
				if (cmdParts[0].equals("undo"))
				{
					if(!RecordedCommand.undoHasNext())
						System.out.println("Nothing to undo.");
					else
						RecordedCommand.undoOneCommand();
				}				
				if (cmdParts[0].equals("redo"))
				{
					if(!RecordedCommand.redoHasNext())
						System.out.println("Nothing to redo.");
					else
						RecordedCommand.redoOneCommand();
				}
				if (cmdParts[0].equals("hire"))
					(new CmdHire()).execute(cmdParts);
				if (cmdParts[0].equals("listEmployees"))
					(new CmdListEmployees()).execute(cmdParts);
				if (cmdParts[0].equals("createProject"))
					(new CmdCreateProject()).execute(cmdParts);
				if (cmdParts[0].equals("setupTeam"))
					(new CmdSetupTeam()).execute(cmdParts);
				if (cmdParts[0].equals("startNewDay"))
					(new CmdStartNewDay()).execute(cmdParts);
				if (cmdParts[0].equals("listTeams"))
					(new CmdListTeams()).execute(cmdParts);
				if (cmdParts[0].equals("listProjects"))
					(new CmdListProjects()).execute(cmdParts);
				if (cmdParts[0].equals("joinTeam"))
					(new CmdJoinTeam()).execute(cmdParts);
				if (cmdParts[0].equals("changeTeam"))
					(new CmdChangeTeam()).execute(cmdParts);
				if (cmdParts[0].equals("takeProject"))
					(new CmdTakeProject()).execute(cmdParts);
				if (cmdParts[0].equals("suggestTeam"))
					(new CmdSuggestTeam()).execute(cmdParts);
				if (cmdParts[0].equals("showEmployeeDetails"))
					(new CmdShowEmployeeDetails()).execute(cmdParts);
				if (cmdParts[0].equals("showProjectWorkerDetails"))
					(new CmdShowProjectWorkerDetails()).execute(cmdParts);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		finally{
			if (inFile!=null) //If it has been opened successfully, close it
            inFile.close();
            in.close();

		}
	}
}

    
