package com.perfios.vehicleManagement.service;

import java.util.List;

import com.perfios.vehicleManagement.exception.ResourceNotFoundException;
import com.perfios.vehicleManagement.model.Vehicle;

public interface VehicleService {

//	Vehicle saveVehicle(Vehicle vehicle);
	List<Vehicle> getAllVehicles();
	Vehicle getVehicleById(Integer id) throws ResourceNotFoundException;
	Vehicle updateVehicle(Vehicle vehicle, Integer id) throws ResourceNotFoundException;
	void deleteVehicle(Integer id) throws ResourceNotFoundException;
	List<Vehicle> showAll();
	Vehicle saveNewVehicle(Vehicle vehicle,Float on_road_price);
	List<Vehicle> search(String make);
	List<Vehicle> orderVehiclesDesc(Integer year);
	List<Vehicle> orderVehiclesAsc(Integer year);
	List<Vehicle> listAll(String keyword);
//	List<Vehicle> priceList(Vehicle vehicle);
	}
