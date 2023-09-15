package com.practice.grpcservice.grpc;

import com.sportstracker.apiserver.grpc.AuthServiceGrpc;
import com.sportstracker.apiserver.grpc.UsernameAuthRequest;
import com.sportstracker.apiserver.grpc.UsernameAuthResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcChannelManager {

    public static void main(String[] args) {
        // 创建 gRPC 通道
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 6565)
                .usePlaintext() // 指定使用非加密连接，仅用于示例，请勿在生产环境中使用
                .build();

        // 使用通道创建 gRPC 客户端存根，执行 gRPC 调用
        AuthServiceGrpc.AuthServiceBlockingStub stub = AuthServiceGrpc.newBlockingStub(channel);
        UsernameAuthRequest authRequest = UsernameAuthRequest.newBuilder().setSTTAuthorization("bw9itu8jlut04cndbap3r1gwrp4xm83f").build();
        UsernameAuthResponse response = stub.getUsername(authRequest);

        // 处理响应
        System.out.println("Response: " + response);

        // 关闭通道
        channel.shutdown();
    }
}