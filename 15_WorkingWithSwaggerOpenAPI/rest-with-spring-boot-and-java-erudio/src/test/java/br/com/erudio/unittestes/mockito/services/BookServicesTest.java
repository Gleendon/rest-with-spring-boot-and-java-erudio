package br.com.erudio.unittestes.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.services.BookServices;
import br.com.erudio.unittests.mapper.mocks.MockBook;

//Definindo o ciclo de vida do teste, será realizado por classe
@TestInstance(Lifecycle.PER_CLASS)

//Extendendo da extensão 
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	// Instanciando a classe teste que contei os mock a serem testados
	MockBook input;

	// Injeção controlada pelo mockito, para acesar as informações do mock e não da
	// base
	@InjectMocks
	private BookServices service;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Mock
	BookRepository repository;

	@BeforeEach
	void setMocks() throws Exception {
		input = new MockBook();

		// injetando os mocks no service
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		// O repository irá retornar as informações do mock quando for acionado
		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		// recendo o mock e verficando se o comportamento é o esperado
		var result = service.findById(1L);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(sdf.parse("02/04/2023"), result.getLaunchDate());
		assertEquals(new BigDecimal(50), result.getPrice());
		assertEquals("Title teste1", result.getTitle());
	}

	@Test
	void testCreate() throws ParseException {
		// Cria a entidade sem o id
		Book entity = input.mockEntity(1);

		// Inserindo ID na entidade criada
		Book persisted = entity;
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		// O repository irá retornar as informações do mock quando for acionado
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(sdf.parse("02/04/2023"), result.getLaunchDate());
		assertEquals(new BigDecimal(50), result.getPrice());
		assertEquals("Title teste1", result.getTitle());
	}

	@Test
	void testCreateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() throws ParseException {
		// Cria a entidade sem o id
		Book entity = input.mockEntity(1);

		// Inserindo ID na entidade criada
		Book persisted = entity;
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		// O repository irá retornar as informações do mock quando for acionado
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(sdf.parse("02/04/2023"), result.getLaunchDate());
		assertEquals(new BigDecimal(50), result.getPrice());
		assertEquals("Title teste1", result.getTitle());
	}

	@Test
	void testUpdateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() throws ParseException {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

	@Test
	void testFindAll() throws ParseException {
		List<Book> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var people = service.findAll();

		assertNotNull(people);
		assertEquals(14, people.size());

		var personOne = people.get(1);

		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());

		assertTrue(personOne.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", personOne.getAuthor());
		assertEquals(sdf.parse("02/04/2023"), personOne.getLaunchDate());
		assertEquals(new BigDecimal(50), personOne.getPrice());
		assertEquals("Title teste1", personOne.getTitle());

		var personFour = people.get(4);

		assertNotNull(personFour);
		assertNotNull(personFour.getKey());
		assertNotNull(personFour.getLinks());

		assertTrue(personFour.toString().contains("links: [</api/books/v1/4>;rel=\"self\"]"));
		assertEquals("Author Test4", personFour.getAuthor());
		assertEquals(sdf.parse("02/04/2023"), personFour.getLaunchDate());
		assertEquals(new BigDecimal(50), personFour.getPrice());
		assertEquals("Title teste4", personFour.getTitle());

		var personSeven = people.get(7);

		assertNotNull(personSeven);
		assertNotNull(personSeven.getKey());
		assertNotNull(personSeven.getLinks());

		assertTrue(personSeven.toString().contains("links: [</api/books/v1/7>;rel=\"self\"]"));
		assertEquals("Author Test7", personSeven.getAuthor());
		assertEquals(sdf.parse("02/04/2023"), personSeven.getLaunchDate());
		assertEquals(new BigDecimal(50), personSeven.getPrice());
		assertEquals("Title teste7", personSeven.getTitle());
	}

}
