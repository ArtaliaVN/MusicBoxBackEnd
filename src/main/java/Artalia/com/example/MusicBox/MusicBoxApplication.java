package Artalia.com.example.MusicBox;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicBoxApplication {
	
	public static void main(String[] args) throws IOException, GeneralSecurityException { 
		SpringApplication.run(MusicBoxApplication.class, args);	
	}
}
