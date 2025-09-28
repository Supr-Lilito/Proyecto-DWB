package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.product.api.entity.Category;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {
	
	@Query(value ="SELECT * FROM category ORDER BY category", nativeQuery = true)
	List<Category> getCategories();

}
