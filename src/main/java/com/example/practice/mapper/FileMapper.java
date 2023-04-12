package com.example.practice.mapper;


import com.example.practice.dto.BoardDto;
import com.example.practice.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface FileMapper {


	public List<FileDto> getFileList(FileDto fileDto) throws Exception;

	public void insertFile(FileDto fileDto) throws Exception;




}
