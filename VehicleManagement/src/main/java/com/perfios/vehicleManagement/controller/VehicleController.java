package com.perfios.vehicleManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.perfios.vehicleManagement.exception.ResourceNotFoundException;
import com.perfios.vehicleManagement.model.Vehicle;
import com.perfios.vehicleManagement.repository.VehicleRepository;
import com.perfios.vehicleManagement.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "This is spring boot rest api and hibernate vehicle mngnt application")
public class VehicleController {
	private VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}
	@ApiOperation(value = "it will add CAR DATA ")
	@GetMapping("/search")
	public String viewSearchedPage(Model model, @Param("keyword") String keyword) {
		List<Vehicle> listVehicles = vehicleService.listAll(keyword);
		model.addAttribute("vehicles", listVehicles);
		model.addAttribute("keyword", keyword);
		//System.out.println(listVehicles);
		return "search";
	}

//	@GetMapping("/search/{make}")
//	public ModelAndView search(HttpServletRequest request, ModelAndView model, @PathVariable("make") String make) {
//
//		List<Vehicle> listVehicle = vehicleService.search(make);
//		System.out.println(make);
//		model.addObject("keyword", listVehicle);
//		model.setViewName("search");
//		return model;
//	}
	
//    @RequestMapping("/search/{make}")
//    public String viewHomePage(Model model, @Param("make") String make) {
//        
//        // Offloads work from a MySQL DB hosted on AWS into in-memory persistence 
//        List<Vehicle> listVehicles = vehicleService.listAll(make);
//        model.addAttribute("listVehicles", listVehicles);
//        model.addAttribute("keyword", make); // To display the searched word upon refresh
//
//        return "search";
//    }

	@GetMapping("/vehicle")
	public String homePage(Model m) {
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		m.addAttribute("vehicles", vehicles);
	//	System.out.println(vehicles);
		return "vehicle";
	}

	@GetMapping("/admin")
	public String adminhomePage(Model m) {
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		m.addAttribute("vehicles", vehicles);
	//	System.out.println(vehicles);
		return "admin";
	}

	
	@PostMapping("/savevehicle")
	public String saveVehicle(Vehicle vehicle,Float on_road_price) {
		vehicleService.saveNewVehicle(vehicle,on_road_price);
		return "redirect:/admin";
	}

	@GetMapping("/addVehicle")
	public String newVehiclePage(Model model, HttpSession session) {
		Vehicle vehicle = new Vehicle();
		model.addAttribute(vehicle);
		session.setAttribute("msg", "Vehicle Data Added Successfully...");
		return "new_vehicle";
	}

	@GetMapping("/updateVehicle/{id}")
	public String updateVehicle(@PathVariable int id, Model model) throws ResourceNotFoundException {
		Vehicle v = vehicleService.getVehicleById(id);
		model.addAttribute("vehicle", v);
		return "edit";
	}

	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Vehicle v, HttpSession session,Float on_road_price) {
		vehicleService.saveNewVehicle(v,on_road_price);
		session.setAttribute("msg", "Vehicle Data Updated Sucessfully..");
		return "redirect:/admin";
	}

	@GetMapping("/deleteVehicle/{id}")
	public String deleteEMp(@PathVariable int id, HttpSession session) throws ResourceNotFoundException {

		vehicleService.deleteVehicle(id);
		session.setAttribute("msg", "Vehicle Data Delete Sucessfully..");
		return "redirect:/admin";
	}

	@GetMapping("/orderYearDesc")
	public String orderByYearDesc(Integer year, Model model) {
		List<Vehicle> vehicles = vehicleService.orderVehiclesDesc(year);
		// vehicleService.orderVehicles(year);
		model.addAttribute("vehicles", vehicles);
		System.out.println(vehicles);
		return "vehicle";
	}
	
	@GetMapping("/orderYearAsc")
	public String orderByYearAsc(Integer year, Model model) {
		List<Vehicle> vehicles = vehicleService.orderVehiclesAsc(year);
		// vehicleService.orderVehicles(year);
		model.addAttribute("vehicles", vehicles);
		System.out.println(vehicles);
		return "vehicle";
	}
}
