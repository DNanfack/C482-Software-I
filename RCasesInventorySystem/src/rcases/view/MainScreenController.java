package rcases.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import rcases.model.Product;

public class MainScreenController implements Initializable {

    @FXML
    private TableView<Part> partTableView;
    
    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField searchPartsfield;

    @FXML
    private TextField searchProductsfield;

    @FXML
    private Button addPartButton;
    
    @FXML
    private Button button2;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("partId"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }
        
}