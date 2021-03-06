package com.it.mapper;

import java.util.List;

import com.it.domain.CartmainVO;
import com.it.domain.CartsubVO;

public interface CartMapper {
	public List<CartmainVO> getListMain();
	
	public List<CartsubVO> getListSub(CartmainVO cartmain);
	
	public List<CartsubVO> getListCart(CartmainVO cartmain);

	public CartmainVO readMain(CartmainVO cartmain);
	
	public CartsubVO readSub(CartsubVO cartsub);
	
	public CartsubVO readSubProduct(CartsubVO cartsub);
	
	public CartmainVO readMainid(CartmainVO cartmain);
	
	public void insertMain(CartmainVO cartmain);
	
	public void insertSub(CartsubVO cartsub);
	
	public void updateSub(CartsubVO cartsub);
	
	public void deleteMain(CartmainVO cartmain);
	
	public void deleteSub(CartsubVO cartsub);
	
	public void cartdeleteAll(CartmainVO cartmain);
	
}
