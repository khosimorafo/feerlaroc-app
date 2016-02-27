package com.feerlaroc.core.converter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.ToJson;

import java.io.IOException;

public  class AnInterfaceAdapter {
    @ToJson
    public void write(JsonWriter jsonWriter, AnInterface anInterface) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("name").value(anInterface.getName());
        jsonWriter.endObject();
    }

    @FromJson
    public AnInterface read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();

        String name = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "name":
                    name = jsonReader.nextString();
                    break;
            }
        }

        jsonReader.endObject();
        return new AnImplementation(name);
    }

    interface AnInterface {
        String getName();
    }

    static class AnImplementation implements AnInterface {
        private final String theName;

        AnImplementation(String name) {
            theName = name;
        }

        @Override public String getName() {
            return theName;
        }
    }
}