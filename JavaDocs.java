import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class JavaDocs extends JFrame implements ActionListener{
    private Container pane;
    private JTextArea t;
    private JButton newFileButton, loadButton, saveButton, graphs, fonts;
    final JFileChooser fc = new JFileChooser();
    private JLabel fileName;
    private String text;
    private String path;

    
    public JavaDocs(){
	this.setTitle("JavaDocs");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	pane=this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

	//load button
	loadButton = new JButton("Load File");
	loadButton.addActionListener(this);

	//save button
	saveButton = new JButton("Save File");
	saveButton.addActionListener(this);

	//New File Button
	newFileButton = new JButton("New File");
	newFileButton.addActionListener(this);

	//File name
	fileName = new JLabel("File Name");

	//Graphs button
	graphs = new JButton("Show me graphy stuffs");

	//Fonts button
	fonts = new JButton("Fonts");
	fonts.addActionListener(this);

	//TextArea where you type things
	t = new JTextArea(
			  "This is your workspace!\n\n" +
			  "If you would like to open a text file, use the 'Load File' button!\n\n" +
			  "Otherwise, make a new file by pressing the 'New File' button.\n\n"+
			  "Please remember to save your work before exiting, we are not responsible for any lost text! :)\n"
			  );

	JPanel buttons = new JPanel();
	buttons.add(newFileButton);
	buttons.add(loadButton);
	buttons.add(saveButton);
	buttons.add(fileName);

	JPanel bottomButtons = new JPanel();
	bottomButtons.add(fonts);
	bottomButtons.add(graphs);

	pane.add (buttons);
	pane.add (t);
	pane.add (bottomButtons);
    }

    public void actionPerformed (ActionEvent e){

	if (e.getSource() == newFileButton){
	    String newFileName = JOptionPane.showInputDialog ("Please type in your new file's name.");
	    if(!(newFileName == null) && !(newFileName == "")){
		fileName.setText(newFileName+".txt");
		t.setText("");
	    }else{}
	}
	    
	
	if (e.getSource() == loadButton){
	    int returnVal = 0;
	    returnVal = fc.showOpenDialog(JavaDocs.this);

	    File file = null;
	    if (returnVal == JFileChooser.APPROVE_OPTION){
		file = fc.getSelectedFile();
		fileName.setText(file.getName());
		if (!(fileName.getText()).endsWith(".txt")){
		    JOptionPane.showMessageDialog (null, "File not loaded. Please only load files that end in '.txt'.", "Loading Failed",JOptionPane.PLAIN_MESSAGE);
		}else{	
		    text="";
		    try{
			Scanner s = new Scanner(file);
			while (s.hasNextLine()){
			    text+=s.nextLine()+"\n";
			}
			t.setText(text+"\n");
		    }catch(FileNotFoundException error){
			System.out.println("File '"+file+"' not found!");
			//This should never happen
		    }
		}
	    }else {}
	    //When Cancel button or X is pressed, nothing happens
	}
	

	if (e.getSource() == saveButton){
	    if (!(fileName.getText()).endsWith(".txt")){
		JOptionPane.showMessageDialog (null, "File not saved. Please only save files that end in '.txt'.", "Saving Failed",JOptionPane.PLAIN_MESSAGE);
	    }
	    else{
		try{	
		    FileWriter w = new FileWriter(fileName.getText());
		    text = t.getText();
		    w.write(text);
		    w.close();
		    JOptionPane.showMessageDialog (null, "Your file has been saved!", "File Saved",JOptionPane.PLAIN_MESSAGE);
		}catch(IOException error){
		    System.out.println(error);
		}
	    }
	}

	if (e.getSource() == fonts){
	    String[] choices = {"Times New Roman", "Comic Sans MS"};
	    Object s = JOptionPane.showInputDialog(null, "Choose your preferred font", "Fonts", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
	    if (s!=null){
		Font newFont = new Font(s.toString(), Font.PLAIN, (t.getFont()).getSize());
		t.setFont(newFont);
	    }else{}
	}
	
    }

}
