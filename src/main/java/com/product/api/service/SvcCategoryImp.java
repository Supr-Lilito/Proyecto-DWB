package com.product.api.service;

import java.util.List;

import javax.swing.plaf.synth.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;
import com.product.api.dto.DtocategoryIn;
import com.product.commons.dto.ApiResponse;
import com.product.exception.DBAccessException;

@Service
public class SvcCategoryImp implements SvcCategory{
	
	@Autowired
	RepoCategory repo;

	@Override
	public List<Category> findAll() {
		try {
			return repo.findAll();
		} catch (DataAccessException e) {
			throw new DBAccessException();
		}
	}

	@Override
	public List<Category> findActive() {
		try {
			return repo.findByStatusOrderByCategoryAsc(1);
		} catch (DataAccessException e) {
			throw new DBAccessException();
		}
	}

	@Override
	public ApiResponse create(DtoCategoryIn in) {
		try {
			repo.create(in.getCategory(), in.getTag());
			return new ApiResponse("La categoría ha sido registrada");
		} catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_category"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya está registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya está registrado");
			throw new DBAccessException();
		}
	}

	@Override
	public ApiResponse update(DtoCategoryIn in, Integer id) {
		try {
			validateId(id);
			repo.update(id, in.getCategory(), in.getTag());
			return new ApiResponse("La categoría ha sido actualizada");
		} catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_category"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya está registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya está registrado");
			throw new DBAccessException();
		}
	}

	@Override
	public ApiResponse enable(Integer id) {
		try {
			validateId(id);
			repo.setStatus(id, 1);
			return new ApiResponse("La categoría ha sido activada");
		} catch (DataAccessException e) {
			throw new DBAccessException();
		}
	}

	@Override
	public ApiResponse disable(Integer id) {
		try {
			validateId(id);
			repo.setStatus(id, 0);
			return new ApiResponse("La categoría ha sido desactivada");
		} catch (DataAccessException e) {
			throw new DBAccessException();
		}
	}

	private void validateId(Integer id) {
		if(repo.findById(id).isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
	}
}
