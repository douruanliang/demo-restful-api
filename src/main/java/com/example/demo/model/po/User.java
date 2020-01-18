package com.example.demo.model.po;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{5,19}$")
    private String pwd;

    private String nickname;
}
