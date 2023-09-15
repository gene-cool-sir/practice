package com.practice.grpcservice.grpc;

import com.sportstracker.apiserver.grpc.AuthServiceGrpc;
import com.sportstracker.apiserver.grpc.UsernameAuthRequest;
import com.sportstracker.apiserver.grpc.UsernameAuthResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GrpcAuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase{

    @Override
    public void getUsername(UsernameAuthRequest request, StreamObserver<UsernameAuthResponse> responseObserver) {
        String sttAuthorization = request.getSTTAuthorization();
        System.out.println(sttAuthorization);

        responseObserver.onNext(UsernameAuthResponse.newBuilder().setAuthenticated(true).setUsername("hello, gene").build());

        responseObserver.onCompleted();
    }
}
