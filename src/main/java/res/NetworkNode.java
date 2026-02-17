package res;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class NetworkNode extends Button {

    private int _id;
    private int _energyConsumption;
    private int _residualEnergy;
    private VBox _parent;

    private NodeInformation _info;

    public final Image iconDefault = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/circle.png")));
    public final Image iconClusterHead = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/star-inside-circle.png")));

    ImageView iconDisplayDefault = new ImageView(iconDefault);
    ImageView iconDisplayClusterHead = new ImageView(iconClusterHead);

    public NetworkNode(int id, int energyConsumption, int residualEnergy, VBox parent) {
        this._id = id;
        this._energyConsumption = energyConsumption;
        this._residualEnergy = residualEnergy;
        this._parent = parent;
        this._info = new NodeInformation(_id, _energyConsumption, _residualEnergy);

        this.iconDisplayDefault.setFitHeight(15);
        this.iconDisplayDefault.setFitWidth(15);
        this.iconDisplayClusterHead.setFitHeight(25);
        this.iconDisplayClusterHead.setFitWidth(25);

        this.setPrefSize(15, 15);
        this.setMaxSize(15, 15);
        this.setStyle("-fx-background-color: #fca232; -fx-font-size: 1px;");
        //this.setStyle("-fx-background-color: transparent;");
        //this.setGraphic(iconDisplayDefault);

        this.hoverProperty().addListener((_, _, newValue) -> {
            if (newValue) {
                _parent.getChildren().add(_info);
            } else {
                _parent.getChildren().clear();
            }
        });
    }

    public int getNodeId() { return this._id; }
}
