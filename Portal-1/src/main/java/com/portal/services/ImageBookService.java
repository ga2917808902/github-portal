package com.portal.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.portal.Repositories.IImageBookRepository;
import com.portal.models.Book;
import com.portal.models.ImageBook;

@Service
public class ImageBookService {

	@Autowired
	IImageBookRepository repo;
	
	public List<ImageBook> findByBook(Book book) {
		List<ImageBook> list = repo.findByBook(book);
		return list;
	}
	
	public void saveOrUpdate(ImageBook imageBook) {
		repo.save(imageBook);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public void saveImage(MultipartFile imageFile) throws IOException {
		String folder = "E:\\Eclipse\\github-portal-1\\Portal-1\\src\\main\\webapp\\resources\\image\\product\\";
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(folder + imageFile.getOriginalFilename());
		Files.write(path, bytes);
		
	}
}
