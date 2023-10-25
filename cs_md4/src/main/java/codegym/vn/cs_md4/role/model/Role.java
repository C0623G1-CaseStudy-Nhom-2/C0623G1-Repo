package codegym.vn.cs_md4.role.model;

import codegym.vn.cs_md4.account.model.Account;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "app_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String roleName;
    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;
    @ManyToMany(mappedBy = "role")
    @JsonManagedReference
    private Set<Account> account;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(int id, String roleName, boolean isDeleted, Set<Account> account) {
        this.id = id;
        this.roleName = roleName;
        this.isDeleted = isDeleted;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccountSet(Set<Account> account) {
        this.account = account;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
