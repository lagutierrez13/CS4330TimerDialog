import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimerModel implements Runnable {
    private final AtomicBoolean running = new AtomicBoolean(false); // Feature Flag: START and STOP thread
    private Thread worker;
    private long startTime;
    private JLabel timeLabel;
    private final int sleepInterval = 1000;

    public TimerModel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }


    public void start(){
        worker = new Thread(this);
        worker.start();
    }


    public void stop() { running.set(false); }      // Feature Flag: Set to false to kill thread.


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

        running.set(true);                          // Feature Flag: Set to true to start new thread.

        new Thread(() -> {
            while (running.get()) {
                long displayTime = System.currentTimeMillis() - startTime;
                try {
                    SwingUtilities.invokeLater(() -> {
                        String display = millisToDisplayFormat(displayTime);

                        timeLabel.setText(display);
                    });
                    Thread.sleep(sleepInterval);
                    System.out.printf("Thread Name: %s, Elapsed Time: %s\n",
                            Thread.currentThread().getName(),
                            millisToDisplayFormat(displayTime));
                } catch (InterruptedException e){
                    System.err.println("ERROR");
                }
            }
        }).start();
    }
}
