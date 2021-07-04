public class CmdTakeProject extends RecordedCommand{
    String tname;
    String pname;
    String startDate;
    @Override
    public void execute(String[] cmdParts){
        try{
            if (cmdParts.length<4)
                throw new ExInsufficientArguments();
            Company company = Company.getInstance();
            tname = cmdParts[1];
            pname = cmdParts[2];
            startDate = cmdParts[3];
            String[] sDayParts = startDate.split("-");
            if(sDayParts.length<3)
                throw new ExInvalidDate();
            if(!(sDayParts[1].equals("Jan")||sDayParts[1].equals("Feb")||sDayParts[1].equals("Mar")
            ||sDayParts[1].equals("Apr")||sDayParts[1].equals("May")||sDayParts[1].equals("Jun")
            ||sDayParts[1].equals("Jul")||sDayParts[1].equals("Aug")||sDayParts[1].equals("Sep")
            ||sDayParts[1].equals("Oct")||sDayParts[1].equals("Nov")||sDayParts[1].equals("Dec")))
                throw new ExInvalidDate();
            else{
                Day sDay = new Day(startDate);
                if(!sDay.valid())
                    throw new ExInvalidDate();
            }
            if(company.findTeam(tname) == null)
                throw new ExTeamNotExist();
            if(company.findProject(pname) == null)
                throw new ExProjectNotExist();
            if(SystemDate.getInstance().toString().equals(startDate))
                throw new ExStartToday();
            if(company.findProject(pname).hasTeam())
                throw new ExProjectAlreadyAssigned();
            if(company.checkAvailable(tname,pname,startDate) != null){
                String date = company.checkAvailable(tname,pname,startDate);
                throw new ExTeamNotAvailable(tname,date);
            }
                

            company.takeProject(tname, pname, startDate);
            addUndoCommand(this);
            clearRedoList();
            System.out.print("Done.");
        } catch(ExInsufficientArguments e){
            System.out.println(e.getMessage());
        } catch(ExTeamNotExist e){
            System.out.println(e.getMessage());
        } catch(ExProjectNotExist e){
            System.out.println(e.getMessage());
        } catch(ExStartToday e){
            System.out.println(e.getMessage());
        } catch(ExProjectAlreadyAssigned e){
            System.out.println(e.getMessage());
        } catch(ExTeamNotAvailable e){
            System.out.println(e.getMessage());
        } catch(ExInvalidDate e){
            System.out.println(e.getMessage());
        }
    }  
    
    @Override
    public void undoMe()
	{
		Company company = Company.getInstance();
        company.deleteProject(tname, pname);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
        
        Company company = Company.getInstance();
        company.takeProject(tname, pname, startDate);
        addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
        
		
    }
    
}
