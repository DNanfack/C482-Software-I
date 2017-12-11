/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcases.model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Roberto Cases
 */

public class Product {

    private ArrayList<Part> associatedParts;
    private final IntegerProperty productID;
    private final StringProperty productName;
    private final DoubleProperty productPrice;
    private final IntegerProperty productInStock;
    private final IntegerProperty productMin;
    private final IntegerProperty productMax;


    //// Constructor
    public Product(int productID, String name, double price, int inStock, int min, int max, ArrayList<Part> associatedParts) {
        this.productID = new SimpleIntegerProperty(productID);
        this.productName = new SimpleStringProperty(name);
        this.productPrice = new SimpleDoubleProperty(price);
        this.productInStock = new SimpleIntegerProperty(inStock);
        this.productMin = new SimpleIntegerProperty(min);
        this.productMax = new SimpleIntegerProperty(max);
        this.associatedParts = new ArrayList<>(associatedParts);
    }

    public Product() {
        this.productID = new SimpleIntegerProperty(0);
        this.productName = new SimpleStringProperty("");
        this.productPrice = new SimpleDoubleProperty(0);
        this.productInStock = new SimpleIntegerProperty(0);
        this.productMin = new SimpleIntegerProperty(0);
        this.productMax = new SimpleIntegerProperty(0);
        this.associatedParts = new ArrayList<>();

    }

    //// Getters
    public IntegerProperty productIDProperty() {
        return productID;
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public DoubleProperty productPriceProperty() {
        return productPrice;
    }

    public IntegerProperty productInStockProperty() {
        return productInStock;
    }

    public IntegerProperty productMinProperty() {
        return productMin;
    }

    public IntegerProperty productMaxProperty() {
        return productMax;
    }

    public int getProductID() {
        return this.productID.get();
    }

    public String getName() {
        return this.productName.get();
    }

    public double getPrice() {
        return this.productPrice.get();
    }

    public int getInStock() {
        return this.productInStock.get();
    }

    public int getMin() {
        return this.productMin.get();
    }

    public int getMax() {
        return this.productMax.get();
    }
    
    public ArrayList<Part> getAssociatedParts() {
        return this.associatedParts;
    }

    public ObservableList<Part> getAssociatedPartsObservable() {
        ObservableList<Part> parts = FXCollections.observableArrayList(this.associatedParts);
        return parts;
    }

    //// Setters
    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public void setName(String name) {
        this.productName.set(name);
    }

    public void setPrice(double price) {
        this.productPrice.set(price);
    }

    public void setInStock(int inStock) {
        this.productInStock.set(inStock);
    }

    public void setMin(int min) {
        this.productMin.set(min);
    }

    public void setMax(int max) {
        this.productMax.set(max);
    }

    public void setAssociatedParts(ArrayList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
}
