package fakeNews.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fakeNews.dao.FakeNewsDAO;
import fakeNews.dao.RefutacionDAO;
import fakeNews.dao.RefutadorDAO;
import fakeNews.modelo.FakeNews;
import fakeNews.modelo.Refutacion;

import javax.swing.JRadioButton;

public class AltaModificacionRefutacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField FakeFecha;
	private JTextField FakeFuentes;
	boolean esOficial;

	/**
	 * Create the frame.
	 * 
	 * @param fakeSeleccionada
	 */
	public AltaModificacionRefutacion(int legajoSeleccionado) {

		setLayout(null);

		JLabel TituloFake = new JLabel("Titulo de la fake news");
		TituloFake.setForeground(new Color(255, 0, 128));
		TituloFake.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 21));
		TituloFake.setBounds(20, 70, 221, 41);
		add(TituloFake);

		JLabel tituloPantalla = new JLabel("Refutacion de Fakenews");
		tituloPantalla.setForeground(new Color(255, 128, 192));
		tituloPantalla.setBackground(new Color(255, 128, 255));
		tituloPantalla.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 50));
		tituloPantalla.setBounds(20, 11, 593, 45);
		add(tituloPantalla);

		JLabel RefutadorFake = new JLabel("Refutador");
		RefutadorFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		RefutadorFake.setForeground(new Color(255, 0, 128));
		RefutadorFake.setBounds(20, 122, 119, 29);
		add(RefutadorFake);

		JButton botonSiguiente1 = new JButton("Tablas Fake");
		botonSiguiente1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero
				// modificar.
				marco.setContentPane(new Consulta());
				marco.validate();
			}
		});
		botonSiguiente1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 11));
		botonSiguiente1.setForeground(new Color(255, 0, 128));
		botonSiguiente1.setBackground(new Color(255, 128, 255));
		botonSiguiente1.setBounds(322, 374, 108, 22);
		add(botonSiguiente1);

		JLabel Fuentefake = new JLabel("Fuentes");
		Fuentefake.setFont(new Font("Tahoma", Font.BOLD, 20));
		Fuentefake.setForeground(new Color(255, 0, 128));
		Fuentefake.setBounds(20, 212, 97, 22);
		add(Fuentefake);

		FakeFecha = new JTextField();
		FakeFecha.setBounds(184, 180, 280, 20);
		add(FakeFecha);
		FakeFecha.setColumns(10);

		JLabel fechaFake = new JLabel("Ingresa fecha");
		fechaFake.setForeground(new Color(255, 0, 128));
		fechaFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		fechaFake.setBackground(new Color(255, 0, 128));
		fechaFake.setBounds(20, 172, 154, 29);
		add(fechaFake);

		FakeFuentes = new JTextField();
		FakeFuentes.setBounds(184, 217, 280, 20);
		add(FakeFuentes);
		FakeFuentes.setColumns(10);

		JComboBox<String> boxRefutador = new JComboBox<>();
		boxRefutador.setToolTipText("");
		boxRefutador.setMaximumRowCount(3);
		boxRefutador.setBounds(149, 129, 280, 22);
		add(boxRefutador);
		RefutadorDAO rDao = new RefutadorDAO();
    	for (String refutador : rDao.traerTodas()) {
			boxRefutador.addItem(refutador);
			
		}
		

		JLabel lblesOrganismoOficial = new JLabel("¿Es organismo Oficial?");
		lblesOrganismoOficial.setForeground(new Color(255, 0, 128));
		lblesOrganismoOficial.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblesOrganismoOficial.setBounds(20, 263, 232, 22);
		add(lblesOrganismoOficial);

		JRadioButton NORadioButton = new JRadioButton("no");
		NORadioButton.setBounds(269, 267, 42, 23);
		add(NORadioButton);

		JRadioButton SIRadioButton_1 = new JRadioButton("si");
		SIRadioButton_1.setBounds(336, 267, 42, 23);
		add(SIRadioButton_1);

		ButtonGroup grupoOficial = new ButtonGroup();
		grupoOficial.add(SIRadioButton_1);
		grupoOficial.add(NORadioButton);

		/*JLabel titulo = new JLabel("");
		titulo.setForeground(new Color(255, 128, 192));
		titulo.setText(fakeSeleccionada.getTitulo());
		titulo.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 20));
		titulo.setBackground(new Color(255, 128, 255));
		titulo.setBounds(281, 67, 554, 45);
		add(titulo);*/
		esOficial = SIRadioButton_1.isSelected();
		

		JButton btnNewRefutador = new JButton("Crear Refutador");
		btnNewRefutador.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero
				// modificar.
				marco.setContentPane(new AltaModificacionRefutador(null));
				marco.validate();
			}
		});
		btnNewRefutador.setBounds(436, 129, 129, 23);
		add(btnNewRefutador);
		JButton Guardar = new JButton("Guardar");
		Guardar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {

	            Refutacion refutacion = new Refutacion();
	            refutacion.setRefutador(boxRefutador.getSelectedItem().toString());
	            refutacion.setOrganismo_oficial(SIRadioButton_1.isSelected() ? "si" : "no");
	            refutacion.setFuentes(FakeFuentes.getText().trim());
	            refutacion.setFecha(FakeFecha.getText().trim());

	            RefutacionDAO rDao = new RefutacionDAO();
	            int idRefutacion = rDao.guardar(refutacion);

	            FakeNewsDAO fDao = new FakeNewsDAO();
	            fDao.actualizarIdRefutacionPorLegajo(legajoSeleccionado, idRefutacion);
	        }
	    });


		Guardar.setBounds(20, 375, 89, 23);
		add(Guardar);

	}
}
