import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FolhaPagamentoApp {
    private static final ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

    public static void main(String[] args) {
        importarRegistros();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato esperado de data
        Scanner scanner = new Scanner(System.in); // Criar o objeto Scanner fora do loop
        while (true) {
            exibirMenu();
            System.out.print("Opção: ");
            String opcao;
            try {
                opcao = scanner.nextLine();
            } catch (NoSuchElementException e) {
                // Se ocorrer uma exceção de NoSuchElementException, provavelmente a entrada foi encerrada.
                // Neste caso, encerramos o programa.
                System.out.println("Encerrando o programa.");
                return;
            }

            switch (opcao) {
                case "1":
                    buscarRegistro();
                    break;
                case "2":
                    removerRegistro();
                    break;
                case "3":
                    listarRegistros();
                    break;
                case "4":
                    ordenarRegistros();
                    break;
                case "5":
                    System.out.println("Encerrando o programa.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println();
        }
    }

    private static void importarRegistros() {
        try (InputStream inputStream = FolhaPagamentoApp.class.getResourceAsStream("folhapagto.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato esperado de data

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                if (fields.length == 16) {
                    RegistroFolhaPagamento registro = new RegistroFolhaPagamento();
                    registro.setEmpresa(fields[0]);
                    registro.setMes((fields[1]));
                    registro.setAno((fields[2]));
                    registro.setNome(fields[3]);
                    registro.setCargo(fields[4]);
                    registro.setLotacao(fields[5]);
                    registro.setAdmissao((fields[6])); // Parse da data
                    registro.setNascimento(fields[7]);
                    registro.setVencimentos((fields[8]));
                    registro.setEncargos((fields[9]));
                    registro.setBeneficios((fields[10]));
                    registro.setOutrasRemuneracoes((fields[11]));
                    registro.setVinculo(fields[12]);
                    registro.setDetalheVinculo(fields[13]);
                    registro.setLiminar(fields[14]);
                    registro.setArquivoId((fields[15]));

                    arvore.inserir(registro);
                }
            }
            System.out.println("Registros importados com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exibirMenu() {
        System.out.println("------ Menu ------");
        System.out.println("1. Buscar registro");
        System.out.println("2. Remover registro");
        System.out.println("3. Listar registros");
        System.out.println("4. Ordenar registros");
        System.out.println("5. Sair");
    }

    private static void removerRegistro() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite a chave (nome) do registro a ser removido: ");
            String nome = scanner.nextLine();

            RegistroFolhaPagamento registroRemover = new RegistroFolhaPagamento();
            registroRemover.setNome(nome);

            boolean removido = arvore.remover(registroRemover);
            if (removido) {
                System.out.println("Registro removido com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        }
    }

    private static void listarRegistros() {
        List<RegistroFolhaPagamento> registros = arvore.listarEmOrdem();
        for (RegistroFolhaPagamento registro : registros) {
            System.out.println(registro);
        }
    }

    private static void buscarRegistro() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite parte do nome do registro a ser buscado: ");
            String parteNome = scanner.nextLine().toUpperCase(); // Convertendo para maiúsculas para facilitar a comparação

            List<RegistroFolhaPagamento> resultados = arvore.listarEmOrdem();
            List<RegistroFolhaPagamento> registrosEncontrados = new ArrayList<>();

            for (RegistroFolhaPagamento registro : resultados) {
                if (registro.getNome().toUpperCase().contains(parteNome)) {
                    registrosEncontrados.add(registro);
                }
            }

            if (!registrosEncontrados.isEmpty()) {
                System.out.println("Registros encontrados:");
                for (RegistroFolhaPagamento registro : registrosEncontrados) {
                    System.out.println(registro);
                }
            } else {
                System.out.println("Nenhum registro encontrado com a sequência informada.");
            }
        }
    }


    private static void ordenarRegistros() {
        List<RegistroFolhaPagamento> registros = arvore.listarEmOrdem();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Escolha o atributo para ordenação:");
            System.out.println("1. Nome");
            System.out.println("2. Vencimentos");
            System.out.println("3. Encargos");
            System.out.println("4. Benefícios");
            System.out.println("5. Outras Remunerações");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Collections.sort(registros, Comparator.comparing(RegistroFolhaPagamento::getNome));
                    break;
                case 2:
                    Collections.sort(registros, Comparator.comparing(RegistroFolhaPagamento::getVencimentos));
                    break;
                case 3:
                    Collections.sort(registros, Comparator.comparing(RegistroFolhaPagamento::getEncargos));
                    break;
                case 4:
                    Collections.sort(registros, Comparator.comparing(RegistroFolhaPagamento::getBeneficios));
                    break;
                case 5:
                    Collections.sort(registros, Comparator.comparing(RegistroFolhaPagamento::getOutrasRemuneracoes));
                    break;
                default:
                    System.out.println("Opção inválida. Nenhum registro foi ordenado.");
                    return;
            }

            System.out.println("Registros ordenados:");
            for (RegistroFolhaPagamento registro : registros) {
                System.out.println(registro);
            }
        }
    }
}
