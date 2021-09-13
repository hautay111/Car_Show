package app.controller.employee_controller;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

//import app.controller.employee_controller.Change_shift_email;
import app.controller.homepage.Info_Employee;
import app.dao.connectDB;
import app.model.ChangeShift;
import app.model.Inventory;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Change_shift_employee implements Initializable{
	
	  @FXML
	    private TableColumn<ChangeShift,String> col_emp_name;

	    @FXML
	    private TableColumn<ChangeShift,String> col_emp_email;

	    @FXML
	    private TableColumn<ChangeShift,String> col_main_shift;

	    @FXML
	    private TableColumn<ChangeShift,String> col_switch_shift;

	    @FXML
	    private TableColumn<ChangeShift,String> col_date_switch;

	    @FXML
	    private TableColumn<ChangeShift,String> col_shift_change_date;

	    @FXML
	    private TableView<ChangeShift> table_changeshift;
	    
	    @FXML
	    private TableColumn<ChangeShift, Integer> col_no_shift_id;
	    
//		@FXML
//	    private AnchorPane View_email;
//
//	    @FXML
//	    private TextField email_name;
//
//	    @FXML
//	    private TextField email_email;
//
//	    @FXML
//	    private TextArea email_content;

	    @FXML
	    private Label ltotal;
	    
	    int index = -1;
	    
	    Connection conn =null;
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    ObservableList<ChangeShift> listM;
	    ObservableList<ChangeShift> dataList;
	    
	    public void UpdateTable_changeshift(){


	    	col_no_shift_id.setCellValueFactory(new PropertyValueFactory<ChangeShift,Integer>("shift_id"));
	    	col_emp_name.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("emp_name"));
	    	col_emp_email.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("emp_email"));
	    	col_main_shift.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("main_shift"));
	    	col_switch_shift.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("switch_shift"));
	    	col_date_switch.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("date_switch"));
	    	col_shift_change_date.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("shift_change_date"));
	    	
	    	
	    	


	        listM = connectDB.getDataChangeShift();
	        table_changeshift.setItems(listM);
	    }
	    
//	    @FXML
//		void Sent(ActionEvent event) throws Exception {
//			try {
//				if (email.getText().trim().equals("")) {
//					JOptionPane.showMessageDialog(null, "Email cannot be blank!!");
//					System.out.println("trong r");
//				} else {
//					date_switch.getEditor().getText().trim().equals("")) {
//						JOptionPane.showMessageDialog(null, "Date switch cannot be blank!!");
//						System.out.println("trong r");
//				} else {
//			    	  shift_change_date.getEditor().getText().trim().equals("")) {
//							JOptionPane.showMessageDialog(null, "Date change cannot be blank!!");
//							System.out.println("trong r");
//						}
//					}
//				String a1,a2,a3,a4,a5,a6;
//				a1=name.getText()
//					pst.setString(1, name.getText());
//					pst.setString(2, email.getText());
//					pst.setString(3, title.getText());
//					pst.setString(4, email.getText());
//					pst.setString(5, date_switch.getEditor().getText());
//					pst.setString(6, shift_change_date.getEditor().getText());
//					pst.setString(7, content.getText());
//					pst.execute();
////					--------------------------------------------------
//					// Your gmail address
//					String myEmail = "crmgroupapp@gmail.com";
//					
//					// Your gmail password
//					String password = "crmapp0123123";
//					
//					Properties properties = new Properties();
//
//					properties.put("mail.smtp.auth", "true");
//					properties.put("mail.smtp.starttls.enable", "true");
//					properties.put("mail.smtp.host", "smtp.gmail.com");
//					properties.put("mail.smtp.port", "587");
//
//					Session session = Session.getInstance(properties, new Authenticator() {
//						protected PasswordAuthentication getChangeshiftAuthentication() {
//							return new PasswordAuthentication(myEmail,password);
//						}
//					});
//	    try {
//			System.out.println("Sending.");					
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(myEmail));
//			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getText()));
//			message.setSubject("Change Shift:");
//			String htmlCode = "<h2>"
//					+ "			Super market group send to <i> <u>" + name.getText()+ "</u> </i> . <br> "
//					+ "        Change shift."
//					+ "    </h2>"
//					+ "    <h3>Title: <i> <u>" + title.getValue()+ "</u> </i> <br/> "
//					+ "        Main shift: <i> <u>" + main_shift.getText()+ "</u> </i> <br>"
//					+ "        Switch shift:<i> <u>" + switch_shift.getText()+ "</u> </i> <br/> "
//					+ "        Date switch:<i> <u>" + date_switch.getEditor().getText() + "</u> </i> <br/>"
//					+ "        Shift change date:<i> <u>" + shift_change_date.getEditor().getText() + "</u> </i> <br/>"
//					+ "    </h3>"
//					+ "    <p>"
//					+ "        If you have any questions, please contact: 0252112002 . <br>"
//					+ "        wish you good day."
//					+ "    </p>";
//			message.setContent(htmlCode, "text/html; charset=utf-8");
//			Transport.send(message);
//			JOptionPane.showMessageDialog(null, "Send Successfully");
//			System.out.println("Message sent successfully");
//		} catch (Exception ex) {
//			// TODO: handle exception
//			ex.printStackTrace();
//
//	    }
//	    
//	    private static String E_name,E_email;
//	    @FXML
//	    void Changeshift_email(MouseEvent event) {
//		    try {
//		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/employee/change_shift_email.fxml"));
//						E_name=email_name.getText();
//						E_email=email_email.getText();
//		                Parent root = (Parent) fxmlLoader.load();
//		                System.out.println(E_name+E_email);
//		                Stage stage = new Stage();
//		            	Change_shift_email email=fxmlLoader.getController();
//		            	email.getInformation(E_name, E_email);
//		                stage.setScene(new Scene(root));  
//		                stage.show();             
//		                UpdateTable_changeshift();
//		                
//		        } catch(Exception e) {
//		        	
//		           e.printStackTrace();
//		          }
//	    }

	    @FXML
	    void Changeshift_email(MouseEvent event) {
		    try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/employee/change_shift_email.fxml"));
		                Parent root = (Parent) fxmlLoader.load();
		                Stage stage = new Stage();
		                stage.setScene(new Scene(root));  
		                stage.show();             
		                UpdateTable_changeshift();
		                
		        } catch(Exception e) {
		        	
		           e.printStackTrace();
		          }
	    
	    }
		@FXML
		void exit(MouseEvent event) {
			try {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../../ui/homepage/Home_Employee.fxml"));
				Parent parent;
		
				parent = loader.load();
		
				Scene scene = new Scene(parent);
				stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			UpdateTable_changeshift();
		}
		
		
	}
