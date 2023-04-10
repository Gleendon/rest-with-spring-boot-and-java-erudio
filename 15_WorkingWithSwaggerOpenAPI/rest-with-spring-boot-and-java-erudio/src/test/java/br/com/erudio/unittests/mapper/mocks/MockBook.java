package br.com.erudio.unittests.mapper.mocks;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.model.Book;

public class MockBook {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 

    public Book mockEntity() throws ParseException {
        return mockEntity(0);
    }
    
    public BookVO mockVO() throws ParseException {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() throws ParseException {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() throws ParseException {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) throws ParseException {
        Book book = new Book();
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(sdf.parse("02/04/2023"));
        book.setPrice(new BigDecimal(50));
        book.setTitle("Title teste" + number);
        book.setId(number.longValue());
        return book;
    }

    public BookVO mockVO(Integer number) throws ParseException {
        BookVO book = new BookVO();
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(sdf.parse("02/04/2023"));
        book.setPrice(new BigDecimal(50));
        book.setTitle("Title teste" + number);
        book.setKey(number.longValue());
        return book;
    }

}
