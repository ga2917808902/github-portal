package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.ILanguageRepository;
import com.portal.models.Language;

@Service
public class LanguageService {

	@Autowired
	ILanguageRepository repo;
	
	public List<Language> findAll(){
		return (List<Language>) repo.findAll();
	}
	
	public Optional<Language> findById(int id) {
		return repo.findById(id);
	}
	
	public void saveOrUpdate(Language lang) {
		repo.save(lang);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
}
