package com.example.dao;

import com.example.model.ProductSales;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Model
public class ProductSalesDAO implements Serializable {

//    @Resource(lookup = "jdbc/JavaMartDB")
//    private DataSource dataSource;
    @PersistenceContext
    private EntityManager em;

    public void addProductSalesRecord(ProductSales salesRecord) throws SQLException {
        em.persist(salesRecord);
        /*try (Connection conn = dataSource.getConnection()) {
            // TODO: Insert a new ProductSales record into the database
            PreparedStatement pStmt = conn.prepareCall("INSERT INTO ProductSales VALUES(?, ?, ?)");
            // Use a PreparedStatement
            pStmt.setInt(1, salesRecord.getSales_id());
            pStmt.setString(2, salesRecord.getProduct_id());
            pStmt.setInt(3, salesRecord.getQuantity_sold());
            pStmt.executeUpdate();
        }*/
    }

    public List<ProductSales> getProductSalesBySalesID(int sales_ID) throws SQLException {
        String queryString = "SELECT p from ProductSales p WHERE   p.sales_id =" + sales_ID;
        TypedQuery<ProductSales> query = em.createQuery(queryString, ProductSales.class);
        List<ProductSales> pSales = query.getResultList();
        /*List<ProductSales> pSales = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ProductSales WHERE Sales_ID = " + sales_ID);
            while (rs.next()) {
                int sales_id = rs.getInt("Sales_ID");
                String product_id = rs.getString("Product_ID");
                int quantity_sold = rs.getInt("Quantity");
                pSales.add(new ProductSales(sales_id, product_id, quantity_sold));
            }
        }*/
        return pSales;
    }
}
