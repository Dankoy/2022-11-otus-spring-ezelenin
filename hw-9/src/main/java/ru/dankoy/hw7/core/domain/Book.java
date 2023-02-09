package ru.dankoy.hw7.core.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

// https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/
// https://thorben-janssen.com/ultimate-guide-to-implementing-equals-and-hashcode-with-hibernate/#Using_a_Generated_Primary_Key
// The implementation of the equals() and hashCode() methods for Hibernate entities is an often discussed topic that provides an interesting, technical challenge. But as I explained at the beginning of this article, you only need to override Object’s default implementations, if you work with multiple Hibernate Sessions or with detached entities. For all other applications, the default implementation works perfectly fine.
//
// If you decide to provide your own equals() and hashCode() implementations, you need to make sure that your code fulfills the contracts defined by the Java language and that the hash code of your objects doesn’t change when the entity gets persisted. The implementation of these methods, therefore, depends on the different keys available for your entity and how you set their values:
//
//    If your entity has a business key or a natural ID, you can use it within your equals() and hashCode() method.
//    If you set your primary key values programmatically, you can use its value in your equals check and when you calculate the hash code.
//    If you tell Hibernate to generate your primary key values, you need to use a fixed hash code, and your equals() method requires explicit handling of null values.

// Если использовать графы, при этом удалить из книги жанры и авторы, то книга будет возвращаться фрэймворком как null

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "books")
public class Book {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name", nullable = false, unique = false)
  private String name;


  @BatchSize(size = 10)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "books_authors",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
  private Set<Author> authors = new HashSet<>();


  @BatchSize(size = 10)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "books_genres",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
  private Set<Genre> genres = new HashSet<>();


  @OneToMany(targetEntity = Commentary.class, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  @JsonManagedReference // решает проблему с рекурсивной сериализацией
  @Setter
  private Set<Commentary> commentaries = new HashSet<>();
}
