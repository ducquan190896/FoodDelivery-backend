package com.fooddelivery.backend.Models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fooddelivery.backend.Models.Enums.OrderStatus;
import com.fooddelivery.backend.Models.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Orders")
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private  Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private Courier courier;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Min(value = 0, message = "total price must be higher than 0")
    @Column(name = "total")
    private double total;

    @Min(value = 0, message = "total delivery fee must be higher than 0")
    @Column(name = "delivery_fee")
    private double deliveryFee;

    @Min(value = 0, message = "finalPrice price must be higher than 0")
    @Column(name = "final_price")
    private double finalPrice;

    @Min(value = 0, message = "quantity must be higher than 0")
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "note")
    private String note;

    @Column(name = "to_longitude")
    private Double toLongitude;

    @Column(name = "to_latitude")
    private Double toLatitude;

    @Column(name = "from_longitude")
    private Double fromLongitude;

    @Column(name = "from_latitude")
    private Double fromLatitude;

    @JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDish> orderDishes = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public Order(Customer customer, Restaurant restaurant, OrderStatus status) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.status = status;
        this.total = 0;
    }

    public Order(Customer customer, Restaurant restaurant, OrderStatus status, double total, int quantity, Double toLongitude, Double toLatitude) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.status = status;
        this.total = total;
        this.quantity = quantity;
        this.toLongitude = toLongitude;
        this.toLatitude = toLatitude;
    }

    

    

}