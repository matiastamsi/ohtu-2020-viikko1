package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusMuutetaanNollaksiElleiPositiivinen() {
        Varasto v = new Varasto(-2.0);
        assertEquals(0.0, 0.0, v.getTilavuus());
    }
    
    @Test
    public void varastonLuominenKunSaldoJaTilavuusPositiivisia() {
        Varasto v = new Varasto(123.321, 12.3);
        assertEquals(123.321, 123.321, v.getTilavuus());
        assertEquals(12.3, 12.3, v.getSaldo());
    }
    
    @Test
    public void varastonLuominenKunSaldoJaTilavuusNegatiivisia() {
        Varasto v = new Varasto(-123.321, -12.3);
        assertEquals(0.0, 0.0, v.getTilavuus());
        assertEquals(0.0, 0.0, v.getSaldo());
    }
    
    @Test
    public void varastonLuominenKunLaikkyyYli() {
        Varasto v = new Varasto(12, 123);
        assertEquals(12, 12, v.getTilavuus());
        assertEquals(12, 12, v.getSaldo());
    }
    
    @Test
    public void varastoMerkkijonona() {
        Varasto v = new Varasto(3.0, 2.5);
        assertEquals("saldo = 2.5, vielä tilaa 0.5", v.toString());
    }
    
    @Test
    public void negatiivinenLisaysEiTeeMitaan() {
        Varasto v = new Varasto(1.1, 1.0);
        v.lisaaVarastoon(-100);
        assertEquals(1.0, 1.0, v.getSaldo());
    }
    
    @Test
    public void lisatessaYlimaarainenLaikkyyYli() {
        Varasto v = new Varasto(5.0, 4.3);
        v.lisaaVarastoon(1000);
        assertEquals(5.0, 5.0, v.getSaldo());
    }
    
    @Test
    public void josVarastostaOtetaanNegatiivinenMaaraPalautaNolla() {
        Varasto v = new Varasto(1.0, 0.9);
        double m = v.otaVarastosta(-1);
        assertEquals(0.0, 0.0, m);
    }
    
    @Test
    public void otetaanKaikkiMitaVoidaan() {
        Varasto v = new Varasto(12.0, 10.0);
        assertEquals(10.0, 10.0, v.otaVarastosta(11));
    }

}