import tools.StopWatch;

public class App {
    public static void main(String[] args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        Naloga6.naloga6_1();
        Naloga6.naloga6_2();

        stopWatch.stop();
        System.out.println("This took " + stopWatch.getElapsedTime() + " ms.");
    }
}
