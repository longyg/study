package com.yglong.contest;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.StopWatch;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SprintTest1 {
    public static void main(String[] args) {
        List<String> strings = SpringFactoriesLoader.loadFactoryNames(ApplicationContextInitializer.class, SpringFactoriesLoader.class.getClassLoader());
        for (String string : strings) {
            System.out.println(string);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());

        System.out.println(System.getProperty("java.awt.headless"));

        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext();
        System.out.println(context instanceof GenericApplicationContext);
        System.out.println(context instanceof DefaultResourceLoader);

        Set<String> set = new LinkedHashSet<>();
        set.add("hello");
        set.add("world");
        System.out.println(set.size());
        String[] arr = new String[0];
        System.out.println(arr.length);
        arr = set.toArray(arr);
        System.out.println(arr.length);
    }

}
