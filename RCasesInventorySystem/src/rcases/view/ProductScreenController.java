package rcases.view;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rcases.model.Inventory;
import static rcases.model.Inventory.getAllParts;
import rcases.model.Part;
import rcases.model.Product;

public class ProductScreenController {

    @FXML
    private TextField productMaxField;

    @FXML
    private TextField productMinField;

    @FXML
    private TextField productIDField;

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
    private Product selectedProduct;
    private Stage dialogStage;
    private boolean okClicked = false;
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    public ObservableList<Part> tempPart=FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        partsIDColumn.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject());
        partsNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        partsInStockColumn.setCellValueFactory(
                cellData -> cellData.getValue().inStockProperty().asObject());
        partsPriceColumn.setCellValueFactory(
                cellData -> cellData.getValue().priceProperty().asObject());
        associatedPartsIDColumn.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject());
        associatedPartsNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        associatedPartsInStockColumn.setCellValueFactory(
                cellData -> cellData.getValue().inStockProperty().asObject());
        associatedPartsPriceColumn.setCellValueFactory(
                cellData -> cellData.getValue().priceProperty().asObject());
        productID = Inventory.getProductIDCount();
        productIDField.setText("Auto-Gen: " + productID);
        partsTableView.setItems(getAllParts());
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    void partsToProductHandler(ActionEvent event) {
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        associatedPartsTableView.setItems(currentParts);
    }

    @FXML
    void productCancelHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to Cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            productID = Inventory.cancelProductIDCount();
            dialogStage.close();
        } else {
            alert.close();
        }
        
    }

    @FXML
    void productDeleteHandler(ActionEvent event) {
        Part part = associatedPartsTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete " + part.getName() + " from Associated Parts?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            currentParts.remove(part);
            associatedPartsTableView.setItems(currentParts);
        } else {
            alert.close();
        }
        
    }

    @FXML
    void productSaveHandler(ActionEvent event) {
        if (isProductValid()) {
            String name = productNameField.getText();
            String inStock = productInStockField.getText();
            String price = productPriceField.getText();
            String min = productMinField.getText();
            String max = productMaxField.getText();
            
            Product newProduct = new Product();
            newProduct.setProductID(productID);
            newProduct.setName(name);
            newProduct.setInStock(Integer.parseInt(inStock));
            newProduct.setPrice(Double.parseDouble(price));
            newProduct.setMin(Integer.parseInt(min));
            newProduct.setMax(Integer.parseInt(max));
            newProduct.setAssociatedParts(currentParts);
            Inventory.addProduct(newProduct);
        } 
            okClicked = true;
            dialogStage.close();
        }

    private boolean isProductValid() {
        return true;
    }

    @FXML
    void productSearchHandler(ActionEvent event) {
        String searchItem=productSearchField.getText();
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
                    tempPart.clear();
                    tempPart.add(p);
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
        
        productIDField.setText(Integer.toString(product.getProductID()));
        productNameField.setText(product.getName());
        productInStockField.setText(Integer.toString(product.getInStock()));
        productPriceField.setText(Double.toString(product.getPrice()));
        productMinField.setText(Integer.toString(product.getMin()));
        productMaxField.setText(Integer.toString(product.getMax()));
        }

}
