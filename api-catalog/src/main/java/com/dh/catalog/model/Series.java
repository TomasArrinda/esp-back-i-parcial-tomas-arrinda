package com.dh.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Series")
public class Series implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String genre;
    private List<Season> seasons;

    @AllArgsConstructor
    @NoArgsConstructor
    @Document
    public class Season {
        @Id
        private String id;
        private int seasonNumber;
        private List<Chapter> chapters;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Document
    public class Chapter {
        @Id
        private String id;
        private String name;
        private int number;
        private String urlStream;
    }
}