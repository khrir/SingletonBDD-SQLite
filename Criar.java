import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Criar{

  private BD bd;
  private Costumer costumer;

  public Criar(BD bd){
    this.bd = bd;
  }

  public void criarTabela(){
    String sql = "CREATE TABLE IF NOT EXISTS tbl_costumer (id integer PRIMARY KEY, name VARCHAR(30), phone VARCHAR(20));";

    boolean conected = false;

    try{
      conected = bd.conectar(); 

      Statement stmt = bd.criarStatement();

      stmt.execute(sql);

      System.out.println("\nCreated!");
    }catch(SQLException e){
      System.err.println("\nError___:" + e.getMessage());
      
    }finally{
      if(conected){
        bd.desconectar();
      }
    }
  }
  
  public void inserirDados(){
    bd.conectar();
    costumer = new Costumer();

    String sqlInsert = "INSERT INTO tbl_costumer(id, name, phone) VALUES (?, ?, ?)";
    PreparedStatement preparedStatement = bd.criarPreparedStatement(sqlInsert);

    try{
      preparedStatement.setInt(1, costumer.getId());
      preparedStatement.setString(2, costumer.getName());
      preparedStatement.setString(3, costumer.getPhone());

      int result = preparedStatement.executeUpdate();

      if(result == 1){
        System.out.println("\nADD!");
      }else{
        System.out.println("\nNAO ADD!");
      }
    }catch(SQLException e){
      System.out.println("\nError___: " + e.getMessage());
    }finally{
      if(preparedStatement != null){
        try{
          preparedStatement.close();
          bd.desconectar();
        }catch(SQLException ex){
          System.out.println("\nError___: " + ex.getMessage());
        }
      }
    }
    
  }

  public void lerDados(){
    bd.conectar();
    ResultSet resultSet = null;
    Statement stmt = null;

    String query = "SELECT * FROM tbl_costumer";

    stmt = bd.criarStatement();

    try{
      resultSet = stmt.executeQuery(query);
      while(resultSet.next()){
        System.out.println("\n==========================");
        System.out.println("ID_______: " + resultSet.getInt("id"));
        System.out.println("NOME_____: " +resultSet.getString("name"));
        System.out.println("Telefone_: " + resultSet.getString("phone"));
      }
    }catch(SQLException e){
      System.out.println("\nError___: " + e.getMessage());
    }finally{
      try{
        resultSet.close();
        stmt.close();
        bd.desconectar();
      }catch(SQLException ex){
        System.out.println("\nError___: " + ex.getMessage());
      }
    }
  }

  public void buscarCamarada(){
    bd.conectar();
    Scanner in = new Scanner(System.in);

    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    System.out.println("Entre com o id que deseja buscar: ");
    int idCostumer = in.nextInt();

    String sql = "SELECT * FROM tbl_costumer WHERE id = ?";

    try{
      preparedStatement = bd.criarPreparedStatement(sql);
      preparedStatement.setInt(1, idCostumer);

      resultSet = preparedStatement.executeQuery();

      while(resultSet.next()){
        System.out.println("\n==========================");
        System.out.println("ID_______: " + resultSet.getInt("id"));
        System.out.println("NOME_____: " +resultSet.getString("name"));
        System.out.println("Telefone_: " + resultSet.getString("phone"));
      }

    }catch(SQLException e){
      System.out.println("\nError___: " + e.getMessage());
    }finally{
      try{
        preparedStatement.close();
        resultSet.close();
        bd.desconectar();
      }catch(SQLException ex){
        System.out.println("\nError___: " + ex.getMessage());
      }
    }
  }

  public void attDados(){
    bd.conectar();

    Scanner in = new Scanner(System.in);
    PreparedStatement preparedStatement = null;

    System.out.println("\nEntre com o id que deseja modificar: ");
    int idCostumer = in.nextInt();

    try{

      System.out.println("\nEntre com o que deseja atualizar: 1 - nome | 2 - phone");
      int opcao = in.nextInt();

      if(opcao == 1){
        String sql = "UPDATE tbl_costumer SET name = ? WHERE id = ?";

        System.out.println("\nEntre com o novo nome: ");
        String newName = in.next();

        preparedStatement = bd.criarPreparedStatement(sql);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, idCostumer);
      }
      else if(opcao == 2){
        String sql = "UPDATE tbl_costumer SET phone = ? WHERE id = ?";

        System.out.println("\nEntre com o novo phone: ");
        String newPhone = in.next();

        preparedStatement = bd.criarPreparedStatement(sql);
        preparedStatement.setString(1, newPhone);
        preparedStatement.setInt(2, idCostumer);
      }
      preparedStatement.executeUpdate();
    }catch(SQLException e){
      System.out.println("\nError___: " + e.getMessage());
    }finally{
      try{
        System.out.println("\nATT!!!");
        preparedStatement.close();
        bd.desconectar();
      }catch(SQLException ex){
        System.out.println("\nError___: " + ex.getMessage());
      }
    }
  }

  public void deletarDados(){
    bd.conectar();
    Scanner in = new Scanner(System.in);

    System.out.println("\nEntre com o id que deseja deletar: ");
    int idCostumer = in.nextInt();

    PreparedStatement preparedStatement = null;

    String sql = "DELETE FROM tbl_costumer WHERE id = ?";

    try{
      preparedStatement = bd.criarPreparedStatement(sql);
      preparedStatement.setInt(1, idCostumer);

      int qtdLinhas = preparedStatement.executeUpdate();
      System.out.println(qtdLinhas + " registro(os) deletado(os)!");
    }catch(SQLException e){
      System.out.println("\nError___: " + e.getMessage());
    }finally{
      try{
        preparedStatement.close();
        bd.desconectar();
      }catch(SQLException ex){
        System.out.println("\nError___: " + ex.getMessage());
      }
    }
  }

  
}