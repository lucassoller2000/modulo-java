package br.com.cwi.crescer.tcc.security.password;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Criptografia {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    String result = encoder.encode("myPassword");
//    assertTrue(encoder.matches("myPassword", result));
    public String criptografarSenha(String senha){
//        return BCrypt.hashpw(senha, BCrypt.gensalt());
        return encoder.encode(senha);
    }

    public boolean senhaIgual(String senhaUsuario, String senhaCriptografada){
//        if (BCrypt.checkpw(senhaUsuario, senhaCriptografada)) {
        if(encoder.matches(senhaUsuario, senhaCriptografada)){
            return true;
        }return false;
    }
}
