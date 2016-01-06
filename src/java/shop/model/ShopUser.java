package shop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Miguel
 */
@Entity
public class ShopUser implements UserInterface, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userName;
    
    private String userPassword;
    private boolean userAdmin;
    private boolean userBanned;
    
    public ShopUser(){
        
    }
    
    public ShopUser(String userName, String userPassword, boolean userAdmin, boolean userBanned){
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAdmin = userAdmin;
        this.userBanned = userBanned;
    }

    
    @Override
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
         this.userName = userName;
    }
    
    @Override
    public String getUserPassword(){
        return userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    @Override
    public boolean getUserAdmin() {
        return userAdmin;
    }
    
    public void setUserAdmin(boolean userAdmin) {
         this.userAdmin = userAdmin;
    }
    
    @Override
    public boolean getUserBanned(){
        return userBanned;
    }
    
    @Override
    public void setUserBanned(boolean userBanned) {
         this.userBanned = userBanned;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return userName.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopUser)) {
            return false;
        }
        ShopUser other = (ShopUser) object;
        if ((this.userName.equals(other.getUserName()))) {
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shop.model.ShopUser("+userName+":"+userPassword+":"+userAdmin+":"+userBanned+")";
    }
    
}
