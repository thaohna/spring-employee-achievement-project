package com.anhthao.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anhthao.models.Depart;
import com.anhthao.services.DepartService;
import com.anhthao.services.StaffService;

@Controller
@RequestMapping("/departs")
public class DepartController {
	
	@Autowired
	private DepartService departService;
	
	@GetMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("depart", new Depart());
		
		return "departs/addOrEdit";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, Depart depart) {
		
		String message = "New depart inserted!";
		
		if(depart.getId() != null) {
			message = "The depart updated!";
		}
		
		departService.save(depart);
		
		model.addAttribute(depart);
		model.addAttribute("message", message);
		
		return "departs/addOrEdit";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(ModelMap modelMap, @PathVariable(name = "id") Integer id) {
		
		Optional<Depart> optDepart = departService.findById(id);
		
		if (optDepart.isPresent()) {
			modelMap.addAttribute("depart", optDepart.get());
		} else {
			return list(modelMap);
		}
		
		return "departs/addOrEdit";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(ModelMap modelMap, @PathVariable(name = "id") Integer id) {
		
		departService.deleteById(id);
		
			list(modelMap);
		
		
		
		return "departs/list";
	}
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap) {
		List<Depart> departs = departService.findAll();
		
		modelMap.addAttribute("departs", departs);
		
		return "departs/list";
	}
	
	@RequestMapping("/find")
	public String find(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		
		List<Depart> list = departService.findByNameLikeOrderByName(name);
		model.addAttribute("departs", list);
		
		return "departs/find";
	}
	

}
