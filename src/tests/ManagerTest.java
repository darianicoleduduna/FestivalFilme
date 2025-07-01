package tests;
import classes.Manager;
import classes.Staff;
import classes.Zone;
import classes.FunctieStaff;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {

    @Test
    public void testBonusAprobat() {

        Manager manager = new Manager("Manager", "Test", "manager@test.com", "0700000000",
                10000, FunctieStaff.Manager_Festival, 10, Zone.Birou_de_Informatii, 8);

        Staff angajat = new Staff("Ion", "Popescu", "ion.popescu@gmail.com", "0711111111",
                3000, FunctieStaff.Voluntar, 12, Zone.Zona_Food_Court, 8); // a lucrat 12 ore, norma 8

        int salariuInitial = angajat.getSalariu();
        int oreLucrate = angajat.getNrOreLucru();
        int norma = angajat.getNorma();

        angajat.aplicaPentruBonus(manager);

        int salariuAsteptat = salariuInitial + 100 * (oreLucrate - norma);
        assertEquals(salariuAsteptat, angajat.getSalariu());
        assertEquals(oreLucrate, angajat.getNorma());
    }

    @Test
    public void testBonusRespins() {

        Manager manager = new Manager("Manager", "Test", "manager@test.com", "0700000000",
                10000, FunctieStaff.Manager_Festival, 10, Zone.Birou_de_Informatii, 8);

        Staff angajat = new Staff("Ana", "Ionescu", "ana.ionescu@gmail.com", "0722222222",
                3000, FunctieStaff.Fotograf_Videograf, 6, Zone.Zona_Sali, 8); // a lucrat sub normă

        int salariuInitial = angajat.getSalariu();

        angajat.aplicaPentruBonus(manager);

        assertEquals((salariuInitial - 200), angajat.getSalariu());
        assertEquals(6,angajat.getNorma());
    }
}