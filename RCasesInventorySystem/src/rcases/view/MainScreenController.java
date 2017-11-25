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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rcases.model.Part;
import static rcases.model.Inventory.getAllParts;

public class MainScreenController implements Initializable {

    @FXML // fx:id="searchPartsfield"
    private TextField searchPartsfield; // Value injected by FXMLLoader

    @FXML // fx:id="searchProductsfield"
    private TextField searchProductsfield; // Value injected by FXMLLoader
    
    @FXML
    private Button addPartButton;
    
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
    void exitHandler(ActionEvent event) {

    }

    @FXML
    void partsAddHandler(ActionEvent event) throws IOException{
     Stage stage = null; 
     Parent root = null;
     if(event.getSource()==addPartButton){
        //get reference to the button's stage         
        stage=(Stage) addPartButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/rcases/view/PartScreen.fxml"));
        }
     
     //create a new scene with root and set the stage
      Scene scene;
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

    

    @FXML
    void partsDeleteHandler(ActionEvent event) {

    }

    @FXML
    void partsModifyHandler(ActionEvent event) {

    }

    @FXML
    void partsSearchHandler(ActionEvent event) {

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
        partsTableView.setItems(getAllParts());
    }

}