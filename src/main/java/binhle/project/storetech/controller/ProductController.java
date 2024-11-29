package binhle.project.storetech.controller;


import binhle.project.storetech.DTO.request.ProductCreationRequest;
import binhle.project.storetech.entity.impo.Product;
import binhle.project.storetech.services.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping({"", "/"})
    public String showAllProduct(Model model){
        service.getAllProduct();
        model.addAttribute("products", service.getAllProduct());
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        ProductCreationRequest request = new ProductCreationRequest();
        model.addAttribute("request", request);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductCreationRequest request
            , BindingResult result){
        if(request.getImageFile().isEmpty()){
            result.addError(new FieldError("request", "imageFile", "Image file is required"));
        }
        if(result.hasErrors()){
            return "products/CreateProduct";
        }
        service.CreateProduct(request);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id){
        try{
            Product product_find = service.findProductById(id);
            model.addAttribute("product_find", product_find);
            service.showEditProduct(id, model);
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/products";
        }
        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute ProductCreationRequest request
            , BindingResult result
            , @RequestParam int id
            , Model model){
        try {
            Product product_find = service.findProductById(id);
            model.addAttribute("product_find", product_find);
            if (result.hasErrors()) {
                return "products/EditProduct";
            }
            service.editProduct(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam int id
    ){
        try {
            service.deleteProduct(id);
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/products";
    }
}
