package com.example.practice.mapper;


import com.example.practice.dto.BoardDto;
import com.example.practice.dto.SortDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SortMapper {


	public List<SortDto> getSortList(SortDto sortDto) throws Exception;

	public void updateSort(SortDto sortDto) throws Exception;

	public void updateReSort(SortDto sortDto) throws Exception;




}
