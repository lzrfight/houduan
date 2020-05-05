package com.bishe.houduan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "board")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Article title.
     */
    private String articleTitle;

    /**
     * Article content after render to html.
     */
    private String articleContentHtml;

    /**
     * Article content in markdown syntax.
     */
    private String articleContentMd;

    /**
     * Article abstract.
     */
    private String articleAbstract;

    /**
     * Article cover's url.
     */
    private String articleCover;

    /**
     * Article release date.
     */
    private Date articleDate;
}

