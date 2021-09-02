import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class BD{
  private static BD instance;
  private Connection conexao;



  //Ritual Singleton

  private BD(){

  }

  public static BD getInstance(){
    if(instance == null){
      synchronized(BD.class){
        if(instance == null){
          instance = new BD();
        }
      }
    }
    return instance;
  }



  //Conexao com o banco sqlite

  public boolean conectar(){
    try{
      String url = "jdbc:sqlite:DriverBD/banco_sqlite.db";
      this.conexao = DriverManager.getConnection(url);
    }catch(SQLException e){
      System.err.println("\nNao foi possivel conectar ao banco.\n" + e.getMessage());
      return false;
    }
    return true;
  }

  public boolean desconectar(){
    try{
      if(this.conexao.isClosed() == false){
        this.conexao.close();
      }
    }catch(SQLException e){
      System.err.println("\nError.\n" + e.getMessage());
      return false;
    }
    
    return true;
  }

  

  //Cria os Statements para os sqls serem executados

  public Statement criarStatement(){
    try{
      return this.conexao.createStatement();
    }catch(SQLException e){
      System.err.println("\nProblema.\n" + e.getMessage());
      return null;
    }
  }

  public PreparedStatement criarPreparedStatement(String sql){
    try{
      return this.conexao.prepareStatement(sql);
    }catch(SQLException e){
      System.err.println("\nProblema.\n" + e.getMessage());
      return null;
    }
  }

  public Connection getConnection(){
    return this.conexao;
  }

}