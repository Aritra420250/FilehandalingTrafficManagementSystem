package gui;

import javax.swing.*;

public abstract class Signal extends JPanel {
    protected String direction;
    protected int vehicleCount;
    protected JLabel label;

    public Signal(String direction, int vehicleCount) {
        this.direction = direction;
        this.vehicleCount = vehicleCount;
        setupPanel();
    }

    private void setupPanel() {
        setBorder(BorderFactory.createTitledBorder(direction));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label = new JLabel("RED", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(16f));
        add(Box.createVerticalGlue());
        add(label);
        add(Box.createVerticalGlue());
        setBackground(java.awt.Color.RED);
    }

    public String getDirection() {
        return direction;
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public abstract void allowTraffic(int durationInSeconds);
}
