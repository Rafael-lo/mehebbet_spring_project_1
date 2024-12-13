package com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	public Boolean existsByName(String name);

	public List<Category> findByIsActiveTrue();
	
}
