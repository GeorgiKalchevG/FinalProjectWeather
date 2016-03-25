package com.example.model;

public class Car {
	private String model;
	private String marka;
	/**
	 * @param model
	 * @param marka
	 */
	public Car(String model, String marka) {
		super();
		this.model = model;
		this.marka = marka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	
	
}
