package psyq.sym;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SymDefType {
	private List<SymDefTypePrimitive> primTypes = new ArrayList<>();
	
	public SymDefType(SymDefTypePrimitive[] primTypes) {
		Collections.addAll(this.primTypes, primTypes);
	}
	
	public SymDefType(int type) {
		while ((type & 0xFFF0) != 0) {
			int type1 = (type >> 4) & 3;

			switch (type1) {
			case 1: primTypes.add(SymDefTypePrimitive.PTR); break;
			case 2: primTypes.add(SymDefTypePrimitive.FCN); break;
			case 3: primTypes.add(SymDefTypePrimitive.ARY); break;
			}

			type = ((type >> 2) & 0xFFF0) + (type & 0xF);
		}

		switch (type)
		{
		case 0:	primTypes.add(SymDefTypePrimitive.NULL); break;
		case 1: primTypes.add(SymDefTypePrimitive.VOID); break;
		case 2:	primTypes.add(SymDefTypePrimitive.CHAR); break;
		case 3:	primTypes.add(SymDefTypePrimitive.SHORT); break;
		case 4:	primTypes.add(SymDefTypePrimitive.INT); break;
		case 5:	primTypes.add(SymDefTypePrimitive.LONG); break;
		case 6:	primTypes.add(SymDefTypePrimitive.FLOAT); break;
		case 7:	primTypes.add(SymDefTypePrimitive.DOUBLE); break;
		case 8:	primTypes.add(SymDefTypePrimitive.STRUCT); break;
		case 9:	primTypes.add(SymDefTypePrimitive.UNION); break;
		case 10: primTypes.add(SymDefTypePrimitive.ENUM); break;
		case 11: primTypes.add(SymDefTypePrimitive.MOE); break;
		case 12: primTypes.add(SymDefTypePrimitive.UCHAR); break;
		case 13: primTypes.add(SymDefTypePrimitive.USHORT); break;
		case 14: primTypes.add(SymDefTypePrimitive.UINT); break;
		case 15: primTypes.add(SymDefTypePrimitive.ULONG); break;
		}
	}
	
	public SymDefTypePrimitive[] getTypesList() {
		return primTypes.toArray(SymDefTypePrimitive[]::new);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = primTypes.size() - 1; i >= 0 ; --i) {
			builder.append(primTypes.get(i).name()).append(' ');
		}
		
		return builder.toString();
	}
}