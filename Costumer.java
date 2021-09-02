import java.util.Scanner;

public class Costumer{
  private int id;
  private String name;
  private String phone;

  public Costumer(){
    Scanner in = new Scanner(System.in);
    System.out.println("Entre com o id, nome, e phone");
    id = in.nextInt();
    name = in.next();
    phone = in.next(); 
  }

  public Costumer(int id, String name, String phone){
    this.id = id;
    this.name = name;
    this.phone = phone;
  }

  public int getId(){
    return id;
  }
  public String getName(){
    return name;
  }
  public String getPhone(){
    return phone;
  }
}