package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;
import com.product.commons.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Tag(name = "Product Image", description = "Catálogo de images de products")
public class CtrlProductImage {
	
    @Autowired
    SvcProductImage svc;

    @Operation(summary = "Agregar imagen al producto", description = "Sube y asocia una nueva imagen a un producto específico")
    @PostMapping("/{id}/image")
    public ResponseEntity<ApiResponse> createProductImage(
    		@PathVariable("id") Integer productId, 
    		@Valid @RequestBody DtoProductImageIn in) {
        return ResponseEntity.ok(svc.upload(productId, in));
    }
    
    @Operation(summary = "Consultar imágenes del producto", description = "Obtiene el listado de todas las imágenes asociadas a un producto")
    @GetMapping("/{id}/image")
    public ResponseEntity<List<ProductImage>> getProductImages(
    		@PathVariable("id") Integer productId) {
    	return ResponseEntity.ok(svc.getImages(productId));
    }
    
    @Operation(summary = "Eliminar imagen del producto", description = "Elimina una imagen específica asociada a un producto")
    @DeleteMapping("/{id}/image/{product-image-id}")
    public ResponseEntity<ApiResponse> deleteProductImage(
    		@PathVariable("id") Integer productId, 
    		@PathVariable("product-image-id") Integer productImageId) {
    	return ResponseEntity.ok(svc.deleteImage(productId, productImageId));
    }

}