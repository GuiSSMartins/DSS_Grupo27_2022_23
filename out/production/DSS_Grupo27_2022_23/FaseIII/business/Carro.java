import java.util.ArrayList;

public class Carro {
	public Piloto _conduz;
	public Administrador _adiciona;
	public ArrayList<Jogador> _escolhe = new ArrayList<Jogador>();
	public ArrayList<Resultado> _referente = new ArrayList<Resultado>();
	public Categoria _pertence_a;
	public Cilindrada _caracterizado_pela;
	public Fiabilidade _caracterizada_pela;
	public Motor_Combustão _utiliza_um;
	public ArrayList<Pneu> _possui = new ArrayList<Pneu>();
	public ArrayList<Evento> _está_envolvido = new ArrayList<Evento>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._conduz != null ) {
			lHashCode += this._conduz.hashCode();
		}
		if ( this._adiciona != null ) {
			lHashCode += this._adiciona.hashCode();
		}
		if ( this._escolhe != null ) {
			lHashCode += this._escolhe.hashCode();
		}
		if ( this._referente != null ) {
			lHashCode += this._referente.hashCode();
		}
		if ( this._pertence_a != null ) {
			lHashCode += this._pertence_a.hashCode();
		}
		if ( this._caracterizado_pela != null ) {
			lHashCode += this._caracterizado_pela.hashCode();
		}
		if ( this._caracterizada_pela != null ) {
			lHashCode += this._caracterizada_pela.hashCode();
		}
		if ( this._utiliza_um != null ) {
			lHashCode += this._utiliza_um.hashCode();
		}
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( this._está_envolvido != null ) {
			lHashCode += this._está_envolvido.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Carro) {
			Carro lCarroObject = (Carro) aObject;
			boolean lEquals = true;
			lEquals &= ((this._conduz == lCarroObject._conduz)
				|| (this._conduz != null && this._conduz.equals(lCarroObject._conduz)));
			lEquals &= ((this._adiciona == lCarroObject._adiciona)
				|| (this._adiciona != null && this._adiciona.equals(lCarroObject._adiciona)));
			lEquals &= ((this._escolhe == lCarroObject._escolhe)
				|| (this._escolhe != null && this._escolhe.equals(lCarroObject._escolhe)));
			lEquals &= ((this._referente == lCarroObject._referente)
				|| (this._referente != null && this._referente.equals(lCarroObject._referente)));
			lEquals &= ((this._pertence_a == lCarroObject._pertence_a)
				|| (this._pertence_a != null && this._pertence_a.equals(lCarroObject._pertence_a)));
			lEquals &= ((this._caracterizado_pela == lCarroObject._caracterizado_pela)
				|| (this._caracterizado_pela != null && this._caracterizado_pela.equals(lCarroObject._caracterizado_pela)));
			lEquals &= ((this._caracterizada_pela == lCarroObject._caracterizada_pela)
				|| (this._caracterizada_pela != null && this._caracterizada_pela.equals(lCarroObject._caracterizada_pela)));
			lEquals &= ((this._utiliza_um == lCarroObject._utiliza_um)
				|| (this._utiliza_um != null && this._utiliza_um.equals(lCarroObject._utiliza_um)));
			lEquals &= ((this._possui == lCarroObject._possui)
				|| (this._possui != null && this._possui.equals(lCarroObject._possui)));
			lEquals &= ((this._está_envolvido == lCarroObject._está_envolvido)
				|| (this._está_envolvido != null && this._está_envolvido.equals(lCarroObject._está_envolvido)));
			return lEquals;
		}
		return false;
	}
	public abstract class Carro {
		private String _marca;
		private String _modelo;
		private int _potenciaCombustao;
		private int _cilindrada;
		private double _pAC;
		private double _fiabilidade;
		private String _id;

		public int hashCode() {
			int lHashCode = 0;
			if ( this._marca != null ) {
				lHashCode += this._marca.hashCode();
			}
			if ( this._modelo != null ) {
				lHashCode += this._modelo.hashCode();
			}
			if ( this._id != null ) {
				lHashCode += this._id.hashCode();
			}
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof Carro) {
				Carro lCarroObject = (Carro) aObject;
				boolean lEquals = true;
				lEquals &= ((this._marca == lCarroObject._marca)
					|| (this._marca != null && this._marca.equals(lCarroObject._marca)));
				lEquals &= ((this._modelo == lCarroObject._modelo)
					|| (this._modelo != null && this._modelo.equals(lCarroObject._modelo)));
				lEquals &= this._potenciaCombustao == lCarroObject._potenciaCombustao;
				lEquals &= this._cilindrada == lCarroObject._cilindrada;
				lEquals &= this._pAC == lCarroObject._pAC;
				lEquals &= this._fiabilidade == lCarroObject._fiabilidade;
				lEquals &= ((this._id == lCarroObject._id)
					|| (this._id != null && this._id.equals(lCarroObject._id)));
				return lEquals;
			}
			return false;
		}
	}
	public class C1 extends Carro.Carro {

		public boolean verificaFiabilidade(double aFiabilidade) {
			throw new UnsupportedOperationException();
		}

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof C1) {
				C1 lC1Object = (C1) aObject;
				boolean lEquals = true;
				return lEquals;
			}
			return false;
		}
	}
	public class C2 extends Carro.Carro {

		public boolean verificaCilindrada(int aCilindrada_) {
			throw new UnsupportedOperationException();
		}

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof C2) {
				C2 lC2Object = (C2) aObject;
				boolean lEquals = true;
				return lEquals;
			}
			return false;
		}
	}
	public class GT extends Carro.Carro {
		private double _taxaDeterioracao;

		public boolean verificaCilindrada(int aCilindrada) {
			throw new UnsupportedOperationException();
		}

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof GT) {
				GT lGTObject = (GT) aObject;
				boolean lEquals = true;
				lEquals &= this._taxaDeterioracao == lGTObject._taxaDeterioracao;
				return lEquals;
			}
			return false;
		}
	}
	public class SC extends Carro.Carro {

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof SC) {
				SC lSCObject = (SC) aObject;
				boolean lEquals = true;
				return lEquals;
			}
			return false;
		}
	}
	public class C1Hibrido extends Carro.C1 {
		private int _potenciaEletrico;

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof C1Hibrido) {
				C1Hibrido lC1HibridoObject = (C1Hibrido) aObject;
				boolean lEquals = true;
				lEquals &= this._potenciaEletrico == lC1HibridoObject._potenciaEletrico;
				return lEquals;
			}
			return false;
		}
	}
	public class C2Hibrido extends Carro.C2 {
		private int _potenciaEletrico;

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof C2Hibrido) {
				C2Hibrido lC2HibridoObject = (C2Hibrido) aObject;
				boolean lEquals = true;
				lEquals &= this._potenciaEletrico == lC2HibridoObject._potenciaEletrico;
				return lEquals;
			}
			return false;
		}
	}
	public class GTHibrido extends Carro.GT {
		private int _potenciaEletrico;

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof GTHibrido) {
				GTHibrido lGTHibridoObject = (GTHibrido) aObject;
				boolean lEquals = true;
				lEquals &= this._potenciaEletrico == lGTHibridoObject._potenciaEletrico;
				return lEquals;
			}
			return false;
		}
	}
}