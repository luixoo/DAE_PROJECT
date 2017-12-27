/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.UserGroup.GROUP;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author Luis
 */

@Entity
@NamedQuery(name = "getAllCCPMembers", query = "SELECT s FROM CCPMember s ORDER BY s.name")
public class CCPMember extends User implements Serializable {
    
    public CCPMember() {
    }

    public CCPMember(String userName, String password, String name, String email) {
        super(userName, password, GROUP.CCPMember, name, email);
    }
}
