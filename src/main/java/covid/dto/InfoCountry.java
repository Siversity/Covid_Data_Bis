package covid.dto;

public interface InfoCountry {

    // Attributs
    String getCode_Country();
    String getName_Country();
    float getTotal_Cases();
    float getTotal_Deaths();
    float getIcu_Patients();
    float getHosp_Patients();
    float getTotal_Tests();
    float getTotal_Vaccinations();
    float getFully_Vaccinated();
    float getStringency_Index();
    float getPopulation();
    float getGdp();
}
