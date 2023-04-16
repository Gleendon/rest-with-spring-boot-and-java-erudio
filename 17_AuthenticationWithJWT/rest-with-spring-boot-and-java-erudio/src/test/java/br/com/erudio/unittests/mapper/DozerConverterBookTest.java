package br.com.erudio.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.unittests.mapper.mocks.MockBook;

public class DozerConverterBookTest {
    
    MockBook inputObject;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeEach
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() throws ParseException {
        BookVO output = DozerMapper.parseObject(inputObject.mockEntity(), BookVO.class);
        
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Author Test0", output.getAuthor());
        assertEquals(sdf.parse("02/04/2023"), output.getLaunchDate());
        assertEquals(new BigDecimal(50), output.getPrice());
        assertEquals("Title teste0", output.getTitle());
    }

    @Test
    public void parseEntityListToVOListTest() throws ParseException {
        List<BookVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), BookVO.class);
        BookVO outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Author Test0", outputZero.getAuthor());
        assertEquals(sdf.parse("02/04/2023"), outputZero.getLaunchDate());
        assertEquals(new BigDecimal(50), outputZero.getPrice());
        assertEquals("Title teste0", outputZero.getTitle());
        
        BookVO outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Author Test7", outputSeven.getAuthor());
        assertEquals(sdf.parse("02/04/2023"), outputSeven.getLaunchDate());
        assertEquals(new BigDecimal(50), outputSeven.getPrice());
        assertEquals("Title teste7", outputSeven.getTitle());
        
        BookVO outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("Author Test12", outputTwelve.getAuthor());
        assertEquals(sdf.parse("02/04/2023"), outputTwelve.getLaunchDate());
        assertEquals(new BigDecimal(50), outputTwelve.getPrice());
        assertEquals("Title teste12", outputTwelve.getTitle());
    }

    @Test
    public void parseVOToEntityTest() throws ParseException {
        Book output = DozerMapper.parseObject(inputObject.mockVO(), Book.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Author Test0", output.getAuthor());
        assertEquals(sdf.parse("02/04/2023"), output.getLaunchDate());
        assertEquals(new BigDecimal(50), output.getPrice());
        assertEquals("Title teste0", output.getTitle());
    }

    @Test
    public void parserVOListToEntityListTest() throws ParseException {
        List<Book> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Book.class);
        Book outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Author Test0", outputZero.getAuthor());
        assertEquals(sdf.parse("02/04/2023"), outputZero.getLaunchDate());
        assertEquals(new BigDecimal(50), outputZero.getPrice());
        assertEquals("Title teste0", outputZero.getTitle());
        
        Book outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Author Test7", outputSeven.getAuthor());
        assertEquals(sdf.parse("02/04/2023"), outputSeven.getLaunchDate());
        assertEquals(new BigDecimal(50), outputSeven.getPrice());
        assertEquals("Title teste7", outputSeven.getTitle());
        
        Book outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Author Test12", outputTwelve.getAuthor());
        assertEquals(sdf.parse("02/04/2023"), outputTwelve.getLaunchDate());
        assertEquals(new BigDecimal(50), outputTwelve.getPrice());
        assertEquals("Title teste12", outputTwelve.getTitle());
    }
}
