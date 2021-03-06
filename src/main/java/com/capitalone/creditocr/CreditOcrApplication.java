package com.capitalone.creditocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditOcrApplication extends SpringBootServletInitializer {



    public static void main(String[] args) {
        new SpringApplicationBuilder(CreditOcrApplication.class)
                .properties("spring.config.name:application")
                .build()
                .run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
        return app.sources(CreditOcrApplication.class);
    }

    @Bean
    public ThreadLocal<ITesseract> provideTesseract() {
        return ThreadLocal.withInitial(() -> {
            ITesseract instance = new Tesseract();
            instance.setDatapath( System.getenv("TESSDATA_PREFIX") );
            instance.setLanguage("eng");

            return instance;
        });
    }
}
