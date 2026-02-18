package sys.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import res.NetworkNode;
import res.Utility;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private int nodePanelArea = 725;

    @FXML private Pane nodePanel;

    @FXML private Button buttonCreateNodes;
    @FXML private Button buttonDefaultSelection;
    @FXML private Button buttonEnhancedSelection;

    @FXML private VBox nodeInformationPanel;

    @FXML private Spinner nodeSpinner;
    @FXML private Spinner maxCapacitySpinner;

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

            node.setLayoutX(Math.random()*nodePanelArea);
            node.setLayoutY(Math.random()*nodePanelArea);
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

        Node[] children = nodePanel.getChildren().toArray(new Node[0]);
        if (!(children.length > 0)) {
            JOptionPane.showMessageDialog(null, "There must be one or more nodes created!");
            maxCapacitySpinner.getEditor().setText("");
            buttonDefaultSelection.setDisable(false);
            return;
        }

        int[] energyConsumption = new int[children.length];
        int[] residualEnergy = new int[children.length];
        int n = children.length;

        int count = 0;
        for (Node node  : children) {
            energyConsumption[count] = ((NetworkNode) node).getEnergyConsumption();
            residualEnergy[count] = ((NetworkNode) node).getResidualEnergy();
            count++;
        }

        for (int i = 0; i < children.length; i++) {
            System.out.println();
        }

        System.out.println("Default Selection Process!");
        buttonDefaultSelection.setDisable(false);
    }

    @FXML
    protected void onEnhancedSelection() {
        buttonEnhancedSelection.setDisable(true);
        System.out.println("Enhanced Selection Process!");
        buttonEnhancedSelection.setDisable(false);
    }
}