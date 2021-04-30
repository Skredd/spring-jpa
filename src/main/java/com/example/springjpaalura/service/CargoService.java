package com.example.springjpaalura.service;

import com.example.springjpaalura.orm.Cargo;
import com.example.springjpaalura.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CargoService {

    private boolean system = true;
    @Autowired CargoRepository cargoRepository;

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
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);

        System.out.println("Salvo");
    }

    private void update(Scanner scanner) {
        System.out.println("Digite a nova descricao");
        String descricao = scanner.next();

        System.out.println("Digite o ID");
        Long id = scanner.nextLong();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
    }

    private void visualizar() {
        Iterable<Cargo> cargoList = cargoRepository.findAll();
        cargoList.forEach(cargo -> {
            System.out.println(cargo);
        });
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o ID");
        Long id = scanner.nextLong();
        cargoRepository.deleteById(id);
    }
}
