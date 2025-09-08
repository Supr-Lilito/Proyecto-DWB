package com.product.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa una categoría.
 * 
 * @author Erik Eduardo Gómez López
 * @author Jesús Elías Vázquez Reyes
 */
@Entity
@Table(name = "category")
public class Category {
    /**
     * ID único de cada categoría.
     */
	@Id
    private Integer category_id;
    /**
     *  Nombre único de la categoría.
     */
    private String category;
    /**
     * Etiqueta única de categoría.
     */
    private String tag;
    /**
     * Estado de la categoría: 1 ó 0.
     */
    private Integer status;
    
    
    /**
     * Lista donde se guardan todas las categorias .
     */
    public static List<Category> listaCategorias = new ArrayList<>();

    /**
     * Constructor vacío de la clase Category.
     */
    public Category() {
    	
    }
    
    /**
     * Constructor por parámetros de la clase Category.
     *
     * @param category_id un identificador para la categoría
     * @param category el nombre de la categoría
     * @param tag una etiqueta asociada a dicha categoría
     * @param status el estado de la categoría
      */
    public Category(Integer category_id, String category, String tag, Integer status){
        super();
    	this.category_id = category_id;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    /**
     * Método para obtener el id de una categoría.
     * @return el identificador de la categoría
     */
    public Integer getCategoryId() {
        return category_id;
    }

    /**
     * Método para asignar el id de una categoría.
     * @param category_id el id de la categoría
     */
    public void setCategoryId(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * Método para obtener el nombre de una categoría.
     * @return el nombre de la categoría
     */
    public String getCategory() {
        return category;
    }

    /**
     * Método para asignar el nombre a una categoría.
     * @param category el nombre de la categoría
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Método para obtener la tag de una categoría.
     * @return la tag de la categoría
     */
    public String getTag() {
        return tag;
    }

    /**
     * Método para asignar una tag a una categoría.
     * @param tag la tag de la categoría
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Método para obtener el estado de una categoría.
     * @return el estado de la categoría
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Método para asignar el estado a una categoría.
     * @param status el estado de la categoría
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}