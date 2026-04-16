package fakeNews.vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class Pantalla extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * 
	 * @param marco
	 */
	public Pantalla() {
	setLayout(null);
		
	JButton btnRefutacion = new JButton("Consulta Fake News");
	btnRefutacion.setBounds(468, 29, 436, 510);
	add(btnRefutacion);
	btnRefutacion.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			marco.setContentPane(new Consulta());
			marco.validate();
			
		}
	});
	
	JButton Refutador = new JButton("Consulta Refutador");
	Refutador.setBounds(10, 29, 448, 510);
	add(Refutador);
	Refutador.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			marco.setContentPane(new ConsultaRefutador());
			marco.validate();
		}
	});
	
	
	
	
	
}
}
	



	


