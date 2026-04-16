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

import fakeNews.dao.PlataformaDAO;
import fakeNews.dao.RedSocialDAO;
import fakeNews.modelo.RedSocial;

public class AltaModificacionRedSocial extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField FieldNumeroDeInterraciones;
	private JTextField FieldUsuario;


	
	/**
	 * Create the frame.
	 * @param fakeNewsAModificar 
	 */
	public AltaModificacionRedSocial(RedSocial redSocialAModificar) {
	
		setLayout(null);
		
		JLabel NombreFake = new JLabel("Usuario");
		NombreFake.setForeground(new Color(255, 0, 128));
		NombreFake.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 21));
		NombreFake.setBounds(20, 70, 256, 22);
		add(NombreFake);
		
		JLabel tituloPantalla = new JLabel("Divulgacion");
		tituloPantalla.setForeground(new Color(255, 128, 192));
		tituloPantalla.setBackground(new Color(255, 128, 255));
		tituloPantalla.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 50));
		tituloPantalla.setBounds(20, 11, 554, 45);
		add(tituloPantalla);
		
		JLabel NumeroDeInteraccionesFake = new JLabel("Numero De Interacciones");
		NumeroDeInteraccionesFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		NumeroDeInteraccionesFake.setForeground(new Color(255, 0, 128));
		NumeroDeInteraccionesFake.setBounds(20, 116, 240, 29);
		add(NumeroDeInteraccionesFake);
		
		FieldNumeroDeInterraciones = new JTextField();
		FieldNumeroDeInterraciones.setBounds(20, 144, 280, 20);
		add(FieldNumeroDeInterraciones);
		FieldNumeroDeInterraciones.setColumns(10);
		
		JLabel PlataformaFake = new JLabel("Plataforma");
		PlataformaFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		PlataformaFake.setForeground(new Color(255, 0, 128));
		PlataformaFake.setBounds(20, 171, 280, 29);
		add(PlataformaFake);
		FieldUsuario = new JTextField();
		FieldUsuario.setBounds(20, 91, 280, 22);
		add(FieldUsuario);
		FieldUsuario.setColumns(10);
		
		
		
		
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

                RedSocial red = new RedSocial();
                red.setUsuario(FieldUsuario.getText());
                red.setNumeroDeInteracciones(FieldNumeroDeInterraciones.getText());
                red.setPlataforma(FieldUsuario.getText());

                RedSocialDAO rDao = new RedSocialDAO();
                rDao.guardar(red);
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
		
		JComboBox<String> BoxPlataforma = new JComboBox<String>();
		BoxPlataforma.setToolTipText("");
		BoxPlataforma.setMaximumRowCount(7);
		BoxPlataforma.setBounds(20, 204, 280, 22);
		add(BoxPlataforma);
		PlataformaDAO pDao = new PlataformaDAO();
		for(String pla : pDao.traerTodas()) {
			BoxPlataforma.addItem(pla);
		}
		BoxPlataforma.setBounds(20, 204, 280, 22);
		add(BoxPlataforma);
		
		
		
		
		
	}
}
