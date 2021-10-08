package com.csse.publictransport.controler;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.csse.publictransport.model.TravelCard;
import com.csse.publictransport.service.TravelCardService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TravelCardControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private TravelCardService travelCardService;
	
	String id=null;
	String status=null;
	
	@Before
	public void init() {
		Collection<TravelCard> travelCards=travelCardService.findAll();
		if(travelCards.size()!= 0) {
			for (TravelCard travelCard : travelCards) {
				id = travelCard.getId();
            	status = travelCard.getStatus();
                break;
            }
		}
	}
	
	@Test
    public void getAllTravelCardTest() throws Exception {
        if(id != null) {
            mockMvc.perform(get("/travel-card/"+"/all")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
        }else {
            mockMvc.perform(get("/travel-card/"+"/all")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent());
        }
    }
	
	@Test
    public void getAllTravelCardByIdTest() throws Exception {
        if(id != null) {
            mockMvc.perform(get("/travel-card/"+id+"")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(jsonPath("id", is(id)));
        }else {
            mockMvc.perform(get("/travel-card/"+id+"")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent());
        }
    }
	
	
	@Test
    public void getAllTravelCardByStatusTest() throws Exception {
        if(status != null) {
            mockMvc.perform(get("/travel-card/"+"/status/"+status+"")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
        }else {
            mockMvc.perform(get("/travel-card/"+"/status/"+status+"")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent());
        }
    }

}
