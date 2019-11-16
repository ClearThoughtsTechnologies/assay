package com.clearthoughts.clearcampus.assay.api.resources;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.clearthoughts.clearcampus.assay.api.TestPaperAPI;
import com.clearthoughts.clearcampus.assay.model.TestPaper;

@Component
public class TestPaperModelAssemler extends RepresentationModelAssemblerSupport<TestPaper, TestPaperResource> {

	public TestPaperModelAssemler() {
		super(TestPaperAPI.class, TestPaperResource.class);
	}

	@Override
	public TestPaperResource toModel(TestPaper tp) {
		TestPaperResource tpr = new TestPaperResource(tp);
		tpr.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TestPaperAPI.class).findTestPaperForId(tp.get_id())).withSelfRel()
				);
		return tpr;
	}

}
