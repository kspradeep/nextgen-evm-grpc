package com.extreme.nextgen.evm.statistics.server;

import com.proto.statistics.Statistics;
import com.proto.statistics.StatisticsRequest;
import com.proto.statistics.StatisticsResponse;
import com.proto.statistics.StatisticsServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class StatisticsServerImpl extends StatisticsServiceGrpc.StatisticsServiceImplBase {

    @Override
    public void statistics(StatisticsRequest request, StreamObserver<StatisticsResponse> responseObserver) {
        // extract the fields we need
        Statistics statistics = request.getStatistics();
        String firstName = statistics.getFirstName();
        String lastName = statistics.getLastName();
        // create the response
        String result = "Hello " + firstName + " " + lastName;
        StatisticsResponse response = StatisticsResponse.newBuilder().setResult(result).build();
        //send the response
        responseObserver.onNext(response);
        //complete the rpc call
        responseObserver.onCompleted();
    }

}
