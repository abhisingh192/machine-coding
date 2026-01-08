package org.lib_mgmt_system.entity;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
}
