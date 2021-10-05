package com.csse.publictransport.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.csse.publictransport.service.CommonService;

/**
 * Common Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class CommonServiceImpl implements CommonService {

	@Override
	public String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		return format.format(date);
	}
	
}
