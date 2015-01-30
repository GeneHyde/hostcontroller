package Configuration;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import DataObjects.Profile;
import DatabaseUtilities.DatabaseDataObjectUtilities;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SaveAsNewFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
        private Profile Profile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveAsNewFrame frame = new SaveAsNewFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaveAsNewFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 287, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton SaveButton = new JButton("Save");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField.getText();
                                Profile newProfile = new Profile(id, "", "");		
                            try {
                                DatabaseDataObjectUtilities.addProfileToDB(newProfile);
                            } catch (SQLException ex) {
                                Logger.getLogger(SaveAsNewFrame.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(SaveAsNewFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
				dispose();
			}
		});
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(SaveButton);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(CancelButton);
		
		JLabel lblProfileName = new JLabel("Profile Name:");
		contentPane.add(lblProfileName, BorderLayout.WEST);
	}

}
