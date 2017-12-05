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
    public ObservableList<Part> tempPart=FXCollections.observableArrayList();
    private Stage stage;
    private Object root;
    private InvMgmt invMgmt;
    
    public MainScreenController() {
        
    }
       
    @FXML
    void exitHandler(ActionEvent event) {

    }

    @FXML
    public void partsButtonHandler(ActionEvent event) throws IOException{
        //get reference to the button's stage         
        stage=(Stage) addPartButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/rcases/view/PartScreen.fxml"));
        //create a new scene with root and set the stage
        Scene scene;
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
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
    //Needs to be lookupPart Method called from Inventory or lookupPart needs to be empty method
    String searchItem=searchPartsField.getText();
        if (searchItem.equals("")){
                partsTableView.setItems(getAllParts());
        }
          else{
        boolean found=false;
        try{
        int itemNumber=Integer.parseInt(searchItem);
            Part p = Inventory.lookupPart(itemNumber);
//        for(Part p: Inventory.getAllParts()){
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

    }

    @FXML
    void productsDeleteHandler(ActionEvent event) {

    }

    @FXML
    void productsModifyHandler(ActionEvent event) {

    }

    @FXML
    void productsSearchHandler(ActionEvent event) {

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
        
        
        
        // need to add section for Product table
        
    }
    
    public void setMainApp(InvMgmt invMgmt) {
        this.invMgmt = invMgmt;
        
        if(!(Inventory.alreadyExecuted)) {
        existingParts();
        Inventory.alreadyExecuted = true;
        }
        partsTableView.setItems(getAllParts());
        
    }

}
