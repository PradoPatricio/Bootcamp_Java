package shopping.dto;



public class ProductDto {
    private  Long id;
    private  String name;
    private  double price;

    public ProductDto(){
        
    }
    public ProductDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getname() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public Long getId(){
        return id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public void setId(Long id){
        this.id=id;
    }

}