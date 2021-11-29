package com.yglong.contest;

import org.springframework.util.ClassUtils;

public class SpringTest {
    public static void main(String[] args) {
        System.out.println(ClassUtils.getDefaultClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassUtils.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());

        try {
            Class<?> aClass1 = Class.forName(SpringTest.class.getName());
            System.out.println(aClass1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class<?> aClass1 = Class.forName(SpringTest.class.getName(), false, SpringTest.class.getClassLoader());
            System.out.println(aClass1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<?> aClass = null;
        try {
            aClass = ClassUtils.forName("org.springframework.web.servlet.DispatcherServlet", null);
            System.out.println(aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Class<?> bClass = null;
        try {
            bClass = ClassUtils.forName("org.springframework.web.reactive.DispatcherHandler", null);
            System.out.println(bClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Class<?> cClass = null;
        try {
            cClass = ClassUtils.forName("org.glassfish.jersey.servlet.ServletContainer", null);
            System.out.println(cClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
            System.out.println(stackTrace.length);
            for (StackTraceElement stackTraceElement : stackTrace) {
                if ("main".equals(stackTraceElement.getMethodName())) {
                    System.out.println(stackTraceElement.getClassName());
                    Class<?> aClass1 = Class.forName(stackTraceElement.getClassName());
                    System.out.println(aClass1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
