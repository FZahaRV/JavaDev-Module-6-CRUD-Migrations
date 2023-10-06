import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

public class DatabaseMigration {
    public static void main(String[] args) {
        try {
            Flyway flyway = Flyway.configure()
                    .dataSource("jdbc:h2:~/test", "sa", null)
                    .load();
            flyway.migrate();
        }catch (FlywayException e) {
            e.printStackTrace();
        }

    }
}
