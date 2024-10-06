package com.udea.labcicd.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class DataController {

    @GetMapping("/")
    public String healthCheck() {
        return "Aplicación funcionando correctamente";
    }

    @GetMapping("/version")
    public String version() {
        return "La versión actual es 1.0.0";
    }

    @GetMapping("/nations")
    public JsonNode getRandomNations() {
        var objectMapper = new ObjectMapper();
        var faker = new Faker(new Locale("en-US"));
        var nations = objectMapper.createArrayNode();
        for (var i = 0; i < 10; i++) {
            var nation = faker.nation();
            nations.add(objectMapper.createObjectNode()
                    .put("nationality", nation.nationality())
                    .put("capitalCity", nation.capitalCity())
                    .put("flag", nation.flag())
                    .put("language", nation.language()));
        }
        return nations;
    }

    @GetMapping("/currencies")
    public JsonNode getRandomCurrencies() {
        var objectMapper = new ObjectMapper();
        var faker = new Faker(new Locale("en-US"));
        var currencies = objectMapper.createArrayNode();
        for (var i = 0; i < 20; i++) {
            var currency = faker.currency();
            currencies.add(objectMapper.createObjectNode()
                    .put("name", currency.name())
                    .put("code", currency.code()));
        }
        return currencies;
    }

    @GetMapping("/aviation")
    public JsonNode getRandomAviation() {
        var objectMapper = new ObjectMapper();
        var faker = new Faker(new Locale("en-US"));
        var aviations = objectMapper.createArrayNode();
        for (var i = 0; i < 20; i++) {
            var nation = faker.aviation();
            aviations.add(objectMapper.createObjectNode()
                    .put("aircraft", nation.aircraft())
                    .put("airport", nation.airport())
                    .put("METAR", nation.METAR()));
        }
        return aviations;
    }

}