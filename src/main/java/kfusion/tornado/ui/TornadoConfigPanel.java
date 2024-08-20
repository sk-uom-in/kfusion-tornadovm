package kfusion.tornado.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import kfusion.tornado.common.TornadoModel;
import uk.ac.manchester.tornado.api.TornadoBackend;
import uk.ac.manchester.tornado.api.common.TornadoDevice;
import uk.ac.manchester.tornado.api.runtime.TornadoRuntime;

public class TornadoConfigPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 4887971237978617495L;
    private static final String PANEL_NAME = "Tornado Configuration";

    final JComboBox<TornadoDevice> deviceComboBox;
    public final JCheckBox enableTornadoCheckBox;

    private final TornadoModel config;
    private int countDevices(TornadoBackend backend) {
        int deviceCount = 0;
        try {
            int index = 0;
            while (true) {
                backend.getDevice(index);
                deviceCount++;
                index++;
            }
        } catch (IndexOutOfBoundsException e) {
            // Finished counting devices
        } catch (Exception e) {
            System.err.println("Error counting devices: " + e.getMessage());
        }
        return deviceCount;
    }
    private TornadoDevice[] getAllTornadoDevices() {
        // System.out.println("Counting devices");
        final TornadoDevice[] devices;
        TornadoBackend driver = TornadoRuntime.getTornadoRuntime().getBackend(0);
        final List<TornadoDevice> tmpDevices = new ArrayList<>();
        if (driver != null) {
            // System.out.println("Counting devices");
            int deviceCount = countDevices(driver); // Use the new countDevices method
            // System.out.println("Device count: " + deviceCount); // Debugging line
            for (int devIndex = 0; devIndex < deviceCount; devIndex++) {
                try {
                    final TornadoDevice device = driver.getDevice(devIndex);
                    tmpDevices.add(device);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Device index out of bounds: " + devIndex); // Debugging line
                    break;
                }
            }
            devices = new TornadoDevice[tmpDevices.size()];
            tmpDevices.toArray(devices);
        } else {
            devices = new TornadoDevice[0];
        }
        return devices;
    }

    public TornadoConfigPanel(final TornadoModel config) {
        
        this.config = config;
        final TornadoDevice[] devices = getAllTornadoDevices();
        final TornadoDeviceSelection deviceSelectModel = new TornadoDeviceSelection(devices);
        deviceComboBox = new JComboBox<>();
        deviceComboBox.setModel(deviceSelectModel);
        deviceComboBox.setEnabled(false);
        deviceComboBox.addActionListener(this);

        enableTornadoCheckBox = new JCheckBox("Use Tornado");
        enableTornadoCheckBox.setSelected(false);
        enableTornadoCheckBox.addActionListener(this);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), PANEL_NAME));
        add(enableTornadoCheckBox);
        add(new JLabel("  Tornado Device:"));
        add(deviceComboBox);
    }

    public void updateModel() {
        config.setTornadoDevice((TornadoDevice) deviceComboBox.getSelectedItem());
        config.setUseTornado(enableTornadoCheckBox.isSelected());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        deviceComboBox.setEnabled(enableTornadoCheckBox.isSelected());
        updateModel();
    }
}
