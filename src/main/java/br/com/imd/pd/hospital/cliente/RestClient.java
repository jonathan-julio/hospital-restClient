package br.com.imd.pd.hospital.cliente;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.imd.pd.hospital.cliente.models.Hospital;
import br.com.imd.pd.hospital.cliente.models.HospitalImpl;
import br.com.imd.pd.hospital.cliente.models.Location;

public class RestClient {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8080/server/location";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String jsonBody = "{ \"latitude\": -5.8658099, \"longitude\": -35.2258257 }";

        String response = HttpUtils.post(url, headers, jsonBody);

         
        JsonElement jsonElement = JsonParser.parseString(response);
        JsonObject object = jsonElement.getAsJsonObject();

        String name = object.get("name").getAsString();
        int vacancies = object.get("vacancies").getAsInt();
        JsonElement locationElement = object.get("location");

        double latitude = locationElement.getAsJsonObject().get("latitude").getAsDouble();
        double longitude = locationElement.getAsJsonObject().get("longitude").getAsDouble();
        Location location = new Location(latitude, longitude);

        Hospital hospital = new HospitalImpl(name, vacancies, location);

        System.out.println("Nome do hospital: " + hospital.getName());
        System.out.println("Número de vagas disponíveis: " + hospital.getVacancies());
        System.out.println("Localização do hospital: Latitude = "+ hospital.getLocation().getLatitude() 
                    +"  Longitude = " + hospital.getLocation().getLongitude());

    }
}
