package com.cinema.point.dto;

import com.cinema.point.domain.Role;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

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

    //    @NotEmpty(message = "phone value is required")
//    @Pattern(regexp = "^\\d+$", message = "phone cannot contain letters")
//    @Size(min = 10, max = 10, message = "invalid phone value")
    private String phone;

    @NotEmpty(message = "state.required.field")
    private String password;

    @NotEmpty(message = "state.required.field")
    private String confirmPassword;

    private Role role = Role.USER;

    private byte[] picture = getByteImage();

    private byte[] getByteImage() {
        BufferedImage bImage = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            bImage = ImageIO.read(new File("F:\\PC_Educate\\Programming\\java\\cinema\\src\\main\\webapp\\resources\\image\\default-avatar.png"));
            ImageIO.write(bImage, "png", bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

}
