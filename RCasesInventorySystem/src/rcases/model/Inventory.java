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

    private final ObservableList<Product> products = FXCollections.observableArrayList();
    private final ObservableList<Part> allParts = FXCollections.observableArrayList();

    //Getters
    public ObservableList<Product> getProducts() {
        return products;
    }

    public ObservableList<Part> getParts() {
        return allParts;
    }

    //Methods to manage the global lists
    public void addPart(Part part) {
        if (validatePart(part)) {
            allParts.add(part);
        }
    }

    public void addProduct(Product product) {
        
    }

    public boolean removeProduct(int productId) {
        Product product = lookupProduct(productId);
        if (product != null) {
            products.remove(product);
            return true;
        }
        return false;
    }

    public boolean removePart(int partId) {
        Part part = lookupPart(partId);
        if (part != null) {
            allParts.remove(part);
            return true;
        }
        return false;
    }

    public void updatePart(Part part) {
        if (validatePart(part)) {
            removePart(part.getPartId());
            addPart(part);
        }
    }

    public void updateProduct(Product product){
        
    }

    public Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getPartId() == partId) {
                return part;
            }
        }
        return null;
    }

    public Product lookupProduct(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public boolean validatePart(Part part) {
        String errorMessage = "";
        boolean valid = true;
        if (part.getMin() > part.getMax()) {
            //minmax error
            errorMessage = errorMessage + "\r\t -Minimum must be less than maximum.\r\n";
            valid = false;
        }
        if ((part.getInStock() < part.getMin()) || (part.getInStock() > part.getMax())) {
            errorMessage = errorMessage + "\r\t -Instock Quantity must be more than the minimum and less than the maximum.\r\n";
            valid = false;
        }
        if (part.getName().isEmpty()) {
            errorMessage = errorMessage + "\r\t -The part to add must have a valid name.\r\n";
            valid = false;
        }
        if (part.getPrice() < 0) {
            errorMessage = errorMessage + "\r\t -The price must not be negative.\r\n";
            valid = false;
        }

        return valid;
    }


}