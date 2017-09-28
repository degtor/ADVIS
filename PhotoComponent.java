//the Component
// • 4.1 Design the component’s external API
// • These are the methods that application programmers see and use
// • Many will just forward to the underlying model or the UI
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PhotoComponent extends JComponent implements ChangeListener {
	// • 4.2 Make your component a listener for the Model’s ChangeEvents or PropertyChangeEvents

	PhotoModel model, old;

	
	public PhotoComponent(Image i) {
		setModel(new PhotoModel(i)); // Picture goes in here
		updateUI();
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.gray);
		g.fillRoundRect(2, 2, this.getWidth() - 4,
				this.getHeight() - 4, 30, 30);
	}

	public void setModel(PhotoModel m) {
		old = this.model;

		System.out.println(this);

		if (old != null)
			 old.removeChangeListener(this);

		 model = m;
		 model.addChangeListener(this);

		//System.out.println("Model damn: " + model.getImage());
		this.firePropertyChange("model", old, model);
		m.fireChangeEvent();

	 }
 
	 // Used to set and return the model. When your model is set, your component should register iteself as a listener for the model's change events.
	public PhotoModel getModel() {
		System.out.println("getModel");
		return model;
	}

	public Image getImage() {
		System.out.println("Got Image in Component");
		Image i = model.getImage();
		return i;
	}
 
	public void setUI(PhotoCompAbstractUI ui) {
		System.out.println("Abstract UI Set");
		super.setUI(ui);
	}
	
	//4.4 set and change the UI on the fly
	public void updateUI() {
		System.out.println("Updated UI");
 		setUI((PhotoCompAbstractUI) UIManager.getUI(this));
 		invalidate();
	}
 
 	//4.4 should return whatever UI_CLASS_ID string is for "compatible" UIS for this component
 	public String getUIClassID() { 
		return PhotoCompAbstractUI.UI_CLASS_ID;
	}

    //@Override
    public void stateChanged(ChangeEvent e) {
		System.out.println("StateChanged? REPAINTING" + e);
		repaint();
    }


    //4.3. Send PropertyChangeEvents if the component’s internal state changes
	//• Other components might be listening to you--send PropertyChangeEvents if anything component-specific changes
}