package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IBranchRepository;
import com.portal.models.Branch;

@Service
public class BranchService {

	@Autowired
	IBranchRepository repo;
	
	public List<Branch> findAll(){
		return (List<Branch>) repo.findAll();
	}
	
	public Optional<Branch> findById(int id) {
		return repo.findById(id);
	}
	
	public void saveOrUpdate(Branch branch) {
		repo.save(branch);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
}
