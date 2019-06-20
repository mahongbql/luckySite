package com.luckysite.util;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * @author mahongbin
 * @date 2019/6/20 14:33
 * @Description
 */
public class HttpBodyReaderWrapper extends HttpServletRequestWrapper {

    private final String body;

    public HttpBodyReaderWrapper(HttpServletRequest request) throws UnsupportedEncodingException {
        super(request);
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = this.cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            }

        }

        this.body = new String(sb.toString().getBytes(), "UTF-8");
    }

    public InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        try {
            int len;
            while((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            byteArrayOutputStream.flush();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public String getBody() {
        return this.body;
    }
}
