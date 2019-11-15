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
    private MedicationTaken.Builder medicationTakenBuilder;
    private MedicationNotTaken.Builder medicationNotTakenBuilder;

    public GRPCClient()
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
        this.stub = MedicationServiceGrpc.newBlockingStub(channel);
        this.medicationRequest = MedicationRequest.newBuilder().build();
        this.medicationTakenBuilder = MedicationTaken.newBuilder();
        this.medicationNotTakenBuilder = MedicationNotTaken.newBuilder();
    }

    public List<Medication> getMedications()
    {
        MedicationResponse medicationResponse = stub.getMedication(medicationRequest);
        return medicationResponse.getMedicationsList();
    }

    public void sendMedicationTaken(String medicationName)
    {
        stub.taken(medicationTakenBuilder.setMedicationName(medicationName).build());
    }

    public void sendMedicationNotTaken(String medicationName)
    {
        stub.notTaken(medicationNotTakenBuilder.setMedicationName(medicationName).build());
    }

}
