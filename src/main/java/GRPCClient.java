import grpc.MedicationServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import static grpc.MedicationServiceGrpc.*;
import static grpc.MedicationServiceOuterClass.*;

public class GRPCClient
{
    public static void main(String[] args)
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();

        MedicationServiceBlockingStub stub = MedicationServiceGrpc.newBlockingStub(channel);

        MedicationRequest medicationRequest = MedicationRequest.newBuilder().build();

        MedicationResponse medicationResponse = stub.getMedication(medicationRequest);

        System.out.println(medicationResponse.getMedicationsList());
    }
}
