package com.it.service;

import java.util.List;

import com.it.domain.CartmainVO;
import com.it.domain.CartsubVO;

public interface CartService {
	
	public void cartinsert(CartmainVO cartmain, CartsubVO cartsub);
	
	public CartmainVO readMainid(CartmainVO cartmain);
	
	public List<CartsubVO> getListCart(CartmainVO cartmain);
	
	public String deleteSub(CartsubVO cartsub);
	
	public void deleteMain(CartmainVO cartmain);
	
	public String updateSub(CartsubVO cartsub);
	
	public void cartdeleteAll(CartmainVO cartmain);
}
