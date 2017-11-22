package rcases.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainScreenController {

    @FXML // fx:id="searchPartsfield"
    private TextField searchPartsfield; // Value injected by FXMLLoader

    @FXML // fx:id="searchProductsfield"
    private TextField searchProductsfield; // Value injected by FXMLLoader
    
    @FXML
    private Button addPartButton;
    
    @FXML
    private Button button2;
    
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInStockColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    void exitHandler(ActionEvent event) {

    }

    @FXML
    void partsAddHandler(ActionEvent event) throws IOException{
         Stage stage; 
     Parent root;
     if(event.getSource()==addPartButton){
        //get reference to the button's stage         
        stage=(Stage) addPartButton.getScene().getWindow();
        //load up OTHER FXML document
  root = FXMLLoader.load(getClass().getResource("/rcases/view/PartScreen.fxml"));
      }
     else{
       stage=(Stage) button2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/rcases/view/MainScreen.fxml"));
      }
     //create a new scene with root and set the stage
      Scene scene = new Scene(root);
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
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    partIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
    partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    partInstockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
    partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    
    }

}
