package com.cybergari.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CyberGariApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CyberGariApplication.class, args);
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			t.printStackTrace();
		}
	}

}
