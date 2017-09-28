
//photoCompUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.ComponentUI;

public class BasicPhotoUI extends PhotoCompAbstractUI implements MouseListener {

	// Creates and return an instance of your UI class
	public static ComponentUI createUI(JComponent c) {
		System.out.println("CreatedUI" + c);
 	   return new BasicPhotoUI();
   	}
 
 	// Register 'this' UI Instance as the listener for the component's input events
 	public void installUI(JComponent c) {
		System.out.println("InstalledUI");
 	   ((PhotoComponent) c).addMouseListener(this); // we’ll handle mouse events for the Photo component
 	}
 
	// Unregister 'this' UI instance as the listener for the components input events
 	public void uninstallUI(JComponent c) {
		System.out.println("UninstalledUI");
 	   ((PhotoComponent) c).removeMouseListener(this);
 	}
 
 	//Step 3
	@Override
 	public void paint(Graphics g, JComponent c) {
		System.out.println("Painting in UI");

		super.paint(g, c);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(image, 0, 0, null);

		PhotoComponent pc = (PhotoComponent) c;
		PhotoModel model = pc.getModel();

		Image i = model.getImage();

		//g.drawImage(i, 0, 0, this);

 	   // do painting for the component here!
	   //2. • Your component will automatically delegate its drawing to your UI’s paint() method 
 	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	// implement the various MouseListener methods...
	// • 3. Implement any interfaces you need in order to respond to input events 
	// • Example: if your component must respond to the mouse, have your UI class implement MouseListener. You’ll tell the component to send any mouse events to your UI to be handled there. 
	
	// • 4. Draw yourself correctly given your current size • Recall that your parent component may resize you! In your painting code, use the current size (getWidth()/getHeight()) and draw in the space alloted to you.
	
	
}