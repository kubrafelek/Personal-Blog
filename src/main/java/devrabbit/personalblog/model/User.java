package devrabbit.personalblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import devrabbit.personalblog.model.enums.UserStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonIgnore // just in case Jackson tries wants to betray us
    @Column(name = "password", nullable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.ACTIVE;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastSuccessfullyLoggedInTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastFailureLoggedInTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public void addRoles(Set<Role> roles) {
        roles.forEach(role -> role.setUser(this));
        this.roles.addAll(roles);
    }

    public void removeRoles(Set<Role> roles) {
        roles.forEach(role -> role.setUser(null));
        this.roles.removeAll(roles);
    }

    public void lockUser() {
        this.userStatus = UserStatus.LOCKED;
    }
}
