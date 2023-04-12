package com.example.practice.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Alias("fileDto")
public class FileDto extends BaseDto {

    private Integer idx;
    private Integer boardIdx;
    private String saveFileNm;
    private String origFileNm;



}
