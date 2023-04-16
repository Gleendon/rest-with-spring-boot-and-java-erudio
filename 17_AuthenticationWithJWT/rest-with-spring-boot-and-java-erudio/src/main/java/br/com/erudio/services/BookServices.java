package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;

@Service
public class BookServices {
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	public List<BookVO> findAll(){
		logger.info("Find all Books");
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		
		books.stream().forEach( 
				b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel())
				);
		return books;		
	}
	
	public BookVO findById(Long id) {
		logger.info("Find one Book");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public BookVO create(BookVO bookVO) {
		if(bookVO == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one book!");

		var entity = DozerMapper.parseObject(bookVO, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());

		return vo;
	}
	
	
	public BookVO update(BookVO bookVO) {
		if(bookVO == null) throw new RequiredObjectIsNullException();
		logger.info("Updating one book!");
		
		var entity = repository.findById(bookVO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setAuthor(bookVO.getAuthor());
		entity.setLaunchDate(bookVO.getLaunchDate());
		entity.setPrice(bookVO.getPrice());
		entity.setTitle(bookVO.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());		
		return vo;
		
	}
	
	
	public void delete(Long id) {
		logger.info("Deleting one person!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	
}
