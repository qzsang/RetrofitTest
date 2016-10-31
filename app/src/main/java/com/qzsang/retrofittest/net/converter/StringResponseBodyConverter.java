package com.qzsang.retrofittest.net.converter;

import com.qzsang.retrofittest.util.LogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        String str = value.string();
        LogUtil.e("StringResponseBodyConverter",str + "|");
        try {
            return str;
        } finally {
            value.close();
        }
    }
}