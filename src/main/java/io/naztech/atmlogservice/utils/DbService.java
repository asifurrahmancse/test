package io.naztech.atmlogservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nazdaqTechnologies.jdbc.JdbcResult;
import com.nazdaqTechnologies.jdbc.JdbcService;
import com.nazdaqTechnologies.jdbc.JdbcStatementFactory;
import com.nazdaqTechnologies.jdbc.StoredProcedure.JdbcStoredProcedure;

import io.naztech.atmlogservice.constant.SpName;
import io.naztech.atmlogservice.model.AtmLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DbService extends FileService {
	
	@Autowired
	JdbcService jdbcService;
	
	public void insertIntoDb(AtmLog atmLog) {
		
		atmLog = normalizeDate(atmLog);
		
//		String sp = "ACT_t_cbs_npsb_link_insert";
		String sp = SpName.INS_cbs_npsb_link_insert.toString();

		Map<String, Object> args = (Map<String, Object>) atmLog.getValueMap();
		
		JdbcResult jdbcResult = new JdbcResult();
		JdbcStoredProcedure jdbcStoredProcedure = jdbcService.getJdbcStoredProcedure(sp);
		jdbcResult.setFilteredOutputParamMap(jdbcStoredProcedure.getSpOutputParamMap());
		jdbcResult.setProcessWarnings(false);
		
		try {
			jdbcResult = jdbcService.executeSP(sp, args, jdbcResult);
		} catch (Exception e) {
			log.error("error {}, \nMessage *** : {}", e, e.getLocalizedMessage());
		}
		
	}
	
	public AtmLog normalizeDate(AtmLog atmLog) {
		String postingDate = atmLog.getPostingDate();

		String[] dateValue = postingDate.split("/");
    	String formatedDate = dateValue[2]+ "-" + dateValue[1] + "-" + dateValue[0];
    	
    	atmLog.setPostingDate(formatedDate);

		return atmLog;
		
	}
	

}
