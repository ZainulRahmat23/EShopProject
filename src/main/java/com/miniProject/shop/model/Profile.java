package com.miniProject.shop.model;

import com.miniProject.shop.Dto.Customer.TransactionHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Profiles")
@NoArgsConstructor
@AllArgsConstructor
@Data

@NamedNativeQuery(
        name = "history",
        query = """
            select p.Id as productId, o.Id as orderId, p.product as productName, od.Price as totalPrice,od.Quantity as quantity,o.Status as status
                       from Profiles as c
                       left join Orders as o on c.Id = o.Customer
                       left join OrderDetails as od on o.Id = od.OrderId
                       left join Product as p on od.Product = p.Id
                       where c.Id = :id
                       and o.Status != 'Progress'
                """,
        resultSetMapping = "transactionHistory"
)
@SqlResultSetMapping(
        name = "transactionHistory",
        classes = @ConstructorResult(
                targetClass = TransactionHistory.class,
                columns = {
                        @ColumnResult(name = "productId", type = Long.class),
                        @ColumnResult(name = "orderId", type = Integer.class),
                        @ColumnResult(name = "productName", type = String.class),
                        @ColumnResult(name = "totalPrice", type = BigDecimal.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "status", type = String.class)
                }
        )
)
public class Profile {
    @Id
    @Column(name = "Id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id", nullable = false)
    private User users;

    @Column(name = "FirstName", nullable = false, length = 20)
    private String firstName;

    @Column(name = "LastName", length = 20)
    private String lastName;

    @Column(name = "BirthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "Gender", nullable = false, length = 1)
    private String gender;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public String fetchFullName(){
        return String.format("%s %s",firstName,lastName);
    }

    public Profile(Long id, String firstName, String lastName, LocalDate birthDate, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Profile(String firstName, String lastName, LocalDate birthDate, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

}