package com.portal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IBranchRepository;
import com.portal.models.Branch;
import com.portal.models.Category;

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
	
	public List<Branch> findByCategory(Category category){
		return repo.findByCategory(category);
	}
	
	public int countBook(int id) {
		return repo.countBook(id);
	}
	
	/**
	 * Hàm dùng để count book trong mỗi ngành(branch) book, nếu count > 0 thì add vào list
	 * @param category id của loại
	 * @return danh sách thư mục(branch)
	 */
	public List<Object[]> contents(Category category) {
		List<Object[]> listContents = new ArrayList<>();
		List<Branch> list = this.findByCategory(category);
		for (Branch branch : list) {
			int countBook = this.countBook(branch.getId());
			if (countBook > 0) {
				Object[] arrays = {branch.getId(), branch.getName(), countBook };
				listContents.add(arrays);
			}
		}

		return listContents;
	}
	
}
