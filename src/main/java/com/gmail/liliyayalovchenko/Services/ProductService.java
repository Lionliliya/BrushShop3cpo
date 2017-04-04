package com.gmail.liliyayalovchenko.Services;

import com.gmail.liliyayalovchenko.DAO.ProductDAO;
import com.gmail.liliyayalovchenko.Domains.Category;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import com.gmail.liliyayalovchenko.Domains.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Transactional
    public List<Product> getProductsByCategory(String categoryName) {
        return productDAO.getProductsByCategory(categoryName);
    }

    @Transactional
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    @Transactional
    public List<Product> getProductsByCategoryId(int id) {
        return productDAO.getProductsByCategoryId(id);
    }

    @Transactional
    public List<Product> getProductsByBrand(String brand) {
        return productDAO.getProductsByBrand(brand);
    }

    @Transactional
    public void saveProduct(Product product) {
        productDAO.saveProduct(product);
    }

    @Transactional
    public void saveProduct(int id, String name, int price, String currency, Category productCategory, int amount,
                     String inStock, String description, String shortDesc, String metaDescription,
                     String metaKeyWords, String metaTitle, String image1,
                     String image2, String image3, String image4, boolean isNew, int discount, String brand) {
        productDAO.saveProduct(id, name, price, currency, productCategory, amount, inStock,
                description, shortDesc, metaDescription, metaKeyWords, metaTitle, image1,
                image2, image3, image4, isNew, discount, brand);
    }

    @Transactional
    public void addFeedbackToProduct(FeedBack feedBack, int productId) {
        productDAO.addFeedbackToProduct(feedBack, productId);
    }

    @Transactional
    public List<Product> search(String pattern) {
        return productDAO.search(pattern);
    }

    @Transactional
    public List<Product> getProductsByCategoryPriceDown(int id) {
        return productDAO.getProductsByCategoryPriceDown(id);
    }

    @Transactional
    public List<Product> getProductsByCategoryPriceUp(int id) {
        return productDAO.getProductsByCategoryPriceUp(id);
    }

    @Transactional
    public List<Product> getAllPriceDown() {
        return productDAO.getAllPriceDown();
    }

    @Transactional
    public List<Product> getAllPriceUp() {
        return productDAO.getAllPriceUp();
    }

    @Transactional
    public void removeFeedBack(FeedBack feedBack) {
        productDAO.removeFeedBack(feedBack);
    }

    @Transactional
    public void updateProduct(Product productById) {
        productDAO.updateProduct(productById);
    }

    @Transactional
    public void removeFromCategory(int id) {
        productDAO.removeFromCategory(id);
    }

    @Transactional
    public void remove(int id) {
        productDAO.remove(id);
    }

    @Transactional
    public Set<String> getAllBrands() {
        return productDAO.getAllBrands();
    }

    @Transactional
    public List<Product> getProductsByBrandPriceUp(String curBrand) {
        return productDAO.getProductsByBrandPriceUp(curBrand);
    }

    @Transactional
    public List<Product> getProductsByBrandPriceDown(String curBrand) {
        return productDAO.getProductsByBrandPriceDown(curBrand);
    }

    @Transactional
    public List<Product> getProductsByBrandAndCategory(String brandName, int categoryId) {
        return productDAO.getProductsByBrandAndCategory(brandName, categoryId);
    }

    @Transactional
    public List<Product> getAllProductsOnSale() {
        return productDAO.getAllProductsOnSale();
    }

}
