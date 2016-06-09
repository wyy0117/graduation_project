package Sql;

import java.sql.ResultSet;
import java.sql.Statement;

import JDBC.Conn;

public class getData {

	public static String getlSex(String lnum) {
		String sex = null;
		try {
			Statement st = Conn.getst();
			String getSex = MySql.select("sex", "building", "lnum='" + lnum+"'");
			ResultSet rs = st.executeQuery(getSex);

			while (rs.next()) {
				sex = rs.getString(1);
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sex;
	}

	public static int getcount(String type, String lnum, String rnum) {
		int count = 0;
		try {
			Statement st = Conn.getst();
			String getcount = MySql.select(type, "room", "lnum='" + lnum
					+ "' and rnum='" + rnum+"'");
			ResultSet rs = st.executeQuery(getcount);
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
		}
		return count;
	}

	public static boolean isExistStu(String lnum, String rnum, String bnum) {
		boolean isExist = false;
		Statement st;
		String getStatus = MySql.select("status", "bed", "lnum='" + lnum
				+ "' and rnum='" + rnum + "' and bnum= '" + bnum + "'");
		try {
			st = Conn.getst();
			ResultSet rs = st.executeQuery(getStatus);
			if (rs.next()) {
				if (rs.getInt(1) == 0) {
					isExist = false;
				} else {
					isExist = true;
				}

			}

		} catch (Exception e) {
		}

		return isExist;
	}

	public static boolean isExistBuild(String lnum) {
		boolean isExist = false;
		Statement st;
		String getBuild = MySql.select("lnum", "building", "lnum='" + lnum
				+ "'");
		try {
			st = Conn.getst();
			ResultSet rs = st.executeQuery(getBuild);
			if (rs.next()) {
				isExist = true;
			}

		} catch (Exception e) {
		}
		return isExist;
	}

	public static boolean isExistBed(String lnum, String rnum, String bnum) {
		boolean isExist = false;
		StringBuffer sb = new StringBuffer();
		sb.append("lnum='");
		sb.append(lnum + "' and ");
		sb.append("rnum='");
		sb.append(rnum + "' and ");
		sb.append("bnum='");
		sb.append(bnum + "'");
		String select = MySql.select("status", "bed", sb.toString());
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(select);
			if (rs.next()) {
				isExist = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isExist;
	}

	public static String getClass(String snum) {
		String sclassString = null;
		String classString = MySql.select("class", "student", "snum='" + snum
				+ "'");
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(classString);
			if (rs.next()) {
				sclassString = rs.getString(1);
			}

		} catch (Exception e) {
		}
		return sclassString;
	}

	public static String getstulnum(String snum) {
		String stulnumString = "";
		String selectstulnum = MySql.select("lnum", "student", "snum='" + snum
				+ "'");
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectstulnum);
			if (rs.next()) {
				stulnumString = rs.getString(1);
			}
		} catch (Exception e) {
		}
		return stulnumString;
	}

	public static String getsturnum(String snum) {
		String sturnumString = "";
		String selectsturnum = MySql.select("rnum", "student", "snum='" + snum
				+ "'");
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectsturnum);
			if (rs.next()) {
				sturnumString = rs.getString(1);
			}
		} catch (Exception e) {
		}
		return sturnumString;
	}

	public static String getstubnum(String snum) {
		String stubnumString = "";
		String selectstubnum = MySql.select("bnum", "student", "snum='" + snum
				+ "'");
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectstubnum);
			if (rs.next()) {
				stubnumString = rs.getString(1);
			}
		} catch (Exception e) {
		}
		return stubnumString;
	}

	public static String getstuname(String snum) {
		String stunameString = "";
		String selectstuname = MySql.select("sname", "student", "snum='" + snum
				+ "'");
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectstuname);
			if (rs.next()) {
				stunameString = rs.getString(1);
			}
		} catch (Exception e) {
		}
		return stunameString;

	}

	public static String getstuclass(String snum) {
		String stuclassString = "";
		String selectstuclass = MySql.select("class", "student", "snum='"
				+ snum + "'");
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectstuclass);
			if (rs.next()) {
				stuclassString = rs.getString(1);
			}
		} catch (Exception e) {
		}
		return stuclassString;

	}

	public static String getStuSex(String snum) {
		String stuSex = "";
		String selectStuSexString = MySql.select("sex", "student", "snum='"
				+ snum + "'");
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectStuSexString);
			if (rs.next()) {
				stuSex = rs.getString(1);
			}
		} catch (Exception e) {
		}
		return stuSex;

	}

}
