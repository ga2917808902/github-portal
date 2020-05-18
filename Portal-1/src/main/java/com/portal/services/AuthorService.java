package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IAuthorRepository;
import com.portal.models.Author;

@Service
public class AuthorService {

	@Autowired
	IAuthorRepository repo;
	
	public Page<Author> findAll(Pageable pageable){
		return repo.findAll(pageable);
	}
	
	public List<Author> findAll(){
		return (List<Author>) repo.findAll();
	}
	
	public Optional<Author> findById(int id) {
		return repo.findById(id); 
	}
	
	public void saveOrUpdate(Author author) {
		repo.save(author);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	/**
	 * Hàm dùng để kiểm tra tác giả đã có trong book chưa, nếu có rồi thì sẽ remove khỏi list
	 * @param tempList danh sách author
	 * @return danh sách tác giả sau khi đã kiểm tra xong
	 */
	
	public List<Author> listAuthors(List<Author> tempList){
		List<Author> list = this.findAll();
		list.sort((Author s1, Author s2)->s1.getName().compareTo(s2.getName())); 
		for (int i = 0; i < tempList.size(); i++) {
			int index = binarySearch(list, tempList.get(i).getName());
			if (index != -1) {
				list.remove(index);
			}
		}
		
		return list;
	}
	
	/**
	 * Binarysearch with list string
	 * @param list, danh sách chứa tất cả tác giả
	 * @param x, tên của 1 tác giả đã có trong cuốn sách
	 * @return mid(=0) nếu có
	 */
	int binarySearch(List<Author> list, String x) {
        int low = 0;
        int high = list.size() - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (list.get(mid).getName().compareTo(x) < 0) {
                low = mid + 1;
            } else if (list.get(mid).getName().compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
