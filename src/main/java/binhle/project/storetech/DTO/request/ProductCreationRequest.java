package binhle.project.storetech.DTO.request;

import binhle.project.storetech.entity.category;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    String name;
    String brand;
    double price;
    @Column(columnDefinition = "TEXT")
    String description;

    Date createdAt;
    Date updatedAt;
    MultipartFile imageFileName;
    category category;

    public MultipartFile getImageFile() {
        return imageFileName;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFileName = imageFile;
    }

}
