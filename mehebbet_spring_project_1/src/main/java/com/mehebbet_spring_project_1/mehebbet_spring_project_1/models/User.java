package com.mehebbet_spring_project_1.mehebbet_spring_project_1.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Уникальное имя пользователя
    private String username;

    @Column(nullable = false) // Пароль не может быть пустым
    private String password;

    @Column(nullable = false) // Поле для включения/выключения пользователя
    private boolean enabled = true; // По умолчанию пользователь активен

    @OneToMany(mappedBy = "user")
    private List<Product> products; // Связь с продуктами

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Basket basket;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
