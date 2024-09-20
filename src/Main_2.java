import java.io.IOException;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;
import java.util.Scanner;

public class Main_2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
            PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
            String prefixoArquivo;
            
            int opcao;
            do {
                System.out.println("Menu:");
                System.out.println("1 - Incluir");
                System.out.println("2 - Alterar");
                System.out.println("3 - Excluir");
                System.out.println("4 - Exibir por ID");
                System.out.println("5 - Exibir todos");
                System.out.println("6 - Salvar dados");
                System.out.println("7 - Recuperar dados");
                System.out.println("0 - Sair");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir o enter
                
                switch (opcao) {
                    case 1 -> {
                        System.out.println("Incluir: 1 - Pessoa Fisica | 2 - Pessoa Juridica");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (tipo == 1) {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.print("Idade: ");
                            int idade = scanner.nextInt();
                            scanner.nextLine();
                            repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                        } else {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("CNPJ: ");
                            String cnpj = scanner.nextLine();
                            repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                        }
                    }
                        
                    case 2 -> {
                        System.out.println("Alterar: 1 - Pessoa Fisica | 2 - Pessoa Juridica");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1) {
                            System.out.print("ID da Pessoa Fisica a alterar: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            PessoaFisica pessoaFisica = repoFisica.obter(id);
                            if (pessoaFisica != null) {
                                System.out.print("Novo nome: ");
                                pessoaFisica.setNome(scanner.nextLine());
                                System.out.print("Novo CPF: ");
                                pessoaFisica.setCpf(scanner.nextLine());
                                System.out.print("Nova idade: ");
                                pessoaFisica.setIdade(scanner.nextInt());
                                scanner.nextLine();
                                repoFisica.alterar(pessoaFisica);
                            } else {
                                System.out.println("Pessoa Fisica não encontrada.");
                            }
                        } else {
                            System.out.print("ID da Pessoa Juridica a alterar: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            PessoaJuridica pessoaJuridica = repoJuridica.obter(id);
                            if (pessoaJuridica != null) {
                                System.out.print("Novo nome: ");
                                pessoaJuridica.setNome(scanner.nextLine());
                                System.out.print("Novo CNPJ: ");
                                pessoaJuridica.setCnpj(scanner.nextLine());
                                repoJuridica.alterar(pessoaJuridica);
                            } else {
                                System.out.println("Pessoa Juridica não encontrada.");
                            }
                        }
                    }
                        
                    case 3 -> {
                        System.out.println("Excluir: 1 - Pessoa Fisica | 2 - Pessoa Juridica");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1) {
                            System.out.print("ID da Pessoa Fisica a excluir: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            repoFisica.excluir(id);
                        } else {
                            System.out.print("ID da Pessoa Juridica a excluir: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            repoJuridica.excluir(id);
                        }
                    }
                        
                    case 4 -> {
                        System.out.println("Exibir por ID: 1 - Pessoa Fisica | 2 - Pessoa Juridica");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1) {
                            System.out.print("ID da Pessoa Fisica: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            PessoaFisica pessoaFisica = repoFisica.obter(id);
                            if (pessoaFisica != null) {
                                pessoaFisica.exibir();
                            } else {
                                System.out.println("Pessoa Fisica não encontrada.");
                            }
                        } else {
                            System.out.print("ID da Pessoa Juridica: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            PessoaJuridica pessoaJuridica = repoJuridica.obter(id);
                            if (pessoaJuridica != null) {
                                pessoaJuridica.exibir();
                            } else {
                                System.out.println("Pessoa Juridica não encontrada.");
                            }
                        }
                    }
                        
                    case 5 -> {
                        System.out.println("Exibir todos: 1 - Pessoa Fisica | 2 - Pessoa Juridica");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1) {
                            for (PessoaFisica pessoaFisica : repoFisica.obterTodos()) {
                                pessoaFisica.exibir();
                            }
                        } else {
                            for (PessoaJuridica pessoaJuridica : repoJuridica.obterTodos()) {
                                pessoaJuridica.exibir();
                            }
                        }
                    }
                        
                    case 6 -> {
                        System.out.print("Prefixo do arquivo para salvar: ");
                        prefixoArquivo = scanner.nextLine();
                        try {
                            repoFisica.persistir(prefixoArquivo + ".fisica.bin");
                            repoJuridica.persistir(prefixoArquivo + ".juridica.bin");
                            System.out.println("Dados salvos com sucesso.");
                        } catch (Exception e) {
                            System.out.println("Erro ao salvar os dados: " + e.getMessage());
                        }
                    }
                        
                    case 7 -> {
                        System.out.print("Prefixo do arquivo para recuperar: ");
                        prefixoArquivo = scanner.nextLine();
                        try {
                            repoFisica.recuperar(prefixoArquivo + ".fisica.bin");
                            repoJuridica.recuperar(prefixoArquivo + ".juridica.bin");
                            System.out.println("Dados recuperados com sucesso.");
                        } catch (IOException | ClassNotFoundException e) {
                            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
                        }
                    }
                        
                    case 0 -> System.out.println("Finalizando...");
                        
                    default -> System.out.println("Opção inválida!");
                }
                
            } while (opcao != 0);
        }
    }
}
