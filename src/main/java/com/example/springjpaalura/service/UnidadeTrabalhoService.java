package com.example.springjpaalura.service;

import com.example.springjpaalura.orm.UnidadeTrabalho;
import com.example.springjpaalura.repository.UnidadeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UnidadeTrabalhoService {

    private boolean system = true;
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
                    visualizar();
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
        System.out.println("Descricao da unidade");
        String descricao = scanner.next();

        scanner.nextLine();

        System.out.println("Digite o endereco");
        String endereco = scanner.nextLine();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);

        System.out.println("Salvo");
    }

    private void update(Scanner scanner) {
        System.out.println("Digite a nova descricao");
        String descricao = scanner.next();

        System.out.println("Digite o endereco");
        String endereco = scanner.next();

        System.out.println("Digite o ID");
        Long id = scanner.nextLong();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);

        System.out.println("Atualizado");
    }

    private void visualizar() {
        Iterable<UnidadeTrabalho> unidadeTrabalhos = unidadeTrabalhoRepository.findAll();
        unidadeTrabalhos.forEach(unidadeTrabalho -> {
            System.out.println(unidadeTrabalho);
        });
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o ID");
        Long id = scanner.nextLong();
        unidadeTrabalhoRepository.deleteById(id);

        System.out.println("Deletado");
    }
}
