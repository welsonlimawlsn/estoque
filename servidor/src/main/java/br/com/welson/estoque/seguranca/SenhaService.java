package br.com.welson.estoque.seguranca;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class SenhaService {

    public String hash(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public boolean compara(String senha, String hash) {
        return BCrypt.checkpw(senha, hash);
    }

}
