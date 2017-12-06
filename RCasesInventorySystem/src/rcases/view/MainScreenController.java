package rcases.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rcases.InvMgmt;
import rcases.model.InhousePart;
import rcases.model.Inventory;
import static rcases.model.Inventory.deletePart;
import rcases.model.Part;
import static rcases.model.Inventory.getAllParts;
import static rcases.model.Inventory.getPartIDCount;
import rcases.model.OutsourcedPart;

public class MainScreenController {

    @FXML 
    private TextField searchPartsField;
    @FXML 
    private TextField searchProductsField;    
    @FXML
    private Button addPartButton;    
    @FXML
    private Button modifyPartButton;
    @FXML
    private Button deletePartButton;    
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
    private TableView<Part> productsTableView;  
    @FXML
    private TableColumn<Part, Integer> productsIDColumn;  
    @FXML
    private TableColumn<Part, String> productsNameColumn;  
    @FXML
    private TableColumn<Part, Integer> productsInStockColumn;  
    @FXML
    private TableColumn<Part, Double> productsPriceColumn;
    @FXML
    public ObservableList<Part> tempPart=FXCollections.observableArrayList();
    private Stage stage;
    private Object root;
    private Part newPart;
    private Product newProduct;
    private InvMgmt invMgmt;
    
    public MainScreenController() {
        
    }
       
    @FXML
    void exitHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
        else {
            System.out.println("You clicked cancel.");
        }
    }

    @FXML
    public void partsButtonHandler(ActionEvent event) throws IOException{
        /*//get reference to the button's stage         
        stage=(Stage) addPartButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/rcases/view/PartScreen.fxml"));
        //create a new scene with root and set the stage
        Scene scene;
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();*/
        boolean okClicked = invMgmt.showPartScreen(newPart); //will null work here?
     
    }

    

    @FXML
    void partsDeleteHandler(ActionEvent event) {
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        deletePart(part);
    }


    @FXML
    public void partsModifyHandler(ActionEvent event) {
        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            boolean okClicked = invMgmt.showPartScreen(selectedPart);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part in the Table");
            alert.showAndWait();
        }
    }
    
    @FXML
    void partsSearchHandler(ActionEvent event) {
    String searchItem=searchPartsField.getText();
        if (searchItem.equals("")){
                partsTableView.setItems(getAllParts());
        }
          else{
        boolean found=false;
        try{
        int itemNumber=Integer.parseInt(searchItem);
            Part p = Inventory.lookupPart(itemNumber);
           if(p.getPartID()==itemNumber){
                found=true;
                tempPart.clear();
                tempPart.add(p);
                partsTableView.setItems(tempPart);
            
            }
            
  //      }
            if (found==false){
            partsTableView.setItems(getAllParts());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found");

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
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("Part not found");
            
            alert.showAndWait();
            }
    }
    }
    
    }

    @FXML
    void productsAddHandler(ActionEvent event) {
        /*//get reference to the button's stage         
        stage=(Stage) addPartButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/rcases/view/PartScreen.fxml"));
        //create a new scene with root and set the stage
        Scene scene;
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();*/
        boolean okClicked = invMgmt.showProductScreen(newProduct); //will null work here?
    }

    @FXML
    void productsDeleteHandler(ActionEvent event) {
        Product product = partsTableView.getSelectionModel().getSelectedItem();
        deleteProduct(product);
    }

    @FXML
    void productsModifyHandler(ActionEvent event) {
        Part selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean okClicked = invMgmt.showProductScreen(selectedProduct);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product in the Table");
            alert.showAndWait();
        }
    }

    @FXML
    void productsSearchHandler(ActionEvent event) {
        String searchItem=searchProductsField.getText();
        if (searchItem.equals("")){
                productsTableView.setItems(getProducts());
        }
          else{
        boolean found=false;
        try{
        int itemNumber=Integer.parseInt(searchItem);
            Product p = Inventory.lookupProduct(itemNumber);
           if(p.getProductID()==itemNumber){
                found=true;
                tempProduct.clear();
                tempProduct.add(p);
                productsTableView.setItems(tempProduct);
            
            }
            
  //      }
            if (found==false){
            productsTableView.setItems(getProducts());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Product not found");
            alert.setContentText("Please search by exact Product Name or ID #");

            alert.showAndWait();
        }
    }
    catch(NumberFormatException e){
        for(Part p: Inventory.getProducts()){
            if(p.getName().equals(searchItem)){
                System.out.println("This is part "+p.getProductID());
                found=true;
                tempProduct.clear();
                tempProduct.add(p);
                productsTableView.setItems(tempProduct);
            }
            
        }
            if (found==false){
            productsTableView.setItems(getProducts());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Product not found");
            alert.setContentText("Please search by ProductID or exact Product Name");
            
            alert.showAndWait();
            }
    }
    }

    }
    
    void existingParts() {
        int partID = Inventory.getPartIDCount();
        InhousePart camPart1 = new InhousePart(partID, "RC8021", 79.99, 42, 0, 999, 8025);
            Inventory.addPart(camPart1);
        OutsourcedPart camPart2 = new OutsourcedPart(getPartIDCount(), "RC8025", 149.99, 35, 0, 999, "ADT");
            Inventory.addPart(camPart2);
        OutsourcedPart camPart3 = new OutsourcedPart(getPartIDCount(), "OC835", 199.99, 26, 0, 999, "ADT");
            Inventory.addPart(camPart3);
    }
    
    void existingProducts() {
        int productID = Inventory.getProductIDCount();
        //add products here                                            
    }
        
    
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
        productsIDColumn.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject());
        productsNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        productsInStockColumn.setCellValueFactory(
                cellData -> cellData.getValue().inStockProperty().asObject());
        productsPriceColumn.setCellValueFactory(
                cellData -> cellData.getValue().priceProperty().asObject());
    }
    
    public void setMainApp(InvMgmt invMgmt) {
        this.invMgmt = invMgmt;
        
        //does existingParts method now work without alreadyExecuted method?
        if(!(Inventory.alreadyExecuted)) {
        existingParts();
        //existingProducts();
        Inventory.alreadyExecuted = true;
        }
        partsTableView.setItems(getAllParts());
        productsTableView.setItems(getProducts());
        
    }

}
