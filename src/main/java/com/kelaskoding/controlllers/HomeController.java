package com.kelaskoding.controlllers;

import javax.servlet.http.HttpSession;

import com.kelaskoding.Entity.Product;
import com.kelaskoding.Service.ProductService;
import com.kelaskoding.dto.SearchFormData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpSession httpSession;
    
    @GetMapping
    public String home(Model model){
        String messages = "ini adalah aplikasi spring boot dengan MVC";
        model.addAttribute("msg", messages);
        model.addAttribute("SearchForm",  new SearchFormData());
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("product", new Product());
        return "add";
    }

    @PostMapping("/save")
    public String save(Product product, Model model){
        productService.add(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        //service to delete
        productService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        model.addAttribute("product",  productService.findById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(Product product){
        productService.edit(product);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(SearchFormData searchFormData, Model model){
        model.addAttribute("SearchForm", searchFormData);
        httpSession.setAttribute("searchKey", searchFormData.getKeyword());
        model.addAttribute("products", productService.findByName(searchFormData.getKeyword()));
        return "index";
    }
}
