package usmb.nc.cgi;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String email;

    public Customer(){

    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // standard constructors / setters / getters / toString
}