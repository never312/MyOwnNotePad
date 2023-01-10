package NotePad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private final String NAME = "new file";
    private JTabbedPane tabs = new JTabbedPane();
    private JFileChooser fileChooser = new JFileChooser();

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }


    public Main(){

        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("Файл");
        JMenuItem newFile = new JMenuItem("Создать файл");
        JMenuItem openFile = new JMenuItem("Открыть файл");
        JMenuItem saveFile = new JMenuItem("Сохранить файл как");

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        menu.add(file);

        JFrame window = new JFrame("NotePad");
        window.setSize(800, 600);
        window.setJMenuBar(menu);
        window.add(tabs);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea text = new JTextArea();
                Scroll scroll = new Scroll(text, NAME);
                tabs.add(NAME, scroll);
            }
        });

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tabs.countComponents() != 0){

                    Scroll text = (Scroll) tabs.getSelectedComponent();
                    String output = text.getText();

                    fileChooser.showSaveDialog(null);

                    File file = fileChooser.getSelectedFile();

                    try{
                        FileOutputStream writer = new FileOutputStream(file);
                        writer.write(output.getBytes());
                    }catch (IOException eq) {eq.printStackTrace();};
                } else {
                    JOptionPane.showMessageDialog(tabs, "Нет открытых файлов");
                }
            }
        });

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    fileChooser.showOpenDialog(null);
                    File file = fileChooser.getSelectedFile();
                    String input = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

                    JTextArea text = new JTextArea(input);

                    Scroll scroll = new Scroll(text, file.getName());
                    tabs.addTab(file.getName(), scroll);

                } catch (IOException e1) {e1.printStackTrace();}

            }
        });
    }
}
