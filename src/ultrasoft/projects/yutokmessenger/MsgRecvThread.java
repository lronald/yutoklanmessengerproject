/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrasoft.projects.yutokmessenger;
import java.net.*; 
import java.io.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lronald
 */
public class MsgRecvThread implements Runnable{
    // this thread basically listens on a particular socket for incoming
    // messages and outputs them in the main window. 
    Thread msgRecvThread; 
    MainWindow parentWindow; 
    Socket MsgRecvSocket; 
    InputStream MsgRecvInputStream; 
    
    public MsgRecvThread(MainWindow parentWin, Socket socket) throws IOException {
        parentWindow = parentWin; 
        MsgRecvSocket = socket; 
        MsgRecvInputStream = MsgRecvSocket.getInputStream(); 
        msgRecvThread = new Thread(this,"MsgRecvThread"); 
        msgRecvThread.start(); 
    }
    
    @Override
    public void run() {
        while(true) {
            try { 
                int msgbyte = MsgRecvInputStream.read();
                parentWindow.updateInbox(Byte.toString((byte)msgbyte)); 
            } catch (IOException ex) {
                // ignore the error for now !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }
    }
}
