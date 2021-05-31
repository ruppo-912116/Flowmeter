import grpc
from concurrent import futures
from datetime import datetime
from Mailing import sendingMail
import time
import sys

#python -m grpc_tools.protoc --proto_path=. ./unary.proto --python_out=. --grpc_python_out=.

import send_data_pb2_grpc as pb2_grpc
import send_data_pb2 as pb2

#ml packages
import pandas as pd
import numpy as np
from sklearn import model_selection
import pickle
class UnaryService(pb2_grpc.UnaryServicer):
    def __init__(self,*args,**kwargs):
        self.index =[52,44,73,74,17,18,60,47,46,58,19,16,45,48,11,70,43,8,67,34,40,22,42,23,27,28,21,71,24,29,7,26,13,4,41,69,9,10,57,20]
        self.model = pickle.load(open('40_Feat_Random_Forest_Final_Model_pcap_underSampled.pkl','rb'))
        self.list_of_pridiction=[]
        self.sender_email = sys.argv[1]
        self.rec_email = "rupanchaulagain.rc@gmail.com"
        self.password = sys.argv[2]
        self.prevMsgTime = datetime(2020,1, 1, 1, 1, 1, 1)
        self.detection = False
        self.prev= ''
        print("loaded")
    def GetServerResponse(self, request, context):
        message = request.message
        li  = []
        
        for i in message.split(','):
            li.append(i.strip())
        linp = np.array(li)
        nlinp = pd.DataFrame(linp[self.index].reshape(-1,1))
        prediction = self.model.predict(nlinp.T)
        self.list_of_pridiction.append(prediction[0])
        if len(self.list_of_pridiction)>=200:
            a=self.list_of_pridiction.count(0)
            b=self.list_of_pridiction.count(1)
            c=self.list_of_pridiction.count(2)
            d=self.list_of_pridiction.count(3)
            if a>=100:
                print("ddos attack")
                info = str(d) + ' normal ' + str(a) + " ddos " + str(b) + " portscan " + str(c) + " dos"
                msg = {'message':"Ddos attack detected","info": info}
                self.detection = True
                current_detection = "Ddos"
            if b>=100:
                info = str(d) + ' normal ' + str(a) + " ddos " + str(b) + " portscan " + str(c) + " dos"
                msg = {'message':"Portscan attack detected","info": info}
                print("portscan detect")
                self.detection = True
                current_detection = "Portscan"
            if c>=100:

                info = str(d) + ' normal ' + str(a) + " ddos " + str(b) + " portscan " + str(c) + " dos"
                msg = {'message':"Dos attack detected","info": info}
                print("dos attack detect")
                self.detection = True
                current_detection = "Dos"
            if d>=100:
                self.detection = False
                info = str(d) + ' normal ' + str(a) + " ddos " + str(b) + " portscan " + str(c) + " dos"
                print("normal","  It have ",a," ddos ",b," portscan",c," dos")
            print(d, ' normal ' ,a," ddos ",b," portscan",c," dos" )
            currentTime = datetime.utcnow()
            timeInMinutes = abs(currentTime - self.prevMsgTime).total_seconds()/60.0
            if (self.detection == True):
                if (timeInMinutes >=10):
                        self.prevMsgTime = datetime.utcnow()
                        sendingMail(self.sender_email, self.rec_email,self.password,msg)
                elif (timeInMinutes < 10):
                    if self.prev == '':
                        self.prevMsgTime = datetime.utcnow()
                        sendingMail(self.sender_email, self.rec_email,self.password,msg)
                        self.prev = current_detection
                    elif self.prev != current_detection:
                        self.prevMsgTime = datetime.utcnow()
                        sendingMail(self.sender_email, self.rec_email,self.password,msg)
                        self.prev = current_detection



            self.detection = False
            self.list_of_pridiction = []
        result = f'Hello I am up and running received "{message}" message from you'
        result  = {'message': result, 'received': True}

        return pb2.MessageResponse(**result)

def server():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    pb2_grpc.add_UnaryServicer_to_server(UnaryService(),server)
    server.add_insecure_port('[::]:50051')
    server.start()
    server.wait_for_termination()


if __name__ == "__main__":
    server()
