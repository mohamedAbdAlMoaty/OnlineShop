package com.example.hp.onlineshop.Model.DataModel;

public class Category {
	private String image;
	private int subCategory;
	private int id;
	private String title;
	private int products;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setSubCategory(int subCategory){
		this.subCategory = subCategory;
	}

	public int getSubCategory(){
		return subCategory;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setProducts(int products){
		this.products = products;
	}

	public int getProducts(){
		return products;
	}

}
