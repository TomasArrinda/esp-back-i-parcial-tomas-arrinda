package com.dh.apiserie.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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
    public static class Season {
        @Id
        private String id;
        private int seasonNumber;
        private List<Chapter> chapters;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Document
    public static class Chapter {
        @Id
        private String id;
        private String name;
        private int number;
        private String urlStream;
    }
}