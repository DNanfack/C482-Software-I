package rcases.view;

import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import rcases.model.InhousePart;
import static rcases.model.Inventory.addPart;
import rcases.model.Part;

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
    
    @FXML 
    private Button partSaveButton;
    
    @FXML
    private Button partCancelButton;
                    
    
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

    public void partButtonHandler(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     if(event.getSource()==partSaveButton){
        addPart(buildPart());
        stage=(Stage) partSaveButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/rcases/view/MainScreen.fxml"));
      }
     else{
        stage=(Stage) partCancelButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/rcases/view/MainScreen.fxml"));
      }
     //create a new scene with root and set the stage
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    partToggleGroup = new ToggleGroup();
    this.inhouseRadioButton.setToggleGroup(partToggleGroup);
    this.outsourcedRadioButton.setToggleGroup(partToggleGroup);
    }
    
    public Part buildPart() {
        InhousePart part = new InhousePart();
    
        if (inhouseRadioButton.isSelected()) {
            
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
        }
        return part;
     }
}
    

