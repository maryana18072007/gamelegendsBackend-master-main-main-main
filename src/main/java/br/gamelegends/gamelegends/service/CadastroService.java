package br.gamelegends.gamelegends.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gamelegends.gamelegends.model.Cadastro;
import br.gamelegends.gamelegends.model.CadastroRepository;
import jakarta.transaction.Transactional;

@Service
public class CadastroService {
    final CadastroRepository cadastroRepository;

    public CadastroService(CadastroRepository _cadastroRepository) {
        this.cadastroRepository = _cadastroRepository;
    }

    @Transactional
    public Cadastro save(Cadastro _cadastro) {
        return cadastroRepository.save(_cadastro);
    }

    public List<Cadastro> findAll() {
        return cadastroRepository.findAll();
    }

    public Optional<Cadastro> findById(long id) {
        return cadastroRepository.findById(id);
    }

    public Cadastro findByEmail(String email) {
        return cadastroRepository.findByEmail(email);
    }

    @Transactional
    public Cadastro update(Long id, Cadastro cadastro) {
        Optional<Cadastro> optionalCadastro = cadastroRepository.findById(id);

        if (optionalCadastro.isPresent()) {
            Cadastro existingCadastro = optionalCadastro.get();
            if (cadastro.getDatanascimento() == null) {
                throw new RuntimeException("Data de nascimento não pode ser nula.");
            }
            existingCadastro.setEmail(cadastro.getEmail());
            existingCadastro.setNome(cadastro.getNome());
            existingCadastro.setDatanascimento(cadastro.getDatanascimento());
            existingCadastro.setCpf(cadastro.getCpf());
            existingCadastro.setTelefone(cadastro.getTelefone());
            existingCadastro.setUsuario(cadastro.getUsuario()); // Adicionando a atualização do campo usuario

            return cadastroRepository.save(existingCadastro);
        } else {
            throw new RuntimeException("Cadastro não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void delete(long id) {
        cadastroRepository.deleteById(id);
    }
}