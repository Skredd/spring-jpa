package com.example.springjpaalura.orm;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionario;

    @Override public String toString() {
        return "Cargo{" + "id=" + id + ", descricao='" + descricao + '\'' + '}';
    }
}
