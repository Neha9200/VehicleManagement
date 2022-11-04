package com.perfios.vehicleManagement.model;

import javax.persistence.*;
//import javax.validation.constraints.*;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Integer year;

	private String make;

	private String model;

	private Float base_price;
	
	private Float reg_fee;
	
	private Float road_tax;
	
	private Float insurance;
	
	private Float onRoad_price;
		
	public Vehicle() {
	
	}

	public Vehicle(Integer year, String make, String model, Float base_price, Float reg_fee, Float road_tax,
			Float insurance, Float onRoad_price) {
		super();
		this.year = year;
		this.make = make;
		this.model = model;
		this.base_price = base_price;
		this.reg_fee = reg_fee;
		this.road_tax = road_tax;
		this.insurance = insurance;
		this.onRoad_price = base_price+reg_fee+insurance+road_tax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Float getBase_price() {
		return base_price;
	}

	public void setBase_price(Float base_price) {
		this.base_price = base_price;
	}

	public Float getReg_fee() {
		return reg_fee;
	}

	public void setReg_fee(Float reg_fee) {
		this.reg_fee = reg_fee;
	}

	public Float getRoad_tax() {
		return road_tax;
	}

	public void setRoad_tax(Float road_tax) {
		this.road_tax = road_tax;
	}

	public Float getInsurance() {
		return insurance;
	}

	public void setInsurance(Float insurance) {
		this.insurance = insurance;
	}

	public Float getOnRoad_price() {
		return onRoad_price;
	}

	public void setOnRoad_price(Float onRoad_price) {
		this.onRoad_price = onRoad_price;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", year=" + year + ", make=" + make + ", model=" + model + ", base_price="
				+ base_price + ", reg_fee=" + reg_fee + ", road_tax=" + road_tax + ", insurance=" + insurance
				+ ", onRoad_price=" + onRoad_price + "]";
	}

	
}
