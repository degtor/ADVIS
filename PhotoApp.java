import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class PhotoApp {
	public static void main(String args[]) {

		UIManager.put(PhotoCompAbstractUI.UI_CLASS_ID, "BasicPhotoUI"); //Goes in PhotoApp main?
		new PhotoApp("The Photo Application");
	}

	
	public PhotoApp(String title) {
		JFrame window = new JFrame(title);
		window.getContentPane().setLayout(new BorderLayout());
				
		//LAYOUT INIT ----
				
		//Menubar
		JMenuBar menuBar = new JMenuBar();
		window.getContentPane().add(menuBar, BorderLayout.PAGE_START);
		
		//BottomBar
		JPanel statusBar = new JPanel();
		statusBar.setLayout(new BorderLayout());
		
		JLabel status = new JLabel("Status");
		statusBar.add(status, BorderLayout.LINE_START);
		window.getContentPane().add(statusBar, BorderLayout.PAGE_END);
	
		//Center
		JComponent centerArea = new PhotoComponent(null);
		centerArea.setPreferredSize(new Dimension(400,400));
		centerArea.setMaximumSize(new Dimension(400,400));
		window.getContentPane().add(centerArea, BorderLayout.CENTER);
		
		//left toolBar
		JToolBar catBar = new JToolBar(null, JToolBar.VERTICAL);
		catBar.setMaximumSize(new Dimension(400, 200));
		
		JToggleButton famVacButton = new JToggleButton("Family");
		famVacButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (famVacButton.isSelected()) {
					famVacButton.setText("Family");
					status.setText("You changed to Family mode");
				} else {
					famVacButton.setText("Vacation");
					status.setText("You changed to Vacation mode");
				}
			}
		});
		catBar.add(famVacButton);
		
		JToggleButton portGroupButton = new JToggleButton("Portrait");
		portGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (portGroupButton.isSelected()) {
					portGroupButton.setText("Portrait");
					status.setText("You changed to Portrait mode");
				} else {
					portGroupButton.setText("Group");
					status.setText("You changed to Group mode");
				}
			}
		});
		catBar.add(portGroupButton);
		
		Container toolbarContentPane = window.getContentPane();
		toolbarContentPane.add(catBar, BorderLayout.LINE_START);
	
		//END LAYOUT INIT -----
	
		//WRITE MENU ----
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		//sub file
		JMenuItem importItem = new JMenuItem("Import");
		importItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				final JFileChooser chooser = new JFileChooser();
				int result = chooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						File selectedFile = chooser.getSelectedFile();
						Image i = ImageIO.read(selectedFile);

						PhotoComponent pc = new PhotoComponent(i);

					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});
		
		fileMenu.add(importItem);
		
		JMenuItem deleteItem = new JMenuItem("Delete");
		fileMenu.add(deleteItem);
		
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(
			event -> quitItem()
		);
		
		fileMenu.add(quitItem);
		
		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);
		
		//sub view
		JRadioButtonMenuItem photoViewerItem = new JRadioButtonMenuItem("Photo viewer");
		viewMenu.add(photoViewerItem);
		JRadioButtonMenuItem browserItem = new JRadioButtonMenuItem("Browser");
		viewMenu.add(browserItem);
		JRadioButtonMenuItem splitModeItem = new JRadioButtonMenuItem("Split mode");
		viewMenu.add(splitModeItem);
		
		//END WRITE MENU ----
		
		
		window.setJMenuBar(menuBar);
		window.pack();
		window.setVisible(true);
	}
	
	public void importItem() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);

	}
	
	public void quitItem() {
		System.exit(0);
	}
}