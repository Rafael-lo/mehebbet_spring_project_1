package com.mehebbet_spring_project_1.mehebbet_spring_project_1.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private Long id;

    @Column(nullable = false)
    private String name; // Название продукта

    @Column(nullable = false)
    private String category; // Категория продукта

    @Column(nullable = false)
    private int stock; // Количество на складе

    @Column(nullable = false)
    private String imagePath;

    @Column(name = "sales_per_day", nullable = false)
    private int salesPerDay; // Продажи в день

    @Column(name = "sales_per_month", nullable = false)
    private int salesPerMonth; // Продажи в месяц

    @Column(nullable = false)
    private double rating; // Рейтинг продукта

    @Column(name = "rated_count", nullable = false)
    private int ratedCount; // Рейтинг количество

    @Column(nullable = false)
    private int sales; // Общие продажи

    @Column(nullable = false)
    private double revenue; // Доход

    @Column(nullable = false)
    private double price; // Доход

    @Column(nullable = false)
    private double sale; // Скидка

    @Column(name = "fast_delivery" ,nullable = false)
    private boolean fastDelivery;

    @Column(name = "last_update")
    private LocalDate lastUpdate; // Дата последнего обновления

    @Column(nullable = false)
    private String description; // Доход

    @ManyToOne // Связь многие-к-одному с пользователем
    @JoinColumn(name = "user_id", nullable = false) // Имя столбца, ссылающегося на пользователя
    private User user;
    
    public Product(String name, String category, int stock, double price, String description, User user) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.description = description;
        this.user = user; // Assign the user
    }
}
