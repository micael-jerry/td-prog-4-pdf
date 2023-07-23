package com.spring.boot.service;

import com.spring.boot.model.Company;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyService {
    public Company get() {
        try {
            InputStream fls = new FileInputStream("company.json");
            JsonReader jsonReader = Json.createReader(fls);
            JsonObject companyObject = jsonReader.readObject();

            jsonReader.close();
            fls.close();

            Company company = new Company();
            company.setName(companyObject.getString("name"));
            company.setDescription(companyObject.getString("description"));
            company.setSlogan(companyObject.getString("slogan"));
            company.setLogo(this.getLogo(companyObject.getJsonArray("logo")));
            return company;
        } catch (IOException e) {
            e.printStackTrace();
            return new Company();
        }
    }

    public void save(Company company, byte[] logo, boolean isUpdate) throws FileNotFoundException {
        JsonObjectBuilder companyObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder companyLogoArrayBuilder = Json.createArrayBuilder();

        if (isUpdate) {
            if (logo.length == 0) {
                logo = this.get().getLogo();
            }
        }

        for (byte b : logo) {
            companyLogoArrayBuilder.add(b);
        }

        companyObjectBuilder
                .add("name", company.getName())
                .add("description", company.getDescription())
                .add("slogan", company.getSlogan())
                .add("logo", companyLogoArrayBuilder);

        JsonObject companyObject = companyObjectBuilder.build();

        OutputStream os = new FileOutputStream("company.json");
        JsonWriter jsonWriter = Json.createWriter(os);

        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory factory = Json.createWriterFactory(config);
        jsonWriter = factory.createWriter(os);

        jsonWriter.writeObject(companyObject);
        jsonWriter.close();
    }

    private byte[] getLogo(JsonArray logoByteJsonArray) {
        byte[] logoByteArray = new byte[logoByteJsonArray.size()];
        int index = 0;
        for (JsonValue value : logoByteJsonArray) {
            logoByteArray[index++] = Byte.parseByte(value.toString());
        }
        return logoByteArray;
    }
}
