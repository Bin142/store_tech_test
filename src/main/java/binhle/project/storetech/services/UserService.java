package binhle.project.storetech.services;

import binhle.project.storetech.DTO.request.UserCreationRequest;
import binhle.project.storetech.entity.impo.User;
import binhle.project.storetech.enums.Role;
import binhle.project.storetech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository Userrepo;

    //lấy tất cả user
    public List<User> findAll() {
        return Userrepo.findAll();
    }

    //tìm user theo id
    public User findById(int id) {
        var user = Userrepo.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    //tìm user theo username
    public User findByUsername(String username) {
        var user = Userrepo.findByUsername(username);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    //tạo mới 1 user
    public void createUser(UserCreationRequest request){
//        MultipartFile imageFile  = request.getImageFile();
        Date createdAt = new Date();
//        String storageFileName = createdAt.getTime() + "_" + imageFile .getOriginalFilename();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        try{
//            String uploadDir = "public/images/users/";
//            Path uploadPath = Paths.get(uploadDir);
//            if(!Files.exists(uploadPath)){
//                Files.createDirectories(uploadPath);
//            }
//            try(InputStream inputStream = imageFile .getInputStream()){
//                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
//            }
//        }catch (Exception ex){
//            System.out.println("Exception: " + ex.getMessage());
//        }
        User user = new User();
        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setPhonenumber(request.getPhonenumber());
        user.setAddress(request.getAddress());
        user.setAge(request.getAge());
        user.setCreatedAt(createdAt);
        user.setUsername(request.getUsername());
//        user.setImageFileName(storageFileName);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        //set mặc định là USER_ROLE
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        Userrepo.save(user);
    }

    //edit user
    public void showEditUser(int id, Model model){
        var user  = findById(id);
        UserCreationRequest request = new UserCreationRequest();
        request.setFullname(user.getFullname());
        request.setEmail(user.getEmail());
        request.setPhonenumber(user.getPhonenumber());
        request.setAddress(user.getAddress());
        request.setAge(user.getAge());
        request.setPassword(user.getPassword());

        model.addAttribute("request", request);
    }
//    public void editUser(int id){
//        try{
//            User user = findById(id);
//            UserCreationRequest request = new UserCreationRequest();
//            if (!request.getImageFile().isEmpty()) {
//                //delete old image
//                String uploadDir = "public/images/users/";
//                Path oldimagePath = Paths.get(uploadDir+user.getImageFileName());
//                try{
//                    Files.delete(oldimagePath);
//                }catch(Exception ex){
//                    System.out.println("Exception: " + ex.getMessage());
//                }
//                //save new image file
//                MultipartFile image = request.getImageFile();
//                Date createdAt = new Date();
//                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
//                try(InputStream inputStream = image.getInputStream()){
//                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
//                }
//                user.setImageFileName(storageFileName);
//            }
//            if(!Userrepo.existsByUsername(request.getUsername())){
//                user.setFullname(user.getFullname());
//                user.setEmail(user.getEmail());
//                user.setPhonenumber(user.getPhonenumber());
//                user.setAddress(user.getAddress());
//                user.setAge(user.getAge());
//                user.setPassword(user.getPassword());
//                Userrepo.save(user);
//            }else{
//                throw new RuntimeException("Username already exists");
//            }
//        }catch(Exception e){
//            System.out.println("Exception : "+e.getMessage());
//        }
//    }

    //xóa user
    public void deleteUser(int id){
        var user = findById(id);
        if(!Userrepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        Userrepo.deleteById(id);
    }

}
