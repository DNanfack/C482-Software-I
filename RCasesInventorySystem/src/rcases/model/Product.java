/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcases.model;

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

    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;


    //// Constructor
    public Product() {
        productID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }


    //// Getters

    public IntegerProperty productIDProperty() {
        return productID;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public IntegerProperty inStockProperty() {
        return inStock;
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public int getProductID() {
        return this.productID.get();
    }

    public String getName() {
        return this.name.get();
    }

    public double getPrice() {
        return this.price.get();
    }

    public int getInStock() {
        return this.inStock.get();
    }

    public int getMin() {
        return this.min.get();
    }

    public int getMax() {
        return this.max.get();
    }

    public ObservableList getAssociatedParts() {
        return associatedParts;
    }


    //// Setters
    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
}
