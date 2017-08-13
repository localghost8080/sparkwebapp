package com.samrat.main;

import static spark.Spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;


public class SparkMain {
	static Logger logger = LoggerFactory.getLogger(SparkMain.class);
	public static void main(String[] args) {
		port(8080);

		Configuration config = new Configuration();
		config.setClassForTemplateLoading(SparkMain.class, "/");
		
		get("/",(req,resp)->{
			logger.debug("inside request");
			StringWriter writer = new StringWriter();		
			Map<String , String> template = new HashMap<>();
			try{
				Template helloTemplate = config.getTemplate("Hello.ftl");
				template.put("name", "Samrat");
				helloTemplate.process(template, writer);
			}catch(Exception e){
				halt(500);
			}
			return writer;
			
		});

	}
}
