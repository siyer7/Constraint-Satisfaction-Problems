import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
public class n_QueensProblem implements CSP{
	private ArrayList<Variable> variables = new ArrayList<>();
	public n_QueensProblem(int n) {
		SetUp(n);
	}
	@Override
	public void SetUp(int n) {
		ArrayList<Value> varDomains = new ArrayList<>();
		for(int j = 0; j < n; j++)
			varDomains.add(new Value(("row "+(j+1)), ("row "+j)));
		for(int i = 0; i < n; i++)
			variables.add(new Variable("@column "+(i+1), null, varDomains, new Value[n]));
	}
	@Override
	public boolean isConsistent(Variable var, Value val) {
		long start = System.currentTimeMillis();
        for (int i=0; i<(Integer.parseInt((var.Name().split(" "))[1])-1); i++)
            if (((Value[]) variables.get(i).GetObject())[(Integer.parseInt((val.Name().split(" "))[1])-1)] != null) 
                return false;
        for (int i=(Integer.parseInt((var.Name().split(" "))[1])-1), j=(Integer.parseInt((val.Name().split(" "))[1])-1); i>=0 && j>=0; i--, j--) 
            if (((Value[]) variables.get(i).GetObject())[j]  != null) 
                return false;
        for (int i=(Integer.parseInt((var.Name().split(" "))[1])-1), j=(Integer.parseInt((val.Name().split(" "))[1])-1); i>=0 && j<variables.size(); i--, j++) 
            if (((Value[]) variables.get(i).GetObject())[j]  != null)
            	return false;
        ((Value[]) variables.get((Integer.parseInt((var.Name().split(" "))[1])-1)).GetObject())[(Integer.parseInt((val.Name().split(" "))[1])-1)] = val;
        
        NumberFormat formatter = new DecimalFormat("#0.000000000");
		long end = System.currentTimeMillis();
		System.out.println(formatter.format((end - start) / 1000d));
        return true;
	}
	@Override
	public boolean assignmentIsComplete() {
		for(Variable v : variables)
			if(v.GetValue() == null)
				return false;
		return true;
	}
	@Override
	public Variable SelectUnassignedVariable() {
		for(Variable v : variables)
			if(v.GetValue() == null)
				return v;
		return null;
	}
	@Override
	public Value Clear(Variable var) {
		Arrays.fill(((Value[])variables.get((Integer.parseInt((var.Name().split(" "))[1])-1)).GetObject()), null);
		return null;
	}
}