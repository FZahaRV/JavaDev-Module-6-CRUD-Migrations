import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final Connection conn;
    private PreparedStatement createSt;
    private PreparedStatement readSt;

    {
        try {
            conn = Database.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public long create(String name) {
        if (name.length() < 2 || name.length() > 1000) {
            System.out.println("Name (" + name + ") not valid");
            return 0;
        }
        long result = 0;
        try{
            createSt = conn.prepareStatement("INSERT INTO client (NAME) VALUES (?)");
            createSt.setString(1, name);
            createSt.executeUpdate();
            readSt = conn.prepareStatement("SELECT id FROM client WHERE name = (?)");
            readSt.setString(1, name);
            ResultSet rs = readSt.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            result = rs.getInt("id");
        }catch (SQLException e) {
            System.out.println("Adding name (" + name + ") was not successful");
            e.printStackTrace();
        }
        return result;
    }
    public String getById(long id) {
        if (id > getMaxId()) {
            System.out.println("Client with id " + id + " does not exist");
            return "";
        }
        String result = null;
        try{
            readSt = conn.prepareStatement("SELECT name FROM client WHERE id = (?)");
            readSt.setLong(1, id);
            ResultSet rs = readSt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            result = rs.getString("name");
        }catch (SQLException e) {
            System.out.println("Name output was not successful");
            e.printStackTrace();
        }
        return result;
    }
    public void setName(long id, String name) {
        if (id > getMaxId() || name.length() < 2 || name.length() > 1000) {
            System.out.println("Client with id " + id + " does not exist or name (" + name + ") not valid");
            return;
        }
        try {
            createSt = conn.prepareStatement("UPDATE client SET name = (?) WHERE id = (?)");
            createSt.setString(1, name);
            createSt.setLong(2, id);
            createSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update name (" + name + ") by id " + id + " is not successful");
            e.printStackTrace();
        }
    }
    public void deleteById(long id) {
        if (id > getMaxId()) {
            System.out.println("Client with id " + id + " does not exist");
            return;
        }
        try {
            createSt = conn.prepareStatement("DELETE FROM client WHERE id = (?)");
            createSt.setLong(1, id);
            createSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("The deletion was not successful");
            e.printStackTrace();
        }
    }
    public List<Client> listAll() {
        List<Client> list = new ArrayList<>();
        try {
            readSt = conn.prepareStatement("SELECT id, name FROM client");
            ResultSet rs = readSt.executeQuery();
            while (rs.next()) {
                list.add(new Client(rs.getString("name"),
                        rs.getInt("id")));
            }
            if (list.isEmpty()) {
                System.out.println("Table is empty");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public long getMaxId() {
        long result = 0;
        try {
            readSt = conn.prepareStatement("SELECT MAX(id) FROM client");
            ResultSet rs = readSt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public record Client(String name, long id) {
    }
}
