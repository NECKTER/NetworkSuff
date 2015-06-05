package Mainpackage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class StartFrame extends JFrame  {
StartFrame(){
	super("OUTLAW");
	setLayout(new BorderLayout());
	setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	StartPanel startpanel = new StartPanel();
	this.add(startpanel);
	pack();
	this.setVisible(true);
    
}
	

}
