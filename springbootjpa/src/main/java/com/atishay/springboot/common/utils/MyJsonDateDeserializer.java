package com.atishay.springboot.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/***
 * This class called when object mapper converts string json to object for field
 * which is date.
 * 
 * @author vishvas
 *
 */
public class MyJsonDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
			throws IOException, JsonProcessingException {
		String date = jsonparser.getText() + "";
		Date dateResponse = null;
		try {

			System.out.println("date is  : " + date);

			SimpleDateFormat angularDateFormat = new SimpleDateFormat(VariableUtils.DATE_FORMAT_FROM_ANGULAR);
			Date angularDate = angularDateFormat.parse(date);

			dateResponse = VariableUtils.SIMPLE_DATE_FORMAT_OF_DATABASE
					.parse(VariableUtils.SIMPLE_DATE_FORMAT_OF_DATABASE.format(angularDate));

		} catch (Exception e) {
			System.err.println("first exception from MyJsonDateDeserializer ");
		}

		if (MethodUtils.isObjectisNullOrEmpty(dateResponse)) {

			// if date contains format like "EEE MMM dd yyyy hh:mm:ss aaa" then
			// below try..cache is executed. (Java date format)
			try {
				dateResponse = VariableUtils.SIMPLE_DATE_FORMAT_OF_DATABASE.parse(date);
			} catch (Exception e) {
				System.err.println("second exception from MyJsonDateDeserializer ");
			}
		}

		return dateResponse;
	}

}
