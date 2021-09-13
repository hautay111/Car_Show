package app.controller.manage_controller;

import javafx.geometry.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import app.model.Run_Out;
import app.model.Ware_house;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ware_house implements Initializable {

	Connection conn = null;
	Connection connection = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null, pst = null;
	Ware_house ware_house = null;
	String query = null;
	int index = -1;
	ObservableList<Ware_house> listM;
	ObservableList<Ware_house> dataList;

	@FXML
	private TextField search;

	@FXML
	private Label wh_id;

	@FXML
	private TableView<Ware_house> table_ware_house;

	@FXML
	private TableColumn<Ware_house, Integer> col_no;

	@FXML
	private TableColumn<Ware_house, String> col_id;

	@FXML
	private TableColumn<Ware_house, Integer> col_pro_id;

	@FXML
	private TableColumn<Ware_house, String> col_name;

	@FXML
	private TableColumn<Ware_house, String> col_sup;

	@FXML
	private TableColumn<Ware_house, Integer> col_amount;

	@FXML
	private TableColumn<Ware_house, Integer> col_price;

	@FXML
	private TableColumn<Ware_house, String> col_date_input;

	@FXML
	private TableColumn<Ware_house, String> col_date_expiry;

	@FXML
	private TableColumn<Ware_house, String> col_action;
	
	@FXML
	private TableColumn<Ware_house, Integer> col_ipd_id;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		search_wh();
		Action();
	}

	@FXML
	void getSelected(MouseEvent event) {
		index = table_ware_house.getSelectionModel().getSelectedIndex();
		if (index <= -1) {
			return;
		}
		wh_id.setText(col_ipd_id.getCellData(index).toString());
	}

	@FXML
	void search_wh() {
		col_no.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("no"));
		col_id.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("id"));
		col_sup.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("sup"));
		col_date_expiry.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("date_expiry"));
		col_date_input.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("date_input"));
		col_name.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("name"));
		col_amount.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("amount"));
		col_price.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("price"));
		col_pro_id.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("pro_id"));
		col_ipd_id.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("ipd_id"));

		dataList = connectDB.getDataWarehouse();
		table_ware_house.setItems(dataList);
		FilteredList<Ware_house> filteredData = new FilteredList<>(dataList, b -> true);
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getSup().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getDate_expiry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(person.getPrice()).indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false;
			});
		});
		SortedList<Ware_house> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table_ware_house.comparatorProperty());
		table_ware_house.setItems(sortedData);

	}
	 @FXML
	    void Update_table(MouseEvent event) throws SQLException {
		 conn=connectDB.ConnectDb();
		 try {
				ware_house = table_ware_house.getSelectionModel().getSelectedItem();
				String sql1 = "SELECT input_detail.expiry\r\n" + "FROM input_detail\r\n"
						+ "WHERE input_detail.input_detail_id='" + wh_id.getText()
						+ "' AND input_detail.expiry<Now();";
				pst = conn.prepareStatement(sql1);
				rs = pst.executeQuery();
				if (rs.next()) {
					String sql2 = "UPDATE input_detail set input_detail.amount=0 WHERE input_detail.input_detail_id='"
							+ wh_id.getText() + "' AND input_detail.expiry <NOW();";
//					preparedStatement = connection.prepareStatement(sql2);
//					preparedStatement.execute();
					System.out.println(sql2);
					search_wh();
				} else {
					JOptionPane.showMessageDialog(null, "Not expired.");
				}

			} catch (SQLException ex) {
				Logger.getLogger(ware_house.class.getName()).log(Level.SEVERE, null, ex);
			}
	    }
	public void Action() {
		col_no.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("no"));
		col_id.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("id"));
		col_sup.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("sup"));
		col_date_expiry.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("date_expiry"));
		col_date_input.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("date_input"));
		col_name.setCellValueFactory(new PropertyValueFactory<Ware_house, String>("name"));
		col_amount.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("amount"));
		col_price.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("price"));
		col_pro_id.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("pro_id"));
		col_ipd_id.setCellValueFactory(new PropertyValueFactory<Ware_house, Integer>("ipd_id"));
		
		Callback<TableColumn<Ware_house, String>, TableCell<Ware_house, String>> cellFoctory = (
				TableColumn<Ware_house, String> param) -> {
			final TableCell<Ware_house, String> cell = new TableCell<Ware_house, String>() {

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
						setText(null);

					} else {

						FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

						deleteIcon.setStyle(" -fx-cursor: hand ;" + "-glyph-size:15px;" + "-fx-fill:#ff1744;");
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
							System.out.println("hello");
							System.out.println("ipd_id "+ wh_id.getText());
							try {
								index = table_ware_house.getSelectionModel().getSelectedIndex();
								if (index <= -1) {
									return;
								}
								wh_id.setText(col_ipd_id.getCellData(index).toString());
								
								conn=connectDB.ConnectDb();
								ware_house = table_ware_house.getSelectionModel().getSelectedItem();
								String sql1 = "SELECT input_detail.expiry\r\n" + "FROM input_detail\r\n"
										+ "WHERE input_detail.input_detail_id='" + wh_id.getText()
										+ "' AND input_detail.expiry<Now();";
															
								pst = conn.prepareStatement(sql1);
								rs = pst.executeQuery();
								if (rs.next()) {
									String sql2 = "UPDATE input_detail set input_detail.amount=0 WHERE input_detail.input_detail_id='" +wh_id.getText() + "' AND input_detail.expiry <NOW();";
//									preparedStatement = connection.prepareStatement(sql2);
//									preparedStatement.execute();
									System.out.println("sql2 "+sql2);
									pst=conn.prepareStatement(sql2);
									pst.execute();
									search_wh();
								} else {
									JOptionPane.showMessageDialog(null, "Not expired.");
								}

							} catch (SQLException ex) {
								Logger.getLogger(ware_house.class.getName()).log(Level.SEVERE, null, ex);
							}

						});

						HBox managebtn = new HBox(deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

						setGraphic(managebtn);

						setText(null);

					}
				}
			};
			return cell;
		};
		col_action.setCellFactory(cellFoctory);
		table_ware_house.setItems(dataList);
	}

}
