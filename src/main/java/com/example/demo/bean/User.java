package com.example.demo.bean;

import com.example.demo.comm.result.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Result {




    private String name;
    private String address;


}
