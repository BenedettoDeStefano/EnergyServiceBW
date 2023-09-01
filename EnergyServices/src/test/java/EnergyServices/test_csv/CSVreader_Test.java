package EnergyServices.test_csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import EnergyServices.Entities.Comune;
import EnergyServices.Entities.Provincia;
import EnergyServices.Service.CSVreader;


@SpringBootTest
public class CSVreader_Test {
	
	@Autowired
	private CSVreader csvReader;

    private String provinciaCsvPath;
    private String comuneCsvPath;

    @BeforeEach
    public void setUp() {
        provinciaCsvPath = "province-italiane.csv";
        comuneCsvPath = "comuni-italiani.csv";
    }

    @Test
    public void testGetListaProvince() {
        List<Provincia> province = csvReader.getListaProvince(provinciaCsvPath);

        // Verifica che la lista non sia vuota o null
        assertNotNull(province);
        assertFalse(province.isEmpty());

        // Verifica il numero specifico di province che dovrebbero essere lette
        assertEquals(107, province.size());
    }

    @Test
    public void testGetListaComuni() {
        List<Comune> comuni = csvReader.getListaComuni(comuneCsvPath);

        // Verifica che la lista non sia vuota o null
        assertNotNull(comuni);
        assertFalse(comuni.isEmpty());

        // Verifica il numero specifico di comuni che dovrebbero essere letti
        assertEquals(7904, comuni.size());
    }
}
