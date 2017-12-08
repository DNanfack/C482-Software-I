package rcases.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProductScreenController {

    @FXML
    private TextField productMaxField;

    @FXML
    private TextField productMinField;

    @FXML
    private TextField productIDfield;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productInStockField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TextField productSearchField;
    
    @FXML
    private TableView<Part> partsTableView;
    
    @FXML
    private TableColumn<Part, Integer> partsIDColumn;
    
    @FXML
    private TableColumn<Part, String> partsNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> partsInStockColumn;
    
    @FXML
    private TableColumn<Part, Double> partsPriceColumn;
    
    @FXML
    private TableView<Part> associatedPartsTableView;
    
    @FXML
    private TableColumn<Part, Integer> associatedPartsIDColumn;
    
    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> associatedPartsInStockColumn;
    
    @FXML
    private TableColumn<Part, Double> associatedPartsPriceColumn;
    
    private int productID;
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    public ObservableList<Part> tempPart=FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        productID = Inventory.getProductIDCount();
        productIDField.setText("Auto-Generated: " + productID);
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void partsToProductHandler(ActionEvent event) {
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        associatedPartsTableView.setItems(currentParts);
    }

    @FXML
    void productCancelHandler(ActionEvent event) {
        productID = Inventory.cancelProductIDCount();
        dialogStage.close();
    }

    @FXML
    void productDeleteHandler(ActionEvent event) {
        Part part = AssociatedPartsTableView.getSelectionModel().getSelectedItem();
        currentParts.remove(part);
        associatedPartsTableView.setItems(currentParts);
    }

    @FXML
    void productSaveHandler(ActionEvent event) {

    }

    @FXML
    void productSearchHandler(ActionEvent event) {
        String searchItem=searchPartsField.getText();
        if (searchItem.equals("")){
                partsTableView.setItems(getAllParts());
        } else{
            boolean found=false;
            try{
                int itemNumber=Integer.parseInt(searchItem);
                Part p = Inventory.lookupPart(itemNumber);
                if(p != null){
                    found=true;
                    tempPart.clear();
                    tempPart.add(p);
                    partsTableView.setItems(tempPart);
                } 
                if (found==false){
                partsTableView.setItems(getAllParts());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Part Not Found");
                alert.setContentText("Please search by exact name or ID");

                alert.showAndWait();
            }
        }
        catch(NumberFormatException e){
            for(Part p: Inventory.getAllParts()){
                if(p.getName().equals(searchItem)){
                    System.out.println("This is part "+p.getPartID());
                    found=true;
                    tempParts.clear();
                    tempParts.add(p);
                    partsTableView.setItems(tempPart);
                }
            
            }
            if (found==false){
            partsTableView.setItems(getAllParts());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Part Not Found");
            alert.setContentText("Please search by exact name or ID");
            
            alert.showAndWait();
            }
        }
        }
    }
    
    public void setProduct(Product product) {
        selectedProduct = product;
        
        productIDfield.setText(Integer.toString(product.getProductID()));
        productNameField.setText(product.getName());
        productInStockField.setText(Integer.toString(product.getInStock()));
        productPriceField.setText(Double.toString(product.getPrice()));
        productMinField.setText(Integer.toString(product.getMin()));
        productMaxField.setText(Integer.toString(product.getMax()));
        }

}
