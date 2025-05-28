package gui;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TrafficSignal extends Signal {
    private Timer timer;
    String fileName = "output.txt";

    public TrafficSignal(String direction, int vehicleCount) {
        super(direction, vehicleCount); 
    }

    @Override
    public void allowTraffic(int durationInSeconds) {
        logColorChange("GREEN", durationInSeconds);
        setBackground(Color.GREEN);
        label.setText("GO: " + durationInSeconds + "s");

        if (timer != null) timer.cancel();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = durationInSeconds;

            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (count > 0) {
                        label.setText("GO: " + count + "s");
                        count--;
                    } else {
                        label.setText("RED");
                        setBackground(Color.RED);
                        logColorChange("RED", 0);
                        timer.cancel();
                    }
                });
            }
        }, 0, 1000);
    }

    private void logColorChange(String color, int duration) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String timestamp = LocalDateTime.now().toString();
            String log = String.format("[%s] %s signal changed to %s%s\n",
                    timestamp, direction, color,
                    color.equals("GREEN") ? " for " + duration + "s" : "");
            writer.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
