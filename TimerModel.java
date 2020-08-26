import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimerModel implements Runnable {
    private final AtomicBoolean running = new AtomicBoolean(false); // Feature Flag: START and STOP thread
    private Thread worker;
    private long startTime;
    private int sleepInterval;

    public TimerModel(int sleepInterval) {
        sleepInterval = sleepInterval;
    }

    public void start(){
        worker = new Thread(this);
        worker.start();
    }


    public void stop() { running.set(false); } // Feature Flag: Set to false to kill thread.

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        running.set(true); // Feature Flag: Set to true to start new thread.

        new Thread(() -> {
            while (running.get()) {
                long displayTime = System.currentTimeMillis() - startTime;
                try {
                    SwingUtilities.invokeLater(() -> { System.out.println(displayTime); });
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e){
                    System.out.println("ERROR");
                }
            }
        }).start();
    }
}
