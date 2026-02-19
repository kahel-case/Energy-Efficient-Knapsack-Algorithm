package res;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NetworkNode extends Button {

    private int _id;
    private int _energyConsumption;
    private int _residualEnergy;
    private VBox _parent;

    private NodeInformation _info;

    public NetworkNode(int id, int energyConsumption, int residualEnergy, VBox parent) {
        this._id = id;
        this._energyConsumption = energyConsumption;
        this._residualEnergy = residualEnergy;
        this._parent = parent;
        this._info = new NodeInformation(_id, _energyConsumption, _residualEnergy);

        this.setPrefSize(15, 15);
        this.setMaxSize(15, 15);
        this.setStyle("-fx-background-color: #fca232; -fx-font-size: 1px;");

        this.hoverProperty().addListener((_1, _2, newValue) -> {
            if (newValue) {
                _parent.getChildren().add(_info);
            } else {
                _parent.getChildren().clear();
            }
        });
    }

    public int getNodeId() { return this._id; }
    public int getEnergyConsumption() { return this._energyConsumption; }
    public int getResidualEnergy() { return this._residualEnergy; }
}
