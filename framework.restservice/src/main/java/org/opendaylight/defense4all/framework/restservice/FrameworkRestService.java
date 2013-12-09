/**
 * Copyright (c) <2013> <Radware Ltd.> and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * @author Gera Goft 
 * @version 0.1
 */

package org.opendaylight.defense4all.framework.restservice;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.opendaylight.defense4all.framework.core.FMHolder;


@Path("/")
public class FrameworkRestService {

	private static Logger log = LoggerFactory.getLogger(FrameworkRestService.class);

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Path("hostaddress")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHostAddress() {

		log.debug("getHostaddress: invoked");
		String hostaddress = FMHolder.get().getHostAddr();
		return hostaddress;
	}

	@POST
	@Path("hostaddress")
	@Consumes(MediaType.APPLICATION_JSON)
	//	@Produces(MediaType.APPLICATION_JSON)
	public void setHostAddress(String hostaddress, @Context HttpServletResponse servletResponse) throws Exception {

		log.debug("setHostAddress: invoked");
		if (hostaddress == null || hostaddress.isEmpty()) {
			log.debug("setHostaddress: hostaddress is null or empty.");
			servletResponse.sendError(400);
		}
		try {
			FMHolder.get().getFrameworkMgmtPoint().setHostAddr(hostaddress);
		} catch ( Throwable e) {  
			log.error("Failed to set hostaddress - " + e.getLocalizedMessage());
			servletResponse.sendError(400);
		}
	}
}
