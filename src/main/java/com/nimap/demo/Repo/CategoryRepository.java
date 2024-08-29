package com.nimap.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimap.demo.EntityClasses.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}