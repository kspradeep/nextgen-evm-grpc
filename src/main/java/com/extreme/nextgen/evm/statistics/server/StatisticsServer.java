package com.extreme.nextgen.evm.statistics.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class StatisticsServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello nextgen evm gRPC");

        // plaintext server
        Server server = ServerBuilder.forPort(50056)
                 .addService(new StatisticsServerImpl())
                .build();


        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }

}
