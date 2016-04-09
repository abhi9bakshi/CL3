#server side code

import socket
import sha1code	
#create a server side socket object
serversocket =socket.socket(socket.AF_INET,socket.SOCK_STREAM)	#create a unix domain stream socket

#get the hostname of local machine
hostname= '127.0.0.1'
print hostname
port = 12345	#port to which server will be listening
#bind the socket to port
serversocket.bind((hostname, port))	#bind serverside socket to host and port

#set max no. of connections to listen
serversocket.listen(10)

while True:
	#establish a connection by accepting connection from client
	clientsocket,addr =serversocket.accept() #clientsocket is socket connected with client	, addr corresponds to address of client
	print("successfully accepted connection from %s" % str(addr))	#print address of client from whom connection is recieved

	#receive 1024 bytes of message from client	
	plain_text =clientsocket.recv(1024)	
	message_digest=sha1code.sha1(plain_text)		#get message digest from sha1 algorithm
	clientsocket.send(message_digest)	#send back message digest
	clientsocket.close()	#close client side socket

