/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrasoft.projects.yutokmessenger;
import java.io.IOException;
import java.net.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author lronald
 */
public class ChatRequestListenThread implements Runnable {
    Thread chatrequestlistenthread; 
    public ChatRequestListenThread() {
        chatrequestlistenthread = new Thread(this,"ChatRequestListenThread"); 
        chatrequestlistenthread.start(); 
    }
    @Override
    public void run() {
        while(true) {
            try { 
                ServerSocket ChatRequestListener = new ServerSocket(5001,100);
                //ChatRequestListener.bind(new InetSocketAddress("192.168.43.171",5001));
                Socket peer = ChatRequestListener.accept(); 
                RequestConfirmDialogWindow confirmdialog = new RequestConfirmDialogWindow(null,true,peer.getInetAddress().getHostAddress()); 
                confirmdialog.setVisible(true); 
                peer.close(); 
                ChatRequestListener.close(); 
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Please check network connections or services","Oops!",JOptionPane.ERROR_MESSAGE); 
                System.exit(1); 
            }
        }
    }
}
