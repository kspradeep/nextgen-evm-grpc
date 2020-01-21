package com.extreme.nextgen.evm.statistics.client;

import com.proto.statistics.Statistics;
import com.proto.statistics.StatisticsRequest;
import com.proto.statistics.StatisticsResponse;
import com.proto.statistics.StatisticsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class StatisticsClientStandalone {

    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC statistics client");
        StatisticsClientStandalone main=new StatisticsClientStandalone();
        main.run();

    }

    private void run(){
        ManagedChannel  channel=ManagedChannelBuilder.forAddress("localhost",50056).usePlaintext().build();
        doUnaryCall(channel);
    }

    private  void doUnaryCall(ManagedChannel channel){
        // created a statistics service client (blocking - synchronous)
        StatisticsServiceGrpc.StatisticsServiceBlockingStub statisticsClient=StatisticsServiceGrpc.newBlockingStub(channel);

        // Unary
        // created a protocol buffer statistics message

        Statistics statistics=Statistics.newBuilder().setFirstName("Pradeep").setLastName("Shanthaveerappa").build();
        // do the same for a StatisticsRequest
        StatisticsRequest statisticsRequest=StatisticsRequest.newBuilder().setStatistics(statistics).build();

        // call the RPC and get back a StatisticsResponse (protocol buffers)
        StatisticsResponse statisticsResponse=statisticsClient.statistics(statisticsRequest);
        System.out.println(statisticsResponse.getResult());
    }
}
