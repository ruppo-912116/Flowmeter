import socket, sys, time, argparse
import os
from struct import *


import grpc
import send_data_pb2_grpc as pb2_grpc
import send_data_pb2 as pb2


class GrpcClient(object):
    def __init__(self):
        self.host = 'localhost'
        self.server_port = 50052
        self.channel = grpc.insecure_channel(
            '{}:{}'.format(self.host, self.server_port))

        self.stub = pb2_grpc.UnaryStub(self.channel)

    def get_url(self,message):
        message = pb2.Message(message = message)

        return self.stub.GetServerResponse(message)


class Sniffer:
    def __init__(self):
        self.client = GrpcClient()
        # argument parser for console arguments
        parser = argparse.ArgumentParser(
            description='A packet sniffer. Collect packets until ctrl+c pressed or after -t seconds ')
        # optimal arguments
        parser.add_argument("-f", "--filename", type=str, help="pcap file name (don't give extension)",
                            default='capture')
        parser.add_argument("-nr", "--noraw", action='store_false', default=True,
                            help="No Raw mode, Stops printing raw packets")
        parser.add_argument("-t", "--time", type=int, default=0, help="Capture time in second")
        # store pares arguments
        self.args = parser.parse_args()
        # initialize stat variables
        self.start_time = time.time()
        self.start_time1 = time.time()
        self.ip = False
        self.packet_count = 0
        self.tcp_count = 0
        self.udp_count = 0
        # try capture all packets(linux) if not, capture ip packets(windows)
        # windows doesnt support socket.AF_PACKET so fallback to ip packets
        try:
            # create raw packet socket
            self.s = socket.socket(socket.AF_PACKET, socket.SOCK_RAW, socket.ntohs(3))
        except AttributeError:
            # set ip mode true
            self.ip = True
            # get the public network interface
            HOST = socket.gethostbyname(socket.gethostname())

            # create a raw utp socket and bind it to the public interface
            self.s = socket.socket(socket.AF_INET, socket.SOCK_RAW, socket.IPPROTO_IP)
            self.s.bind((HOST, 0))

            # Include IP headers
            self.s.setsockopt(socket.IPPROTO_IP, socket.IP_HDRINCL, 1)

            # receive all packages
            self.s.ioctl(socket.SIO_RCVALL, socket.RCVALL_ON)
        except socket.error as e:
            print('Socket could not be created.')
            print('    Error Code : {}'.format(getattr(e, 'errno', '?')))
            print('       Message : {}'.format(e))
            sys.exit()

    # starts capture loop, saves to pcap file and displays packet detail
    def capture_packets(self):
        while True:
            
            # Receive data from the socket, return value is a pair (bytes, address)
            # max buffer size for packets
            packet = self.s.recvfrom(65565)

            #print('packettttt','-------->',str(packet))

            # packet string from tuple
            packet = packet[0]

            #print("-------------Packet Start-------------")
            # print raw packet if noraw not given
            #if self.args.noraw:
                #print('Packet: {}'.format(str(packet)))
                

            # add packet to pcap file
            self.add_pcap(packet)

            
            self.start_time2 = time.time()    
            if self.start_time2 - self.start_time1 >= 10 :

                    self.client.get_url(message="run the pcabread")
                    os.remove("capture.pcap")
                    self.open_pcap(self.args.filename + '.pcap', (101 if self.ip else 1))
                    self.packet_count = self.packet_count +1
                    print('fileupdated ', self.packet_count )
                    self.start_time1 = time.time()
        
            self.control_time()

    # beatify mac addresses
    def mac_addr(self, a):
        # split address to 6 character
        pieces = (a[i] for i in range(6))
        # format to 00:00:00:00:00:00
        return '{:2x}:{:2x}:{:2x}:{:2x}:{:2x}:{:2x}'.format(*pieces)

    def control_time(self):
        if self.args.time > 0 and ((time.time() - self.start_time) > self.args.time):
            self.exit()
            sys.exit(1)

    def print_stats(self):
        stats = [
            'Captured packets: {}'.format(self.packet_count),
            'TCP Packets: {}'.format(self.tcp_count),
            'UDP Packets: {}'.format(self.udp_count),
            'Total Time: {}'.format(time.time() - self.start_time)
        ]
        print('---' + ' '.join(stats))

    def run(self):
        try:
            # open pcap if ip mode enabled link_type is 101, else 1(ethernet)
            self.open_pcap(self.args.filename + '.pcap', (101 if self.ip else 1))
            # start capturing
            self.capture_packets()
        except KeyboardInterrupt:  # exit on ctrl+c
            self.exit()

    def exit(self):
        # close file
        self.close_pcap()
        # print accumulated stats to screen
        self.print_stats()

    def empty_file(filename):
        open(filename, 'w').close()

    def open_pcap(self, filename, link_type=1):
        # open given filename write mode in binary
        self.pcap_file = open(filename, 'wb')
        # create pcap header and write file
        # header format (https://wiki.wireshark.org/Development/LibpcapFileFormat#Global_Header)
        # (magic_number,version_major,version_minor,thiszone,sigfigs,snaplen,network)
        # python representation
        # (unsigned int(1byte),unsigned short(2byte),unsigned short(2byte),int(4byte),unsigned int(1byte),unsigned int(1byte),unsigned int(1byte))
        self.pcap_file.write(pack('@ I H H i I I I', 0xa1b2c3d4, 2, 4, 0, 0, 65535, link_type))

    def add_pcap(self, data):
        ts_sec, ts_usec = map(int, str(time.time()).split('.'))
        length = len(data)
        # packet header format (https://wiki.wireshark.org/Development/LibpcapFileFormat#Record_.28Packet.29_Header)
        # (ts_sec,ts_usec,incl_len,orig_len)
        # python representation
        # (unsigned int(1byte),unsigned int(1byte),unsigned int(1byte),unsigned int(1byte))
        self.pcap_file.write(pack('@ I I I I', ts_sec, ts_usec, length, length))
        self.pcap_file.write(data)

    def close_pcap(self):
        # close file
        self.pcap_file.close()


if __name__ == '__main__':
    app = Sniffer()
    app.run()