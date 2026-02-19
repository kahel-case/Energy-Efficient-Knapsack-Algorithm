package res;

import javafx.scene.Node;
import sys.main.HelloApplication;

import javax.swing.*;
import java.net.URL;

public class Utility {

    public static int[] compileWeights(Node[] nodes) {
        int[] energyConsumption = new int[nodes.length];
        int count = 0;
        for (Node node  : nodes) {
            energyConsumption[count] = ((NetworkNode) node).getEnergyConsumption();
            count++;
        }
        return energyConsumption;
    }

    public static int[] compileValues(Node[] nodes) {
        int[] residualEnergy = new int[nodes.length];
        int count = 0;
        for (Node node  : nodes) {
            residualEnergy[count] = ((NetworkNode) node).getResidualEnergy();
            count++;
        }
        return residualEnergy;
    }

    public static void mentira() {
        try {
            URL imageUrl = HelloApplication.class.getResource("/icons/nesesito400baros.jpg");
            if (imageUrl == null) {
                System.err.println("Image not found. Check the path.");
                return;
            }
            ImageIcon customIcon = new ImageIcon(imageUrl);
            JOptionPane.showMessageDialog(null, "Over 400? What for?", "400", JOptionPane.ERROR_MESSAGE, customIcon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading image.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
