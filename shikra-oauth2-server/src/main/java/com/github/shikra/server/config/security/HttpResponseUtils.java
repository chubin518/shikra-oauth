package com.github.shikra.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpResponseUtils {
    public static void jsonWriter(HttpServletResponse response, Object content) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(mapper.writeValueAsString(content));
        } finally {
            if (null != writer) {
                writer.flush();
                writer.close();
            }
        }
    }
}
