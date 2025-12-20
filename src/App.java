import Naloga8.Naloga8;
import tools.StopWatch;

public class App {
    public static void main(String[] args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        Naloga8.naloga8_1();
        Naloga8.naloga8_2();

        stopWatch.stop();
        System.out.println("This took " + stopWatch.getElapsedTime() + " ms.");
    }
}
