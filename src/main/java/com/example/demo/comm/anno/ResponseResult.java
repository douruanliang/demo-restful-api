package com.example.demo.comm.anno;


import com.example.demo.comm.result.PlatformResult;
import com.example.demo.comm.result.Result;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

    Class<? extends Result> value() default PlatformResult.class;

}
