package com.gmail.liliyayalovchenko.Services;

import com.gmail.liliyayalovchenko.DAO.ProductInCartDAO;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ProductInCartService {

    @Autowired
    private ProductInCartDAO productInCartDAO;

    @Transactional
    public List<ProductInCart> getProductsInCart() {
        return productInCartDAO.getProductsInCart();
    }

    @Transactional
    public void saveProductInCart(List<ProductInCart> productsCart) {
        productInCartDAO.saveProductInCart(productsCart);
    }

    @Transactional
    public void saveProductInCart(ProductInCart productInCart) {
        productInCartDAO.saveProductInCart(productInCart);
    }

    @Transactional
    public ProductInCart getById(int id) {
        return productInCartDAO.getById(id);
    }

    @Transactional
    public void delete(ProductInCart product) {
        productInCartDAO.delete(product);
    }

    @Transactional
    public List<ProductInCart> getProductsInCart(int id) {
        return productInCartDAO.getProductsInCart(id);
    }
}
