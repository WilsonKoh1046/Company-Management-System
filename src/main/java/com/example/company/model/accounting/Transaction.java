package com.example.company.model.accounting;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    // mappedBy -> the attribute of this class in other entities
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "transaction", fetch = FetchType.LAZY)
    @JsonManagedReference // For class we want to SHOW the child entity (in JSON format), use JsonManagedReference
    private List<Account> entry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getEntry() {
        return entry;
    }

    public void setEntry(List<Account> entry) {
        this.entry = entry;
    }

    public void addEntryAccount(Account account) {
        this.entry.add(account);
    }

}
