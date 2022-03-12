package com.it.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.CartmainVO;
import com.it.domain.CartsubVO;
import com.it.domain.ProductVO;
import com.it.service.CartService;
import com.it.service.MemberService;
import com.it.service.ProductService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/shop/")
public class ShopController {

	@Setter(onMethod_ = @Autowired)
	private ProductService serviceproduct;
	
	@Setter(onMethod_ = @Autowired)
	private MemberService memberservice;
	
	@Setter(onMethod_ = @Autowired)
	private CartService cartservice;
	
	@GetMapping("/list")
	private void list(Model model) {
		model.addAttribute("list", serviceproduct.getList());
	}
	
	@GetMapping("/read")
	private void read(Model model, ProductVO product) {
		model.addAttribute("shop", serviceproduct.read(product));
	}
	
	@GetMapping("/cart")
	private void cartsub(CartsubVO cartsub, Model model) {
		ProductVO product = new ProductVO();
		product.setP_code(cartsub.getP_code());
		log.info(cartsub);
		model.addAttribute("cartsub", serviceproduct.read(product));
	}
	
	@PostMapping("/cart")
	private String cartsub(CartsubVO cartsub, HttpSession session) {
		String m_id = (String) session.getAttribute("m_id");
		if(m_id != null) {
			CartmainVO cartmain = new CartmainVO();
			cartmain.setM_id(m_id);
			cartservice.cartinsert(cartmain, cartsub);
			return "redirect:/shop/cartinfo";
		} else {
			return "redirect:/shop/member/login";
		}
	}
	
	@GetMapping("/cartinfo")
	public String cartinfo(HttpSession session, Model model) {
		// 세션아이디를 이용해서 cm_code를 구하고
		// cm_code를 이용해서 getListCart를 조회해서 리스트 출력
		String m_id = (String) session.getAttribute("m_id");
		String m_name = (String) session.getAttribute("m_name");
		if(m_id != null) {
			CartmainVO cartmain = new CartmainVO();
			cartmain.setM_id(m_id);
			cartmain = cartservice.readMainid(cartmain);
			log.info(cartmain);
			model.addAttribute("m_id", m_id);
			model.addAttribute("m_name", m_name);
			model.addAttribute("cm_code", cartmain.getCm_code());
//			cartservice.getListCart(cartmain).forEach(cs -> log.info(cs));
			model.addAttribute("list", cartservice.getListCart(cartmain));
			return "/shop/cartinfo";
		} else {
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("/delete")
	public String deleteSub(CartsubVO cartsub) {
		cartservice.deleteSub(cartsub);
		return "redirect:/shop/cartinfo";
	}
	
	@PostMapping("/updatecnt")
	public String updateCnt(CartsubVO cartsub) {
		log.info("updatecnt다  " + cartsub);
		cartservice.updateSub(cartsub);
		return "redirect:/shop/cartinfo";
	}
	
	@GetMapping("/cartdeleteall")
	public void cartdeleteall(CartmainVO cartmain) {
		
	}
}
