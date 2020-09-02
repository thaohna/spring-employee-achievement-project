package com.anhthao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhthao.models.Depart;
import com.anhthao.repositories.DepartRepository;


@Service
public class DepartServiceImpl implements DepartService {
	@Autowired
	DepartRepository departRepository;
	
	@Override
	public Depart save(Depart entity) {
		return departRepository.save(entity);
	}
	

	@Override
	public List<Depart> findByNameLikeOrderByName(String name) {
		return departRepository.findByNameLikeOrderByName("%" + name + "%");
	}


	@Override
	public List<Depart> saveAll(List<Depart> entities) {
		return (List<Depart>) departRepository.saveAll(entities);
	}

	@Override
	public Optional<Depart> findById(Integer id) {
		return departRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return departRepository.existsById(id);
	}

	@Override
	public List<Depart> findAll() {
		return (List<Depart>) departRepository.findAll();
	}

	@Override
	public List<Depart> findAllById(List<Integer> ids) {
		return (List<Depart>) departRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return departRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		departRepository.deleteById(id);
	}

	@Override
	public void delete(Depart entity) {
		departRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Depart> entities) {
		departRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		departRepository.deleteAll();
	}
	
	
}
