package util;

import java.util.List;

import model.Book;

public class BookUtil {

	public static void ViewBooks(List<Book> books) {
		System.out.println("View Books");
		System.out.println("----------");
		
		System.out.println("Id\tTitle\tPrice");
		
		for(Book book : books) {
			System.out.println(""+ book.getId() + "\t" + book.getTitle() + "\t" + book.getPrice());
		}
	}
}
