package com.yglong.javabasic.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompleteFutureDemo {
    static class CallRemoteApi implements Supplier<Long> {
        private String api;

        public CallRemoteApi(String api) {
            this.api = api;
        }

        @Override
        public Long get() {
            System.out.println("Calling remote api: " + api);
            Random random = new Random();
            return (long) (random.nextInt(5) * 100);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] apis = new String[]{"first api", "second api", "third api"};

        List<CompletableFuture<Long>> futures = Arrays.stream(apis).map(api -> {
            return CompletableFuture.supplyAsync(new CallRemoteApi(api)).thenApply(r -> {
                System.out.println("Finished api call: " + api + ": " + r);
                return r;
            });
        }).collect(Collectors.toList());

        CompletableFuture<List<Long>> allFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> {
                    System.out.println("all future then apply: " + v);
                    return futures.stream().map(f -> {
                        try {
                            return f.get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).collect(Collectors.toList());
                });

        List<Long> allResults = allFuture.get();
        allResults.forEach(System.out::println);
    }
}
