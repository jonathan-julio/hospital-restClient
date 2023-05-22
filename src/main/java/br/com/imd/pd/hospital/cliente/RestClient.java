package br.com.imd.pd.hospital.cliente;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.imd.pd.hospital.cliente.utils.HttpUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "RestClient")
public class RestClient implements Runnable {
    
    @Option(names = {"-u", "--url"}, description = "URL")
    private String url;
    
    @Option(names = {"-m", "--method"}, description = "HTTP Method")
    private String method;

    @Option(names = {"-p", "--payload"}, description = "Resquest Payload")
    private String payload;

    private Map<String, String> headers;

    public RestClient() {
        this.initHeaders();
    }  

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new RestClient()).execute(args); 
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String response = HttpUtils.post(this.url, headers, this.payload);

            JsonElement jsonElement = JsonParser.parseString(response);
            JsonObject object = jsonElement.getAsJsonObject();
            String prettyResponde = gson.toJson(object);
            System.out.println(prettyResponde);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initHeaders() {
        this.headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
    }

    private void debug() {
        System.out.println(this.method);
        System.out.println(this.url);
        System.out.println(this.payload);
    } 
}
