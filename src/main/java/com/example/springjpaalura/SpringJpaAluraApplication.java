package com.example.springjpaalura;

import com.example.springjpaalura.orm.Cargo;
import com.example.springjpaalura.orm.UnidadeTrabalho;
import com.example.springjpaalura.repository.CargoRepository;
import com.example.springjpaalura.service.CargoService;
import com.example.springjpaalura.service.FuncionaroService;
import com.example.springjpaalura.service.RelatoriosService;
import com.example.springjpaalura.service.UnidadeTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringJpaAluraApplication implements CommandLineRunner {

    private Boolean system = true;

    @Autowired
    private CargoService cargoService;
    @Autowired
    private FuncionaroService funcionaroService;
    @Autowired
    private UnidadeTrabalhoService unidadeTrabalhoService;
    @Autowired
    private RelatoriosService relatoriosService;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while(system) {
            System.out.println("Qual acao vc deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargos ");
            System.out.println("2 - unidade trabalho ");
            System.out.println("3 - funcionario ");
            System.out.println("4 - Relatorios");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    cargoService.inicio(scanner);
                    break;
                case 2:
                    unidadeTrabalhoService.inicio(scanner);
                    break;
                case 3:
                    funcionaroService.inicio(scanner);
                    break;
                case 4:
                    relatoriosService.inicio(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }
}
