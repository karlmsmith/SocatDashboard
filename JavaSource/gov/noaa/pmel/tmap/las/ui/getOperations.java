/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package gov.noaa.pmel.tmap.las.ui;

import gov.noaa.pmel.tmap.las.jdom.LASConfig;
import gov.noaa.pmel.tmap.las.product.server.LASConfigPlugIn;
import gov.noaa.pmel.tmap.las.util.ContainerComparator;
import gov.noaa.pmel.tmap.las.util.Operation;
import gov.noaa.pmel.tmap.las.util.View;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

/** 
 * MyEclipse Struts
 * Creation date: 03-13-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class getOperations extends ConfigService {
	private static Logger log = LogManager.getLogger(getOperations.class.getName());
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LASConfig lasConfig = (LASConfig)servlet.getServletContext().getAttribute(LASConfigPlugIn.LAS_CONFIG_KEY);
		
		String dsID = request.getParameter("dsid");
		String varID = request.getParameter("varid");
		String view = request.getParameter("view");
		String format = request.getParameter("format");
		
		if ( format == null ) {
			format = "json";
		}
		log.info("Starting: getOperations.do?dsid="+dsID+"&varid="+varID+"&view="+view+".");
		try {
			ArrayList<Operation> operations;
			if ( view != null) {
				operations = lasConfig.getOperations(view, dsID, varID);

				if ( operations.size() <= 0 ) {
					sendError(response, "operations", format, "No operations found.");
					return null;
				} else {
					// Check to see if there's something in there.
					Operation op = operations.get(0);
					String name = op.getName();
					if ( name == null || name.equals("") ) {
						sendError(response, "operations", format, "No operations found.");
						return null;
					}
				}
			} else {
				operations = new ArrayList<Operation>();
				ArrayList<View> views = lasConfig.getViewsByDatasetAndVariable(dsID, varID);
				HashMap<String, Operation> allOps = new HashMap<String, Operation>();
				for (Iterator viewIt = views.iterator(); viewIt.hasNext();) {
					View aView = (View) viewIt.next();
					ArrayList<Operation> ops = lasConfig.getOperations(aView.getValue(), dsID, varID);
					for (Iterator opsIt = ops.iterator(); opsIt.hasNext();) {
						Operation op = (Operation) opsIt.next();
						String id = op.getID();
						allOps.put(id, op);
					}
				}
				for (Iterator idIt = allOps.keySet().iterator(); idIt.hasNext();) {
					String id = (String) idIt.next();
					operations.add(allOps.get(id));
				}
				
			}
			Collections.sort(operations, new ContainerComparator("order", "name"));

			PrintWriter respout = response.getWriter();

			if ( format.equals("xml") ) {
				response.setContentType("application/xml");
				respout.print(Util.toXML(operations, "operations"));
			} else {
				response.setContentType("application/json");
				JSONObject json_response = toJSON(operations, "operations");
				log.debug(json_response.toString(3));
				json_response.write(respout);      
			}

			// JDOMException, JSONException and IOException expected
		} catch (Exception e) {
			sendError(response, "operations", format, e.toString());
		} 

		log.info("Finished: getOperations.do?dsid="+dsID+"&varid="+varID+"&view="+view+".");
		return null;
	}
	private JSONObject toJSON(ArrayList<Operation> operations, String wrapper) throws JSONException {
		JSONObject json_response = new JSONObject();
		JSONObject operations_object = new JSONObject();
		for (Iterator operIt = operations.iterator(); operIt.hasNext();) {
			Operation op = (Operation) operIt.next();
			JSONObject operation = op.toJSON();        
			operations_object.array_accumulate("operation", operation);
		}
		operations_object.put("status", "ok");
		operations_object.put("error", "");
		json_response.put(wrapper, operations_object);
		return json_response;
	}
	private JSONObject toJSONwithoutWrapper(ArrayList<Operation> operations) throws JSONException {
		JSONObject operations_object = new JSONObject();
		for (Iterator operIt = operations.iterator(); operIt.hasNext();) {
			Operation op = (Operation) operIt.next();
			JSONObject operation = op.toJSON();        
			operations_object.array_accumulate("operation", operation);
		}
		return operations_object;
	}
}