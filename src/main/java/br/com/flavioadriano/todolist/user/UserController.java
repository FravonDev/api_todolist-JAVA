package br.com.flavioadriano.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
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
    public void create(@RequestBody UserModel userModel) {
        System.out.println(userModel.toString());
    }
}
