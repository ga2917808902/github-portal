package com.portal.configs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.portal.models.Book;
import com.portal.models.ImageBook;
import com.portal.services.ImageBookService;
import com.pqscan.pdftoimage.PDFDocument;

@Component
public class PDFToImage {

	@Autowired
	ImageBookService imageBookService;

	/**
	 * Hàm dùng để kiểm tra điều kiện xử lý
	 * @param list
	 * @param source
	 * @param idBook
	 */
	public void process(List<ImageBook> list, String source, int idBook) {
		int countViewsImage = 0;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMain() == 2) {
					countViewsImage++;
				}
			}
			if (countViewsImage == 0) {
				insertImageBook(idBook, source);
			}
		} else if (list.size() == 0) {
			insertImageBook(idBook, source);
		}
	}
	
	/**
	 * Hàm dùng để thêm vào db
	 * @param idBook
	 * @param name source name
	 */

	private void insertImageBook(int idBook, String name) {
		List<String> listImages = render(
				"E:\\Eclipse\\github-portal-1\\Portal-1\\src\\main\\webapp\\resources\\pdf\\" + name);
		for (int i = 0; i < listImages.size(); i++) {
			imageBookService.saveOrUpdate(new ImageBook(listImages.get(i), 2, new Book(idBook)));
		}
	}
	
	/**
	 * Hàm dùng để save pdf to image
	 * @param name tên source
	 * @return về list tên của file pdf
	 */

	public List<String> render(String name) {
		try {
			List<String> list = new ArrayList<>();
			String destinationDir = "E:\\Eclipse\\github-portal-1\\Portal-1\\src\\main\\webapp\\resources\\image\\pdf\\";

			// first, load PDF document to memory
			PDFDocument doc = new PDFDocument();
			File file = new File(name);
			doc.loadPDF(name);
			int pageCount = doc.getPageCount();

			// second, convert each PDF page and save to jpg image
			for (int i = 0; i < pageCount; i++) {
				BufferedImage image = doc.toImage(i);
				ImageIO.write(image, "jpg",
						new File(destinationDir + file.getName().replace(".pdf", "") + "_" + i + ".jpg"));
				list.add(file.getName().replace(".pdf", "")+ "_" + i + ".jpg");
				if (i == 15) {
					break;
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
