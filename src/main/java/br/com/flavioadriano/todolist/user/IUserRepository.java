package br.com.flavioadriano.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// definir qual o tipo de dado e qual é o tipo do ID
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    // spring data jpa vai criar a query automaticamente buscando por username
    UserModel findByUsername(String username);
}
