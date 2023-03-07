package br.com.alura.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_order")
    private LocalDate date = LocalDate.now();
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @ManyToOne
    private Client cliente;
    @OneToMany
    private List<OrderItem> orders;

    public Order() {

    }

    public Order(Client cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", localDate=" + date +
                ", totalPrice=" + totalPrice +
                ", cliente=" + cliente +
                '}';
    }
}
