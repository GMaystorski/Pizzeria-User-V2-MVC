import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import static constants.Constants.*;
public class GuiView {
	private List<JTextField> textFields = new ArrayList<>();
	private JFrame currFrame;
	private int move = 0;

	public void showMenu() {
		 JFrame frame = new JFrame();
		 currFrame = frame;
		 frame.setTitle("Login");
		 frame.setLayout(null);
		 frame.setSize(350,200);
		 
		 JLabel userLabel = new JLabel("Username: ");
		 userLabel.setBounds(60,30,80,15);
		 frame.add(userLabel);
		 
		 JTextField userField = new JTextField();
		 userField.setEditable(true);
		 userField.setBounds(150,25,150,25);
		 frame.add(userField);
		 textFields.add(userField);
		 
		 JLabel passLabel = new JLabel("Password: ");
		 passLabel.setBounds(60,80,80,15);
		 frame.add(passLabel);
		 
		 JPasswordField pass = new JPasswordField();
		 pass.setEditable(true);
		 pass.setBounds(150,75,150,25);
		 frame.add(pass);
		 textFields.add(pass);
		 
		 JButton log = new JButton("Login");
		 log.setBounds(80,120,80,20);
		 frame.add(log);
		 log.addActionListener(addListener(LOG_IN));
		 
		 JButton reg = new JButton("Register");
		 reg.setBounds(170,120,90,20);
		 frame.add(reg);
		 reg.addActionListener(addListener(REGISTER));
		 
		 frame.setVisible(true);
		 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void adminSubmenu() {
		
		JFrame frame = new JFrame();
		currFrame = frame;
		frame.setTitle("Menu");
		frame.setSize(300,300);
		
		//Create buttons and add to frame//
		JButton b1 = new JButton("Add new products");
		JButton b2 = new JButton("Manage existing products");
		JButton b3 = new JButton("View orders for a set period of time");
		JButton b4 = new JButton("Manage admin permissions");
		JButton b5 = new JButton("Log out");
		
		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		frame.add(b4);
		frame.add(b5);
		
		b1.addActionListener(addListener(ADMIN_MENU_FIRST));
		b2.addActionListener(addListener(ADMIN_MENU_SECOND));
		b3.addActionListener(addListener(ADMIN_MENU_THIRD));
		b4.addActionListener(addListener(ADMIN_MENU_FOURTH));
		b5.addActionListener(addListener(LOG_OUT));
		
		//Set frame attributes//
		frame.setLayout(new GridLayout(5,1));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void clientSubmenu() {
		
				//Create frame//
				JFrame frame = new JFrame();
				currFrame = frame;
				frame.setTitle("Menu");
				frame.setSize(300,300);
				
				//Create buttons and add to frame//
				JButton b1 = new JButton("Create an order");//Dobavi izvestqvane za ostavashto vreme do dostavka
				JButton b2 = new JButton("View all products");
				JButton b3 = new JButton("Repeat previous order");
				JButton b4 = new JButton("Log out");
				
				frame.add(b1);
				frame.add(b2);
				frame.add(b3);
				frame.add(b4);
				
				b1.addActionListener(addListener(CLIENT_MENU_FIRST));
				b2.addActionListener(addListener(CLIENT_MENU_SECOND));
				b3.addActionListener(addListener(CLIENT_MENU_THIRD));
				b4.addActionListener(addListener(LOG_OUT));
				
				
				//Set frame attributes//
				frame.setLayout(new GridLayout(4,1));
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
	}
	
	public void addProduct() {
		
		JFrame frame = new JFrame();
		currFrame = frame;
		frame.setTitle("Add products");
		frame.setSize(300,200);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(new Font(nameLabel.getFont().getFontName(),Font.PLAIN,15));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField nameField = new JTextField();
		textFields.add(nameField);
		frame.add(nameLabel);
		frame.add(nameField);
		
		JLabel quantLabel = new JLabel("Quantity: ");
		quantLabel.setFont(new Font(quantLabel.getFont().getFontName(),Font.PLAIN,15));
		quantLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField quantField = new JTextField();
		textFields.add(quantField);
		frame.add(quantLabel);
		frame.add(quantField);
		
		JLabel priceLabel = new JLabel("Price: ");
		priceLabel.setFont(new Font(priceLabel.getFont().getFontName(),Font.PLAIN,15));
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField priceField = new JTextField();
		textFields.add(priceField);
		frame.add(priceLabel);
		frame.add(priceField);
		
		JButton back = new JButton("Back");
		back.addActionListener(addListener(GO_BACK));
		frame.add(back);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(addListener(ADD));
		frame.add(addButton);
		
		frame.setLayout(new GridLayout(4,2));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void modifyProduct(List<Object> products) {
		
		JFrame frame = new JFrame("Manage products");
		currFrame = frame;
		frame.setSize(600, 50*(products.size())/3);
		frame.setLayout(new GridLayout(2+(products.size())/3,5));
		frame.add(new JLabel("Product name"));
		frame.add(new JLabel("Quantity"));
		frame.add(new JLabel("Price"));
		frame.add(new JLabel(" "));
		frame.add(new JLabel(" "));
		
		
		for(int i = 0 ; i<products.size() ; i+=3) {
			JTextField b1 = new JTextField(products.get(i).toString());
			textFields.add(b1);
			frame.add(b1);
			JTextField b2 = new JTextField(products.get(i+1).toString());
			textFields.add(b2);
			frame.add(b2);
			JTextField b3 = new JTextField(products.get(i+2).toString());
			textFields.add(b3);
			frame.add(b3);
			
			JTextField oldName = new JTextField(products.get(i).toString());
			oldName.setEditable(false);
			textFields.add(oldName);
			
			JButton up = new JButton("Update");
			up.addActionListener(addListener(UPDATE));
			frame.add(up);
			
			JButton delete = new JButton("Delete");
			delete.addActionListener(addListener(DELETE));
			
			frame.add(delete);
		}
		JButton back = new JButton("Back");
		back.addActionListener(addListener(GO_BACK));
		frame.add(back);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	

	public void makeAdmin(List<Object> usernames) {
		JFrame frame = new JFrame("Make Admin");
		currFrame = frame;
		frame.setSize(500, 50*usernames.size());
		frame.setLayout(new GridLayout(2+usernames.size(),3));
		frame.add(new JLabel("Username"));
		frame.add(new JLabel(" "));
		frame.add(new JLabel(" "));
		
		for(int i = 0 ; i < usernames.size(); i++) {
			JTextField t1 = new JTextField(usernames.get(i).toString());
			textFields.add(t1);
			t1.setEditable(false);
			frame.add(t1);
			JButton b1 = new JButton("Make Admin");
			b1.addActionListener(addListener(CHANGE_TO_ADMIN));
			frame.add(b1);
			JButton b2 = new JButton("Make Client");
			b2.addActionListener(addListener(CHANGE_TO_CLIENT));
			frame.add(b2);
		}
		JButton back = new JButton("Back");
		back.addActionListener(addListener(GO_BACK));
		frame.add(back);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void setDates() {
		JFrame frame = new JFrame("Set date interval");
		currFrame = frame;
		frame.setSize(300, 100);
		frame.setLayout(new GridLayout(2,3));
		
		frame.add(new JLabel("From: "));
		frame.add(new JLabel("To: "));
		frame.add(new JLabel(" "));
		
		JTextField text1 = new JTextField();
		textFields.add(text1);
		frame.add(text1);
		JTextField text2 = new JTextField();
		textFields.add(text2);
		frame.add(text2);
		
		JButton button = new JButton("Go");
		button.addActionListener(addListener(GET_ORDERS));
		
		frame.add(button);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void viewOrders(List<List<Object>> orders,List<List<String>> carts) {
		double sum = 0;
		JFrame frame = new JFrame("View Orders");
		currFrame = frame;		
		frame.setSize(500,600);
		frame.add(new JLabel("Username"));
		frame.add(new JLabel("Date"));
		frame.add(new JLabel("Location"));
		
		JTextField t1 = new JTextField(orders.get(move).get(1).toString());
		t1.setEditable(false);
		frame.add(t1);
			
		JTextField t2 = new JTextField(orders.get(move).get(2).toString());
		t2.setEditable(false);
		frame.add(t2);
			
		JTextField t3 = new JTextField(orders.get(move).get(3).toString());
		t3.setEditable(false);
		frame.add(t3);
		
		frame.add(new JLabel("Product name"));
		frame.add(new JLabel("Quantity in cart"));
		frame.add(new JLabel("Price"));
		
		
		for(int i = 0 ; i < carts.get(move).size() ; i+=3) {
			JTextField temp1 = new JTextField(carts.get(move).get(i));
			t1.setEditable(false);
			frame.add(temp1);
			
			JTextField temp2 = new JTextField(carts.get(move).get(i+1));
			t2.setEditable(false);
			frame.add(temp2);
			
			JTextField temp3 = new JTextField(carts.get(move).get(i+2));
			t3.setEditable(false);
			frame.add(temp3);
			
			double temp = Integer.parseInt(carts.get(move).get(i+1))*Double.parseDouble(carts.get(move).get(i+2));
			sum+=temp;
		}
		
		frame.add(new JLabel(" "));
		frame.add(new JLabel(" "));
		
		JTextField total = new JTextField("Total :" + sum);
		total.setEditable(false);
		frame.add(total);
		
		JButton back = new JButton("Back");
		back.addActionListener(addListener(GO_BACK));
		frame.add(back);
		
		JButton previous = new JButton("Previous");
		previous.addActionListener(UserController.createPreviousListener(orders, carts));
		JButton next = new JButton("Next");
		next.addActionListener(UserController.createNextListener(orders, carts));
		
	
		frame.add(previous);
		frame.add(next);
		
		frame.setLayout(new GridLayout(0,3));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void viewProducts(List<Object> products) {
		JFrame frame = new JFrame("View menu");
		currFrame = frame;
		frame.setSize(750,50*(products.size()/3));
		frame.setLayout(new GridLayout(2+(products.size()/3),5));
		
		JButton back = new JButton("Back");
		back.addActionListener(addListener(GO_BACK));
		frame.add(back);
		frame.add(new JLabel(" "));
		frame.add(new JLabel(" "));
		frame.add(new JLabel(" "));
		JButton toOrder = new JButton("Proceed to order");
		toOrder.addActionListener(addListener(PROCEED_TO_ORDER));
		frame.add(toOrder);
		
		frame.add(new JLabel("Product name"));
		frame.add(new JLabel("Quantity"));
		frame.add(new JLabel("Price(lv.)"));
		frame.add(new JLabel(" "));
		frame.add(new JLabel(" "));
		
		for(int i = 0 ; i < products.size() ; i+=3) {
			
			JTextField b1 = new JTextField(products.get(i).toString());
			textFields.add(b1);
			b1.setEditable(false);
			frame.add(b1);
			
			JTextField b2 = new JTextField(products.get(i+1).toString());
			textFields.add(b2);
			b2.setEditable(false);
			frame.add(b2);
			
			JTextField b3 = new JTextField(products.get(i+2).toString());
			textFields.add(b3);
			b3.setEditable(false);
			frame.add(b3);
			
			JButton add = new JButton("Add to Cart");
			add.addActionListener(addListener(ADD_TO_CART));
			frame.add(add);
			
			JButton remove = new JButton("Remove from Cart");
			remove.addActionListener(addListener(REMOVE_FROM_CART));
			frame.add(remove);	
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void enterLocation() {
		
		JFrame frame = new JFrame("Enter location");
		currFrame = frame;
		frame.setSize(300, 100);
		frame.setLayout(new GridLayout(1,2));
		
		JTextField t1 = new JTextField();
		textFields.add(t1);
		frame.add(t1);
		
		JButton b1 = new JButton("Confirm");
		frame.add(b1);
		b1.addActionListener(addListener(ENTER_LOCATION));
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void pressDummyButton(ActionListener listener) {
		JButton button = new JButton();
		button.addActionListener(listener);
		button.doClick();
	}
	
	public void createOrder(String location,List<String> cart) {
		double sum = 0;
		JFrame frame = new JFrame("Create an order");
		currFrame = frame;
		frame.setSize(600,100*cart.size()/3);
		frame.setLayout(new GridLayout(2+(cart.size()/3),4));
		frame.add(new JLabel("Product name"));
		frame.add(new JLabel("Quantity in cart"));
		frame.add(new JLabel("Price"));
		
		JButton button = new JButton("Order");
		button.addActionListener(UserController.createOrderListener(location,cart));
		frame.add(button);
		
		for(int i = 0 ; i < cart.size() ; i+=3) {
			JTextField t1 = new JTextField(cart.get(i));
			t1.setEditable(false);
			frame.add(t1);
			
			JTextField t2 = new JTextField(cart.get(i+1));
			t2.setEditable(false);
			frame.add(t2);
			
			JTextField t3 = new JTextField(cart.get(i+2));
			t3.setEditable(false);
			frame.add(t3);
			
			double temp = Integer.parseInt(cart.get(i+1))*Double.parseDouble(cart.get(i+2));
			JTextField t4 = new JTextField("Subtotal: " + temp);
			t4.setEditable(false);
			frame.add(t4);
			sum+=temp;
		}
		
		JButton back = new JButton("Back");
		back.addActionListener(addListener(GO_BACK));
		frame.add(back);
		
		frame.add(new JLabel(" "));
		frame.add(new JLabel(" "));
		
		JTextField total = new JTextField("Total: " + sum);
		total.setEditable(false);
		frame.add(total);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void reCreateOrder(List<List<String>> carts,String[] locations) {
		double sum = 0;
		JFrame frame = new JFrame("Repeat order");
		currFrame = frame;
		frame.setSize(600,100*carts.get(move).size()/3);
		frame.setLayout(new GridLayout(2+(carts.get(move).size()/3),4));
		frame.add(new JLabel("Product name"));
		frame.add(new JLabel("Quantity in cart"));
		frame.add(new JLabel("Price"));
		
		JTextField total = new JTextField();
		total.setEditable(false);

		frame.add(total);
		
		for(int i = 0 ; i < carts.get(move).size() ; i+=3) {
			JTextField t1 = new JTextField(carts.get(move).get(i));
			t1.setEditable(false);
			frame.add(t1);
			
			JTextField t2 = new JTextField(carts.get(move).get(i+1));
			t2.setEditable(false);
			frame.add(t2);
			
			JTextField t3 = new JTextField(carts.get(move).get(i+2));
			t3.setEditable(false);
			frame.add(t3);
			
			double temp = Integer.parseInt(carts.get(move).get(i+1))*Double.parseDouble(carts.get(move).get(i+2));
			JTextField t4 = new JTextField("Subtotal: " + temp);
			t4.setEditable(false);
			frame.add(t4);
			sum+=temp;
		}
		
		total.setText("Total: " + sum);
		
		JButton back = new JButton("Back");
		back.addActionListener(addListener(GO_BACK));
		frame.add(back);
		
		JButton previous = new JButton("Previous");
		previous.addActionListener(UserController.createPreviousReorderListener(carts, locations));
		JButton next = new JButton("Next");
		next.addActionListener(UserController.createNextReorderListener(carts, locations));
		
		frame.add(previous);
		frame.add(next);
		
		JButton button = new JButton("Order");
		button.addActionListener(UserController.createReOrderListener(locations[move], carts.get(move)));
		frame.add(button);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public ActionListener addListener(String command) {
		return UserController.createListener(command);
	}
	
	public List<String> getTextFields() {
		List<String> texts = new ArrayList<>();
		for(JTextField field : textFields) {
			texts.add(field.getText());
		}
		return texts;
	}

	public void setTextFields(List<String> texts) {
		for(int i = 0 ; i < textFields.size() ; i++) {
			textFields.get(i).setText(texts.get(i));
		}
	}
	
	public void clearFrame() {
		currFrame.dispose();
		textFields.clear();
	}
	
	public void printFields() {
		for(JTextField text : textFields) {
			System.out.println(text.getText());
		}
	}
	
	public void setFrameVisible() {
		currFrame.setVisible(true);
	}
	
	public void displayMessage(String text) {
		JOptionPane.showMessageDialog(null, text);
	}

	
	
	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}
	
}