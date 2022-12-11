package com.dh.apiserie;

import com.dh.apiserie.model.Series;
import com.dh.apiserie.repository.SerieRepositoryMongo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableMongoRepositories(basePackages = "com.dh.apiserie.repository")
public class ApiSerieApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiSerieApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(SerieRepositoryMongo repository) {
		return (args) -> {
			if (!repository.findAll().isEmpty()) {
				return;
			}
			Series.Chapter chapter1Season1 = new Series.Chapter(null, "Primero", 1, "www.netflix.com");
			Series.Chapter chapter2Season1 = new Series.Chapter(null, "Segundo", 2, "www.netflix.com");
			Series.Chapter chapter1Season2 = new Series.Chapter(null, "Tercero", 1, "www.netflix.com");
			Series.Chapter chapter2Season2 = new Series.Chapter(null, "Cuarto", 2, "www.netflix.com");
			List<Series.Chapter> chapterList = null;
			chapterList.add(chapter1Season1);
			chapterList.add(chapter2Season1);
			Series.Season season1 = new Series.Season(null, 1, chapterList);
			chapterList.remove(0);
			chapterList.remove(1);
			chapterList.add(chapter1Season2);
			chapterList.add(chapter2Season2);
			Series.Season season2 = new Series.Season(null, 2, chapterList);
			List<Series.Season> seasonList = null;
			seasonList.add(season1);
			seasonList.add(season2);
			repository.save(new Series(null, "Serie 1", "Terror", seasonList));
			repository.save(new Series(null, "Serie 2", "Terror", seasonList));
			repository.save(new Series(null, "Serie 3", "Comedia", seasonList));
			repository.save(new Series(null, "Serie 4", "Fantasia", seasonList));
		};
	}
}