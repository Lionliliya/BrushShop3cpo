package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.Domains.Category;
import com.gmail.liliyayalovchenko.Domains.Product;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import com.gmail.liliyayalovchenko.Services.CategoryService;
import com.gmail.liliyayalovchenko.Services.PostService;
import com.gmail.liliyayalovchenko.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


@Controller
@RequestMapping("/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("articles", postService.getTwoLatest());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping("/about")
    public ModelAndView about(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("articles", postService.getTwoLatest());
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping("/catalog/brand/{brand}")
    public ModelAndView brand(@PathVariable("brand") String brand,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.addObject("curBrand", brand);
        modelAndView.addObject("productBrand", productService.getProductsByBrand(brand));
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("brand");
        return modelAndView;
    }

    @RequestMapping("/catalog/{id}")
    public ModelAndView category(@PathVariable("id") int id,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("currentCategory", categoryService.getCategoryById(id));
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());
        List<Product> products = productService.getProductsByCategoryId(id);
        if (products.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("products", products);
            modelAndView.addObject("productsSize", products.size());
        }
        modelAndView.setViewName("category");
        return modelAndView;
    }

    @RequestMapping("/catalog/brandSort/{id}")
    public ModelAndView categoryBrandFilter(@PathVariable("id") int id,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        List<String> brandsForFiltering = Arrays.asList(request.getParameterValues("brandName"));
        List<Product> filteredProducts = new ArrayList<>();

        for (String brandName : brandsForFiltering) {
            filteredProducts.addAll(productService.getProductsByBrandAndCategory(brandName, id));
        }
        modelAndView.addObject("currentCategory", categoryService.getCategoryById(id));
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.addObject("activeBrands", brandsForFiltering);
        if (filteredProducts.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("products", filteredProducts);
            modelAndView.addObject("productsSize", filteredProducts.size());
        }
        modelAndView.setViewName("category");
        return modelAndView;
    }

    @RequestMapping("/catalog/priceDown/{id}")
    public ModelAndView sortByPriceDown(@PathVariable("id") int id,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryService.getCategoryById(id);
        List<Product> productList = productService.getProductsByCategoryPriceDown(id);
        modelAndView.addObject("currentCategory", category);
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());

        if (productList.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("products", productList);
            modelAndView.addObject("productsSize", productList.size());
        }

        modelAndView.setViewName("category");
        return modelAndView;
    }

    @RequestMapping("/catalog/brand/priceUp/{curBrand}")
    public ModelAndView sortBrandByPriceUp(@PathVariable("curBrand") String curBrand,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.getProductsByBrandPriceUp(curBrand);
        modelAndView.addObject("curBrand", curBrand);
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());

        if (productList.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("productBrand", productList);
            modelAndView.addObject("productsSize", productList.size());
        }

        modelAndView.setViewName("brand");
        return modelAndView;
    }

    @RequestMapping("/catalog/brand/priceDown/{curBrand}")
    public ModelAndView sortBrandByPriceDown(@PathVariable("curBrand") String curBrand,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.getProductsByBrandPriceDown(curBrand);
        modelAndView.addObject("curBrand", curBrand);
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());

        if (productList.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("productBrand", productList);
            modelAndView.addObject("productsSize", productList.size());
        }

        modelAndView.setViewName("brand");
        return modelAndView;
    }

    @RequestMapping("/catalog/priceUp/{id}")
    public ModelAndView sortByPriceUp(@PathVariable("id") int id,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryService.getCategoryById(id);
        List<Product> productList = productService.getProductsByCategoryPriceUp(id);
        modelAndView.addObject("currentCategory", category);
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());

        if (productList.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("products", productList);
            modelAndView.addObject("productsSize", productList.size());
        }

        modelAndView.setViewName("category");
        return modelAndView;
    }



    @RequestMapping("/packing")
    public ModelAndView packing(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("packing");
        return modelAndView;
    }

    @RequestMapping("/deliveryAndPayments")
    public ModelAndView delivery(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("delivery");
        return modelAndView;
    }

    @RequestMapping("/contacts")
    public ModelAndView contacts(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productService.getAllBrands());
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @RequestMapping("/contacts/sendMail")
    public ModelAndView sendMail(@RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "secondName") String secondName,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "subject") String subject,
                                 @RequestParam(value = "massage") String massage,
                                 ModelMap model,
                                 HttpServletRequest request) throws MessagingException {
        HttpSession session = request.getSession();
        checkSession(session);

        sendMailFromCustomer(firstName, secondName, email, subject, massage);

        return new ModelAndView("redirect:/contacts", model);
    }

    private void sendMailFromCustomer(String firstName, String secondName, String email, String subject, String massage) throws MessagingException {
        Properties mailServerProperties = System.getProperties();

        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);

        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("service.beautytree@gmail.com"));
        generateMailMessage.setSubject("FROM CUSTOMER");
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("От клиента: " + firstName + " " + secondName + "\n");
        emailBody.append("Тема: " + subject + "\n");
        emailBody.append("Email: " + email + "\n");
        emailBody.append(massage);

        generateMailMessage.setText(emailBody.toString(), "utf-8");
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "service.beautytree", "lion5232");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();

    }

    public void checkSession(HttpSession session) {
        try {
            ArrayList<ProductInCart> ProductsInCart = (ArrayList<ProductInCart>)
                    session.getAttribute("ProductsInCart");
            int cartCount = (int)session.getAttribute("cartSize");
            ProductsInCart.get(0);
        } catch (Exception e) {
            ArrayList<ProductInCart> ProductsInCart = new ArrayList<>();
            session.setAttribute("ProductsInCart", ProductsInCart);
            session.setAttribute("cartSize", ProductsInCart.size());
        }
    }

    public int totalAmount(HttpSession session) {
        int totalAmount = 0;
        ArrayList<ProductInCart> ProductsInCart = (ArrayList<ProductInCart>) session.getAttribute("ProductsInCart");
        for (ProductInCart aProductsInCart : ProductsInCart) {
            totalAmount += aProductsInCart.getPrice();
        }
        return totalAmount;
    }

}
