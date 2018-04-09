package edu.asupoly.ser422.lab3.resources;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.xmlrpc.base.Array;

import edu.asupoly.ser422.lab3.model.PhoneEntry;
import edu.asupoly.ser422.lab3.model.PhoneBook;
import edu.asupoly.ser422.lab3.services.PhoneService;
import edu.asupoly.ser422.lab3.services.PhoneServiceImpl;

/**
 * @apiDefine BadRequestError
 * @apiError (Error 4xx) {400} BadRequest Bad Request Encountered
 * 
 * */
/**
 * @apiDefine ActivityNotFoundError
 * @apiError (Error 4xx) {404} NotFound Activity cannot be found
 * 
 * */
/**
 * @apiDefine BadMethodError
 * @apiError (Error 4xx) {405} BadMethod Method not allowed
 * 
 * */
/**
 * @apiDefine InternalServerError
 * @apiError (Error 5xx) {500} InternalServerError Error with server
 * */
/**
 * @apiDefine NotImplementedError
 * @apiError (Error 5xx) {501} NotImplemented Resource not yet implemented
 * */

@Path("/phoneEntry")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class PhoneEntryResource {

	private static PhoneService __pService = PhoneServiceImpl.getInstance();
	@Context
	private UriInfo _uriInfo;
	
	/**
	 * @api {get} /phoneEntry/unlisted Get list of all unlisted phone entries.
	 * @apiName getUnlisted
	 * @apiGroup Entries
	 * 
	 * @apiUse BadRequestError
	 * @apiUse ActivityNotFoundError
	 * @apiUse BadMethodError
	 * @apiUse InternalServerError
	 * @apiUse NotImplementedError
	 * 
	 * @apiDescription This method returns all the numbers in the system
	 * that has not been added to a phone book. A phone number can only be
	 * added once so it is either in a phone book or unlisted.
	 * 
	 * @apiSuccessExample Success-Response: 
	 * 		{json} HTTP/1.1 200 OK
	 * 
	 * 		[{"firstname":"dave","lastname":"matthew","phone":"3172944521","book":" ","id":0},
	 * 		 {"firstname":"mike","lastname":"jones","phone":"8125551212","book":" ","id":1}]
	 * */
	@GET
	@Path("/unlisted")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getUnlisted() {
		List<PhoneEntry> copy = __pService.getUnlisted();

		try {
			String aString = new ObjectMapper().writeValueAsString(copy);
			return Response.status(200).entity(aString).build();
		} catch (Exception exc) {
			return Response.status(200).entity("No entries exist for that input").build();
		}
	}
	
	/**
	 * @api {get} /phoneEntry/{phSearch} Get entry based on phone number
	 * @apiName getPhoneEntry
	 * @apiGroup Entries
	 * 
	 * @apiParam {String} phoneNum Mandatory phone number.
	 * 
	 * @apiDescription Returns details associated with phone number. Searches
	 * unlisted and phone books. Provides user first and last name, name of phone book
	 * if it belongs to one, and the user id.
	 * 
	 * 
	 * @apiUse BadRequestError
	 * @apiUse ActivityNotFoundError
	 * @apiUse BadMethodError
	 * @apiUse InternalServerError
	 * @apiUse NotImplementedError
	 * 
	 * @apiSuccessExample Success-Response: 
	 * 		{json} HTTP/1.1 200 OK
	 * 		[{"firstname":"dave","lastname":"matthew","phone":"3172944521","book":" ","id":0}]
	 * 
	 * */
	//USE PATH PARAM -it goes to specific resource
	@GET
	@Path("/{phSearch}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getPhoneEntry(@PathParam("phSearch") String phoneNum) {

		List<PhoneEntry> result = __pService.getEntry(phoneNum);

		try {
			String s = new ObjectMapper().writeValueAsString(result);
			return Response.status(200).entity(s).build();
		} catch (Exception exc) {
			return Response.status(200).entity("No entries exist for that input").build();
		}
	}
	/**
	 * @api {post} /phoneEntry/newEntry Create phone entry.
	 * @apiName createEntry
	 * @apiGroup Entries
	 * 
	 * @apiParam {String} fname  	Mandatory Firstname.
	 * @apiParam {String} lname     Mandatory Lastname.
	 * @apiParam {String} phoneNum  Mandatory Phone number of the User.
	 * 
	 * @apiDescription Takes input of first name, last name, and phone number and creates
	 * an unlisted entry. UserID is assigned by the program.
	 * 
	 * 
	 * @apiUse BadRequestError
	 * @apiUse ActivityNotFoundError
	 * @apiUse BadMethodError
	 * @apiUse InternalServerError
	 * @apiUse NotImplementedError
	 * 
	 * @apiSuccessExample Success-Response: 
	 * 		{json} HTTP/1.1 201 Created
	 * 		
	 * 		{ "entryId" : "0"}
	 * 
	 * @apiSuccessExample Success-Response:
	 * 
	 * 		Location →http://localhost:8080/lab3_dmarnol2/rest/phoneEntry/newEntry/0
	 * 
	 * */
	@POST
	@Path("/newEntry")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createEntry(@FormParam("fname") String fname, @FormParam("lname") String lname,
			@FormParam("phoneNum") String phoneNum) {

		int res = __pService.createEntry(fname, lname, phoneNum);

		if (res == -1) {
			return Response.status(500).entity("{ \" EXCEPTION CREATING ENTRY \"}").build();
		} else if (res == -2) {
			return Response.status(400).entity("{ \" Creation not allowed: Entry already exists \"}").build();
		}
		return Response.status(201).header("Location", String.format("%s/%s", _uriInfo.getPath().toString(), res)) // getRequestUri().toString(),
																														// res))
				.entity("{ \"entryId\" : \"" + res + "\"}").build();
	}
	
	/**
	 * @api {delete} /phoneEntry/{id} Delete phone entry.
	 * @apiName deleteEntry
	 * @apiGroup Entries
	 * 
	 * @apiParam {Number} id  Mandatory The ID assigned to user at creation.
	 * 
	 * @apiDescription Deletes phone entry from either unlisted file or phone book.
	 * 
	 * 
	 * @apiUse BadRequestError
	 * @apiUse ActivityNotFoundError
	 * @apiUse BadMethodError
	 * @apiUse InternalServerError
	 * @apiUse NotImplementedError
	 * 
	 * @apiSuccessExample Success-Response: 
	 * 		HTTP/1.1 204 No Content
	 * 
	 * 
	 * */
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteEntry(@PathParam("id") int id) {

		int res = __pService.removeEntry(id);
		// if (__pService.removeEntry(phoneNum)) {

		if (res == -1) {
			return Response.status(500).entity("{ \" EXCEPTION CREATING ENTRY \"}").build();
		} else if (res == -2) {
			return Response.status(404, "{ \"message \" : \"No Entry for id: " + id + "\"}").build(); 
		} else {
			return Response.status(204).build();
		}
	}
	
	/**
	 * @api {put} /phoneEntry Change first name and last name.
	 * @apiName updateEntry
	 * @apiGroup Entries
	 * 
	 * @apiParam {String} userId  Mandatory User identifcation number.
	 * @apiParam {String} fname   Mandatory Firstname of the User.
	 * @apiParam {String} lname   Mandatory Lastname of the User.
	 * 
	 * @apiDescription Changes the first and last name of user associated with
	 * phone entry.
	 * 
	 * 
	 * @apiUse BadRequestError
	 * @apiUse ActivityNotFoundError
	 * @apiUse BadMethodError
	 * @apiUse InternalServerError
	 * @apiUse NotImplementedError
	 * 
	 * @apiSuccessExample Success-Response: 
	 * 		HTTP/1.1 204 No Content
	 * 		Location → http://localhost:8080/lab3_dmarnol2/rest/phoneEntry/newEntry/3
	 * 
	 * */
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateEntry(@FormParam("userId") String userId, @FormParam("fname") String fname,
			@FormParam("lname") String lname) {
		if (__pService.updateEntry(userId, fname, lname)) {
			return Response.status(201)
					.header("Location", String.format("%s/%s", _uriInfo.getAbsolutePath().toString(), userId))
					.entity("{ \"entryId\" : \"" + userId + "\"}").build();
		} else {
			return Response.status(404, "{ \"message \" : \"No Entry for " + userId + "\"}").build(); 
		}

	}

}
