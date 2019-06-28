package com.springboot.hktss;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.springboot.hktss.entity.Employee;
import com.springboot.hktss.service.EmployeeSerice;



@SpringBootApplication
@ComponentScan("com.springboot.hktss")
@EnableJpaRepositories(basePackages = "com.springboot.hktss")
@EntityScan("com.springboot.hktss") 
public class ExcelFileDatainDatabaseApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);

	@Autowired	EmployeeSerice service;

	public static void main(String[] args) {
		SpringApplication.run(ExcelFileDatainDatabaseApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		log.info("Getting All Employee data insert");
		
	       //   service.addEmployee(new Employee("srinu", 23));

	service.getAllEmployees().forEach(emp -> log.info(emp.toString()));

		 try
	        {
	            InputStream file = ExcelFileDatainDatabaseApplication.class.getClassLoader().getResourceAsStream("Book.xlsx");
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext())
	            {
	            	Employee emp = new Employee();
	                Row row = rowIterator.next();
	                String id = row.getCell(0).getNumericCellValue()+"";
	                String name = row.getCell(1).getStringCellValue();
	                String age = row.getCell(2).getNumericCellValue()+"";
	                emp.setId((new Double(id)).longValue());
	                emp.setAge(new Double(age).intValue());
	                emp.setName(name);
	                System.out.println("");
	                service.addEmployee(emp);
	            }
	            file.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}

}
