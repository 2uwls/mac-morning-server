package morning.macmorning.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {


    @NotEmpty(message = "사용자 이메일은 필수 항목입니다.")
    @Email
    private String email;
    @Size(min = 2, max = 10)
    @NotEmpty(message = "사용자 이름은 필수 항목입니다.")
    private String name;
    @NotEmpty(message = "사용자 비밀번호는 필수 항목입니다.")
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
