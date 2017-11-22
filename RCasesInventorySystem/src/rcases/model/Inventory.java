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
    
//Getters
    public static ObservableList<Product> getProducts() {
        return products;
    }

    public static ObservableList<Part> getParts() {
        return allParts;
    }
    
    public static void addPart(Part part) {
        allParts.add(part);
    }
}

