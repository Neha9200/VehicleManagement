package com.perfios.vehicleManagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.perfios.vehicleManagement.exception.ResourceNotFoundException;
import com.perfios.vehicleManagement.model.Vehicle;
import com.perfios.vehicleManagement.repository.VehicleRepository;
import com.perfios.vehicleManagement.service.VehicleService;

@Service
@Repository
public class VehicleServiceImpl implements VehicleService {

	private VehicleRepository repo;

	public VehicleServiceImpl(VehicleRepository repo) {
		super();
		this.repo = repo;
	}

//	@Override
//	public Vehicle saveVehicle(Vehicle v) {
//		List<Vehicle> obj = repo.onRoadPrice(v);
//		for (Vehicle vehicle : obj) {
//			vehicle.setOnRoad_price(vehicle.getOnRoad_price());
//		}
//		System.out.println("on :" + v.getOnRoad_price());
//		return repo.save(v);
//	}

	public Vehicle get(int id) {
		return repo.findById(id).get();
	}

	public void delete(int id) {
		repo.deleteById(id);
	}

	public List<Vehicle> getAllVehicles() {
		return repo.findAll();
	}

	@Override
	public Vehicle getVehicleById(Integer id) throws ResourceNotFoundException {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle, Integer id) throws ResourceNotFoundException {
		Vehicle existingVehicle = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException());
		existingVehicle.setMake(vehicle.getMake());
		existingVehicle.setModel(vehicle.getModel());
		existingVehicle.setYear(vehicle.getYear());
		existingVehicle.setBase_price(vehicle.getBase_price());
		existingVehicle.setInsurance(vehicle.getInsurance());
		existingVehicle.setReg_fee(vehicle.getReg_fee());
		existingVehicle.setRoad_tax(vehicle.getRoad_tax());
//		existingVehicle.setOnRoad_price(vehicle.getBase_price() + vehicle.getInsurance() + vehicle.getReg_fee() + vehicle.getRoad_tax());

		repo.save(existingVehicle);
		return existingVehicle;
	}

	@Override
	public void deleteVehicle(Integer id) throws ResourceNotFoundException {
		Vehicle existingVehicle = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException());
		repo.deleteById((int) id);
	}

	@Override
	public List<Vehicle> showAll() {
		// TODO Auto-generated method stub
		List<Vehicle> showList = new ArrayList<>();
		for (Vehicle v : repo.findAll()) {
			showList.add(v);
		}
		return showList;
	}

	@Override
	public Vehicle saveNewVehicle(Vehicle vehicle,Float on_road_price) {
		
		vehicle.setOnRoad_price(vehicle.getBase_price() + vehicle.getInsurance() + vehicle.getReg_fee() + vehicle.getRoad_tax());
		
		return repo.save(vehicle);
	}


	@Override
	public List<Vehicle> search(String make) {
		// TODO Auto-generated method stub
		if (make != null) {
			return repo.getUserByUsername(make);
		}
		return repo.findAll();
	}

	@Override
	public List<Vehicle> orderVehiclesDesc(Integer year) {
		if (year != null) {
			return repo.orderList(year);
		}
		return repo.findAll(Sort.by(Sort.Direction.DESC, "year"));

	}

	@Override
	public List<Vehicle> listAll(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}

	@Override
	public List<Vehicle> orderVehiclesAsc(Integer year) {
		// TODO Auto-generated method stub
		if (year != null) {
			return repo.orderList(year);
		}
		return repo.findAll(Sort.by(Sort.Direction.ASC, "year"));
	}

//	@Override
//	public List<Vehicle> priceList(Vehicle vehicle) {
//		return repo.onRoadPrice(vehicle);
//	}

}
