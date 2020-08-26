import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimerModel implements Runnable {

    private final AtomicBoolean running = new AtomicBoolean(false); // Feature Flag: START and STOP thread
    private final int sleepInterval = 1000;
    private Thread worker;
    private long startTime;
    private TimerDialog timerDialog;

    public void start(){
        worker = new Thread(this);
        worker.start();
    }

    public void stop() { running.set(false); } // Feature Flag: Set to false to kill thread.

    private String millisToDisplayFormat(long milliseconds) {
        String result = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliseconds),
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
        return result;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        running.set(true); // Feature Flag: Set to true to start new thread.

        new Thread(() -> {
            while (running.get()) {
                try {
                    SwingUtilities.invokeLater(() -> {  millisToDisplayFormat(elapsedTime()); });
                    Thread.sleep(sleepInterval);
                } catch (InterruptedException e){
                    System.out.println("ERROR: " + e);
                }
            }
        }).start();
    }

    public long elapsedTime(){
        return System.currentTimeMillis() - startTime;
    }
}
