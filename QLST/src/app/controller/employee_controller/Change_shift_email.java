package app.controller.employee_controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import com.github.sarxos.webcam.util.Initializable;

import app.dao.connectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Change_shift_email implements Initializable{

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	@FXML
    private AnchorPane main;
	
    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextArea content;

    @FXML
    private DatePicker date;

    @FXML
    private DatePicker date_change;

    @FXML
    private ComboBox<String> main_shift;

    @FXML
    private ComboBox<String> swich_shift;

    @FXML
    void Sent(ActionEvent event) {
    	conn = connectDB.ConnectDb();
		try {
			if (email.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Email cannot be blank!!");
				System.out.println("trong r");
				Stage stage = (Stage) main.getScene().getWindow();
			    // do what you have to do
			    stage.close();

			} else {
//				--------------------------------------------------
				// Your gmail address
				String myAccountEmail = "crmgroupapp@gmail.com";
				// Your gmail password
				String password = "crmapp0123123";

				Properties properties = new Properties();

				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");

				Session session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(myAccountEmail, password);
					}
				});

				try {
					System.out.println("Sending.");					
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(myAccountEmail));
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getText()));
					message.setSubject("Change Shift:");
					String htmlCode = "<h2>"
							+ "			Super market group send to <i> <u>" + name.getText()+ "</u> </i> . <br> "
							+ "        Change shift."
							+ "    </h2>"
							+ "    <h3>Main shift: <i> <u>" + main_shift.getValue()+ "</u> </i> <br>"
							+ "        Switch shift:<i> <u>" + swich_shift.getValue()+ "</u> </i> <br/> "
							+ "        Date switch:<i> <u>" + date.getEditor().getText() + "</u> </i> <br/>"
							+ "        Shift change date:<i> <u>" + date_change.getEditor().getText() + "</u> </i> <br/>"
							+ "    </h3>"
							+ "    <p>"
							+ "        If you have any questions, please contact: 0252112002 . <br>"
							+ "        wish you good day."
							+ "    </p>";
					message.setContent(htmlCode, "text/html; charset=utf-8");
					Transport.send(message);
					JOptionPane.showMessageDialog(null, "Send Successfully");
					Stage stage = (Stage) main.getScene().getWindow();
				    // do what you have to do
				    stage.close();

				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
					Stage stage = (Stage) main.getScene().getWindow();
				    // do what you have to do
				    stage.close();

				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }
    
    ObservableList<String> list1 = FXCollections.observableArrayList("Shift 1", "Shift 2");
    ObservableList<String> list2 = FXCollections.observableArrayList("Shift 1", "Shift 2");

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		main_shift.setItems(list1);
		swich_shift.setItems(list2);
	}

	@Override
	public void teardown() {
		// TODO Auto-generated method stub
		
	}

}
