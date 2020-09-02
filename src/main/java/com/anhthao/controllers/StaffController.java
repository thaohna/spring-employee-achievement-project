package com.anhthao.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anhthao.dtos.StaffDto;
import com.anhthao.models.Depart;
import com.anhthao.models.Staff;
import com.anhthao.services.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("staffs", staffService.findAll());
		
		return "staffs/list";
	}
	
	@GetMapping("/add")
	public String add(ModelMap model) {
		StaffDto staff = new StaffDto();
		
		model.addAttribute("staffDto", staff);
		
		return "staffs/addOrEdit";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model,@Validated StaffDto staffDto,
			BindingResult result) {
		
		if(result.hasErrors()) {
			model.addAttribute("message", "Please input all required fields!");
			model.addAttribute("staffDto", staffDto);
			
			return "staffs/addOrEdit";
		}
		
		if(staffDto.getId() != null) {
			model.addAttribute("message", "Updated!");
		} else {
			model.addAttribute("message", "Inserted!");
		}
		
		Path path = Paths.get("images/");
		
		try(InputStream inputStream = staffDto.getImage().getInputStream()) {
			Files.copy(inputStream, path.resolve(staffDto.getImage().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			String filename = staffDto.getImage().getOriginalFilename();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Error: " + e.getMessage());
		}
		
		
		Staff staff = new Staff();
		staff.setBirthday(staffDto.getBirthday());
		staff.setName(staffDto.getName());
		staff.setPhoto(staffDto.getImage().getOriginalFilename());
		
		Depart depart = new Depart();
		depart.setId(staffDto.getDepartId());
		staff.setDepart(depart);
		
		staffService.save(staff);
		
		model.addAttribute("staffDto", staffDto);
		
		return "staffs/addOrEdit";
	}
	
	@ModelAttribute(name = "departs")
	public List<Depart> getDepart(){
		return staffService.findAllDeparts();
	}
}
