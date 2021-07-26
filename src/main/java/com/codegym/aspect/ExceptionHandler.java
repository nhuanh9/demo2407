package com.codegym.aspect;

import com.codegym.exception.F404Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ModelAndView show404Page() {
        return new ModelAndView("/404");
    }

}
