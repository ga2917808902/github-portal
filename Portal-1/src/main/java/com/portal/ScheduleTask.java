package com.portal;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.portal.models.Book;
import com.portal.services.BookService;

@Component
@EnableScheduling
public class ScheduleTask {

	@Autowired
	BookService bookService;

	// fixedDelay: Chay xong het roi moi chay tiep
	// fixedRate Cu dung thoi gian la chay, khong quan tam thang truoc chay xong hay chua
	/**
	 * Hàm dùng để tự cập nhật views của book mỗi 1h
	 * @throws InterruptedException
	 */
	@Scheduled(fixedDelay = 60* 60 * 1000)
	public void scheduleFixedDelayTask() throws InterruptedException {
		for (Map.Entry<Integer, Book> entry : BookService.mapBooks.entrySet()) {
			bookService.saveOrUpdate(entry.getValue());
		}
	}
	
	//Chay theo ngay
//	@Scheduled(cron = "5-10/1 * 12-14 * * MON-FRI")
//	public void scheduleTaskUsingCronExpression() throws InterruptedException {
//	  System.out.println("Task3 - " + new Date());
//	}
}
