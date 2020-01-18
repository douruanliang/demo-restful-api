package com.example.demo.comm.anno;


import java.lang.annotation.*;

@Target({ElementType.TYPE ,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginedAuth {
}
