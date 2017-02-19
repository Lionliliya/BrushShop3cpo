package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.ProductDAO;
import com.gmail.liliyayalovchenko.Domains.Category;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import com.gmail.liliyayalovchenko.Domains.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Product a");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getAllPriceDown() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Product a order by a.price desc ");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getAllPriceUp() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Product a order by a.price asc ");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getProductsByCategory(String categoryName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT c FROM Category c WHERE c.name =:parameter")
                .setParameter("parameter", categoryName);
        if (query.list().size()==0) {
            ArrayList<Product> products = new ArrayList<Product>();
            return products;
        } else {
            Category category = (Category) query.list().get(0);
            Query query2 = session.createQuery
                    ("SELECT a FROM Product a WHERE a.productCategory =:var")
                    .setParameter("var", category);
            return query2.list();
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getProductsByBrand(String brand) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Product p WHERE p.brand =:parameter")
                .setParameter("parameter", brand);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getProductsByBrandAndCategory(String brandName, int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Product p WHERE p.brand =:brand and p.productCategory.id =:category");
        query.setParameter("brand", brandName);
        query.setParameter("category", categoryId);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getAllProductsOnSale() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Product p WHERE p.discount > 0");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Set<String> getAllBrands() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Product p");
        List<Product> products = query.list();
        Set<String> brands = products.stream().map(Product::getBrand).collect(Collectors.toSet());
        return brands;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getProductsByBrandPriceUp(String curBrand) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Product p where p.brand = :parameter order by p.price");
        query.setParameter("parameter", curBrand);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getProductsByBrandPriceDown(String curBrand) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Product p where p.brand = :parameter order by p.price desc");
        query.setParameter("parameter", curBrand);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Product getProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, id);
        return product;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getProductsByCategoryId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Product a WHERE a.productCategory.id =:var");
        query.setParameter("var", id);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getProductsByCategoryPriceDown(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Product p WHERE p.productCategory.id =:var order by p.price desc ");
        query.setParameter("var", id);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> getProductsByCategoryPriceUp(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery
                ("SELECT p FROM Product p WHERE p.productCategory.id =:var order by p.price asc")
                .setParameter("var", id);
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveProduct(int id, String name, int price, String currency, Category productCategory, int amount,
                            String inStock, String description, String shortDesc, String metaDescription, String metaKeyWords,
                            String metaTitle, String image1,
                            String image2, String image3, String image4, boolean isNew, int discount, String brand) {
        Session session = sessionFactory.getCurrentSession();

        Product resultProduct = session.load(Product.class, id);
        updateProduct(name, price, currency, productCategory, amount, inStock, description, shortDesc, metaDescription, metaKeyWords, metaTitle, image1, image2, image3, image4, isNew, discount, brand, resultProduct);
        session.update(resultProduct);
    }

    private void updateProduct(String name, int price, String currency, Category productCategory, int amount, String inStock, String description, String shortDesc, String metaDescription, String metaKeyWords, String metaTitle, String image1, String image2, String image3, String image4, boolean isNew, int discount, String brand, Product resultProduct) {
        resultProduct.setName(name);
        resultProduct.setPrice(price);
        resultProduct.setCurrency(currency);
        resultProduct.setProductCategory(productCategory);
        resultProduct.setAmount(amount);
        resultProduct.setInStock(inStock);
        resultProduct.setDescription(description);
        resultProduct.setShortDesc(shortDesc);
        resultProduct.setMetaDescription(metaDescription);
        resultProduct.setMetaKeyWords(metaKeyWords);
        resultProduct.setMetaTitle(metaTitle);
        resultProduct.setImage1(image1);
        resultProduct.setImage2(image2);
        resultProduct.setImage3(image3);
        resultProduct.setImage4(image4);
        resultProduct.setIsNew(isNew);
        resultProduct.setDiscount(discount);
        resultProduct.setBrand(brand);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addFeedbackToProduct(FeedBack feedBack, int productId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, productId);
        product.addFeedBack(feedBack);
        session.update(product);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeFeedBack(FeedBack feedBack) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, feedBack.getProduct().getId());
        product.removeFeedBack(feedBack);
        session.update(product);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Product> search(String pattern){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Product a WHERE a.name LIKE :pattern");
        query.setParameter("pattern", "%" + pattern + "%");
        try {
            List<Product> resultList = query.list();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateProduct(Product productById) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productById);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeFromCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a FROM Product a WHERE a.productCategory.id = :var");
        query.setParameter("var", id);
        List<Product> productList = query.list();
        if (productList.size() != 0) {
            productList.forEach(session::delete);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, id);
        session.delete(product);
    }
}
