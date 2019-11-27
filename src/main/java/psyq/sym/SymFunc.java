package psyq.sym;

import java.util.ArrayList;
import java.util.List;

public class SymFunc extends SymObject {
	String fileName = null;
	String funcName;
	
	long endOffset = 0L;
	
	List<SymDef> args = new ArrayList<>();
	SymDefType retnType;
	

	public SymFunc(long offset, SymDefType retnType, String funcName) {
		super(offset, 0);
		
		this.retnType = retnType;
		this.funcName = funcName;
	}
	
	public void setEndOffset(long endOffset) {
		this.endOffset = endOffset;
	}
	
	public long getEndOffset() {
		return endOffset;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFuncName() {
		return funcName;
	}
	
	public void addArgument(SymDef arg) {
		args.add(arg);
	}
	
	public SymDef[] getArguments() {
		return args.toArray(SymDef[]::new);
	}
	
	public String getArgumentsAsString() {
		if (args.size() == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < args.size() - 1; ++i) {
			builder.append(normalizeType(args.get(i).getDefType().toString())).append(' ').append(args.get(i).getName()).append(',');
		}
		
		SymDef lastArg = args.get(args.size() - 1);
		
		builder.append(normalizeType(lastArg.getDefType().toString())).append(' ').append(lastArg.getName());
		
		return builder.toString();
	}
	
	public SymDefType getReturnType() {
		return retnType;
	}
	
	private static String normalizeType(String type) {
		return type.replace("PTR", "*");
	}

	public String getReturnTypeAsString() {
		SymDefTypePrimitive[] primTypes = retnType.getTypesList();
		StringBuilder builder = new StringBuilder();
		
		for (int i = 1; i < primTypes.length; ++i) {
			builder.append(normalizeType(primTypes[i].name())).append(' ');
		}
		
		return builder.toString();
	}
}