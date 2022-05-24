package com.br.ages.orientacaobucalbackend.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

@Service
public class PdfService {

    public ByteArrayInputStream geraPdf(Map<String, ArrayList> map) throws DocumentException, IOException
    {
        String paragraphString = "";
        int id = 1;
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream pdf = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, pdf);
        } catch (DocumentException e) {
            throw new RuntimeException("Object creation error.", e);
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        for (var entry : map.entrySet()) {
            ArrayList alternativeData = entry.getValue();
            String question = entry.getKey();
            String text = alternativeData.get(0).toString();
            String criticalLevel = alternativeData.get(1).toString();
            paragraphString = id + ". " + question + " " + text + " [" + criticalLevel + "]";

            document.add(new Paragraph(paragraphString));
            id++;
        }
        document.close();

        return new ByteArrayInputStream(pdf.toByteArray());
    }

    public String getCriticalColour(Map<String, ArrayList> map) throws DocumentException, IOException {
        String level = "Verde";
        for (var entry : map.entrySet()) {
            ArrayList alternativeData = entry.getValue();
            String criticalLevel = alternativeData.get(1).toString();

            if (criticalLevel.equals("Vermelho") || level.equals("Vermelho"))
                level = "Vermelho";
            else if (criticalLevel.equals("Amarelo"))
                level = "Amarelo";
        }
        return level;
    }

    public static String convertJsonToCsv(JSONObject json) throws IOException {

        Document document = new Document(PageSize.A4);

        JsonNode jsonTree = new ObjectMapper().readTree(new File("src/main/JsonToCsvTestFolder/test.json"));

        Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File("src/main/JsonToCsvTestFolder/test.csv"), jsonTree);


                return "a";
    }
}
