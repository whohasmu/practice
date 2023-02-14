package com.example.practice.DTO;

import com.example.practice.validate.SampleValidates;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class TestVO {

    @NotEmpty(groups = SampleValidates.Group1.class, message = "test message 1")
    private String name;
    @NotEmpty(groups = SampleValidates.Group1.class, message = "test message 22")
    private String text;

}
