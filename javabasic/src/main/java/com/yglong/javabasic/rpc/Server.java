package com.yglong.javabasic.rpc;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ServerSocket serverSocket = new ServerSocket(9888);

        while (true) {
            Socket client = serverSocket.accept();

            InputStream inputStream = client.getInputStream();
            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);
            Class clazz = (Class) dataInputStream.readObject();
            String methodName = (String) dataInputStream.readObject();
            Class[] paramTypes = (Class[]) dataInputStream.readObject();
            Object[] a = (Object[]) dataInputStream.readObject();

            Method method = clazz.getMethod(methodName, paramTypes);
            Object result = method.invoke(clazz.newInstance(), a);

            OutputStream outputStream = client.getOutputStream();
            ObjectOutputStream dataOutputStream = new ObjectOutputStream(outputStream);
            dataOutputStream.writeObject(result);
        }
    }
}
