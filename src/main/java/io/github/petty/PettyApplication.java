package io.github.petty;

import org.springframework.ai.vectorstore.qdrant.autoconfigure.QdrantVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		QdrantVectorStoreAutoConfiguration.class
})
public class PettyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PettyApplication.class, args);
	}

}
