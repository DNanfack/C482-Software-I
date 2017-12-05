/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcases.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public abstract class Part {

    protected IntegerProperty partID;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty inStock;
    protected IntegerProperty min;
    protected IntegerProperty max;
            
    //partID
    public int getPartID() {
        return this.partID.get();
    }
    
    public void setPartID(int partID) {
        this.partID.set(partID);
    }
    
    public IntegerProperty partIDProperty() {
        return partID;
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
