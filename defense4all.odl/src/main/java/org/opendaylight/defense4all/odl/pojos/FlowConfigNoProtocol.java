/**
 * Copyright (c) <2013> <Radware Ltd.> and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * @author Gera Goft 
 * @version 0.1
 */

package org.opendaylight.defense4all.odl.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.opendaylight.defense4all.core.DFHolder;
import org.opendaylight.defense4all.core.NetNode;
import org.opendaylight.defense4all.framework.core.ExceptionControlApp;
import org.opendaylight.defense4all.odl.OdlFlowConfigInfo;
import org.opendaylight.defense4all.odl.controller.Connector.JsonPreprocessor;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class FlowConfigNoProtocol {
	
	public static final Short IP_ETHER_TYPE = 0x800;
	public static final Short ARP_ETHER_TYPE = 0x806;
	
	/* Actions from org.opendaylight.controller.sal.action.ActionType */
	public enum ActionType {
		DROP,
	    INTERFACE,
	    SW_PATH, // OF Local
	    HW_PATH, // "send to normal"
	    OUTPUT, // physical port. e.g. "OUTPUT=3"
	    ENQUEUE,
	    SET_NW_DST
	}

	public String  name;			
	public String  cookie; // Long - number
	public SentNode node;			
	public short   ingressPort;		
	public short   vlanId;
	public short   priority; 
	public String  etherType;
	public String  dlSrc;		public String dlDst;
	public String  nwSrc;		public String nwDst;
	public String  tpSrc;		public String tpDst;
	public short   idleTimeout;	public short  hardTimeout;
	public boolean installInHw;

    public FlowConfigNoProtocol() {}
    
    public FlowConfigNoProtocol(OdlFlowConfigInfo flowInfo) throws ExceptionControlApp {
    	
    	name = flowInfo.key; 
    	cookie = String.valueOf(flowInfo.id); 
    	String nodeId = (String) DFHolder.get().netNodesRepo.getCellValue(flowInfo.nodeLabel, NetNode.ID);
    	node = new SentNode(nodeId, "OF");
    	ingressPort = flowInfo.ingressPort;	
    	vlanId = flowInfo.vlanId; 
    	priority = flowInfo.floor;
    	etherType = String.valueOf(flowInfo.etherType);
    	dlSrc = flowInfo.dlSrc; dlDst = flowInfo.dlDst;
    	nwSrc = flowInfo.nwSrc; nwDst = flowInfo.nwDst;
    	tpSrc = flowInfo.tpSrc; tpDst = flowInfo.tpDst;
    	idleTimeout = flowInfo.idleTimeout; hardTimeout = flowInfo.hardTimeout;
    	installInHw = true; 
    }
    
    @Override
    public boolean equals(Object other) {
    	
    	if(! FlowConfigNoProtocol.class.isInstance(other)) return false;
    	
    	FlowConfigNoProtocol otherFlowConfig = (FlowConfigNoProtocol) other;
    	if(name == null || !name.equals(otherFlowConfig.name)) return false;
    	if(cookie == null || !cookie.equals(otherFlowConfig.cookie)) return false;
    	if(node==null || node.id==null || !((String) node.id).equals((String)(otherFlowConfig.node.id))) return false;
    	if(ingressPort != otherFlowConfig.ingressPort) return false;
    	if(vlanId != otherFlowConfig.vlanId) return false;
    	if(priority != otherFlowConfig.priority) return false;
    	if(etherType == null || !etherType.equals(otherFlowConfig.etherType)) return false;
    	if(nwDst == null || !nwDst.equals(otherFlowConfig.nwDst)) return false;
    	if(idleTimeout != otherFlowConfig.idleTimeout) return false;
    	if(hardTimeout != otherFlowConfig.hardTimeout) return false;
    	if(installInHw != otherFlowConfig.installInHw) return false;
    	// Skip actions match for now
    	return true;
    }

    public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getCookie() {return cookie;}
	public void setCookie(String cookie) {this.cookie = cookie;}

	public SentNode getNode() {return node;}
	public void setNode(SentNode node) {this.node = node;}

	public short getIngressPort() {return ingressPort;}
	public void setIngressPort(short ingressPort) {this.ingressPort = ingressPort;}

	public short getVlanId() {return vlanId;}
	public void setVlanId(short vlanId) {this.vlanId = vlanId;}

	public short getPriority() {return priority;}
	public void setPriority(short priority) {this.priority = priority;}

	public String getEtherType() {return etherType;}
	public void setEtherType(String etherType) {this.etherType = etherType;}

	public String getDlSrc() {return dlSrc;}
	public void setDlSrc(String dlSrc) {this.dlSrc = dlSrc;}

	public String getDlDst() {return dlDst;}
	public void setDlDst(String dlDst) {this.dlDst = dlDst;}

	public String getNwSrc() {return nwSrc;}
	public void setNwSrc(String nwSrc) {this.nwSrc = nwSrc;}

	public String getNwDst() {return nwDst;}
	public void setNwDst(String nwDst) {this.nwDst = nwDst;}

	public String getTpSrc() {return tpSrc;}
	public void setTpSrc(String tpSrc) {this.tpSrc = tpSrc;}

	public String getTpDst() {return tpDst;}
	public void setTpDst(String tpDst) {this.tpDst = tpDst;}

	public short getIdleTimeout() {return idleTimeout;}
	public void setIdleTimeout(short idleTimeout) {this.idleTimeout = idleTimeout;}

	public short getHardTimeout() {return hardTimeout;}
	public void setHardTimeout(short hardTimeout) {this.hardTimeout = hardTimeout;}
	
	public boolean isInstallInHw() {return installInHw;}
	public void setInstallInHw(boolean installInHw) {this.installInHw = installInHw;}

	@Override
    public String toString() {
        return "FlowConfig [name=" + name + ", cookie=" + cookie + ", node=" + node
        		+ ", ingressPort=" + ingressPort + ", vlanId=" + vlanId + ", priority=" + priority
        		+ ", etherType=" + etherType
        		+ ", dlSrc=" + dlSrc + ", dlDst=" + dlDst + ", nwSrc=" + nwSrc + ", nwDst=" + nwDst
        		+ ", tpSrc=" + tpSrc + ", tpDst=" + tpDst 
        		+ ", idleTimeout=" + idleTimeout + ", hardTimeout=" + hardTimeout 
        		+ ", installInHw=" + installInHw + getDerivedAdditionsToString() + "]";
    }
	
	public String getDerivedAdditionsToString() {return "";}

	public static JsonPreprocessor getJsonPreprocessor() {
		return new JsonPreprocessor() {			
			@Override
			public String preProcess(String jsonStr) {return jsonStr;}
		};
	}
}
