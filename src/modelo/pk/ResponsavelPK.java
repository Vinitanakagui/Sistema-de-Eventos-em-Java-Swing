package modelo.pk;

import java.io.Serializable;
import java.util.Objects;


public class ResponsavelPK implements Serializable
{
   
    private Integer idResponsavel;
    
    private Integer evento;

    public ResponsavelPK() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idResponsavel);
        hash = 71 * hash + Objects.hashCode(this.evento);
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
        final ResponsavelPK other = (ResponsavelPK) obj;
        if (!Objects.equals(this.idResponsavel, other.idResponsavel)) {
            return false;
        }
        return Objects.equals(this.evento, other.evento);
    }
    
 
    
}
