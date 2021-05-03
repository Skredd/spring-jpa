package com.example.springjpaalura.service;

import com.example.springjpaalura.orm.Funcionario;
import com.example.springjpaalura.orm.FuncionarioProjecao;
import com.example.springjpaalura.repository.FuncionarioRepository;
import com.example.springjpaalura.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private boolean system = true;

    @Autowired FuncionarioRepository funcionaroRepository;

    public void inicio(Scanner scanner) {
        while (system) {
            System.out.println("O que deseja fazer");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario nome");
            System.out.println("2 - Busca funcionario por nome, salario maior que, data de contratacao");
            System.out.println("3 - Busca funcionario data de contratacao");
            System.out.println("4 - Pesquisa os funcionarios e salario");




            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscaFuncionarioPorNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioPorNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacaoMaior(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioPorNome(Scanner scanner) {
        System.out.println("Digite o nome que deseja procurar");
        String name = scanner.next();
        List<Funcionario> list = funcionaroRepository.findByNome(name);
        list.forEach(System.out::println);
    }


    private void buscaFuncionarioPorNomeSalarioMaiorData(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data");
        String data = scanner.next();

        LocalDate localDate = LocalDate.parse(data, DateUtils.createFormatter());

        List<Funcionario> funcionarios = funcionaroRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacaoMaior(Scanner scanner) {

        System.out.println("Digite a data de contratacao");
        String dataContratacao = scanner.next();
        LocalDate localDate = LocalDate.parse(dataContratacao, DateUtils.createFormatter());

        List<Funcionario> funcionarios = funcionaroRepository.findDataContratacaoMaior(localDate);

        funcionarios.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> funcionarioProjecaos = funcionaroRepository.findFuncionarioSalario();
        funcionarioProjecaos.forEach(f -> {
            System.out.println("Funcionario: id: " + f.getId()
            + " nome: " + f.getNome() + " salario: " + f.getSalario());
        });
    }

}
