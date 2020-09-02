package com.anhthao.services;

import java.util.List;
import java.util.Optional;

import com.anhthao.models.Depart;
import com.anhthao.models.Staff;
import com.anhthao.repositories.StaffRepository;

public interface StaffService {

	void deleteAll();

	void deleteAll(List<Staff> entities);

	void delete(Staff entity);

	void deleteById(Long id);

	long count();

	List<Staff> findAllById(Iterable<Long> ids);

	List<Staff> findAll();

	boolean existsById(Long id);

	Optional<Staff> findById(Long id);

	List<Staff> saveAll(List<Staff> entities);

	<S extends Staff> S save(S entity);

	List<Depart> findAllDeparts();
	
	
	

}
