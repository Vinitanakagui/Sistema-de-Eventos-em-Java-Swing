package modelo.pk;

import java.io.Serializable;
import java.util.Objects;

public class PresencaPK implements Serializable 
{
    private Integer matricula;
    private Integer idPresenca;

    public PresencaPK() {}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.matricula);
        hash = 23 * hash + Objects.hashCode(this.idPresenca);
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
        final PresencaPK other = (PresencaPK) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return Objects.equals(this.idPresenca, other.idPresenca);
    }

   
}
