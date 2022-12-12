
package DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.SubUtilizadores;

import DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subCatálogos.Campeonato;

import java.util.ArrayList;
public class Administrador {
	public ArrayList<Campeonato> _adiciona = new ArrayList<Campeonato>();
	public Utilizador _é_um;


	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Administrador) {
			Administrador lAdministradorObject = (Administrador) aObject;
			boolean lEquals = true;
			lEquals &= ((this._adiciona == lAdministradorObject._adiciona)
				|| (this._adiciona != null && this._adiciona.equals(lAdministradorObject._adiciona)));
			lEquals &= ((this._é_um == lAdministradorObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lAdministradorObject._é_um)));
			return lEquals;
		}
		return false;
	}
}