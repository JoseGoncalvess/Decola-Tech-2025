package br.com.DecolaTech.barber_shop_api.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(
        name = "CLIENTS",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_EMAIL", columnNames = "email"),
                @UniqueConstraint(name = "UK_PHONE", columnNames = "phone")
        }
)
@Getter
@Setter
@ToString
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 11, columnDefinition = "bpchar(11)")
    private String phone;

    @ToString.Exclude
    @OneToMany(mappedBy = "client", cascade = ALL, orphanRemoval = true)
    private Set<ScheduleEntity> schedules = new HashSet<>();


    public ClientEntity(Long id, String name, String email, String phone, Set<ScheduleEntity> schedules) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.schedules = schedules;
    }

    public ClientEntity(){}

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ClientEntity that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone);
    }
}



