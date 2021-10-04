package com.csse.publictransport.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.csse.publictransport.service.CommonService;

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
