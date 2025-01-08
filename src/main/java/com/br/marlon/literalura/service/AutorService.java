package com.br.marlon.literalura.service;

import com.br.marlon.literalura.model.Autor;
import com.br.marlon.literalura.model.Livro;
import com.br.marlon.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;


}
