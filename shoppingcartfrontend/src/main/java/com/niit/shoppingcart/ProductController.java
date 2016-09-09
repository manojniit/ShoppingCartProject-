package com.niit.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.Supplier;
import com.niit.shoppingcart.util.FileUtil;

@Controller
public class ProductController {

	@Autowired(required=true)
	private ProductDAO productDAO;
	@Autowired(required=true)
	private CategoryDAO categoryDAO; 
	@Autowired(required=true)
	private SupplierDAO supplierDAO;
	
	private String path="aaa.img";
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public String listProducts(Model model)
	{
		model.addAttribute("product",new Product());
		model.addAttribute("category",new Category());
		model.addAttribute("supplier",new Supplier());
		model.addAttribute("productList",this.productDAO.list());
		model.addAttribute("categoryList",this.categoryDAO.list());
		model.addAttribute("supplierList",this.supplierDAO.list());
		return "product";
	}

	@RequestMapping(value="/product/add", method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product)
	{
		Category category = categoryDAO.getByName(product.getCategory().getName());
		Supplier supplier = supplierDAO.getByName(product.getSupplier().getName());
		product.setCategory(category);
		product.setSupplier(supplier);
		product.setCategory_id(category.getId());
		product.setSupplier_id(supplier.getId());
		productDAO.save(product);
		productDAO.update(product);
		MultipartFile file =(MultipartFile) product.getImage();
		FileUtil.upload(path,file,product.getId()+".jpg");
	return "product";
	}
	@RequestMapping("product/remove/{id}")
	public String removeProduct(@PathVariable("id") String id,ModelMap model) throws Exception
	{
		try{
		productDAO.delete(id);
		model.addAttribute("message","successfully added");
		}
		catch(Exception e){
		model.addAttribute("message",e.getMessage());
		}
		return "redirect:/products";
	}
	@RequestMapping("product/edit/{id}")
	public String editProduct(@PathVariable("id") String id,Model model)
	{
		System.out.println("edit product");
		model.addAttribute("product",this.productDAO.get(id));
		model.addAttribute("listproducts",this.productDAO.list());
		model.addAttribute("categoryList",this.categoryDAO.list());
		model.addAttribute("supplierList",this.supplierDAO.list());
		return "product";
	}
	@RequestMapping("product/get/{id}")
	public String getSelectProduct(@PathVariable("id") String id,Model model,RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("selectedProduct",productDAO.get(id));
		return "product";
	}
	public String backToHome(@ModelAttribute("selectedProduct") final Object selectedProduct,final Model model)
	{
		model.addAttribute("selectedProduct", selectedProduct);
		return "/home";
	}
}

