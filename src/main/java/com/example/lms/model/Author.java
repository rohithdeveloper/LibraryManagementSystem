
package com.example.lms.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "author")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;

    @Column(name = "author_name")
    private String name;

    @Column(name = "author_age", nullable = false)
    private int age;

    @Column(name = "author_ph")
    private String mobno;

    @Column(name = "author_email")
    private String email;

//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    private List<Book> books = new ArrayList<>();
@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private List<Book> books = new ArrayList<>();
}

