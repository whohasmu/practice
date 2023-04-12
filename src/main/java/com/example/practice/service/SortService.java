package com.example.practice.service;

import com.example.practice.dto.BoardDto;
import com.example.practice.dto.FileDto;
import com.example.practice.dto.SortDto;
import com.example.practice.mapper.BoardMapper;
import com.example.practice.mapper.FileMapper;
import com.example.practice.mapper.SortMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class SortService {

    @Autowired
    private SortMapper sortMapper;


    public List<SortDto> getSortList(SortDto sortDto) throws Exception  {

        if (sortDto.getRowsPerPage() < 1) {
            throw new Exception();
        }
        if (sortDto.getPageIdx() < 1) {
            throw new Exception();
        }

        return sortMapper.getSortList(sortDto);

    }



    @Transactional
    public void updateSort(SortDto sortDto) throws Exception {

        sortMapper.updateSort(sortDto);
        sortMapper.updateReSort(sortDto);

    }



}
