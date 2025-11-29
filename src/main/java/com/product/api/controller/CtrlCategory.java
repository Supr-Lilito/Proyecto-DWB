package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.commons.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Catálogo de categories")
public class CtrlCategory {
	
	@Autowired
	SvcCategory svc;

	@Operation(summary = "Consultar todas las categorías", description = "Obtiene el listado completo de categorías registradas en el sistema")
    @GetMapping
	public ResponseEntity<List<Category>> findAll(){
		return ResponseEntity.ok(svc.findAll());
	}
	
	@Operation(summary = "Consultar categorías activas", description = "Obtiene el listado de categorías que se encuentran activas en el sistema")
	@GetMapping("/active")
	public ResponseEntity<List<Category>> findActive(){
		return ResponseEntity.ok(svc.findActive());
	}
	
	@Operation(summary = "Crear categoría", description = "Registra una nueva categoría en el sistema")
	@PostMapping
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody DtoCategoryIn in) {
		return ResponseEntity.ok(svc.create(in));
	}
	
	@Operation(summary = "Actualizar categoría", description = "Modifica la información de una categoría existente")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@Valid @RequestBody DtoCategoryIn in, @PathVariable("id") Integer id){
		return ResponseEntity.ok(svc.update(in, id));
	}

	@Operation(summary = "Habilitar categoría", description = "Activa una categoría previamente deshabilitada")
	@PatchMapping("/{id}/enable")
	public ResponseEntity<ApiResponse> enable(@PathVariable Integer id) {
		return ResponseEntity.ok(svc.enable(id));
	}

	@Operation(summary = "Deshabilitar categoría", description = "Desactiva una categoría del sistema sin eliminarla")
	@PatchMapping("/{id}/disable")
	public ResponseEntity<ApiResponse> disable(@PathVariable Integer id) {
		return ResponseEntity.ok(svc.disable(id));
	}

}