import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLogging {
    private static final Logger logger = LogManager.getLogger(TestLogging.class);

    public static void main(String[] args) {
    	logger.info("This is an info message.");
    	
        logger.debug("This is a debug message.");
        System.out.println("no log()");
    }
    
}