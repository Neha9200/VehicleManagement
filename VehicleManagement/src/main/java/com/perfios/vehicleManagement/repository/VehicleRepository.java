package com.perfios.vehicleManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.perfios.vehicleManagement.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	Optional<Vehicle> findById(long id);

	@Query("SELECT v FROM Vehicle v WHERE v.make = :make")
	public List<Vehicle> getUserByUsername(@Param("make") String make);

	@Query("SELECT v FROM Vehicle v ORDER BY v.year ")
	public List<Vehicle> orderList(@Param("year") Integer year);

	@Query("SELECT v FROM Vehicle v WHERE CONCAT(v.id, v.year, v.make, v.model, v.base_price, v.reg_fee, v.road_tax, v.insurance) LIKE %?1%")
	public List<Vehicle> search(String keyword);

//	@Query("SELECT v.base_price, v.insurance, v.reg_fee, v.road_tax, v.base_price + v.insurance + v.reg_fee + v.road_tax as onRoadPrice FROM Vehicle v")
//	public double onRoadPrice(Float on_road_price);

}
