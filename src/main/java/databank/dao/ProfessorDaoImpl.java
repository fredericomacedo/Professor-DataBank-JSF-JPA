/*****************************************************************
 * File:  ProfessorDaoImpl.java Course materials (23W) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import databank.model.ProfessorPojo;
import ejb.ProfessorService;

/**
 * Description:  Implements the C-R-U-D API for the database
 * 
 * TODO 01 - Some components are managed by CDI.<br>
 * TODO 02 - Methods which perform DML need @Transactional annotation.<br>
 * TODO 03 - Fix the syntax errors to correct methods. <br>
 * TODO 04 - Refactor this class.  Move all the method bodies and EntityManager to a new service class (e.g. ProfessorService) which is a
 * singleton (EJB).<br>
 * TODO 05 - Inject the service class using EJB.<br>
 * TODO 06 - Call all the methods of service class from each appropriate method here.
 */
@Named
@ApplicationScoped
public class ProfessorDaoImpl implements ProfessorDao, Serializable {
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	//Get the log4j2 logger for this class
	private static final Logger LOG = LogManager.getLogger();

	
	@Inject
    private ProfessorService professorService;

    @Override
    public List<ProfessorPojo> readAllProfessors() {
        LOG.debug("reading all professors");
        return professorService.readAllProfessors();
    }

    @Override
    public ProfessorPojo createProfessor(ProfessorPojo professor) {
        LOG.debug("creating a professor = {}", professor);
        return professorService.createProfessor(professor);
    }

    @Override
    public ProfessorPojo readProfessorById(int professorId) {
        LOG.debug("read a specific professor = {}", professorId);
        return professorService.readProfessorById(professorId);
    }

    @Override
    public ProfessorPojo updateProfessor(ProfessorPojo professorWithUpdates) {
        LOG.debug("updating a specific professor = {}", professorWithUpdates);
        return professorService.updateProfessor(professorWithUpdates);
    }

    @Override
    public void deleteProfessorById(int professorId) {
        LOG.debug("deleting a specific professorID = {}", professorId);
        professorService.deleteProfessorById(professorId);
    }

}
