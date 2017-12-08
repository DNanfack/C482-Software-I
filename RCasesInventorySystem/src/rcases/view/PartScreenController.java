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
import rcases.model.Inventory;
import rcases.model.OutsourcedPart;
import rcases.model.Part;
import static rcases.view.MainScreenController.modifyIndex;

public class PartScreenController {

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
    private Part part;
    private Part selectedPart;
    private OutsourcedPart selectedOutPart;
    private InhousePart selectedInPart;
    private Stage dialogStage;
    private boolean okClicked = false;
    int partIndex = modifyIndex();
                    
    @FXML
    private void initialize() {
        partToggleGroup = new ToggleGroup();
        this.inhouseRadioButton.setToggleGroup(partToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(partToggleGroup);
        partID = Inventory.getPartIDCount();
        partIDField.setText("Auto-Generated: " + partID);
        
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
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
     * @param part
     */
    public void setPart(Part part) {
        selectedPart = part;
        
        partIDField.setText(Integer.toString(part.getPartID()));
        partNameField.setText(part.getName());
        partInStockField.setText(Integer.toString(part.getInStock()));
        partPriceField.setText(Double.toString(part.getPrice()));
        PartMinField.setText(Integer.toString(part.getMin()));
        partMaxField.setText(Integer.toString(part.getMax()));

        if (part instanceof InhousePart) {
            selectedInPart = (InhousePart) part;
            companyMachineLabel.setText("Machine ID");
            inhouseRadioButton.selectedProperty().set(true);
            companyMachineField.setText(Integer.toString(selectedInPart.getMachineID()));
        } else {
            selectedOutPart = (OutsourcedPart) part;
            companyMachineLabel.setText("Company Name");
            outsourcedRadioButton.selectedProperty().set(true);
            companyMachineField.setText(selectedOutPart.getCompanyName());
        }
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks save.
     */
    @FXML
    private void handleNewSave() {
            String name = partNameField.getText();
            String inStock = partInStockField.getText();
            String price = partPriceField.getText();
            String min = PartMinField.getText();
            String max = partMaxField.getText();
            String machineID = companyMachineField.getText();
            String companyName = companyMachineField.getText();
            if (isPartValid(String name, String inStock, String price, String min, String max, String machineID, String companyName)) {
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
        } 
            okClicked = true;
            dialogStage.close();
        }
    
    @FXML
    private void handleModifySave() {
       
            String partID = partIDField.getText();
            String name = partNameField.getText();
            String inStock = partInStockField.getText();
            String price = partPriceField.getText();
            String min = PartMinField.getText();
            String max = partMaxField.getText();
            String companyMachine = companyMachineField.getText();
            if (isPartValid(String name, String inStock, String price, String min, String max, String machineID, String companyName)) {
                if ((this.partToggleGroup.getSelectedToggle().equals(this.inhouseRadioButton))) {
                InhousePart inPart = new InhousePart();
                inPart.setPartID(Integer.parseInt(partID));
                inPart.setName(name);
                inPart.setPrice(Double.parseDouble(price));
                inPart.setInStock(Integer.parseInt(inStock));
                inPart.setMin(Integer.parseInt(min));
                inPart.setMax(Integer.parseInt(max));
                inPart.setMachineID(Integer.parseInt(companyMachine));
                Inventory.updatePart(partIndex, inPart);
            
            } else {
                OutsourcedPart outPart = new OutsourcedPart();
                outPart.setPartID(Integer.parseInt(partID));
                outPart.setName(name);
                outPart.setPrice(Double.parseDouble(price));
                outPart.setInStock(Integer.parseInt(inStock));
                outPart.setMin(Integer.parseInt(min));
                outPart.setMax(Integer.parseInt(max));
                outPart.setCompanyName(companyMachine);
                Inventory.updatePart(partIndex, outPart);
            }
        } 
            okClicked = true;
            dialogStage.close();
        }
    
    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        partID = Inventory.cancelPartIDCount();
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isPartValid(String name, String inStock, String price, String min, String max, String companyMachine) {
        String errorMessage = "";
        //first checks to see if inputs are null
        if (name == null || name.length() == 0) {
            errorMessage += "No valid part name!\n"; 
        }
       if (inStock == null || inStock.length() == 0) {
            errorMessage += "No valid Iventory value!\n";  
        } else {
            try {
                Integer.parseInt(inStock);
            } catch (NumberFormatException e) {
                errorMessage += "No valid Inventory value (must be an integer)!\n"; 
            }
        }
        if (price == null || price.length() == 0) {
            errorMessage += "No valid price!\n"; 
        } else {
            try {
                Integer.parseDouble(price);
            } catch (NumberFormatException e) {
                errorMessage += "No valid Price value (must be a double)!\n"; 
            }
        }
        if (min == null || min.length() == 0) {
            errorMessage += "No valid Min value!\n"; 
        } else {
            try {
                Integer.parseInt(min);
            } catch (NumberFormatException e) {
                errorMessage += "No valid Min value (must be an integer)!\n"; 
            }
        }        
        if (max == null || max == 0) {
            errorMessage += "No valid Max value!\n"; 
        } else {
            try {
                Integer.parseInt(max);
            } catch (NumberFormatException e) {
                errorMessage += "No valid Max value (must be an integer)!\n"; 
            }
        }
        if (companyMachine == null || companyMachine == 0) {
            errorMessage += "No valid Machine ID or Company Name!\n"; 
        }
        //then checks to see if values fit within specified criteria
        If (inStock < min || inStock > max) {
            errorMessage += "Inventory must be between the minimum or maximum value!\n";
        }
        if (max < min || min >= max) {
            errorMessage += "Inventory must be between the minimum or maximum value!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
        
    }
    
         
}
