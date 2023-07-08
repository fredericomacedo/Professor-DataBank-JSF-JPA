package ejb;



import java.util.List;

import javax.ejb.Singleton;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import databank.model.ProfessorPojo;

@Singleton
public class ProfessorService {
    private static final Logger LOG = LogManager.getLogger();

    @PersistenceContext( name = "PU_DataBank")
    private EntityManager em;

    
    public List<ProfessorPojo> readAllProfessors() {
        LOG.debug("reading all professors");
        TypedQuery<ProfessorPojo> allProfessorsQuery = em.createNamedQuery(ProfessorPojo.PROFESSOR_FIND_ALL, ProfessorPojo.class);
        return allProfessorsQuery.getResultList();
    }
    
    @Transactional
    public ProfessorPojo createProfessor(ProfessorPojo professor) {
        LOG.debug("creating a professor = {}", professor);
        em.persist(professor);
        return professor;
    }

    public ProfessorPojo readProfessorById(int professorId) {
        LOG.debug("read a specific professor = {}", professorId);
        return em.find(ProfessorPojo.class, professorId);
    }

    @Transactional
    public ProfessorPojo updateProfessor(ProfessorPojo professorWithUpdates) {
        LOG.debug("updating a specific professor = {}", professorWithUpdates);
        ProfessorPojo mergedProfessorPojo = em.merge(professorWithUpdates);
        return mergedProfessorPojo;
    }

    @Transactional
    public void deleteProfessorById(int professorId) {
        LOG.debug("deleting a specific professorID = {}", professorId);
        ProfessorPojo professor = readProfessorById(professorId);
        LOG.debug("deleting a specific professor = {}", professor);
        if (professor != null) {
            em.remove(professor);
        }
    }
}
