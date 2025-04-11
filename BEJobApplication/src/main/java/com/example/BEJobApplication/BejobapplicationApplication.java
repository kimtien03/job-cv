package com.example.bejobapplication;

import java.awt.Desktop;
import java.net.URI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BejobapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BejobapplicationApplication.class, args);

		try {
				String url = "http://localhost:8090/swagger-ui.html";
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(url));
			}
		} catch (Exception e) {
			System.out.println("⚠️ Không thể mở trình duyệt tự động.");
		}
	}

}
