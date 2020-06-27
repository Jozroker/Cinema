package com.cinema.point.dto;

import com.cinema.point.domain.Role;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Data
public class RegisterUserDTO {

    private Long id;

    @NotEmpty(message = "state.required.field")
    private String username;

    private String firstName;

    private String lastName;

    @NotEmpty(message = "state.required.field")
    @Email(message = "state.email.invalid")
    private String email;

    private String phone;

    @NotEmpty(message = "state.required.field")
    private String password;

    @NotEmpty(message = "state.required.field")
    private String confirmPassword;

    private Role role = Role.USER;

    private String pictureString = getByteImage();

    private String getByteImage() {
        String base64Image = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("F" +
                    ":\\PC_Educate\\Programming\\java\\cinema\\src\\main" +
                    "\\webapp\\resources\\image\\default-avatar.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            base64Image = Base64.encodeBase64String(imageInByte);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return base64Image;

    }

}
