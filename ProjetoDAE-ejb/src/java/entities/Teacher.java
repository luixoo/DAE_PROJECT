/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.security.auth.Subject;

/**
 *
 * @author Luis
 */
@Entity
@NamedQuery(name = "getAllTeachers", query = "SELECT t FROM Teacher t ORDER BY t.name")
public class Teacher extends User {

    private String office;
    
    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    protected Teacher() {
        subjects = new LinkedList<>();
    }

    public Teacher(String username, String password, String name, String email, String office) {
        super(username, password, UserGroup.GROUP.Teacher, name, email);
        this.office = office;
        subjects = new LinkedList<>();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }    
    
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }    
    
}
