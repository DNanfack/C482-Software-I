/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcases.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class OutsourcedPart extends Part {

    protected StringProperty companyName;

    //Constructors
    public OutsourcedPart(String name, double price, int min, int max, int inStock, String companyName) {
        this.partId = new SimpleIntegerProperty(newPartId());
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.companyName = new SimpleStringProperty(companyName);
    }

    public OutsourcedPart() {
        this.partId = new SimpleIntegerProperty(newPartId());
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.inStock = new SimpleIntegerProperty(0);
        this.companyName = new SimpleStringProperty("");
    }

    //Getters
    public String getCompanyName() {
        return this.companyName.get();
    }

    //Setters
    public void setCompanyName(String companyName) {
        this.companyName = new SimpleStringProperty(companyName);
    }
}