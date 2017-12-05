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
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } else{
        dialogStage.close();
    }
     //create a new scene with root and set the stage
      
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if (part instanceof InhousePart) {
            //inhouseRadioButton.selectedProperty().set(true);
            part.setPartID(partID);
            part.setName(partNameField.getText());
            part.setInStock(parseInt(partInStockField.getText()));
            part.setPrice(parseDouble(partPriceField.getText()));
            part.setMin(parseInt(PartMinField.getText()));
            part.setMax(parseInt(partMaxField.getText()));
            //part.setMachineID(parseInt(companyMachineField.getText()));
            //Inventory.addPart(part);
            } else {
                        
            //outsourcedRadioButton.selectedProperty().set(true);
            part.setPartID(partID);
            part.setName(partNameField.getText());
            part.setInStock(parseInt(partInStockField.getText()));
            part.setPrice(parseDouble(partPriceField.getText()));
            part.setMin(parseInt(PartMinField.getText()));
            part.setMax(parseInt(partMaxField.getText()));
            //part.setCompanyName(companyMachineField.getText());
            //Inventory.addPart(selectedOutPart);
        }

            okClicked = true;
            dialogStage.close();
        }
    }

    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        return true;
        /*String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
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
        }*/
    }
    
    /**
     *
     * @param url
     * @param rb
     */
    /*public void initialize(URL url, ResourceBundle rb) {
    partToggleGroup = new ToggleGroup();
    this.inhouseRadioButton.setToggleGroup(partToggleGroup);
    this.outsourcedRadioButton.setToggleGroup(partToggleGroup);
    partID = Inventory.getPartIDCount();
    partIDField.setText("Auto-Generated: " + partID);
    }*/
     
}
