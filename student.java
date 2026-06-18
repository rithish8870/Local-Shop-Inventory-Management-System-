import java.sql.*;

public class ProductDAO {

    public void addProduct(String name,int qty,double price) {

        try(Connection con = DBConnection.getConnection()) {

            String sql =
                    "INSERT INTO products(name,quantity,price) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,name);
            ps.setInt(2,qty);
            ps.setDouble(3,price);

            ps.executeUpdate();

            System.out.println("Product Added");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void viewProducts() {

        try(Connection con = DBConnection.getConnection()) {

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM products");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("id")+" | "+
                        rs.getString("name")+" | "+
                        rs.getInt("quantity")+" | ₹"+
                        rs.getDouble("price"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void lowStockAlert() {

        try(Connection con = DBConnection.getConnection()) {

            String sql =
                    "SELECT * FROM products WHERE quantity < 10";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nLow Stock Products:");

            while(rs.next()) {
                System.out.println(
                        rs.getString("name") +
                        " Remaining: " +
                        rs.getInt("quantity"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}