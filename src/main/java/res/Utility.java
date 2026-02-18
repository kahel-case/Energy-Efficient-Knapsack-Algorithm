package res;

import javax.swing.*;

public class Utility {

    public static int validInteger(String number) {
        int n = 0;
        try {
            n = Integer.parseInt(number);
            if (n < 1) n = 1;
            if (n > 100) n = 100;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid integer!");
        }
        return n;
    }

    public static int validCapacity(String number) {
        int n = 0;
        try {
            n = Integer.parseInt(number);
            if (n < 1) n = 1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid integer!");
        }
        return n;
    }
}
