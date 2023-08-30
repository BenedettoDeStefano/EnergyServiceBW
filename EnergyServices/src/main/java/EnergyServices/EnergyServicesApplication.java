package EnergyServices;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import EnergyServices.Entities.Comune;
import EnergyServices.Entities.Provincia;
import EnergyServices.Service.CSVreader;



@SpringBootApplication
public class EnergyServicesApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EnergyServicesApplication.class, args);
		
		CSVreader csvService = context.getBean(CSVreader.class);
		
		List<Provincia> elencoProvince = csvService.getListaProvince("province-italiane.csv");
		
//		for (Provincia provincia : elencoProvince) {
//            System.out.println(provincia.getSigla() + " " + provincia.getProvincia() + " " + provincia.getRegione());
//		}
		
		List<Comune> elencoComuni = csvService.getListaComuni("comuni-italiani.csv");
		
	}	
	
}
