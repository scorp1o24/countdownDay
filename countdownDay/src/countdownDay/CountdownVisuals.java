package countdownDay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownVisuals extends JFrame {
    private JLabel countdownLabel;
    private int hoursRemaining;
    private int minutesRemaining;
    private int secondsRemaining;
    private Timer timer;

    public CountdownVisuals(int hours) {
        this.hoursRemaining = hours;
        this.minutesRemaining = 0;
        this.secondsRemaining = 0;

        countdownLabel = new JLabel(String.format("%02d:%02d:%02d", hoursRemaining, minutesRemaining, secondsRemaining));
        countdownLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(countdownLabel, BorderLayout.CENTER);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCountdown();
            }
        });
        timer.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateCountdown() {
        if (hoursRemaining == 0 && minutesRemaining == 0 && secondsRemaining == 0) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Countdown complete!");
            dispose();
        } else {
            if (secondsRemaining == 0) {
                if (minutesRemaining == 0) {
                    hoursRemaining--;
                    minutesRemaining = 59;
                } else {
                    minutesRemaining--;
                }
                secondsRemaining = 59;
            } else {
                secondsRemaining--;
            }

            countdownLabel.setText(String.format("%02d:%02d:%02d", hoursRemaining, minutesRemaining, secondsRemaining));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CountdownVisuals(24);
            }
        });
    }
}
