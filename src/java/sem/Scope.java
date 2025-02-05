package sem;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Scope {
	private Scope outer;
	private Map<String, Symbol> symbolTable;
	
	public Scope(Scope outer) { 
		this.outer = outer; 
	}
	
	public Scope() { this(null); }

	public Symbol lookup(String name) {
		// stream to iterate over scope chain
		return Stream.iterate(this, s -> s.outer)
				.filter(Objects::nonNull)
				.map(s -> s.symbolTable.get(name))
				.filter(Objects::nonNull)
				.findFirst()
				.orElse(null);
	}

	public Symbol lookupCurrent(String name) {
		return symbolTable.get(name);
	}
	
	public void put(Symbol sym) {
		symbolTable.put(sym.name, sym);
	}
}
