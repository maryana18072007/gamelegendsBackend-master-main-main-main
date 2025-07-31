package br.gamelegends.gamelegends.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gamelegends.gamelegends.model.Cadastro; // Importar o modelo Cadastro
import br.gamelegends.gamelegends.model.Login;
import br.gamelegends.gamelegends.model.LoginRepository;
import jakarta.transaction.Transactional;

@Service
public class LoginService {
    // Criar objeto repository
    final LoginRepository loginRepository;

    // Injeção de Dependência
    public LoginService(LoginRepository _loginRepository) {
        this.loginRepository = _loginRepository;
    }

    // Método INSERT INTO LOGIN
    @Transactional
    public Login save(Login _login) {
        return loginRepository.save(_login);
    }

    // Método SELECT * FROM LOGIN
    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    public Login findAllById(long id) {
        return loginRepository.findById(id).orElse(null); // Use findById para retornar um Optional
    }

    // Método UPDATE LOGIN
    @Transactional
    public Login update(Long id, Login login) {
        Optional<Login> optionalLogin = loginRepository.findById(id);

        if (optionalLogin.isPresent()) {
            Login existingLogin = optionalLogin.get();

            // Atualiza os campos necessários do Login
            existingLogin.setEmail(login.getEmail());
            existingLogin.setSenha(login.getSenha());

            // Se você quer atualizar o nome do Cadastro vinculado, faça o seguinte:
            Cadastro cadastro = existingLogin.getCadastro(); // Obtém o cadastro vinculado
            if (cadastro != null) { // Verifique se o cadastro não é nulo
                cadastro.setNome(login.getCadastro().getNome()); // Atualiza o nome no Cadastro
                cadastro.setUsuario(login.getCadastro().getUsuario()); // Atualiza o tipo de usuário no Cadastro
                // Você pode atualizar outros campos do Cadastro aqui, se necessário
            }

            // Salva as alterações no banco de dados
            return loginRepository.save(existingLogin);
        } else {
            throw new RuntimeException("Login não encontrado com o ID: " + id);
        }
    }

    // Método DELETE LOGIN
    @Transactional
    public void delete(long id) {
        loginRepository.deleteById(id);
    }
}