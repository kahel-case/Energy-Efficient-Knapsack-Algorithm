package res;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class NodeInformation extends TextFlow {

    public NodeInformation(int id, int energyConsumption, int residualEnergy) {
        Text text = new Text();

        this.setLineSpacing(5);
        text.setFont(Font.font("Monospaced", 12));

        text.setText(String.format("""
                            %-10s %-30d%n\
                            %-10s %-30s%n\
                            %-10s %-30s%n""",
                "Node ID:", id, "Energy Consumption:", energyConsumption, "Residual Energy:", residualEnergy));
        this.getChildren().add(text);
    }
}
