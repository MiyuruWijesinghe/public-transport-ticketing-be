package com.csse.publictransport.service;

import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * Common Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface CommonService {

	/**
	 * Format date.
	 *
	 * @param date - the date
	 * @return the string
	 */
	public String formatDate(Date date);
	
}
