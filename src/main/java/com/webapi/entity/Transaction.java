package com.webapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int amount;
    private String name;
    private LocalDateTime date;
    private int transferToID;
    private int transferFromID;

    @JsonIgnoreProperties("transactions")
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTransferToID() {
        return transferToID;
    }

    public void setTransferToID(int transferToID) {
        this.transferToID = transferToID;
    }

    public int getTransferFromID() {
        return transferFromID;
    }

    public void setTransferFromID(int transferFromID) {
        this.transferFromID = transferFromID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
