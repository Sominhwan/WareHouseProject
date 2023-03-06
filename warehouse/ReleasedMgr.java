package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class ReleasedMgr {
	DBConnectionMgr pool;
	public ReleasedMgr()
	{
		pool=DBConnectionMgr.getInstance();
	}
	public Vector<ProductBean> loadWhenOpened()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ProductBean> vlist=new Vector<ProductBean>();
		try {
			con = pool.getConnection();
			sql = "select * from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ProductBean bean=new ProductBean(rs.getString("PROD_CODE"), rs.getString("CATEGORY"),
						rs.getString("PROD_NAME"), rs.getString("PROD_SIZE"), rs.getString("PROD_COLOR"),
						rs.getInt("PROD_STOCK"));
				vlist.addElement(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	public Vector<ProductBean> loadWarehouseOut(String keyword)//����� ��ǰ �˻�
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ProductBean> vlist=new Vector<ProductBean>();
		try {
			con = pool.getConnection();
			sql = "select * from product where PROD_CODE like ? OR PROD_NAME like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ProductBean bean=new ProductBean(rs.getString("PROD_CODE"), rs.getString("CATEGORY"),
						rs.getString("PROD_NAME"), rs.getString("PROD_SIZE"), rs.getString("PROD_COLOR"),
						rs.getInt("PROD_STOCK"));
				vlist.addElement(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	public Vector<ReleasedBean> outSearch(String keyword)//����� �˻�
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ReleasedBean> vlist=new Vector<ReleasedBean>();
		try {
			con = pool.getConnection();
			sql = "select * from takeout_log where PROD_CODE like ? or MEMBER_IDX like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ReleasedBean bean=new ReleasedBean(rs.getInt("TAKEOUT_IDX"), rs.getString("PRODUCT_CODE"),
						rs.getInt("MEMBER_IDX"), rs.getString("TAKEOUT_DATE"), rs.getInt("TAKEOUT_AMOUNT"),
						rs.getString("OTHER"), rs.getString("TAKEOUT_ADDRESS") );
				vlist.addElement(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	public boolean releasedStart(String prodCode, int memberIdx, int takeoutAmount, String other, String takeoutAddress)//���
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag=false;
		try {
			con = pool.getConnection();
			sql = "insert into takeout_log values (null,?,?,now(),?,?,?)";//mysql���� ���� �ð��� �����ϴ� �� now()
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prodCode);
			pstmt.setInt(2, memberIdx);
			pstmt.setInt(3, takeoutAmount);
			pstmt.setString(4, other);
			pstmt.setString(5, takeoutAddress);
			int cnt=pstmt.executeUpdate();
			if(cnt==1) flag=true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
}