package com.example.dao;

import com.example.model.Inventory;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Model
public class InventoryDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    /*@Resource(lookup = "jdbc/JavaMartDB")
    private DataSource dataSource;*/
    // Get the Inventory for this Product
    public Inventory getInventory(String product_id) throws SQLException {
        Inventory inventory = em.find(Inventory.class, product_id);
        /*Inventory inventory = null;
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM INVENTORY WHERE Product_ID = '" + product_id + "'");
            rs.next();
            inventory = new Inventory(product_id, rs.getInt("AVAILABLE"));
        }*/
        return inventory;
    }

    public void updateInventory(String product_id, int quantity) throws SQLException {
        Inventory inventory = new Inventory(product_id, quantity);
        em.merge(inventory);
//        try (Connection conn = dataSource.getConnection()) {
//            Statement stmt = conn.createStatement();
//            // TODO: Update the inventory with the quantity value passed
//            stmt.executeUpdate("UPDATE INVENTORY SET Available = " + quantity + " WHERE Product_ID = '" + product_id + "'");
//        }
    }
}
