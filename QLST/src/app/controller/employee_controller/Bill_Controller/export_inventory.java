package app.controller.employee_controller.Bill_Controller;
           

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.dao.connectDB;
import jxl.Workbook; 
import jxl.write.*; 

public class export_inventory {

        public static void main(String[] args) throws InterruptedException {

        	WritableWorkbook wworkbook;
            try {
           	wworkbook = Workbook.createWorkbook(new File("C:\\Users\\hau\\Pictures\\Project\\test.xls"));
          			
				connectDB obj_DBConnection_LMC=new connectDB();
				Connection connection=obj_DBConnection_LMC.ConnectDb();
				PreparedStatement ps=null;
				
				ResultSet rs=null;
				 
			 
	 			String query="SELECT * FROM ware_house INNER JOIN product ON ware_house.pro_id= product.pro_id";
//	 			where date like '%oct-2015%'
			
		 			 
				
				ps=connection.prepareStatement(query);
				System.out.println(ps);
				rs=ps.executeQuery();
				 WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
				 Label label = new Label(0, 2, "A label record");
				  wsheet.addCell(label);
		          int i=0;
				 
		           
		           int j=1;
				while(rs.next()){
					
					i=0;
					
					 label = new Label(i++, j, j+"");
					  wsheet.addCell(label);					  
					  label = new Label(i++, j, rs.getString("pro_name"));
					  wsheet.addCell(label);
					 label = new Label(i++, j, rs.getString("pro_sale_price"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("pro_expiry"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("pro_unit"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("pro_brand"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("pro_category"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("barcode"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("amount_stock"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("amount_input"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("price_input"));
					  wsheet.addCell(label);
					  label = new Label(i++, j, rs.getString("date_input"));
					  wsheet.addCell(label);


					  
					  
					 
					j++;
				}
				
				
				
            
            wworkbook.write();
            wworkbook.close();
            System.out.println("fineshed");
            
            
            
            
            
            } catch (Exception e) {
            System.out.println(e);
			}
        }

      
        
        
}
