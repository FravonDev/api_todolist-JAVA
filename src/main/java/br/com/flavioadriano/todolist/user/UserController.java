package br.com.flavioadriano.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired // o spring vai gerenciar a criação de uma instância automaticamente
    private IUserRepository userRepository;

    /**
     * tipos de retorno
     * String (texto)
     * Integer (número inteiro)
     * Double (número decimal) 0.0000
     * Float (número decimal) 0.000
     * char ('A' ou 'B'))
     * boolean (true ou false)
     * void (vazio)
     * 
     * 
     **/
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        System.out.println(userModel.toString());
        System.out.println(userModel.getName());
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe no banco de dados");
        }
        // criptografar a senha
        var hashedPassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(hashedPassword);

        var userCreated = userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
