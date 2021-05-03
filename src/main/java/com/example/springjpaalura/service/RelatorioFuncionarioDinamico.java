package com.example.springjpaalura.service;

import com.example.springjpaalura.orm.Funcionario;
import com.example.springjpaalura.repository.FuncionarioRepository;
import com.example.springjpaalura.specification.SpecificationFuncionario;
import com.example.springjpaalura.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    @Autowired FuncionarioRepository funcionarioRepository;

    public void inicial(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        if (nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        if (cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        if (salario == 0) {
            salario = null;
        }

        System.out.println("Digite a data de contratacao");
        String data = scanner.next();

        LocalDate dataContratacao;

        if (data.equalsIgnoreCase("NULL")) {
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse(data, DateUtils.createFormatter());
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(
                Specification.where(SpecificationFuncionario.nome(nome))
                .or(SpecificationFuncionario.cpf(cpf))
                .or(SpecificationFuncionario.salario(salario))
                .or(SpecificationFuncionario.dataContratacao(dataContratacao)));

        funcionarios.forEach(System.out::println);
    }

}
