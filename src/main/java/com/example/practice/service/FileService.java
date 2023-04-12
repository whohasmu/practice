package com.example.practice.service;

import com.example.practice.dto.BoardDto;
import com.example.practice.dto.FileDto;
import com.example.practice.mapper.BoardMapper;
import com.example.practice.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileService {

    @Autowired
    private FileMapper fileMapper;

//    public List<BoardDto> getFileInfo(FileDto fileDto) throws Exception  {
//
//
//        return fileMapper.getFileInfo(fileDto);
//
//    }


}
