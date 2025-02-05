package sem;

import java.util.HashMap;
import java.util.Map;

public class Scope {
	private Scope outer;
	private Map<String, Symbol> symbolTable;

	public Scope(Scope outer) {
		this.outer = outer;
		this.symbolTable = new HashMap<>();
	}

	public Scope() {
		this(null);
	}

	public Symbol lookup(String name) {
		Symbol sym = symbolTable.get(name);
		if (sym != null) {
			return sym;
		} else if (outer != null) {
			return outer.lookup(name);
		}
		return null;
	}

	public Symbol lookupCurrent(String name) {
		return symbolTable.get(name);
	}

	public void put(Symbol sym) {
		symbolTable.put(sym.name, sym);
	}
}