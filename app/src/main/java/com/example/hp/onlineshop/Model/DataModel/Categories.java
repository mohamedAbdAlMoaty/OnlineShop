package com.example.hp.onlineshop.Model.DataModel;

import java.util.ArrayList;

public class Categories {
	private int countTotal;
	private int pages;
	private ArrayList<Category> data;

	public void setCountTotal(int countTotal){
		this.countTotal = countTotal;
	}

	public int getCountTotal(){
		return countTotal;
	}

	public void setPages(int pages){
		this.pages = pages;
	}

	public int getPages(){
		return pages;
	}

	public void setData(ArrayList<Category> data){
		this.data = data;
	}

	public ArrayList<Category> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Categories{" +
			"count_total = '" + countTotal + '\'' +
			",pages = '" + pages + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}