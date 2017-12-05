/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcases;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rcases.model.Part;
import rcases.view.MainScreenController;
import rcases.view.PartScreenController;

public class InvMgmt extends Application {

    private Stage primaryStage;
    private AnchorPane mainScreen;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        showMainScreen();
    }

    /*@Override
    public void start(Stage primaryStage) throws IOException {
    try {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(InvMgmt.class.getResource("/rcases/view/MainScreen.fxml"));
    mainScreen = loader.load();
    Scene scene = new Scene(mainScreen);
    MainScreenController controller = loader.getController();
    controller.
    primaryStage.setTitle("Inventory Management System");
    primaryStage.setScene(scene);
    primaryStage.show();
    } catch (IOException ex) {
    Logger.getLogger(InvMgmt.class.getName()).log(Level.SEVERE, null, ex);
    }
    }*/
    
    public void showMainScreen() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InvMgmt.class.getResource("/rcases/view/MainScreen.fxml"));
            mainScreen = (AnchorPane) loader.load();   

            // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(mainScreen);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public boolean showPartScreen(Part part) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InvMgmt.class.getResource("/rcases/view/PartScreen.fxml"));
        AnchorPane partScreen = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Part");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(partScreen);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        PartScreenController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPart(part);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
        } catch (IOException e) {
        e.printStackTrace();
        return false;
        }
    }

}

