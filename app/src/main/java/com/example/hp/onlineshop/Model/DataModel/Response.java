package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.SerializedName;

public class Response{
	private int code;
	@SerializedName("data")
	private Data data;
	private boolean status;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' +
			",status = '" + status + '\'' + 
			"}";
		}
}
