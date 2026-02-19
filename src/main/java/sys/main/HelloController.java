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

public class HelloController implements Initializable {

    @FXML private Pane nodePanel;

    @FXML private Button buttonCreateNodes;
    @FXML private Button buttonDefaultSelection;
    @FXML private Button buttonEnhancedSelection;

    @FXML private VBox nodeInformationPanel;

    @FXML private Spinner<Integer> nodeSpinner;
    @FXML private Spinner<Integer> maxCapacitySpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void onCreateNodes() {
        nodePanel.getChildren().clear();
        buttonCreateNodes.setDisable(true);

        int n = Utility.validInteger(nodeSpinner.getEditor().getText());
        if (n == 100 || n == 1) {
            nodeSpinner.getEditor().setText(String.valueOf(n));
        }

        for (int i = 0; i < n; i++) {
            int energyConsumption = (int) Math.max(50, (Math.random() * 150));
            int residualEnergy = (int) Math.max(50, (Math.random()*300));

            NetworkNode node = new NetworkNode(i, energyConsumption, residualEnergy, nodeInformationPanel);

            int nodePanelArea = 725;
            node.setLayoutX(Math.random()* nodePanelArea);
            node.setLayoutY(Math.random()* nodePanelArea);
            nodePanel.getChildren().add(node);
        }

        System.out.println("Node Created!");
        buttonCreateNodes.setDisable(false);
    }

    @FXML
    protected void onDefaultSelection() {
        buttonDefaultSelection.setDisable(true);

        int capacity;
        try {
            capacity = Integer.parseInt(maxCapacitySpinner.getEditor().getText());
            if (capacity < 1) { capacity = 1; }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid integer!");
            maxCapacitySpinner.getEditor().setText("");
            buttonDefaultSelection.setDisable(false);
            return;
        }
        maxCapacitySpinner.getEditor().setText(String.valueOf(capacity));

        Node[] nodes = nodePanel.getChildren().toArray(new Node[0]);
        if (!(nodes.length > 0)) {
            JOptionPane.showMessageDialog(null, "There must be one or more nodes created!");
            maxCapacitySpinner.getEditor().setText("");
            buttonDefaultSelection.setDisable(false);
            return;
        }

        int[] energyConsumption = new int[nodes.length];
        int[] residualEnergy = new int[nodes.length];
        int pointer = nodes.length;

        int count = 0;
        for (Node node  : nodes) {
            energyConsumption[count] = ((NetworkNode) node).getEnergyConsumption();
            residualEnergy[count] = ((NetworkNode) node).getResidualEnergy();
            count++;
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        System.out.println("Default Selection Process: " + KnapsackAlgorithm.defaultSelection(capacity, energyConsumption, residualEnergy, pointer, nodes));

        long endTime = System.nanoTime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long duration = endTime - startTime;
        long memoryUsed = memoryAfter - memoryBefore;
        System.out.println("Execution Time: " + (duration / 1_000_000.0) + " ms");
        System.out.println("Memory Used: " + (memoryUsed / 1024.0) + " KB");
        JOptionPane.showMessageDialog(null,
                "Execution Time: " + (duration / 1_000_000.0) + " ms" + "\n" +
                        "Memory Used: " + (memoryUsed / 1024.0) + " KB");

        buttonDefaultSelection.setDisable(false);
    }

    @FXML
    protected void onEnhancedSelection() {
        buttonEnhancedSelection.setDisable(true);
        int capacity;
        try {
            capacity = Integer.parseInt(maxCapacitySpinner.getEditor().getText());
            if (capacity < 1) { capacity = 1; }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid integer!");
            maxCapacitySpinner.getEditor().setText("");
            buttonEnhancedSelection.setDisable(false);
            return;
        }
        maxCapacitySpinner.getEditor().setText(String.valueOf(capacity));

        Node[] nodes = nodePanel.getChildren().toArray(new Node[0]);
        if (!(nodes.length > 0)) {
            JOptionPane.showMessageDialog(null, "There must be one or more nodes created!");
            maxCapacitySpinner.getEditor().setText("");
            buttonDefaultSelection.setDisable(false);
            return;
        }

        int[] energyConsumption = new int[nodes.length];
        int[] residualEnergy = new int[nodes.length];
        int pointer = nodes.length;

        int count = 0;
        for (Node node  : nodes) {
            energyConsumption[count] = ((NetworkNode) node).getEnergyConsumption();
            residualEnergy[count] = ((NetworkNode) node).getResidualEnergy();
            count++;
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        System.out.println("Enhanced Selection Process: " + KnapsackAlgorithm.enhancedSelection(capacity, energyConsumption, residualEnergy, pointer, nodes));

        long endTime = System.nanoTime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long duration = endTime - startTime;
        long memoryUsed = memoryAfter - memoryBefore;
        System.out.println("Execution Time: " + (duration / 1_000_000.0) + " ms");
        System.out.println("Memory Used: " + (memoryUsed / 1024.0) + " KB");
        JOptionPane.showMessageDialog(null,
                "Execution Time: " + (duration / 1_000_000.0) + " ms" + "\n" +
                "Memory Used: " + (memoryUsed / 1024.0) + " KB");

        buttonEnhancedSelection.setDisable(false);
    }
}