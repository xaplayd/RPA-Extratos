package entities;

import java.util.Objects;

public class EmpresaBB {
	
	private Integer codEmp;
	private Integer codFil;
	private String chaveJ;
	private String pass;
	
	public EmpresaBB(Integer codEmp, Integer codFil, String chaveJ, String pass) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.chaveJ = chaveJ;
		this.pass = pass;
	}
	
	public EmpresaBB() {};

	public Integer getCodEmp() {
		return codEmp;
	}

	public void setCodEmp(Integer codEmp) {
		this.codEmp = codEmp;
	}

	public Integer getCodFil() {
		return codFil;
	}

	public void setCodFil(Integer codFil) {
		this.codFil = codFil;
	}

	public String getChaveJ() {
		return chaveJ;
	}

	public void setChaveJ(String chaveJ) {
		this.chaveJ = chaveJ;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chaveJ, codEmp, codFil, pass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaBB other = (EmpresaBB) obj;
		return Objects.equals(chaveJ, other.chaveJ) && Objects.equals(codEmp, other.codEmp)
				&& Objects.equals(codFil, other.codFil) && Objects.equals(pass, other.pass);
	}

	@Override
	public String toString() {
		return "Empresa [codEmp=" + codEmp + ", codFil=" + codFil + ", chaveJ=" + chaveJ + ", pass=" + pass + "]";
	}
	
	

}
