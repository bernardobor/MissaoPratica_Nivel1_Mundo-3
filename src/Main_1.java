import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class Main_1 {
    public static void main(String[] args) {
        try {
            // Repositório de Pessoas Físicas - repo1
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            repo1.inserir(new PessoaFisica(1, "Bernardo Oliveira", "111.111.111-11", 30));
            repo1.inserir(new PessoaFisica(2, "Bernardo Ramos", "222.222.222-22", 25));

            // Persistir dados de repo1
            String arquivoPF = "pessoasFisicas.dat";
            repo1.persistir(arquivoPF);

            // Recuperar dados com repo2
            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar(arquivoPF);

            // Exibir todas as pessoas físicas recuperadas
            System.out.println("Pessoas Fisicas Recuperadas:");
            for (PessoaFisica pessoa : repo2.obterTodos()) {
                pessoa.exibir();
            }

            // Repositório de Pessoas Jurídicas - repo3
            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            repo3.inserir(new PessoaJuridica(1, "Digiral avaliacao", "11.111.111/0001-11"));
            repo3.inserir(new PessoaJuridica(2, "ETEG", "22.222.222/0001-22"));

            // Persistir dados de repo3
            String arquivoPJ = "pessoasJuridicas.dat";
            repo3.persistir(arquivoPJ);

            // Recuperar dados com repo4
            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar(arquivoPJ);

            // Exibir todas as pessoas jurídicas recuperadas
            System.out.println("\nPessoas Juridicas Recuperadas:");
            for (PessoaJuridica pessoa : repo4.obterTodos()) {
                pessoa.exibir();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
