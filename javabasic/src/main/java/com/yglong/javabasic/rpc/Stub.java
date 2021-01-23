package com.yglong.javabasic.rpc;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Stub {

    public static <T> T getStub(Class<T> clazz) {

        InvocationHandler handler = (proxy, method, args) -> {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(9888));

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream dataOutputStream = new ObjectOutputStream(outputStream);
            dataOutputStream.writeObject(clazz);
            dataOutputStream.writeObject(method.getName());
            dataOutputStream.writeObject(method.getGenericParameterTypes());
            dataOutputStream.writeObject(args);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);
            return dataInputStream.readObject();
        };
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
    }
}
