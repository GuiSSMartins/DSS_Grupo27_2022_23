package DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subPartidas;

import DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subCatálogos.Piloto;
import DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subCatálogos.Carro;

import java.util.ArrayList;
import java.util.List;

public class Evento {
	private int idSecção;
	private int acontecimento;
	private List<String> idjogadoresEnvolvidos;


	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Evento) {
			Evento lEventoObject = (Evento) aObject;
			boolean lEquals = true;
			lEquals &= this.idSecção == lEventoObject.idSecção;
			lEquals &= this.acontecimento == lEventoObject.acontecimento;
			lEquals &= this.idjogadoresEnvolvidos == lEventoObject.idjogadoresEnvolvidos;
			return lEquals;
		}
		return false;
	}
}