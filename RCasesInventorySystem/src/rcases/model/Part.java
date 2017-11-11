/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcases.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author rober
 */
public abstract class Part {
    protected IntegerProperty partId;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty inStock;
    protected IntegerProperty min;
    protected IntegerProperty max;
}
