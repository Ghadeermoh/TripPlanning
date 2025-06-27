package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import testdata.SearchData;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {
    public static SearchData loadSearchData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(
                    new File("src/test/resources/testdata/searchData.json"),
                    SearchData.class
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
