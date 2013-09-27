package org.drfoliberg.films3000.data.database.structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Structure implements Serializable {

	private static final long serialVersionUID = -4906363292977241287L;
	private ArrayList<Table> tables;

	public Structure() {
		tables = new ArrayList<>();
	}

	public Structure(File f) {
		this.tables = new ArrayList<>();
		try {
			InputStream is = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(is);
			Object o = ois.readObject();

			if (o instanceof ArrayList) {
				ArrayList<?> tables = (ArrayList<?>) o;
				for (Object t : tables) {
					if (t instanceof Table) {
						this.tables.add((Table) t);
					}
				}
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ajouterTable(Table table) {
		this.tables.add(table);
	}

	public String getSql() {
		StringBuilder sql = new StringBuilder();
		for (Table table : tables) {
			sql.append(table.getSql());
		}

		return sql.toString();
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	@Override
	public boolean equals(Object o) {
		boolean egal = false;

		if (o instanceof Structure) {
			Structure compare = (Structure) o;
			if (compare.tables.size() == this.tables.size() && this.tables.containsAll(compare.tables)) {
				egal = true;
			}
		}

		return egal;
	}
}
