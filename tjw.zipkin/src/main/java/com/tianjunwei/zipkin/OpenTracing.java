package com.tianjunwei.zipkin;

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;

public class OpenTracing {

	private final io.opentracing.Tracer tracer;

    private OpenTracing(io.opentracing.Tracer tracer) {
        this.tracer = tracer;
    }

    private void sayHello(String helloTo) {
        Span span = tracer.buildSpan("say-hello").startManual();
        String helloStr = String.format("Hello, %s!", helloTo);
        System.out.println(helloStr);

        span.finish();
    }

    public static void main(String[] args) {
       
        String helloTo = "hello tracing";
        new OpenTracing(GlobalTracer.get()).sayHello(helloTo);
    }
}
