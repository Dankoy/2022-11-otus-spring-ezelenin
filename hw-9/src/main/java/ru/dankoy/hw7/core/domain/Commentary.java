package ru.dankoy.hw7.core.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/
// https://thorben-janssen.com/ultimate-guide-to-implementing-equals-and-hashcode-with-hibernate/#Using_a_Generated_Primary_Key
// The implementation of the equals() and hashCode() methods for Hibernate entities is an often discussed topic that provides an interesting, technical challenge. But as I explained at the beginning of this article, you only need to override Object’s default implementations, if you work with multiple Hibernate Sessions or with detached entities. For all other applications, the default implementation works perfectly fine.
//
// If you decide to provide your own equals() and hashCode() implementations, you need to make sure that your code fulfills the contracts defined by the Java language and that the hash code of your objects doesn’t change when the entity gets persisted. The implementation of these methods, therefore, depends on the different keys available for your entity and how you set their values:
//
//    If your entity has a business key or a natural ID, you can use it within your equals() and hashCode() method.
//    If you set your primary key values programmatically, you can use its value in your equals check and when you calculate the hash code.
//    If you tell Hibernate to generate your primary key values, you need to use a fixed hash code, and your equals() method requires explicit handling of null values.


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "book")
// фиксит рекурсивный вызов equals и hashcode комментария и книги
@Getter
@Entity
@Table(name = "commentaries")
public class Commentary {

  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "text", nullable = false, unique = false)
  private String text;

  @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
  @JsonBackReference // решает проблему с рекурсивной сериализацией
  @Setter
  private Book book;

}
