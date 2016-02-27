package com.feerlaroc.core.converter;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by root on 2016/02/27.
 */
public class FeerlarocConverterFactory extends Converter.Factory {

    /** Create an instance using a default {@link Moshi} instance for conversion. */
    public static FeerlarocConverterFactory create() {
        return create(new Moshi.Builder().build());
    }

    /** Create an instance using {@code moshi} for conversion. */
    public static FeerlarocConverterFactory create(Moshi moshi) {
        return new FeerlarocConverterFactory(moshi, false);
    }

    private final Moshi moshi;
    private final boolean lenient;

    private FeerlarocConverterFactory(Moshi moshi, boolean lenient) {
        if (moshi == null) throw new NullPointerException("moshi == null");
        this.moshi = moshi;
        this.lenient = lenient;
    }

    /** Return a new factory which uses {@linkplain JsonAdapter#lenient() lenient} adapters. */
    public FeerlarocConverterFactory asLenient() {
        return new FeerlarocConverterFactory(moshi, true);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        JsonAdapter<?> adapter = moshi.adapter(type);
        if (lenient) {
            adapter = adapter.lenient();
        }
        return new FeerlarocResponseBodyConverter<>(adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        JsonAdapter<?> adapter = moshi.adapter(type);
        if (lenient) {
            adapter = adapter.lenient();
        }
        return new FeerlarocRequestBodyConverter<>(adapter);
    }

}
