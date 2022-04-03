
package Testes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Teste2 {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        String cmd="ping -c 2 192.168.0.107";
        Process myProcess = Runtime.getRuntime().exec(cmd);
        myProcess.waitFor();
        if(myProcess.exitValue() == 0) {
            System.out.println("pingou");
        } else {
            System.out.println("nao pingou");
        }
        */
        byte[] ipServer={ (byte)192 , (byte)168 , (byte)0, (byte)107 };
        byte[] ipAlt={ (byte)127 , (byte)0 , (byte)0, (byte)1 };
                    String conArduino="";
                    Socket s=null;  
                    InetAddress inet;
                    
                    String cmd="ping -c 2 192.168.0.107";
                    Process myProcess = Runtime.getRuntime().exec(cmd);
                    myProcess.waitFor();
                    if(myProcess.exitValue() == 0) {
                        inet = InetAddress.getByAddress(ipServer);
                        System.out.println("Enviando Ping Request para saber se está ativo " + inet);
                        s = new Socket(InetAddress.getByAddress(ipServer),5000);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Arduino não está ativo na rede!");
                        inet = InetAddress.getByAddress(ipAlt);
                        System.out.println("Enviando Ping Request para saber se está ativo " + inet);
                        s = new Socket(InetAddress.getByAddress(ipAlt),3306);
                    }

                    OutputStream sout=s.getOutputStream();
                    DataInputStream sin=new DataInputStream(s.getInputStream());
                    DataInputStream keyboard=new DataInputStream(System.in);
                    
        
        
        /*
       String[] str=new String[]{"xterm","-e","ping -c 2 192.168.0.107"};
        Process proc=Runtime.getRuntime().exec(str);
        proc.waitFor();
        int res=proc.exitValue();
        if(res==0){
            System.out.println("pingou com Sucesso");
        }else{
            System.out.println("erro");
        } 
        */
}
}
