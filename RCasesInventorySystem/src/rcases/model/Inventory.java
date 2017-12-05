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
    public static boolean alreadyExecuted = false;
    
    //Parts
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

    //modify to use lookupPart Method?
    public static Part lookupPart(int itemNumber) {
        for(Part p: getAllParts()){
            if(p.getPartID()==itemNumber){
                System.out.println("This is part "+ itemNumber);
                return p;                
            }
       }
       return null;
    }
    
    
    //Products
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
    
   
    public static int cancelProductIDCount() {
        productIDCount--;
        return productIDCount;
    }

    public static product lookupProduct(int itemNumber) {
        for(Part p: getProducts()){
            if(p.getProductID()==itemNumber){
                System.out.println("This is part "+ itemNumber);
                return p;                
            }
       }
       return null;
    }
    
    //modify to use lookupProduct Method?
    public static void updateProduct(int index, Product product) {
        products.set(index, product);
    }
    
    public boolean alreadyExecuted() {
        return false;
    }

}
