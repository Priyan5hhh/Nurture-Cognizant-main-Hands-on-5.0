public class SingletonPatternExample {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger();
        logger.getLogger();
        Logger logger2 = Logger.getLogger();
        logger2.getLogger();
        System.out.println(logger == logger2);
    }
}

class Logger {
    private static Logger instance;
    private Logger() {
        System.out.println("Logger Object");
    }
    public static Logger getLogger(){
        if(instance == null) {
            instance = new Logger();
        }
        return instance;
    } 
}