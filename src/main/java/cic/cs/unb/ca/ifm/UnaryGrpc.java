package cic.cs.unb.ca.ifm;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: send_data.proto")
public final class UnaryGrpc {

  private UnaryGrpc() {}

  public static final String SERVICE_NAME = "Unary";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SendData.Message,
      SendData.MessageResponse> getGetServerResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetServerResponse",
      requestType = SendData.Message.class,
      responseType = SendData.MessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SendData.Message,
      SendData.MessageResponse> getGetServerResponseMethod() {
    io.grpc.MethodDescriptor<SendData.Message, SendData.MessageResponse> getGetServerResponseMethod;
    if ((getGetServerResponseMethod = UnaryGrpc.getGetServerResponseMethod) == null) {
      synchronized (UnaryGrpc.class) {
        if ((getGetServerResponseMethod = UnaryGrpc.getGetServerResponseMethod) == null) {
          UnaryGrpc.getGetServerResponseMethod = getGetServerResponseMethod =
              io.grpc.MethodDescriptor.<SendData.Message, SendData.MessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetServerResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SendData.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SendData.MessageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UnaryMethodDescriptorSupplier("GetServerResponse"))
              .build();
        }
      }
    }
    return getGetServerResponseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UnaryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnaryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnaryStub>() {
        @Override
        public UnaryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnaryStub(channel, callOptions);
        }
      };
    return UnaryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UnaryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnaryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnaryBlockingStub>() {
        @Override
        public UnaryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnaryBlockingStub(channel, callOptions);
        }
      };
    return UnaryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UnaryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnaryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnaryFutureStub>() {
        @Override
        public UnaryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnaryFutureStub(channel, callOptions);
        }
      };
    return UnaryFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UnaryImplBase implements io.grpc.BindableService {

    /**
     */
    public void getServerResponse(SendData.Message request,
                                  io.grpc.stub.StreamObserver<SendData.MessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetServerResponseMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetServerResponseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SendData.Message,
                SendData.MessageResponse>(
                  this, METHODID_GET_SERVER_RESPONSE)))
          .build();
    }
  }

  /**
   */
  public static final class UnaryStub extends io.grpc.stub.AbstractAsyncStub<UnaryStub> {
    private UnaryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UnaryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnaryStub(channel, callOptions);
    }

    /**
     */
    public void getServerResponse(SendData.Message request,
                                  io.grpc.stub.StreamObserver<SendData.MessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetServerResponseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UnaryBlockingStub extends io.grpc.stub.AbstractBlockingStub<UnaryBlockingStub> {
    private UnaryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UnaryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnaryBlockingStub(channel, callOptions);
    }

    /**
     */
    public SendData.MessageResponse getServerResponse(SendData.Message request) {
      return blockingUnaryCall(
          getChannel(), getGetServerResponseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UnaryFutureStub extends io.grpc.stub.AbstractFutureStub<UnaryFutureStub> {
    private UnaryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UnaryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnaryFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SendData.MessageResponse> getServerResponse(
        SendData.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getGetServerResponseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SERVER_RESPONSE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UnaryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UnaryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SERVER_RESPONSE:
          serviceImpl.getServerResponse((SendData.Message) request,
              (io.grpc.stub.StreamObserver<SendData.MessageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UnaryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UnaryBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SendData.getDescriptor();

    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Unary");
    }
  }

  private static final class UnaryFileDescriptorSupplier
      extends UnaryBaseDescriptorSupplier {
    UnaryFileDescriptorSupplier() {}
  }

  private static final class UnaryMethodDescriptorSupplier
      extends UnaryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UnaryMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UnaryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UnaryFileDescriptorSupplier())
              .addMethod(getGetServerResponseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
