package fakeNews;

import javax.swing.JFrame;

//import fakeNews.vista.AltaModificacionFakeNews;
//import fakeNews.vista.Consulta;
//import fakeNews.vista.AltaModificacionRefutador;
//import fakeNews.vista.ConsultaRefutador;
import fakeNews.vista.Pantalla;


public class App {

	public static void main(String[] args) {
		JFrame marco = new JFrame();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(0,0, 2000, 800);
		marco.setVisible(true);
		
		//marco.setContentPane(new AltaModificacionFakeNews(null));
		//marco.setContentPane(new Consulta());
		//marco.setContentPane(new AltaModificacionRefutador(null));
		marco.setContentPane(new Pantalla());



		marco.validate();
		
	}

}
