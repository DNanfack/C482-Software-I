package rcases.view;

import java.io.IOException;
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
import rcases.model.Inventory;
import rcases.model.OutsourcedPart;

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
    
    private int partID;
                    
    @FXML
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
    
    
    @FXML
    void partButtonHandler(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     if(event.getSource()==partSaveButton){
        String name = partNameField.getText();
        String inStock = partInStockField.getText();
        String price = partPriceField.getText();
        String min = PartMinField.getText();
        String max = partMaxField.getText();
        String machineID = companyMachineField.getText();
        String companyName = companyMachineField.getText();
        //need to change this so it recognizes that it is modifying a part and save as modified part as opposed to new part
        if ((this.partToggleGroup.getSelectedToggle().equals(this.inhouseRadioButton))) {
            InhousePart inPart = new InhousePart();
            inPart.setPartID(partID);
            inPart.setName(name);
            inPart.setPrice(Double.parseDouble(price));
            inPart.setInStock(Integer.parseInt(inStock));
            inPart.setMin(Integer.parseInt(min));
            inPart.setMax(Integer.parseInt(max));
            inPart.setMachineID(Integer.parseInt(machineID));
            Inventory.addPart(inPart);
        } else {
            OutsourcedPart outPart = new OutsourcedPart();
            outPart.setPartID(partID);
            outPart.setName(name);
            outPart.setPrice(Double.parseDouble(price));
            outPart.setInStock(Integer.parseInt(inStock));
            outPart.setMin(Integer.parseInt(min));
            outPart.setMax(Integer.parseInt(max));
            outPart.setCompanyName(companyName);
            Inventory.addPart(outPart);
}
        stage=(Stage) partSaveButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/rcases/view/MainScreen.fxml"));
    } else{
        stage=(Stage) partCancelButton.getScene().getWindow();
        partID = Inventory.cancelPartIDCount();
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
    partID = Inventory.getPartIDCount();
    partIDField.setText("Auto-Generated: " + partID);
    }
     
}
