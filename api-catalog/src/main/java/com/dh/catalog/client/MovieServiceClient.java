package com.dh.catalog.client;

import com.dh.catalog.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@FeignClient(name="api-movie")
public interface MovieServiceClient {
	@GetMapping("/movies/{genre}")
	List<MovieDto> getMovieByGenre(@PathVariable (value = "genre") String genre);

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	class MovieDto implements Serializable {
		private String id;
		private String name;
		private String genre;
		private String urlStream;
	}
}