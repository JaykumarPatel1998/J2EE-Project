package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mvcdemo.model.Claim;
 
public class ClaimDao {
 
    public static void insertClaim(int registration_id, String date_of_claim, String description, String status) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM claim WHERE registration_id=?";
            connection = DB.getConnection();
 
            ps = connection.prepareStatement(query);
            ps.setInt(1, registration_id);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i =0;
        while(rs.next()) {
        	i++;
        }
        if(i<3) {
        try {
            String query = "INSERT INTO claim (registration_id, date_of_claim, description, status) VALUES (?, ?, ?, ?)";
            connection = DB.getConnection();
 
            ps = connection.prepareStatement(query);
            ps.setInt(1, registration_id);
            ps.setString(2, date_of_claim);
            ps.setString(3, description);
            ps.setString(4, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        } else {
        	throw new Exception("Claim Quota exceeded");
        }
    }
 
    public static List<Claim> getAllClaims() {
        List<Claim> claims = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
 
        try {
            String query = "SELECT * FROM claim";
            connection = DB.getConnection();
 
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
 
            while (rs.next()) {
                int claim_id = rs.getInt("claim_id");
                int registration_id = rs.getInt("registration_id");
                String date_of_claim = rs.getString("date_of_claim");
                String description = rs.getString("description");
                String status = rs.getString("status");
 
                Claim claim = new Claim(claim_id, registration_id, date_of_claim, description, status);
                claims.add(claim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return claims;
    }
}