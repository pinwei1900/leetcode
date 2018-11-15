```protobuf
syntax = "proto2";

option java_package = "robin.message.protobuf";
option java_outer_classname = "RobinRequestProto";

message RobinRequest {
  required int32 lenth = 1;
  required int32 type = 2;
  optional bytes content = 3;
}
```

执行

```shell
protoc.exe --java_out=./protobuf robinRequest.proto
```

