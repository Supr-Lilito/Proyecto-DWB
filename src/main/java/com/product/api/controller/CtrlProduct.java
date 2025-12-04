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

import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;
import com.product.api.service.SvcProduct;
import com.product.commons.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Catálogo de products")
public class CtrlProduct {

	@Autowired
	SvcProduct svc;

	@Operation(summary = "Consultar todos los productos", description = "Obtiene el listado completo de productos registrados en el sistema")
	@GetMapping
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		return svc.getProducts();
	}

	@Operation(summary = "Consultar producto por ID", description = "Obtiene el detalle completo de un producto específico mediante su identificador")
	@GetMapping("/{id}")
	public ResponseEntity<DtoProductOut> getProduct(@PathVariable Integer id) {
		return svc.getProduct(id);
	}

	@Operation(summary = "Crear producto", description = "Registra un nuevo producto en el sistema")
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody DtoProductIn in) {
		return svc.createProduct(in);
	}

	@Operation(summary = "Actualizar producto", description = "Modifica la información de un producto existente")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @Valid @RequestBody DtoProductIn in) {
		return svc.updateProduct(id, in);
	}

	@Operation(summary = "Habilitar producto", description = "Activa un producto previamente deshabilitado")
	@PatchMapping("/{id}/enable")
	public ResponseEntity<ApiResponse> enableProduct(@PathVariable Integer id) {
		return svc.enableProduct(id);
	}

	@Operation(summary = "Deshabilitar producto", description = "Desactiva un producto del sistema sin eliminarlo")
	@PatchMapping("/{id}/disable")
	public ResponseEntity<ApiResponse> disableProduct(@PathVariable Integer id) {
		return svc.disableProduct(id);
	}
	
	
    @GetMapping("/gtin/{gtin}")
    public ResponseEntity<Product> getProductByGtin(@PathVariable("gtin") String gtin) {
        return svc.getProductByGtin(gtin);
    }

    @PutMapping("/{gtin}/stock/{stock}")
    public ResponseEntity<ApiResponse> updateProductStock(
            @PathVariable("gtin") String gtin, 
            @PathVariable("stock") Integer stock) {
        return svc.updateProductStock(gtin, stock);
    }
	
	
}