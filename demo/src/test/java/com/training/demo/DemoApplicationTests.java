package com.training.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.text.NumberFormat;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private ApplicationContext ctx;
	
	@Autowired @Qualifier("defaultCurrencyFormat")
	private NumberFormat nf;

	@Test
	void defaultCurrency() {
		double amount = 12345678.901234;
		System.out.println(nf.format(amount));
	}
    @Test
	void germanCurrency() {
		double amount = 12345678.901234;
		NumberFormat deutschNF= ctx.getBean("germanCurrencyFormat", NumberFormat.class);
		System.out.println(deutschNF.format(amount));
	}

	@Test
	void contextLoads() {
		int count = ctx.getBeanDefinitionCount();
		System.out.println("There are " + count + " Beans in the application context");

		for (String name : ctx.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

}
