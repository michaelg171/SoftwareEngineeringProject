package Model;

public class Item {

    public String ID;
    public String name;
    public double price;
    public int quantity;

    public Item(String ID, String name, double price, int quantity){

        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getID() {
      return ID;
    }

    public void setID(String ID) {
      this.ID = ID;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public double getPrice() {
      return price;
    }

    public void setPrice(double price) {
      this.price = price;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }
    
    public String toString() {
    	
    	String s = "";
    	
    	// s += "Thing: "+value
    	// s += "Thing: "+value
    	
    	return s;
    }
    
    public void writeToDb() {
    	// preparedstatement = adfasdfjs("insert into table (name, email ...) values(?, ?, ? ...")
        // preparedstatement.insertString(1, name)
    }
}