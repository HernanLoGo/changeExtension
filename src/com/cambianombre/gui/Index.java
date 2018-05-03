package com.cambianombre.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Index {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Index() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 561, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSeleccionarRuta = new JButton("Seleccionar ruta");
		btnSeleccionarRuta.setBounds(226, 36, 132, 23);
		frame.getContentPane().add(btnSeleccionarRuta);
	}
}
