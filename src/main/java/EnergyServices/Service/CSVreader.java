package EnergyServices.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import EnergyServices.Entities.Comune;
import EnergyServices.Entities.Provincia;
import EnergyServices.Repository.ComuneRepository;
import EnergyServices.Repository.ProvinciaRepository;

@Service
public class CSVreader {
	
	@Autowired
	ProvinciaRepository provRepo;
	@Autowired
	ComuneRepository comuneRepo;
	
	
	// -------------- LISTA PROVINCE
	
	public List<Provincia> getListaProvince(String path) {
		
		//creo un array vuoto che conterrà le province
		List<Provincia> listaProvince = new ArrayList<>();
		
		//uso il parser per specificare il separatore utilizzato nela mio file CSV
		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		
		//faccio un trycatch per leggere il file passato sfruttando la libreria OPENCSV
		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(path))
                .withCSVParser(parser)
                .withSkipLines(1) // Salta la riga dell'intestazione
                .build()) {

            String riga[];
            while ((riga = csvReader.readNext()) != null) {
                String sigla = riga[0];
                String provincia = riga[1];
                String regione = riga[2];
                
                // Cambio la sigla per Roma 
                if ("Roma".equalsIgnoreCase(provincia)) {
                    sigla = "RM"; 
                }
                
                // Cambio il valore del parametro "provincia" da "Verbania" a "Verbano-Cusio-Ossola"
                if ("Verbania".equalsIgnoreCase(provincia)) {
                    provincia = "Verbano-Cusio-Ossola";
                }
                
                // Cambio il valore del parametro "provincia" da "Monza-Brianza" a "Monza e della Brianza"
                if ("Monza-Brianza".equalsIgnoreCase(provincia)) {
                    provincia = "Monza e della Brianza";
                }
                
                // Cambio il valore del parametro "provincia" da "La-Spezia" a "La Spezia"
                if ("La-Spezia".equalsIgnoreCase(provincia)) {
                    provincia = "La Spezia";
                }
                
                // Cambio il valore del parametro "provincia" da "Reggio-Emilia" a "Reggio nell'Emilia"
                if ("Reggio-Emilia".equalsIgnoreCase(provincia)) {
                    provincia = "Reggio nell'Emilia";
                }
                
                // Cambio il valore del parametro "provincia" da "Forli-Cesena" a "Forlì-Cesena"
                if ("Forli-Cesena".equalsIgnoreCase(provincia)) {
                    provincia = "Forlì-Cesena";
                }
                
                // Cambio il valore del parametro "provincia" da "Pesaro-Urbino" a "Pesaro e Urbino"
                if ("Pesaro-Urbino".equalsIgnoreCase(provincia)) {
                    provincia = "Pesaro e Urbino";
                }
                
                // Cambio il valore del parametro "provincia" da "Ascoli-Piceno" a "Ascoli Piceno"
                if ("Ascoli-Piceno".equalsIgnoreCase(provincia)) {
                    provincia = "Ascoli Piceno";
                }
                
                // Cambio il valore del parametro "provincia" da "Reggio-Calabria" a "Reggio Calabria"
                if ("Reggio-Calabria".equalsIgnoreCase(provincia)) {
                    provincia = "Reggio Calabria";
                }
                
                // Cambio il valore del parametro "provincia" da "Vibo-Valentia" a "Vibo Valentia"
                if ("Vibo-Valentia".equalsIgnoreCase(provincia)) {
                    provincia = "Vibo Valentia";
                }
        
                // Non tengo conto delle province che non voglio aggiungere alla Lista province e quindi al DB
                if (!"OG".equals(sigla) && !"OT".equals(sigla) && !"VS".equals(sigla) && !"CI".equals(sigla)) {
                    Provincia nuovaProvincia = new Provincia(sigla, provincia, regione);
                    listaProvince.add(nuovaProvincia);
                }
               }
            
         // Aggiungo la provincia Sud Sardegna
            Provincia nuovaProvincia = new Provincia("SU", "Sud Sardegna", "Sardegna");
            listaProvince.add(nuovaProvincia);
            
            provRepo.saveAll(listaProvince);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return listaProvince;
	}
	
	// -------------- LISTA COMUNI
	
	public List<Comune> getListaComuni(String path) {
		
		List<Comune> listaComuni = new ArrayList<>();
		int numerazioneCrescente = 1;
		
		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		
		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(path))
                .withCSVParser(parser)
                .withSkipLines(1) // Salta la riga dell'intestazione
                .build()) {

            String riga[];
            
            while ((riga = csvReader.readNext()) != null) {
            	
                int codiceProvincia = Integer.parseInt(riga[0]);
                
                //Assumo che sia un valore string il valore progressivo del comune (valore sbagliato) 
                String progressivoComuneError = riga[1];
                //dichiaro una variabile con valore corretto
                int progressivoComune;
                
                // modifico il valore cel campo dove trovo l'errore "#RIF!"
                if ("#RIF!".equals(progressivoComuneError)) {
                	progressivoComune = numerazioneCrescente;
                	numerazioneCrescente++;
                } else {
                    progressivoComune = Integer.parseInt(progressivoComuneError);
                }
              
                String denominazione = riga[2];
                String nomeProvincia = riga[3];
                
                // Cambio la il nome della provincia Valle d'Aosta
                if ("Valle d'Aosta/Vallée d'Aoste".equalsIgnoreCase(nomeProvincia)) {
                	nomeProvincia = "Aosta"; 
                }
                
                // Cambio la il nome della provincia Bolzano/Bozen
                if ("Bolzano/Bozen".equalsIgnoreCase(nomeProvincia)) {
                	nomeProvincia = "Bolzano"; 
                }

                //System.out.println(codiceProvincia +" "+ progressivoComune +" "+ denominazione +" "+ nomeProvincia);

                //cerco la provincia per il nome della provincia definita nel comune
                List<Provincia> province = provRepo.findByProvincia(nomeProvincia);
                if (province != null) {
                	Provincia provinciaComune = province.get(0);
                	
                	Comune nuovoComune = new Comune(codiceProvincia, progressivoComune, denominazione, provinciaComune);
                	listaComuni.add(nuovoComune);
                	comuneRepo.save(nuovoComune);
                	// stampa a consolle la lista di tutti i comuni
                    //System.out.println(nuovoComune); 
                }
                
                comuneRepo.saveAll(listaComuni);
                
               }			
			
		} catch(Exception e) {
			System.out.println("Errore durante la lettura del file CSV: " + e.getMessage());
			e.printStackTrace();
		}
		return listaComuni;
		
	}
}
