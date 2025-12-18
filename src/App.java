import tools.StopWatch;

public class App {
    public static void main(String[] args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Naloga7.naloga7_1();
        Naloga7.naloga7_2();

        stopWatch.stop();
        System.out.println("This took " + stopWatch.getElapsedTime() + " ms.");
    }
}
