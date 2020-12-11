package Net;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Created by Defias on 2020/08.
 * Description: ECHO TCP GUI客户端
 */
public class Test_TCPEchoClientGUI extends JFrame {

    public static void main(String[] args) {
        String server = "localhost"; // Server name or IP address
        int servPort = 4442;

        JFrame frame = new Test_TCPEchoClientGUI(server, servPort);
        frame.setVisible(true);
    }

    public Test_TCPEchoClientGUI(String server, int servPort) {
        super("TCP Echo Client"); // Set the window title
        setSize(300, 300); // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set echo send text field
        final JTextField echoSend = new JTextField();
        getContentPane().add(echoSend, "South");

        // Set echo replay text area
        final JTextArea echoReply = new JTextArea(8, 20);
        echoReply.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(echoReply);
        getContentPane().add(scrollPane, "Center");

        final Socket socket; // Client socket
        final DataInputStream in; // Socket input stream
        final OutputStream out; // Socket output stream
        try {
            // Create socket and fetch I/O streams
            socket = new Socket(server, servPort);

            in = new DataInputStream(socket.getInputStream());
            out = socket.getOutputStream();
            echoSend.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (event.getSource() == echoSend) {
                        byte[] byteBuffer = echoSend.getText().getBytes();
                        try {
                            out.write(byteBuffer);
                            in.readFully(byteBuffer);
                            echoReply.append(new String(byteBuffer) + "\n");
                            echoSend.setText("");
                        } catch (IOException e) {
                            echoReply.append("ERROR\n");
                        }
                    }
                }
            });

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        socket.close();
                    } catch (Exception exception) {
                    }
                    System.exit(0);
                }
            });
        } catch (IOException exception) {
            echoReply.append(exception.toString() + "\n");
        }
    }
}