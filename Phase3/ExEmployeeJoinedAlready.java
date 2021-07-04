

public class ExEmployeeJoinedAlready extends Exception{
        /**
         *
         */
        private static final long serialVersionUID = 1L;
    
        public ExEmployeeJoinedAlready() {
            super("Employee has joined a team already.");
        }
        public ExEmployeeJoinedAlready (String message) { super(message); }
}