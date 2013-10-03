package com.apodoba.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apodoba.dao.ShopDao;
import com.apodoba.domain.CategoryEntity;
import com.apodoba.dto.CategoryDTO;
import com.apodoba.dto.CategoryDTOBuilder;

@Controller
@Transactional(readOnly = true)
@RequestMapping("/shop")
public class ShopSpringMvc {

    private static CategoryDTOBuilder categoryDTOBuilder = new CategoryDTOBuilder();

    @Autowired
    ShopDao shopDao;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public @ResponseBody String hello() {
        return "Hello from Spring MVC";
    }

    class A {
        public String a;
        public int b;
    }


    // curl -v -H "Accept: application/json" http://localhost:8080/shop-springmvc/shop/json
    @RequestMapping(value = "json", method = RequestMethod.GET)
    public @ResponseBody A soneJson() {
        A a = new A();
        a.a="aaa";
        a.b = 5;
        return a;
    }


    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody CategoryDTO getCategoryById(@PathVariable("categoryId") Long categoryId) {
        CategoryEntity categoryEntity = shopDao.getCategoryById(categoryId);
        CategoryDTO dto = categoryDTOBuilder.buildDTO(categoryEntity);
        return dto;
    }
}
