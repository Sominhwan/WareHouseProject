package warehouse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class AllDetailsMgr {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;
	FileWriter recieptfileWriter, releasefileWriter, inventoryfileWriter;
	BufferedWriter recieptbufferedWriter, releasebufferedWriter, inventorybufferedWriter;

	public AllDetailsMgr(int allDetail) {
		if (allDetail == 0) {
			reciept();
		} else if (allDetail == 1) {
			release();
		} else if (allDetail == 2) {
			Inventory();
		}
	}

	// �԰� ����
	public void reciept() {
		pool = DBConnectionMgr.getInstance();
		String sql = null;
		String recieptHeader = "�԰�¥" + "\t" + "\t" + "ī�װ�" + "\t" + "��ǰ�ڵ�" + "\t" + "�԰����" + "\t" + "����ȣ" + "\n";
		// �԰� ���� ���� ����
		try {
			recieptfileWriter = new FileWriter(".\\�԰���.txt");
			recieptbufferedWriter = new BufferedWriter(recieptfileWriter);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			con = pool.getConnection();
			sql = "SELECT s.STORED_DATE, p.CATEGORY, s.PROD_CODE, s.STORED_STOCK, m.MEMBER_IDX\r\n"
					+ "FROM stored_log s, product p, member m\r\n" + "where s.PROD_CODE = p.PROD_CODE\r\n"
					+ "	and s.MEMBER_IDX = m.MEMBER_IDX\r\n" + "ORDER BY s.STORED_DATE DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			recieptbufferedWriter.write(recieptHeader);
			while (rs.next()) {
				String recieptData = rs.getDate("STORED_DATE") + "\t" + rs.getString("CATEGORY") + "\t"
						+ rs.getString("PROD_CODE") + "\t" + rs.getInt("STORED_STOCK") + "\t" + rs.getInt("MEMBER_IDX");
				recieptbufferedWriter.write(recieptData + "\n");
			}
			System.out.println("�԰� ���� ���� �Ϸ�!");
			recieptbufferedWriter.close();
			recieptfileWriter.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}
		}
	}

	// ��� ����
	public void release() {
		pool = DBConnectionMgr.getInstance();
		String sql = null;
		String releaseHeader = "���¥" + "\t" + "\t" + "ī�װ�" + "\t" + "��ǰ�ڵ�" + "\t" + "������" + "\t" + "����ȣ" + "\n";
		// ��� ���� ���� ����
		try {
			releasefileWriter = new FileWriter(".\\�����.txt");
			releasebufferedWriter = new BufferedWriter(releasefileWriter);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			con = pool.getConnection();
			sql = "SELECT t.TAKEOUT_DATE, p.CATEGORY, p.PROD_CODE, t.TAKEOUT_AMOUNT, m.MEMBER_IDX, t.OTHER\r\n"
					+ "FROM takeout_log t, product p, member m\r\n" + "where t.PROD_CODE = p.PROD_CODE\r\n"
					+ "	and t.MEMBER_IDX = m.MEMBER_IDX\r\n" + "ORDER BY TAKEOUT_DATE DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			releasebufferedWriter.write(releaseHeader);
			while (rs.next()) {
				String releaseData = rs.getDate("TAKEOUT_DATE") + "\t" + rs.getString("CATEGORY") + "\t"
						+ rs.getString("PROD_CODE") + "\t" + rs.getInt("TAKEOUT_AMOUNT") + "\t"
						+ rs.getInt("MEMBER_IDX");
				releasebufferedWriter.write(releaseData + "\n");
			}
			System.out.println("��� ���� ���� �Ϸ�!");
			releasebufferedWriter.close();
			releasefileWriter.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}
		}
	}

	// ��� ����
	public void Inventory() {
		pool = DBConnectionMgr.getInstance();
		String sql = null;
		String inventoryHeader = "��ǰ�ڵ�" + "\t" + "ī�װ�" + "\t" + "��ǰ��" + "\t" + "��ǰ������" + "\t" + "��Ǳ����" + "\t" + "������"
				+ "\n";
		// ��� ���� ���� ����
		try {
			inventoryfileWriter = new FileWriter(".\\�����.txt");
			inventorybufferedWriter = new BufferedWriter(inventoryfileWriter);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			con = pool.getConnection();
			sql = "SELECT PROD_CODE, CATEGORY, PROD_NAME, PROD_SIZE, PROD_COLOR, PROD_STOCK\r\n" + "FROM product\r\n"
					+ "ORDER BY PROD_CODE DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			inventorybufferedWriter.write(inventoryHeader);
			while (rs.next()) {
				String inventoryData = rs.getString("PROD_CODE") + "\t" + rs.getString("CATEGORY") + "\t"
						+ rs.getString("PROD_NAME") + "\t" + rs.getString("PROD_SIZE") + "\t" + "\t"
						+ rs.getString("PROD_COLOR") + "\t" + rs.getString("PROD_STOCK");
				inventorybufferedWriter.write(inventoryData + "\n");
			}
			System.out.println("��� ���� ���� �Ϸ�!");
			inventorybufferedWriter.close();
			inventoryfileWriter.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}
		}
	}
}
