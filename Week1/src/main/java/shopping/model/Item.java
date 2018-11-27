package shopping.model;

public class Item {
    private  long id;
    private  String name;
    private  double price;

    public Item(){
        
    }
    public Item(long id,String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getname() {
        return name;
    }
    public long getId(){
        return id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public void setId(long id){
        this.id=id;
    }

}