package com.spring.boot.employee.service;

import com.spring.boot.employee.model.company.Company;
import com.spring.boot.employee.model.company.CompanyAddress;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            company.setFiscalIdentityNif(companyObject.getString("fiscalIdentityNif"));
            company.setFiscalIdentityStat(companyObject.getString("fiscalIdentityStat"));
            company.setFiscalIdentityRcs(companyObject.getString("fiscalIdentityRcs"));
            company.setAddress(this.getCompanyAddress(companyObject.getJsonObject("address")));
            company.setEmail(companyObject.getString("email"));
            company.setPhones(this.getPhones(companyObject.getJsonArray("phones")));
            company.setLogo(this.getLogo(companyObject.getJsonArray("logo")));
            return company;
        } catch (Exception e) {
            e.printStackTrace();
            return new Company(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    new CompanyAddress(),
                    null,
                    new ArrayList<>(),
                    null
            );
        }
    }

    public void save(Company company, byte[] logo, boolean isUpdate) throws FileNotFoundException {
        JsonObjectBuilder companyObjectBuilder = Json.createObjectBuilder();

        companyObjectBuilder
                .add("name", company.getName())
                .add("description", company.getDescription())
                .add("slogan", company.getSlogan())
                .add("fiscalIdentityNif", company.getFiscalIdentityNif())
                .add("fiscalIdentityStat", company.getFiscalIdentityStat())
                .add("fiscalIdentityRcs", company.getFiscalIdentityRcs())
                .add("address", this.saveAddress(company.getAddress()))
                .add("email", company.getEmail())
                .add("phones", this.savePhones(company.getPhones()))
                .add("logo", this.saveLogo(logo, isUpdate));

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

    private JsonObjectBuilder saveAddress(CompanyAddress address) {
        JsonObjectBuilder companyAddressObjectBuilder = Json.createObjectBuilder();
        companyAddressObjectBuilder
                .add("house", address.getHouse())
                .add("street", address.getStreet())
                .add("city", address.getCity())
                .add("zipCode", address.getZipCode());
        return companyAddressObjectBuilder;
    }

    private JsonArrayBuilder saveLogo(byte[] logo, boolean isUpdate) {
        JsonArrayBuilder companyLogoArrayBuilder = Json.createArrayBuilder();
        if (isUpdate) {
            if (logo.length == 0) {
                logo = this.get().getLogo();
            }
        }
        for (byte b : logo) {
            companyLogoArrayBuilder.add(b);
        }
        return companyLogoArrayBuilder;
    }

    private JsonArrayBuilder savePhones(List<String> phones) {
        JsonArrayBuilder companyLogoArrayBuilder = Json.createArrayBuilder();
        for (String phone : phones) {
            companyLogoArrayBuilder.add(phone);
        }
        return companyLogoArrayBuilder;
    }

    private byte[] getLogo(JsonArray logoByteJsonArray) {
        byte[] logoByteArray = new byte[logoByteJsonArray.size()];
        int index = 0;
        for (JsonValue value : logoByteJsonArray) {
            logoByteArray[index++] = Byte.parseByte(value.toString());
        }
        return logoByteArray;
    }

    private CompanyAddress getCompanyAddress(JsonObject addressCompanyObject) {
        return new CompanyAddress(
                addressCompanyObject.getString("house"),
                addressCompanyObject.getString("street"),
                addressCompanyObject.getString("city"),
                addressCompanyObject.getString("zipCode")
        );
    }

    private List<String> getPhones(JsonArray phonesListJsonArray) {
        List<String> phones = new ArrayList<>();
        for (JsonValue value : phonesListJsonArray) {
            phones.add(value.toString().replaceAll("\"", ""));
        }
        return phones;
    }
}
