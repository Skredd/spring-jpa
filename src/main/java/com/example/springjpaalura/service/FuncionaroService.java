package com.example.springjpaalura.service;

import com.example.springjpaalura.orm.Cargo;
import com.example.springjpaalura.orm.Funcionario;
import com.example.springjpaalura.orm.UnidadeTrabalho;
import com.example.springjpaalura.repository.CargoRepository;
import com.example.springjpaalura.repository.FuncionaroRepository;
import com.example.springjpaalura.repository.UnidadeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class FuncionaroService {

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired FuncionaroRepository funcionaroRepository;
    @Autowired CargoRepository cargoRepository;
    @Autowired UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public void inicio(Scanner scanner) {
        while (system) {
            System.out.println("O que deseja fazer");
            System.out.println("0 - Sair");
            System.out.println("1 - criar");
            System.out.println("2 - atualizar");
            System.out.println("3 - visualizar");
            System.out.println("4 - deletar");


            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    criar(scanner);
                    break;
                case 2:
                    update(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void criar(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contratacao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o id do cargo");
        Long cargoId = scanner.nextLong();

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);

        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);

        funcionaroRepository.save(funcionario);

        System.out.println("Salvo");
    }

    private List<UnidadeTrabalho> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Long unidadeId = scanner.nextLong();

            if(unidadeId != 0) {
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }

    private void update(Scanner scanner) {
        System.out.println("Digite o ID");
        Long funcionarioId = scanner.nextLong();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite o cargoId");
        Long cargoId = scanner.nextLong();

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);

        List<UnidadeTrabalho> unidades = unidade(scanner);

        System.out.println("Digite a data de contratacao");
        String dataContratacao = scanner.next();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);
        funcionaroRepository.save(funcionario);
        System.out.println("Atualizado");
    }

    private void visualizar(Scanner scanner) {
        System.out.println("Qual pagina deseja vizualizar?");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.unsorted());

        Page<Funcionario> funcionarios = funcionaroRepository.findAll(pageable);

        System.out.println(funcionarios);
        System.out.println("Pagina atual " + funcionarios.getNumber());
        System.out.println("Total de elementos " + funcionarios.getTotalElements());
        funcionarios.forEach(funcionario ->  System.out.println(funcionario));
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o ID");
        Long id = scanner.nextLong();
        funcionaroRepository.deleteById(id);

        System.out.println("Deletado");
    }
}
