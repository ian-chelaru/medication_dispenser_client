package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: MedicationService.proto")
public final class MedicationServiceGrpc {

  private MedicationServiceGrpc() {}

  public static final String SERVICE_NAME = "MedicationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.MedicationServiceOuterClass.MedicationRequest,
      grpc.MedicationServiceOuterClass.MedicationResponse> getGetMedicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMedication",
      requestType = grpc.MedicationServiceOuterClass.MedicationRequest.class,
      responseType = grpc.MedicationServiceOuterClass.MedicationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.MedicationServiceOuterClass.MedicationRequest,
      grpc.MedicationServiceOuterClass.MedicationResponse> getGetMedicationMethod() {
    io.grpc.MethodDescriptor<grpc.MedicationServiceOuterClass.MedicationRequest, grpc.MedicationServiceOuterClass.MedicationResponse> getGetMedicationMethod;
    if ((getGetMedicationMethod = MedicationServiceGrpc.getGetMedicationMethod) == null) {
      synchronized (MedicationServiceGrpc.class) {
        if ((getGetMedicationMethod = MedicationServiceGrpc.getGetMedicationMethod) == null) {
          MedicationServiceGrpc.getGetMedicationMethod = getGetMedicationMethod = 
              io.grpc.MethodDescriptor.<grpc.MedicationServiceOuterClass.MedicationRequest, grpc.MedicationServiceOuterClass.MedicationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "MedicationService", "getMedication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.MedicationServiceOuterClass.MedicationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.MedicationServiceOuterClass.MedicationResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MedicationServiceMethodDescriptorSupplier("getMedication"))
                  .build();
          }
        }
     }
     return getGetMedicationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MedicationServiceStub newStub(io.grpc.Channel channel) {
    return new MedicationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MedicationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MedicationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MedicationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MedicationServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MedicationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getMedication(grpc.MedicationServiceOuterClass.MedicationRequest request,
        io.grpc.stub.StreamObserver<grpc.MedicationServiceOuterClass.MedicationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMedicationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMedicationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.MedicationServiceOuterClass.MedicationRequest,
                grpc.MedicationServiceOuterClass.MedicationResponse>(
                  this, METHODID_GET_MEDICATION)))
          .build();
    }
  }

  /**
   */
  public static final class MedicationServiceStub extends io.grpc.stub.AbstractStub<MedicationServiceStub> {
    private MedicationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationServiceStub(channel, callOptions);
    }

    /**
     */
    public void getMedication(grpc.MedicationServiceOuterClass.MedicationRequest request,
        io.grpc.stub.StreamObserver<grpc.MedicationServiceOuterClass.MedicationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMedicationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MedicationServiceBlockingStub extends io.grpc.stub.AbstractStub<MedicationServiceBlockingStub> {
    private MedicationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.MedicationServiceOuterClass.MedicationResponse getMedication(grpc.MedicationServiceOuterClass.MedicationRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMedicationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MedicationServiceFutureStub extends io.grpc.stub.AbstractStub<MedicationServiceFutureStub> {
    private MedicationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.MedicationServiceOuterClass.MedicationResponse> getMedication(
        grpc.MedicationServiceOuterClass.MedicationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMedicationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_MEDICATION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MedicationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MedicationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_MEDICATION:
          serviceImpl.getMedication((grpc.MedicationServiceOuterClass.MedicationRequest) request,
              (io.grpc.stub.StreamObserver<grpc.MedicationServiceOuterClass.MedicationResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MedicationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MedicationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.MedicationServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MedicationService");
    }
  }

  private static final class MedicationServiceFileDescriptorSupplier
      extends MedicationServiceBaseDescriptorSupplier {
    MedicationServiceFileDescriptorSupplier() {}
  }

  private static final class MedicationServiceMethodDescriptorSupplier
      extends MedicationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MedicationServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MedicationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MedicationServiceFileDescriptorSupplier())
              .addMethod(getGetMedicationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
