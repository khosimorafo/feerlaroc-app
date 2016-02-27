package com.feerlaroc.core.converter;

import com.squareup.moshi.JsonAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by root on 2016/02/27.
 */
public class FeerlarocResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final JsonAdapter<T> adapter;

    FeerlarocResponseBodyConverter(JsonAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        try {
            return adapter.fromJson(value.source());
        } finally {
            value.close();
        }
    }
}