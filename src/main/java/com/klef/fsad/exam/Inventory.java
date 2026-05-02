package com.klef.fsad.exam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private int id;

    @Column(name = "product_name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "supplier", length = 100)
    private String supplier;

    @Column(name = "category", length = 100)
    private String category;

    // Constructors
    public Inventory() {
    }

    public Inventory(String name, String description, int quantity, double price, Date date, String status) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.status = status;
    }

    public Inventory(String name, String description, int quantity, double price, Date date, 
                     String status, String supplier, String category) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.status = status;
        this.supplier = supplier;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", supplier='" + supplier + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
