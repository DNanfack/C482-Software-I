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

    //idCounter belongs to the class. Will be auto-incremeneted in sequential order for new parts.
    private static int idCounter = 0;

    protected IntegerProperty partId;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty min;
    protected IntegerProperty max;
    protected IntegerProperty inStock;

    //increments newIds
    public int newPartId() {
        return idCounter++;
    }

    //getters
    public int getPartId() {
        return this.partId.get();
    }

    public String getName() {
        return this.name.get();
    }

    public double getPrice() {
        return this.price.get();
    }

    public int getMin() {
        return this.min.get();
    }

    public int getMax() {
        return this.max.get();
    }

    public int getInStock() {
        return this.inStock.get();
    }

    //setters
    public void setPartId() {
        int newPartId = newPartId();
        partId.set(newPartId);
    }

    public void setPartId(int p) {
        partId.set(p);
    }

    public void setName(String n) {
        name.set(n);
    }

    public void setPrice(double p) {
        price.set(p);
    }

    public void setMin(int m) {
        min.set(m);
    }

    public void setMax(int m) {
        max.set(m);
    }

    public void setInStock(int i) {
        inStock.set(i);
    }
}