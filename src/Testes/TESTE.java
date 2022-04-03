
package Testes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TESTE {
    
    
            
   
    
    public static void main(String[] args) {
    
        byte[] ipServer={ (byte)192 , (byte)168 , (byte)0, (byte)107 };
        String conArduino="";
        Socket s;  
        InetAddress inet;
        String ip = "192.168.0.107";
        String teste;
        try {
            inet = InetAddress.getByAddress(ipServer);
           
            System.out.println("Enviando Ping Request para saber se está ativo " + inet);
            
            
            if(InetAddress.getByName(ip).isReachable(50)){
                s = new Socket(InetAddress.getByAddress(ipServer),5000);
                if(s.isConnected()){//verifica se o ip do arduino está ativo
                    //s = new Socket(InetAddress.getByAddress(ipServer),2000);
                    OutputStream sout=s.getOutputStream();
                    DataInputStream sin=new DataInputStream(s.getInputStream());
                    DataInputStream keyboard=new DataInputStream(System.in);
                   // String command,response ;
                    //sout.write("a".getBytes());
                    System.out.println("o IP esta na rede");
                }else{
                    System.out.println("o IP esta na rede");
                }
            }else{
                System.out.println("eitaaaaa");
            }
        } catch (UnknownHostException ex) {
            System.out.println("nao tem ip - erro no: inet = InetAddress.getByAddress(ipServer); ");
        } catch (IOException ex) {
            System.out.println("nao tem rota para o ip na rede - erro no: s = new Socket(InetAddress.getByAddress(ipServer),5000);");
        }
        

    }
}
