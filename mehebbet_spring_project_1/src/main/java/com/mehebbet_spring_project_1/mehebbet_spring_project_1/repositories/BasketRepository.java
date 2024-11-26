package com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Authority;

@Repository
public interface BasketRepository extends JpaRepository<Authority, Long> {}
