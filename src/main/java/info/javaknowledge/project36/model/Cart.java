package info.javaknowledge.project36.model;

public class Cart {

    int pid;
    String pname;
    int qty = 1;
    double price;
    double total;

    public Cart(int pid, String pname, int qty, double price, double total) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.total = total;
        this.qty = qty;
    }

    

    public Cart() {

    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    

    @Override
    public String toString() {
        String s = "pid=" + pid + "!pname=" + pname + "!qty=" + qty + "!price=" + price + "!total=" + total + "!";
        return s;
    }
}
