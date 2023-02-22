package br.com.glendon.math;

import br.com.glendon.converters.NumberConverter;
import br.com.glendon.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SimpleMath {

    public Double sum( Double numberOne,  Double numberTwo) {
        return numberOne + numberTwo;
    }
    
    public Double subtraction( Double numberOne,  Double numberTwo) {
        return numberOne - numberTwo;
    }
    
    public Double multiply( Double numberOne,  Double numberTwo) {
        return numberOne * numberTwo;
    }

    public Double division( Double numberOne,  Double numberTwo) {
        return numberOne / numberTwo;
    }
    
    public Double average( Double numberOne,  Double numberTwo) {
        return (numberOne + numberTwo)/2;
    }
    
    public Double squareroot( Double numberOne) {
        return Math.sqrt(numberOne);
    }
    
    
}
