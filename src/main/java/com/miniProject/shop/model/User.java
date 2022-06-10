package com.miniProject.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Username", length = 50)
    private String username;

    @Lob
    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Role", nullable = false, length = 30)
    private String role;

    @Column(name = "Enabled", nullable = false)
    private boolean enabled = false;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "users")
    private Profile profile;


    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = true;
    }

    public User(Long id, String username, String password, String role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}