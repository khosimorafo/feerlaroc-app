package com.feerlaroc.core.converter;

import com.squareup.moshi.JsonAdapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by root on 2016/02/27.
 */
public class FeerlarocRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private final JsonAdapter<T> adapter;

    FeerlarocRequestBodyConverter(JsonAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        adapter.toJson(buffer, value);
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}