/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.model;

/**
 *
 * @author trabladorr
 */
public interface UserInterface {
    
    public String getUserName();
    public String getUserPassword();
    public boolean getUserAdmin();
    public boolean getUserBanned();
    public void setUserBanned(boolean banned);
}
