package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;

@Service
public class SvcCategoryImp implements SvcCategory{
	
	@Autowired
	RepoCategory repo;

	@Override
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(repo.getCategories(), HttpStatus.OK);
    }
	
	

}
