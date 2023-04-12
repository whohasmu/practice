package com.example.practice.mapper;


import com.example.practice.dto.MenuDto;
import com.example.practice.dto.SortDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MenuMapper {


	public List<MenuDto> getMenuList(MenuDto menuDto) throws Exception;


	public List<MenuDto> getMenuMngList(MenuDto menuDto) throws Exception;

	public MenuDto getMenuDetail(MenuDto menuDto) throws Exception;

	public void insertMenu(MenuDto menuDto) throws Exception;

	public void updateMenu(MenuDto menuDto) throws Exception;

	public void updateReSort(MenuDto menuDto) throws Exception;





}
