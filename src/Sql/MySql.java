package Sql;

public class MySql {

	public static String insert(String table,String field, String values) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(table);
		if (!field.equals("*")) {
			sb.append("( ");
			sb.append(field);
			sb.append(")");
		}
		
		sb.append(" values (");
		sb.append(values);
		sb.append(");");
		return sb.toString();
	}

	public static String select(String select, String from, String where) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append(select);
		sb.append(" from ");
		sb.append(from);
		sb.append(" where ");
		sb.append(where);
		sb.append(";");
		return sb.toString();
	}

	public static String update(String table, String set,
			String where) {
		StringBuffer sb = new StringBuffer();
		sb.append("update ");
		sb.append(table);
		sb.append(" set ");
		sb.append(set);
		sb.append(" where ");
		sb.append(where);
		sb.append(";");
		return sb.toString();
	}

	public static String delete(String from, String where) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ");
		sb.append(from);
		sb.append(" where ");
		sb.append(where);
		sb.append(";");
		return sb.toString();
	}
}
