package com.excilys.projet.java.cdb.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationBackComputer 
{
	private static Logger logger = LoggerFactory.getLogger(ValidationBackComputer.class);

    public static boolean dateFormatValidator(String date) {
        boolean dateIsValid = true;
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            dateIsValid = false;
            logger.error("invalid date format: " + date);
        } catch (NullPointerException e) {
            logger.info("the date is null");
        }
        return dateIsValid;
    }
}
