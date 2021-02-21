package com.example.dao;

import com.example.model.Sales;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Model
public class SalesDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

//    @Resource(lookup = "jdbc/JavaMartDB")
//    private DataSource dataSource;
    public Sales createSalesRecord() throws SQLException {
        Sales newSalesRecord = new Sales();
        em.persist(newSalesRecord);
        em.flush();
        return newSalesRecord;
        /*try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Sales (Total_Sale) VALUES (0)", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return new Sales(rs.getInt(1), new Date(), 0);
        }*/
    }

    public void updateSalesRecord(Sales productSale) throws SQLException {
        em.merge(productSale);
        /*try (Connection conn = dataSource.getConnection()) {
            // TODO: complete the method to update the sales record with productSale.getSales_id()
            PreparedStatement pStmt = conn.prepareStatement("UPDATE Sales SET Date_Sold = ?, Total_Sale = ? WHERE Sales_id = ?");
            // Note: use a prepared statement
            pStmt.setInt(3, productSale.getSales_id());
            pStmt.setDate(1, new java.sql.Date(productSale.getSales_date().getTime()));
            pStmt.setDouble(2, productSale.getTotal_sale());
            if (pStmt.executeUpdate() != 1) {
                throw new SQLException("Failed to update the Sales record");
            }
        }*/
    }

    public void removeSalesRecord(Sales productSales) throws SQLException {
        em.remove(productSales);
        /*try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM Sales WHERE Sales_Id = " + productSales.getSales_id());
        }*/
    }
}
