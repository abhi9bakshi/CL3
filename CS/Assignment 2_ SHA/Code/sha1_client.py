#client side code

import sha1code	#import the code for sha1 algorithm 
import socket
clientsocket=socket.socket(socket.AF_INET,socket.SOCK_STREAM)	#create a unix domain, stream type client side socket

#get hostname of local machine
#host = socket.gethostname()
host = '127.0.0.1'	#localhost
#assign port no.
port = 12345

clientsocket.connect((host, port))	#connect to localhost,port no. 

clientsocket.send("thisisaplaintext")	# send message to be encrypted to server
message_from_server=clientsocket.recv(1024)	#get message digest from server
message_digest=sha1code.sha1("thisisaplaintext")		#compute message digest locally

if(message_digest == message_from_server):
	print("success")
else:
	print("failure")
