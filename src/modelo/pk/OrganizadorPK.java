package modelo.pk;

import java.io.Serializable;
import java.util.Objects;

public class OrganizadorPK  implements Serializable
{
    private Integer semana;
    
    private Integer idOrganizador;

    public OrganizadorPK() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.semana);
        hash = 29 * hash + Objects.hashCode(this.idOrganizador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrganizadorPK other = (OrganizadorPK) obj;
        if (!Objects.equals(this.semana, other.semana)) {
            return false;
        }
        return Objects.equals(this.idOrganizador, other.idOrganizador);
    }
 
    
    
}
