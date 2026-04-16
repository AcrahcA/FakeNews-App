package fakeNews.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


import fakeNews.modelo.TraducionAudioVisual;
import fakeNews.dao.ProgramaDAO;
import fakeNews.dao.TraducionAudioVisualDAO;
import javax.swing.JComboBox;


public class AltaModificacionTraduccionAudioVisual extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField FieldNombreDelPrograma;
	private JTextField FieldMinutosDedicados;


	
	/**
	 * Create the frame.
	 * @param fakeNewsAModificar 
	 */
	public AltaModificacionTraduccionAudioVisual(TraducionAudioVisual traduccionAudioVisualAModificar) {
	
		setLayout(null);
		
		FieldNombreDelPrograma = new JTextField();
		FieldNombreDelPrograma.setBounds(20, 221, 280, 22);
		add(FieldNombreDelPrograma);
		FieldNombreDelPrograma.setColumns(10);
		
		JLabel NombreFake = new JLabel("Nombre Del Programa");
		NombreFake.setForeground(new Color(255, 0, 128));
		NombreFake.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 21));
		NombreFake.setBounds(20, 70, 256, 22);
		add(NombreFake);
		
		JLabel tituloPantalla = new JLabel("Divulgacion TA");
		tituloPantalla.setForeground(new Color(255, 128, 192));
		tituloPantalla.setBackground(new Color(255, 128, 255));
		tituloPantalla.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 50));
		tituloPantalla.setBounds(20, 11, 554, 45);
		add(tituloPantalla);
		
		JLabel NumeroDeInteraccionesFake = new JLabel("Minutos Dedicados");
		NumeroDeInteraccionesFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		NumeroDeInteraccionesFake.setForeground(new Color(255, 0, 128));
		NumeroDeInteraccionesFake.setBounds(20, 116, 240, 29);
		add(NumeroDeInteraccionesFake);
		
		FieldMinutosDedicados = new JTextField();
		FieldMinutosDedicados.setBounds(20, 144, 280, 20);
		add(FieldMinutosDedicados);
		FieldMinutosDedicados.setColumns(10);
		
		JLabel PlataformaFake = new JLabel("Rating");
		PlataformaFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		PlataformaFake.setForeground(new Color(255, 0, 128));
		PlataformaFake.setBounds(20, 171, 280, 29);
		add(PlataformaFake);
		
		
		
		
		JButton botonSiguiente1 = new JButton("Tablas Fake");
		botonSiguiente1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new Consulta());
				marco.validate();
			}
		});
		botonSiguiente1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 11));
		botonSiguiente1.setForeground(new Color(255, 0, 128));
		botonSiguiente1.setBackground(new Color(255, 128, 255));
		botonSiguiente1.setBounds(322, 374, 108, 22);
		add(botonSiguiente1);
		
		JButton Guardar = new JButton("Guardar");
		Guardar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        TraducionAudioVisual audioVisual = new TraducionAudioVisual();
		        audioVisual.setNombreDelPrograna(FieldNombreDelPrograma.getText());
		        audioVisual.setMinutosDedicados(FieldMinutosDedicados.getText());

		        TraducionAudioVisualDAO tDao = new TraducionAudioVisualDAO();
		        tDao.guardar(audioVisual);
		    }
		});

		Guardar.setBounds(20, 375, 89, 23);
		add(Guardar);
        
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new Consulta());
				marco.validate();
			}
		});
		btnVolver.setBounds(171, 375, 89, 23);
		add(btnVolver);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setMaximumRowCount(7);
		comboBox.setBounds(20, 398, 280, 22);
		add(comboBox);
	    ProgramaDAO pDao = new ProgramaDAO();
		for(String pla : pDao.traerTodas()) {
			comboBox.addItem(pla);
		}
		comboBox.setBounds(20, 91, 280, 22);
		add(comboBox);
		
		
		
		
	}
}
