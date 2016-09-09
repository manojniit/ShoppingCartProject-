package com.niit.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.niit.shoppingcart.MultipartFile;

@Entity
@Table
@Component
public class Product {
	@Id
	private String id;
	private String name;
	private String description;
	private int price;
	@Transient
	public MultipartFile image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public org.springframework.web.multipart.MultipartFile getCategory() {
		// TODO Auto-generated method stub
		return null;
	}
	public org.springframework.web.multipart.MultipartFile getSupplier() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
	public void setSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		
	}
	public void setCategory_id(String id2) {
		// TODO Auto-generated method stub
		
	}
	public void setSupplier_id(String id2) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	

}
