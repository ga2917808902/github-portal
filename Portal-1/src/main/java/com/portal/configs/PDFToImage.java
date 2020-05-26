package com.portal.configs;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portal.models.Book;
import com.portal.models.ImageBook;
import com.portal.services.ImageBookService;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

@Component
public class PDFToImage {

	@Autowired
	ImageBookService imageBookService;

	public void process(List<ImageBook> list, String nameBook, int idBook) {
		int countViewsImage = 0;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMain() == 2) {
					countViewsImage++;
				}
			}
			if (countViewsImage == 0) {
				insertImageBook(idBook, nameBook);
			}
		}
	}

	private void insertImageBook(int idBook, String name) {
		List<String> listImages = render("D:\\" + name + ".pdf");
		for (int i = 0; i < listImages.size(); i++) {
			imageBookService.saveOrUpdate(new ImageBook(listImages.get(i), 2, new Book(idBook)));
		}
	}

	public List<String> render(String name) {
		try {
			List<String> list = new ArrayList<>();
			String destinationDir = "E:\\Eclipse\\github-portal-1\\Portal-1\\src\\main\\webapp\\resources\\image\\pdf\\";

			File sourceFile = new File(name);
			File destinationFile = new File(destinationDir);

			String fileName = sourceFile.getName().replace(".pdf", "");
			if (sourceFile.exists()) {
				if (!destinationFile.exists()) {
					destinationFile.mkdir();
					System.out.println("Folder created in: " + destinationFile.getCanonicalPath());
				}

				RandomAccessFile raf = new RandomAccessFile(sourceFile, "r");
				FileChannel channel = raf.getChannel();
				ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
				PDFFile pdf = new PDFFile(buf);
				int pageNumber = 1;
				for (int i = 3; i < 15; i++) {
					PDFPage page = pdf.getPage(i);

					// image dimensions
					int width = 1200;
					int height = 1400;

					// create the image
					Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(),
							(int) page.getBBox().getHeight());
					BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

					// width & height, // clip rect, // null for the ImageObserver, // fill
					// background with white, // block until drawing is done
					Image image = page.getImage(width, height, rect, null, true, true);
					Graphics2D bufImageGraphics = bufferedImage.createGraphics();
					bufImageGraphics.drawImage(image, 0, 0, null);

					File imageFile = new File(destinationDir + fileName + "_" + pageNumber + ".png");// change file
																										// format here.
																										// Ex: .png,
																										// .jpg, .jpeg,
																										// .gif, .bmp

					ImageIO.write(bufferedImage, "png", imageFile);
					pageNumber++;

					System.out.println(
							imageFile.getName() + " File created in Folder: " + destinationFile.getCanonicalPath());
					list.add(imageFile.getName());
				}
				return list;
			} else {
				System.err.println(sourceFile.getName() + " File not exists");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
