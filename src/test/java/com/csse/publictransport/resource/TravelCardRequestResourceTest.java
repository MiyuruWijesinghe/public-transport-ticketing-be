package com.csse.publictransport.resource;


import static org.junit.Assert.assertEquals;
import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.csse.publictransport.utill.TestUtils;

/**
 * Travel Card Request Resource Test
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MenukaJ   IT19004914     Created
 *    
 ********************************************************************************************************
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TravelCardRequestResourceTest {

	private LocalValidatorFactoryBean localValidatorFactory;
	
	@Before
	public void setUp() {
		localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
	}
	
	private TravelCardRequestResource setTravelCardRequestResource() {
		TravelCardRequestResource travelCardRequestResource = new TravelCardRequestResource();
		travelCardRequestResource.setUserId("12");
		travelCardRequestResource.setQrCode("fdsf64sdf65sdsfd6f64");
		travelCardRequestResource.setCreditBalance("3000.00");
		travelCardRequestResource.setStatus("ACTIVE");
		return travelCardRequestResource;
	}
	
	
	/**
     * Must be a numeric value.
     * Expected: {common-numeric.pattern}
     */
	@Test
	public void userIdPattern() {
		TravelCardRequestResource travelCardRequestResource = setTravelCardRequestResource();
		travelCardRequestResource.setUserId("ABCDEF");
		assertEquals("{common-numeric.pattern}", TestUtils.getFieldErrorMessageKey(travelCardRequestResource, localValidatorFactory));
	}
	
	
	/**
     * Can not be null.
     * Expected: {common.not-null}
     */
	@Test
	public void creditBalanceNotNull() {
		TravelCardRequestResource travelCardRequestResource = setTravelCardRequestResource();
		travelCardRequestResource.setCreditBalance(null);
		assertEquals("{common.not-null}", TestUtils.getFieldErrorMessageKey(travelCardRequestResource, localValidatorFactory));
	}
	
	
	/**
     * Must be a numeric value.
     * Expected: {common-amount.pattern}
     */
	@Test
	public void creditBalancePattern() {
		TravelCardRequestResource travelCardRequestResource = setTravelCardRequestResource();
		travelCardRequestResource.setCreditBalance("ABCDEF");
		assertEquals("{common-amount.pattern}", TestUtils.getFieldErrorMessageKey(travelCardRequestResource, localValidatorFactory));
	}
	
	
	/**
     * status is required.
     * Expected: {common.not-null}
     */
	@Test
	public void statusNotNull() {
		TravelCardRequestResource travelCardRequestResource = setTravelCardRequestResource();
		travelCardRequestResource.setStatus(null);
		assertEquals("{common.not-null}", TestUtils.getFieldErrorMessageKey(travelCardRequestResource, localValidatorFactory));
	}
	
	
	/**
     * Status should be ACTIVE or INACTIVE.
     * Expected: {common-status.pattern}
     */
	@Test
	public void statusPattern() {
		TravelCardRequestResource travelCardRequestResource = setTravelCardRequestResource();
		travelCardRequestResource.setStatus("ABCDEF");
		assertEquals("{common-status.pattern}", TestUtils.getFieldErrorMessageKey(travelCardRequestResource, localValidatorFactory));
	}
}
