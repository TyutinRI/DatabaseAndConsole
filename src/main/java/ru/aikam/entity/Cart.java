package ru.aikam.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "cart_good",
    joinColumns = {@JoinColumn(name = "cart_id")},
    inverseJoinColumns = {@JoinColumn(name = "good_id")})
    private List<Good> goods;

    public Cart (){}

    public Cart(Date date, Customer customer, List<Good> goods) {
        this.date = date;
        this.customer = customer;
        this.goods = goods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
}
