package sys.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import res.KnapsackAlgorithm;
import res.NetworkNode;
import res.Utility;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private Pane nodePanel;

    @FXML private Button buttonCreateNodes;
    @FXML private Button buttonDefaultSelection;
    @FXML private Button buttonEnhancedSelection;

    @FXML private VBox nodeInformationPanel;

    @FXML private Spinner<Integer> nodeSpinner;
    @FXML private Spinner<Integer> maxCapacitySpinner;

    private int numberOfNodes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void onCreateNodes() {
        nodePanel.getChildren().clear();
        buttonCreateNodes.setDisable(true);

        int n;

        // RESTRICTS: floats, letters, doubles
        try {
            n = Integer.parseInt(nodeSpinner.getEditor().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid integer.");
            nodeSpinner.getEditor().setText("");
            buttonCreateNodes.setDisable(false);
            return;
        }

        // RESTRICTS: Integers less than zero
        if (n < 0) {
            JOptionPane.showMessageDialog(null, "The number of nodes must not go below zero.");
            nodeSpinner.getEditor().setText("");
            buttonCreateNodes.setDisable(false);
            return;
        }

        // RESTRICTS: Integers greater than 100
        if (n > 100) {
            n = 100;
            nodeSpinner.getEditor().setText(String.valueOf(n));
            JOptionPane.showMessageDialog(null, "The number of nodes cannot exceed 100.");
        }

        numberOfNodes = n;

        // Assigns individual nodes random values
        // Also assigns their random location
        for (int i = 0; i < n; i++) {
            int energyConsumption = (int) Math.max(50, (Math.random() * 150));
            int residualEnergy = (int) Math.max(50, (Math.random()*300));

            NetworkNode node = new NetworkNode(i, energyConsumption, residualEnergy, nodeInformationPanel);

            int nodePanelArea = 725;
            node.setLayoutX(Math.random()* nodePanelArea);
            node.setLayoutY(Math.random()* nodePanelArea);
            nodePanel.getChildren().add(node);
        }

        buttonCreateNodes.setDisable(false);
    }

    @FXML
    protected void onDefaultSelection() {
        buttonDefaultSelection.setDisable(true);

        int capacity;

        // RESTRICTS: floats, letters, doubles
        try {
            capacity = Integer.parseInt(maxCapacitySpinner.getEditor().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid integer!");
            maxCapacitySpinner.getEditor().setText("");
            buttonDefaultSelection.setDisable(false);
            return;
        }

        // RESTRICTS: Integers less than zero
        if (capacity < 0) {
            JOptionPane.showMessageDialog(null, "The number of nodes must not go below zero.");
            maxCapacitySpinner.getEditor().setText("");
            buttonDefaultSelection.setDisable(false);
            return;
        }

        // ME TRAIS EL KAMBIO!
        if (capacity >= 400 && numberOfNodes > 80) {
            Utility.mentira();
            maxCapacitySpinner.getEditor().setText("");
            buttonDefaultSelection.setDisable(false);
            return;
        }

        // Collects all individual nodes inside the nodes panel and puts them into an array
        Node[] nodes = nodePanel.getChildren().toArray(new Node[0]);
        if (!(nodes.length > 0)) {
            JOptionPane.showMessageDialog(null, "There must be one or more nodes created!");
            buttonDefaultSelection.setDisable(false);
            return;
        }

        // Collects all weights and values for the algorithm
        int[] energyConsumption = Utility.compileWeights(nodes);
        int[] residualEnergy = Utility.compileValues(nodes);
        int pointer = nodes.length;

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        // THE ACTUAL DEFAULT ALGORITHM
        System.out.println("Default Selection Process: " + KnapsackAlgorithm.defaultSelection(capacity, energyConsumption, residualEnergy, pointer, nodes));

        long endTime = System.nanoTime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long duration = endTime - startTime;
        long memoryUsed = memoryAfter - memoryBefore;
        System.out.println("Execution Time: " + (duration / 1_000_000.0) + " ms");
        System.out.println("Memory Used: " + (memoryUsed / 1024.0) + " KB\n");
        JOptionPane.showMessageDialog(null,
                "Execution Time: " + (duration / 1_000_000.0) + " ms" + "\n" +
                        "Memory Used: " + (memoryUsed / 1024.0) + " KB");

        buttonDefaultSelection.setDisable(false);
    }

    @FXML
    protected void onEnhancedSelection() {
        buttonEnhancedSelection.setDisable(true);

        int capacity;

        // RESTRICTS: floats, letters, doubles
        try {
            capacity = Integer.parseInt(maxCapacitySpinner.getEditor().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid integer!");
            maxCapacitySpinner.getEditor().setText("");
            buttonEnhancedSelection.setDisable(false);
            return;
        }

        // RESTRICTS: Integers less than zero
        if (capacity < 0) {
            JOptionPane.showMessageDialog(null, "The number of nodes must not go below zero.");
            maxCapacitySpinner.getEditor().setText("");
            buttonEnhancedSelection.setDisable(false);
            return;
        }

        // Collects all individual nodes inside the nodes panel and puts them into an array
        Node[] nodes = nodePanel.getChildren().toArray(new Node[0]);
        if (!(nodes.length > 0)) {
            JOptionPane.showMessageDialog(null, "There must be one or more nodes created!");
            buttonEnhancedSelection.setDisable(false);
            return;
        }

        // Collects all weights and values for the algorithm
        int[] energyConsumption = Utility.compileWeights(nodes);
        int[] residualEnergy = Utility.compileValues(nodes);
        int pointer = nodes.length;

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        // THE ACTUAL ENHANCED ALGORITHM
        System.out.println("Enhanced Selection Process: " + KnapsackAlgorithm.enhancedSelection(capacity, energyConsumption, residualEnergy, pointer, nodes));

        long endTime = System.nanoTime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long duration = endTime - startTime;
        long memoryUsed = memoryAfter - memoryBefore;
        System.out.println("Execution Time: " + (duration / 1_000_000.0) + " ms");
        System.out.println("Memory Used: " + (memoryUsed / 1024.0) + " KB\n");
        JOptionPane.showMessageDialog(null,
                "Execution Time: " + (duration / 1_000_000.0) + " ms" + "\n" +
                "Memory Used: " + (memoryUsed / 1024.0) + " KB");

        buttonEnhancedSelection.setDisable(false);
    }
}