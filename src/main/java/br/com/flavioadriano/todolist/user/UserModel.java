package br.com.flavioadriano.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // ajuda a criar os getters e setters automaticamente
// @Getter apenas getters
// @Setter apenas setters
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    // também podemos definir para atributos especificos
    // @Getter
    private String name;

    // @Setter
    // se eu quiser mudar o nome do atributo no banco de dados
    // @Column(name = "senha")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
