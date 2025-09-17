package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Curso;

public class CursoDAO extends ControleDAO<Curso>
{
    
    public List<Curso> buscarTodos()
    {
        EntityManager em = getEntityManager();
        TypedQuery<Curso> query = em.createNamedQuery("Curso.buscarTodos", Curso.class);
            
        
        return query.getResultList();
    }
    
    public Curso buscarCurso(String nome)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Curso> query = em.createNamedQuery("Curso.buscarCurso", Curso.class)
                .setParameter("nome", nome);
        
        return query.getSingleResult();
    }
    
    
    
}
