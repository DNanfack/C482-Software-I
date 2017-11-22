package rcases.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PartScreenController implements Initializable  {

    @FXML
    private RadioButton inhouseRadioButton;

    @FXML
    private Label companyMachineLabel;

    @FXML
    private TextField partIDField;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField partInStockField;

    @FXML
    private TextField partPriceField;

    @FXML
    private TextField companyMachineField;

    @FXML
    private TextField partMaxField;

    @FXML
    private TextField PartMinField;

    @FXML
    private RadioButton outsourcedRadioButton;
    
    @FXML
    private ToggleGroup partToggleGroup;
        
    
    public void radioHandler()
     {
         if (this.partToggleGroup.getSelectedToggle().equals(this.inhouseRadioButton)){
            companyMachineLabel.setText("Machine ID");
            companyMachineField.setPromptText("Machine ID");
         }
         if (this.partToggleGroup.getSelectedToggle().equals(this.outsourcedRadioButton)){
            companyMachineLabel.setText("Company Name");
            companyMachineField.setPromptText("Company Name");
         }
     }

    /**
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
    partToggleGroup = new ToggleGroup();
    this.inhouseRadioButton.setToggleGroup(partToggleGroup);
    this.outsourcedRadioButton.setToggleGroup(partToggleGroup);
    }
    
    public Part buildPart() {
        if (inHouseRadio.isSelected()) {
            InhousePart part = new InhousePart();
            part.setName(this.partNameField.getText());

            if (!this.partIDField.getText().isEmpty()) {
                part.setPartId(parseInt(this.partIDField.getText()));
            }
            if (!this.partInStockField.getText().isEmpty()) {
                part.setInStock(parseInt(this.partInStockField.getText()));
            }
            if (!this.partPriceField.getText().isEmpty()) {
                part.setPrice(parseDouble(this.partPriceField.getText()));
            }
            if (!this.partMaxField.getText().isEmpty()) {
                part.setMax(parseInt(this.partMaxField.getText()));
            }
            if (!this.PartMinField.getText().isEmpty()) {
                part.setMin(parseInt(this.PartMinField.getText()));
            }
            if (!this.companyMachineField.getText().isEmpty()) {
                part.setMachineId(parseInt(this.companyMachineField.getText()));
            }
            return part;
     }
}
