package binhle.project.storetech.services;

import binhle.project.storetech.DTO.request.ProductCreationRequest;
import binhle.project.storetech.entity.impo.Product;
import binhle.project.storetech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    //lấy danh sách sản phẩm
    public List<Product> getAllProduct(){
        return repo.findAll();
    }

    //tạo mới 1 sản phẩm
    public void CreateProduct(ProductCreationRequest request){
        MultipartFile image =request.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
        try{
            String uploadDir = "public/images/products/";
            Path uploadPath = Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }

        Product product = new Product();
        if(product.getUpdatedAt() == null){
            product.setUpdatedAt(createdAt);
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setBrand(request.getBrand());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storageFileName);
        product.setCategory(request.getCategory());
        repo.save(product);
    }

    //edit sản phẩm
    public void showEditProduct(int id, Model model){
            var product = findProductById(id);
            ProductCreationRequest request = new ProductCreationRequest();
            if(product.getUpdatedAt() == null){
                product.setUpdatedAt(request.getUpdatedAt());
            }
            request.setName(product.getName());
            request.setBrand(product.getBrand());
            request.setPrice(product.getPrice());
            request.setDescription(product.getDescription());
            request.setCreatedAt(product.getCreatedAt());
            request.setUpdatedAt(product.getUpdatedAt());
            model.addAttribute("request", request);
    }
    public void editProduct(int id){
        try {
            ProductCreationRequest request = new ProductCreationRequest();
            Product product_find = findProductById(id);
            if (!request.getImageFile().isEmpty()) {
                //delete old image
                String uploadDir = "public/images/products/";
                Path oldimagePath = Paths.get(uploadDir+product_find.getImageFileName());
                try{
                    Files.delete(oldimagePath);
                }catch(Exception ex){
                    System.out.println("Exception: " + ex.getMessage());
                }
                //save new image file
                MultipartFile image = request.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
                try(InputStream inputStream = image.getInputStream()){
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }
                product_find.setImageFileName(storageFileName);
            }
            product_find.setName(request.getName());
            product_find.setDescription(request.getDescription());
            product_find.setPrice(request.getPrice());
            product_find.setBrand(request.getBrand());
            repo.save(product_find);
        }catch(Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
    }

    //xóa sản phẩm
    public void deleteProduct(int id){
        var product = findProductById(id);
        //delete image
        Path imagePath = Paths.get("public/images/products/" + product.getImageFileName());
            try {
                Files.delete(imagePath);
            }catch(Exception e){
                System.out.println("Exception :"+ e.getMessage());
            }
             repo.deleteById(id);
    }

    public Product findProductById(int id){
        var product = repo.findById(id);
        if(product.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        return product.get();
    }

}
