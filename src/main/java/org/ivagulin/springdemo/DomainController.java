package org.ivagulin.springdemo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.ivagulin.springdemo.model.Domain;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class DomainController {
	@PersistenceContext
	private EntityManager em;

	@RequestMapping(method = RequestMethod.GET)
	public List<Domain> listDomains(){
		return em.createQuery("from Domain", Domain.class).getResultList();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public Domain postDomain(@RequestBody String hostname) {
		Domain d = new Domain();
		d.setHostname(hostname);
		d.setSerial(1);
		em.persist(d);
		return d;
	}
	
	@RequestMapping(path="/{domainId}")
	@Transactional
	public Domain putDomain(@PathVariable Integer domainId, @RequestBody String newHostname) {
		Domain d = em.find(Domain.class, domainId);
		d.setHostname(newHostname);
		d.setSerial(d.getSerial()+1);
		return d;
	}
}
