/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import util.Security;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    protected String username;
    
    @NotNull(message = "Password cannot be empty")
    protected String password;
    
    @NotNull(message = "Name cannot be empty")
    protected String name;
    
    @NotNull(message = "Email cannot be empty")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
                    + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
                    + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid email format")
    protected String email;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    protected UserGroup group;

    protected User() {
        
    }

    protected User(String username, String password, UserGroup.GROUP group, String name, String email) {
        this.username = username;
        this.password = Security.hashPassword(password);
        this.group = new UserGroup(group, this);
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
