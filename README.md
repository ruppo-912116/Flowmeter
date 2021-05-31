# Intro
The CICFlowMeter is an open source tool that generates Biflows from pcap files, and extracts features from these flows.

CICFlowMeter is a network traffic flow generator available from here . It can be used to generate bidirectional flows, where the first packet determines the forward (source to destination) and backward (destination to source) directions, hence the statistical time-related features can be calculated separately in the forward and backward directions. Additional functionalities include, selecting features from the list of existing features, adding new features, and controlling the duration of flow timeout.

NOTE: TCP flows are usually terminated upon connection teardown (by FIN packet) while UDP flows are terminated by a flow timeout. The flow timeout value can be assigned arbitrarily by the individual scheme e.g., 600 seconds for both TCP and UDP.

For citation in your works and also understanding CICFlowMeter (formerly ISCXFlowMeter) completely, you can find below published paper:
Gerard Drapper Gil, Arash Habibi Lashkari, Mohammad Mamun, Ali A. Ghorbani, "Characterization of Encrypted and VPN Traffic Using Time-Related Features", In Proceedings of the 2nd International Conference on Information Systems Security and Privacy(ICISSP 2016) , pages 407-414, Rome , Italy.


----------------------------------------
## Install jnetpcap local repo

for linux, sudo is a prerequisite
```
//linux :at the pathtoproject/jnetpcap/linux/jnetpcap-1.4.r1425
//windows: at the pathtoproject/jnetpcap/win/jnetpcap-1.4.r1425
mvn install:install-file -Dfile=jnetpcap.jar -DgroupId=org.jnetpcap -DartifactId=jnetpcap -Dversion=1.4.1 -Dpackaging=jar
```

Add the proto file in src/main/proto

For dependencies refer to this link
https://medium.com/@intuting/implement-grpc-service-using-java-gradle-7a54258b60b8

open terminal

```
$ ./gradlew build
```


nga open a Terminal in the IDE
```
make sure the grpc server is up and running
//linux:
$ sudo bash
$ gradle execCmd

//windows:
$ gradlew exeCmd
```
