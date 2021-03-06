package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.Domains.*;
import com.gmail.liliyayalovchenko.Services.CategoryService;
import com.gmail.liliyayalovchenko.Services.ClientService;
import com.gmail.liliyayalovchenko.Services.FeedBackService;
import com.gmail.liliyayalovchenko.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FeedBackService feedBackService;

    @RequestMapping("/catalog")
    public ModelAndView catalog(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);

        ModelAndView modelAndView = new ModelAndView();
        List<Category> allCategories = categoryService.getAllCategories();

        System.out.println("All categories---------------------------------------------");
        for (Category allCategory : allCategories) {
            System.out.println(allCategory.getId());
        }
        System.out.println("All categories---------------------------------------------");

        modelAndView.addObject("categories", allCategories);
        modelAndView.addObject("products", productService.getAllProducts());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.setViewName("catalog");
        return modelAndView;
    }

    @RequestMapping("/catalog/sale")
    public ModelAndView onSale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("products", productService.getAllProductsOnSale());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.setViewName("sale");
        return modelAndView;
    }



    @RequestMapping("/catalog/brandSort")
    public ModelAndView catalogBrandSort(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);

        ModelAndView modelAndView = new ModelAndView();
        List<String> brandsForFiltering = Arrays.asList(request.getParameterValues("brandName"));
        List<Product> filteredProducts = new ArrayList<>();

        for (String s : brandsForFiltering) {
            filteredProducts.addAll(productService.getProductsByBrand(s));
        }

        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("products", filteredProducts);
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("activeBrands", brandsForFiltering);
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.setViewName("catalog");
        return modelAndView;
    }

    @RequestMapping("/catalog/priceDown")
    public ModelAndView priceDown(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("products", productService.getAllPriceDown());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.setViewName("catalog");
        return modelAndView;
    }

    @RequestMapping("/catalog/priceUp")
    public ModelAndView priceUp(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("products", productService.getAllPriceUp());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.setViewName("catalog");
        return modelAndView;
    }


    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public ModelAndView cart(@RequestParam(value = "id") int id,
                             @RequestParam(value = "productCategory") String category,
                             @RequestParam(value = "smallimage") String smallimage,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "price") int price,
                             @RequestParam(value = "currency") String currency,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        Product product = productService.getProductById(id);
        ProductInCart productInCart = new ProductInCart(product, category, smallimage, name, price, currency, 1);

        ArrayList<ProductInCart> productsInCart = (ArrayList<ProductInCart>) session.getAttribute("ProductsInCart");
        productsInCart.add(productInCart);
        session.setAttribute("ProductsInCart", productsInCart);
        session.setAttribute("cartSize", productsInCart.size());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("ProductsInCart", session.getAttribute("ProductsInCart"));
        modelAndView.addObject("totalValue", totalAmount(session));
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        session.setAttribute("totalValue", totalAmount(session));
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @RequestMapping(value = "/cart")
    public ModelAndView cartGet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.addObject("ProductsInCart", session.getAttribute("ProductsInCart"));
        modelAndView.addObject("totalValue", totalAmount(session));
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("cart");
        session.setAttribute("totalValue", totalAmount(session));
        return modelAndView;
    }

    @RequestMapping("/product/{id}")
    public ModelAndView product(@PathVariable("id") int id,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("product", productService.getProductById(id));
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.setViewName("product");
        return modelAndView;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
    public ModelAndView product(@RequestParam(value = "firstName") String firstName,
                                @PathVariable("id") int id,
                                @RequestParam(value = "phone") String phone,
                                @RequestParam(value = "evaluation") int evaluation,
                                @RequestParam(value = "feedback") String feedback,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        Product product = productService.getProductById(id);
        Client client;
        try {
            client = clientService.findClientByPhone(phone);
            FeedBack feedBack = new FeedBack(product, new Date(), client, evaluation, feedback);
            product.addFeedBack(feedBack);
            feedBackService.saveFeedBack(feedBack);
            productService.updateProduct(product);
        } catch (IndexOutOfBoundsException ex) {
            modelAndView.addObject("message", "Ой-ой...Отзывы могуть оставлять только клиенты. " +
                    "К сожалению, Вы не сделали ни одного заказа у нас.");
        }


        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("product", productService.getProductById(id));
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(value = "pattern") String pattern, HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        List<Product> productList = productService.search(pattern);
        if (productList == null) {
            modelAndView.addObject("size", 0);
        } else {
            modelAndView.addObject("products", productList);
        }
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("search");
        return modelAndView;
    }

    public void checkSession(HttpSession session) {
        try {
            ArrayList<ProductInCart> ProductsInCart = (ArrayList<ProductInCart>)
                    session.getAttribute("ProductsInCart");
            int cartCount = (int) session.getAttribute("cartSize");
            ProductsInCart.get(0);
        } catch (Exception e) {
            ArrayList<ProductInCart> ProductsInCart = new ArrayList<>();
            session.setAttribute("ProductsInCart", ProductsInCart);
            session.setAttribute("cartSize", ProductsInCart.size());
        }
    }

    public int totalAmount(HttpSession session) {
        int totalAmount = 0;
        ArrayList<ProductInCart> ProductsInCart = (ArrayList<ProductInCart>)
                session.getAttribute("ProductsInCart");
        for (ProductInCart aProductsInCart : ProductsInCart) {
            totalAmount += aProductsInCart.getPrice();
        }
        return totalAmount;
    }

}
