package com.turkcell.spring_library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.spring_library.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
