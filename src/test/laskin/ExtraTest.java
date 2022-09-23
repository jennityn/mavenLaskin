package laskin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ExtraTest extends AbstractParent {

    private static Laskin laskin = new Laskin();
    private final double DELTA = 0.001;

    @BeforeAll
    public static void testVirtaON() {
        System.out.println("@BeforeAll Virta ON (ennen ensimmäistä testiä)");
        laskin.virtaON();
    }

    @AfterAll
    public static void testVirtaOFF() {
        System.out.println("@AfterAll Virta OFF (kaikki testit ajettu).");
        laskin.virtaOFF();
        laskin = null;
    }

    @BeforeEach
    public void testNollaa() {
        System.out.println("  Nollaa laskin.");
        laskin.nollaa();
        assertEquals(0, laskin.annaTulos(), DELTA, "Nollaus ei onnistunut");
    }

    @ParameterizedTest(name="Luvun {0} neliö on {1}")
    @CsvSource({ "2, 4", "4, 16", "5, 25" })
    public void testNelio(double luku,  double tulos) {
        laskin.nelio(luku);
        assertEquals(tulos, laskin.annaTulos(), DELTA,"Neliöön korotus ei toimi");
    }

    @Test
    public void testNeliojuuri2() {
        laskin.neliojuuri(25);
        assertEquals(5, laskin.annaTulos(), DELTA,"Luvun 5 neliöön korotus väärin");
    }

    @Test
    @DisplayName("Testaa negatiivinen neliöjuuri")
    public void testNeliojuuriNegat() {
        ArithmeticException poikkeus = assertThrows(ArithmeticException.class, () -> laskin.neliojuuri(-10));
        assertEquals("Negatiivisella luvulla ei ole neliöjuurta", poikkeus.getMessage());
    }
}
