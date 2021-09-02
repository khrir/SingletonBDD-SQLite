import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    BD teste = BD.getInstance();
    Criar c = new Criar(teste);
    int opcao;
    Scanner in = new Scanner(System.in);

    do{
      System.out.println("\n========================================");
      System.out.println("Escolha o que deseja realizar: ");
      System.out.println("1 - Inserir\n2 - Mostrar todos\n3 - Consultar\n4 - Atualizar\n5 - deletar\n9 - Sair");
      opcao = in.nextInt();

      switch(opcao){
        case 1: c.inserirDados(); break;
        case 2: c.lerDados(); break;
        case 3: c.buscarCamarada(); break;
        case 4: c.attDados(); break;
        case 5: c.deletarDados(); break;
        default: System.out.println("\nOpcao invalida, mas se fechar eh valida"); break;
      }
    }while(opcao != 9);
    
    

    
    


  }
}