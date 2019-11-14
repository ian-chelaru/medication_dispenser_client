package grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

import static grpc.MedicationServiceGrpc.*;
import static grpc.MedicationServiceOuterClass.*;

public class GRPCClient
{
    private MedicationServiceBlockingStub stub;
    private MedicationRequest medicationRequest;

    public GRPCClient()
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
        this.stub = MedicationServiceGrpc.newBlockingStub(channel);
        this.medicationRequest = MedicationRequest.newBuilder().build();
    }

    public List<Medication> getMedications()
    {
        MedicationResponse medicationResponse = stub.getMedication(medicationRequest);
        return medicationResponse.getMedicationsList();
    }

}
