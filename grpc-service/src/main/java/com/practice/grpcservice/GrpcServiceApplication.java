package com.practice.grpcservice;

import org.lognet.springboot.grpc.autoconfigure.GRpcAutoConfiguration;
import org.lognet.springboot.grpc.autoconfigure.GRpcValidationConfiguration;
import org.lognet.springboot.grpc.autoconfigure.NettyServerBuilderSelector;
import org.lognet.springboot.grpc.health.DefaultHealthStatusService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
		GRpcAutoConfiguration.class
})
public class GrpcServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcServiceApplication.class, args);
	}

}
