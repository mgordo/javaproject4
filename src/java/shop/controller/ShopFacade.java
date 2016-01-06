package shop.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Miguel
 */
@Stateless
public class ShopFacade {
    @PersistenceContext(unitName = "shopPU")
    private EntityManager em;
}

/**
 * This class should provide functionality to obtain users and items from the database
 */