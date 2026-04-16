package fakeNews.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


import fakeNews.modelo.TraduccionesEscritas;
import fakeNews.dao.DiarioDAO;
import fakeNews.dao.TraduccionesEscritasDAO;


public class AltaModificacionTraduccionEscrita extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField FieldCantidadDeVisualizaciones;
	private JTextField FieldNombreNota;


	
	/**
	 * Create the frame.
	 * @param fakeNewsAModificar 
	 */
	public AltaModificacionTraduccionEscrita(TraduccionesEscritas traduccionEscritaAModificar) {
	
		setLayout(null);
		
		FieldCantidadDeVisualizaciones = new JTextField();
		FieldCantidadDeVisualizaciones.setBounds(20, 221, 280, 22);
		add(FieldCantidadDeVisualizaciones);
		FieldCantidadDeVisualizaciones.setColumns(10);
		
		JLabel NombreFake = new JLabel("Nombre Del Diario");
		NombreFake.setForeground(new Color(255, 0, 128));
		NombreFake.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 21));
		NombreFake.setBounds(20, 70, 256, 22);
		add(NombreFake);
		
		JLabel tituloPantalla = new JLabel("Divulgacion TE");
		tituloPantalla.setForeground(new Color(255, 128, 192));
		tituloPantalla.setBackground(new Color(255, 128, 255));
		tituloPantalla.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 50));
		tituloPantalla.setBounds(20, 11, 554, 45);
		add(tituloPantalla);
		
		JLabel NumeroDeInteraccionesFake = new JLabel("Nombre De La Nota");
		NumeroDeInteraccionesFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		NumeroDeInteraccionesFake.setForeground(new Color(255, 0, 128));
		NumeroDeInteraccionesFake.setBounds(20, 116, 240, 29);
		add(NumeroDeInteraccionesFake);
		
		FieldNombreNota = new JTextField();
		FieldNombreNota.setBounds(20, 144, 280, 20);
		add(FieldNombreNota);
		FieldNombreNota.setColumns(10);
		
		JLabel PlataformaFake = new JLabel("Visualizaciones");
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
		Guardar.setBounds(20, 375, 89, 23); // ← FALTABA ESTO
		Guardar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		    	TraduccionesEscritas escritas = new TraduccionesEscritas();
				escritas.setNombreDiario(FieldCantidadDeVisualizaciones.getText());
				escritas.setNombreNota(FieldNombreNota.getText());
                escritas.setCantidadDevisualizaciones(FieldCantidadDeVisualizaciones.getText());
				
				TraduccionesEscritasDAO fDao = new TraduccionesEscritasDAO();
				fDao.guardar(escritas);
		    }
		});
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
		
		JComboBox<String> BoxNombreDiario = new JComboBox<String>();
		BoxNombreDiario.setToolTipText("");
		BoxNombreDiario.setMaximumRowCount(7);
		BoxNombreDiario.setBounds(20, 398, 280, 22);
		add(BoxNombreDiario);
		DiarioDAO dDao = new DiarioDAO();
		for(String diario : dDao.traerTodas()) {
			BoxNombreDiario.addItem(diario);
		}
		BoxNombreDiario.setBounds(20, 91, 280, 22);
		add(BoxNombreDiario);
		
		
		
	}
}
