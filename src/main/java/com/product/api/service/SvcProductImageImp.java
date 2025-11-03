package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.commons.dto.ApiResponse;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcProductImageImp implements SvcProductImage {

	@Autowired
	RepoProductImage repo;
	
	@Value("${app.upload.dir}")
	private String uploadDir;
	
	@Override
	public ApiResponse upload(Integer productId, DtoProductImageIn in) {
		try {
			
			// 0 - Validar formato del Base64
			// Eliminar el prefijo "data:image/png;base64," si existe
			if (in.getImage().startsWith("data:image")) {
				int commaIndex = in.getImage().indexOf(",");
					if (commaIndex != -1) {
				in.setImage(in.getImage().substring(commaIndex + 1));
				}
			}

		    // 1 - Decodificar imagen
			
			// Decodifica la cadena Base64 a bytes
			byte[] imageBytes = Base64.getDecoder().decode(in.getImage());

			// Genera un nombre único para la imagen (se asume extensión PNG)
			String fileName = UUID.randomUUID().toString() + ".png";

			// Construye la ruta completa donde se guardará la imagen
			Path imagePath = Paths.get(uploadDir, "img", "product", fileName);
			
			
			// 2 - Guardar el File en el sistema de archivos
			
			// Asegurarse de que el directorio exista
			Files.createDirectories(imagePath.getParent());

			// Escribir el archivo en el sistema de archivos
			Files.write(imagePath, imageBytes);
			
			
			// 3 - Guardar la ruta en la base de datos
			
			// Crear la entidad productImage y guardar la URL en la base de datos
		
			ProductImage productImage = new ProductImage();
			productImage.setProductId(productId);
			productImage.setImage("/" + uploadDir + "/img/product/" + fileName);
			productImage.setStatus(1);

			// Guardar la ruta de la imagen
			repo.save(productImage);
			
		    return new ApiResponse("La imagen del producto ha sido actualizada");
		}catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}

	}
	
	
	@Override
	public List<ProductImage> getImages(Integer productId) {
		try {
			return repo.findByProductId(productId);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ApiResponse deleteImage(Integer productId, Integer productImageId) {
		try {
			Optional<ProductImage> optional = repo.findById(productImageId);
			
			if (optional.isEmpty()) {
				throw new ApiException(HttpStatus.NOT_FOUND, "La imagen no existe");
			}
			
			ProductImage image = optional.get();
			
			if (!image.getProductId().equals(productId)) {
				throw new ApiException(HttpStatus.FORBIDDEN, "La imagen no pertenece al producto especificado");
			}

			Path imagePath = Paths.get(uploadDir, image.getImage());
			Files.deleteIfExists(imagePath);
			
			repo.delete(image);
			
			return new ApiResponse("La imagen ha sido eliminada");
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		} catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el archivo");
		}
	}

}
