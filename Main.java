package editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class Main  extends JFrame implements ActionListener {

    JFrame frame;
    JTextArea textarea;

    Main(){
        frame =new JFrame("Text Editor ");
        textarea=new JTextArea();
        frame.add(textarea);
        frame.setSize(800,800);
        frame.setVisible(true);
        JMenuBar menubar=new JMenuBar();
        JMenu filemenu=new JMenu("filemenu");
        JMenu editmenu=new JMenu("editmenu");

        JMenuItem openmenu=new JMenuItem("open file");
        JMenuItem savemenu=new JMenuItem("save file");
        JMenuItem printmenu=new JMenuItem("print file");
        JMenuItem newmenu=new JMenuItem("new file");
        savemenu.addActionListener(this);

        JMenuItem cutmenu=new JMenuItem("cut file");
        JMenuItem copymenu=new JMenuItem("copy file");
        JMenuItem pastemenu=new JMenuItem("paste file");
        JMenuItem closemenu=new JMenuItem("close file");

        frame.add(menubar);

        menubar.add(filemenu);
        menubar.add(editmenu);
        filemenu.add(openmenu);
        filemenu.add(savemenu);
        filemenu.add(printmenu);
        filemenu.add(newmenu);

        editmenu.add(cutmenu);
        editmenu.add(copymenu);
        editmenu.add(pastemenu);
        editmenu.add(closemenu);
        //action listner method
        cutmenu.addActionListener(this);
        copymenu.addActionListener(this);
        pastemenu.addActionListener(this);
        openmenu.addActionListener(this);
        newmenu.addActionListener(this);
        closemenu.addActionListener(this);
        frame.setJMenuBar(menubar);
        frame.show();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

Main f=new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if(s=="cut file"){
            textarea.cut();
        }else if(s=="copy file"){
            textarea.copy();
        }else if(s=="paste file"){
            textarea.paste();
        } else if (s=="save file") {
            JFileChooser jFileChooser=new JFileChooser("c:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer= null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(textarea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s=="print file"){
            try {
                textarea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s=="open file"){
            JFileChooser jFileChooser=new JFileChooser("c:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try{
                    String s1="",s2="";
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    textarea.setText(s2);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(s=="new file"){
            textarea.setText("");
        }else if(s=="close file"){
            frame.setVisible(false);
        }
    }
}