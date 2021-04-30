package com.example.springjpaalura.orm;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Double salario;
    private LocalDate dataContratacao;
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_unidades", joinColumns = {
            @JoinColumn(name = "fk_funcionario") },
            inverseJoinColumns = { @JoinColumn(name = "fk_unidade") })
    private List<UnidadeTrabalho> unidadeTrabalhos;

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", nome='" + nome + '\'' + ", cpf='" + cpf + '\'' + ", salario='" + salario
                + '\'' + ", dataContratacao=" + dataContratacao + ", cargo=" + cargo;
    }
}
