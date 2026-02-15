package backend;

/* it creates an object of IMainController 
 */
public class MainControllerFactory{
	
	public IMainController createMainController() {
		IMainController imc = new MainController();
		return imc;
	}

}