package com.extreme.nextgen.evm.statistics.client;

import com.proto.statistics.Statistics;
import com.proto.statistics.StatisticsRequest;
import com.proto.statistics.StatisticsResponse;
import com.proto.statistics.StatisticsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StatisticsClientSpringBoot {

    private StatisticsServiceGrpc.StatisticsServiceBlockingStub statisticsClient;
    @PostConstruct
    private void init(){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50056).usePlaintext().build();
        // created a statistics service client (blocking - synchronous)
        statisticsClient=StatisticsServiceGrpc.newBlockingStub(channel);
    }
    public   String doUnaryCall(){

        // Unary
        // created a protocol buffer statistics message

        Statistics statistics=Statistics.newBuilder().setFirstName("Pradeep").setLastName("Shanthaveerappa").build();
        // do the same for a StatisticsRequest
        StatisticsRequest statisticsRequest=StatisticsRequest.newBuilder().setStatistics(statistics).build();

        // call the RPC and get back a StatisticsResponse (protocol buffers)
        StatisticsResponse statisticsResponse=statisticsClient.statistics(statisticsRequest);
        System.out.println(statisticsResponse.getResult());
        return statisticsResponse.getResult();
    }

}
