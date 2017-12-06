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

    private IntegerProperty productID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;
    private ArrayList<Part> associatedParts;

    public Product(String name, double price, int min, int max, int inStock, ArrayList<Part> associatedParts) {
        this.productID = new SimpleIntegerProperty(getProductID());
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.associatedParts = new ArrayList<>(associatedParts);
    }

    public Product() {
        this.productId = new SimpleIntegerProperty(getProductID());
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.inStock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.associatedParts = new ArrayList<>();

    }

    //productID
    public int getProductID() {
        return this.productID.get();
    }
    
    public void setProductID(int productID) {
        this.productID.set(productID);
    }
    
    public IntegerProperty productIDProperty() {
        return productID;
    }
    
    //name
    public String getName() {
        return this.name.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    //price
    public double getPrice() {
        return this.price.get();
    }
    
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public DoubleProperty priceProperty() {
        return price;
    }
    
    //inStock
    public int getInStock() {
        return this.inStock.get();
    }
    
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }
    
    public IntegerProperty inStockProperty() {
        return inStock;
    }
    
    //min
    public int getMin() {
        return this.min.get();
    }
    
    public void setMin(int min) {
        this.min.set(min);
    }
    
    public IntegerProperty minProperty() {
        return min;
    }
    
    //max
    public int getMax() {
        return this.max.get();
    }
    
    public void setMax(int max) {
        this.max.set(max);
    }
    
    public IntegerProperty maxProperty() {
        return max;
    }
}

    public int getAssociatedPartsCount() {
        return this.associatedParts.size();
    }

    public ArrayList<Part> getAssociatedParts() {
        return this.associatedParts;
    }

    public ObservableList<Part> getAssociatedPartsObservable() {
        ObservableList<Part> parts = FXCollections.observableArrayList(this.associatedParts);
        return parts;
    }
   
    public void setAssociatedParts(ArrayList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    //These methods help manage the associatedParts list
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    public boolean removeAssociatedPart(Part part) {
        boolean success = false;
        if ((this.associatedParts.contains(part)) && (this.associatedParts.size() == 1)) {
            success = false;
        } else {
            this.associatedParts.remove(part);
            success = true;
        }
        return success;
    }
}

