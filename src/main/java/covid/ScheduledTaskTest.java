package covid;

import au.com.bytecode.opencsv.CSVReader;
import covid.dao.ContinentRepository;
import covid.dao.CountryRepository;
import covid.dao.InfoDailyCountryRepository;
import covid.entity.Continent;
import covid.entity.Country;
import covid.entity.InfoDailyCountry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableJpaRepositories(basePackages = {"covid.dao"})
@EntityScan(basePackages = {"covid.entity"})
@Component
public class ScheduledTaskTest {

    // DAO
    @Autowired
    private final ContinentRepository continentDAO;
    @Autowired
    private final CountryRepository countryDAO;
    @Autowired
    private final InfoDailyCountryRepository infoDailyCountryDAO;

    // Constructeur
    public ScheduledTaskTest(ContinentRepository continentDAO, CountryRepository countryDAO, InfoDailyCountryRepository infoDailyCountryDAO) {
        this.continentDAO = continentDAO;
        this.countryDAO = countryDAO;
        this.infoDailyCountryDAO = infoDailyCountryDAO;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // Téléchargements
    //////////////////////////////////////////////////////////////////////////////////////
    // Téléchargement fichier OWD
    @Scheduled(initialDelay = 1000, fixedRate = 86400000)
    public void downloadFile() {
        // Code
        try {
            // Téléchargement et stockage du fichier OWD
            // On crée un lien direct entre l'url et notre instance
            ReadableByteChannel readChannel = Channels.newChannel(new URL("https://covid.ourworldindata.org/data/owid-covid-data.csv").openStream());
            // On crée un chemin nommé "dataOWD.csv"
            FileOutputStream fileOS = new FileOutputStream("dataOWD.csv");
            // On stocke les données téléchargées dans le chemin
            FileChannel writeChannel = fileOS.getChannel();
            writeChannel
                    .transferFrom(readChannel, 0, Long.MAX_VALUE);

            // Traitement du fichier OWD
            // On transforme le chemin en fichier csv
            File fileOWD = new File("dataOWD.csv");
            // On crée une instance de lecture pour lire le csv
            FileReader frOWD = new FileReader(fileOWD);
            // On définit le séparateur (pour le csv : ",")
            CSVReader csvrOWD = new CSVReader(frOWD, ',');

            // Lecture du fichier OWD
            // On initialise un tableau et une variable vide qui va contenir les élements lus par le Reader
            // List<String[]> dataOWD = new ArrayList<String[]>();
            String[] oneData = csvrOWD.readNext();
            oneData = csvrOWD.readNext();
            // Si la ligne lue n'est pas vide
            while (oneData != null) {
                
                /*
                int size = nextLine.length;
                if (size == 0) {
                    continue;
                }
                String debut = nextLine[0].trim();
                if (debut.length() == 0 && size == 1) {
                    continue;
                }
                if (debut.startsWith("#")) {
                    continue;
                }*/
                //dataOWD.add(nextLine);
                //or (String[] oneData : dataOWD) {

                // Ajout des données
                if (!((oneData[1] == null) || (oneData[1].length()) == 0)) {
                    // Ajout du continent
                    if (!(continentDAO.existsById(oneData[1]))) {
                        Continent newContinent = saveContinent(oneData);
                        continentDAO.save(newContinent);
                    }

                    // Ajout du pays
                    if (!(countryDAO.existsById(oneData[0]))) {
                        Country newCountry = saveCountry(oneData, continentDAO);
                        countryDAO.save(newCountry);
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(oneData[3], formatter);

                    // Ajout de l'infoDaily
                    if ((infoDailyCountryDAO.getIdInfoCountryByCountryInformedCodeCountryAndDate(oneData[0], date) == null)) {
                        InfoDailyCountry newInfoDaily = saveInfoDailyCountry(oneData, countryDAO);
                        infoDailyCountryDAO.save(newInfoDaily);
                    }
                //}
            }
                oneData = csvrOWD.readNext();
            }

            // On supprime la 1ère ligne qui correspond au no des attributs
            //dataOWD.remove(0);

            // Stockage des données du fichier OWD
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(WebApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Vérificateur donnée
    public float verificateur(String attribute) {
        float value = 0;
        if (!((attribute == null) || (attribute.length()) == 0)) {
            value = Float.valueOf(attribute);
        }
        return value;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // Liants
    //////////////////////////////////////////////////////////////////////////////////////
    // Liant Continent - Country
    public void linkContinentCountry(Continent continent, Country country) {
        continent.getCountries().add(country);
        country.setContinent(continent);
    }

    // Liant Country - InfoDailyCountry
    public void linkCountryInfoDailyCountry(Country country, InfoDailyCountry infoDailyCountry) {
        country.getInfos().add(infoDailyCountry);
        infoDailyCountry.setCountryInformed(country);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // Sauvegardes
    //////////////////////////////////////////////////////////////////////////////////////
    // Sauvegarde Continent
    public Continent saveContinent(String[] oneData) {
        // Récupération des données
        String nameContinent = oneData[1];

        // Ajout des données
        Continent continent = new Continent();
        continent.setNameContinent(nameContinent);

        // Return
        return continent;
    }

    // Sauvegarde Country
    public Country saveCountry(String[] oneData, ContinentRepository continentDAO) {
        // Récupération des données
        String codeCountry = oneData[0];
        String nameCountry = oneData[2];

        // Ajout des données
        Country country = new Country();
        country.setCodeCountry(codeCountry);
        country.setNameCountry(nameCountry);

        // Lien entre Continent et Country
        Continent continent = continentDAO.findById(oneData[1]).get();
        linkContinentCountry(continent, country);

        // Actualisation de Continent
        continentDAO.save(continent);

        // Return
        return country;
    }

    // Sauvegarde InfoDailyCountry
    public InfoDailyCountry saveInfoDailyCountry(String[] oneData, CountryRepository countryDAO) throws ParseException {
        // Récupération des données
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(oneData[3], formatter);
        float totalCases = verificateur(oneData[4]);
        float totalDeaths = verificateur(oneData[7]);
        float newCases = verificateur(oneData[5]);
        float newDeaths = verificateur(oneData[8]);
        float positiveRate = verificateur(oneData[31]);
        float newTests = verificateur(oneData[26]);
        float newVaccinations = verificateur(oneData[37]);

        // Ajout des données
        InfoDailyCountry infoDailyCountry = new InfoDailyCountry();
        infoDailyCountry.setDate(date);
        infoDailyCountry.setTotalCases(totalCases);
        infoDailyCountry.setTotalDeaths(totalDeaths);
        infoDailyCountry.setNewCases(newCases);
        infoDailyCountry.setNewDeaths(newDeaths);
        infoDailyCountry.setPositiveRate(positiveRate);
        infoDailyCountry.setNewTests(newTests);
        infoDailyCountry.setNewVaccinations(newVaccinations);

        // Lien entre Country et InfoDailyCountry
        Country country = countryDAO.findById(oneData[0]).get();
        linkCountryInfoDailyCountry(country, infoDailyCountry);

        // Réactualisation de Country
        LocalDate today = LocalDate.now();
        if (date.equals(today.minusDays(2))) {
            // Récupétation des données actuelles de Country
            float icuPatients = verificateur(oneData[17]);
            float hospPatients = verificateur(oneData[19]);
            float totalTests = verificateur(oneData[25]);
            float totalVaccinations = verificateur(oneData[34]);
            float fullyVaccinated = verificateur(oneData[36]);
            float stringencyIndex = verificateur(oneData[43]);
            float population = verificateur(oneData[44]);
            float gdp = verificateur(oneData[49]);

            // Ajout des données
            country.setTotalCases(totalCases);
            country.setTotalCases(totalCases);
            country.setTotalDeaths(totalDeaths);
            country.setIcuPatients(icuPatients);
            country.setHospPatients(hospPatients);
            country.setTotalTests(totalTests);
            country.setTotalVaccinations(totalVaccinations);
            country.setFullyVaccinated(fullyVaccinated);
            country.setStringencyIndex(stringencyIndex);
            country.setPopulation(population);
            country.setGdp(gdp);
        }
        // Actualisation de Country
        countryDAO.save(country);

        // Return
        return infoDailyCountry;
    }
    
}
