import java.io.IOException;
import java.net.Socket;

public class Main {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.56.1",2000);
			UserModel model = new UserModel(socket);
			GuiView view = new GuiView();
			UserController.setModel(model);
			UserController.setView(view);
			view.showMenu();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
