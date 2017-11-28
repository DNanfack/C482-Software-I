/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcases.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Roberto Cases
 */
public class Inventory {

    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int partIDCount = 0;
    private static int productIDCount = 0;

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void deletePart(Part part) {
        allParts.remove(part);
    }

    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }

    public static int getPartIDCount() {
        partIDCount++;
        return partIDCount;
    }
    
    public static int cancelPartIDCount() {
        partIDCount--;
        return partIDCount;
    }

    public static int lookupPart(String searchItem) { //Will variable searchItem be in conflict when I implement product search?
       //move search code here and call it from MainScreenController or make this empty method
    }

    public static ObservableList<Product> getProducts() {
        return products;
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void removeProduct(Product product) {
        products.remove(product);
    }

    public static int getProductIDCount() {
        productIDCount++;
        return productIDCount;
    }

    public static int lookupProduct(String searchTerm) {
        
    }

    public static void updateProduct(int index, Product product) {
        products.set(index, product);
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
