package com.example.springjpaalura.orm;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "unidade_trabalho")
public class UnidadeTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String endereco;
    @ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

    @Override public String toString() {
        return "UnidadeTrabalho{" + "id=" + id + ", descricao='" + descricao + '\'' + ", endereco='" + endereco;
    }
}
