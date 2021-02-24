package com.yglong.study.designpattern.behavior.chainofresponsibility;

/**
 * 责任链模式
 * <p>
 * 对于一个请求有多个处理器的情况，可以使用责任链模式，将多个处理器构成一个处理器链
 * <p>
 * 简化调用者的使用
 */
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        Request req = new HttpRequest("test");
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler handlerC = new ConcreteHandlerC();
        handlerA.setNext(handlerB);
        handlerB.setNext(handlerC);

        handlerA.handle(req);
    }
}

interface Request {
    String getParameter();
}

class HttpRequest implements Request {
    private String parameter;

    public HttpRequest(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String getParameter() {
        return parameter;
    }
}

interface Handler {
    void setNext(Handler next);

    void handle(Request request);
}

abstract class AbstractHandler implements Handler {
    protected Handler next;


    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Request request) {
        if (next != null) {
            next.handle(request);
        }
    }
}

class ConcreteHandlerA extends AbstractHandler {

    @Override
    public void handle(Request request) {
        System.out.println("Concrete handler A handle request with parameter: " + request.getParameter());
        super.handle(request);
    }
}

class ConcreteHandlerB extends AbstractHandler {

    @Override
    public void handle(Request request) {
        System.out.println("Concrete handler B handle request with parameter: " + request.getParameter());
        super.handle(request);
    }
}

class ConcreteHandlerC extends AbstractHandler {
    @Override
    public void handle(Request request) {
        System.out.println("Concrete handler C handle request with parameter: " + request.getParameter());
        super.handle(request);
    }
}
