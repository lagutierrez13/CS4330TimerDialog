import javax.swing.*;
import java.awt.*;

public class TimerDialog extends JDialog {

    /** Start this timer. */
    protected final JButton startButton = new JButton("Start");

    /** Stop this timer. */
    protected final JButton stopButton = new JButton("Stop");

    /** Display the elapsed time. */
    protected final JLabel timeDisplay =
            new JLabel("0:00:00", SwingConstants.CENTER);

    protected TimerModel timerModel = new TimerModel(1000);


    public TimerDialog() {
        super((JFrame) null, "Timer");
        setSize(new Dimension(200,120));
        configureUI();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void configureUI() {
        setLayout(new BorderLayout());
        add(timeDisplay, BorderLayout.CENTER);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        buttons.add(startButton);
        buttons.add(stopButton);
        add(buttons, BorderLayout.SOUTH);

        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> startClicked());

        stopButton.setFocusPainted(false);
        stopButton.addActionListener(e -> stopClicked());
    }

    /** Callback to be invoked when the start button is clicked.
     * Hook to be overridden by a subclass. */
    protected void startClicked() {
        timeDisplay.setText("0:00:00");
        timerModel.start();
    }

    /** Callback to be invoked when the stop button is clicked.
     * Hook to be overridden by a subclass. */
    protected void stopClicked() {
        timeDisplay.setText("Stop clicked.");
        timerModel.stop();
    }

    public static void main(String[] args) {
        new TimerDialog();
    }
}