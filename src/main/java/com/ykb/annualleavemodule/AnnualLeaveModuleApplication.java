package com.ykb.annualleavemodule;
import com.ykb.annualleavemodule.model.EmployeeDetailsModel;
import com.ykb.annualleavemodule.model.EmployeeGroupsModel;
import com.ykb.annualleavemodule.model.EmployeesModel;
import com.ykb.annualleavemodule.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.ykb.annualleavemodule")
public class AnnualLeaveModuleApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository _repo;

	public static void main(String[] args) {
		SpringApplication.run(AnnualLeaveModuleApplication.class, args);
	}

	@Bean
	public Docket leaveApiSwaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Override
	public void run(String... args) throws Exception {
		//initial record insertion on startup

		EmployeesModel emp1 = new EmployeesModel();
		EmployeeDetailsModel eDet1 = new EmployeeDetailsModel();
		EmployeeGroupsModel groupEmplyee = new EmployeeGroupsModel();

		EmployeesModel emp2 = new EmployeesModel();
		EmployeeDetailsModel eDet2 = new EmployeeDetailsModel();
		EmployeeGroupsModel groupEmplyee2 = new EmployeeGroupsModel();

		EmployeesModel emp3 = new EmployeesModel();
		EmployeeDetailsModel eDet3 = new EmployeeDetailsModel();
		EmployeeGroupsModel groupEmplyee3 = new EmployeeGroupsModel();

		emp1.setE_Id(123L);
		emp1.setName("Cem");
		emp1.setSurname("Cantekin");

		eDet1.setEd_Id(1L);
		eDet1.setRemainingLeave(5);
		eDet1.setStartToWork(LocalDate.parse("2022-08-01"));

		groupEmplyee.setEg_Id(1L);
		groupEmplyee.setGroupCode("employee");
		groupEmplyee.setGroupName("employee");

		emp1.setEmployeeType(groupEmplyee);
		emp1.setEmployeeDetails(eDet1);

		emp2.setE_Id(124L);
		emp2.setName("Can");
		emp2.setSurname("Cem");

		eDet2.setEd_Id(124L);
		eDet2.setRemainingLeave(15);
		eDet2.setStartToWork(LocalDate.parse("2020-01-01"));

		groupEmplyee2.setEg_Id(1L);
		groupEmplyee2.setGroupCode("employee");
		groupEmplyee2.setGroupName("employee");

		emp2.setEmployeeType(groupEmplyee);
		emp2.setEmployeeDetails(eDet2);

		emp3.setE_Id(125L);
		emp3.setName("Mert");
		emp3.setSurname("Cöm");

		eDet3.setEd_Id(125L);
		eDet3.setRemainingLeave(15);
		eDet3.setStartToWork(LocalDate.parse("2020-01-01"));

		//groupEmplyee3.setEg_Id(2L);
		groupEmplyee3.setGroupCode("manager");
		groupEmplyee3.setGroupName("manager");

		emp3.setEmployeeType(groupEmplyee3);
		emp3.setEmployeeDetails(eDet3);

		_repo.save(emp1);
		_repo.save(emp2);
		_repo.save(emp3);
	}
}
