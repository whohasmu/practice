package com.example.practice.VO;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestVO {

    @NotNull
    private String name;
    private int age;

}
