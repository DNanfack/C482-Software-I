package rcases.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rcases.model.InhousePart;
import rcases.model.Inventory;
import static rcases.model.Inventory.deletePart;
import rcases.model.Part;
import static rcases.model.Inventory.getAllParts;
import static rcases.model.Inventory.getPartIDCount;
import rcases.model.OutsourcedPart;

public class MainScreenController implements Initializable {

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
    
    //need to add tableview and column for product tableview
    
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
    void partsModifyHandler(ActionEvent event) {
        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        int selectedPartIndex = getAllParts().indexOf(selectedPart);
        if (selectedPart != null) {
            try {
                //get reference to the button's stage         
                stage=(Stage) modifyPartButton.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("/rcases/view/PartScreen.fxml"));
                //PartScreenController.populateSelectedPart(selectedPart);
                Scene scene;
                scene = new Scene((Parent) root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No part selected");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part to Modify");
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
        InhousePart camPart1 = new InhousePart();
            camPart1.setPartID(partID);
            camPart1.setName("OC835");
            camPart1.setPrice(199.99);
            camPart1.setInStock(100);
            camPart1.setMin(0);
            camPart1.setMax(5);
            camPart1.setMachineID(1835);
            Inventory.addPart(camPart1);
        OutsourcedPart camPart2 = new OutsourcedPart();
            camPart2.setPartID(getPartIDCount());
            camPart2.setName("RC8025");
            camPart2.setPrice(99.99);
            camPart2.setInStock(100);
            camPart2.setMin(0);
            camPart2.setMax(5);
            camPart2.setCompanyName("ADT");
            Inventory.addPart(camPart2);
    }
    
        
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partsIDColumn.setCellValueFactory(
                new PropertyValueFactory<>("partID"));
        partsNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        partsInStockColumn.setCellValueFactory(
                new PropertyValueFactory<>("inStock"));
        partsPriceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        if(!(Inventory.alreadyExecuted)) {
            existingParts();
            Inventory.alreadyExecuted = true;
        }
        partsTableView.setItems(getAllParts());
        
        // need to add section for Product table
        
    }

}
