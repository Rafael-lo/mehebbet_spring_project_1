package com.mehebbet_spring_project_1.mehebbet_spring_project_1.controllers;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Category;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Product;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.UserDtls;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.CartService;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.CategoryService;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.ProductService;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.UserService;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.util.CommonUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import io.micrometer.common.util.StringUtils;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CartService cartService;

    @ModelAttribute
    public void getUserDetails(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            UserDtls userDtls = userService.getUserByEmail(email);
            m.addAttribute("user", userDtls);
            Integer countCart = cartService.getCountCart(userDtls.getId());
            m.addAttribute("countCart", countCart);
        }

        List<Category> allActiveCategory = categoryService.getAllActiveCategory();
        m.addAttribute("categorys", allActiveCategory);
    }

    @GetMapping("/")
    public String index(Model m) {

        List<Category> allActiveCategory = categoryService.getAllActiveCategory().stream()
                .sorted((c1,c2)->c2.getId().compareTo(c1.getId()))
                .limit(6).toList();
        List<Product> allActiveProducts = productService.getAllActiveProducts("").stream()
                .sorted((p1,p2)->p2.getId().compareTo(p1.getId()))
                .limit(8).toList();
        m.addAttribute("category", allActiveCategory);
        m.addAttribute("products", allActiveProducts);
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/products")
    public String products(Model m, @RequestParam(value = "category", defaultValue = "") String category,
                           @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize,
                           @RequestParam(defaultValue = "") String ch) {

        List<Category> categories = categoryService.getAllActiveCategory();
        m.addAttribute("paramValue", category);
        m.addAttribute("categories", categories);

        Page<Product> page = null;
        if (StringUtils.isEmpty(ch)) {
            page = productService.getAllActiveProductPagination(pageNo, pageSize, category);
        } else {
            page = productService.searchActiveProductPagination(pageNo, pageSize, category, ch);
        }

        List<Product> products = page.getContent();
        m.addAttribute("products", products);
        m.addAttribute("productsSize", products.size());

        m.addAttribute("pageNo", page.getNumber());
        m.addAttribute("pageSize", pageSize);
        m.addAttribute("totalElements", page.getTotalElements());
        m.addAttribute("totalPages", page.getTotalPages());
        m.addAttribute("isFirst", page.isFirst());
        m.addAttribute("isLast", page.isLast());

        return "product";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable int id, Model m) {
        Product productById = productService.getProductById(id);
        m.addAttribute("product", productById);
        return "view_product";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session)
            throws IOException {

        String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
        user.setProfileImage(imageName);
        UserDtls saveUser = userService.saveUser(user);

        if (!ObjectUtils.isEmpty(saveUser)) {
            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/img").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator
                        + file.getOriginalFilename());

//				System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            session.setAttribute("succMsg", "Успех");
        } else {
            session.setAttribute("errorMsg", "Что то не так");
        }

        return "redirect:/register";
    }

//	Forgot Password Code

    @GetMapping("/forgot-password")
    public String showForgotPassword() {
        return "forgot_password.html";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, HttpSession session, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {

        UserDtls userByEmail = userService.getUserByEmail(email);

        if (ObjectUtils.isEmpty(userByEmail)) {
            session.setAttribute("errorMsg", "Не правильная почта");
        } else {

            String resetToken = UUID.randomUUID().toString();
            userService.updateUserResetToken(email, resetToken);

            //String url = CommonUtil.generateUrl(request) + "/reset-password?token=" + resetToken;

            //Boolean sendMail = commonUtil.sendMail(url, email);

//            if (sendMail) {
//                session.setAttribute("succMsg", "Проверте почту вам отправлена ссылка на изменение пароля");
//            } else {
//                session.setAttribute("errorMsg", "Что то не так");
//            }
        }

        return "redirect:/forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPassword(@RequestParam String token, HttpSession session, Model m) {

        UserDtls userByToken = userService.getUserByToken(token);

        if (userByToken == null) {
            m.addAttribute("msg", "Ссылка устарела");
            return "message";
        }
        m.addAttribute("token", token);
        return "reset_password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String password, HttpSession session,
                                Model m) {

        UserDtls userByToken = userService.getUserByToken(token);
        if (userByToken == null) {
            m.addAttribute("errorMsg", "Ссылка не устарела");
            return "message";
        } else {
            userByToken.setPassword(passwordEncoder.encode(password));
            userByToken.setResetToken(null);
            userService.updateUser(userByToken);
            m.addAttribute("msg", "Успех");

            return "message";
        }

    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String ch, Model m) {
        List<Product> searchProducts = productService.searchProduct(ch);
        m.addAttribute("products", searchProducts);
        List<Category> categories = categoryService.getAllActiveCategory();
        m.addAttribute("categories", categories);
        return "product";

    }
}