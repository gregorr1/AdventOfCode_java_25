import tools.StopWatch;

public class App {
    public static void main(String[] args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        Naloga1.naloga1_1();
        Naloga1.naloga1_2();

        stopWatch.stop();
        System.out.println("This took " + stopWatch.getElapsedTime() + " ms.");
    }
}
