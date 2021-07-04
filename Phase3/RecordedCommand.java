import java.util.*;
public abstract class RecordedCommand implements Command{
    
    public abstract void undoMe();
    public abstract void redoMe();

    private static ArrayList<RecordedCommand> undoList = new ArrayList<>();
    private static ArrayList<RecordedCommand> redoList = new ArrayList<>();

    protected static void addUndoCommand(RecordedCommand cmd)
    {
        undoList.add(cmd);
    }
    protected static void addRedoCommand(RecordedCommand cmd)
    {
        redoList.add(cmd);
    }

    protected static void clearRedoList()
    {
        redoList.clear();
    }

    protected static void undoOneCommand()
    {
        RecordedCommand c = undoList.remove(undoList.size()-1);
        c.undoMe();
    }

    public static void redoOneCommand()
    {
        RecordedCommand c = redoList.remove(redoList.size()-1);
        c.redoMe();
    }

    public static boolean undoHasNext()
    {
        if(undoList.isEmpty())
            return false;
        else return true;
    }

    public static boolean redoHasNext()
    {
        if(redoList.isEmpty())
            return false;
        else return true;
    }

}

    
