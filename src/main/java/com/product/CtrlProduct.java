package com.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CtrlProduct {

    @GetMapping
    public List<Category> getCategories() {
    	
        List<Category> categorias = new ArrayList<>();
  
        categorias.add(new Category(1, "Zapatos", "Zpts", 1));
        categorias.add(new Category(2, "Electrodomésticos", "Eltrc", 1));
        categorias.add(new Category(3, "Consolas", "Cnsl", 1));
        categorias.add(new Category(4, "Fruta", "Frt", 1));
        categorias.add(new Category(5, "Camisas", "Cmsas", 1));
        
        return categorias;
    }
}
