package Lesson_3;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase")
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "id_customer")
//    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "id_customer"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> products;

    @ManyToMany
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_customer")
    )
    private List<Customer> customers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Customer> getCustomers(){
        return customers;
    }

//    public int getCustomerId() {
//        return customer.getId();
//    }
//
//    public String getCustomerName() {
//        return customer.getName();
//    }
}
