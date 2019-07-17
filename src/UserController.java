import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static constants.Constants.*;


public class UserController {
	private static GuiView view;
	private static UserModel model;
	private static int count = 0;
	
	public static ActionListener createListener(String command) {
		switch(command) {
			case LOG_IN : return createLogListener(); 
			case REGISTER : return createRegListener();
			
			case ADMIN_MENU_FIRST : return createAdminFirstOptionListener();
			case ADMIN_MENU_SECOND : return createAdminSecondOptionListener();
			case ADMIN_MENU_THIRD : return createAdminThirdOptionListener();
			case ADMIN_MENU_FOURTH : return createAdminFourthOptionListener();
			
			case ADD : return createAddListener(); 
			case UPDATE : return createUpdateListener(count);
			case DELETE : return createDeleteListener(count);
			case CHANGE_TO_ADMIN : return createChangeTypeListener(count,1);
			case CHANGE_TO_CLIENT : return createChangeTypeListener(count,0);
			case GET_ORDERS : return createGetOrdersListener();
			case LOG_OUT : return createLogOutListener();
			case GO_BACK : return createBackListener();
			
			default : return createDefaultListener();
		}
	}
	
	private static ActionListener createLogListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> fields = view.getTextFields();
				int result = model.logUser(fields.get(0), fields.get(1));
				switch(result) {
					case FAIL: view.displayMessage("Error logging user!"); break;
					case CLIENT: view.displayMessage("Client logged successfully!"); view.clearFrame(); view.clientSubmenu(); break;
					case ADMIN : view.displayMessage("Admin logged successfully!"); view.clearFrame(); view.adminSubmenu(); break;
				}
			}
			
		};
	}
	
	private static ActionListener createRegListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> fields = view.getTextFields();
				int result = model.regUser(fields.get(0), fields.get(1));
				switch(result) {
					case FAIL : view.displayMessage("Failed to register user! Try another username."); break;
					case INVALID_PASSWORD : view.displayMessage("Invalid password! Make sure your password is atleast 8 characters long."); break;
					case SUCCESS :{
								view.displayMessage("Successfully registered!"); 
								model.setUserIsAdmin(false);
								view.clearFrame(); 
								view.clientSubmenu(); 
								break;
						}
				}
			}
			
		};
	}
	
	private static ActionListener createAdminFirstOptionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.clearFrame();
				model.sendMessage(MENU_FIRST);
				view.addProduct();
			}
			
		};
	}

	private static ActionListener createAdminSecondOptionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.clearFrame();
				model.sendMessage(MENU_SECOND);
				List<Object> products = (List<Object>) model.receiveObject();
				view.modifyProduct(products);
			}
			
		};
	}
	
	private static ActionListener createAdminThirdOptionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.clearFrame();
				model.sendMessage(MENU_THIRD);
				view.setMove(0);
				view.setDates();
			}
			
		};
	}
	
	private static ActionListener createAdminFourthOptionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.clearFrame();
				model.sendMessage(MENU_FOURTH);
				List<Object> usernames = (List<Object>) model.receiveObject();
				view.makeAdmin(usernames);
			}
			
		};
	}
	
	private static ActionListener createAddListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> fields = view.getTextFields();
				int result = model.addProduct(fields.get(0), fields.get(1), fields.get(2));
				view.displayMessage(result == FAIL ? "Failed to add product!" : "Product added successfully!");
			}
			
		};
	}
	
	private static ActionListener createUpdateListener(int index) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> fields = view.getTextFields();
				int result = model.updateProduct(fields.get(index),fields.get(index+1),fields.get(index+2),fields.get(index+3));
				view.displayMessage(result == 0 ? "Update failed!" : "Update successful!");
				view.clearFrame();
				count = 0;
				List<Object> products = (List<Object>) model.receiveObject();
				view.modifyProduct(products);
			}
			
		};
	}
	
	private static ActionListener createDeleteListener(int index) {
		count+=4;
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> fields = view.getTextFields();
				int result = model.deleteProduct(fields.get(index));
				view.displayMessage(result == 0 ? "Delete failed!" : "Delete successful!");
				view.clearFrame();
				count = 0;
				List<Object> products = (List<Object>) model.receiveObject();
				view.modifyProduct(products);
			}
			
		};
	}
	
	private static ActionListener createGetOrdersListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> fields = view.getTextFields();
				List<List<String>> carts;
				List<List<Object>> orders;
				if(!fields.get(0).isEmpty() && !fields.get(1).isEmpty()) {
					carts = model.getOrders(fields.get(0), fields.get(1));
					view.clearFrame();
				}
				else {
					view.displayMessage("You have to fill in both dates! Setting default values!");
					carts = model.getOrders(EMPTY_DATE, EMPTY_DATE);
					view.clearFrame();
				}
				if(carts.get(0).isEmpty()) {
					view.displayMessage("You have no orders yet!");
					view.adminSubmenu();
				}
				else {
					orders = (List<List<Object>>) model.receiveObject();
					view.viewOrders(orders, carts);
				}
				
				
			}
			
		};
	}
	
	private static ActionListener createChangeTypeListener(int index,int type) {
		if(type == 0) count++;
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> fields = view.getTextFields();
				int result = model.changeUserType(fields.get(index), type);
				view.displayMessage(result == 0 ? "User is already " + (type == 0 ? "a client!" : "an admin!") : "User changed to " + (type == 0 ? "client!" : "admin!"));
			}
			
		};
	}
	
	private static ActionListener createLogOutListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.clearFrame();
				view.displayMessage("User Logged Out!");
				model.sendMessage(LOG_OUT);
				view.showMenu();
			}
			
		};
	}
	
	private static ActionListener createBackListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.sendMessage(GO_BACK);
				count = 0;
				view.clearFrame();
				if(model.getUserIsAdmin()) {
					view.adminSubmenu();
				}
				else view.clientSubmenu();
			}
			
		};
	}
	
	public static ActionListener createPreviousListener(List<List<Object>> orders , List<List<String>> carts) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getMove() == 0) {
					view.displayMessage("No previous order!");
				}
				else {
					view.clearFrame();
					view.setMove(view.getMove() - 1);
					view.viewOrders(orders, carts);
				}
				
			}
			
		};
	}
	
	public static ActionListener createNextListener(List<List<Object>> orders , List<List<String>> carts) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getMove() == orders.size() - 1) {
					view.displayMessage("No next order!");
				}
				else {
					view.clearFrame();
					view.setMove(view.getMove() + 1);
					view.viewOrders(orders, carts);
				}
				
			}
			
		};
	}
	
	private static ActionListener createDefaultListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.displayMessage("Invalid command for listener");
			}
			
		};
	}

	public static GuiView getView() {
		return view;
	}

	public static void setView(GuiView view) {
		UserController.view = view;
	}

	public static UserModel getModel() {
		return model;
	}

	public static void setModel(UserModel model) {
		UserController.model = model;
	}
	

}
