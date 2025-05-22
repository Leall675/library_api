package com.leal.library.service;

import com.leal.library.dto.AutorDTO;
import com.leal.library.exceptions.OperacaoNaoPermitidaException;
import com.leal.library.exceptions.RegistroDiplicadoException;
import com.leal.library.model.Autor;
import com.leal.library.model.Livro;
import com.leal.library.repository.AutorRepository;
import com.leal.library.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void salvarAutor(Autor autor) {
        validarAutor(autor);
        autorRepository.save(autor);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> autorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void deletarAutor(Autor autor) {
        if (possuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException("Não é possível excluir um autor que possui livro cadastrado.");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> buscarPorParametro(String nome, String nacionalidade) {
        if (nome != null) {
            return autorRepository.findByNomeContainingIgnoreCase(nome);
        } else if (nacionalidade != null) {
            return autorRepository.findByNacionalidadeContainingIgnoreCase(nacionalidade);
        } else {
            return autorRepository.findAll();
        }
    }

    public void atualizarAutor(Autor autor) {
        if (autor.getId() == null) {
            throw new RuntimeException("O autor precisar existir para ser atualizado.");
        }
        validarAutor(autor);
        autorRepository.save(autor);
    }

    public void validarAutor(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDiplicadoException("Autor já cadastrado na base !");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = autorRepository.findByNomeIgnoreCaseAndDataNascimentoAndNacionalidadeIgnoreCase(
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade()
        );
        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        if (autorEncontrado.isPresent()) {
            Autor encontrado = autorEncontrado.get();
            return !autor.getId().equals(encontrado.getId());
        }
        return false;
    }
     //nao permitir excluir um autor que possui algum livro
    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
//        List<Livro> livros = autor.getLivros();
//        return livros != null && !livros.isEmpty();
    }
}
