package com.apodoba.jersey;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.apodoba.dao.ShopDao;

import com.apodoba.domain.CategoryEntity;
import com.apodoba.dto.CategoryDTO;
import com.apodoba.dto.CategoryDTOBuilder;

@Component
@Path("/shop")
@Transactional
public class ShopJersey {

    private static CategoryDTOBuilder categoryDTOBuilder = new CategoryDTOBuilder();

    @Autowired
    ShopDao shopDao;

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public Response helloRest() {
        return Response.ok("REST").build();
     }

    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("categoryId") Long categoryId) {
        CategoryEntity categoryEntity = shopDao.getCategoryById(categoryId);
        CategoryDTO dto = categoryDTOBuilder.buildDTO(categoryEntity);
        return Response.status(200).entity(dto).build();
    }
}
