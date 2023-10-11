package br.com.flavioadriano.todolist.user;

import lombok.Data;

@Data // ajuda a criar os getters e setters automaticamente
// @Getter apenas getters
// @Setter apenas setters
public class UserModel {
    private String username;
    // também podemos definir para atributos especificos
    // @Getter
    private String name;

    // @Setter
    private String password;

}
