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
 * Bus Resource Test
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BusResourceTest {

	private LocalValidatorFactoryBean localValidatorFactory;
	
	@Before
	public void setUp() {
		localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
	}
	
	private BusResource setBusResource() {
		BusResource busResource = new BusResource();
		busResource.setVehicleNo("WP000789");
		busResource.setCapacity("30");
		busResource.setStatus("ACTIVE");
		return busResource;
	}
	
	
	/**
     * Can not be null.
     * Expected: {common.not-null}
     */
	@Test
	public void vehicleNoNotNull() {
		BusResource busResource = setBusResource();
		busResource.setVehicleNo(null);
		assertEquals("{common.not-null}", TestUtils.getFieldErrorMessageKey(busResource, localValidatorFactory));
	}

	
	/**
     * Can not be null.
     * Expected: {common.not-null}
     */
	@Test
	public void capacityNotNull() {
		BusResource busResource = setBusResource();
		busResource.setCapacity(null);
		assertEquals("{common.not-null}", TestUtils.getFieldErrorMessageKey(busResource, localValidatorFactory));
	}
	
	
	/**
     * Must be a numeric value.
     * Expected: {common-numeric.pattern}
     */
	@Test
	public void capacityPattern() {
		BusResource busResource = setBusResource();
		busResource.setCapacity("ABCDEF");
		assertEquals("{common-numeric.pattern}", TestUtils.getFieldErrorMessageKey(busResource, localValidatorFactory));
	}
	
	
	/**
     * status is required.
     * Expected: {common.not-null}
     */
	@Test
	public void statusNotNull() {
		BusResource busResource = setBusResource();
		busResource.setStatus(null);
		assertEquals("{common.not-null}", TestUtils.getFieldErrorMessageKey(busResource, localValidatorFactory));
	}
	
	
	/**
     * Status should be ACTIVE or INACTIVE.
     * Expected: {common-status.pattern}
     */
	@Test
	public void statusPattern() {
		BusResource busResource = setBusResource();
		busResource.setStatus("ABCDEF");
		assertEquals("{common-status.pattern}", TestUtils.getFieldErrorMessageKey(busResource, localValidatorFactory));
	}
}
