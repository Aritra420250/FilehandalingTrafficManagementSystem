package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TrafficManagementSystem extends JFrame {
    private Signal[] signals;
    private int currentIndex = 0;
    private int rounds = 0;
    private final int maxRounds = 2;

    public TrafficManagementSystem() {
        setTitle("Traffic Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridLayout(2, 2));

        signals = new Signal[] {
            new TrafficSignal("North", 10),
            new TrafficSignal("South", 5),
            new TrafficSignal("East", 15),
            new TrafficSignal("West", 7)
        };

        for (Signal s : signals) {
            add(s);
        }

        setVisible(true);
        startTrafficCycle();
    }

    private void startTrafficCycle() {
        Timer controller = new Timer();

        controller.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (rounds >= maxRounds) {
                    controller.cancel();
                    JOptionPane.showMessageDialog(null, "✅ All traffic signal simulations completed.");
                    return;
                }

                Signal current = signals[currentIndex];
                int greenTime = calculateGreenTime(current.getVehicleCount());
                current.allowTraffic(greenTime);

                currentIndex++;
                if (currentIndex == signals.length) {
                    currentIndex = 0;
                    rounds++;
                }
            }
        }, 0, 5000); 
    }

    public static int calculateGreenTime(int vehicleCount) {
        int min = 5;
        int max = 30;
        return Math.max(min, Math.min(vehicleCount, max));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrafficManagementSystem::new);
    }
}
