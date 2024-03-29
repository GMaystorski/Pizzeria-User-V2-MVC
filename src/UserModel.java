import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import wrappers.ObjScanWrapper;
import static constants.Constants.*;



public class UserModel {
	private PrintStream printout;
	private ObjScanWrapper objScan;
	private boolean userIsAdmin;
	private List<String> cart;
	
	public UserModel(Socket socket) throws IOException {
		printout = new PrintStream(socket.getOutputStream());
		objScan = new ObjScanWrapper(new ObjectInputStream(socket.getInputStream()));
		cart = new ArrayList<>();
	}
	
	public int logUser(String username , String password) {
		 printout.println(username);
		 printout.println(password);
		 printout.println(LOG_IN);
		 List<Object> results =  (List<Object>) objScan.readObject();
		 if(!results.isEmpty()) {
			 setUserIsAdmin((boolean) results.get(2));
			 switch(results.get(2).toString()) {
			 	case "false" : return CLIENT;
			 	case "true" : return ADMIN; 
			 }
		 }
		 return FAIL;
	}
	
	public int regUser(String username , String password) {
		 printout.println(username);
		 printout.println(password);
		 if(password.length() < 8) {
			printout.println(String.valueOf(FAIL));
			objScan.readObject();
			return INVALID_PASSWORD;
		 }
		 else {
			 printout.println(REGISTER);
		 }
		 int result = (int) objScan.readObject();
		 return result;
	}
	
	public int addProduct(String name,String quantity,String price) {
		printout.println(ADD);
		printout.println(name);
		printout.println(quantity);
		printout.println(price);
		int result = (int) objScan.readObject();
		return result;
	}

	public int updateProduct(String name,String quantity,String price,String oldName) {

			printout.println(UPDATE);
			printout.println(name);
			printout.println(quantity);
			printout.println(price);
			printout.println(oldName);
			int result = (int) objScan.readObject();
			return result;
	}
	
	public int deleteProduct(String name) {
		printout.println(DELETE);
		printout.println(name);
		int result = (int) objScan.readObject();
		return result;
	}
	
	public int changeUserType(String username , int type) {
		printout.println(String.valueOf(type));
		printout.println(username);
		int result = (int) objScan.readObject();
		return result;
	}
	
	public List<List<String>> getOrders(String dateFrom , String dateTo){
		List<List<String>> carts = new ArrayList<>();
		if(dateFrom.equals(EMPTY_DATE) && dateTo.equals(EMPTY_DATE)) {
			printout.println(EMPTY_DATE);
		}
		else {
			printout.println(VALID_DATES);
			printout.println(dateFrom);
			printout.println(dateTo);
		}
		int orderSize = (int) objScan.readObject();
		if(orderSize == 0) {
			carts.add(new ArrayList<>());
			return carts;
		}
		else {
			for (int i = 0 ; i < orderSize ; i++) {
				carts.add((List<String>) objScan.readObject());
			}
		}
		return carts;
	}
	
	public int addToCart(String product,String price) {
		if(cart.contains(product)) {
			int temp = Integer.parseInt(cart.get(cart.indexOf(product)+1));
			temp++;
			cart.set(cart.indexOf(product)+1, String.valueOf(temp));
			return temp;
		}
		else {
			cart.add(product);
			cart.add(String.valueOf(1));
			cart.add(price);
			return 1;
		}
}

	public int removeFromCart(String product) {
		if(cart.contains(product)) {
			cart.remove(cart.indexOf(product)+1);
			cart.remove(cart.indexOf(product)+1);
			cart.remove(product);
			return SUCCESS;
		}
		else return FAIL;
	}
	
	public int createOrder(String location,List<String> cart) {
			printout.println(CREATE);
			printout.println(location);
			sendCart(cart);
			int result = (int)objScan.readObject();
			return result;
			
	}

	public void sendCart(List<String> cart) {
		printout.println(String.valueOf(cart.size()));
		for(String str : cart) {
			printout.println(str);
		}
	}
	
	
	public void sendMessage(String message) {
		printout.println(message);
	}	
	
	public Object receiveObject() {
		return objScan.readObject();
	}
	

	public boolean getUserIsAdmin() {
		return userIsAdmin;
	}
	

	public void setUserIsAdmin(boolean userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}

	public List<String> getCart() {
		return cart;
	}
}
