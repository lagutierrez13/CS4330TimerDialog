import javax.swing.*;

public class TimerModel {
    private long startTime;

    public void start(){
        startTime = System.currentTimeMillis();
        new Thread(() -> {
            while (isRunning()) {
                SwingUtilities.invokeLater(() -> { System.out.println(System.currentTimeMillis() - startTime); });
                try{
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                }catch (InterruptedException e){
                    System.out.println("ERROR");
                }
            }
        }).start();
    }

    public void stop() { Thread.currentThread().interrupt(); } // change to not be interrupt cuz it doesn't work

    public long elapsedTime(){
        return System.currentTimeMillis() - startTime;
    }

    public boolean isRunning() { return Thread.currentThread().isAlive(); }
}
