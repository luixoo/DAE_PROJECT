/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

/**
 *
 * @author Luis
 */

import exceptions.EntityDoesNotExistsException;
import exceptions.EntityAlreadyExistsException;
import entities.CCPMember;
import entities.User;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;


@Stateless
public class CCPMemberBean {
    @PersistenceContext
    private EntityManager em;

    public void create(String username, String password, String name, String email)
            throws EntityAlreadyExistsException {
        try {
            if (em.find(User.class, username) != null) {
                throw new EntityAlreadyExistsException(
                        "Um utilizador já existe com esse username.");
            }
            em.persist(new CCPMember(username, password, name, email));
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(String username, String password, String name, String email)
            throws EntityDoesNotExistsException, MyConstraintViolationException,
            MyConstraintViolationException {
        try {
            CCPMember ccpMember = em.find(CCPMember.class, username);
            if (ccpMember == null) {
                throw new EntityDoesNotExistsException(
                        "Não existe um utilizador CCP com esse username");
            }
            ccpMember.setName(name);
            ccpMember.setEmail(email);

            em.merge(ccpMember);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(
                    Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(String username) throws EntityDoesNotExistsException {
        try {
            CCPMember ccpMember = em.find(CCPMember.class, username);
            if (ccpMember == null) {
                throw new EntityDoesNotExistsException(
                        "Não existe um utilizador CCP com esse username");
            }

            em.remove(ccpMember);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public boolean isCCPMember(String email) {
        try {
            List<CCPMember> ccpMembers = em.createNamedQuery("getAllCCPMembers").getResultList();
            for (CCPMember ccpMember : ccpMembers) {
                if (ccpMember.getEmail().equals(email)) {
                    return true;
                }
            }
            
            return false;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

}
