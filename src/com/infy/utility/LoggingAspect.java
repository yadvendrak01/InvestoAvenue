package com.infy.utility;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.infy.dao.*Impl.*(..))", throwing = "exception")
	public void logExceptionDao(Exception exception) throws Exception {
		exception.printStackTrace();
		log(exception);
		throw new Exception("DAO.TECHNICAL_ERROR");
	}

	@AfterThrowing(pointcut = "execution(* com.infy.service.*Impl.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception) throws Exception {
		if (exception.getMessage() != null
				&& (exception.getMessage().contains("Service") || exception
						.getMessage().contains("Validator"))) {
			log(exception);
		}
		throw exception;

	}

	private void log(Exception exception) {

		Logger logger = LogConfig.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
	}

}
