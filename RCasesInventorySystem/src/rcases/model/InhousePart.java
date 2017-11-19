/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcases.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InhousePart extends Part {

    protected IntegerProperty machineId;

    //Constructors
    public InhousePart(String name, double price, int min, int max, int inStock, int machineId) {
        this.partId = new SimpleIntegerProperty(newPartId());
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.machineId = new SimpleIntegerProperty(machineId);
    }

    public InhousePart() {
        this.partId = new SimpleIntegerProperty(newPartId());
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.inStock = new SimpleIntegerProperty(0);
        this.machineId = new SimpleIntegerProperty(0);
    }

    //Getters
    public int getMachineId() {
        return this.machineId.get();
    }

    //Setters
    public void setMachineId(int machineId) {
        this.machineId = new SimpleIntegerProperty(machineId);
    }
    
}
