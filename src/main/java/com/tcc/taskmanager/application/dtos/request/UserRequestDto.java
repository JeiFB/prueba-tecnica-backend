package com.tcc.taskmanager.application.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Invalid Name")
    @Size(min=2,message = "Min Name 2 chars")
    private String name;
    @NotBlank(message = "Invalid Email")
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "Invalid Password")
    private String password;
}
